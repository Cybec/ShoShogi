package de.htwg.se.ShoShogi.model

import de.htwg.se.ShoShogi.ShoShogi.boardSize
import de.htwg.se.ShoShogi.controller.Controller
import de.htwg.se.ShoShogi.util.Observer

import scala.language.reflectiveCalls
import org.junit.runner.RunWith
import org.scalatest.{ Matchers, WordSpec }
import org.scalatest.junit.JUnitRunner

//TODO 1: Controller testen

@RunWith(classOf[JUnitRunner])
class ControllerSpec extends WordSpec with Matchers {
  "A Controller" when {
    "" should {
      val controller = new Controller(new Board(boardSize, new EmptyPiece), Player("Player1", true), Player("Player2", false))

      "printPossibleMoves called" should {
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
  }
}
