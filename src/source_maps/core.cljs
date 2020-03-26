(ns source-maps.core)

(defn something-that-throws [_ _]
  (try
    (throw (js/Error. "Whoops"))
    (catch js/Error e (js/console.error e))))

(something-that-throws nil nil)

;; Expose cloud functions via `exports` global
(def exports
  {"index" something-that-throws})
