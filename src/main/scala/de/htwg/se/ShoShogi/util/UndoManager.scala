package de.htwg.se.ShoShogi.util

//TODO: undo/redo funktioniert nicht richtig wenn es hintereinander gemacht wird

class UndoManager {
  private var undoStack: List[Command] = Nil
  private var redoStack: List[Command] = Nil

  def saveStep(command: Command) = {
    undoStack = command :: undoStack
    redoStack = Nil
  }

  //Nicht testbar nur in der Gui benuzt
  def undoStep = {
    undoStack match {
      case Nil =>
      case head :: stack => {
        head.undoStep
        undoStack = stack
        redoStack = head :: redoStack
      }
    }
  }

  //Nicht testbar nur in der Gui benutzt
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
