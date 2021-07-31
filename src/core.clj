(ns app.core
  (:require [java-time :as t]))

(defn time-str
  []
  (t/format
   (t/with-zone (t/formatter "hh:mm::ss a") (t/zone-id))
   (t/instant)))

(time-str)

(defn print-time-wait []
  (println (time-str))
  (Thread/sleep 1000))

(def f (atom nil))

(defn main []
  (reset! f (future
              (dorun
               (repeatedly print-time-wait))))
  (println "On the code again")
  (future (println "from the future"))
  (+ 1 1))

(main)

(future-cancel @f)

;; (defn main []
;;   (let [f (future
;;             (Thread/sleep 5000)
;;             (println "on the code again"))]
;;     (while (not (future-done? f))
;;       (println "Still busy")
;;       (Thread/sleep 1000)
;;       (future-cancel f)
;;       (println
;;        (future-cancelled? f)))))

