(ns grate.handler-test
  (:require [clojure.test :refer :all])
  (:require [grate.handler :refer :all]))

(deftest test-index
  (testing "Handle index"
    (is (= {:status 200, :headers {"Content-Type" "application/json"}, :body ()}
           (index)))))

(deftest test-post-successful-created
  (testing "Handle post"
    (let [response (post "Crone|Todd|M|green|1970-10-10")]
      (is (not (nil? (:body response))))
      (is (= (:status response) 201)))))

(deftest test-post-bad-request
  (testing "Handle post"
    (let [response (post "I am bad")]
      (is (not (nil? (:body response))))
      (is (= (:status response) 400)))))
