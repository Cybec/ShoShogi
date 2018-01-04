package de.htwg.se.ShoShogi.controller

import de.htwg.se.ShoShogi.model._
import de.htwg.se.ShoShogi.util.Observable

import scala.collection.mutable.ListBuffer

// TODO 1: schauen ob vals und vars aus dem classenparameter entfernt werden köennen

//noinspection ScalaStyle
class Controller(var board: Board[Piece], val player_1: Player, val player_2: Player) extends Observable {
  val boardSize = 9
  var container = board.getContainer()

  def createEmptyBoard(): Unit = {
    board = new Board[Piece](boardSize, new EmptyPiece)
    notifyObservers
  }

  def createNewBoard(): Unit = {
    board = new Board[Piece](boardSize, new EmptyPiece)

    //Steine fuer Spieler 1
    board = board.replaceCell(0, 0, Lancer(player_1))
    board = board.replaceCell(1, 0, Knight(player_1))
    board = board.replaceCell(2, 0, SilverGeneral(player_1))
    board = board.replaceCell(3, 0, GoldenGeneral(player_1))
    board = board.replaceCell(4, 0, King(player_1))
    board = board.replaceCell(5, 0, GoldenGeneral(player_1))
    board = board.replaceCell(6, 0, SilverGeneral(player_1))
    board = board.replaceCell(7, 0, Knight(player_1))
    board = board.replaceCell(8, 0, Lancer(player_1))
    board = board.replaceCell(7, 1, Bishop(player_1))
    board = board.replaceCell(1, 1, Rook(player_1))
    for (i <- 0 to 8) {
      board = board.replaceCell(i, 2, Pawn(player_1))
    }

    //Steine fuer Spieler 2
    board = board.replaceCell(0, 8, Lancer(player_2))
    board = board.replaceCell(1, 8, Knight(player_2))
    board = board.replaceCell(2, 8, SilverGeneral(player_2))
    board = board.replaceCell(3, 8, GoldenGeneral(player_2))
    board = board.replaceCell(4, 8, King(player_2))
    board = board.replaceCell(5, 8, GoldenGeneral(player_2))
    board = board.replaceCell(6, 8, SilverGeneral(player_2))
    board = board.replaceCell(7, 8, Knight(player_2))
    board = board.replaceCell(8, 8, Lancer(player_2))
    board = board.replaceCell(1, 7, Bishop(player_2))
    board = board.replaceCell(7, 7, Rook(player_2))
    for (i <- 0 to 8) {
      board = board.replaceCell(i, 6, Pawn(player_2))
    }

    notifyObservers
  }

  def boardToString(): String = board.toString

  def possibleMoves(pos: (Int, Int)): List[(Int, Int)] = {
    board.cell(pos._1, pos._2) match {
      case Some(piece) => piece.getMoveSet((pos._1, pos._2), board)
      case None => List()
    }
  }

  def movePiece(currentPos: (Int, Int), destination: (Int, Int)): Boolean = {
    if (possibleMoves(currentPos).contains(destination)) {

      val tempPieceDestination = board.cell(destination._1, destination._2).getOrElse(return false)
      val tempPieceCurrent = board.cell(currentPos._1, currentPos._2).getOrElse(return false)

      board = board.replaceCell(destination._1, destination._2, tempPieceCurrent)
      board = board.replaceCell(currentPos._1, currentPos._2, new EmptyPiece)

      board = board.addToPlayerContainer(tempPieceCurrent.player, tempPieceDestination)
      //promotable(tempPieceDestination, destination)
      notifyObservers
      true
    } else {
      false
    }
  }

  def promotable(currentPos: (Int, Int), dest: (Int, Int)): Boolean = {
    val piece = board.cell(currentPos._1, currentPos._2).getOrElse(return false)
    if ((piece.player == player_1 && piece.hasPromotion && dest._2 > 5) || (piece.player == player_2 && piece.hasPromotion && dest._2 < 3)) {
      true
    } else {
      false
    }
  }

  def promotePiece(currentPos: (Int, Int)): Boolean = {
    var piece = board.cell(currentPos._1, currentPos._2).getOrElse(return false)
    piece = piece.promotePiece.getOrElse(return false)
    board = board.replaceCell(currentPos._1, currentPos._2, piece)
    true
  }
}
