(ns grate.record.repository
  (:require [grate.record.parser :as record]
            [grate.record.validator :as validator]))

(def records (atom []))

(defn load-from-file
  "Loads records from multi-line text file with one record per line"
  [file-location]
  (swap! records concat (filter validator/valid? (with-open [reader (clojure.java.io/reader file-location)]
                                                   (doall (map record/parse (line-seq reader))))))
  @records)