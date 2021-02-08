(ns grate.record.parser
  (:require [clojure.string :as str]))

(defn map-values
  [values]
  (let [[last first gender color date] values]
    {:last-name      last
     :first-name     first
     :gender         gender
     :favorite-color color
     :date-of-birth  date}))

(defn split-n-trim
  [line delim]
  (map-values (map str/trim (str/split line delim))))

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
