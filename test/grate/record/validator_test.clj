(ns grate.record.validator-test
  (:require [clojure.test :refer :all]
            [grate.record.validator :refer :all]))

(deftest test-valid-date
  (testing "Date string of correct format will validate"
    (is (nil? (date "1970-10-10")))))

(deftest test-nil-date
  (testing "Nil date string will NOT validate"
    (is (not (nil? (date nil))))))

(deftest test-invalid-date
  (testing "Date string of incorrect format will NOT validate"
    (is (not (nil? (date "^$^%-(*-()"))))
    (is (not (nil? (date "10"))))
    (is (not (nil? (date "lskfhj"))))
    (is (not (nil? (date "10"))))))

(deftest test-invalid-date-year
  (testing "Date string of bad year will validate"
    (is (not (nil? (date "9999-10-10"))))
    (is (not (nil? (date "9-10-10"))))
    (is (not (nil? (date "90-10-10"))))
    (is (not (nil? (date "900-10-10"))))
    (is (not (nil? (date "0000-10-10"))))))

(deftest test-invalid-date-month
  (testing "Date string of bad year will validate"
    (is (not (nil? (date "2000-00-10"))))))

(deftest test-invalid-date-day
  (testing "Date string of bad year will validate"
    (is (not (nil? (date "2000-10-00"))))))

(deftest test-valid-field-values
  (testing "Record fields with valid values"
    (is (nil? (name-str "Miller")))
    (is (nil? (name-str "Thatcher")))
    (is (nil? (gender "F")))
    (is (nil? (color "blue")))))

(deftest test-invalid-field-values
  (testing "Record fields with valid values"
    (is (not (nil? (name-str ""))))
    (is (not (nil? (name-str nil))))
    (is (not (nil? (gender "N"))))
    (is (not (nil? (color "mauve"))))))
