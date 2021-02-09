(defproject grate "0.1.0-SNAPSHOT"
  :description "GRate Application"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :plugins [[lein-cloverage "1.2.2"]
            [lein-cljfmt "0.7.0"]]
  :dependencies [[org.clojure/clojure "1.10.2"]
                 [org.clojure/data.json "1.0.0"]
                 [compojure "1.6.2"]
                 [http-kit "2.5.1"]
                 [ring/ring-defaults "0.3.2"]]
  :main ^:skip-aot grate.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all
                       :jvm-opts ["-Dclojure.compiler.direct-linking=true"]}})
