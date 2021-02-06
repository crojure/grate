(ns grate.core
  (:gen-class)
  (:require [grate.file :as file]
            [grate.output :as output]))

(defn print-records
  [records]
  (doseq [record records] (println record)))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (print-records (map output/display-str (file/read-records "test/load-bar-test.txt"))))

