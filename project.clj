(defproject jessicalameri/monadic-heap "0.0.1-SNAPSHOT"
  :description "Heap implementation with monadic return, written in Clojure."

  :url "https://github.com/jessicalameri/monadic-heap"

  :aot [monadic-heap.internal.core
        monadic-heap.type]

  :deploy-repositories [["releases" :clojars]]

  :lein-release {:scm :git
                 :deploy-via :clojars}

  :profiles {:uberjar     {:aot :all}

             :integration {:test-paths ^:replace ["test/integration/" "test/helpers"]}

             :unit        {:test-paths ^:replace ["test/unit/" "test/helpers"]}

             :dev         {:source-paths   ["dev"]
                           :resource-paths ["test/resources/"]
                           :plugins        [[com.github.clojure-lsp/lein-clojure-lsp "1.3.20"]]
                           :dependencies   [[org.clojure/clojure "1.11.1"]
                                            [org.clojure/tools.namespace "1.3.0"]
                                            [prismatic/schema "1.4.1"]
                                            [funcool/cats "2.4.2"]]
                           :repl-options   {:init-ns user}}}

  :min-lein-version "2.4.2"

  :resource-paths ["resources"]

  :aliases {"run-dev"         ["with-profile" "+repl-start" "trampoline" "repl" ":headless"]
            "run-dev-notramp" ["with-profile" "+repl-start" "repl" ":headless"]
            "diagnostics"     ["clojure-lsp" "diagnostics"]
            "format"          ["clojure-lsp" "format" "--dry"]
            "format-fix"      ["clojure-lsp" "format"]
            "clean-ns"        ["clojure-lsp" "clean-ns" "--dry"]
            "clean-ns-fix"    ["clojure-lsp" "clean-ns"]
            "lint"            ["do" ["diagnostics"] ["format"] ["clean-ns"]]
            "lint-fix"        ["do" ["format-fix"] ["clean-ns-fix"]]
            "unit"            ["with-profile" "+unit" "test"]
            "integration"     ["with-profile" "+integration" "test"]}

  :test-paths ["test/unit" "test/integration" "test/helpers"])
