package de.htwg.se.ShoShogi.model

import de.htwg.se.ShoShogi.ShoShogi.boardSize
import de.htwg.se.ShoShogi.controller.Controller
import de.htwg.se.ShoShogi.util.Observer

import scala.language.reflectiveCalls
import org.junit.runner.RunWith
import org.scalatest.{ Matchers, WordSpec }
import org.scalatest.junit.JUnitRunner

//noinspection ScalaStyle
@RunWith(classOf[JUnitRunner])
class ControllerSpec extends WordSpec with Matchers {
  val controller = new Controller(new Board(boardSize, new EmptyPiece), Player("Player1", true), Player("Player2", false))

  "Controller" when {
    "called printPossibleMoves" should {
      "print for \"pmv 0a\"" in {
        val player_1 = Player("Player1", true)
        controller.board = controller.board.replaceCell(0, 0, Lancer(player_1))
        for (i <- 0 to 8) {
          controller.board = controller.board.replaceCell(i, 2, Pawn(player_1))
        }
        controller.possibleMoves(0, 0) should be(List[(Int, Int)]((0, 1)))
      }
    }
  }
  "Controller" when {
    "called printPossibleMoves" should {
      "print wrong cell numbers" in {
        controller.possibleMoves(-1, 0) should be(List())
        controller.possibleMoves(0, -1) should be(List())
        controller.possibleMoves(9, 0) should be(List())
        controller.possibleMoves(0, 9) should be(List())
      }
    }
  }
  "Controller" when {
    "called createEmptyBoard" should {
      "create an empty Board with size" in {
        controller.createEmptyBoard()
        controller.board.size should be(9)
      }
    }
  }
  "Controller" when {
    "called createNewBoard" should {
      "create an filled Board" in {
        controller.createNewBoard()

        controller.board.cell(0, 0).getOrElse("Error get cell") shouldBe a[Lancer]
        controller.board.cell(1, 0).getOrElse("Error get cell") shouldBe a[Knight]
        controller.board.cell(2, 0).getOrElse("Error get cell") shouldBe a[SilverGeneral]
        controller.board.cell(3, 0).getOrElse("Error get cell") shouldBe a[GoldenGeneral]
        controller.board.cell(4, 0).getOrElse("Error get cell") shouldBe a[King]
        controller.board.cell(5, 0).getOrElse("Error get cell") shouldBe a[GoldenGeneral]
        controller.board.cell(6, 0).getOrElse("Error get cell") shouldBe a[SilverGeneral]
        controller.board.cell(7, 0).getOrElse("Error get cell") shouldBe a[Knight]
        controller.board.cell(8, 0).getOrElse("Error get cell") shouldBe a[Lancer]
        controller.board.cell(7, 1).getOrElse("Error get cell") shouldBe a[Bishop]
        controller.board.cell(1, 1).getOrElse("Error get cell") shouldBe a[Rook]
        for (i <- 0 to 8) {
          controller.board.cell(i, 2).getOrElse("Error get cell") shouldBe a[Pawn]
        }
        controller.board.cell(0, 8).getOrElse("Error get cell") shouldBe a[Lancer]
        controller.board.cell(1, 8).getOrElse("Error get cell") shouldBe a[Knight]
        controller.board.cell(2, 8).getOrElse("Error get cell") shouldBe a[SilverGeneral]
        controller.board.cell(3, 8).getOrElse("Error get cell") shouldBe a[GoldenGeneral]
        controller.board.cell(4, 8).getOrElse("Error get cell") shouldBe a[King]
        controller.board.cell(5, 8).getOrElse("Error get cell") shouldBe a[GoldenGeneral]
        controller.board.cell(6, 8).getOrElse("Error get cell") shouldBe a[SilverGeneral]
        controller.board.cell(7, 8).getOrElse("Error get cell") shouldBe a[Knight]
        controller.board.cell(8, 8).getOrElse("Error get cell") shouldBe a[Lancer]
        controller.board.cell(1, 7).getOrElse("Error get cell") shouldBe a[Bishop]
        controller.board.cell(7, 7).getOrElse("Error get cell") shouldBe a[Rook]
        for (i <- 0 to 8) {
          controller.board.cell(i, 6).getOrElse("Error get cell") shouldBe a[Pawn]
        }
      }
    }
  }
  "Controller" when {
    "called boardToString" should {
      "create an String of the empty Board" in {
        controller.createEmptyBoard()
        controller.boardToString() should be(
          "   0    1    2    3    4    5    6    7    8 \n \n" +
            "------------------------------------------------\n " +
            "|    |    |    |    |    |    |    |    |    | \ta\n" +
            "------------------------------------------------\n " +
            "|    |    |    |    |    |    |    |    |    | \tb\n" +
            "------------------------------------------------\n " +
            "|    |    |    |    |    |    |    |    |    | \tc\n" +
            "------------------------------------------------\n " +
            "|    |    |    |    |    |    |    |    |    | \td\n" +
            "------------------------------------------------\n " +
            "|    |    |    |    |    |    |    |    |    | \te\n" +
            "------------------------------------------------\n " +
            "|    |    |    |    |    |    |    |    |    | \tf\n" +
            "------------------------------------------------\n " +
            "|    |    |    |    |    |    |    |    |    | \tg\n" +
            "------------------------------------------------\n " +
            "|    |    |    |    |    |    |    |    |    | \th\n" +
            "------------------------------------------------\n " +
            "|    |    |    |    |    |    |    |    |    | \ti\n" +
            "------------------------------------------------\n"
        )
      }
    }
  }
  "Controller" when {
    "called boardToString" should {
      "create an String of the filled Board" in {
        controller.createNewBoard()
        controller.boardToString() should be(
          "   0    1    2    3    4    5    6    7    8 \n \n" +
            "------------------------------------------------\n " +
            "| L  | KN | SG | GG | K  | GG | SG | KN | L  | \ta\n" +
            "------------------------------------------------\n " +
            "|    | R  |    |    |    |    |    | B  |    | \tb\n" +
            "------------------------------------------------\n " +
            "| P  | P  | P  | P  | P  | P  | P  | P  | P  | \tc\n" +
            "------------------------------------------------\n " +
            "|    |    |    |    |    |    |    |    |    | \td\n" +
            "------------------------------------------------\n " +
            "|    |    |    |    |    |    |    |    |    | \te\n" +
            "------------------------------------------------\n " +
            "|    |    |    |    |    |    |    |    |    | \tf\n" +
            "------------------------------------------------\n " +
            "| P  | P  | P  | P  | P  | P  | P  | P  | P  | \tg\n" +
            "------------------------------------------------\n " +
            "|    | B  |    |    |    |    |    | R  |    | \th\n" +
            "------------------------------------------------\n " +
            "| L  | KN | SG | GG | K  | GG | SG | KN | L  | \ti\n" +
            "------------------------------------------------\n"
        )
      }
    }
  }
}
