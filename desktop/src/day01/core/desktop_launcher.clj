(ns day01.core.desktop-launcher
  (:require [day01.core :refer :all])
  (:import [com.badlogic.gdx.backends.lwjgl LwjglApplication]
           [org.lwjgl.input Keyboard])
  (:gen-class))

(defn -main
  []
  (LwjglApplication. day01 "day01" screen-width screen-height)
  (Keyboard/enableRepeatEvents true))

;(-main)
