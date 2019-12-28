(ns metrics-server.api.hardware
  (:require [metrics-server.core :refer [call-api check-required-params with-collection-format]])
  (:import (java.io File)))

(defn get-metrics-with-http-info
  "Get hardware metrics"
  []
  (call-api "/hardware" :get
            {:path-params   {}
             :header-params {}
             :query-params  {}
             :form-params   {}
             :content-types []
             :accepts       []
             :auth-names    []}))

(defn get-metrics
  "Get hardware metrics"
  []
  (:data (get-metrics-with-http-info)))

;; Задание 1.1
(defn f_task1 [metrics] (
	filter 
		(fn [a] (> (get a :cpuTemp) 2)) 
	metrics))

;; Задание 1.2
(defn f_task2 [metrics] (/ 
	(reduce + (map (fn [a] (get a :cpuTemp)) metrics)) 
	(count metrics))
	)

;; Задание 1.3
(defn f_task3  [metrics] (/ 
	(reduce + (map (fn [a] (get a :cpuLoad)) metrics)) 
		(count metrics)))

;; Главный метод
(defn -main [& args] 
(println (f_task1 (get-metrics)))
(println (f_task2 (get-metrics))) 
(println (f_task3 (get-metrics)))
)