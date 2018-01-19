package de.htwg.se.ShoShogi.model

import de.htwg.se.ShoShogi.model.boardComponent.boardBaseImpl.Board
import de.htwg.se.ShoShogi.model.pieceComponent._
import de.htwg.se.ShoShogi.model.playerComponent.Player
import org.junit.runner.RunWith
import org.scalatest._
import org.scalatest.junit.JUnitRunner

import scala.collection.mutable.ListBuffer

//noinspection ScalaStyle
@RunWith(classOf[JUnitRunner])
class BoardSpec extends WordSpec with Matchers {
  "A Board is the playing field of Shogi. A Board" when {
    "to be constructed" should {
      val player_1 = Player("Nick", true)
      val smallBoard = new Board(1, pieceFactory.apply("EmptyPiece", player_1))
      val biggerBoard = new Board(2, pieceFactory.apply("EmptyPiece", player_1))
      val board = new Board(9, pieceFactory.apply("EmptyPiece", player_1))
      "be created with the length of its edges as size. Testing size 1, 2 and 9" in {
        smallBoard.size should be(1)
        biggerBoard.size should be(2)
        board.size should be(9)
      }
      "give access to its cells" in {
        smallBoard.cell(0, 0) should be(Some(smallBoard.board(0)(0)))
        biggerBoard.cell(0, 0) should be(Some(biggerBoard.board(0)(0)))
        biggerBoard.cell(0, 1) should be(Some(biggerBoard.board(0)(1)))
        biggerBoard.cell(1, 0) should be(Some(biggerBoard.board(1)(0)))
        biggerBoard.cell(1, 1) should be(Some(biggerBoard.board(1)(1)))
      }
      "wrong access on cells" in {
        smallBoard.cell(-1, 0) should be(None)
      }
      "have a nice String representation" in {
        smallBoard.toString.contains(0) should be()
      }
    }
    "using an actual Board" should {
      val player_1 = Player("Nick", true)
      var board = new Board(9, pieceFactory.apply("EmptyPiece", player_1))

      board = board.replaceCell(0, 0, pieceFactory.apply("Lancer", player_1))
      board = board.replaceCell(1, 0, pieceFactory.apply("Knight", player_1))
      board = board.replaceCell(2, 0, pieceFactory.apply("SilverGeneral", player_1))
      board = board.replaceCell(3, 0, pieceFactory.apply("GoldenGeneral", player_1))
      board = board.replaceCell(4, 0, pieceFactory.apply("King", player_1))
      board = board.replaceCell(5, 0, pieceFactory.apply("GoldenGeneral", player_1))
      board = board.replaceCell(6, 0, pieceFactory.apply("SilverGeneral", player_1))
      board = board.replaceCell(7, 0, pieceFactory.apply("Knight", player_1))
      board = board.replaceCell(8, 0, pieceFactory.apply("Lancer", player_1))
      board = board.replaceCell(7, 1, pieceFactory.apply("Bishop", player_1))
      board = board.replaceCell(1, 1, pieceFactory.apply("Rook", player_1))
      for (i <- 0 to 8) {
        board = board.replaceCell(i, 2, pieceFactory.apply("Pawn", player_1))
      }
      "replace cells with Pieces" in {
        board.board(0)(0) should be(pieceFactory.apply("Lancer", player_1))
        board.board(1)(0) should be(pieceFactory.apply("Knight", player_1))
        board.board(2)(0) should be(pieceFactory.apply("SilverGeneral", player_1))
        board.board(3)(0) should be(pieceFactory.apply("GoldenGeneral", player_1))
        board.board(4)(0) should be(pieceFactory.apply("King", player_1))
        board.board(5)(0) should be(pieceFactory.apply("GoldenGeneral", player_1))
        board.board(6)(0) should be(pieceFactory.apply("SilverGeneral", player_1))
        board.board(7)(0) should be(pieceFactory.apply("Knight", player_1))
        board.board(8)(0) should be(pieceFactory.apply("Lancer", player_1))
        board.board(7)(1) should be(pieceFactory.apply("Bishop", player_1))
        board.board(1)(1) should be(pieceFactory.apply("Rook", player_1))
        board.board(0)(2) should be(pieceFactory.apply("Pawn", player_1))
        board.board(8)(2) should be(pieceFactory.apply("Pawn", player_1))
      }
    }
  }

