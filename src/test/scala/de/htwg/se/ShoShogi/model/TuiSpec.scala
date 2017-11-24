package de.htwg.se.ShoShogi.model

import de.htwg.se.ShoShogi.ShoShogi.{boardSize, controller}
import de.htwg.se.ShoShogi.aview.Tui
import de.htwg.se.ShoShogi.controller.Controller
import org.junit.runner.RunWith
import org.scalatest.{Matchers, WordSpec}
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
}
