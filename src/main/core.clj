; Copyright 2022 mrcl dbbrk
; SPDX-License-Identifier: Apache-2.0

(ns main.core
  (:gen-class)
  (:require [clojure2d.core :refer :all]
            [clojure2d.pixels :refer :all]
            [gfx.core :as gfx]
            [gfx.mtx :as mtx]
            [gfx.primitives :refer :all]
            )
  )

(def w 400)
(def h 400)
(def pxls (pixels w h))

(defn persp-div [vertices]
  (mapv (fn [[x y z w]] [(/ x w) (/ y w) (/ z w) 1]) vertices))

(defn viewport-transform
  [vertices]
  (let [vertices (persp-div vertices)]
    (vec (for [[x y] vertices] [(* 0.5 (inc x) w), (* 0.5 (inc y) h)])))
  )

(defn draw-vertices
  [cv {verts :vertices}]
  (doseq [[x y] (viewport-transform verts)]
    (rect cv (dec x) (dec y) 3 3))
  )

(defn draw-mesh
  [cv {:keys [vertices mesh]}]
  (let [viewport-verts (viewport-transform vertices)]
    (doseq [[v0 v1] mesh]
      (let [[x0 y0] (viewport-verts v0)
            [x1 y1] (viewport-verts v1)]
        (line cv x0 y0 x1 y1)))
  ))

(defn transforms
  [frm]
  (mtx/mul
    (gfx/frustum
      -1.0 1.0 1.0 -1.0 1.0 10.0)
    (gfx/transform
      [:t  0.0 0.0 -0.3]
      [:ry (* 0.02 frm)]
      [:s  0.33]
      [:rx (/ Math/PI 2)])
  ))

(defn draw
  [cv win frm state]
  (set-canvas-pixels! cv pxls)
  (set-color cv 0xff000000)
  (let [scene (pyramid (transforms frm))]
    (draw-mesh cv scene)
    (draw-vertices cv scene ))
  state)

(defn -main
  [& args]
  (let [cnv (canvas w h :low)
        win (show-window cnv "TODO: Proper Title" draw)]
    )
  )
