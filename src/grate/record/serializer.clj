(ns grate.record.serializer
  (:require [clojure.string :as str]
            [clojure.data.json :as json]))

(defn format-date-str
  [date-str]
  (let [[year month day] (str/split date-str #"-")]
    (str (Integer/parseInt month) "/" (Integer/parseInt day) "/" year)))

(defn to-csv
  [record]
  (str (:last-name record) "," (:first-name record) "," (:gender record) "," (:favorite-color record) ","
       (format-date-str (:date-of-birth record))))

(defn format-date
  [record]
  {:last-name      (:last-name record)
   :first-name     (:first-name record)
   :gender         (:gender record)
   :favorite-color (:favorite-color record)
   :date-of-birth  (format-date-str (:date-of-birth record))})

(defn to-json
  [records]
  (json/write-str (map format-date records)))
