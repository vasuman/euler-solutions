(defn is-leap-year? [x]
  (and (zero? (mod x 4)) (or (not (zero? (mod x 100))) (zero? (mod x 400)))))
(def year [31 28 31 30 31 30 31 31 30 31 30 31])
(def leap-year (into [] (map + year [0 1 0 0 0 0 0 0 0 0 0 0])))
(defn gen-month-list [s-year e-month e-year]
  (loop [y s-year y-list []]
    (let [c-mlist (if (is-leap-year? y) leap-year year)]
      (if (= y e-year)
        (into y-list (subvec c-mlist 0 e-month))
        (recur (inc y) (into y-list c-mlist))))))
; 0 - Monday : 6 - Sunday
(defn make-week-list [start-day month-list]
  (loop [day start-day months month-list weeks []]
    (if (empty? months) weeks
      (recur (mod (+ day (first months)) 7) (rest months) (conj weeks day)))))
(defn get-sundays [start-day s-year e-month e-year]
  (count (filter #(= 6 %) (make-week-list start-day (gen-month-list s-year e-month e-year)))))
; 1 Jan 1901 was a (Tuesday = 1)
(println (get-sundays 1 1901 12 2000))
