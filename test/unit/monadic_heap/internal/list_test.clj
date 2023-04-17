(ns monadic-heap.internal.list-test
  (:require [clojure.test :refer [is testing deftest]]
            [monadic-heap.internal.list :as list]))

(def default-list [:a :b :c :d])

(deftest swap-test
  (testing "should change values from determined positions"
    (is (= [:a :c :b :d]
           (list/swap default-list 1 2))))

  (testing "if the indexes are the same, nothing changes"
    (is (= default-list
           (list/swap default-list 1 1)))))

(deftest value-test
  (testing "returns a value if it exists"
    (is (= :c
           (list/value default-list 2))))

  (testing "returns nothing is index doesnt exists"
    (is (nil? (list/value default-list -1)))
    (is (nil? (list/value default-list 4)))))
