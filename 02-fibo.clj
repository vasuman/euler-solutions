(defn fib [m]
  (loop [p 1 n 2 acc 0]
      (if (= acc m) n
        (recur n (+ p n) (inc acc)))))

(println (reduce +
          (filter even? 
            (take-while #(< % 4000000) 
              (map fib (range))))))
