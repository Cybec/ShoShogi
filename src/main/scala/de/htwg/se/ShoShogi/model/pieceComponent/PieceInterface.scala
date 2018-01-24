package de.htwg.se.ShoShogi.model.pieceComponent

import de.htwg.se.ShoShogi.model.boardComponent.BoardInterface

/**
  * Interface for Pieces on Board
  */
trait PieceInterface {
  /**
    * Name of Piece
    */
  val name: String

  /**
    * true if this Pieces has a promotion
    */
  val hasPromotion: Boolean

  /**
    * promote this Piece
    *
    * @return Option with new Promoted Piece
    */
  def promotePiece: Option[PieceInterface]

  /**
    * true if this piece is owned by player_1
    *
    * @return true = player_1 / false = player_2
    */
  def isFirstOwner: Boolean

  /**
    * abbreviation of this Piece
    *
    * @return abbreviation
    */
  override def toString: String

  /**
    * Long name of this Piece
    *
    * @return Name
    */
  def toStringLong: String

  /**
    * Calculating possible Moves
    *
    * @param pos   current position of piece
    * @param board ShoShogi Board
    * @return Returning List of all possible moves
    */
  def getMoveSet(pos: (Int, Int), board: BoardInterface): List[(Int, Int)]

  /**
    * Recursive method for calculating moveSet
    *
    * @param board  ShoShogi Board
    * @param newPos new position
    * @param rek    amount of recursive calls left to do
    * @param value  found possible move cell
    * @return Returning List of all possible moves
    */
  def rekMoveSet(board: BoardInterface, newPos: (Int, Int), rek: Int, value: (Int, Int)): List[(Int, Int)]

  /**
    * Cloning this piece to another player
    *
    * @param first true = player_1 / false = player_2
    * @return cloned piece
    */
  def cloneToNewPlayer(first: Boolean): PieceInterface

  /**
    * An equals function to compare if this piece abbreviation is the same as the handed over
    *
    * @param piecesAbbreviation abbreviation to compare with
    * @return Returning the result of this compare
    */
  def typeEquals(piecesAbbreviation: String): Boolean = this.toString().trim == piecesAbbreviation.trim
}
