(ns monadic-heap.internal.core-test
  (:require [cats.data :as data]
            [cats.monad.maybe :as maybe]
            [clojure.test :refer [are is testing deftest]]
            [heap-helper :as heap]
            [monadic-heap.internal.core :as core]))

(deftest size-test
  (testing "should return the size of the internal array"
    (are [size list] (= size (core/current-size (data/pair (count list) list)))
      3 [1 2 3]
      4 [:a :b :c :d]
      0 [])))

(deftest top-test
  (testing "return a monad of the first element"
    (is (maybe/maybe? (core/top (data/pair 3 (heap/list->maybe-list [0 1 2])))))
    (is (maybe/just? (core/top (data/pair 3 (heap/list->maybe-list [0 1 2])))))
    (is (= 0 (maybe/from-maybe (core/top (data/pair 3 (heap/list->maybe-list [0 1 2]))))))
    (is (maybe/nothing? (core/top (data/pair 0 []))))))
