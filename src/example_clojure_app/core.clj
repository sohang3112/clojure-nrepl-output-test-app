(ns example-clojure-app.core
  (:gen-class))

(defonce !state (atom {:word "Hello!"
                       :sleep-ms 2000}))

(defn log! [& xs]
  (apply (partial println (java.sql.Timestamp. (System/currentTimeMillis))) xs))

(defn print-heartbeat! []
  (log! "Out of band:" @!state)
  (Thread/sleep (:sleep-ms @!state)))

(defn -main
  "Spawn of a future printing heart beats."
  [& _args]
  (future
    (while (not= :quit (:word @!state))
      (print-heartbeat!))
    (log! "Quitting:" @!state)))

(comment
  (def f (-main))
  (do (log! "In band:" @!state) {:evaluation-results @!state})
  (swap! !state assoc :word :quit)
  (swap! !state assoc :word "Go!")
  (swap! !state assoc :sleep-ms 500)
  (swap! !state assoc :sleep-ms 4000)
  (swap! !state assoc :word "foo bar baz!")
  @f ; weird stuff happens when evaluating this one
  )