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
  //TODO: Testing for false in movePiece
  "Controller" when {
    "called printPossibleMoves" should {
      "print for \"pmv 0c\"" in {
        controller.createNewBoard()
        controller.possibleMoves(0, 2) should be(List[(Int, Int)]((0, 3)))
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
        controller.possibleMoves(-1, 9) should be(List())
        controller.possibleMoves(9, -1) should be(List())
      }
    }
  }
  "Controller" when {
    "called boardToString" should {
      "create an String of the empty Board" in {
        controller.createEmptyBoard()
        controller.boardToString() should be(
          "Captured Player 1: \n" +
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
            "------------------------------------------------\n" +
            "Captured Player 2: \n"
        )
      }
    }
  }
  "Controller" when {
    "called boardToString" should {
      "create an String of the filled Board" in {
        controller.createNewBoard()
        controller.boardToString() should be(
          "Captured Player 1: \n" +
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
            "------------------------------------------------\n" +
            "Captured Player 2: \n"
        )
      }
    }
  }
  "Controller" when {
    "called boardToString" should {
      "create an String of the filled Board with captured" in {
        controller.createNewBoard()
        controller.movePiece((6, 2), (6, 3)) should be(true)
        controller.movePiece((8, 6), (8, 5)) should be(true)
        controller.movePiece((7, 1), (2, 6)) should be(true)
        controller.boardToString() should be(
          "Captured Player 1: P    \n" +
            "   0    1    2    3    4    5    6    7    8 \n \n" +
            "------------------------------------------------\n " +
            "| L  | KN | SG | GG | K  | GG | SG | KN | L  | \ta\n" +
            "------------------------------------------------\n " +
            "|    | R  |    |    |    |    |    |    |    | \tb\n" +
            "------------------------------------------------\n " +
            "| P  | P  | P  | P  | P  | P  |    | P  | P  | \tc\n" +
            "------------------------------------------------\n " +
            "|    |    |    |    |    |    | P  |    |    | \td\n" +
            "------------------------------------------------\n " +
            "|    |    |    |    |    |    |    |    |    | \te\n" +
            "------------------------------------------------\n " +
            "|    |    |    |    |    |    |    |    | P  | \tf\n" +
            "------------------------------------------------\n " +
            "| P  | P  | B  | P  | P  | P  | P  | P  |    | \tg\n" +
            "------------------------------------------------\n " +
            "|    | B  |    |    |    |    |    | R  |    | \th\n" +
            "------------------------------------------------\n " +
            "| L  | KN | SG | GG | K  | GG | SG | KN | L  | \ti\n" +
            "------------------------------------------------\n" +
            "Captured Player 2: \n"
        )
      }
    }
  }
  "Controller" when {
    "called boardToString" should {
      "create an String of the filled Board with captured both" in {
        controller.createNewBoard()
        controller.movePiece((8, 6), (8, 5)) should be(true)
        controller.movePiece((6, 2), (6, 3)) should be(true)
        controller.movePiece((8, 5), (8, 4)) should be(true)
        controller.movePiece((7, 1), (2, 6)) should be(true)
        controller.movePiece((1, 7), (2, 6)) should be(true)
        controller.boardToString() should be(
          "Captured Player 1: P    \n" +
            "   0    1    2    3    4    5    6    7    8 \n \n" +
            "------------------------------------------------\n " +
            "| L  | KN | SG | GG | K  | GG | SG | KN | L  | \ta\n" +
            "------------------------------------------------\n " +
            "|    | R  |    |    |    |    |    |    |    | \tb\n" +
            "------------------------------------------------\n " +
            "| P  | P  | P  | P  | P  | P  |    | P  | P  | \tc\n" +
            "------------------------------------------------\n " +
            "|    |    |    |    |    |    | P  |    |    | \td\n" +
            "------------------------------------------------\n " +
            "|    |    |    |    |    |    |    |    | P  | \te\n" +
            "------------------------------------------------\n " +
            "|    |    |    |    |    |    |    |    |    | \tf\n" +
            "------------------------------------------------\n " +
            "| P  | P  | B  | P  | P  | P  | P  | P  |    | \tg\n" +
            "------------------------------------------------\n " +
            "|    |    |    |    |    |    |    | R  |    | \th\n" +
            "------------------------------------------------\n " +
            "| L  | KN | SG | GG | K  | GG | SG | KN | L  | \ti\n" +
            "------------------------------------------------\n" +
            "Captured Player 2: B    \n"
        )
      }
    }
  }
  "Controller" when {
    "called movePiece" should {
      "return false if the Player tries to move enemy piece" in {
        controller.movePiece((0, 8), (0, 7)) should be(false)
        controller.movePiece((1, 8), (1, 7)) should be(false)
        controller.movePiece((2, 8), (2, 7)) should be(false)
        controller.movePiece((3, 8), (3, 7)) should be(false)
        controller.movePiece((4, 8), (4, 7)) should be(false)
        controller.movePiece((5, 8), (5, 7)) should be(false)
        controller.movePiece((6, 8), (6, 7)) should be(false)
        controller.movePiece((7, 8), (7, 7)) should be(false)
        controller.movePiece((8, 8), (8, 7)) should be(false)
        controller.movePiece((0, 6), (0, 5)) should be(false)
      }
    }
  }
  "Controller" when {
    "called movePiece" should {
      "return false, if the destination is invalide" in {
        controller.createNewBoard()
        controller.movePiece((1, 7), (0, 0)) should be(false)
        controller.movePiece((1, 7), (0, 1)) should be(false)
        controller.movePiece((1, 7), (0, 2)) should be(false)
        controller.movePiece((1, 7), (0, 3)) should be(false)
        controller.movePiece((1, 7), (0, 4)) should be(false)
        controller.movePiece((1, 7), (0, 5)) should be(false)
        controller.movePiece((1, 7), (0, 6)) should be(false)
        controller.movePiece((1, 7), (0, 7)) should be(false)
        controller.movePiece((1, 7), (0, 8)) should be(false)

        controller.movePiece((1, 7), (1, 0)) should be(false)
        controller.movePiece((1, 7), (1, 1)) should be(false)
        controller.movePiece((1, 7), (1, 2)) should be(false)
        controller.movePiece((1, 7), (1, 3)) should be(false)
        controller.movePiece((1, 7), (1, 4)) should be(false)
        controller.movePiece((1, 7), (1, 5)) should be(false)
        controller.movePiece((1, 7), (1, 6)) should be(false)
        controller.movePiece((1, 7), (1, 7)) should be(false)
        controller.movePiece((1, 7), (1, 8)) should be(false)

        controller.movePiece((1, 7), (2, 0)) should be(false)
        controller.movePiece((1, 7), (2, 1)) should be(false)
        controller.movePiece((1, 7), (2, 2)) should be(false)
        controller.movePiece((1, 7), (2, 3)) should be(false)
        controller.movePiece((1, 7), (2, 4)) should be(false)
        controller.movePiece((1, 7), (2, 5)) should be(false)
        controller.movePiece((1, 7), (2, 6)) should be(false)
        controller.movePiece((1, 7), (2, 7)) should be(false)
        controller.movePiece((1, 7), (2, 8)) should be(false)

        controller.movePiece((1, 7), (3, 0)) should be(false)
        controller.movePiece((1, 7), (3, 1)) should be(false)
        controller.movePiece((1, 7), (3, 2)) should be(false)
        controller.movePiece((1, 7), (3, 3)) should be(false)
        controller.movePiece((1, 7), (3, 4)) should be(false)
        controller.movePiece((1, 7), (3, 5)) should be(false)
        controller.movePiece((1, 7), (3, 6)) should be(false)
        controller.movePiece((1, 7), (3, 7)) should be(false)
        controller.movePiece((1, 7), (3, 8)) should be(false)

        controller.movePiece((1, 7), (4, 0)) should be(false)
        controller.movePiece((1, 7), (4, 1)) should be(false)
        controller.movePiece((1, 7), (4, 2)) should be(false)
        controller.movePiece((1, 7), (4, 3)) should be(false)
        controller.movePiece((1, 7), (4, 4)) should be(false)
        controller.movePiece((1, 7), (4, 5)) should be(false)
        controller.movePiece((1, 7), (4, 6)) should be(false)
        controller.movePiece((1, 7), (4, 7)) should be(false)
        controller.movePiece((1, 7), (4, 8)) should be(false)

        controller.movePiece((1, 7), (5, 0)) should be(false)
        controller.movePiece((1, 7), (5, 1)) should be(false)
        controller.movePiece((1, 7), (5, 2)) should be(false)
        controller.movePiece((1, 7), (5, 3)) should be(false)
        controller.movePiece((1, 7), (5, 4)) should be(false)
        controller.movePiece((1, 7), (5, 5)) should be(false)
        controller.movePiece((1, 7), (5, 6)) should be(false)
        controller.movePiece((1, 7), (5, 7)) should be(false)
        controller.movePiece((1, 7), (5, 8)) should be(false)

        controller.movePiece((1, 7), (6, 0)) should be(false)
        controller.movePiece((1, 7), (6, 1)) should be(false)
        controller.movePiece((1, 7), (6, 2)) should be(false)
        controller.movePiece((1, 7), (6, 3)) should be(false)
        controller.movePiece((1, 7), (6, 4)) should be(false)
        controller.movePiece((1, 7), (6, 5)) should be(false)
        controller.movePiece((1, 7), (6, 6)) should be(false)
        controller.movePiece((1, 7), (6, 7)) should be(false)
        controller.movePiece((1, 7), (6, 8)) should be(false)

        controller.movePiece((1, 7), (7, 0)) should be(false)
        controller.movePiece((1, 7), (7, 1)) should be(false)
        controller.movePiece((1, 7), (7, 2)) should be(false)
        controller.movePiece((1, 7), (7, 3)) should be(false)
        controller.movePiece((1, 7), (7, 4)) should be(false)
        controller.movePiece((1, 7), (7, 5)) should be(false)
        controller.movePiece((1, 7), (7, 6)) should be(false)
        controller.movePiece((1, 7), (7, 7)) should be(false)
        controller.movePiece((1, 7), (7, 8)) should be(false)

        controller.movePiece((1, 7), (8, 0)) should be(false)
        controller.movePiece((1, 7), (8, 1)) should be(false)
        controller.movePiece((1, 7), (8, 2)) should be(false)
        controller.movePiece((1, 7), (8, 3)) should be(false)
        controller.movePiece((1, 7), (8, 4)) should be(false)
        controller.movePiece((1, 7), (8, 5)) should be(false)
        controller.movePiece((1, 7), (8, 6)) should be(false)
        controller.movePiece((1, 7), (8, 7)) should be(false)
        controller.movePiece((1, 7), (8, 8)) should be(false)
        controller.movePiece((1, 7), (8, -8)) should be(false)
      }
    }
  }
  "Controller" when {
    controller.createNewBoard()
    "called promotable" should {
      "return false if Pawn of Player 1 is not in Promotionzone" in {
        controller.promotable((0, 6), (0, 5)) should be(false)
      }
      "return false if Pawn of Player 2 is not in Promotionzone" in {
        controller.promotable((0, 2), (0, 3)) should be(false)
      }
      "return true if Pawn of Player 1 is in Promotionzone" in {
        controller.promotable((0, 6), (0, 2)) should be(true)
      }
      "return true if Pawn of Player 2 is in Promotionzone" in {
        controller.promotable((0, 2), (0, 6)) should be(true)
      }
      "return false if no Piece was selected in the Algorithmen" in {
        controller.promotable((8, 5), (8, 4)) should be(false)
      }
    }
    "called promotePiece" should {
      "return false if Piece of Player 1 has no Promotion" in {
        controller.promotePiece((8, 5)) should be(false)
      }
      "return false if Piece of Player 2 has no Promotion" in {
        controller.promotePiece((0, 5)) should be(false)
      }
      "return true if Piece of Player 1 has a Promotion" in {
        controller.promotePiece((0, 6)) should be(true)
      }
      "return true if Piece of Player 2 has a Promotion" in {
        controller.promotePiece((0, 2)) should be(true)
      }
      "return false because invalid cell" in {
        controller.promotePiece((0, -5)) should be(false)
      }
    }
  }
}
