(ns grate.date
  (:require [clojure.string :as str]))

(defn format-date-str
  [date-str]
  (let [[year month day] (str/split date-str #"-")]
       (str month "/" day "/" year)))
