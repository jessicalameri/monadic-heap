(ns monadic-heap.internal.core
  (:require [cats.monad.maybe :as monad.maybe]
            [monadic-heap.internal.heapify :as heapify]
            [monadic-heap.internal.list :as list]
            [schema.core :as s]))

(s/defn size :- s/Int
  [arr :- [s/Any]]
  (count arr))

(s/defn top
  [arr :- [s/Any]]
  (if-let [value (first arr)]
    (monad.maybe/just value)
    (monad.maybe/nothing)))

(s/defn ^:private last-index :- s/Int
  [arr :- [s/Any]]
  (dec (size arr)))

(s/defn add-to-heap
  [arr :- [s/Any]
   element :- s/Any
   comparator-fn :- (s/pred ifn?)]
  (let [new-arr (conj arr element)
        index   (last-index new-arr)]
    (heapify/up new-arr index comparator-fn)))

(s/defn remove-from-heap
  [arr :- [s/Any]
   comparator-fn :- (s/pred ifn?)]
  (let [last-index (last-index arr)]
    (if (pos? last-index)
      (-> arr
          (list/swap 0 last-index)
          pop
          (heapify/down 0 comparator-fn))
      [])))

