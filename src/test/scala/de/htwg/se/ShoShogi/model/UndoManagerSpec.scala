package de.htwg.se.ShoShogi.model

import com.google.inject.{Guice, Injector}
import com.google.inject.name.Names
import de.htwg.se.ShoShogi.ShoShogiModule
import de.htwg.se.ShoShogi.controller.controllerComponent.{ControllerInterface, MoveResult}
import de.htwg.se.ShoShogi.controller.controllerComponent.controllerBaseImpl.{RoundState, playerOneRound, playerTwoRound}
import de.htwg.se.ShoShogi.model.boardComponent.BoardInterface
import de.htwg.se.ShoShogi.model.fileIoComponent.FileIOInterface
import de.htwg.se.ShoShogi.model.playerComponent.Player
import de.htwg.se.ShoShogi.util.UndoManager
import org.junit.runner.RunWith
import org.scalatest.{Matchers, WordSpec}
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class UndoManagerSpec extends WordSpec with Matchers {
  "An UndoManager" when {
    val undoManager = new UndoManager
    val injector: Injector = Guice.createInjector(new ShoShogiModule)
    val controller: ControllerInterface = injector.getInstance(classOf[ControllerInterface])
    "called undoStep" should {
      "have the last done move on the redo Step" in {


        controller.createNewBoard()
        controller.movePiece((0, 2), (0, 3)) should be(MoveResult.validMove)
      //  undoManager.saveStep()
      //  undoManager.saveStep()
      }
    }
  }
}
