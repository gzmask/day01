(ns day01.physics)

(comment
  (def img (javax.imageio.ImageIO/read  (java.io.File. "resources/walltilemap.bmp")))
  (use 'clojure.repl)
  (use 'clojure.pprint)
  (use 'clojure.java.javadoc)
  (pprint (.getRGB img 60 0))
  (pprint (.getHeight img))
  (pprint (.getWidth img))
  (javadoc (.getData img))
  (javadoc img)
  (doc for)
  (range 50)
  (make-2d-list "resources/walltilemap.bmp")
)

(defn make-2d-list [file-str]
  "this function shows how easy to get pixel value"
  (let [img (javax.imageio.ImageIO/read  (java.io.File. file-str))
        h (.getHeight img)
        w (.getWidth img)]
    (for [y (range h)]
      (for [x (range w)]
        (cond
         (= (.getRGB img x y) -1) false
         (= (.getRGB img x y) -16777216) true)))))
