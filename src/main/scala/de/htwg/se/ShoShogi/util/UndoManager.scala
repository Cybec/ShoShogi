package de.htwg.se.ShoShogi.util

//TODO: undo/redo funktioniert nicht richtig wenn es hintereinander gemacht wird

class UndoManager {
  private var undoStack: List[Command] = Nil
  private var redoStack: List[Command] = Nil

  def saveStep(command: Command) = {
    undoStack = command :: undoStack
    redoStack = Nil
  }

  def undoStep = {
    if (redoStack.size == 0) {
      undoStack = undoStack.slice(1, undoStack.size)
    }
    undoStack match {
      case Nil =>
      case head :: stack => {
        head.undoStep
        undoStack = stack
        redoStack = head :: redoStack
      }
    }
  }

  def redoStep = {
    redoStack match {
      case Nil =>
      case head :: stack => {
        head.redoStep
        redoStack = stack
        undoStack = head :: undoStack
      }
    }
  }
}
