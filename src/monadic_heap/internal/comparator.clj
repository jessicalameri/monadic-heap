(ns monadic-heap.internal.comparator
  (:require [monadic-heap.internal.list :as list]
            [schema.core :as s]))

(s/defn more-relevant? :- s/Bool
  [arr :- [s/Any]
   left :- s/Int
   right :- s/Int
   comparator-fn :- (s/pred ifn?)]
  (let [left-value  (list/value arr left)
        right-value (list/value arr right)]
    (cond
      (nil? left-value)  false
      (nil? right-value) true
      :else              (comparator-fn left-value right-value))))
