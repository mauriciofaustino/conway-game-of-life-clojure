(ns game-of-life.core
  (:gen-class))

(def rows 3)
(def cols 3)

(defn- valid-position? [[row col]]
  (and (> row -1)
       (> col -1)
       (< row cols)
       (< col rows)))

(defn- get-neighbors-positions [row col]
  (let [up (dec row)
        left (dec col)
        down (inc row)
        right (inc col)]
    (filter valid-position? [[up left]  [up col]  [up right]
                              [row left]           [row right]
                              [down left][down col][down right]])))

(defn- get-neighbors [generation row col]
  (map (fn [[nrow ncol]] ((generation nrow) ncol))
       (get-neighbors-positions row col)))

(defn lives? [generation row col]
  (let [cell ((generation row) col)
        neighbors (get-neighbors generation row col)
        neighbors-alive (count (filter true? neighbors))]
    (or
 (and cell
          (> neighbors-alive 1)
          (< neighbors-alive 4))
     (= neighbors-alive 3))))
