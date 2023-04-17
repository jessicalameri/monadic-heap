(ns monadic-heap.api
  (:require [schema.core :as s])
  (:import monadic_heap.type.MonadicHeap))

(s/defn heap :- MonadicHeap
  [comparator-fn :- (s/pred ifn?)]
  (MonadicHeap. [] comparator-fn))

(s/defn min-heap :- MonadicHeap
  []
  (heap <))

(s/defn max-heap :- MonadicHeap
  []
  (heap >))
