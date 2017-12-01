package de.htwg.se.ShoShogi.model

import de.htwg.se.ShoShogi.ShoShogi.boardSize
import de.htwg.se.ShoShogi.controller.Controller
import de.htwg.se.ShoShogi.util.Observer

import scala.language.reflectiveCalls
import org.junit.runner.RunWith
import org.scalatest.{Matchers, WordSpec}
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class ControllerSpec extends WordSpec with Matchers {
  "A Controller" when {
    "" should {
      val controller = new Controller(new Board(boardSize, new EmptyPiece), Player("Player1", true), Player("Player2", false))

      "notify its Observer after creation of emptyBoard" in {
        controller.createEmptyBoard()
        controller.board.size should be(9)
      }
      "notify its Observer after creation of playfield" in {
        controller.createNewBoard()
        controller.board.size should be(9)
      }
      "remove from Observerlist" in {
        val i = controller.subscribers.length
        controller.subscribers.length should be(i - 1)
      }
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
