(ns grate.file
  (:require [grate.record :as record]))

(defn read-records
  [file-location]
  (with-open [reader (clojure.java.io/reader file-location)]
    (doall (map record/parse (line-seq reader)))))