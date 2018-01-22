package de.htwg.se.ShoShogi.model.boardComponent

import de.htwg.se.ShoShogi.model.boardComponent.boardBaseImpl.Board
import de.htwg.se.ShoShogi.model.pieceComponent.PieceInterface
import de.htwg.se.ShoShogi.model.playerComponent.Player

trait BoardInterface {

  /**
    * The size of the playing board
    *
    * @return the size of the board
    */
  def size: Int

  /**
    * Copys the current board
    *
    * @return a board Interface
    */
  def copyBoard(): BoardInterface

  def getContainer: (List[PieceInterface], List[PieceInterface])

  def createNewBoard(): BoardInterface

  def setContainer(container: (List[PieceInterface], List[PieceInterface])): BoardInterface

  def addToPlayerContainer(first: Boolean, piece: PieceInterface): BoardInterface

  def getFromPlayerContainer(player: Player)(pred: (PieceInterface) => Boolean): Option[(Board, PieceInterface)]

  def cell(col: Int, row: Int): Option[PieceInterface]

  def replaceCell(col: Int, row: Int, cell: PieceInterface): BoardInterface

  def getPiecesInColumn(column: Int, stateTurn: Boolean): List[PieceInterface]

  def getEmptyCellsInColumn(column: Int, range: (Int, Int)): List[(Int, Int)]

  def toArray: Array[Array[PieceInterface]]

}
