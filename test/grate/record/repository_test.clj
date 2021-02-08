(ns grate.record.repository-test
  (:require [clojure.test :refer :all]
            [grate.record.repository :refer :all]
            [grate.record.comparator :as comparator]))

(defn with-repo-data [f]
  (load-from-file "test/test-file.txt")
  (f)
  (delete-all))

(use-fixtures :each with-repo-data)

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

(deftest test-add
  (testing "Add record to repository"
    (let [record (add {:last-name      "Guy"
                       :first-name     "New"
                       :gender         "M"
                       :favorite-color "orange"
                       :date-of-birth  "2000-01-01"})]
      (is (= "New" (:first-name record)))
      (is (= 4 (count (find-all)))))))