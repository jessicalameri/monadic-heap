(ns heap-helper
  (:require [cats.monad.maybe :as maybe]
            [clojure.test :refer :all]))

(defn list->maybe-list
  [arr]
  (->> arr
       (map maybe/just)
       vec))

(defn add
  [heap value]
  (conj heap (maybe/just value)))

(defn fill-from-list
  [heap items]
  (if (pos? (count items))
    (-> heap
        (add (peek items))
        (recur (pop items)))
    heap))

(defn fill
  [heap num-items]
  (if (pos? num-items)
    (-> heap
        (add (rand-int 10000))
        (recur (dec num-items)))
    heap))

(defn ->list
  ([heap]
   (->list heap []))
  ([heap arr]
   (if (= 0 (count heap))
     arr
     (let [top (maybe/from-maybe (peek heap))]
       (recur (pop heap) (conj arr top))))))
