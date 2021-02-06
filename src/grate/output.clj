(ns grate.output
  (:require [clojure.string :as str])
  (:require [clojure.edn :as edn]))

(defn format-date-str
  [date-str]
  (let [[year month day] (str/split date-str #"-")]
    (str (edn/read-string month) "/" (edn/read-string day) "/" year)))

(defn display-str
  [record]
  (str (:last-name record) "," (:first-name record) "," (:gender record) "," (:favorite-color record) ","
       (format-date-str (:date-of-birth record))))