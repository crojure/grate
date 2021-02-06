(ns grate.core
  (:gen-class)
  (:require [grate.record :as record]
            [grate.records :as records]
            [grate.output :as output]))

(defn print-records
  [records]
  (doseq [record records] (println record)))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (print-records
    (map output/display-str
         (sort record/compare-on-gender-asc-then-last-name-asc (records/load-from "test/test-file.txt")))))

