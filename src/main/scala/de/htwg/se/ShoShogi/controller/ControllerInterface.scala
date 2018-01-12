package de.htwg.se.ShoShogi.controller

import de.htwg.se.ShoShogi.ShoShogi.boardSize
import de.htwg.se.ShoShogi.model.{Board, EmptyPiece, Piece, Player}

import scala.swing.Publisher

trait ControllerInterface extends playerRounds with Publisher {
  def createController(board: Board, player1: Player, player2: Player): Controller = new Controller(board, player1, player2)

  object MoveResult extends Enumeration {
    type EnumType = Value
    val invalidMove, validMove, kingSlain = Value
  }

  def createEmptyBoard(): Unit

  def createNewBoard(): Unit

  def boardToString(): String

  def boardToArray(): Array[Array[Piece]]

  def possibleMoves(pos: (Int, Int)): List[(Int, Int)]

  def movePiece(currentPos: (Int, Int), destination: (Int, Int)): MoveResult.Value

  def possibleMovesConqueredPiece(piece: String): List[(Int, Int)]

  def getPossibleMvConPlayer1(piece: String): List[(Int, Int)]

  def getPossibleMvConPlayer2(piece: String): List[(Int, Int)]

  def moveConqueredPiece(pieceAbbreviation: String, destination: (Int, Int)): Boolean

  def promotable(position: (Int, Int)): Boolean

  def promotePiece(piecePosition: (Int, Int)): Boolean

}
