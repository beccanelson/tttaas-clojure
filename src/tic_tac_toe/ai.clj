(ns tic-tac-toe.ai
  (:require [tic-tac-toe.board :refer :all]
            [tic-tac-toe.game :refer [game-over? switch-player progress-game-state]]))

(defn score [game-state]
  (let [board (:board game-state) player-marker (:current-player game-state)]
    (if (is-winner board player-marker)
      10
      (if (is-winner board (switch-player player-marker))
        -10
        0))))

(defn recreate-game-state [spot current-game-state]
  (let [marker (:current-player current-game-state)
        board (:board current-game-state)]
    (let [progressed-board
           (place-marker board spot marker)]
      (progress-game-state current-game-state progressed-board))))

(def find-max-score
  (fn [[spot score]] (= score (max score))))

(def find-min-score
  (fn [[spot score]] (= score (min score))))

(declare score-for-each-possible-move)

(defn return-max-score [game-state]
  (let [[[spot score]] (filter find-max-score (score-for-each-possible-move game-state))]
    score))

(defn return-min-score [game-state]
  (let [[[spot score]] (filter find-min-score (score-for-each-possible-move game-state))]
    score))

(defn return-min-or-max [coll game-state]
  (if (= (:current-player game-state) "O")
    (return-max-score game-state)
    (return-min-score game-state)))

(defn minimax [spot current-game-state]
  (let [possible-game-state (recreate-game-state spot current-game-state)]
    (if (game-over? possible-game-state)
      (score possible-game-state)
      (return-min-or-max (score-for-each-possible-move possible-game-state) possible-game-state))))

(defn score-for-each-possible-move [game-state]
  (for [possible-move (available-spots (:board game-state))]
    [possible-move (minimax possible-move game-state)]))

(defn best-computer-move [current-game-state]
  (let [[[spot score]] (filter find-max-score (score-for-each-possible-move current-game-state))]
    spot))
