(ns user
  (:require [app.main :as main]))


(defn go
  []
  (main/start))

(defn reset
  []
  (main/reset))