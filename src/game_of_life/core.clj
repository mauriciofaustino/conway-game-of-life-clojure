(ns game-of-life.core
  (:gen-class))

(def rows 3)
(def cols 3)

(def neighbors-rel-pos [[-1 -1] [-1  0] [-1  1]
                        [ 0 -1]         [ 0  1]
                        [ 1 -1] [ 1  0] [ 1  1]])

(defn- valid-position? [[row col]]
  (and (> row -1)
       (> col -1)
       (< row cols)
       (< col rows)))

(defn- abs-positions [row col]
  (filter valid-position?
    (map (fn [[rel-row rel-col]] [(+ rel-row row) (+ rel-col col)]) neighbors-rel-pos)))

(defn- get-neighbors [generation row col]
  (map (fn [[abs-row abs-col]] ((generation abs-row) abs-col)) (abs-positions row col)))

(defn lives? [generation row col]
  (let [cell ((generation row) col)
        neighbors (get-neighbors generation row col)
        neighbors-alive (count (filter true? neighbors))]
    (or
     (and cell
          (> neighbors-alive 1)
          (< neighbors-alive 4))
     (= neighbors-alive 3))))
