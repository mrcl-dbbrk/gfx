; Copyright 2022 mrcl dbbrk
; SPDX-License-Identifier: Apache-2.0

(ns gfx.mtx
  (:gen-class))

(defn- dot [a b] (apply + (map * a b)))

(defn transpose [m] (apply mapv vector m))

(defn mul [m & ms]
  (let [mtxT*vec #(mapv dot %1 (repeat %2))
        mtx*mtx #(mapv mtxT*vec (repeat (transpose %1)) %2)]
    (reduce mtx*mtx m ms)))
