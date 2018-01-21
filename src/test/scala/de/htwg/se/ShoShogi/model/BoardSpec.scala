package de.htwg.se.ShoShogi.model

import com.google.inject.Guice
import com.google.inject.name.Names
import de.htwg.se.ShoShogi.ShoShogiModule
import de.htwg.se.ShoShogi.model.boardComponent.BoardInterface
import de.htwg.se.ShoShogi.model.boardComponent.boardBaseImpl.Board
import de.htwg.se.ShoShogi.model.pieceComponent.PieceInterface
import de.htwg.se.ShoShogi.model.pieceComponent.pieceBaseImpl._
import de.htwg.se.ShoShogi.model.playerComponent.Player
import net.codingwell.scalaguice.InjectorExtensions._
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
      val injector = Guice.createInjector(new ShoShogiModule)
      val board: BoardInterface = injector.instance[BoardInterface](Names.named("normal")).createNewBoard()
      val smallBoard = new Board(1, PieceFactory.apply(PiecesEnum.EmptyPiece, player_1.first))
      val biggerBoard = new Board(2, PieceFactory.apply(PiecesEnum.EmptyPiece, player_1.first))

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
        smallBoard.toString.contains(0)
      }
    }
    "using an actual Board" should {
      val player_1 = Player("Nick", true)
      var board = new Board(9, PieceFactory.apply(PiecesEnum.EmptyPiece, player_1.first))

      board = board.replaceCell(0, 0, PieceFactory.apply(PiecesEnum.Lancer, player_1.first))
      board = board.replaceCell(1, 0, PieceFactory.apply(PiecesEnum.Knight, player_1.first))
      board = board.replaceCell(2, 0, PieceFactory.apply(PiecesEnum.SilverGeneral, player_1.first))
      board = board.replaceCell(3, 0, PieceFactory.apply(PiecesEnum.GoldenGeneral, player_1.first))
      board = board.replaceCell(4, 0, PieceFactory.apply(PiecesEnum.King, player_1.first))
      board = board.replaceCell(5, 0, PieceFactory.apply(PiecesEnum.GoldenGeneral, player_1.first))
      board = board.replaceCell(6, 0, PieceFactory.apply(PiecesEnum.SilverGeneral, player_1.first))
      board = board.replaceCell(7, 0, PieceFactory.apply(PiecesEnum.Knight, player_1.first))
      board = board.replaceCell(8, 0, PieceFactory.apply(PiecesEnum.Lancer, player_1.first))
      board = board.replaceCell(7, 1, PieceFactory.apply(PiecesEnum.Bishop, player_1.first))
      board = board.replaceCell(1, 1, PieceFactory.apply(PiecesEnum.Rook, player_1.first))
      for (i <- 0 to 8) {
        board = board.replaceCell(i, 2, PieceFactory.apply(PiecesEnum.Pawn, player_1.first))
      }
      "replace cells with Pieces" in {
        board.board(0)(0) should be(PieceFactory.apply(PiecesEnum.Lancer, player_1.first))
        board.board(1)(0) should be(PieceFactory.apply(PiecesEnum.Knight, player_1.first))
        board.board(2)(0) should be(PieceFactory.apply(PiecesEnum.SilverGeneral, player_1.first))
        board.board(3)(0) should be(PieceFactory.apply(PiecesEnum.GoldenGeneral, player_1.first))
        board.board(4)(0) should be(PieceFactory.apply(PiecesEnum.King, player_1.first))
        board.board(5)(0) should be(PieceFactory.apply(PiecesEnum.GoldenGeneral, player_1.first))
        board.board(6)(0) should be(PieceFactory.apply(PiecesEnum.SilverGeneral, player_1.first))
        board.board(7)(0) should be(PieceFactory.apply(PiecesEnum.Knight, player_1.first))
        board.board(8)(0) should be(PieceFactory.apply(PiecesEnum.Lancer, player_1.first))
        board.board(7)(1) should be(PieceFactory.apply(PiecesEnum.Bishop, player_1.first))
        board.board(1)(1) should be(PieceFactory.apply(PiecesEnum.Rook, player_1.first))
        board.board(0)(2) should be(PieceFactory.apply(PiecesEnum.Pawn, player_1.first))
        board.board(8)(2) should be(PieceFactory.apply(PiecesEnum.Pawn, player_1.first))
      }
    }
  }

  "Board" when {
    "something added to container" should {
      "have pieces in both container" in {
        val player_1 = Player("Nick", true)
        val player_2 = Player("Mert", false)
        var board = new Board(9, PieceFactory.apply(PiecesEnum.EmptyPiece, player_1.first))

        board = board.addToPlayerContainer(player_1.first, PieceFactory.apply(PiecesEnum.Lancer, player_2.first))
        board = board.addToPlayerContainer(player_1.first, PieceFactory.apply(PiecesEnum.King, player_2.first))
        board = board.addToPlayerContainer(player_2.first, PieceFactory.apply(PiecesEnum.Lancer, player_2.first))
        board = board.addToPlayerContainer(player_2.first, PieceFactory.apply(PiecesEnum.King, player_1.first))

        board.getContainer() should be((
          ListBuffer(PieceFactory.apply(PiecesEnum.Lancer, player_1.first), PieceFactory.apply(PiecesEnum.King, player_1.first)),
          ListBuffer(PieceFactory.apply(PiecesEnum.Lancer, player_2.first), PieceFactory.apply(PiecesEnum.King, player_2.first))
        ))
      }
    }
  }

  "Board" when {
    "something removed from container" should {
      "have less pieces in both container" in {
        val player_1 = Player("Nick", true)
        val player_2 = Player("Mert", false)

        var board = new Board(9, PieceFactory.apply(PiecesEnum.EmptyPiece, player_1.first))
        var wantedPiece_0: PieceInterface = PieceFactory.apply(PiecesEnum.EmptyPiece, player_1.first)
        var wantedPiece_1: PieceInterface = PieceFactory.apply(PiecesEnum.EmptyPiece, player_1.first)

        board = board.addToPlayerContainer(player_1.first, PieceFactory.apply(PiecesEnum.Lancer, player_2.first))
        board = board.addToPlayerContainer(player_1.first, PieceFactory.apply(PiecesEnum.Lancer, player_2.first))
        board = board.addToPlayerContainer(player_1.first, PieceFactory.apply(PiecesEnum.King, player_2.first))
        board = board.addToPlayerContainer(player_1.first, PieceFactory.apply(PiecesEnum.King, player_2.first))
        board = board.addToPlayerContainer(player_2.first, PieceFactory.apply(PiecesEnum.Lancer, player_1.first))
        board = board.addToPlayerContainer(player_2.first, PieceFactory.apply(PiecesEnum.Lancer, player_1.first))
        board = board.addToPlayerContainer(player_2.first, PieceFactory.apply(PiecesEnum.King, player_1.first))
        board = board.addToPlayerContainer(player_2.first, PieceFactory.apply(PiecesEnum.King, player_1.first))

        board.getContainer() should be((
          ListBuffer(PieceFactory.apply(PiecesEnum.Lancer, player_1.first), PieceFactory.apply(PiecesEnum.Lancer, player_1.first), PieceFactory.apply(PiecesEnum.King, player_1.first), PieceFactory.apply(PiecesEnum.King, player_1.first)),
          ListBuffer(PieceFactory.apply(PiecesEnum.Lancer, player_2.first), PieceFactory.apply(PiecesEnum.Lancer, player_2.first), PieceFactory.apply(PiecesEnum.King, player_2.first), PieceFactory.apply(PiecesEnum.King, player_2.first))
        ))

        board.getFromPlayerContainer(player_1) {
          PieceFactory.isInstanceOfPiece(PiecesEnum.Lancer, _)
        } match {
          case Some((newBoard, piece)) =>
            board = newBoard
            wantedPiece_0 = piece
          case None =>
        }

        board.getFromPlayerContainer(player_2) {
          PieceFactory.isInstanceOfPiece(PiecesEnum.King, _)
        } match {
          case Some((newBoard, piece)) =>
            board = newBoard
            wantedPiece_1 = piece
          case None =>
        }

        board.getContainer() should be((
          ListBuffer(PieceFactory.apply(PiecesEnum.Lancer, player_1.first), PieceFactory.apply(PiecesEnum.King, player_1.first), PieceFactory.apply(PiecesEnum.King, player_1.first)),
          ListBuffer(PieceFactory.apply(PiecesEnum.Lancer, player_2.first), PieceFactory.apply(PiecesEnum.Lancer, player_2.first), PieceFactory.apply(PiecesEnum.King, player_2.first))
        ))

        PieceFactory.isInstanceOfPiece(PiecesEnum.Lancer, wantedPiece_0) should be(true)
        PieceFactory.isInstanceOfPiece(PiecesEnum.King, wantedPiece_1) should be(true)
      }
      "return None if there is no such piece" in {
        val player_1 = Player("Nick", true)
        val player_2 = Player("Mert", false)
        var board = new Board(9, PieceFactory.apply(PiecesEnum.EmptyPiece, player_1.first))
        var wantedPiece_0: PieceInterface = PieceFactory.apply(PiecesEnum.EmptyPiece, player_1.first)
        var wantedPiece_1: PieceInterface = PieceFactory.apply(PiecesEnum.EmptyPiece, player_1.first)

        board = board.addToPlayerContainer(player_1.first, PieceFactory.apply(PiecesEnum.Lancer, player_2.first))
        board = board.addToPlayerContainer(player_1.first, PieceFactory.apply(PiecesEnum.Lancer, player_2.first))
        board = board.addToPlayerContainer(player_1.first, PieceFactory.apply(PiecesEnum.King, player_2.first))
        board = board.addToPlayerContainer(player_1.first, PieceFactory.apply(PiecesEnum.King, player_2.first))
        board = board.addToPlayerContainer(player_2.first, PieceFactory.apply(PiecesEnum.Lancer, player_1.first))
        board = board.addToPlayerContainer(player_2.first, PieceFactory.apply(PiecesEnum.Lancer, player_1.first))
        board = board.addToPlayerContainer(player_2.first, PieceFactory.apply(PiecesEnum.King, player_1.first))
        board = board.addToPlayerContainer(player_2.first, PieceFactory.apply(PiecesEnum.King, player_1.first))
        board.addToPlayerContainer(player_2.first, PieceFactory.apply(PiecesEnum.EmptyPiece, player_1.first))

        board.getContainer() should be((
          ListBuffer(PieceFactory.apply(PiecesEnum.Lancer, player_1.first), PieceFactory.apply(PiecesEnum.Lancer, player_1.first), PieceFactory.apply(PiecesEnum.King, player_1.first), PieceFactory.apply(PiecesEnum.King, player_1.first)),
          ListBuffer(PieceFactory.apply(PiecesEnum.Lancer, player_2.first), PieceFactory.apply(PiecesEnum.Lancer, player_2.first), PieceFactory.apply(PiecesEnum.King, player_2.first), PieceFactory.apply(PiecesEnum.King, player_2.first))
        ))

        board.getFromPlayerContainer(player_1) {
          PieceFactory.isInstanceOfPiece(PiecesEnum.King, _)
        } match {
          case Some((newBoard, piece)) =>
            board = newBoard
            wantedPiece_0 = piece
          case None =>
        }

        board.getFromPlayerContainer(player_2) {
          PieceFactory.isInstanceOfPiece(PiecesEnum.Pawn, _)
        } match {
          case Some((newBoard, piece)) =>
            board = newBoard
            wantedPiece_1 = piece
          case None =>
        }

        board.getContainer() should be((
          ListBuffer(PieceFactory.apply(PiecesEnum.Lancer, player_1.first), PieceFactory.apply(PiecesEnum.Lancer, player_1.first), PieceFactory.apply(PiecesEnum.King, player_1.first)),
          ListBuffer(PieceFactory.apply(PiecesEnum.Lancer, player_2.first), PieceFactory.apply(PiecesEnum.Lancer, player_2.first), PieceFactory.apply(PiecesEnum.King, player_2.first), PieceFactory.apply(PiecesEnum.King, player_2.first))
        ))

        PieceFactory.isInstanceOfPiece(PiecesEnum.King, wantedPiece_0) should be(true)
        PieceFactory.isInstanceOfPiece(PiecesEnum.EmptyPiece, wantedPiece_1) should be(true)

      }
    }
  }

  "Board" when {
    val player_1 = Player("Nick", true)
    val player_2 = Player("Mert", false)
    var board = new Board(9, PieceFactory.apply(PiecesEnum.EmptyPiece, player_1.first))
    val pawn = PieceFactory.apply(PiecesEnum.Pawn, player_1.first)
    val lancer = PieceFactory.apply(PiecesEnum.Lancer, player_1.first)
    board = board.replaceCell(0, 4, pawn)
    board = board.replaceCell(0, 7, lancer)
    "checked if Piece is in Column" should {
      "have Piece Pawn and Lancer" in {
        board.getPiecesInColumn(0, true) should be(List[PieceInterface](pawn, lancer))
        board.getPiecesInColumn(3, true) should be(List[PieceInterface]())
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
        var board = new Board(2, PieceFactory.apply(PiecesEnum.EmptyPiece, player_1.first))
        val pawn = PieceFactory.apply(PiecesEnum.Pawn, player_2.first)
        val empty = PieceFactory.apply(PiecesEnum.EmptyPiece, player_1.first)
        board = board.replaceCell(0, 0, pawn)
        board.cell(0, 0) should be(Some(pawn))
        board.toArray should be(Array[Array[PieceInterface]](Array(pawn, empty), Array(empty, empty)))
      }
    }
  }
  "A Board" when {
    "called setContainer" should {
      "return a Board with new Containers" in {
        val player_1 = Player("Nick", true)
        val player_2 = Player("Mert", false)
        var board = new Board(9, PieceFactory.apply(PiecesEnum.EmptyPiece, player_1.first))
        val pawn = PieceFactory.apply(PiecesEnum.Pawn, player_1.first)
        val pawn2 = PieceFactory.apply(PiecesEnum.Pawn, player_2.first)
        val lancer = PieceFactory.apply(PiecesEnum.Lancer, player_1.first)
        val lancer2 = PieceFactory.apply(PiecesEnum.Lancer, player_2.first)

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
  "A Board" when {
    "called createNewBoard" should {
      "create a Board with only Empty Pieces" in {
        val player_1 = Player("", true)
        var board = new Board(9, PieceFactory.apply(PiecesEnum.EmptyPiece, player_1.first))
        board.createNewBoard.toString should be(
          "Captured: \n" +
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
            "Captured: \n"
        )
      }
    }

  }
}
