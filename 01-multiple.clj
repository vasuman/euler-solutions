(defn sum_multiple [x]
  (reduce + 
          (filter #(zero? (* (mod %1 3) (mod %1 5))) 
                  (range x))))
(println (sum_multiple 1000))
