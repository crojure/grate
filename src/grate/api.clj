(ns grate.api
  (:require [org.httpkit.server :as server]
            [compojure.core :refer :all]
            [ring.middleware.defaults :refer :all]
            [grate.record.parser :as record]
            [grate.record.validator :as validator]
            [grate.record.repository :as repository]
            [grate.record.serializer :as serializer]
            [grate.record.comparator :as comparator]
            [clojure.data.json :as json]))

(defn index [request]
  {:status  200
   :headers {"Content-Type" "application/json"}
   :body    (serializer/to-json (repository/find-all))})

(defn get-sorted [comparator]
  {:status  200
   :headers {"Content-Type" "application/json"}
   :body    (serializer/to-json (repository/find-all-sorted-by comparator))})

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

(defroutes app-routes
           (context "/records" []
             (GET "/" [] index)
             (POST "/" {body :body} (post (slurp body)))
             (GET "/gender" [] (get-sorted comparator/gender-asc-then-last-name-asc))
             (GET "/birthdate" [] (get-sorted comparator/birth-date-asc))
             (GET "/name" [] (get-sorted comparator/last-name-desc))))

(defn run
  "Run the API"
  []
  (let [port (Integer/parseInt (or (System/getenv "PORT") "3000"))]
    (server/run-server (wrap-defaults #'app-routes api-defaults) {:port port})
    (println (str "Running webserver at http:/127.0.0.1:" port "/"))))

