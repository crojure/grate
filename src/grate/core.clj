(ns grate.core
  (:gen-class)
  (:require [org.httpkit.server :as server]
            [compojure.core :refer :all]
            [ring.middleware.defaults :refer :all]
            [clojure.data.json :as json]
            [grate.record.loader :as records]
            [grate.record.parser :as record]
            [grate.record.validator :as validator]
            [grate.output :as output]
            [grate.record.serializer :as serializer]))


(def records-collection (atom []))

(defn add-record [record-str]
  (let [record (record/parse record-str)
        errors (validator/validate record)]
    (if (empty? errors) (swap! records-collection conj))
    {:record record :errors errors}))

(defn index [req]
  {:status  200
   :headers {"Content-Type" "application/json"}
   :body    (serializer/to-json-array @records-collection)})

(defn post [body]
  (let [result (add-record body)]
    {:status  (if (empty? (:errors result)) 201 400)
     :headers {"Content-Type" "application/json"}
     :body    (json/write-str result)}))

(defroutes app-routes
           (context "/records" []
             (GET "/" [] index)
             (POST "/" {body :body} (post (slurp body)))
             (GET "/gender" [] index)
             (GET "/birthdate" [] index)
             (GET "/name" [] index)))

(defn api
  "Run the API"
  []
  (let [port (Integer/parseInt (or (System/getenv "PORT") "3000"))]
      (server/run-server (wrap-defaults #'app-routes api-defaults) {:port port})
      (println (str "Running webserver at http:/127.0.0.1:" port "/"))))

(defn cli
  "Load file location from first argument and print reports"
  [file-location]
  (println file-location)
  (let [records (records/from-file file-location)]
    (output/print-gender-last-name-sort records)
    (output/print-birthdate-sort records)
    (output/print-last-name-sort records)))

(defn -main
  [& args]
  (if (= (first args) "cli") (cli (second args)) (api)))