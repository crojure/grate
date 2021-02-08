(ns grate.record.loader
  (:require [grate.record :as record]))

(defn from-string
  "Loads records from multi-line string with one record per line"
  [string]
  (filter identity (map record/parse (clojure.string/split-lines string))))

(defn from-file
  "Loads records from multi-line text file with one record per line"
  [file-location]
  (filter identity (with-open [reader (clojure.java.io/reader file-location)]
                     (doall (map record/parse (line-seq reader))))))