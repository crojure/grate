(ns grate.core
  (:gen-class)
  (:require [org.httpkit.server :as server]
            [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer :all]
            [clojure.pprint :as pp]
            [clojure.string :as str]
            [clojure.data.json :as json]
            [grate.records :as records]
            [grate.output :as output]
            [grate.record :as record]))

(def records-collection (atom []))

(defn add-record [record-str]
  (swap! records-collection conj (record/parse record-str)))

(defn index [req]
  {:status  200
   :headers {"Content-Type" "application/json"}
   :body    (json/write-str (output/to-viewable @records-collection))})

(defn post [body]
  (add-record body)
  {:status  201
   :headers {"Content-Type" "application/json"}
   :body    body})

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
  (let [records (records/load-from file-location)]
    (output/gender-last-name-sort records)
    (output/birthdate-sort records)
    (output/last-name-sort records)))

(defn -main
  [& args]
  (if (= (first args) "cli") (cli (second args)) (api)))