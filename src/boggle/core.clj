(ns boggle.core
  (:require [clojure.java.io :as io]))

(def alphabet (apply hash-set (map char (range (int \a) (inc (int \z))))))

(def alphas (map char (range (int \a) (inc (int \z)))))

(defn is-valid-word? [word]
  (every? alphabet word))

(defn get-word-trie []
  (reduce #(assoc-in %1 (vec %2) {}) {} (filter is-valid-word? (line-seq (io/reader (io/file "/home/malcolm/src/boggle/src/boggle/dictionary"))))))

(get-word-trie)

(defn get-row [] (apply vector (for [i (range 8)] {:i i :char (rand-nth alphas)})))

(def board (apply vector (for [j (range 8)] (map (partial merge {:j j}) (get-row)))))

(identity board)

(defn get-board-entry [i j]
  (println i "  - " j)
  (nth (nth board i) j)
  )

(defn get-neighbors [entry]
  (for [x [-1 0 1]
        y [-1 0 1]
        :when (and (not (and (= 0 x) (= 0 y)))
                   (< (+ x (:i entry)) 8)
                   (>= (+ x (:i entry)) 0)
                   (< (+ y (:j entry)) 8)
                   (>= (+ y (:j entry)) 0))]
    (get-board-entry (+ x (:i entry)) (+ y (:j entry)))
    ))

(defn search [board]
  (letfn [(foo [visited , remaining , results , pos ]
            (println visited results pos)
            (if (empty? remaining)
              results
              (for [p (get-neighbors pos)
            k (keys remaining)
            :when (= (:char p) k)
            ]
        (if (empty? (get remaining k))
          (conj visited k)
          (foo (conj visited k) (get remaining k) results p))
        )))]
    (foo [] (get-word-trie) [] (get-board-entry 0 0))
    
    ))

0   -  0
[] [] {:char m, :i 0, :j 0}
(0   -  1
[a] [] {:char a, :i 1, :j 0}
1   -  0
1   -  1
[s] [] {:char s, :i 0, :j 1}
(0   -  0
0   -  1
[a m] [] {:char m, :i 0, :j 0}
[a a] [] {:char a, :i 1, :j 0}
(0   -  1
[a m a] [] {:char a, :i 1, :j 0}
1   -  0
1   -  1
[a m t] [] {:char t, :i 1, :j 1}
(0   -  0
0   -  1
1   -  1
[a m a t] [] {:char t, :i 1, :j 1}
2   -  0
2   -  1
[a m a b] [] {:char b, :i 0, :j 2}
(0   -  0
0   -  1
0   -  2
[a m a t e] [] {:char e, :i 2, :j 0}
1   -  0
1   -  2
2   -  0
2   -  1
2   -  2
(1   -  0
1   -  1
2   -  1
[a m a t e u] [] {:char u, :i 1, :j 2}
[\a \m \a \t \e \s] 3   -  0
3   -  1
(0   -  1
0   -  2
0   -  3
[a m a t e u r] [] {:char r, :i 3, :j 0}
1   -  1
1   -  3
2   -  1
2   -  2
2   -  3
(2   -  0
2   -  1
3   -  1
4   -  0
4   -  1
)))) [a m a u] [] {:char u, :i 1, :j 2}
(0   -  1
0   -  3
1   -  1
1   -  2
1   -  3
) (0   -  1
0   -  2
0   -  3
[a m a u r] [] {:char r, :i 3, :j 0}
1   -  1
1   -  3
[a m a u t] [] {:char t, :i 1, :j 1}
(2   -  0
2   -  1
3   -  1
4   -  0
4   -  1
) 2   -  1
2   -  2
2   -  3
(0   -  0
0   -  1
0   -  2
1   -  0
1   -  2
2   -  0
2   -  1
2   -  2
))) (0   -  0
0   -  1
0   -  2
[a m t m] [] {:char m, :i 0, :j 0}
1   -  0
1   -  2
2   -  0
2   -  1
2   -  2
(0   -  1
[a m t m a] [] {:char a, :i 1, :j 0}
1   -  0
1   -  1
(0   -  0
0   -  1
1   -  1
2   -  0
2   -  1
)))) 1   -  1
[a t] [] {:char t, :i 1, :j 1}
(0   -  0
0   -  1
1   -  1
2   -  0
2   -  1
) 2   -  0
2   -  1
[a b] [] {:char b, :i 0, :j 2}
(0   -  0
0   -  1
0   -  2
[a t m] [] {:char m, :i 0, :j 0}
[a t a] [] {:char a, :i 1, :j 0}
(0   -  1
[a t m a] [] {:char a, :i 1, :j 0}
1   -  0
1   -  1
(0   -  0
0   -  1
1   -  1
2   -  0
2   -  1
)) [a t e] [] {:char e, :i 2, :j 0}
(0   -  0
0   -  1
[a t a m] [] {:char m, :i 0, :j 0}
1   -  1
2   -  0
2   -  1
[a t a b] [] {:char b, :i 0, :j 2}
(0   -  1
[a t a m a] [] {:char a, :i 1, :j 0}
1   -  0
1   -  1
(0   -  0
0   -  1
1   -  1
2   -  0
2   -  1
)) (0   -  1
0   -  3
[a t a b a] [] {:char a, :i 1, :j 0}
[a t a b r] [] {:char r, :i 3, :j 0}
(0   -  0
0   -  1
1   -  1
2   -  0
2   -  1
) 1   -  1
1   -  2
1   -  3
(2   -  0
2   -  1
3   -  1
4   -  0
4   -  1
))) 1   -  0
1   -  2
2   -  0
2   -  1
2   -  2
[a t p] [] {:char p, :i 2, :j 2}
(1   -  0
1   -  1
2   -  1
3   -  0
3   -  1
[\a \t \e \s]) (1   -  1
1   -  2
1   -  3
2   -  1
2   -  3
[a t p o] [] {:char o, :i 3, :j 2}
3   -  1
3   -  2
3   -  3
(2   -  1
2   -  2
2   -  3
3   -  1
3   -  3
4   -  1
4   -  2
4   -  3
))) [a u] [] {:char u, :i 1, :j 2}
(0   -  1
0   -  3
[a b a] [] {:char a, :i 1, :j 0}
[a b r] [] {:char r, :i 3, :j 0}
(0   -  0
0   -  1
[a b a m] [] {:char m, :i 0, :j 0}
1   -  1
[a b a t] [] {:char t, :i 1, :j 1}
(0   -  1
1   -  0
1   -  1
) 2   -  0
2   -  1
(0   -  0
0   -  1
0   -  2
[a b a t a] [] {:char a, :i 1, :j 0}
[a b a t e] [] {:char e, :i 2, :j 0}
(0   -  0
0   -  1
1   -  1
2   -  0
2   -  1
[a b a t a b] [] {:char b, :i 0, :j 2}
(0   -  1
0   -  3
1   -  1
1   -  2
1   -  3
)) 1   -  0
1   -  2
2   -  0
2   -  1
2   -  2
[a b a t u] [] {:char u, :i 1, :j 2}
(1   -  0
1   -  1
2   -  1
3   -  0
3   -  1
[\a \b \a \t \e \s]) (0   -  1
0   -  2
0   -  3
[a b a t u r] [] {:char r, :i 3, :j 0}
1   -  1
1   -  3
2   -  1
2   -  2
2   -  3
(2   -  0
2   -  1
3   -  1
4   -  0
4   -  1
)))) 1   -  1
1   -  2
1   -  3
[a b t] [] {:char t, :i 1, :j 1}
(2   -  0
2   -  1
[a b r u] [] {:char u, :i 1, :j 2}
3   -  1
4   -  0
4   -  1
(0   -  1
0   -  2
0   -  3
1   -  1
1   -  3
2   -  1
2   -  2
2   -  3
[a b r u p] [] {:char p, :i 2, :j 2}
(1   -  1
1   -  2
1   -  3
[a b r u p t] [] {:char t, :i 1, :j 1}
2   -  1
2   -  3
3   -  1
3   -  2
3   -  3
(0   -  0
0   -  1
0   -  2
[a b r u p t e] [] {:char e, :i 2, :j 0}
1   -  0
1   -  2
2   -  0
2   -  1
2   -  2
(1   -  0
1   -  1
[a b r u p t e s] [] {:char s, :i 0, :j 1}
2   -  1
3   -  0
3   -  1
(0   -  0
0   -  2
1   -  0
1   -  1
1   -  2
[\a \b \r \u \p \t \e \s \t])))))) [a b b] [] {:char b, :i 2, :j 1}
(0   -  0
0   -  1
0   -  2
1   -  0
1   -  2
2   -  0
2   -  1
2   -  2
) [a b y] [] {:char y, :i 3, :j 1}
(1   -  0
1   -  1
1   -  2
2   -  0
2   -  2
3   -  0
3   -  1
3   -  2
[\a \b \b \s]) (2   -  0
2   -  1
2   -  2
3   -  0
3   -  2
4   -  0
4   -  1
4   -  2
)) (0   -  1
0   -  2
0   -  3
[a u r] [] {:char r, :i 3, :j 0}
1   -  1
1   -  3
[a u t] [] {:char t, :i 1, :j 1}
(2   -  0
2   -  1
[a u r u] [] {:char u, :i 1, :j 2}
3   -  1
4   -  0
4   -  1
(0   -  1
0   -  2
0   -  3
1   -  1
1   -  3
2   -  1
2   -  2
2   -  3
)) 2   -  1
2   -  2
2   -  3
(0   -  0
0   -  1
0   -  2
[a u t a] [] {:char a, :i 1, :j 0}
[a u t e] [] {:char e, :i 2, :j 0}
(0   -  0
0   -  1
1   -  1
2   -  0
2   -  1
) 1   -  0
1   -  2
2   -  0
2   -  1
2   -  2
[a u t u] [] {:char u, :i 1, :j 2}
(1   -  0
1   -  1
2   -  1
[a u t e u] [] {:char u, :i 1, :j 2}
3   -  0
3   -  1
(0   -  1
0   -  2
0   -  3
[a u t e u r] [] {:char r, :i 3, :j 0}
1   -  1
1   -  3
2   -  1
2   -  2
2   -  3
(2   -  0
2   -  1
3   -  1
4   -  0
4   -  1
))) (0   -  1
0   -  2
0   -  3
1   -  1
1   -  3
2   -  1
2   -  2
2   -  3
)))) [t] [] {:char t, :i 1, :j 1}
(0   -  0
0   -  2
[s m] [] {:char m, :i 0, :j 0}
[s e] [] {:char e, :i 2, :j 0}
(0   -  1
[s m a] [] {:char a, :i 1, :j 0}
1   -  0
1   -  1
(0   -  0
0   -  1
1   -  1
[s m a t] [] {:char t, :i 1, :j 1}
2   -  0
2   -  1
(0   -  0
0   -  1
0   -  2
1   -  0
1   -  2
2   -  0
2   -  1
2   -  2
))) 1   -  0
1   -  1
1   -  2
[s s] [] {:char s, :i 0, :j 1}
(1   -  0
1   -  1
[s e s] [] {:char s, :i 0, :j 1}
[s e t] [] {:char t, :i 1, :j 1}
(0   -  0
0   -  2
[s e s e] [] {:char e, :i 2, :j 0}
1   -  0
1   -  1
1   -  2
[s e s s] [] {:char s, :i 0, :j 1}
(1   -  0
1   -  1
2   -  1
3   -  0
3   -  1
) [s e s t] [] {:char t, :i 1, :j 1}
(0   -  0
0   -  2
1   -  0
1   -  1
1   -  2
) (0   -  0
0   -  1
0   -  2
[s e s t e] [] {:char e, :i 2, :j 0}
1   -  0
1   -  2
2   -  0
2   -  1
2   -  2
(1   -  0
1   -  1
[s e s t e t] [] {:char t, :i 1, :j 1}
2   -  1
3   -  0
3   -  1
(0   -  0
0   -  1
0   -  2
1   -  0
1   -  2
2   -  0
2   -  1
2   -  2
[\s \e \s \t \e \t \s])))) 2   -  1
3   -  0
3   -  1
[s e q] [] {:char q, :i 0, :j 3}
(0   -  0
0   -  1
0   -  2
[s e t a] [] {:char a, :i 1, :j 0}
[s e t e] [] {:char e, :i 2, :j 0}
(0   -  0
0   -  1
1   -  1
2   -  0
2   -  1
) 1   -  0
1   -  2
[s e t s] [] {:char s, :i 0, :j 1}
(1   -  0
1   -  1
2   -  1
3   -  0
3   -  1
) [s e t b] [] {:char b, :i 2, :j 1}
(0   -  0
0   -  2
1   -  0
1   -  1
1   -  2
) 2   -  0
2   -  1
2   -  2
[s e t b] [] {:char b, :i 0, :j 2}
(1   -  0
1   -  1
1   -  2
2   -  0
2   -  2
3   -  0
3   -  1
3   -  2
) [s e t u] [] {:char u, :i 1, :j 2}
(0   -  1
0   -  3
[s e t b a] [] {:char a, :i 1, :j 0}
1   -  1
1   -  2
1   -  3
(0   -  0
0   -  1
1   -  1
2   -  0
2   -  1
)) [s e t p] [] {:char p, :i 2, :j 2}
(0   -  1
0   -  2
0   -  3
1   -  1
1   -  3
2   -  1
2   -  2
2   -  3
[s e t u p] [] {:char p, :i 2, :j 2}
(1   -  1
1   -  2
1   -  3
2   -  1
2   -  3
3   -  1
3   -  2
3   -  3
)) (1   -  1
1   -  2
1   -  3
2   -  1
2   -  3
[s e t p o] [] {:char o, :i 3, :j 2}
3   -  1
3   -  2
3   -  3
(2   -  1
2   -  2
2   -  3
3   -  1
3   -  3
4   -  1
4   -  2
4   -  3
))) (0   -  2
0   -  4
1   -  2
1   -  3
1   -  4
)) [s t] [] {:char t, :i 1, :j 1}
(0   -  0
0   -  2
1   -  0
1   -  1
1   -  2
) [s b] [] {:char b, :i 2, :j 1}
(0   -  0
0   -  1
0   -  2
[s t a] [] {:char a, :i 1, :j 0}
[s t e] [] {:char e, :i 2, :j 0}
(0   -  0
0   -  1
[s t a m] [] {:char m, :i 0, :j 0}
1   -  1
[s t a t] [] {:char t, :i 1, :j 1}
(0   -  1
1   -  0
1   -  1
) 2   -  0
2   -  1
[s t a b] [] {:char b, :i 0, :j 2}
(0   -  0
0   -  1
0   -  2
[s t a t a] [] {:char a, :i 1, :j 0}
[s t a t e] [] {:char e, :i 2, :j 0}
(0   -  0
0   -  1
[s t a t a m] [] {:char m, :i 0, :j 0}
1   -  1
2   -  0
2   -  1
[s t a t a b] [] {:char b, :i 0, :j 2}
(0   -  1
1   -  0
1   -  1
) (0   -  1
0   -  3
1   -  1
1   -  2
1   -  3
)) 1   -  0
1   -  2
(1   -  0
1   -  1
[s t a t e s] [] {:char s, :i 0, :j 1}
2   -  1
3   -  0
3   -  1
(0   -  0
0   -  2
[s t a t e s m] [] {:char m, :i 0, :j 0}
1   -  0
1   -  1
1   -  2
(0   -  1
[s t a t e s m a] [] {:char a, :i 1, :j 0}
1   -  0
1   -  1
(0   -  0
0   -  1
1   -  1
2   -  0
2   -  1
)))) 2   -  0
2   -  1
2   -  2
[s t a t u] [] {:char u, :i 1, :j 2}
[\s \t \a \t \s] (0   -  1
0   -  2
0   -  3
[s t a t u a] [] {:char a, :i 1, :j 0}
[s t a t u e] [] {:char e, :i 2, :j 0}
(0   -  0
0   -  1
1   -  1
2   -  0
2   -  1
) [s t a t u r] [] {:char r, :i 3, :j 0}
(1   -  0
1   -  1
[s t a t u e s] [] {:char s, :i 0, :j 1}
[s t a t u e t] [] {:char t, :i 1, :j 1}
(0   -  0
0   -  2
1   -  0
1   -  1
1   -  2
) 2   -  1
3   -  0
3   -  1
(0   -  0
0   -  1
0   -  2
1   -  0
1   -  2
2   -  0
2   -  1
2   -  2
)) 1   -  1
1   -  3
[s t a t u t] [] {:char t, :i 1, :j 1}
(2   -  0
2   -  1
3   -  1
4   -  0
4   -  1
) 2   -  1
2   -  2
2   -  3
(0   -  0
0   -  1
0   -  2
[s t a t u t a] [] {:char a, :i 1, :j 0}
[s t a t u t e] [] {:char e, :i 2, :j 0}
(0   -  0
0   -  1
1   -  1
2   -  0
2   -  1
[s t a t u t a b] [] {:char b, :i 0, :j 2}
(0   -  1
0   -  3
1   -  1
1   -  2
1   -  3
)) 1   -  0
1   -  2
2   -  0
2   -  1
2   -  2
(1   -  0
1   -  1
2   -  1
3   -  0
3   -  1
[\s \t \a \t \u \t \e \s])))) [s t a u] [] {:char u, :i 1, :j 2}
(0   -  1
0   -  3
1   -  1
1   -  2
1   -  3
[s t a b b] [] {:char b, :i 2, :j 1}
(1   -  0
1   -  1
1   -  2
2   -  0
2   -  2
3   -  0
3   -  1
3   -  2
)) (0   -  1
0   -  2
0   -  3
[s t a u r] [] {:char r, :i 3, :j 0}
1   -  1
1   -  3
2   -  1
2   -  2
2   -  3
(2   -  0
2   -  1
3   -  1
4   -  0
4   -  1
))) 1   -  0
1   -  2
2   -  0
2   -  1
2   -  2
[s t u] [] {:char u, :i 1, :j 2}
(1   -  0
1   -  1
[s t e t] [] {:char t, :i 1, :j 1}
2   -  1
3   -  0
3   -  1
(0   -  0
0   -  1
0   -  2
1   -  0
1   -  2
[s t e t s] [] {:char s, :i 0, :j 1}
2   -  0
2   -  1
2   -  2
(0   -  0
0   -  2
1   -  0
1   -  1
1   -  2
))) (0   -  1
0   -  2
0   -  3
[s t u r] [] {:char r, :i 3, :j 0}
1   -  1
1   -  3
[s t u t] [] {:char t, :i 1, :j 1}
(2   -  0
2   -  1
3   -  1
4   -  0
4   -  1
) 2   -  1
2   -  2
2   -  3
[s t u p] [] {:char p, :i 2, :j 2}
(0   -  0
0   -  1
0   -  2
1   -  0
1   -  2
2   -  0
2   -  1
2   -  2
) (1   -  1
1   -  2
1   -  3
2   -  1
2   -  3
[s t u p o] [] {:char o, :i 3, :j 2}
3   -  1
3   -  2
3   -  3
(2   -  1
2   -  2
2   -  3
3   -  1
3   -  3
4   -  1
4   -  2
4   -  3
)))) (1   -  0
1   -  1
1   -  2
2   -  0
2   -  2
3   -  0
3   -  1
3   -  2
)) (0   -  0
0   -  1
0   -  2
[t m] [] {:char m, :i 0, :j 0}
[t a] [] {:char a, :i 1, :j 0}
(0   -  1
1   -  0
1   -  1
) [t e] [] {:char e, :i 2, :j 0}
(0   -  0
0   -  1
[t a m] [] {:char m, :i 0, :j 0}
[t a a] [] {:char a, :i 1, :j 0}
(0   -  1
[t a m a] [] {:char a, :i 1, :j 0}
1   -  0
1   -  1
(0   -  0
0   -  1
1   -  1
2   -  0
2   -  1
[t a m a b] [] {:char b, :i 0, :j 2}
(0   -  1
0   -  3
1   -  1
1   -  2
1   -  3
)) [\t \a \m \s]) 1   -  1
[t a t] [] {:char t, :i 1, :j 1}
(0   -  0
0   -  1
1   -  1
2   -  0
2   -  1
) 2   -  0
2   -  1
[t a b] [] {:char b, :i 0, :j 2}
(0   -  0
0   -  1
0   -  2
[t a t a] [] {:char a, :i 1, :j 0}
[t a t e] [] {:char e, :i 2, :j 0}
(0   -  0
0   -  1
[t a t a m] [] {:char m, :i 0, :j 0}
1   -  1
2   -  0
2   -  1
(0   -  1
1   -  0
1   -  1
)) 1   -  0
1   -  2
(1   -  0
1   -  1
2   -  1
3   -  0
3   -  1
[\t \a \t \e \s]) 2   -  0
2   -  1
2   -  2
[t a t u] [] {:char u, :i 1, :j 2}
[\t \a \t \s] [t a t p] [] {:char p, :i 2, :j 2}
(0   -  1
0   -  2
0   -  3
1   -  1
1   -  3
2   -  1
2   -  2
2   -  3
) (1   -  1
1   -  2
1   -  3
2   -  1
2   -  3
[t a t p u] [] {:char u, :i 1, :j 2}
3   -  1
3   -  2
3   -  3
(0   -  1
0   -  2
0   -  3
[t a t p u r] [] {:char r, :i 3, :j 0}
1   -  1
1   -  3
2   -  1
2   -  2
2   -  3
(2   -  0
2   -  1
[t a t p u r u] [] {:char u, :i 1, :j 2}
3   -  1
4   -  0
4   -  1
(0   -  1
0   -  2
0   -  3
1   -  1
1   -  3
2   -  1
2   -  2
2   -  3
))))) [t a u] [] {:char u, :i 1, :j 2}
(0   -  1
0   -  3
[t a b a] [] {:char a, :i 1, :j 0}
[t a b r] [] {:char r, :i 3, :j 0}
(0   -  0
0   -  1
1   -  1
2   -  0
2   -  1
) 1   -  1
1   -  2
1   -  3
[t a b b] [] {:char b, :i 2, :j 1}
(2   -  0
2   -  1
3   -  1
4   -  0
4   -  1
) (1   -  0
1   -  1
1   -  2
2   -  0
2   -  2
3   -  0
3   -  1
3   -  2
)) (0   -  1
0   -  2
0   -  3
[t a u r] [] {:char r, :i 3, :j 0}
1   -  1
1   -  3
[t a u t] [] {:char t, :i 1, :j 1}
(2   -  0
2   -  1
3   -  1
4   -  0
4   -  1
) 2   -  1
2   -  2
2   -  3
[t a u p] [] {:char p, :i 2, :j 2}
(0   -  0
0   -  1
0   -  2
[t a u t a] [] {:char a, :i 1, :j 0}
[t a u t e] [] {:char e, :i 2, :j 0}
(0   -  0
0   -  1
1   -  1
2   -  0
2   -  1
[t a u t a u] [] {:char u, :i 1, :j 2}
(0   -  1
0   -  2
0   -  3
1   -  1
1   -  3
2   -  1
2   -  2
2   -  3
)) 1   -  0
1   -  2
(1   -  0
1   -  1
[t a u t e s] [] {:char s, :i 0, :j 1}
2   -  1
3   -  0
3   -  1
(0   -  0
0   -  2
1   -  0
1   -  1
1   -  2
[\t \a \u \t \e \s \t])) 2   -  0
2   -  1
2   -  2
[\t \a \u \t \s]) [t a u o] [] {:char o, :i 3, :j 2}
(1   -  1
1   -  2
1   -  3
2   -  1
2   -  3
3   -  1
3   -  2
3   -  3
) (2   -  1
2   -  2
2   -  3
3   -  1
3   -  3
4   -  1
4   -  2
4   -  3
))) 1   -  0
1   -  2
[t s] [] {:char s, :i 0, :j 1}
(1   -  0
1   -  1
[t e s] [] {:char s, :i 0, :j 1}
[t e t] [] {:char t, :i 1, :j 1}
(0   -  0
0   -  2
1   -  0
1   -  1
1   -  2
[t e s s] [] {:char s, :i 0, :j 1}
[t e s t] [] {:char t, :i 1, :j 1}
(0   -  0
0   -  2
[t e s s e] [] {:char e, :i 2, :j 0}
1   -  0
1   -  1
1   -  2
(1   -  0
1   -  1
2   -  1
3   -  0
3   -  1
)) (0   -  0
0   -  1
0   -  2
[t e s t m] [] {:char m, :i 0, :j 0}
[t e s t a] [] {:char a, :i 1, :j 0}
(0   -  1
[t e s t m a] [] {:char a, :i 1, :j 0}
1   -  0
1   -  1
(0   -  0
0   -  1
1   -  1
[t e s t m a t] [] {:char t, :i 1, :j 1}
2   -  0
2   -  1
(0   -  0
0   -  1
0   -  2
1   -  0
1   -  2
2   -  0
2   -  1
2   -  2
))) [t e s t e] [] {:char e, :i 2, :j 0}
(0   -  0
0   -  1
[t e s t a m] [] {:char m, :i 0, :j 0}
1   -  1
[t e s t a t] [] {:char t, :i 1, :j 1}
(0   -  1
1   -  0
1   -  1
) 2   -  0
2   -  1
[t e s t a b] [] {:char b, :i 0, :j 2}
(0   -  0
0   -  1
0   -  2
[t e s t a t e] [] {:char e, :i 2, :j 0}
1   -  0
1   -  2
2   -  0
2   -  1
2   -  2
[t e s t a t u] [] {:char u, :i 1, :j 2}
(1   -  0
1   -  1
2   -  1
3   -  0
3   -  1
[\t \e \s \t \a \t \e \s]) (0   -  1
0   -  2
0   -  3
1   -  1
1   -  3
2   -  1
2   -  2
2   -  3
)) (0   -  1
0   -  3
1   -  1
1   -  2
1   -  3
)) 1   -  0
1   -  2
(1   -  0
1   -  1
2   -  1
3   -  0
3   -  1
[\t \e \s \t \e \s]) [t e s t b] [] {:char b, :i 2, :j 1}
[\t \e \s \t \s] 2   -  0
2   -  1
2   -  2
[t e s t b] [] {:char b, :i 0, :j 2}
(1   -  0
1   -  1
1   -  2
2   -  0
2   -  2
3   -  0
3   -  1
3   -  2
) [t e s t u] [] {:char u, :i 1, :j 2}
(0   -  1
0   -  3
1   -  1
1   -  2
1   -  3
) (0   -  1
0   -  2
0   -  3
1   -  1
1   -  3
2   -  1
2   -  2
2   -  3
))) 2   -  1
[t e u] [] {:char u, :i 1, :j 2}
(0   -  0
0   -  1
0   -  2
[t e t a] [] {:char a, :i 1, :j 0}
1   -  0
1   -  2
(0   -  0
0   -  1
1   -  1
2   -  0
2   -  1
) 2   -  0
2   -  1
2   -  2
[\t \e \t \s]) 3   -  0
3   -  1
[t e q] [] {:char q, :i 0, :j 3}
(0   -  1
0   -  2
0   -  3
1   -  1
1   -  3
[t e u t] [] {:char t, :i 1, :j 1}
2   -  1
2   -  2
2   -  3
(0   -  0
0   -  1
0   -  2
1   -  0
1   -  2
2   -  0
2   -  1
2   -  2
)) [t e f] [] {:char f, :i 1, :j 3}
(0   -  2
0   -  4
1   -  2
1   -  3
1   -  4
) (0   -  2
0   -  3
0   -  4
1   -  2
1   -  4
2   -  2
2   -  3
2   -  4
)) [t b] [] {:char b, :i 2, :j 1}
(0   -  0
0   -  2
[t s e] [] {:char e, :i 2, :j 0}
1   -  0
1   -  1
1   -  2
(1   -  0
1   -  1
[t s e s] [] {:char s, :i 0, :j 1}
[t s e t] [] {:char t, :i 1, :j 1}
(0   -  0
0   -  2
1   -  0
1   -  1
1   -  2
[t s e s s] [] {:char s, :i 0, :j 1}
(0   -  0
0   -  2
[t s e s s e] [] {:char e, :i 2, :j 0}
1   -  0
1   -  1
1   -  2
(1   -  0
1   -  1
2   -  1
3   -  0
3   -  1
))) 2   -  1
3   -  0
3   -  1
(0   -  0
0   -  1
0   -  2
1   -  0
1   -  2
[t s e t s] [] {:char s, :i 0, :j 1}
2   -  0
2   -  1
2   -  2
(0   -  0
0   -  2
[t s e t s e] [] {:char e, :i 2, :j 0}
1   -  0
1   -  1
1   -  2
(1   -  0
1   -  1
2   -  1
3   -  0
3   -  1
[\t \s \e \t \s \e \s]))))) 2   -  0
2   -  1
2   -  2
[t b] [] {:char b, :i 0, :j 2}
(1   -  0
1   -  1
1   -  2
[t b s] [] {:char s, :i 0, :j 1}
2   -  0
2   -  2
3   -  0
3   -  1
3   -  2
(0   -  0
0   -  2
1   -  0
1   -  1
1   -  2
)) [t u] [] {:char u, :i 1, :j 2}
(0   -  1
0   -  3
1   -  1
1   -  2
1   -  3
) (0   -  1
0   -  2
0   -  3
[t u a] [] {:char a, :i 1, :j 0}
[t u r] [] {:char r, :i 3, :j 0}
(0   -  0
0   -  1
1   -  1
[t u a t] [] {:char t, :i 1, :j 1}
2   -  0
2   -  1
(0   -  0
0   -  1
0   -  2
[t u a t a] [] {:char a, :i 1, :j 0}
[t u a t e] [] {:char e, :i 2, :j 0}
(0   -  0
0   -  1
1   -  1
2   -  0
2   -  1
) 1   -  0
1   -  2
2   -  0
2   -  1
2   -  2
(1   -  0
1   -  1
2   -  1
3   -  0
3   -  1
))) 1   -  1
1   -  3
[t u t] [] {:char t, :i 1, :j 1}
(2   -  0
2   -  1
[t u r b] [] {:char b, :i 0, :j 2}
3   -  1
[t u r f] [] {:char f, :i 1, :j 3}
(0   -  1
0   -  3
[t u r b a] [] {:char a, :i 1, :j 0}
1   -  1
1   -  2
1   -  3
(0   -  0
0   -  1
1   -  1
2   -  0
2   -  1
)) 4   -  0
4   -  1
[t u r f] [] {:char f, :i 0, :j 4}
(0   -  2
0   -  3
0   -  4
[t u r f e] [] {:char e, :i 2, :j 0}
[t u r f e] [] {:char e, :i 4, :j 0}
(1   -  0
1   -  1
2   -  1
3   -  0
3   -  1
) 1   -  2
1   -  4
2   -  2
2   -  3
2   -  4
[t u r f e] [] {:char e, :i 4, :j 2}
(3   -  0
3   -  1
4   -  1
5   -  0
5   -  1
) (3   -  1
3   -  2
3   -  3
4   -  1
4   -  3
5   -  1
5   -  2
5   -  3
)) [t u r q] [] {:char q, :i 1, :j 4}
(0   -  3
0   -  5
1   -  3
1   -  4
1   -  5
[\t \u \r \f \y]) (0   -  3
0   -  4
0   -  5
1   -  3
1   -  5
2   -  3
2   -  4
2   -  5
)) [t u y] [] {:char y, :i 3, :j 1}
(0   -  0
0   -  1
0   -  2
[t u t m] [] {:char m, :i 0, :j 0}
[t u t a] [] {:char a, :i 1, :j 0}
(0   -  1
[t u t m a] [] {:char a, :i 1, :j 0}
1   -  0
1   -  1
(0   -  0
0   -  1
1   -  1
2   -  0
2   -  1
)) [t u t e] [] {:char e, :i 2, :j 0}
(0   -  0
0   -  1
1   -  1
2   -  0
2   -  1
) 1   -  0
1   -  2
[t u t s] [] {:char s, :i 0, :j 1}
(1   -  0
1   -  1
2   -  1
3   -  0
3   -  1
) 2   -  0
2   -  1
2   -  2
[t u t u] [] {:char u, :i 1, :j 2}
(0   -  0
0   -  2
[t u t s e] [] {:char e, :i 2, :j 0}
1   -  0
1   -  1
1   -  2
(1   -  0
1   -  1
2   -  1
3   -  0
3   -  1
[\t \u \t \s \e \s])) (0   -  1
0   -  2
0   -  3
[t u t u e] [] {:char e, :i 2, :j 0}
1   -  1
1   -  3
2   -  1
2   -  2
2   -  3
(1   -  0
1   -  1
2   -  1
3   -  0
3   -  1
))) 2   -  1
2   -  2
2   -  3
[t u p] [] {:char p, :i 2, :j 2}
(2   -  0
2   -  1
2   -  2
3   -  0
3   -  2
4   -  0
4   -  1
4   -  2
) (1   -  1
1   -  2
1   -  3
2   -  1
2   -  3
[t u p u] [] {:char u, :i 1, :j 2}
3   -  1
3   -  2
3   -  3
(0   -  1
0   -  2
0   -  3
1   -  1
1   -  3
2   -  1
2   -  2
2   -  3
)))))(take 3 (search board))









