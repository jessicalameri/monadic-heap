(ns monadic-heap.type
  (:require [cats.data :as data]
            [monadic-heap.internal.core :as internal.core])
  (:import clojure.lang.IPersistentList))

(deftype MonadicHeap [pair comparator-fn]
  Object
  (toString [this] (str (.seq this)))

  IPersistentList
  (seq [_]
    (seq (nth pair 1)))

  (count [_]
    (internal.core/current-size pair))

  (empty [_]
    (MonadicHeap. (data/pair 0 []) comparator-fn))

  (equiv [this other]
    (and (sequential? other) (= (.seq this) (seq other))))

  (cons [_ element]
    (-> (internal.core/add-to-heap pair element comparator-fn)
        (MonadicHeap. comparator-fn)))

  (peek [_]
    (internal.core/top pair))

  (pop [_]
    (-> (internal.core/remove-from-heap pair comparator-fn)
        (MonadicHeap. comparator-fn))))
