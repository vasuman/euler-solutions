(defn get-rep-length [t b]
  (loop [numer t denom b frac-set #{}]
    (let [r (rem numer denom)]
      (if (or (zero? r) (frac-set r))
        (count frac-set)
        (recur (* r 10) denom (conj frac-set r))))))
(defn gen-inv-table [n]
  (zipmap (range 1 n) (map #(get-rep-length 1 %) (range 1 n))))
(let [m (gen-inv-table 1000)]
  (println (apply max-key m (keys m))))
