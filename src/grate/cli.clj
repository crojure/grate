(ns grate.cli (:require [grate.record.serializer :as serializer]
                        [grate.record.loader :as loader]
                        [grate.record.comparator :as comparator]))

(defn print-records
  [records out]
  (doseq [record records] (out record)))

(defn print-report
  [header records out comparator]
  (out header)
  (print-records
    (map serializer/to-csv
         (sort comparator records)) out))

(defn run
  "Load file location from first argument and print reports"
  [file-location]
  (let [records (loader/from-file file-location)]
    (print-report "*** Sort by gender and last name asc ***" records println comparator/gender-asc-then-last-name-asc)
    (print-report "*** Sort by birth date asc ***" records println comparator/birth-date-asc)
    (print-report "*** Sort by last name desc ***" records println comparator/last-name-desc)))



