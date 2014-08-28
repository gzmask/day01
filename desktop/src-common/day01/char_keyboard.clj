(ns day01.char-keyboard
  (:require [play-clj.core :refer :all]
            [play-clj.g2d :refer :all]))

(def screen-width 800)
(def screen-height 600)
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
