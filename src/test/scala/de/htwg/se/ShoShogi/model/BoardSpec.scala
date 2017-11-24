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
      "be created with the lenght of its edges as size. Testing size 1, 2 and 9" in {
        smallBoard.size should be(1)
        biggerBoard.size should be(2)
        board.size should be(9)
      }
      "give access to its cells" in {
        smallBoard.cell(0, 0) should be(Some(smallBoard.rows(0)(0)))
        biggerBoard.cell(0, 0) should be(Some(biggerBoard.rows(0)(0)))
        biggerBoard.cell(0, 1) should be(Some(biggerBoard.rows(0)(1)))
        biggerBoard.cell(1, 0) should be(Some(biggerBoard.rows(1)(0)))
        biggerBoard.cell(1, 1) should be(Some(biggerBoard.rows(1)(1)))
      }
    }
    "using an actuall playfield" should {
      var board = new Board[Piece](9, new EmptyPiece)
      val player_1 = Player("Nick", true)
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
      "replace cells with Pieces" in {
        board.rows(0)(0) should be(Lancer(player_1))
        board.rows(0)(1) should be(Knight(player_1))
        board.rows(0)(2) should be(SilverGeneral(player_1))
        board.rows(0)(3) should be(GoldenGeneral(player_1))
        board.rows(0)(4) should be(King(player_1))
        board.rows(0)(5) should be(GoldenGeneral(player_1))
        board.rows(0)(6) should be(SilverGeneral(player_1))
        board.rows(0)(7) should be(Knight(player_1))
        board.rows(0)(8) should be(Lancer(player_1))
        board.rows(1)(1) should be(Bishop(player_1))
        board.rows(1)(7) should be(Rook(player_1))
        board.rows(2)(0) should be(Pawn(player_1))
        board.rows(2)(8) should be(Pawn(player_1))
      }
      "have a nice String representation" in {

      }

    }
  }
}
