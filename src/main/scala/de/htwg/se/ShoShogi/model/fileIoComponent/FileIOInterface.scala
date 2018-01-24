package de.htwg.se.ShoShogi.model.fileIoComponent

import de.htwg.se.ShoShogi.model.boardComponent.BoardInterface
import de.htwg.se.ShoShogi.model.playerComponent.Player

/**
  * A Interface to implement the I/O unit for the game ShoShogi.
  */
trait FileIOInterface {

  /**
    * Loads the saved game
    *
    * @return Returning an Option with the loaded Board, playerTurn and the two PLayers
    */
  def load: Option[(BoardInterface, Boolean, Player, Player)]

  /**
    * Saving the current game
    *
    * @param board    current Board
    * @param state    current Player Turn (true=player_1/false=player_2)
    * @param player_1 Player_1
    * @param player_2 Player_2
    */
  def save(board: BoardInterface, state: Boolean, player_1: Player, player_2: Player): Unit

}
