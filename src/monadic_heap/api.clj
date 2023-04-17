(ns monadic-heap.api
  (:require [cats.data :as data])
  (:import monadic_heap.type.MonadicHeap))

(defn heap
  [comparator-fn]
  (MonadicHeap. (data/pair 0 []) comparator-fn))

(defn min-heap
  []
  (heap <=))

(defn max-heap
  []
  (heap >=))
