(ns grate.record.serializer-test
  (:require [clojure.test :refer :all]
            [grate.record.serializer :refer :all]))

(deftest test-format-date-str
  (testing "Format date string to M/D/YYYY for output"
    (is (= "10/10/1970" (format-date-str "1970-10-10")))
    (is (= "1/2/1970" (format-date-str "1970-01-02")))))

(deftest test-to-csv
  (testing "Serialize record for CSV output"
        (is (= "Crone,Todd,M,purple,10/10/1970"
               (to-csv {:last-name      "Crone"
                        :first-name     "Todd"
                        :gender         "M"
                        :favorite-color "purple"
                        :date-of-birth  "1970-10-10"})))))
