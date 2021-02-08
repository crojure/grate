(ns grate.records
  (:require [grate.record :as record]))

(defn from-string
  [string]
  (filter identity (map record/parse (clojure.string/split-lines string))))

(defn from-file
  [file-location]
  (filter identity (with-open [reader (clojure.java.io/reader file-location)]
                     (doall (map record/parse (line-seq reader))))))