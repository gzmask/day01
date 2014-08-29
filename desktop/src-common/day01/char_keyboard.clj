(ns day01.char-keyboard
  (:require [play-clj.core :refer :all]
            [play-clj.g2d :refer :all]))

(def speed {:x 10 :y 10})

(defn key-up [entities]
  (map (fn [entity]
         (if (= :char (:guid entity))
           (assoc entity
             :y (+ (:y entity) (:y speed)))))
       entities)
  )

(defn key-down [entities]
  (map (fn [entity]
         (if (= :char (:guid entity))
           (assoc entity
             :y (- (:y entity) (:y speed)))))
       entities)
  )

(defn key-left [entities]
  (map (fn [entity]
         (if (= :char (:guid entity))
           (assoc entity
             :x (- (:x entity) (:x speed)))))
       entities)
  )

(defn key-right [entities]
  (map (fn [entity]
         (if (= :char (:guid entity))
           (assoc entity
             :x (+ (:x entity) (:x speed)))))
       entities)
  )

(defn key-upleft [entities]
  (-> entities
      key-up
      key-left))

(defn key-upright [entities]
  (-> entities
      key-up
      key-right))

(defn key-downright [entities]
  (-> entities
      key-down
      key-right))

(defn key-downleft [entities]
  (-> entities
      key-down
      key-left))

(defn key-control [entities]
  (cond
   (and (key-pressed? :dpad-up) (key-pressed? :dpad-left)) (key-upleft entities)
   (and (key-pressed? :dpad-up) (key-pressed? :dpad-right)) (key-upright entities)
   (and (key-pressed? :dpad-down) (key-pressed? :dpad-left)) (key-downleft entities)
   (and (key-pressed? :dpad-down) (key-pressed? :dpad-right)) (key-downright entities)
   (key-pressed? :dpad-up) (key-up entities)
   (key-pressed? :dpad-down) (key-down entities)
   (key-pressed? :dpad-left) (key-left entities)
   (key-pressed? :dpad-right) (key-right entities)
   :else entities
   ))
