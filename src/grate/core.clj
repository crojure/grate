(ns grate.core
  (:gen-class)
  (:require [grate.cli :as cli]
            [grate.api :as api]))

(defn -main
  [& args]
  (if (= (first args) "cli") (cli/run (second args) println) (api/run)))