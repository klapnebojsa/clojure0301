(ns clojure0301.core)

;VECTOR
(def v [1 2 3])     ;definisemo vector
;(conj v 4)         ; na vector dodajemo clan 4
;[1 2 3 4]

;(conj v 5 6)       ;na vektor dodajemo clanove 5 i 6. vekto v je i dalje [1 2 3] 
;[1 2 3 5 6]

;(seq v)            ;vektor v je i dalje (1 2 3)
;(1 2 3)

;MAPS
(def m {:a 5 :b 6})
;(conj m [:c 7])          ;dodajemo clan [:c 7]
;{:c 7, :b 6, :a 5}

;(seq m)                  ;map m je i dalje ([:a 5] [:b 6])
;([:b 6] [:a 5]) 

;SETS
(def s #{1 2 3})
;(conj s 10)
;#{1 3 2 10}

;(conj s 3 4)
;#{1 4 3 2}

;(seq s)
;(1 3 2)

;LISTS
(def l '(1 2 3))
;(conj l 15 17)
;(17 15 1 2 3)

;(seq l)
;(1 2 3)

;(into v [4 5])
;[1 2 3 4 5]
;(into m [[:c 7] [:d 8]])
;{:c 7, :b 6, :d 8, :a 5}
;(into [1] {:a 1 :b 2})
;[1 [:b 2] [:a 1]]


;(into '(1 2 3) [:a :b :c])
;(:c :b :a 1 2 3)

(defn swap-pairs
  [sequential]
  (into (empty sequential)
        (interleave           ;kombinuje clanove dva niza (prvo uzima clan iz drugog pa iz prvog niza i umrce ih na pocetak list-e)
                              ;kod vektora isto to samo drugim redosledom
          (take-nth 2 (drop 1 sequential))
          (take-nth 2 sequential))))
;(swap-pairs (apply list (range 10)))
;(8 9 6 7 4 5 2 3 0 1)

;swap-pairs (apply vector (range 10)))
;[1 0 3 2 5 4 7 6 9 8]

(take-nth 2 (drop 1 (apply list (range 50))))           ;uzima svaki drugi clan niza pocev od drugog clana
;(1 3 5 7 9 11 13 15 17 19 21 23 25 27 29 31 33 35 37 39 41 43 45 47 49)
(take-nth 2 (apply list (range 50)))                    ;uzima svaki drugi clan niza pocev od prvog calna
;(0 2 4 6 8 10 12 14 16 18 20 22 24 26 28 30 32 34 36 38 40 42 44 46 48)
;(swap-pairs (apply list (range 50)))
;(48 49 46 47 44 45 42 43 40 41 38 39 36 37 34 35 32 33 30 31 28 29 26 27 24 25 22 23 20 21 18 19 16 17 14 15 12 13 10 11 8 9 6 7 4 5 2 3 0 1)

(defn map-map
  [f m]
  (into (empty m)
        (for [[k v] m]
          [k (f v)])))

;(map-map inc (hash-map :z 5 :c 6 :a 0))
;{:z 6, :c 7, :a 1}
;(map-map inc (sorted-map :z 5 :c 6 :a 0))
;{:a 1, :c 7, :z 6}

;(count [1 2 3])
;(count {:a 1 :b 2 :c 3})
;(count #{1 2 3})
;(count '(1 2 3))
;3


;SEQUENCES
;(seq "Clojure")
;(\C \l \o \j \u \r \e)

;(seq {:a 5 :b 6})
;([:b 6] [:a 5])

;(seq (java.util.ArrayList. (range 5)))
;(0 1 2 3 4)

;(seq (into-array ["Clojure" "Programming"]))
;("Clojure" "Programming")

;(seq [])
;nil
;(seq nil)
;nil

;(map str "Clojure")
;("C" "l" "o" "j" "u" "r" "e")

;(set "Programming")
;#{\a \g \i \m \n \o \P \r}

;(first "Clojure")
;\C

;(rest "Clojure")
;(\l \o \j \u \r \e)

;(next "Clojure")
;(\l \o \j \u \r \e)

#_(doseq [x (range 3)]
   (println x))
;0
;1
;2

#_(let [r (range 3)
        rst (rest r)]
    (prn (map str rst))                          ;("1" "2")
    (prn (map #(+ 100 %) r))                     ;(100 101 102)
    (prn (conj r -1) (conj rst 42)))             ;(-1 0 1 2) (42 1 2)

#_(let [s (range 1e6)]
   (time (count s)))
;"Elapsed time: 97.708277 msecs"
;1000000

#_(let [s (apply list (range 1e6))]
   (time (count s)))
;"Elapsed time: 0.020922 msecs"
;1000000

;(cons 0 (range 1 5))
;(0 1 2 3 4)

;(cons 0 (cons 1 (cons 2 (cons 3 (range 4 10)))))
;(0 1 2 3 4 5 6 7 8 9)

;(list* 0 1 2 3 (range 4 10))
;(0 1 2 3 4 5 6 7 8 9)

;(lazy-seq 0 1 2 3 (range 4 10))
;(4 5 6 7 8 9)

;(lazy-seq [1 2 3])
;(1 2 3)

(defn random-ints
  "Returns a lazy seq of random integers in the range [0,limit)."
  [limit]
  (lazy-seq
    (cons (rand-int limit)
          (random-ints limit))))

;(take 10 (random-ints 50))
;(15 36 5 34 8 44 9 18 42 29)

(defn random-ints
  [limit]
  (lazy-seq
    (println "realizing random number")
    (cons (rand-int limit)
          (random-ints limit))))

(def rands (take 10 (random-ints 50)))

;(first rands)
#_(realizing random number
45)

