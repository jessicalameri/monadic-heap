(ns monadic-heap.internal.binary-tree
  (:require [schema.core :as s]))

(s/defn ^:private find-parent :- s/Int
  [node-idx :- s/Int]
  (-> node-idx
      dec
      (/ 2)
      Math/floor
      int))

(s/defn parent :- (s/maybe s/Int)
  [node-idx :- s/Int]
  (let [parent-found (find-parent node-idx)]
    (if (neg? parent-found)
      nil
      parent-found)))

(s/defn left-child :- s/Int
  [node-idx :- s/Int]
  (-> node-idx
      (* 2)
      inc))

(s/defn right-child :- s/Int
  [node-idx :- s/Int]
  (inc (left-child node-idx)))
