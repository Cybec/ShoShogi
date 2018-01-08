
case class Board[Piece](board: Vector[Vector[Piece]]) {
  def this(size: Int, filling: Piece) = this(Vector.tabulate(size, size) { (row, col) => filling })

  val size: Int = board.size

  def cell(col: Int, row: Int): Option[Piece] = {
    if (row >= size || col >= size || row < 0 || col < 0) {
      None
    } else {
      Some(board(col)(row))
    }
  }

  def replaceCell(col: Int, row: Int, cell: Piece): Board[Piece] = copy(board.updated(col, board(col).updated(row, cell)))

  override def toString: String = {
    var index: Int = 0
    val alphabet = Array[Char]('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i')
    val returnValue = new StringBuilder

    returnValue.append("   0    1    2    3    4    5    6    7    8 \n \n")

    for (a <- 1 to 19) {
      if (a % 2 == 1) {
        for (b <- 1 to 48) returnValue.append("-")
      } else {
        for (c <- 0 to 8) {
          cell(index, c) match {
            case Some(piece) => returnValue.append(" | " + piece)
            case None =>
          }
        }
        returnValue.append(" | \t" + alphabet(index))
        index += 1
      }
      returnValue.append("\n")
    }
    returnValue.toString()
  }
}

import de.htwg.se.ShoShogi.model._

import scala.collection.mutable.ListBuffer

val player_1: Player =  Player("Player1", true)
val player_2: Player =  Player("Player2", false)

var board = new Board[Piece](9, new EmptyPiece)

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
board = board.replaceCell(1, 1, Bishop(player_1))
board = board.replaceCell(7, 1, Rook(player_1))
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

def experimentalMoveSet(pos: (Int, Int), board: Board[Piece]): List[(Int, Int)] = {
  //Jede Richtung ein Feld
  var moveList = ListBuffer[(Int, Int)]()

  for (x <- (pos._1 - 1) to (pos._1 + 1)) {
    for (y <- (pos._2 - 1) to (pos._2 + 1)) {
      if (x != pos._1 || y != pos._2) {
        board.cell(x, y).foreach(i => if (i.player.name != player_1.name) {
          moveList.+=((x, y))
        })
      }
    }
  }
  moveList.toList
}


board.cell(0, 4)
board.cell(4, 0)

experimentalMoveSet((4, 0), board)