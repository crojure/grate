(ns grate.output
  (:require [grate.record.serializer :as serializer]
            [grate.record.comparator :as comparator]))

(defn print-records
  [records]
  (doseq [record records] (println record)))

(defn print-gender-last-name-sort
  [records]
  (println "*** Output 1: Sort by gender then last-name ascending ***")
  (print-records (map serializer/to-csv
                      (sort comparator/gender-asc-then-last-name-asc records))))

(defn print-birthdate-sort
  [records]
  (println "*** Output 2: Sort by birth-date ascending ***")
  (print-records (map serializer/to-csv
                      (sort comparator/birth-date-asc records))))

(defn print-last-name-sort
  [records]
  (println "*** Output 3: Sort by last name descending ***")
  (print-records (map serializer/to-csv
                      (sort comparator/last-name-desc records))))

