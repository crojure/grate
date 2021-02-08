(ns grate.record.loader
  (:require [grate.record.parser :as record]
            [grate.record.validator :as validator]))

(defn from-file
  "Loads records from multi-line text file with one record per line"
  [file-location]
  (filter validator/valid? (with-open [reader (clojure.java.io/reader file-location)]
                             (doall (map record/parse (line-seq reader))))))