package de.htwg.se.ShoShogi.util

class UndoManager {
  private var undoStack: List[Command] = Nil
  private var redoStack: List[Command] = Nil

  def clear(): Unit = {
    undoStack = Nil
    redoStack = Nil
  }

  def saveStep(command: Command): Unit = {
    undoStack = command :: undoStack
    redoStack = Nil
  }

  def undoStep(): Boolean = {
    undoStack match {
      case Nil => false
      case head :: stack =>
        head.undoStep()
        undoStack = stack
        redoStack = head :: redoStack
        true
    }
  }

  def redoStep(): Boolean = {
    redoStack match {
      case Nil => false
      case head :: stack =>
        head.redoStep()
        redoStack = stack
        undoStack = head :: undoStack
        true
    }
  }
}
