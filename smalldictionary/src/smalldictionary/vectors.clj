(ns smalldictionary.vectors
  (:gen-class)
  (:require    [smalldictionary.dictionary :refer :all]
               [smalldictionary.ngrams :refer :all]))

;; Default frequency of word.
(def default_frequency 0)

;; Defins word and their ngrams in hash map.
(def word_grams (zipmap file_data (map ngrams_of file_data)))

;; Defines total entries in the dictionary.
(def total_size (count file_data))

;; Define vector of grams.
(def grams (sort (vec (distinct (flatten (vals word_grams))))))

;; Define grams frequency 
(def grams_frequency (frequencies (flatten (vals word_grams))))

;; Returns the frequency of given gram segment, 0 in case of absence.
(defn frequency_of [gram] (get grams_frequency gram default_frequency))

;; Defines the indices of the grams.
(def grams_index (map-indexed vector grams))

(defn gram_set_filter [word_set gram_index]
  (if (contains? word_set (last gram_index)) (frequency_of (last gram_index)) default_frequency))

;; Generates a vector from the query word by utilizing the ngram policy
;; and the gram frequencies.
(defn query_to_vector [query]
  (let [query_set (set (ngrams_of query))]
    (map (partial gram_set_filter query_set) grams_index)))

;; Generates a vector from the given word by utilizing the ngram policy
;; and the gram frequencies.
(defn word_to_vector [word]
  (let [word_set (set (get word_grams word))]
    (map (partial gram_set_filter word_set) grams_index)))

;; Generates all the word vectors and store in the hash map.
(def word_vectors (zipmap file_data (map word_to_vector file_data)))

;; Numerator of cos-theta calculation.
(defn numerator_part [word query]
  (reduce + (map (fn [bi_item] (* (first bi_item) (last bi_item))) (map vector word query))))

;; Calculate the magitude of the given vector.
(defn magnitude [vec] (Math/sqrt (reduce + (map (fn [item] (* item item)) vec))))

;; Denominator of cos-theta calulation.
(defn denominator_part [word query] (* (magnitude word) (magnitude query)))

;; Calculate the dot product of two vectors.
(defn cos_theta_of [word query]
  (let [divisor (denominator_part word query)]
    (if (<= divisor 0) 0.0 (float (/ (numerator_part word query) divisor)))))
