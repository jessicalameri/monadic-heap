(ns monadic-heap.type
  (:require [monadic-heap.internal.core :as internal.core])
  (:import clojure.lang.IPersistentList))

(deftype MonadicHeap [arr comparator-fn]
  Object
  (toString [this] (str (.seq this)))

  IPersistentList
  (seq [_]
    (seq arr))

  (count [_]
    (internal.core/size arr))

  (empty [_]
    (MonadicHeap. [] comparator-fn))

  (equiv [this other]
    (and (sequential? other) (= (.seq this) (seq other))))

  (cons [_ element]
    (-> (internal.core/add-to-heap arr element comparator-fn)
        (MonadicHeap. comparator-fn)))

  (peek [_]
    (internal.core/top arr))

  (pop [_]
    (-> (internal.core/remove-from-heap arr comparator-fn)
        (MonadicHeap. comparator-fn))))
