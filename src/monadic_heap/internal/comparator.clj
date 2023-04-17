(ns monadic-heap.internal.comparator
  (:require [cats.monad.maybe :as maybe]
            [monadic-heap.internal.list :as list]))

(defn more-relevant?
  [arr left right comparator-fn]
  (let [left-value  (list/value arr left)
        right-value (list/value arr right)]
    (cond
      (maybe/nothing? left-value)  false
      (maybe/nothing? right-value) true
      :else                        (comparator-fn
                                     (maybe/from-maybe left-value)
                                     (maybe/from-maybe right-value)))))
