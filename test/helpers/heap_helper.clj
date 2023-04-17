(ns heap-helper
  (:require [clojure.test :refer :all]
            [schema.core :as s])
  (:import monadic_heap.type.MonadicHeap))

(s/defn fill-from-list :- MonadicHeap
  [heap :- MonadicHeap
   items :- [s/Int]]
  (if (pos? (count items))
    (-> heap
        (conj (peek items))
        (recur (pop items)))
    heap))

(s/defn fill :- MonadicHeap
  [heap :- MonadicHeap
   num-items :- s/Int]
  (if (pos? num-items)
    (-> heap
        (conj (rand-int 10000))
        (recur (dec num-items)))
    heap))

(s/defn ->list :- [s/Any]
  ([heap :- MonadicHeap]
   (->list heap []))
  ([heap :- MonadicHeap
    arr :- [s/Any]]
   (if (= 0 (count heap))
     arr
     (let [top (peek heap)]
       (recur (pop heap) (conj arr top))))))
