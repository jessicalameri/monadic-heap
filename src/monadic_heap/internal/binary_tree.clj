(ns monadic-heap.internal.binary-tree)

(defn ^:private find-parent
  [node-idx]
  (-> node-idx
      dec
      (/ 2)
      Math/floor
      int))

(defn parent
  [node-idx]
  (let [parent-found (find-parent node-idx)]
    (if (neg? parent-found)
      nil
      parent-found)))

(defn left-child
  [node-idx]
  (-> node-idx
      (* 2)
      inc))

(defn right-child
  [node-idx]
  (inc (left-child node-idx)))
