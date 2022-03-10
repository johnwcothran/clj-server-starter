(ns app.infix)

;; (defn infix->prefix
;;   [[exp1 operator exp2] :as c]
;;   (let []
;;     (if (or
;;        (coll? exp1)
;;        (coll? exp2))
;;     )
;;     [operator exp1 exp2])
  
  
;;   #_(reduce (fn [v e]
;;             (if (or (= e :and)
;;                     (= e :or))
;;               )) [] c))

;; (comment 
;;   (infix->prefix
;;    #_[:a :and :b] ;; [:and :a :b]
;;    [:a :and [:b :or :c]]))