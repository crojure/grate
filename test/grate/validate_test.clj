(ns grate.validate-test
  (:require [clojure.test :refer :all]
            [grate.validate :refer :all]))

(deftest test-valid-date
  (testing "Date string of correct format will validate"
    (is (= true (date? "1970-10-10")))))

(deftest test-nil-date
  (testing "Nil date string will NOT validate"
    (is (= false (date? nil)))))

(deftest test-invalid-date
  (testing "Date string of incorrect format will NOT validate"
    (is (= false (date? "^$^%-(*-()")))
    (is (= false (date? "10")))
    (is (= false (date? "lskfhj")))
    (is (= false (date? "10")))))

(deftest test-invalid-date-year
  (testing "Date string of bad year will validate"
    (is (= false (date? "9999-10-10")))
    (is (= false (date? "9-10-10")))
    (is (= false (date? "90-10-10")))
    (is (= false (date? "900-10-10")))
    (is (= false (date? "0000-10-10")))))

(deftest test-invalid-date-month
  (testing "Date string of bad year will validate"
    (is (= false (date? "2000-00-10")))))

(deftest test-invalid-date-day
  (testing "Date string of bad year will validate"
    (is (= false (date? "2000-10-00")))))

(deftest test-valid-field-values
  (testing "Record fields with valid values"
    (is (= true (name? "Miller")))
    (is (= true (name? "Thatcher")))
    (is (= true (gender? "F")))
    (is (= true (color? "blue")))))

(deftest test-invalid-field-values
  (testing "Record fields with valid values"
    (is (= false (name? "")))
    (is (= false (name? nil)))
    (is (= false (gender? "N")))
    (is (= false (color? "mauve")))))
