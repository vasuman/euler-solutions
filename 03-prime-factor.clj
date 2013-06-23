(defn is-integer? [x]
  (= 0.0 (mod x 1)))

(defn is-perfect-square? [x]
  (is-integer? (Math/sqrt x)))

(defn get-factors [x]
  (loop [a (Math/ceil (Math/sqrt x))]
    (let [b (Math/sqrt (- (* a a) x))]
      (cond 
        (is-integer? b) [(- a b) (+ a b)]
        :else (recur (inc a))))))

(defn fermat-sieve [x] 
  (loop [factors [x] prime []]
    (if (empty? factors) prime
      (let [new-factors (get-factors (last factors))
            nxt-factors (pop factors)]
        (cond
          (= 1.0 (first new-factors)) 
            (recur nxt-factors (conj prime (last new-factors)))
          :else
            (recur (into nxt-factors new-factors) prime))))))

(println (apply max (fermat-sieve 600851475143)))
