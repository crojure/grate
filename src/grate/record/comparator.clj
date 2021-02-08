(ns grate.record.comparator)

(defn last-name-desc
  [first second]
  (compare (:last-name second) (:last-name first)))

(defn birth-date-asc
  [first second]
  (compare (:date-of-birth first) (:date-of-birth second)))

(defn gender-asc-then-last-name-asc
  [first second]
  (compare (str (:gender first) (:last-name first)) (str (:gender second) (:last-name second))))