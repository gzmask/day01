(ns day01.char-keyboard
  (:require [play-clj.core :refer :all]
            [play-clj.g2d :refer :all]))

(def speed {:x 3 :y 3})

(defn key-up [entities]
  (map (fn [entity]
         (if (and (not (:collide? entity)) (= :char (:guid entity)))
           (assoc entity
             :oldy (:y entity)
             :y (+ (:y entity) (:y speed)))
         entity))
       entities)
  )

(defn key-down [entities]
  (map (fn [entity]
         (if (and (not (:collide? entity)) (= :char (:guid entity)))
           (assoc entity
             :oldy (:y entity)
             :y (- (:y entity) (:y speed)))
           entity))
       entities)
  )

(defn key-left [entities]
  (map (fn [entity]
         (if (and (not (:collide? entity)) (= :char (:guid entity)))
           (assoc entity
             :oldx (:x entity)
             :x (- (:x entity) (:x speed)))
           entity))
       entities)
  )

(defn key-right [entities]
  (map (fn [entity]
         (if (and (not (:collide? entity)) (= :char (:guid entity)))
           (assoc entity
             :oldx (:x entity)
             :x (+ (:x entity) (:x speed)))
           entity))
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
