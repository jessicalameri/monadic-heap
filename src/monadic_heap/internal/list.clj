(ns monadic-heap.internal.list
  (:require [schema.core :as s]))

(s/defn value :- (s/maybe s/Any)
  [arr :- [s/Any]
   node-index :- s/Int]
  (get arr node-index))

(s/defn swap :- [s/Any]
  [arr :- [s/Any]
   left :- s/Int
   right :- s/Int]
  (assoc arr
    right (value arr left)
    left  (value arr right)))
