(ns grate.record.comparator)

(defn compare-on-last-name-desc
  [first second]
  (compare (:last-name second) (:last-name first)))

(defn compare-on-birth-date-asc
  [first second]
  (compare (:date-of-birth first) (:date-of-birth second)))

(defn compare-on-gender-asc-then-last-name-asc
  [first second]
  (compare (str (:gender first) (:last-name first)) (str (:gender second) (:last-name second))))