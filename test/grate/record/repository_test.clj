(ns grate.record.repository-test
  (:require [clojure.test :refer :all]
            [grate.record.repository :refer :all]
            [grate.record.comparator :as comparator]))

(load-from-file "test/test-file.txt")

(deftest test-load-from-file
  (testing "Load repository data from file"
    (is (= {:last-name      "Crone"
            :first-name     "Todd"
            :gender         "M"
            :favorite-color "purple"
            :date-of-birth  "1970-10-10"}
           (first (find-all)))))
  (is (= 3 (count (find-all)))))

(deftest test-find-all-sorted
  (testing "Load repository data from file"
    (let [sorted (find-all-sorted-by comparator/last-name-desc)]
      (is (= {:last-name      "Smith"
              :first-name     "Jennifer"
              :gender         "F"
              :favorite-color "blue"
              :date-of-birth  "1808-07-08"}
             (first sorted)))
      (is (= 3 (count sorted))))))
