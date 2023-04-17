(ns monadic-heap.internal.heapify-test
  (:require [clojure.test :refer :all]
            [monadic-heap.internal.heapify :as heapify]
            [schema.test :as st]))

(def default-list [1 10 7 12])

(st/deftest up-test
  (testing "go all the way up if the value is smaller and re-balance the heap"
    (is (= [0 1 7 12 10]
           (heapify/up (conj default-list 0) 4 <))))


  (testing "go half way up if the value if the value isn't the smallest"
    (is (= [1 3 7 12 10]
           (heapify/up (conj default-list 3) 4 <)))))


(st/deftest down-test
  (testing "go all the way down if the value is smaller and re-balance the heap"
    (is (= [7 10 12]
           (heapify/down [12 10 7] 0 <))))

  (testing "go all the way down if the value is smaller and re-balance the heap"
    (is (= [3 10 7 12 13]
           (heapify/down [13 3 7 12 10] 0 <)))))
