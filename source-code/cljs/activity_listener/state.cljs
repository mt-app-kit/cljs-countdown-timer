
(ns countdown-timer.state
    (:require [reagent.core :refer [atom] :rename {atom ratom}]))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

; @ignore
;
; @description
; ...
;
; @atom (map)
; {:my-countdown (ms)}
(defonce COUNTDOWNS (ratom {}))
