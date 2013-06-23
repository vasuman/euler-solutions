(defn divisible [a b]
  (zero? (mod a b)))
(defn prime-gen [n] 
  (loop [primes [2] x 3]
    (cond
      (= n (count primes)) 
        (last primes)
      (some (partial divisible x) primes) 
        (recur primes (+ x 2))
      :else
        (recur (conj primes x) (+ x 2)))))
(println (prime-gen 6))
