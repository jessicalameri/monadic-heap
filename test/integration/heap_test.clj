(ns heap-test
  (:require [clojure.test :refer :all]
            [heap-helper]
            [monadic-heap.api :as api]
            [schema.test :as st]))

(st/deftest min-heap-addition
  (testing "min heap with 1000 items"
    (let [list (shuffle [7538 6616 6581 3668 1985 2301 7335 4200 503 3431])
          heap (heap-helper/fill-from-list (api/min-heap) list)]
      (is (= (apply min list) (peek heap))))))

(st/deftest min-heap-removal
  (testing "min heap with 1000 items"
    (let [heap           (heap-helper/fill (api/min-heap) 10)
          list-from-heap (heap-helper/->list heap)]
      (is (= (seq list-from-heap) (sort list-from-heap))))))
