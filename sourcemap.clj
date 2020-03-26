(require '[clojure.java.io :as io])
(import '[com.google.debugging.sourcemap SourceMapConsumerV3])

(defn lookup [map-name line column]
  (let [sm-consumer (SourceMapConsumerV3.)
        sm-file (io/file map-name)]
    (.parse sm-consumer (slurp sm-file))
    (when-let [mapping (.getMappingForLine sm-consumer line column)]
      {:original (.getOriginalFile mapping)
       :line (.getLineNumber mapping)
       :column (.getColumnPosition mapping)}
      )))

(let [[f line col] *command-line-args*]
  (println ";;" f "line" line "col" col)
  (prn (lookup f (Integer/parseInt line) (Integer/parseInt col))))

;; cllj sourcemap.clj functions/index.map.js 3333 1
