(ns day01.main-screen
  (:import (javax.imageIO))
  (:require
   [play-clj.core :refer :all]
   [play-clj.ui :refer :all]
   [play-clj.g2d :refer :all]))

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

(defscreen main-screen
  :on-show
  (fn [screen entities]
    (update! screen :renderer (stage))
    (label "Hello world!" (color :white)))

  :on-render
  (fn [screen entities]
    (clear!)
    (render! screen entities)))
