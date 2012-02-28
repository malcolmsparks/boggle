(ns boggle.core
  (:require [clojure.java.io :as io]))

(def alphabet (apply hash-set (map char (range (int \a) (inc (int \z))))))

(def alphas (map char (range (int \a) (inc (int \z)))))

(defn is-valid-word? [word]
  (every? alphabet word))

(defn get-word-trie []
  (reduce #(assoc-in %1 (vec %2) {}) {} (filter is-valid-word? (line-seq (io/reader (io/file "/home/malcolm/src/boggle/src/boggle/small-dictionary"))))))

(get-word-trie)

(defn get-row [] (apply vector (for [i (range 8)] {:i i :char (rand-nth alphas)})))

(def board (apply vector (for [j (range 8)] (map (partial merge {:j j}) (get-row)))))

(identity board)

(defn get-board-entry [i j]
  (nth (nth board i) j)
  )

(defn get-neighbors [entry]
  (for [x [-1 0 1]
        y [-1 0 1]
        :when (and (not (and (= 0 x) (= 0 y)))
                   (< (+ x (:i entry)) 8)
                   (>= (+ x (:i entry)) 0)
                   (< (+ y (:j entry)) 8)
                   (>= (+ y (:j entry))) 0)]
    (get-board-entry (+ x (:i entry)) (+ y (:j entry)))
    ))

(defn search [board]
  (loop [remaining (get-word-trie), results [], pos (get-board-entry 0 0)]
    (if (empty? remaining)
      results
      (for [p (get-neighbors pos)
            ]
        ))
    ))









