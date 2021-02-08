(ns grate.cli
  (:require [grate.record.serializer :as serializer]))

(defn print-records
  [records out]
  (doseq [record records] (out record)))

(defn print-report
  [header records out comparator]
  (out header)
  (print-records
    (map serializer/to-csv
         (sort comparator records)) out))

