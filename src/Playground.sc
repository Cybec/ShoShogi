case class Player(name: String, first:Boolean) {
  override def toString: String = name
}

abstract class Piece(name: String, player: Player) {

  def promotePiece: Option[Piece]

  var hasPromotion: Boolean

  var token: String

  def getMoveSet(pos: (Int, Int)): List[(Int, Int)]
}

//region King

/*Author:   Mert, Nick
* Role:     Erstellt eine King-Figur */
case class King(player: Player)
  extends Piece("King", player: Player) {
  var hasPromotion: Boolean = false

  /*Author:   Mert, Nick
  * Role:     Stuft das Piece auf
  * Return:   Gibt das Promotete Piece zurueck*/
  def promotePiece: Option[Piece] = {
    None
  }

  var token: String = "K"

  /*Author:   Mert, Nick
  * Role:     Gibt die moeglichen Bewegungsfelder zurueck
  * Return:   List[(Int, Int)] mit den Koordinaten */
  def getMoveSet(pos: (Int, Int)): List[(Int, Int)] = {
    val a: List[(Int, Int)] = List((9999,9999),(9999,9999))
    a
  }
}

case class EmptyPiece()
  extends Piece("King", new Player("", false)) {
  var hasPromotion: Boolean = false

  /*Author:   Mert, Nick
  * Role:     Stuft das Piece auf
  * Return:   Gibt das Promotete Piece zurueck*/
  def promotePiece: Option[Piece] = {
    None
  }

  var token: String = ""

  /*Author:   Mert, Nick
  * Role:     Gibt die moeglichen Bewegungsfelder zurueck
  * Return:   List[(Int, Int)] mit den Koordinaten */
  def getMoveSet(pos: (Int, Int)): List[(Int, Int)] = {
    val a: List[(Int, Int)] = List((9999,9999),(9999,9999))
    a
  }
}


//endregion
case class Board[T](rows: Vector[Vector[T]]) {
  def this(size: Int, filling: T) = this(Vector.tabulate(size, size) { (row, col) => filling })

  val size: Int = rows.size

  def cell(row: Int, col: Int): T = rows(row)(col)

  def fill(filling: T): Board[T] = copy(Vector.tabulate(size, size) { (row, col) => filling })

  def replaceCell(row: Int, col: Int, cell: T): Board[T] = copy(rows.updated(row, rows(row).updated(col, cell)))
}



val board1 = new Board[Piece](2, King(new Player("Hans", true)))
val board2 = new Board[Piece](9, new EmptyPiece)

