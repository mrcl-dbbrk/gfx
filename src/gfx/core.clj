; Copyright 2022 mrcl dbbrk
; SPDX-License-Identifier: Apache-2.0

(ns gfx.core
  (:gen-class)
  (:require [clojure.core.match :refer [match]]
            [gfx.mtx :as mtx]
            )
  )

(defn frustum
  [l r t b n f]
  (let [A (/ (+ r l) (- r l))
        B (/ (+ t b) (- t b))
        C (/ (+ f n) (- n f))
        D (/ (* 2 f n) (- n f))
        E (/ (* 2 n) (- l r))
        F (/ (* 2 n) (- t b))]

  [[ E  0  0  0 ]
   [ 0  F  0  0 ]
   [ A  B  C -1 ]
   [ 0  0  D  0 ]]))

(defn transform
  [& ts]
  (apply mtx/mul
    
    [[ 1 0 0 0 ]
     [ 0 1 0 0 ]
     [ 0 0 1 0 ]
     [ 0 0 0 1 ]]
    
    (for [t ts]
      (match t
        [:s x y z]
        [[ x 0 0 0 ]
         [ 0 y 0 0 ]
         [ 0 0 z 0 ]
         [ 0 0 0 1 ]]

        [:s s]
        [[ s 0 0 0 ]
         [ 0 s 0 0 ]
         [ 0 0 s 0 ]
         [ 0 0 0 1 ]]

        [:t x y z]
        [[ 1 0 0 0 ]
         [ 0 1 0 0 ]
         [ 0 0 1 0 ]
         [ x y z 1 ]]

        [:rx a]
        (let [s (Math/sin a), c (Math/cos a)]
          [[ 1   0   0   0 ]
           [ 0   c   s   0 ]
           [ 0 (- s) c   0 ]
           [ 0   0   0   1 ]])

        [:ry a]
        (let [s (Math/sin a), c (Math/cos a)]
          [[ c   0 (- s) 0  ]
           [ 0   1   0   0  ]
           [ s   0   c   0  ]
           [ 0   0   0   1  ]])

        [:rz a]
        (let [s (Math/sin a), c (Math/cos a)]
          [[ c (- s) 0   0 ]
           [ s   c   0   0 ]
           [ 0   0   1   0 ]
           [ 0   0   0   1 ]])
        )
  )))
