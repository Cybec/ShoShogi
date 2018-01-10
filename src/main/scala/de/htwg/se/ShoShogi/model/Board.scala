package de.htwg.se.ShoShogi.model

case class Board(board: Vector[Vector[Piece]], containerPlayer_0: List[Piece], containerPlayer_1: List[Piece]) {
  def this(size: Int, filling: Piece) = this(Vector.tabulate(size, size) { (row, col) => filling }, List.empty[Piece], List.empty[Piece])

  val size: Int = board.size

  def getContainer(): (List[Piece], List[Piece]) = {
    (containerPlayer_0, containerPlayer_1)
  }

  def addToPlayerContainer(player: Player, piece: Piece): Board = {
    if (!piece.isInstanceOf[EmptyPiece]) {
      if (player.first) {
        val newCon: List[Piece] = containerPlayer_0 :+ piece.cloneToNewPlayer(player)
        copy(board, newCon, containerPlayer_1)
      } else {
        val newCon: List[Piece] = containerPlayer_1 :+ piece.cloneToNewPlayer(player)
        copy(board, containerPlayer_0, newCon)
      }
    } else {
      this
    }
  }

  def getFromPlayerContainer(player: Player)(pred: (Piece) => Boolean): Option[(Board, Piece)] = {
    if (player.first) {
      val (before, atAndAfter) = containerPlayer_0 span (x => !pred(x))
      if (atAndAfter.nonEmpty) {
        val getPiece: Piece = atAndAfter.head
        val newCon: List[Piece] = before ::: atAndAfter.drop(1)
        Some((copy(board, newCon, containerPlayer_1), getPiece.cloneToNewPlayer(player)))
      } else {
        None
      }
    } else {
      val (before, atAndAfter) = containerPlayer_1 span (x => !pred(x))
      if (atAndAfter.nonEmpty) {
        val getPiece: Piece = atAndAfter.head
        val newCon: List[Piece] = before ::: atAndAfter.drop(1)
        Some((copy(board, containerPlayer_0, newCon), getPiece.cloneToNewPlayer(player)))
      } else {
        None
      }
    }
  }

  def cell(col: Int, row: Int): Option[Piece] = {
    if (row >= size || col >= size || row < 0 || col < 0) {
      None
    } else {
      Some(board(col)(row))
    }
  }

  def replaceCell(col: Int, row: Int, cell: Piece): Board =
    copy(board.updated(col, board(col).updated(row, cell)), containerPlayer_0, containerPlayer_1)

  def getPiecesInColumn(column: Int, stateTurn: Boolean): List[Piece] = {
    var pieces = List[Piece]()

    if (column <= this.size && column >= 0) {
      for (i <- 0 until this.size) {
        this.cell(column, i) match {
          case Some(_: EmptyPiece) => {}
          case Some(piece) => if (stateTurn == piece.player.first) {
            pieces = pieces :+ piece
          }
        }
      }
    }

    pieces
  }

  def getEmptyCellsInColumn(column: Int, range: (Int, Int)): List[(Int, Int)] = {
    var emptyCells = List[(Int, Int)]()

    if (column <= this.size && column >= 0) {
      for (i <- range._1 to range._2) {
        this.cell(column, i) match {
          case Some(_: EmptyPiece) => emptyCells = emptyCells :+ (column, i)
          case Some(_) => {}
          case None => {}
        }
      }
    }

    emptyCells
  }

  override def toString: String = {
    var index: Int = 0
    val alphabet = Array[Char]('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i')
    val returnValue = new StringBuilder

    returnValue.append("Captured Player 1: ")
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

    returnValue.append("Captured Player 2: ")
    containerPlayer_1.foreach(x => returnValue.append(x).append("   "))
    returnValue.append("\n")

    returnValue.toString()
  }
}