  "Board" when {
    "something added to container" should {
      val player_1 = Player("Nick", true)
      val player_2 = Player("Mert", false)
      var board = new Board(9, pieceFactory.apply("EmptyPiece", player_1))
      "have pieces in both container" in {
        board = board.addToPlayerContainer(player_1, pieceFactory.apply("Lancer", player_2))
        board = board.addToPlayerContainer(player_1, pieceFactory.apply("King", player_2))
        board = board.addToPlayerContainer(player_2, pieceFactory.apply("Lancer", player_2))
        board = board.addToPlayerContainer(player_2, pieceFactory.apply("King", player_1))
        board.getContainer() should be((
          ListBuffer(pieceFactory.apply("Lancer", player_1), pieceFactory.apply("King", player_1)),
          ListBuffer(pieceFactory.apply("Lancer", player_2), pieceFactory.apply("King", player_2))
        ))
      }
      "give back same board without changes when Empty Piece wants to be added to container" in {
        board = board.addToPlayerContainer(player_1, pieceFactory.apply("EmptyPiece", player_1))
        board.getContainer() should be((
          ListBuffer(pieceFactory.apply("Lancer", player_1), pieceFactory.apply("King", player_1)),
          ListBuffer(pieceFactory.apply("Lancer", player_2), pieceFactory.apply("King", player_2))
        ))
      }
    }
  }

