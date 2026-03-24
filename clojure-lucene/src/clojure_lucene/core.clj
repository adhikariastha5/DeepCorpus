(ns clojure-lucene.core
  (:gen-class)
  (:import  
            [org.apache.lucene.analysis.standard StandardAnalyzer StandardTokenizer]            
            [org.apache.lucene.analysis.core KeywordAnalyzer SimpleAnalyzer]
            [org.apache.lucene.store FSDirectory RAMDirectory]
            [org.apache.lucene.util Version]
            [org.apache.lucene.index IndexWriterConfig IndexWriter DirectoryReader]
            [org.apache.lucene.search IndexSearcher Sort SortField SortField$Type]
            [org.apache.lucene.queryparser.classic QueryParser]
            [org.apache.lucene.analysis.tokenattributes CharTermAttribute]
            [org.apache.lucene.document Document Field StoredField LatLonPoint
                                        TextField IntRangeDocValuesField FloatDocValuesField StringField]           
            [org.wiseyak.analyzer NGramAnalyzer]
            [java.io File])
  (:require [clojure.java.io :as io]))                                    

(def analyzer (new NGramAnalyzer))

(def indexDir (File. "/home/adhikari/DeepCorpus/clojure-lucene/resources"))
(def dataDir (File. "/home/adhikari/DeepCorpus/clojure-lucene/resources/10000.txt"))
(def config (IndexWriterConfig. analyzer))
(def suffix "jar")

; (def indexwriter (IndexWriter.))

(def numIndex (IndexWriter indexDir dataDir suffix))

; (def fileopen (-> home
;   (io/as-file)
;   ; (.getPath)
;   (directoryy/open)
;   ))


; (def directory (FSDirectory/open (io/as-file "10000.txt")))
; (def directory (FSDirectory/open (file(.getPath))))
; (def indexwriter (IndexWriter.directory analyzer))
; (defn gen-frag [ query text]
;   (let [
;         analyzer StandardAnalyzer 
;         query (.parse (QueryParser. "" analyzer) query) 
;         highlighter (org.apache.lucene.search.highlight.Highlighter. (org.apache.lucene.search.highlight.SimpleHTMLFormatter.) (org.apache.lucene.search.highlight.QueryScorer. query))
;         tokenStream (org.apache.lucene.search.highlight.TokenSources/getTokenStream "default" text analyzer)
;         frag (.getBestTextFragments highlighter tokenStream text false 4)
;   ]
;     frag
;   )  
; )

; (def some_value (StandardTokenizer "I am happy"))
; (defn read_file [file]
;   (->> file
;   slurp
;   ))

(defn -main
  "I don't do a whole lot...yet."
  [& args]
   (println numIndex))
