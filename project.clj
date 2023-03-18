(defproject gfx "0.1.0-SNAPSHOT"
  :license {:name "Apache-2.0"
            :url "http://www.apache.org/licenses/LICENSE-2.0"}
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [org.clojure/core.match "1.0.0"]
                 [clojure2d "1.4.0"]]
  :main ^:skip-aot main.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