;(nth rands 3)
#_(realizing random number
realizing random number
realizing random number
realizing random number
21)

;(count rands)
#_(realizing random number
realizing random number
realizing random number
realizing random number
realizing random number
realizing random number
realizing random number
realizing random number
realizing random number
realizing random number
10
)

;(count rands)      ;svaki sledeci put samo iskesira podatke koje je dobio prvi put
;10

(defn positive-numbers 
	([] (positive-numbers 1))
	([n] (cons n (lazy-seq (positive-numbers (inc n))))))
;(take 15 (positive-numbers))
;(1 2 3 4 5 6 7 8 9 10 11 12 13 14 15)
(defn positive-numbers11
	([] (positive-numbers 1))
	([n] (cons n (conj (positive-numbers (inc n))))))
;(take 15 (positive-numbers11))
;(1 2 3 4 5 6 7 8 9 10 11 12 13 14 15)

(defn fib [a b] 
  (cons a (lazy-seq (fib b (+ b a)))))
;(take 9 (fib 1 1))
;(1 1 2 3 5 8 13 21 34)

(defn sum-last-2 
   ([] (sum-last-2 1 2))                                  ;ako je funkcija bez argumenata poziva samu sebe sa argumentima 1 i 2
   ([n m] (println n m)                                   ;ako je funkcija pozvana sa dva argumenta
          (cons n                                        
                (lazy-seq 
                  (sum-last-2 m (+ n m))))))         ;onda sabira ta dva broja i dodaje zbir na kraj vektora
;(take 9 (sum-last-2))
#_(
1 2
2 3
3 5
5 8
8 13
13 21
21 34
34 55
55 89
(1 2 3 5 8 13 21 34 55)
)

(defn fib11 [a b] 
  (cons a (conj (fib b (+ b a)))))
;(take 9 (fib11 1 1))

(lazy-seq (range 1 4)(range 1 10))
;(1 2 3 4 5 6 7 8 9)
(lazy-seq (range 1 10) (range 1 4))
;(1 2 3)
(cons (range 0 5) (range 0 2))
;((0 1 2 3 4) 0 1)
(conj (range 0 5) (range 0 2))
;((0 1) 0 1 2 3 4)
(conj (range 0 2) 7 5 6)
;(6 5 7 0 1)
(conj [7 5 6] [1 2])
;[7 5 6 [1 2]]

(repeatedly 10 (partial rand-int 50))
;(44 3 19 27 41 44 29 31 45 0)

#_(let [x31 (repeatedly 10 (partial rand-int 50))]
  x31)

(def x31 (repeatedly 10 (partial rand-int 50)))
;(seq x31)

(apply str (remove (set "aeiouy.")
                   "vowels are useless! or maybe not..."))
;"vwls r slss! r mb nt"

(split-with neg? (range -5 5))         ;daje dva vektora
;[(-5 -4 -3 -2 -1) (0 1 2 3 4)]

#_(let [[t d] (split-with #(< % 12) (range 200000))]
   [(count d) (count t)])
;[1999988 12]
 
(let [[t d] (split-with #(< % 12) (range 200000))]
  [(count t) (count d)])





















