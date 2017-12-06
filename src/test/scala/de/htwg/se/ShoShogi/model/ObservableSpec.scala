package de.htwg.se.ShoShogi.model

import com.sun.corba.se.impl.orb.ParserTable.TestContactInfoListFactory
import de.htwg.se.ShoShogi.ShoShogi.boardSize
import de.htwg.se.ShoShogi.controller.Controller
import de.htwg.se.ShoShogi.util.{ Observable, Observer }

import scala.language.reflectiveCalls
import org.junit.runner.RunWith
import org.scalatest.{ Matchers, WordSpec }
import org.scalatest.junit.JUnitRunner

//noinspection ScalaStyle
@RunWith(classOf[JUnitRunner])
class ObservableSpec extends WordSpec with Matchers {
  "Observer" when {
    "added" should {
      "have one subscriber" in {
        class TestController() extends Observable {

        }
        class TestTui(testController: TestController) extends Observer {
          testController.add(this)

          override def update: Unit = {}
        }
        val con = new TestController()
        val tui = new TestTui(con)
        con.subscribers.size should be(1)
      }
    }
  }
  "Observer" when {
    "removed" should {
      "have one less subscriber" in {
        class TestController() extends Observable {

        }
        class TestTui(testController: TestController) extends Observer {
          testController.add(this)

          def removeFromObserver(): Unit = testController.remove(this)

          override def update: Unit = {}
        }
        val con = new TestController()
        val tui = new TestTui(con)
        con.subscribers.size should be(1)
        tui.removeFromObserver()
        con.subscribers.size should be(0)
      }
    }
  }
  "Observer" when {
    "notified" should {
      "update all subscribers" in {
        class TestController() extends Observable {
          def updateSubscribers(): Unit = notifyObservers
        }
        class TestTui(testController: TestController) extends Observer {
          testController.add(this)
          var updated = false

          def removeFromObserver(): Unit = testController.remove(this)

          override def update: Unit = {
            updated = true
          }
        }
        val con = new TestController()
        val tui = new TestTui(con)
        tui.updated should be(false)
        con.updateSubscribers()
        tui.updated should be(true)
      }
    }
  }
}