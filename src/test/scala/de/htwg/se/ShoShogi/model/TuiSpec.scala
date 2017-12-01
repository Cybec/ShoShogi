package de.htwg.se.ShoShogi.model

import org.scalatest.{Matchers, WordSpec}
import de.htwg.se.ShoShogi.aview.Tui
import de.htwg.se.ShoShogi.controller.Controller
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class TuiSpec extends WordSpec with Matchers {
  "A Tui" when {
    "when used" should {
      val controller = new Controller(new Board(boardSize, new EmptyPiece), Player("Player1", true), Player("Player2", false))
      val tui = new Tui(controller)
      "print menu" in {
       // val string = tui.printInputMenu()
        //string should be("\n   0    1    2    3    4    5    6    7    8 \n \n------------------------------------------------\n |    |    |    |    |    |    |    |    |    | \ta\n------------------------------------------------\n |    |    |    |    |    |    |    |    |    | \tb\n------------------------------------------------\n |    |    |    |    |    |    |    |    |    | \tc\n------------------------------------------------\n |    |    |    |    |    |    |    |    |    | \td\n------------------------------------------------\n |    |    |    |    |    |    |    |    |    | \te\n------------------------------------------------\n |    |    |    |    |    |    |    |    |    | \tf\n------------------------------------------------\n |    |    |    |    |    |    |    |    |    | \tg\n------------------------------------------------\n |    |    |    |    |    |    |    |    |    | \th\n------------------------------------------------\n |    |    |    |    |    |    |    |    |    | \ti\n------------------------------------------------\nq: quit\n------\nn: new\n------")
      }
    }
  }
  val boardSize = 9
  val controller = new Controller(new Board(boardSize, new EmptyPiece), Player("Player1", true), Player("Player2", false))
  val tui = new Tui(controller)

  "A Tui" when {
    "parseArguments called" should {
      "give back \"pmv     0a  \"" in {
        tui.parseArguments(Array("pmv", " ", " ", " ", " ", " 0a", " ", " ")) should be(Some((0, 0)))
      }
      "give back \"pmv     8h  \"" in {
        tui.parseArguments(Array("pmv", " ", " ", " ", " ", " 8h", " ", " ")) should be(Some((8, 7)))
      }
      "give back \"pmv     4d  \"" in {
        tui.parseArguments(Array("pmv", " ", " ", " ", " ", " 4d", " ", " ")) should be(Some((4, 3)))
      }
      "give back \"pmv     0ca  \"" in {
        tui.parseArguments(Array("pmv", " ", " ", " ", " ", " 0ca", " ", " ")) should be(None)
      }
      "give back \"pmv     ca  \"" in {
        tui.parseArguments(Array("pmv", " ", " ", " ", " ", " ca", " ", " ")) should be(None)
      }
    }
    "printPossibleMoves called" should {
      "print for \"pmv 0a\"" in {
        tui.printPossibleMoves(controller.possibleMoves(0, 0)) should be("Possible moves: (0, 1)   ")
      }
    }
  }
}
