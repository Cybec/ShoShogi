package de.htwg.se.ShoShogi.model.boardComponent

import de.htwg.se.ShoShogi.model.boardComponent.boardBaseImpl.Board
import de.htwg.se.ShoShogi.model.pieceComponent.Piece
import de.htwg.se.ShoShogi.model.playerComponent.Player

trait BoardInterface {

  def size: Int

  def copyBoard(): BoardInterface

  def getContainer(): (List[Piece], List[Piece])

  def setContainer(container: (List[Piece], List[Piece])): BoardInterface

  def addToPlayerContainer(player: Player, piece: Piece): BoardInterface

  def getFromPlayerContainer(player: Player)(pred: (Piece) => Boolean): Option[(Board, Piece)]

  def cell(col: Int, row: Int): Option[Piece]

  def replaceCell(col: Int, row: Int, cell: Piece): BoardInterface

  def getPiecesInColumn(column: Int, stateTurn: Boolean): List[Piece]

  def getEmptyCellsInColumn(column: Int, range: (Int, Int)): List[(Int, Int)]

  def toArray: Array[Array[Piece]]

}
