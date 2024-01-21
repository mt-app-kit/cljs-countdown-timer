
(ns countdown-timer.env
    (:require [countdown-timer.state :as state]))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

(defn time-left
  ; @note
  ; The actual state of countdowns are stored in a [Reagent](https://github.com/reagent-project/reagent) atom,
  ; to make Reagent components rerender when the countdown state changes.
  ;
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
