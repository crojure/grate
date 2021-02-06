(ns grate.date-test
  (:require [clojure.test :refer :all]
            [grate.date :refer :all]))

(deftest test-format-date-str
  (testing "Format date string to M/D/YYYY for output"
    (is (= "10/10/1970"
          (format-date-str "1970-10-10")))))

