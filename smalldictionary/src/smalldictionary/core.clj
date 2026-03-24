(ns smalldictionary.core
  (:gen-class)
  (:require   [smalldictionary.dictionary :refer :all]
              [smalldictionary.ngrams :refer :all]
              [smalldictionary.vectors :refer :all]))

;; Maximum words that needs to be suggested.
(def suggestions_size 7)

;; Calculates the angle between two vectors (cos_theta) and 
;; retuns a map entry tuple.
(defn with_angle [query, entry]
  (clojure.lang.MapEntry. (key entry) (cos_theta_of (val entry) query)))

;; Perform the calculation of the cos_theta (angle between) the
;; given query word and the words in the dictionary utilizing
;; the query vectorizer and the suggestion word matrix.
(defn vectors_cos_theta [i_word]
  (let [query (query_to_vector i_word)]
    (map (partial with_angle query) word_vectors)))

;;Iterate over all the vectors to find the similar suggestions.
(defn similar_suggestions [i_word]
  (let [suggestions (take suggestions_size (sort-by val > (vectors_cos_theta i_word)))]
    (into {} (filter (fn [entry] (> (val entry) 0.0)) suggestions))))

;; Read a word from the console.
(defn read_word []
  (println "Type incomplete word: ")
  (let [i_word (read-line)]    
      (println "\nSuggestions: ")
      (println (similar_suggestions (clojure.string/lower-case i_word)))))

;; Program starts from here.
(defn -main  [& args]  (read_word))

