(ns grate.record.validator
  (:require [clojure.string :as str]))

(defn date
  [date-str]
  (if (or
        (nil? date-str)
        (empty? (re-matches #"([12]\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\d|3[01]))" date-str)))
    "Date must be non-null string with format 'YYYY-MM-DD'" nil))

(defn name-str
  [name-str]
  (if (or
        (nil? name-str)
        (str/blank? name-str))
    "Name must be non-null and non-blank string" nil))

(defn gender
  [gender]
  (if (or
        (nil? gender)
        (not (.contains ["M" "F"] gender)))
    "Gender must be 'M' or 'F' only" nil))

(defn color
  [color]
  (if (or
        (nil? color)
        (not (.contains ["red" "yellow" "blue" "orange" "green" "violet" "purple" "brown"] color)))
    "Color must be red, yellow, blue, orange, green, violet, purple or brown" nil))

(defn validate
  [record]
  (filter identity [(name-str (:last-name record))
                    (name-str (:first-name record))
                    (gender (:gender record))
                    (color (:favorite-color record))
                    (date (:date-of-birth record))]))

(defn valid?
  [record]
  (and
    (not (nil? record))
    (empty? (validate record))))