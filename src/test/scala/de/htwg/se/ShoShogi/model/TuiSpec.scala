package de.htwg.se.ShoShogi.model

import org.scalatest.{Matchers, WordSpec}
import de.htwg.se.ShoShogi.aview.Tui
import de.htwg.se.ShoShogi.controller.Controller
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class TuiSpec extends WordSpec with Matchers {
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
  }
}
