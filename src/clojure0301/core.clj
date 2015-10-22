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
(into [1] {:a 1 :b 2})





