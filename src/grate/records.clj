(ns grate.records
  (:require [grate.record :as record]))

(defn from-file
  [file-location]
  (with-open [reader (clojure.java.io/reader file-location)]
    (doall (map record/parse (line-seq reader)))))