package de.htwg.se.ShoShogi.controller.controllerComponent.controllerBaseImpl

import de.htwg.se.ShoShogi.model.Board
import de.htwg.se.ShoShogi.util.Command

class SolveCommand(controller: Controller) extends Command {
  var mementoBoard: Board = controller.getBoardClone
  var (mementoContainer1, mementoContainer2) = controller.getContainer
  var mementoState = controller.currentState

  override def saveStep: Unit = {}

  override def undoStep: Unit = {
    val new_memento = controller.getBoardClone
    val (new_mementoContainer1, new_mementoContainer2) = controller.getContainer
    val new_mementoState = controller.currentState

    controller.replaceBoard(mementoBoard)
    controller.setContainer((mementoContainer1, mementoContainer2))
    controller.currentState = mementoState

    mementoContainer1 = new_mementoContainer1
    mementoContainer2 = new_mementoContainer2
    mementoState = new_mementoState
    mementoBoard = new_memento
  }

  override def redoStep: Unit = {
    val new_memento = controller.getBoardClone
    val (new_mementoContainer1, new_mementoContainer2) = controller.getContainer
    val new_mementoState = controller.currentState

    controller.replaceBoard(mementoBoard)
    controller.setContainer((mementoContainer1, mementoContainer2))
    controller.currentState = mementoState

    mementoContainer1 = new_mementoContainer1
    mementoContainer2 = new_mementoContainer2
    mementoState = new_mementoState
    mementoBoard = new_memento
  }
}

