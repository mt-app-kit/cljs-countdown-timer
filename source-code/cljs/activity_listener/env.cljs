
(ns countdown-timer.env
    (:require [countdown-timer.state :as state]))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

(defn time-left
  ; @description
  ; Returns how many MS left until the end of a specific countdown.
  ;
  ; @param (keyword) countdown-id
  ;
  ; @usage
  ; (start-countdown! :my-countdown {...})
  ; (let [time-left (time-left :my-countdown)]
  ;      (println time-left "ms left"))
  ;
  ; @return (ms)
  [countdown-id]
  (-> state/COUNTDOWNS deref countdown-id))
