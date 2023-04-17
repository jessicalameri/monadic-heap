(ns monadic-heap.internal.comparator-test
  (:require [clojure.test :refer [is testing deftest]]
            [heap-helper :as heap]
            [monadic-heap.internal.comparator :as comparator]))

(def default-list (heap/list->maybe-list [10 30 50 40 20]))

(deftest more-relevant?-test
  (testing "10 isnt greater than 30 "
    (is (false? (comparator/more-relevant? default-list 0 1 >))))

  (testing "50 is greater than 40"
    (is (true? (comparator/more-relevant? default-list 2 3 >))))

  (testing "if left value doesnt exist it isn't more relevant"
    (is (false? (comparator/more-relevant? default-list -1 3 >))))

  (testing "if right value doesnt exist but left does, it is more relevant"
    (is (true? (comparator/more-relevant? default-list 0 -1 >)))))

