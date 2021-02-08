(ns grate.validate
  (:require [clojure.string :as str]))

(defn date?
  [date-str]
  (and
    (not (nil? date-str))
    (not (empty? (re-matches #"([12]\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\d|3[01]))" date-str)))))

(defn name?
  [name-str]
  (and
    (not (nil? name-str))
    (not (str/blank? name-str))))

(defn gender?
  [gender]
  (and
    (not (nil? gender))
    (.contains [ "M" "F" ] gender)))

(defn color?
  [color]
  (and
    (not (nil? color))
    (.contains [ "red" "yellow" "blue" "orange" "green" "violet" "purple" "brown"] color)))

(defn all?
  [record]
  (and
    (name? (:last-name record))
    (name? (:first-name record))
    (gender? (:gender record))
    (color? (:favorite-color record))
    (date? (:date-of-birth record))))