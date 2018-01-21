package de.htwg.se.ShoShogi.model.boardComponent.boardBaseImpl

import com.google.inject.Inject
import com.google.inject.name.Named
import de.htwg.se.ShoShogi.model.boardComponent.BoardInterface
import de.htwg.se.ShoShogi.model.pieceComponent.PieceInterface
import de.htwg.se.ShoShogi.model.pieceComponent.pieceBaseImpl.{PieceFactory, PiecesEnum}
import de.htwg.se.ShoShogi.model.playerComponent.Player

class BoardInj @Inject() (@Named("DefaultSize") boardSize: Int) extends Board(boardSize, PieceFactory.getEmptyPiece) {

  //  override def createNewBoard: BoardInterface = new Board(boardSize, PieceFactory.getEmptyPiece)

}

case class Board(board: Vector[Vector[PieceInterface]], containerPlayer_0: List[PieceInterface], containerPlayer_1: List[PieceInterface]) extends BoardInterface {
  def this(size: Int, filling: PieceInterface) = this(Vector.tabulate(size, size) { (row, col) => filling }, List.empty[PieceInterface], List.empty[PieceInterface])

  override def createNewBoard: BoardInterface = new Board(size, PieceFactory.getEmptyPiece)

  override def getContainer(): (List[PieceInterface], List[PieceInterface]) = {
    (containerPlayer_0, containerPlayer_1)
  }

  override def setContainer(container: (List[PieceInterface], List[PieceInterface])): Board = {
    copy(board, container._1, container._2)
  }

  override def addToPlayerContainer(first: Boolean, piece: PieceInterface): Board = {
    if (!PieceFactory.isInstanceOfPiece(PiecesEnum.EmptyPiece, piece)) {
      if (first) {
        val newCon: List[PieceInterface] = containerPlayer_0 :+ piece.cloneToNewPlayer(first)
        copy(board, newCon, containerPlayer_1)
      } else {
        val newCon: List[PieceInterface] = containerPlayer_1 :+ piece.cloneToNewPlayer(first)
        copy(board, containerPlayer_0, newCon)
      }
    } else {
      this
    }
  }

  override def getFromPlayerContainer(player: Player)(pred: (PieceInterface) => Boolean): Option[(Board, PieceInterface)] = {
    if (player.first) {
      val (before, atAndAfter) = containerPlayer_0 span (x => !pred(x))
      if (atAndAfter.nonEmpty) {
        val getPiece: PieceInterface = atAndAfter.head
        val newCon: List[PieceInterface] = before ::: atAndAfter.drop(1)
        Some((copy(board, newCon, containerPlayer_1), getPiece.cloneToNewPlayer(player.first)))
      } else {
        None
      }
    } else {
      val (before, atAndAfter) = containerPlayer_1 span (x => !pred(x))
      if (atAndAfter.nonEmpty) {
        val getPiece: PieceInterface = atAndAfter.head
        val newCon: List[PieceInterface] = before ::: atAndAfter.drop(1)
        Some((copy(board, containerPlayer_0, newCon), getPiece.cloneToNewPlayer(player.first)))
      } else {
        None
      }
    }
  }

  override def replaceCell(col: Int, row: Int, cell: PieceInterface): Board =
    copy(board.updated(col, board(col).updated(row, cell)), containerPlayer_0, containerPlayer_1)

  override def copyBoard(): Board = copy(board, containerPlayer_0, containerPlayer_1)

  override def getPiecesInColumn(column: Int, stateTurn: Boolean): List[PieceInterface] = {
    var pieces = List[PieceInterface]()

    if (column <= this.size && column >= 0) {
      for (i <- 0 until this.size) {
        this.cell(column, i) match {
          case Some(piece) if (PieceFactory.isInstanceOfPiece(PiecesEnum.EmptyPiece, piece)) => {}
          case Some(piece) => if (stateTurn == piece.isFirstOwner) {
            pieces = pieces :+ piece
          }
          case None => {}
        }
      }
    }
    pieces
  }

  override def getEmptyCellsInColumn(column: Int, range: (Int, Int)): List[(Int, Int)] = {
    var emptyCells = List[(Int, Int)]()

    if (column <= this.size && column >= 0) {
      for (i <- range._1 to range._2) {
        this.cell(column, i) match {
          case Some(piece) if (PieceFactory.isInstanceOfPiece(PiecesEnum.EmptyPiece, piece)) =>
            emptyCells = emptyCells :+ (column, i)
          case Some(_) => {}
          case None => {}
        }
      }
    }

    emptyCells
  }

  override def toArray: Array[Array[PieceInterface]] = {
    val returnList: Array[Array[PieceInterface]] = Array.ofDim[PieceInterface](size, size)

    for {
      col <- 0 until size
      row <- 0 until size
    } {
      cell(col, row) match {
        case Some(piece) => returnList(col)(row) = piece
        case None => returnList(col)(row) = PieceFactory.getEmptyPiece
      }
    }

    returnList
  }

  override def size: Int = board.size

  override def toString: String = {
    var index: Int = 0
    val alphabet = Array[Char]('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i')
    val returnValue = new StringBuilder

    returnValue.append("Captured: ")
    containerPlayer_0.foreach(x => returnValue.append(x).append("   "))

    returnValue.append("\n    0     1     2     3     4     5     6     7     8 \n \n")

    for (a <- 1 to 19) {
      if (a % 2 == 1) {
        for (b <- 1 to 57) returnValue.append("-")
      } else {
        for (c <- 0 to 8) {
          cell(c, index) match {
            case Some(piece) => returnValue.append(" | " + piece)
            case None =>
          }
        }
        returnValue.append(" | \t" + alphabet(index))
        index += 1
      }
      returnValue.append("\n")
    }

    returnValue.append("Captured: ")
    containerPlayer_1.foreach(x => returnValue.append(x).append("   "))
    returnValue.append("\n")

    returnValue.toString()
  }

  override def cell(col: Int, row: Int): Option[PieceInterface] = {
    if (row >= size || col >= size || row < 0 || col < 0) {
      None
    } else {
      Some(board(col)(row))
    }
  }
}

