(ns grate.records-test
  (:require [clojure.test :refer :all]
            [grate.records :refer :all]))

(deftest test-from-file
  (testing "Load file with fields separated by bars"
    (is (= {:last-name      "Crone"
            :first-name     "Todd"
            :gender         "M"
            :favorite-color "purple"
            :date-of-birth  "1970-10-10"}
           (first (from-file "test/test-file.txt"))))))

(deftest test-from-string
  (testing "Load file with fields separated by bars"
    (is (= {:last-name      "Crone"
            :first-name     "Todd"
            :gender         "M"
            :favorite-color "purple"
            :date-of-birth  "1970-10-10"}
           (first (from-string (slurp "test/test-file.txt")))))))
