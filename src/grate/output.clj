(ns grate.output
  (:require [clojure.string :as str]))

(defn format-date-str
  [date-str]
  (let [[year month day] (str/split date-str #"-")]
    (str (Integer/parseInt month) "/" (Integer/parseInt day) "/" year)))

(defn display-str
  [record]
  (str (:last-name record) "," (:first-name record) "," (:gender record) "," (:favorite-color record) ","
       (format-date-str (:date-of-birth record))))