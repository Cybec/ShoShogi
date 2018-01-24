package de.htwg.se.ShoShogi.controller.controllerComponent.controllerBaseImpl

import de.htwg.se.ShoShogi.controller.controllerComponent.ControllerInterface
import de.htwg.se.ShoShogi.model.boardComponent.BoardInterface
import de.htwg.se.ShoShogi.util.Command

class SolveCommand(controller: ControllerInterface) extends Command {
  var mementoBoard: BoardInterface = controller.getBoardClone
  var (mementoContainer1, mementoContainer2) = controller.getContainer
  var mementoState: RoundState = controller.getCurrentStat()

  override def undoStep(): Unit = {
    val new_memento = controller.getBoardClone
    val (new_mementoContainer1, new_mementoContainer2) = controller.getContainer
    val new_mementoState = controller.getCurrentStat()

    controller.replaceBoard(mementoBoard)
    controller.setContainer((mementoContainer1, mementoContainer2))
    controller.setCurrentStat(mementoState)

    mementoContainer1 = new_mementoContainer1
    mementoContainer2 = new_mementoContainer2
    mementoState = new_mementoState
    mementoBoard = new_memento
  }

  override def redoStep(): Unit = {
    val new_memento = controller.getBoardClone
    val (new_mementoContainer1, new_mementoContainer2) = controller.getContainer
    val new_mementoState = controller.getCurrentStat()

    controller.replaceBoard(mementoBoard)
    controller.setContainer((mementoContainer1, mementoContainer2))
    controller.setCurrentStat(mementoState)

    mementoContainer1 = new_mementoContainer1
    mementoContainer2 = new_mementoContainer2
    mementoState = new_mementoState
    mementoBoard = new_memento
  }
}

