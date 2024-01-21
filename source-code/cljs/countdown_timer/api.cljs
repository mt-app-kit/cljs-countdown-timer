
(ns countdown-timer.api
    (:require [countdown-timer.env          :as env]
              [countdown-timer.side-effects :as side-effects]))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

; @redirect (countdown-timer.env/*)
(def time-left env/time-left)

; @redirect (countdown-timer.side-effects/*)
(def start-countdown! side-effects/start-countdown!)
