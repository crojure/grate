(ns grate.api-test
  (:require [clojure.test :refer :all])
  (:require [grate.api :refer :all]
            [grate.record.comparator :as comparator]
            [grate.record.repository :as repository]))

(defn clean-repo [f]
  (f)
  (repository/delete-all))

(use-fixtures :each clean-repo)

(deftest test-index
  (testing "Handle index"
    (is (= {:status 200, :headers {"Content-Type" "application/json"}, :body "[]"}
           (index nil)))))

(deftest test-get-sorted
  (testing "Handle get sorted records"
    (post "Crone|Todd|M|green|1970-10-10")
    (let  [response (get-sorted comparator/birth-date-asc)]
      (is (not (nil? (:body response))))
      (is (= "[{\"last-name\":\"Crone\",\"first-name\":\"Todd\",\"gender\":\"M\",\"favorite-color\":\"green\",\"date-of-birth\":\"10\\/10\\/1970\"}]" (:body response)))
      (is (= (:status response) 200)))))

(deftest test-post-successful-created
  (testing "Handle post record"
    (let [response (post "Crone|Todd|M|green|1970-10-10")]
      (is (not (nil? (:body response))))
      (is (= (:status response) 201)))))

(deftest test-post-bad-request
  (testing "Handle post"
    (let [response (post "I am bad")]
      (is (not (nil? (:body response))))
      (is (= (:status response) 400)))))
