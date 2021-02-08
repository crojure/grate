(ns grate.record.repository
  (:require [grate.record.loader :as loader]))

(def records (atom []))

(defn add
  [record]
  (swap! records conj record)
  record)

(defn delete-all
  []
  (reset! records []))

(defn find-all [] @records)

(defn find-all-sorted-by
  [comparator]
  (sort comparator @records))

(defn load-from-file
  "Loads records from multi-line text file with one record per line"
  [file-location]
  (swap! records concat
         (loader/from-file file-location))
  @records)

