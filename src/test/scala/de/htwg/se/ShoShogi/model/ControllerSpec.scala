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
    "observed by an Ovserver" should {
      val controller = new Controller(new Board(boardSize, new EmptyPiece), Player("Player1", true), Player("Player2", false))
      val observer = new Observer {
        var updated: Boolean = false
        def isUpdated: Boolean = updated
        override def update: Unit = updated = true
      }
      controller.add(observer)
      "notify its Observer after creation of emptyBoard" in {
        controller.createEmptyBoard()
        observer.updated should be(true)
        controller.board.size should be(9)
      }
      "notify its Observer after creation of playfield" in {
        controller.createNewBoard()
        observer.updated should be(true)
        controller.board.size should be(9)
      }
      "remove from Observerlist" in {
        val i = controller.subscribers.length
        controller.remove(observer)
        controller.subscribers.length should be(i-1)
      }
    }
  }
}
