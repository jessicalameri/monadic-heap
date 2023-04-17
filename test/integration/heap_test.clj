(ns heap-test
  (:require [cats.monad.maybe :as monad.maybe]
            [clojure.test :refer :all]
            [heap-helper]
            [monadic-heap.api :as api]))

(deftest min-heap-addition
  (testing "min heap with some items"
    (let [list (shuffle [503 4200 6616 7538 2301 3668 7335 1985 3431 6581])
          heap (heap-helper/fill-from-list (api/min-heap) list)]
      (is (= (apply min list) (monad.maybe/from-maybe (peek heap)))))))

(deftest min-heap-removal
  (testing "min heap with 1000 items"
    (let [heap           (heap-helper/fill (api/min-heap) 1000)
          list-from-heap (heap-helper/->list heap)]
      (is (= (seq list-from-heap) (sort list-from-heap))))))
