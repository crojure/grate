(ns grate.record.repository-test
  (:require [clojure.test :refer :all])
  (:require [grate.record.repository :refer :all]))

(deftest test-load-from-file
  (testing "Load repository data from file"
    (is (= {:last-name      "Crone"
            :first-name     "Todd"
            :gender         "M"
            :favorite-color "purple"
            :date-of-birth  "1970-10-10"}
           (first (load-from-file "test/test-file.txt"))))))

