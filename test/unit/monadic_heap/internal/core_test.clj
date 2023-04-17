(ns monadic-heap.internal.core-test
  (:require [cats.monad.maybe :as monad.maybe]
            [clojure.test :refer [are is testing]]
            [monadic-heap.internal.core :as core]
            [schema.test :as st]))

(st/deftest size-test
  (testing "should return the size of the internal array"
    (are [size list] (= size (core/size list))
      3 [1 2 3]
      4 [:a :b :c :d]
      0 [])))

(st/deftest top-test
  (testing "return a monad of the first element"
    (is (monad.maybe/maybe? (core/top [0 1 2])))
    (is (monad.maybe/just? (core/top [0 1 2])))
    (is (= 0 (monad.maybe/from-maybe (core/top [0 1 2]))))
    (is (monad.maybe/nothing? (core/top [])))
    (is (nil? (monad.maybe/from-maybe (core/top []))))))
