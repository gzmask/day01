(ns day01.main-screen
  (:require
   [day01.char-keyboard :as k]
   [day01.physics :as p]
   [play-clj.core :refer :all]
   [play-clj.ui :refer :all]
   [play-clj.g2d :refer :all]))

(defn place-char [w h]
  (assoc (texture "char.gif")
    :x (- (/ (game :width) 2) (/ w 2))
    :oldx (- (/ (game :width) 2) (/ w 2))
    :y (- (/ (game :height) 2) (/ h 2))
    :oldy (- (/ (game :height) 2) (/ h 2))
    :width w
    :height h
    :guid :char))

(defn place-bg [w h]
  (assoc (texture "walltilemap.jpg")
    :x 0
    :oldx 0
    :y 0
    :oldy 0
    :width w
    :height h
    :guid :bg))

(defscreen main-screen
  :on-show
  (fn [screen entities]
    (update! screen
             :camera (orthographic)
             :renderer (stage))
    [(place-bg (game :width) (game :height))
     (place-char 50 76)])

  :on-render
  (fn [screen entities]
    (clear!)
    (->> entities
         k/key-control
         p/physics
         (render! screen)))

  :on-key-down
  (fn [screen entities]
    (cond
     (= (:key screen) (key-code :escape)) (System/exit 0)))

  :on-resize
  (fn [screen entities]
    (height! screen 600))
  )

;repls
(comment
(use 'play-clj.repl)
(e main-screen)
(e! identity main-screen :x 50 :y 200)
)
