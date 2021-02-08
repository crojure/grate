(ns grate.cli-test
  (:require [clojure.test :refer :all])
  (:require [grate.cli :refer :all]))

(def lines (atom []))

(defn clear-data [f]
  (f)
  (reset! lines []))

(use-fixtures :each clear-data)

(defn out
  [data]
  (swap! lines conj data))

(deftest test-print-records
  (testing "Print records"
    (print-records ["bob" "sarah"] out)
    (is (= ["bob" "sarah"] @lines))))

(deftest test-run
  (testing "Print reports"
    (run "test/test-file.txt" out)
    (is (.contains @lines "Smith,Jennifer,F,blue,7/8/1808"))
    (is (.contains @lines "Adams,Quincy,M,green,1/11/1909"))
    (is (.contains @lines "Crone,Todd,M,purple,10/10/1970"))))
