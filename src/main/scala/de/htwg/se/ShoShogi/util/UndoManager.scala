package de.htwg.se.ShoShogi.util

class UndoManager {
  private var undoStack: List[Command] = Nil
  private var redoStack: List[Command] = Nil

  def saveStep(command: Command): Unit = {
    undoStack = command :: undoStack
    redoStack = Nil
  }

  def undoStep(): Unit = {
    undoStack match {
      case Nil =>
      case head :: stack =>
        head.undoStep()
        undoStack = stack
        redoStack = head :: redoStack
    }
  }

  def redoStep(): Unit = {
    redoStack match {
      case Nil =>
      case head :: stack =>
        head.redoStep()
        redoStack = stack
        undoStack = head :: undoStack
    }
  }

  def getUndoStack(): List[Command] = {
    undoStack
  }

  def getRedoStack(): List[Command] = {
    redoStack
  }
}
