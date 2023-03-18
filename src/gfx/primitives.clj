; Copyright 2022 mrcl dbbrk
; SPDX-License-Identifier: Apache-2.0

(ns gfx.primitives
  (:gen-class)
  (:require [gfx.mtx :as mtx]))

(defn box
  [& transforms]
  (let [transform
         (apply mtx/mul transforms)
        data {
          :vertices [
            [ 0.5  0.5  0.5  1.0 ]
            [ 0.5  0.5 -0.5  1.0 ]
            [ 0.5 -0.5  0.5  1.0 ]
            [ 0.5 -0.5 -0.5  1.0 ]
            [-0.5  0.5  0.5  1.0 ]
            [-0.5  0.5 -0.5  1.0 ]
            [-0.5 -0.5  0.5  1.0 ]
            [-0.5 -0.5 -0.5  1.0 ] ]
          :mesh [
            [0 1] [0 2] [0 4]
            [3 1] [3 2] [3 7]
            [5 1] [5 4] [5 7]
            [6 2] [6 4] [6 7]

            [0 3] [0 5] [3 6] [5 6] [1 7] [2 4] ] }
         ]
    (assoc data :vertices
      (mtx/mul transform (:vertices data)))
  ))

(defn pyramid
  [& transforms]
  (let [transform
         (apply mtx/mul transforms)
        data {
          :vertices [
            [ 0.0  0.0  0.25 1.0 ]
            [ 0.0  0.5 -0.25 1.0 ]
            [ 0.0 -0.5 -0.25 1.0 ]
            [ 0.5  0.0 -0.25 1.0 ]
            [-0.5  0.0 -0.25 1.0 ] ]
          :mesh [
            [0 1] [0 2] [0 3] [0 4]
            [1 3] [3 2] [2 4] [4 1] ] }
         ]
    (assoc data :vertices
      (mtx/mul transform (:vertices data)))
  ))
