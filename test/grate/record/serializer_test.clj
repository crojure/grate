(ns grate.record.serializer_test
  (:require [clojure.test :refer :all]
            [grate.record.serializer :refer :all]))

(deftest test-format-date-str
  (testing "Format date string to M/D/YYYY for output"
    (is (= "10/10/1970"
          (format-date-str "1970-10-10")))))

(deftest test-format-date-str-single-digit-day-and-month
  (testing "Format date string to M/D/YYYY for output"
    (is (= "1/2/1970"
           (format-date-str "1970-01-02")))))

(deftest test-display-str
  (testing "Format record for output"
        (is (= "Crone,Todd,M,hazel,10/10/1970"
               (to-csv {:last-name      "Crone"
                        :first-name     "Todd"
                        :gender         "M"
                        :favorite-color "hazel"
                        :date-of-birth  "1970-10-10"})))))
