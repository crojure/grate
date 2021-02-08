(ns grate.cli
  (:require [grate.record.serializer :as serializer]
            [grate.record.comparator :as comparator]))

(defn print-records
  [records out]
  (doseq [record records] (out record)))

(defn print-gender-last-name-sort
  [records out]
  (out "*** Output 1: Sort by gender then last-name ascending ***")
  (print-records (map serializer/to-csv
                      (sort comparator/gender-asc-then-last-name-asc records)) out))

(defn print-birthdate-sort
  [records out]
  (println "*** Output 2: Sort by birth-date ascending ***")
  (print-records (map serializer/to-csv
                      (sort comparator/birth-date-asc records)) out))

(defn print-last-name-sort
  [records out]
  (println "*** Output 3: Sort by last name descending ***")
  (print-records (map serializer/to-csv
                      (sort comparator/last-name-desc records)) out))

