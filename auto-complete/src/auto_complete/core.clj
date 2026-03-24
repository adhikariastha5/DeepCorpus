(ns auto-complete.core
  (:gen-class)
  (:require [clojure.java.io :as io])
  (:require [clojure.string :as str])
  )

; Take 1k words from 10k words of dictionary
(defn count-words [file n]
  (->> file
    slurp
    clojure.string/lower-case
    (re-seq #"\w+")
    (shuffle)
    (take n)))

; (def data_file (io/file (io/resources "10000.txt")))
; (def word_1k (sort(count-words data_file)))
(def word_1k (sort(count-words "/home/adhikari/DeepCorpus/auto-complete/resources/10000.txt" 1000)))

; Create multigram from the 1k words

(defn singlegram [n,word]    
    (for [i (range(+ (- (count word) n) 1))] (str/join (subvec (str/split word #"") i (+ i n))))
    )

(defn multigram [list_word]
    
    (if (= (type list_word) (type "ant"))
    (let[list_n (flatten(vec [2 3 4]))]      
        (flatten (for [x list_n]
      (singlegram x list_word))))
    (let[list_n (flatten(vec [2 3 4]))
        list_word (flatten(vec list_word))]
        (flatten (for [x list_n
      y list_word]
      (singlegram x y)))))
    )

(def dictionary_unique_multigram (sort(distinct(multigram word_1k))))

(defn word_vector [word]
    (let [gram (frequencies(multigram word))]
        (defn unique_vector [new_word]
            (get gram new_word 0))
        (map unique_vector dictionary_unique_multigram)
        )
    )

(def count_vector (map word_vector word_1k))

(defn magnitude [vect] 
    (Math/sqrt (reduce + (map (fn [item] (* item item)) vect))))

(defn cos_similarity [word_vec1,word_vec2]
    (let [denominator_value (*(magnitude word_vec1)(magnitude word_vec2))
          numerator_value (reduce + (map (fn [bi_item] (* (first bi_item) (last bi_item))) (map vector word_vec1 word_vec2)))
          ]        
        (if (<= denominator_value 0)
            0.0
            (float (/ numerator_value denominator_value))
            )
        )
    )

(defn similarity [word]
    (let[cos_angle (map (partial cos_similarity (word_vector word)) count_vector) ]
        (take 5 (sort-by first > (map vector cos_angle word_1k)))
        )
    )


(defn -main
  [& args]
  (println (similarity "able")))
