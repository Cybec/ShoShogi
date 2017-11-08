package de.htwg.se.ShoShogi.model

case class Board[T](rows: Vector[Vector[T]]) {
  def this(size: Int, filling: T) = this(Vector.tabulate(size, size) { (row, col) => filling })

  val size: Int = rows.size

  def cell(row: Int, col: Int): T = rows(row)(col)

  def fill(filling: T): Board[T] = copy(Vector.tabulate(size, size) { (row, col) => filling })

  def replaceCell(row: Int, col: Int, cell: T): Board[T] = copy(rows.updated(row, rows(row).updated(col, cell)))
}

