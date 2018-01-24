package de.htwg.se.ShoShogi.model.boardComponent

import de.htwg.se.ShoShogi.model.boardComponent.boardBaseImpl.Board
import de.htwg.se.ShoShogi.model.pieceComponent.PieceInterface
import de.htwg.se.ShoShogi.model.playerComponent.Player

/**
  * An Interface to implement the Board unit for the game ShoShogi.
  */
trait BoardInterface {

  /**
   * The size of the playing board
   *
   * @return the size of the board
   */
  def size: Int

  /**
    * Copies the current board
   *
    * @return a the copied board Interface
   */
  def copyBoard(): BoardInterface

  /**
    * Returns List() of conquered Pieces
    *
    * @return player_1 conquered Pieces and player_2 conquered Pieces
    */
  def getContainer: (List[PieceInterface], List[PieceInterface])

  /**
    * Creating a new Board and initialising board with Pieces
    *
    * @return created Board
    */
  def createNewBoard(): BoardInterface

  /**
    * Setting conquered Pieces Lists of the players
    *
    * @param container Tuple List of conquered Pieces
    * @return new Board with changed conquered Pieces
    */
  def setContainer(container: (List[PieceInterface], List[PieceInterface])): BoardInterface

  /**
    * Adding a Piece to conquered Pieces of specified Player
    *
    * @param first true = player_1; false = player_2
    * @param piece conquered Pieces to add
    * @return new Board with changed conquered Pieces
    */
  def addToPlayerContainer(first: Boolean, piece: PieceInterface): BoardInterface

  /**
    * Getting conquered Pieces of Player
    *
    * @param player       required Player
    * @param abbreviation wanted Piece abbreviation
    * @return Returning an Option with Tuple (new Board after remove from conquered Pieces, wanted Piece)
    */
  def getFromPlayerContainer(player: Player)(abbreviation: (PieceInterface) => Boolean): Option[(Board, PieceInterface)]

  /**
    * Get Piece in cell
    *
    * @param col column of Cell
    * @param row row of Cell
    * @return Option with content of Cell
    */
  def cell(col: Int, row: Int): Option[PieceInterface]

  /**
    * Replacing Cell with Piece
    *
    * @param col  column of Cell
    * @param row  row of Cell
    * @param cell new Cell Content
    * @return new Board with changed Cell
    */
  def replaceCell(col: Int, row: Int, cell: PieceInterface): BoardInterface

  /**
    * Get all Pieces in wanted Column, without EmptyPiece
    *
    * @param column    column of Board
    * @param stateTurn true = player_1's turn; false = player_2's turn
    * @return List of Pieces in specified column, without EmptyPiece
    */
  def getPiecesInColumn(column: Int, stateTurn: Boolean): List[PieceInterface]

  /**
    * Get all EmptyPiece in wanted Column
    *
    * @param column column of Board
    * @param range  range in Column
    * @return List of EmptyPiece in specified column
    */
  def getEmptyCellsInColumn(column: Int, range: (Int, Int)): List[(Int, Int)]

  /**
    * Creating a 2D-Array of the Board
    *
    * @return 2D-Array of Board
    */
  def toArray: Array[Array[PieceInterface]]

}
