(ns app.core
  (:require [aleph.http :as http]
            [clojure.walk :refer [keywordize-keys]]
            [jsonista.core :as j]
            [manifold.deferred :as md]))

(defonce app-state (atom #{}))

(defn add-item!
  [{:keys [item]}]
  (swap! app-state conj item))

(comment
  (add-item! {:item "Apple"}))

(defn remove-item!
  [{:keys [item]}]
  (swap! app-state disj item))

(comment
  (remove-item! {:item "Apple"}))

(defn list-items
  []
  @app-state)

(comment
  (list-items))

(defn get-some-data
  "Returns the reference to the league."
  []
  (let [res ""]
    res))

