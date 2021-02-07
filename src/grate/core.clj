(ns grate.core
  (:gen-class)
  (:require [grate.record :as record]
            [grate.records :as records]
            [grate.output :as output]))

(defn print-records
  [records]
  (doseq [record records] (println record)))


(defn output1
  [records]
  (println "*** Output 1 ***")
  (print-records (map output/display-str
                      (sort record/compare-on-gender-asc-then-last-name-asc records))))

(defn output2
  [records]
  (println "*** Output 2 ***")
  (print-records (map output/display-str
                      (sort record/compare-on-birth-date-asc records))))

(defn output3
  [records]
  (println "*** Output 3 ***")
  (print-records(map output/display-str
                     (sort record/compare-on-last-name-desc records))))

(defn -main
  "Load file location from first argument and print reports"
  [& args]
  (let [records (records/load-from (first args))]
    (output1 records)
    (output2 records)
    (output3 records)))

