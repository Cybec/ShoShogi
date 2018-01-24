package de.htwg.se.ShoShogi.aview

import com.google.inject.{ Guice, Injector }
import de.htwg.se.ShoShogi.ShoShogiModule
import de.htwg.se.ShoShogi.controller.controllerComponent.ControllerInterface
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{ Matchers, WordSpec }

@RunWith(classOf[JUnitRunner])
class TuiSpec extends WordSpec with Matchers {
  val injector: Injector = Guice.createInjector(new ShoShogiModule)
  val controller: ControllerInterface = injector.getInstance(classOf[ControllerInterface])
  controller.createNewBoard()

  val tui = new Tui(controller)

  "A Tui" when {
    "parseArguments called" should {
      "give back \"pmv     0a  \"" in {
        tui.parseArguments(Array("pmv", " ", " ", " ", " ", " 0a", " ", " ")) should be(Some(Vector((0, 0))))
      }
      "give back \"pmv     8h  \"" in {
        tui.parseArguments(Array("pmv", " ", " ", " ", " ", " 8h", " ", " ")) should be(Some(Vector((8, 7))))
      }
      "give back \"pmv     4d  \"" in {
        tui.parseArguments(Array("pmv", " ", " ", " ", " ", " 4d", " ", " ")) should be(Some(Vector((4, 3))))
      }
      "give back \"pmv     0ca  \"" in {
        tui.parseArguments(Array("pmv", " ", " ", " ", " ", " 0ca", " ", " ")) should be(None)
      }
      "give back \"pmv     ca  \"" in {
        tui.parseArguments(Array("pmv", " ", " ", " ", " ", " ca", " ", " ")) should be(None)
      }
      "give back \"mv      0g     0f  \"" in {
        tui.parseArguments(Array("mv", "0g", "0f")) should be(Some(Vector((0, 6), (0, 5))))
      }
    }
    "parseArgumentsFromConquered called" should {
      "give back \"mvcp    P    0d\"" in {
        tui.parseArgumentsFromConquered(Array("mvcp", "P", "0d")) should be(Some("P", (0, 3)))
      }
      "give back \"None\"" in {
        tui.parseArgumentsFromConquered(Array("mvcp", "990", "0d")) should be(None)
      }
    }
  }
}