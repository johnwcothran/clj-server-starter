(ns app.hackerrank
  (:require [clojure.string]))


; Complete the 'miniMaxSum' function below.
;
; The function accepts INTEGER_ARRAY arr as parameter.
;

(defn miniMaxSum [arr]
  (loop [idx 0
         min-acc ##Inf
         max-acc ##-Inf]
    (if (= (count arr) idx)
      (println min-acc max-acc)
      (let [arr-without-idx (concat (subvec arr 0 idx)
                                    (subvec arr (inc idx)))
            subvec-sum (apply + arr-without-idx)]
        (recur
         (inc idx)
         (if (< subvec-sum min-acc)
           subvec-sum
           min-acc)
         (if (> subvec-sum max-acc)
           subvec-sum
           max-acc))))))

(comment
 (miniMaxSum [1 3 5 7 9]))

;
; Complete the 'timeConversion' function below.
;
; The function is expected to return a STRING.
; The function accepts STRING s as parameter.
;

(defn timeConversion [s]
  (let [length (count s)
        time-of-day (subs s (- length 2) length)
        min-sec (subs s 2 (- length 2))
        hour (subs s 0 2)
        military-hour (cond
                        (and
                         (= "PM" time-of-day)
                         (not= "12" hour))
                        (-> hour
                            Integer/parseInt
                            (+ 12))
                        (and (= "AM" time-of-day)
                             (= "12" hour))
                        "00"
                        :else hour)]
    (str military-hour min-sec)))
(comment
  (timeConversion "04:00:00PM"))




;
; Complete the 'lonelyinteger' function below.
;
; The function is expected to return an INTEGER.
; The function accepts INTEGER_ARRAY a as parameter.
;

(defn lonelyinteger [a]
  (loop [i 0]
    (let [el (nth a i)
          c (->> a
                 (filterv #(= % el))
                 count)]
      (if (= 1 c)
        el
        (recur (inc i))))))

(comment
  (lonelyinteger [1 2 3 4 3 2 1]))



;
; Complete the 'diagonalDifference' function below.
;
; The function is expected to return an INTEGER.
; The function accepts 2D_INTEGER_ARRAY arr as parameter.
;

(defn diagonalDifference [arr]
  (let [sq-length (count arr)]
    
    (loop [idx 0
           left-diag []
           right-diag []]
      (println left-diag right-diag)
      (let [right-idx (-> sq-length
                          (- 1)
                          (- idx))
            left (get-in arr [idx idx])
            right (get-in arr [right-idx idx])]
        (if (= (count left-diag)
               sq-length)
          (-> (apply + left-diag)
              (- (apply + right-diag))
              Math/abs)
          (recur (inc idx)
                 (conj left-diag left)
                 (conj right-diag right)))))))

(comment
  (diagonalDifference
   [[1 2 3]
    [4 5 6]
    [9 8 9]]))

(comment
  (diagonalDifference
   [[1 2 3 4]
    [4 5 6 7]
    [9 8 9 8]
    [1 2 3 4]]))




;
; Complete the 'countingSort' function below.
;
; The function is expected to return an INTEGER_ARRAY.
; The function accepts INTEGER_ARRAY arr as parameter.
;

(defn countingSort [arr]
  (let [range ((juxt #(apply min %)
                    #(apply max %))
                    arr)]
    range
   #_(loop [result (->> (range 0 (count arr))
                     (mapv (fn [_] 0)))]
    range)))

(comment
  (countingSort [1 1 3 2 1]))
