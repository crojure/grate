(ns grate.record.comparator_test
  (:require [clojure.test :refer :all]
            [grate.record.comparator :refer :all]))

(def jen {:last-name "Smith" :first-name "Jen" :gender "F" :date-of-birth "1980-09-28"})
(def todd {:last-name "Crone" :first-name "Todd" :gender "M" :date-of-birth "1970-10-10"})
(def bob {:last-name "Z" :first-name "Bob" :gender "M" :date-of-birth "1960-07-15"})
(def records [jen todd bob])

(deftest test-last-name-desc
  (testing "Sort last-name desc"
    (let [sorted (sort last-name-desc records)]
      (is (= bob (first sorted)))
      (is (= jen (second sorted)))
      (is (= todd (last sorted))))))