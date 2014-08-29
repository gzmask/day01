(ns day01.physics
  (:require
   [play-clj.core :refer :all]))

(def collide-map "resources/walltilemap.bmp")

(defn collide-2d-list [file-str]
  "this function shows how easy to get pixel value"
  (let [img (javax.imageio.ImageIO/read  (java.io.File. file-str))
        h (.getHeight img)
        w (.getWidth img)]
    (for [y (range h)]
      (for [x (range w)]
        (cond
         (= (.getRGB img x y) -1) false
         (= (.getRGB img x y) -16777216) true)))))

(defn collide? [entity collide-map]
  (let [img (javax.imageio.ImageIO/read  (java.io.File. collide-map))
        screen-w (game :width)
        screen-h (game :height)
        w (.getWidth img)
        h (.getHeight img)
        tile-w (/ screen-w w)
        tile-h (/ screen-h h)
        filp-y (dec (- screen-h (:y entity)))
        tile-x (Math/floor (/ (:x entity) tile-w))
        tile-y (Math/floor (/ filp-y tile-h))
        pixel (.getRGB img tile-x tile-y)
        result (if (not (= pixel -1)) true false)
        ]
    result))

(defn physics [entities]
  (map (fn [entity]
         (if (collide? entity collide-map)
           (assoc entity
             ;:collide? (collide? entity collide-map)
             :x (:oldx entity)
             :y (:oldy entity)
             )
           entity))
       entities))

;repls
(comment
  (def img (javax.imageio.ImageIO/read  (java.io.File. "resources/walltilemap.bmp")))
  (use 'clojure.repl)
  (use 'clojure.pprint)
  (use 'clojure.java.javadoc)
  (.getRGB img 13 0)
  (pprint (.getHeight img))
  (pprint (.getWidth img))
  (javadoc (.getData img))
  (javadoc img)
  (doc for)
  (range 50)
(collide-2d-list "resources/walltilemap.bmp")
(count (collide-2d-list "resources/walltilemap.bmp"))
(use 'clojure.repl)
(doc mod)
(collide? {:x 10 :y 100} collide-map)
(tcollide? {:x 400 :y 300} collide-map)
(tcollide? {:x 20 :y 200} collide-map)
(tcollide? {:x 30 :y 300} collide-map)
(tcollide? {:x 40 :y 400} collide-map)
(tcollide? {:x 50 :y 500} collide-map)
(tcollide? {:x 50 :y 200} collide-map)
(tcollide? {:x 790 :y 470} collide-map)
(defn tcollide? [entity collide-map]
  (let [img (javax.imageio.ImageIO/read  (java.io.File. collide-map))
        screen-w (game :width)
        screen-h (game :height)
        w (.getWidth img)
        h (.getHeight img)
        tile-w (/ screen-w w)
        tile-h (/ screen-h h)
        filp-y (- (game :height) (:y entity))
        tile-x (Math/floor (/ (:x entity) tile-w))
        tile-y (Math/floor (/ filp-y tile-h))
        pixel (.getRGB img tile-x tile-y)
        result (if (not (= pixel -1)) true false)
        ]
    [result screen-w screen-h w h tile-w tile-h tile-x tile-y filp-y]))
  )
