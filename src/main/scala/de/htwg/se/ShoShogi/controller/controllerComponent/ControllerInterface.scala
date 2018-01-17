package de.htwg.se.ShoShogi.controller.controllerComponent

import de.htwg.se.ShoShogi.controller.controllerComponent.controllerBaseImpl.Controller
import de.htwg.se.ShoShogi.model.{Board, Piece, Player}

object MoveResult extends Enumeration {
  type EnumType = Value
  val invalidMove, validMove, kingSlain, validMoveContainer = Value
}

trait ControllerInterface extends scala.swing.Publisher {

  def createController(board: Board, player1: Player, player2: Player): ControllerInterface = new Controller(board, player1, player2)

  val boardSize = 9

  def saveState: Unit

  def undoCommand: Unit

  def redoCommand: Unit

  def changeNamePlayer1(newName: String): Unit

  def changeNamePlayer2(newName: String): Unit

  def getContainer: (List[Piece], List[Piece])

  def createEmptyBoard(): Unit

  def createNewBoard(): Unit

  def boardToString(): String

  def boardToArray(): Array[Array[Piece]]

  def getPossibleMoves(pos: (Int, Int)): List[(Int, Int)]

  def movePiece(currentPos: (Int, Int), destination: (Int, Int)): MoveResult.Value

  def getPossibleMovesConqueredPiece(piece: String): List[(Int, Int)]

  def getPossibleMvConPlayer(piece: String): List[(Int, Int)]

  def moveConqueredPiece(pieceAbbreviation: String, destination: (Int, Int)): Boolean

  def promotable(position: (Int, Int)): Boolean

  def promotePiece(piecePosition: (Int, Int)): Boolean

}
