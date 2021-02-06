(ns grate.file
  (:require [grate.record :as record]))

(defn load
  [file-location]
  (with-open [reader (clojure.java.io/reader file-location)]
    (map record/parse (line-seq reader))))