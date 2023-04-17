(ns monadic-heap.internal.heapify
  (:require [monadic-heap.internal.binary-tree :as tree]
            [monadic-heap.internal.comparator :as comparator]
            [monadic-heap.internal.list :as list]))

(defn up
  [arr index comparator-fn]
  (if-let [parent (tree/parent index)]
    (if (comparator/more-relevant? arr index parent comparator-fn)
      (-> arr
          (list/swap index parent)
          (recur parent comparator-fn))
      arr)
    arr))

(defn down
  [arr index comparator-fn]
  (let [left-child  (tree/left-child index)
        right-child (tree/right-child index)]
    (cond
      (and (comparator/more-relevant? arr left-child right-child comparator-fn)
           (comparator/more-relevant? arr left-child index comparator-fn))
      (-> arr
          (list/swap index left-child)
          (recur left-child comparator-fn))

      (and (comparator/more-relevant? arr right-child left-child comparator-fn)
           (comparator/more-relevant? arr right-child index comparator-fn))
      (-> arr
          (list/swap index right-child)
          (recur right-child comparator-fn))

      :else arr)))
