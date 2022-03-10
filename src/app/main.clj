(ns app.main
  (:require [app.routes :as routes]
            [aleph.http :as http]
            [reitit.coercion.spec]))


(defonce s (atom nil))

(defn start
  []
  (if @s
    (println "Server already started on port 8080")
    (swap! s (fn [_]
               (println "Starting server on port 8080")
               (http/start-server routes/handler {:port 8080})))))
(defn stop
  []
  (when @s
    (println "shutting down server")
    (.close @s)
    (swap! s (fn [_] nil))))

(defn reset
  []
  (stop)
  (start))

(defn -main [& args]
  (let [#_#_mode (keyword (first args))
        args (rest args)]
    (start)))

