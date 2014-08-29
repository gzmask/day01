(ns day01.enter-menu
  (:require [play-clj.core :refer :all]
            [play-clj.g2d :refer :all]))

;screen components for enter menu

(defn place-logo [w h]
  (merge (texture "day01-logo.png")
   {:x (- (/ (game :width) 2) (/ w 2))
    :y (- (/ (game :height) 2) (/ h 2))
    :width w
    :height h
    :guid :logo
    }))

(defn place-end-bn [w h]
  (merge
   (texture "end.png")
   {:x (- (/ (game :width) 2) (/ w 2))
    :y (- (/ (game :height) 2) (/ h 2) 190)
    :width w
    :height h
    :guid :end-bn
    }))

(defn place-start-bn [w h]
  (merge
   (texture "start.png")
   {:x (- (/ (game :width) 2) (/ w 2))
    :y (- (/ (game :height) 2) (/ h 2) 150)
    :width w
    :height h
    :guid :start-bn
    }))

(defn render-start-bn [entities]
  (map
   (fn [entity]
     (if (and (= (:guid entity) :start-bn)
              (:select entity))
       (assoc entity
         :height (* 1.3 (:height entity))
         :width (* 1.3 (:width entity))
         )
       entity)
     ) entities))

(defn render-end-bn [entities]
  (map
   (fn [entity]
     (if (and (= (:guid entity) :end-bn)
              (:select entity))
       (assoc entity
         :height (* 1.3 (:height entity))
         :width (* 1.3 (:width entity))
         )
       entity)
     ) entities))

(defn key-start-bn [entities]
  (map
   (fn [entity]
     (if (= (:guid entity) :start-bn)
       (assoc entity :select true)
       (assoc entity :select false)))
   entities))

(defn key-end-bn [entities]
  (map
   (fn [entity]
     (if (= (:guid entity) :end-bn)
       (assoc entity :select true)
       (assoc entity :select false)))
   entities))

(defn key-enter [entities this-game main-screen]
  (map
   (fn [entity]
     (cond
      (and (= (:guid entity) :start-bn) (:select entity))
      (set-screen! this-game main-screen)
      (and (= (:guid entity) :end-bn) (:select entity))
      (System/exit 0)
      :else entity))
   entities))
