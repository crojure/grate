(ns grate.records-test
  (:require [clojure.test :refer :all]
            [grate.records :refer :all]))

(deftest test-load-bar-file
  (testing "Load file with fields separated by bars"
    (is (= {:last-name      "Crone"
            :first-name     "Todd"
            :gender         "M"
            :favorite-color "hazel"
            :date-of-birth  "1970-10-10"}
           (first (load-from "test/load-bar-test.txt"))))))
