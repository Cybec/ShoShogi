package de.htwg.se.ShoShogi.util

trait Command {

  def saveStep(): Unit

  def undoStep(): Unit

  def redoStep(): Unit

}

