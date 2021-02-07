(ns grate.core
  (:gen-class)
  (:require [org.httpkit.server :as server]
            [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer :all]
            [clojure.pprint :as pp]
            [clojure.string :as str]
            [clojure.data.json :as json]
            [grate.record :as record]
            [grate.records :as records]
            [grate.output :as output]))

(defn print-records
  [records]
  (doseq [record records] (println record)))


(defn output1
  [records]
  (println "*** Output 1 ***")
  (print-records (map output/display-str
                      (sort record/compare-on-gender-asc-then-last-name-asc records))))

(defn output2
  [records]
  (println "*** Output 2 ***")
  (print-records (map output/display-str
                      (sort record/compare-on-birth-date-asc records))))

(defn output3
  [records]
  (println "*** Output 3 ***")
  (print-records (map output/display-str
                      (sort record/compare-on-last-name-desc records))))

(defn index [req]
  {:status  200
   :headers {"Content-Type" "application/json"}
   :body    "{}"})

(defroutes app-routes
           (context "/records" []
             (POST "/" [] index)
             (GET "/gender" [] index)
             (GET "/birthdate" [] index)
             (GET "/name" [] index)))

(defn api
  "Run the API"
  []
  (let [port (Integer/parseInt (or (System/getenv "PORT") "3000"))]
      (server/run-server (wrap-defaults #'app-routes site-defaults) {:port port})
      (println (str "Running webserver at http:/127.0.0.1:" port "/"))))

(defn cli
  "Load file location from first argument and print reports"
  [file-location]
  (println file-location)
  (let [records (records/load-from file-location)]
    (output1 records)
    (output2 records)
    (output3 records)))

(defn -main
  [& args]
  (if (= (first args) "cli") (cli (second args)) (api)))


