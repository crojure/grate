(ns grate.record
  (:require [clojure.string :as str]))

(defn create
  [values]
  (let [[last first gender color date] values]
    {:last-name      last
     :first-name     first
     :gender         gender
     :favorite-color color
     :date-of-birth  date}))

(defn parse
  [line delim]
  (create (map str/trim (str/split line delim))))