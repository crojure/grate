(ns grate.core
  (:gen-class)
  (:require [org.httpkit.server :as server]
            [compojure.core :refer :all]
            [ring.middleware.defaults :refer :all]
            [grate.record.loader :as records]
            [grate.cli :as cli]
            [grate.record.comparator :as comparator]
            [grate.api :as api]))

(defroutes app-routes
           (context "/records" []
             (GET "/" [] api/index)
             (POST "/" {body :body} (api/post (slurp body)))
             (GET "/gender" [] (api/get-sorted comparator/gender-asc-then-last-name-asc))
             (GET "/birthdate" [] (api/get-sorted comparator/birth-date-asc))
             (GET "/name" [] (api/get-sorted comparator/last-name-desc))))

(defn run-api
  "Run the API"
  []
  (let [port (Integer/parseInt (or (System/getenv "PORT") "3000"))]
      (server/run-server (wrap-defaults #'app-routes api-defaults) {:port port})
      (println (str "Running webserver at http:/127.0.0.1:" port "/"))))

(defn run-cli
  "Load file location from first argument and print reports"
  [file-location]
  (println "Loading" file-location)
  (let [records (records/from-file file-location)]
    (cli/print-report "*** Sort by gender and last name asc ***" records println comparator/gender-asc-then-last-name-asc)
    (cli/print-report "*** Sort by birth date asc ***" records println comparator/birth-date-asc)
    (cli/print-report "*** Sort by last name desc ***" records println comparator/last-name-desc)))

(defn -main
  [& args]
  (if (= (first args) "cli") (run-cli (second args)) (run-api)))