
(ns countdown-timer.side-effects
    (:require [countdown-timer.state :as state]
              [countdown-timer.env :as env]
              [time.api :as time]))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

(defn count-down!
  ; @ignore
  ;
  ; @param (keyword) countdown-id
  ; @param (map) countdown-props
  ; {:on-end-f (function)(opt)
  ;  :on-start-f (function)(opt)
  ;  :on-step-f (function)(opt)
  ;  :step (ms)(opt)
  ;  :timeout (ms)(opt)}
  [countdown-id {:keys [on-end-f on-start-f on-step-f step timeout] :or {step 1000 timeout 60000} :as countdown-props}]
  (let [time-left (env/time-left countdown-id)]
       (cond (-> time-left nil?)  (if on-start-f (on-start-f))
             (-> time-left zero?) (if on-end-f   (on-end-f))
             (-> time-left neg?)  (if on-end-f   (on-end-f)))
       (cond (-> time-left nil?)  (time/set-timeout! #(count-down! countdown-id countdown-props) 1000)
             (-> time-left pos?)  (time/set-timeout! #(count-down! countdown-id countdown-props) 1000))
       (cond (-> time-left nil?)  (swap! state/COUNTDOWNS assoc  countdown-id timeout)
             (-> time-left zero?) (swap! state/COUNTDOWNS assoc  countdown-id nil)
             (-> time-left neg?)  (swap! state/COUNTDOWNS assoc  countdown-id nil)
             :step                (swap! state/COUNTDOWNS update countdown-id - step))))

(defn start-countdown!
  ; @description
  ; Starts a countdown timer.
  ;
  ; @param (keyword) countdown-id
  ; @param (map) countdown-props
  ; {:on-end-f (function)(opt)
  ;  :on-start-f (function)(opt)
  ;  :on-step-f (function)(opt)
  ;  :step (ms)(opt)
  ;   Default: 1000
  ;  :timeout (ms)(opt)
  ;   Default: 60000}
  ;
  ; @usage
  ; (start-countdown! :my-countdown
  ;                   {:on-end-f #(println "1 min. elapsed") :step 1000 :timeout 60000})
  ;
  ; @usage
  ; (defn my-function [] (let [time-left (time-left :my-countdown)]
  ;                           (println time-left "ms left from countdown"))
  ; (start-countdown! :my-countdown
  ;                   {:on-step-f my-function :step 1000 :timeout 60000})
  [countdown-id countdown-props]
  (let [time-left (env/time-left countdown-id)]
       (if-not time-left (count-down! countdown-id countdown-props))))
