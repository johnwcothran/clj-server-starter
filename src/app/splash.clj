(ns app.splash
  (:require [clojure.string]))

(defn isogram?
  [s]
  (let [split-str (clojure.string/split s #"")
        uniq-char (reduce (fn
                            [v s]
                            (if
                             (some #(= s %) v)
                              v
                              (conj v s))) [] split-str)]
    (loop [idx 0
           checked-els []]
      
      (let [el (nth uniq-char idx)
            count-in-arr (->> split-str
                              (filter #(= el %))
                              count)]
        (println idx)
        #_(println el count-in-arr (> count-in-arr 0))
        (println checked-els)
        (cond
          (= idx
             (-> split-str
                 count
                 (- 1))) true
          (> count-in-arr 0)
          (recur (inc idx)
                 (conj checked-els el))
          :else false)))))

(comment
  (isogram? "cattt"))

(defn isogram-correct?
  [s]
  (let [split-str (-> s
                      (clojure.string/split #"")
                      sort)]
    (loop [idx 0
           current-s nil]
     (let [el (nth split-str idx)]
       (cond
         (= current-s el) false
         (< (+ 1 idx) (count split-str))
         (recur (inc idx)
                el)
         :else true)))))

(comment
  (isogram-correct? "c\\"))
