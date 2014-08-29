(ns day01.main-screen
  (:import (javax.imageIO))
  (:require
   [day01.char-keyboard :as charkey]
   [play-clj.core :refer :all]
   [play-clj.ui :refer :all]
   [play-clj.g2d :refer :all]))

(def screen-width 800)
(def screen-height 600)

(comment
  (def img (javax.imageio.ImageIO/read  (java.io.File. "resources/day01-logo.jpg")))
  (use 'clojure.pprint)
  (use 'clojure.java.javadoc)
  (pprint (.getRGB img 12 26))
  (pprint (.getHeight img))
  (pprint (.getWidth img))
  (javadoc (.getData img))
  (javadoc img)
  (doc for)
  (range 50)
  (make-2d-list "resources/day01-logo.jpg"))


(defn make-2d-list [file-str]
  "this function shows how easy to get pixel value"
  (let [img (javax.imageio.ImageIO/read  (java.io.File. file-str))
        h (.getHeight img)
        w (.getWidth img)]
    (for [x (range h)]
      (for [y (range w)]
        (.getRGB img x y)))))

(defn place-char [w h]
  (assoc (texture "char.gif")
   :x (- (/ screen-width 2) (/ w 2))
   :y (- (/ screen-height 2) (/ h 2))
   :width w
   :height h
   :guid :char))

(defscreen main-screen
  :on-show
  (fn [screen entities]
    (update! screen :renderer (stage))
    (place-char 50 76))

  :on-render
  (fn [screen entities]
    (clear!)
    (render! screen entities))

  :on-key-down
  (fn [screen entities]
    (cond
     (= (:key screen) (key-code :dpad-up))
     (charkey/key-up entities)
     (= (:key screen) (key-code :dpad-down))
     (charkey/key-down entities)
     (= (:key screen) (key-code :dpad-left))
     (charkey/key-left entities)
     (= (:key screen) (key-code :dpad-right))
     (charkey/key-right entities)
     (= (:key screen) (key-code :escape))
     (System/exit 0)
     )
    )
  )
