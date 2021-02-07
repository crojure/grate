(ns grate.output
  (:require [clojure.string :as str]
            [grate.record :as record]))

(defn format-date-str
  [date-str]
  (let [[year month day] (str/split date-str #"-")]
    (str (Integer/parseInt month) "/" (Integer/parseInt day) "/" year)))

(defn display-str
  [record]
  (str (:last-name record) "," (:first-name record) "," (:gender record) "," (:favorite-color record) ","
       (format-date-str (:date-of-birth record))))

(defn viewable
  [record]
  {:last-name      (:last-name record)
   :first-name     (:first-name record)
   :gender         (:gender record)
   :favorite-color (:favorite-color record)
   :date-of-birth  (format-date-str (:date-of-birth record))})

(defn to-viewable
  [records]
  (map viewable records))

(defn print-records
  [records]
  (doseq [record records] (println record)))

(defn gender-last-name-sort
  [records]
  (println "*** Output 1 ***")
  (print-records (map display-str
                      (sort record/compare-on-gender-asc-then-last-name-asc records))))

(defn birthdate-sort
  [records]
  (println "*** Output 2 ***")
  (print-records (map display-str
                      (sort record/compare-on-birth-date-asc records))))

(defn last-name-sort
  [records]
  (println "*** Output 3 ***")
  (print-records (map display-str
                      (sort record/compare-on-last-name-desc records))))

