package de.htwg.se.ShoShogi.model.pieceComponent

import de.htwg.se.ShoShogi.model.boardComponent.BoardInterface

trait PieceInterface {
  val name: String

  val hasPromotion: Boolean

  def promotePiece: Option[PieceInterface]

  def isFirstOwner: Boolean

  override def toString: String

  def toStringLong: String

  def getMoveSet(pos: (Int, Int), board: BoardInterface): List[(Int, Int)]

  def rekMoveSet(board: BoardInterface, newPos: (Int, Int), rek: Int, value: (Int, Int)): List[(Int, Int)]

  def cloneToNewPlayer(first: Boolean): PieceInterface

  def typeEquals(piecesAbbreviation: String): Boolean = this.toString().trim == piecesAbbreviation.trim
}
