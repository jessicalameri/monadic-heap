(ns monadic-heap.internal.heapify-test
  (:require [cats.monad.maybe :as maybe]
            [clojure.test :refer [is testing deftest]]
            [heap-helper :as heap]
            [monadic-heap.internal.heapify :as heapify]))

(def default-list (heap/list->maybe-list [1 10 7 12]))

(deftest up-test
  (testing "go all the way up if the value is smaller and re-balance the heap"
    (is (= (heap/list->maybe-list [0 1 7 12 10])
           (heapify/up (conj default-list (maybe/just 0)) 4 <))))


  (testing "go half way up if the value if the value isn't the smallest"
    (is (= (heap/list->maybe-list [1 3 7 12 10])
           (heapify/up (conj default-list (maybe/just 3)) 4 <)))))


(deftest down-test
  (testing "go all the way down if the value is smaller and re-balance the heap"
    (is (= (heap/list->maybe-list [7 10 12])
           (heapify/down (heap/list->maybe-list [12 10 7]) 0 <))))

  (testing "go all the way down if the value is smaller and re-balance the heap"
    (is (= (heap/list->maybe-list [3 10 7 12 13])
           (heapify/down (heap/list->maybe-list [13 3 7 12 10]) 0 <)))))
