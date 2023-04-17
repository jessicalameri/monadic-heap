(ns moving-values
  (:require [cats.monad.maybe :as monad.maybe]
            [clojure.test :refer [deftest is testing]]
            [heap-helper :as heap]
            [monadic-heap.api :as api]))

(deftest addition
  (testing "Type stays the same after conj"
    (is (= (type (api/min-heap))
           (-> (api/min-heap)
               (heap/add 1)
               type))))

  (testing "after adding a bunch of values, the top should have the min"
    (let [heap (-> (api/min-heap)
                   (heap/add 7)
                   (heap/add 10)
                   (heap/add 1)
                   (heap/add 12))]
      (is (= 1 (monad.maybe/from-maybe (peek heap))))))

  (testing "after adding a bunch of values, the top should have the max"
    (let [heap (-> (api/max-heap)
                   (heap/add 7)
                   (heap/add 10)
                   (heap/add 1)
                   (heap/add 12))]
      (is (= 12 (monad.maybe/from-maybe (peek heap))))))

  (testing "peeking an empty list should return nothing"
    (is (monad.maybe/nothing? (peek (api/min-heap))))))


(deftest removal
  (testing "Type stays the same after pop"
    (is (= (type (api/min-heap))
           (->> (api/min-heap)
                pop
                type))))

  (testing "after getting a value, it keeps the heap valid"
    (let [heap (-> (api/min-heap)
                   (heap/add 7)
                   (heap/add 10)
                   (heap/add 1)
                   (heap/add 12)
                   pop)]
      (is (= 7 (monad.maybe/from-maybe (peek heap))))))

  (testing "same happens after another pop"
    (let [heap (-> (api/min-heap)
                   (heap/add 7)
                   (heap/add 10)
                   (heap/add 1)
                   (heap/add 12)
                   pop
                   pop)]
      (is (= 10 (monad.maybe/from-maybe (peek heap))))))

  (testing "pop on empty list is idempotent"
    (let [heap (-> (api/min-heap)
                   pop
                   pop)]
      (is (monad.maybe/nothing? (peek heap))))))
