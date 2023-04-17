(ns adding-values
  (:require [clojure.test :refer :all]
            [monadic-heap.api :as api]
            [schema.test :as st]))

(st/deftest addition
  (testing "Type stays the same after conj"
    (is (= (type (api/min-heap))
           (-> (api/min-heap)
               (conj 1)
               type))))

  (testing "after adding a bunch of values, the top should have the min"
    (let [heap (-> (api/min-heap)
                   (conj 7)
                   (conj 10)
                   (conj 1)
                   (conj 12))]
      (is (= 1 (peek heap)))))

  (testing "after adding a bunch of values, the top should have the max"
    (let [heap (-> (api/max-heap)
                   (conj 7)
                   (conj 10)
                   (conj 1)
                   (conj 12))]
      (is (= 12 (peek heap)))))

  (testing "peeking an empty list should return nothing"
    (is (nil? (peek (api/min-heap))))))


(st/deftest removal
  (testing "Type stays the same after pop"
    (is (= (type (api/min-heap))
           (->> (api/min-heap)
                pop
                type))))

  (testing "after getting a value, it keeps the heap valid"
    (let [heap (-> (api/min-heap)
                   (conj 7)
                   (conj 10)
                   (conj 1)
                   (conj 12)
                   pop)]
      (is (= 7 (peek heap)))))

  (testing "same happens after another pop"
    (let [heap (-> (api/min-heap)
                   (conj 7)
                   (conj 10)
                   (conj 1)
                   (conj 12)
                   pop
                   pop)]
      (is (= 10 (peek heap)))))

  (testing "pop on empty list is idempotent"
    (let [heap (-> (api/min-heap)
                   pop
                   pop)]
      (is (nil? (peek heap))))))
