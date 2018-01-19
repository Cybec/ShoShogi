package de.htwg.se.ShoShogi.model.pieceComponent

import de.htwg.se.ShoShogi.model.boardComponent.BoardInterface
import de.htwg.se.ShoShogi.model.pieceComponent.pieceBaseImpl.Piece
import de.htwg.se.ShoShogi.model.playerComponent.Player

trait PieceInterface {

  val hasPromotion: Boolean

  def promotePiece: Option[Piece]

  def isFirstOwner: Boolean

  override def toString: String

  def toStringLong: String

  def getMoveSet(pos: (Int, Int), board: BoardInterface): List[(Int, Int)]

  def rekMoveSet(board: BoardInterface, newPos: (Int, Int), rek: Int, value: (Int, Int)): List[(Int, Int)]

  def cloneToNewPlayer(player: Player): Piece

  def typeEquals(piecesAbbreviation: String): Boolean = this.toString().trim == piecesAbbreviation.trim
}
