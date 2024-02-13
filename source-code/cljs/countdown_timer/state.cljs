
(ns countdown-timer.state
    (:require [reagent.core :as reagent]))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

; @ignore
;
; @description
; ...
;
; @atom (map)
; {:my-countdown (ms)}
(defonce COUNTDOWNS (reagent/atom {}))
