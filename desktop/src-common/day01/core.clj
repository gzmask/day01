(ns day01.core
  (:require
   [day01.enter-menu :as menu]
   [day01.main-screen :as main]
   [play-clj.core :refer :all]
   [play-clj.ui :refer :all]))

(declare day01)

(defscreen title-screen
  :on-show
  (fn [screen entities]
    (update! screen :renderer (stage))
    [(menu/place-logo 220 220)
     (menu/place-end-bn 150 50)
     (menu/place-start-bn 150 50)
     ])

  :on-render
  (fn [screen original-entities]
    (clear!)
    (let [entities
          (-> original-entities
              menu/render-start-bn
              menu/render-end-bn)]
      (render! screen entities)
      original-entities))

  :on-key-down
  (fn [screen entities]
    (cond
     (= (:key screen) (key-code :dpad-up))
     (menu/key-start-bn entities)
     (= (:key screen) (key-code :dpad-down))
     (menu/key-end-bn entities)
     (= (:key screen) (key-code :enter))
     (menu/key-enter entities day01 main/main-screen)
     (= (:key screen) (key-code :dpad-right))
     (println "right")
     (= (:key screen) (key-code :dpad-left))
     (println "left")))
)

(defgame day01
  :on-create
  (fn [this]
    (set-screen! this title-screen)))