  "Board" when {
    "something removed from container" should {
      "have less pieces in both container" in {
        val player_1 = Player("Nick", true)
        val player_2 = Player("Mert", false)

        var board = new Board(9, pieceFactory.apply("EmptyPiece", player_1))
        var wantedPiece_0: Piece = pieceFactory.apply("EmptyPiece", player_1)
        var wantedPiece_1: Piece = pieceFactory.apply("EmptyPiece", player_1)

        board = board.addToPlayerContainer(player_1, pieceFactory.apply("Lancer", player_2))
        board = board.addToPlayerContainer(player_1, pieceFactory.apply("Lancer", player_2))
        board = board.addToPlayerContainer(player_1, pieceFactory.apply("King", player_2))
        board = board.addToPlayerContainer(player_1, pieceFactory.apply("King", player_2))
        board = board.addToPlayerContainer(player_2, pieceFactory.apply("Lancer", player_1))
        board = board.addToPlayerContainer(player_2, pieceFactory.apply("Lancer", player_1))
        board = board.addToPlayerContainer(player_2, pieceFactory.apply("King", player_1))
        board = board.addToPlayerContainer(player_2, pieceFactory.apply("King", player_1))

        board.getContainer() should be((
          ListBuffer(pieceFactory.apply("Lancer", player_1), pieceFactory.apply("Lancer", player_1), pieceFactory.apply("King", player_1), pieceFactory.apply("King", player_1)),
          ListBuffer(pieceFactory.apply("Lancer", player_2), pieceFactory.apply("Lancer", player_2), pieceFactory.apply("King", player_2), pieceFactory.apply("King", player_2))
        ))

        board.getFromPlayerContainer(player_1) {
          _.isInstanceOf[Lancer]
        } match {
          case Some((newBoard, piece)) =>
            board = newBoard
            wantedPiece_0 = piece
          case None =>
        }

        board.getFromPlayerContainer(player_2) {
          _.isInstanceOf[King]
        } match {
          case Some((newBoard, piece)) =>
            board = newBoard
            wantedPiece_1 = piece
          case None =>
        }

        board.getContainer() should be((
          ListBuffer(pieceFactory.apply("Lancer", player_1), pieceFactory.apply("King", player_1), pieceFactory.apply("King", player_1)),
          ListBuffer(pieceFactory.apply("Lancer", player_2), pieceFactory.apply("Lancer", player_2), pieceFactory.apply("King", player_2))
        ))

        wantedPiece_0 shouldBe a[Lancer]
        wantedPiece_1 shouldBe a[King]
      }
      "return None if there is no such piece" in {
        val player_1 = Player("Nick", true)
        val player_2 = Player("Mert", false)
        var board = new Board(9, pieceFactory.apply("EmptyPiece", player_1))
        var wantedPiece_0: Piece = pieceFactory.apply("EmptyPiece", player_1)
        var wantedPiece_1: Piece = pieceFactory.apply("EmptyPiece", player_1)

        board = board.addToPlayerContainer(player_1, pieceFactory.apply("Lancer", player_2))
        board = board.addToPlayerContainer(player_1, pieceFactory.apply("Lancer", player_2))
        board = board.addToPlayerContainer(player_1, pieceFactory.apply("King", player_2))
        board = board.addToPlayerContainer(player_1, pieceFactory.apply("King", player_2))
        board = board.addToPlayerContainer(player_2, pieceFactory.apply("Lancer", player_1))
        board = board.addToPlayerContainer(player_2, pieceFactory.apply("Lancer", player_1))
        board = board.addToPlayerContainer(player_2, pieceFactory.apply("King", player_1))
        board = board.addToPlayerContainer(player_2, pieceFactory.apply("King", player_1))

        board.getContainer() should be((
          ListBuffer(pieceFactory.apply("Lancer", player_1), pieceFactory.apply("Lancer", player_1), pieceFactory.apply("King", player_1), pieceFactory.apply("King", player_1)),
          ListBuffer(pieceFactory.apply("Lancer", player_2), pieceFactory.apply("Lancer", player_2), pieceFactory.apply("King", player_2), pieceFactory.apply("King", player_2))
        ))

        board.getFromPlayerContainer(player_1) {
          _.isInstanceOf[Knight]
        } match {
          case Some((newBoard, piece)) =>
            board = newBoard
            wantedPiece_0 = piece
          case None =>
        }

        board.getFromPlayerContainer(player_2) {
          _.isInstanceOf[Pawn]
        } match {
          case Some((newBoard, piece)) =>
            board = newBoard
            wantedPiece_1 = piece
          case None =>
        }

        board.getContainer() should be((
          ListBuffer(pieceFactory.apply("Lancer", player_1), pieceFactory.apply("Lancer", player_1), pieceFactory.apply("King", player_1), pieceFactory.apply("King", player_1)),
          ListBuffer(pieceFactory.apply("Lancer", player_2), pieceFactory.apply("Lancer", player_2), pieceFactory.apply("King", player_2), pieceFactory.apply("King", player_2))
        ))

        wantedPiece_0 shouldBe a[EmptyPiece]
        wantedPiece_1 shouldBe a[EmptyPiece]

      }
    }
  }
  "Board" when {
    val player_1 = Player("Nick", true)
    val player_2 = Player("Mert", false)
    var board = new Board(9, pieceFactory.apply("EmptyPiece", player_1))
    val pawn = pieceFactory.apply("Pawn", player_1)
    val lancer = pieceFactory.apply("Lancer", player_1)
    board = board.replaceCell(0, 4, pawn)
    board = board.replaceCell(0, 7, lancer)
    "checked if Piece is in Column" should {
      "have Piece Pawn and Lancer" in {
        board.getPiecesInColumn(0, true) should be(List[Piece](pawn, lancer))
        board.getPiecesInColumn(3, true) should be(List[Piece]())
      }
      "give nothing" in {
        board.getPiecesInColumn(10, true) should be(List[(Int, Int)]())
      }
    }
    "checked which fields in a column are empty" should {
      "be (0, 0), (0, 1), (0, 2), (0, 3), (0, 5), (0, 6), (0, 8) " in {
        board.getEmptyCellsInColumn(0, (0, 8)) should be(List[(Int, Int)]((0, 0), (0, 1), (0, 2), (0, 3), (0, 5), (0, 6), (0, 8)))
      }
      "have () when the column is not in the board" in {
        board.getEmptyCellsInColumn(10, (-4, -8)) should be(List[(Int, Int)]())
        board.getEmptyCellsInColumn(0, (0, 10)) should be(List[(Int, Int)]((0, 0), (0, 1), (0, 2), (0, 3), (0, 5), (0, 6), (0, 8)))
      }
    }
  }
  "Board" when {
    "called toArray" should {
      "return a two dimensional Array of all Pieces" in {
        val player_1 = Player("Nick", true)
        val player_2 = Player("Mert", false)
        var board = new Board(2, pieceFactory.apply("EmptyPiece", player_1))
        val pawn = pieceFactory.apply("Pawn", player_2)
        val empty = pieceFactory.apply("EmptyPiece", player_1)
        board = board.replaceCell(0, 0, pawn)
        board.cell(0, 0) should be(Some(pawn))
        board.toArray should be(Array[Array[Piece]](Array(pawn, empty), Array(empty, empty)))
      }
    }
  }
  "A Board" when {
    "called setContainer" should {
      "return a Board with new Containers" in {
        val player_1 = Player("Nick", true)
        val player_2 = Player("Mert", false)
        var board = new Board(9, pieceFactory.apply("EmptyPiece", player_1))
        val pawn = pieceFactory.apply("Pawn", player_1)
        val pawn2 = pieceFactory.apply("Pawn", player_2)
        val lancer = pieceFactory.apply("Lancer", player_1)
        val lancer2 = pieceFactory.apply("Lancer", player_2)

        board.getContainer() should be(List(), List())
        board.setContainer(List(pawn, lancer), List(pawn2, lancer2)).toString should be(
          "Captured: P°    L°    \n" +
            "    0     1     2     3     4     5     6     7     8 \n \n" +
            "---------------------------------------------------------\n " +
            "|     |     |     |     |     |     |     |     |     | \ta\n" +
            "---------------------------------------------------------\n " +
            "|     |     |     |     |     |     |     |     |     | \tb\n" +
            "---------------------------------------------------------\n " +
            "|     |     |     |     |     |     |     |     |     | \tc\n" +
            "---------------------------------------------------------\n " +
            "|     |     |     |     |     |     |     |     |     | \td\n" +
            "---------------------------------------------------------\n " +
            "|     |     |     |     |     |     |     |     |     | \te\n" +
            "---------------------------------------------------------\n " +
            "|     |     |     |     |     |     |     |     |     | \tf\n" +
            "---------------------------------------------------------\n " +
            "|     |     |     |     |     |     |     |     |     | \tg\n" +
            "---------------------------------------------------------\n " +
            "|     |     |     |     |     |     |     |     |     | \th\n" +
            "---------------------------------------------------------\n " +
            "|     |     |     |     |     |     |     |     |     | \ti\n" +
            "---------------------------------------------------------\n" +
            "Captured: P     L     \n"
        )
      }
    }
  }
}
