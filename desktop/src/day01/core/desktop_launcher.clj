(ns day01.core.desktop-launcher
  (:require [day01.core :refer :all])
  (:import [com.badlogic.gdx.backends.lwjgl LwjglApplication]
           [org.lwjgl.input Keyboard])
  (:gen-class))

(defn -main
  []
  (LwjglApplication. day01 "day01" 800 600)
  (Keyboard/enableRepeatEvents true))
