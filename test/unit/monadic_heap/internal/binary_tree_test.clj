(ns monadic-heap.internal.binary-tree-test
  (:require [clojure.test :refer [is testing deftest]]
            [monadic-heap.internal.binary-tree :as tree]))

(deftest parent-test
  (testing "root has no parent"
    (is (nil? (tree/parent 0))))

  (testing "the first level has 0 as parent"
    (is (= 0 (tree/parent 1)))
    (is (= 0 (tree/parent 2)))))

(deftest left-child-test
  (testing "root has a left child"
    (is (= 1 (tree/left-child 0)))))


(deftest right-child-test
  (testing "root has a right child"
    (is (= 2 (tree/right-child 0)))))
