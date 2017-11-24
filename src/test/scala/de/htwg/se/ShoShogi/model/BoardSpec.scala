package de.htwg.se.ShoShogi.model

import org.scalatest._
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class BoardSpec extends WordSpec with Matchers {
  "A Board is the playingfield of Shogi. A Board" when {
    "to be constructed" should {
      val smallBoard = new Board[Piece](1, new EmptyPiece)
      val biggerBoard = new Board[Piece](2, new EmptyPiece)
      val board = new Board[Piece](9, new EmptyPiece)
      "be creted with the lenght of its edges as size. Testing size 1, 2 and 9" in {
        smallBoard.size should be(1)
        biggerBoard.size should be(4)
        board.size should be(81)
      }
      "give access to its cells" in {
        smallBoard.cell(0, 0) should be(Some( ))
        biggerBoard.cell(0, 0) should be(Some( ))
        biggerBoard.cell(0, 1) should be(Some( ))
        biggerBoard.cell(1, 0) should be(Some( ))
        biggerBoard.cell(1, 1) should be(Some( ))
      }
    }
  }
}
