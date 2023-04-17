(ns monadic-heap.internal.core
  (:require [cats.data :as data]
            [cats.monad.maybe :as maybe]
            [monadic-heap.internal.heapify :as heapify]
            [monadic-heap.internal.list :as list]))

(defn current-size
  [pair]
  (nth pair 0))

(defn top
  [pair]
  (first (nth pair 1)))

(defn ->pair
  [arr]
  (data/pair (count arr) arr))

(defn adapt-element
  [element]
  (cond
    (maybe/maybe? element) element
    (nil? element)         (maybe/nothing)
    :else                  (maybe/just element)))

(defn add-to-heap
  [pair element comparator-fn]
  (let [new-index (current-size pair)
        element   (adapt-element element)
        new-arr   (conj (nth pair 1) element)]
    (->pair (heapify/up new-arr new-index comparator-fn))))

(defn remove-from-heap
  [pair comparator-fn]
  (let [last-index (dec (current-size pair))]
    (if (pos? last-index)
      (-> pair
          (nth 1)
          (list/swap 0 last-index)
          pop
          (heapify/down 0 comparator-fn)
          ->pair)
      (->pair []))))

