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

(defn split-n-trim
  [line delim]
  (create (map str/trim (str/split line delim))))

(defn parse
  [line]
  (cond
    (str/includes? line "|") (split-n-trim line #"\|")
    (str/includes? line ",") (split-n-trim line #",")
    :else (split-n-trim (str/replace (str/trim line) #"\s+" ",") #",")))