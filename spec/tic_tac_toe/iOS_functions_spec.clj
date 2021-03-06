(ns tic-tac-toe.iOS-functions-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.iOS-functions :as iOS]))

(describe "iOS functions"
  (context "#minimax-move"
    (it "returns the best computer move given a board"
      (should= 8 (iOS/minimax-move [0,1,2,3,4,5,6,7,8]))))

    (context "#minimax-move"
      (it "returns the best computer move given a board"
        (should= 8 (iOS/minimax-move ["" "" "" "" "" "" "" "" ""]))))

      (it "return the best computer move given a board and a marker"
        (should= 8 (iOS/minimax-move ["" "" "" "" "" "" "" "" ""] "O")))

      (it "return the best computer move given a board and a marker"
        (should= 4 (iOS/minimax-move ["" "" "" "" "" "" "" "" "O"] "X")))

    (context "#return-move-or-index"
      (it "returns the index if the move is empty"
        (should= 0 (iOS/return-move-or-index [0 ""]))))

    (context "#spot-with-index"
      (it "returns a collection of vectors with the spot and the index"
        (should= [[0 ""][1 ""][2 ""][3 ""][4 ""][5 ""][6 ""][7 ""][8 ""]]
        (iOS/spot-with-index ["" "" "" "" "" "" "" "" ""]))))

    (context "#switch-marker"
      (it "returns the opposite marker"
        (should= "X" (iOS/switch-marker "O"))))

    (context "#easy-computer-move"
      (it "returns a random available spot"
        (should= 8 (iOS/easy-computer-move ["X" "O" "X" "O" "X" "O" "X" "O" ""])))))
