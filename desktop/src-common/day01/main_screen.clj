(ns day01.main-screen
  (:import (javax.imageIO))
  (:require
   [day01.char-keyboard :as charkey]
   [play-clj.core :refer :all]
   [play-clj.ui :refer :all]
   [play-clj.g2d :refer :all]))

(defn place-char [w h]
  (assoc (texture "char.gif")
   :x (- (/ (game :width) 2) (/ w 2))
   :y (- (/ (game :height) 2) (/ h 2))
   :width w
   :height h
   :guid :char))

(defscreen main-screen
  :on-show
  (fn [screen entities]
    (update! screen
             :camera (orthographic)
             :renderer (stage))
    (place-char 50 76))

  :on-render
  (fn [screen entities]
    (clear!)
    (->> entities
         charkey/key-control
         (render! screen)))

  :on-key-down
  (fn [screen entities]
    (cond
     (= (:key screen) (key-code :escape)) (System/exit 0)))

  :on-resize
  (fn [screen entities]
    (height! screen 600))
  )
