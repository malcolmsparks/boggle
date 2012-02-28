(ns boggle.core
  (:require [clojure.java.io :as io]))

(def alphabet (apply hash-set (map char (range (int \a) (inc (int \z))))))

(defn is-valid-word? [word]
  (every? alphabet word))

(reduce #(assoc-in %1 (vec %2) {}) {} (filter is-valid-word? (line-seq (io/reader (io/file "/home/malcolm/src/boggle/src/boggle/small-dictionary")))))






