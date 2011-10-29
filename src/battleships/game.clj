(ns battleships.game
  (:require [battleships.board :as board]))

;; there is a special bit of knowledge required here for the ship keys but, since that is
;; core to the game, I'm not going to bother defining the keys dynamically.
(defn new-ships []
  (into {} (map (fn [v] [(:key v) v])
                [(board/make-ship "aircraft carrier" :aircraft-carrier 5)
                 (board/make-ship "battleship" :battleship 4)
                 (board/make-ship "destroyer" :destroyer 3)
                 (board/make-ship "submarine" :submarine 3)
                 (board/make-ship "patrol boat" :patrol-boat 2)])))

(defn new-player [name]
  {:name name, :board (board/make-board 10 10), :ships (new-ships)})

(defn new-game
  ([]
     (new-game "Player 1" "Player 2"))
  ([p1-name p2-name]
     {:player1 (new-player p1-name), :player2 (new-player p2-name)}))

(defn update-board [player board]
  (assoc player :board board))

(defn place-ship
  "Places the ship on the board and returns either the updated player, or nil if the move was invalid."
  [{:keys [board ships] :as player} ship-key coord orientation]
  (if-let [ship (ship-key ships)]
    (if-let [new-board (board/place-ship ship board coord orientation)]
      (-> player
          (update-board new-board)
          (update-in [:ships ship-key] assoc :coord coord :orientation orientation)))))

(defn all-ships-placed?
  "Check to make sure all the ships have a coordinate."
  [{:keys [ships] :as player}]
  (and (every? :coord (vals ships))
       (every? :orientation (vals ships))))

(defn fire-shell
  "Returns the new player or nil if the square is invalid or already shelled."
  [{:keys [board] :as player} coord]
  (if (and (board/valid-square? board coord) (board/not-shelled? board coord))
    (update-board player (board/fire-shell board coord))))

(defn update-hits
  "Returns updated player if there was a new hit, or nil if no change."
  [{:keys [board] :as player} coord]
  (if-let [ship-hit (board/hit? board coord)]
    (update-in player [:ships ship-hit :hits] inc)))

(defn sunk? [])

(defn count-sunk-ships [])

(defn all-ships-sunk? [])

;; write the functions that will then be called by core, which will contain the game loop
;; e.g.
;; place all ships
;; player1 fire
;; player2 fire etc
