(ns grate.record
  (:require [clojure.string :as str])
  (:require [clojure.data.json :as json])
  (:require [grate.validate :as validate]))

(defn valid?
  [record]
  (and
    (not (nil? record))
    (validate/name? (:last-name record))
    (validate/name? (:first-name record))
    (validate/gender? (:gender record))
    (validate/color? (:favorite-color record))
    (validate/date? (:date-of-birth record))))

(defn map-values
  [values]
  (let [[last first gender color date] values]
    {:last-name      last
     :first-name     first
     :gender         gender
     :favorite-color color
     :date-of-birth  date}))

(defn create
  [values]
  (let [record (map-values values)]
    (if (valid? record) record nil)))

(defn split-n-trim
  [line delim]
  (create (map str/trim (str/split line delim))))

(defn bar?
  [line]
  (str/includes? line "|"))

(defn csv?
  [line]
  (str/includes? line ","))

(defn parse
  [line]
  (cond
    (bar? line) (split-n-trim line #"\|")
    (csv? line) (split-n-trim line #",")
    :else (split-n-trim (str/replace (str/trim line) #"\s+" ",") #",")))
