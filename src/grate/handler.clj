(ns grate.handler
  (:require [grate.record.parser :as record]
            [grate.record.validator :as validator]
            [grate.record.repository :as repository]
            [grate.record.serializer :as serializer]
            [clojure.data.json :as json]))

(defn index []
  {:status  200
   :headers {"Content-Type" "application/json"}
   :body    (serializer/to-json-array (repository/find-all))})

(defn add-record [record-str]
  (let [record (record/parse record-str)
        errors (validator/validate record)]
    (if (empty? errors) (repository/add record))
    {:record record :errors errors}))

(defn post [body]
  (let [result (add-record body)]
    {:status  (if (empty? (:errors result)) 201 400)
     :headers {"Content-Type" "application/json"}
     :body    (json/write-str result)}))
