package de.htwg.se.ShoShogi.model.fileIoComponent

import de.htwg.se.ShoShogi.controller.controllerComponent.controllerBaseImpl.RoundState
import de.htwg.se.ShoShogi.model.boardComponent.BoardInterface
import de.htwg.se.ShoShogi.model.playerComponent.Player

trait FileIOInterface {

  def load: Option[(BoardInterface, Boolean, Player, Player)]

  def save(board: BoardInterface, state: Boolean, player_1: Player, player_2: Player): Unit

}
