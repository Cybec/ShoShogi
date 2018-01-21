package de.htwg.se.ShoShogi

import com.google.inject.Guice
import de.htwg.se.ShoShogi.aview.Tui
//import de.htwg.se.ShoShogi.aview.gui.SwingGui
import de.htwg.se.ShoShogi.controller.controllerComponent.ControllerInterface
import de.htwg.se.ShoShogi.controller.controllerComponent.controllerBaseImpl.UpdateAll

import scala.swing.Publisher

object ShoShogi extends Publisher {
  val injector = Guice.createInjector(new ShoShogiModule)
  val controller = injector.getInstance(classOf[ControllerInterface])
  val tui = new Tui(controller)
  //  val gui = new SwingGui(controller)
  //  listenTo(gui)
  controller.publish(new UpdateAll)

  def main(args: Array[String]): Unit = {
    var input: String = ""

    do {
      tui.printInputMenu()
      input = scala.io.StdIn.readLine()
      tui.processInputLine(input)
    } while (input != "q")
    System.exit(0)
  }

  //  reactions += { case _ => if (gui == null) System.exit(0) }
}
