(ns app.routes
  (:require [app.core :as core]
            [muuntaja.core :as m]
            [reitit.coercion.spec]
            [aleph.http :as http]
            [reitit.ring :as ring]
            [jsonista.core :as j]
            [clojure.walk :refer [keywordize-keys]]
            [reitit.ring.coercion :as rrc]
            [reitit.ring.middleware.muuntaja :as muuntaja]
            [reitit.ring.middleware.parameters :as parameters]))

(defn grocery-handler
  [{:keys [body] :as response}]
  #_(def b (-> response :body slurp))
  (let [result (-> response :body slurp
                    j/read-value
                    keywordize-keys
                    :result)
        action (:action result)
        item (get-in result [:parameters
                             :item])
        actions {"item.add" #(core/add-item! {:item item})
                 "item.remove" #(core/remove-item! {:item item})
                 "item.list" #(core/list-items)}
        action-fn (get actions action)]
    {:status 200
     :body {:items (action-fn)}}))

(defn test-handler
  [response]
  {:status 200
   :body {:test "Hello!"}})



(defn authenticated-route-handler
  [{:keys [headers]
    :as   request}]
  (let [token (get headers "authorization")
        #_#_authorized? (when token (= token "password"))]
    {:status 200
     :body {:response "Authorized!"}}))

(defn wrap-authentication
  [handler]
  (fn [{:keys [headers]
        :as   request}]
    (let [token (get headers "authorization")
          valid? (when token (= token "password"))
          failed-response {:status 401
                           :body   {:response "Authorization failed"}}]
      (cond
        valid?       (handler request)
        (nil? token) (do (println "No authorization token found!") failed-response)
        :else        (do (println "Invalid authorization token found!") failed-response)))))


(def handler
  (ring/ring-handler
   (ring/router
    [["/"  {:post
            {:responses
             {200 {:body
                   {}}}
             :handler grocery-handler}}]
     ["/test"  {:get
                {:responses
                 {200 {:body
                       {}}}
                 :handler test-handler}}]]
      ;; router data affecting all routes
    {:data {:coercion   reitit.coercion.spec/coercion
            :muuntaja   m/instance
            :middleware [parameters/parameters-middleware
                         rrc/coerce-request-middleware
                         muuntaja/format-response-middleware
                         rrc/coerce-response-middleware]}})))
