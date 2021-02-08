(ns grate.core
  (:gen-class)
  (:require [org.httpkit.server :as server]
            [compojure.core :refer :all]
            [ring.middleware.defaults :refer :all]
            [grate.record.loader :as records]
            [grate.output :as output]
            [grate.record.comparator :as comparator]
            [grate.handler :as handler]))

(defroutes app-routes
           (context "/records" []
             (GET "/" [] handler/index)
             (POST "/" {body :body} (handler/post (slurp body)))
             (GET "/gender" [] (handler/get-sorted comparator/gender-asc-then-last-name-asc))
             (GET "/birthdate" [] (handler/get-sorted comparator/birth-date-asc))
             (GET "/name" [] (handler/get-sorted comparator/last-name-desc))))

(defn api
  "Run the API"
  []
  (let [port (Integer/parseInt (or (System/getenv "PORT") "3000"))]
      (server/run-server (wrap-defaults #'app-routes api-defaults) {:port port})
      (println (str "Running webserver at http:/127.0.0.1:" port "/"))))

(defn cli
  "Load file location from first argument and print reports"
  [file-location]
  (println "Loading" file-location)
  (let [records (records/from-file file-location)]
    (output/print-gender-last-name-sort records println)
    (output/print-birthdate-sort records println)
    (output/print-last-name-sort records println)))

(defn -main
  [& args]
  (if (= (first args) "cli") (cli (second args)) (api)))