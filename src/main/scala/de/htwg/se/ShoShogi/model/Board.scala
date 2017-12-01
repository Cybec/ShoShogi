package de.htwg.se.ShoShogi.model

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
    returnValue.toString()
  }
}

