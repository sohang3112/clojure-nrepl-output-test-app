(ns example-clojure-app.core
  (:gen-class))

(def word (atom "Hello!"))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (while true
    (println "Outside Eval:" @word)
    (eval (println "Inside Eval"))))
