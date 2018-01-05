package de.htwg.se.ShoShogi.controller

import de.htwg.se.ShoShogi.model._
import de.htwg.se.ShoShogi.util.Observable

import scala.collection.mutable.ListBuffer

// TODO 1: schauen ob vals und vars aus dem classenparameter entfernt werden köennen

trait State {
  def changePlayer(bool: Boolean): Boolean = !bool
}

//noinspection ScalaStyle
class Controller(private var board: Board, val player_1: Player, val player_2: Player) extends Observable with State {
  val boardSize = 9
  var container = board.getContainer()
  var state = true

  def getPieceAbbreviationList: List[String] = Pieces.getPiecesAbbreviation

  def createEmptyBoard(): Unit = {
    board = new Board(boardSize, new EmptyPiece)
    notifyObservers
  }

  def createNewBoard(): Unit = {
    board = new Board(boardSize, new EmptyPiece)

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

      if (state == tempPieceCurrent.player.first) {

        board = board.replaceCell(destination._1, destination._2, tempPieceCurrent)
        board = board.replaceCell(currentPos._1, currentPos._2, new EmptyPiece)

        board = board.addToPlayerContainer(tempPieceCurrent.player, tempPieceDestination)
        state = changePlayer(state)
        notifyObservers
        true
      } else {
        false
      }
    } else {
      false
    }
  }

  def possibleMovesConqueredPiece(piece: String): List[(Int, Int)] = {
    var possibleMoves = List[(Int, Int)]()

    if (state) {
      possibleMoves = getPossibleMvConPlayer1(piece)
    } else {
      possibleMoves = getPossibleMvConPlayer2(piece)
    }

    possibleMoves
  }

  def getPossibleMvConPlayer1(piece: String): List[(Int, Int)] = {
    var possibleMoves = List[(Int, Int)]()

    if (piece == "P") {
      for (column: Int <- 0 until board.size) {
        if (!board.getPiecesInColumn(column).contains(Pawn)) {
          if (!board.getPiecesInColumn(column).contains(King)) {
            possibleMoves = possibleMoves ::: board.getEmptyCellsInColumn(column, (0, 8))
          } else {
            for (row <- 0 until board.size) {
              board.cell(column, row) match {
                case Some(piece) => if (piece.isInstanceOf[EmptyPiece]) {
                  possibleMoves = possibleMoves :+ (column, row)
                } else if (piece.isInstanceOf[King] && piece.player.first) {
                  possibleMoves = possibleMoves.filter(_ != (column, row - 1))
                }
                case None => {}
              }
            }
          }
        }
      }
    } else if (piece == "KN" || piece == "L") {
      for (x <- 0 until board.size) {
        possibleMoves = possibleMoves ::: board.getEmptyCellsInColumn(x, (0, 8))
      }
    } else {
      for (x <- 0 until board.size) {
        possibleMoves = possibleMoves ::: board.getEmptyCellsInColumn(x, (0, 9))
      }
    }
    possibleMoves
  }

  def getPossibleMvConPlayer2(piece: String): List[(Int, Int)] = {
    var possibleMoves = List[(Int, Int)]()

    if (piece == "P") {
      for (column: Int <- 0 until board.size) {
        if (!board.getPiecesInColumn(column).contains(Pawn)) {
          if (!board.getPiecesInColumn(column).contains(King)) {
            possibleMoves = possibleMoves ::: board.getEmptyCellsInColumn(column, (1, 9))
          } else {
            for (row <- (board.size - 1) to 0) {
              board.cell(column, row) match {
                case Some(piece) => if (piece.isInstanceOf[EmptyPiece]) {
                  possibleMoves = possibleMoves :+ (column, row)
                } else if (piece.isInstanceOf[King] && piece.player.first) {
                  possibleMoves = possibleMoves.filter(_ != (column, row + 1))
                }
                case None => {}
              }
            }
          }
        }
      }

    } else if (piece == "KN" || piece == "L") {
      for (x: Int <- 0 until board.size) {
        possibleMoves = possibleMoves ::: board.getEmptyCellsInColumn(x, (1, 9))
      }
    } else {
      for (x: Int <- 0 until board.size) {
        possibleMoves = possibleMoves ::: board.getEmptyCellsInColumn(x, (0, 9))
      }
    }
    possibleMoves
  }

  def moveConqueredPiece(pieceAbbreviation: String, destination: (Int, Int)): Boolean = {
    if (possibleMovesConqueredPiece(pieceAbbreviation).contains(destination)) {

      val currentPlayer = if (state) {
        player_1
      } else {
        player_2
      }
      var tempPiece: Piece = new EmptyPiece

      val success = board.getFromPlayerContainer(currentPlayer) {
        _.typeEquals(pieceAbbreviation)
      } match {
        case Some((newBoard, piece)) =>
          board = newBoard
          tempPiece = piece
          true
        case None => false
      }
      if (success) {
        board = board.replaceCell(destination._1, destination._2, tempPiece)

        state = changePlayer(state)
        notifyObservers
        true
      } else {
        false
      }
    } else {
      false
    }
  }

  def promotable(currentPos: (Int, Int), dest: (Int, Int)): Boolean = {
    val piece = board.cell(currentPos._1, currentPos._2).getOrElse(return false)
    piece.hasPromotion && ((piece.player == player_1 && dest._2 > 5) || (piece.player == player_2 && dest._2 < 3))
  }

  def promotePiece(currentPos: (Int, Int)): Boolean = {
    var piece = board.cell(currentPos._1, currentPos._2).getOrElse(return false)
    piece = piece.promotePiece.getOrElse(return false)
    board = board.replaceCell(currentPos._1, currentPos._2, piece)
    true
  }
}
