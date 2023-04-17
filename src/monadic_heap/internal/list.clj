(ns monadic-heap.internal.list)

(defn value
  [arr node-index]
  (get arr node-index))

(defn swap
  [arr left right]
  (assoc arr
    right (value arr left)
    left  (value arr right)))
