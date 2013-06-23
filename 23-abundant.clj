(defn is-divisible? [x a]
  (zero? (mod x a)))

(defn get-factors [x]
  (loop [a (int (Math/floor (Math/sqrt x)))]
    (cond 
      (is-divisible? x a) [a (/ x a)]
      :else (recur (dec a)))))

(defn gp-sum [x n]
  (reduce + (take (inc n) (iterate #(* x %) 1))))

(defn is-abundant? [n factors]
  (let [freq-fact (frequencies factors)]
    (< (* 2 n) (reduce * (map #(gp-sum % (freq-fact %)) (keys freq-fact))))))

(defn get-prime-factors [factor-map n]
  (reduce into 
    (map #(get factor-map % [%]) (get-factors n))))

(defn gen-abundant [limit]
  (loop [factor-map {1 [] } n 2 abundant []]
    (if (<= limit n) abundant
      (let [factors (get-prime-factors factor-map n)
            nxt-abundant (if (is-abundant? n factors)
                           (conj abundant n) abundant)]
        (recur (assoc factor-map n factors) (inc n) nxt-abundant)))))

(let [abundant-nums (set (gen-abundant 28123))] 
  (println 
    (reduce + 
      (filter 
        ; In case future-me is confused:
        ; Map to make new list by 
        ; subtracting each abundant number by a number in range
        ; Check if any numbers in new list are in abundant sums
        (complement (fn [x] (some abundant-nums (map #(- x %) abundant-nums)))) 
          (range 28123)))))
