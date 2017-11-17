package de.htwg.se.ShoShogi.aview

import de.htwg.se.ShoShogi.controller.Controller
import de.htwg.se.ShoShogi.util.Observer

class Tui(controller: Controller) extends Observer {

  controller.add(this)
  val menuMapStart = Map(
    "q" -> "quit",
    "n" -> "new"
  )
  val menuMapInGame = Map(
    "q" -> "quit",
    "n" -> "new",
    "mv [0-9][a-i] [0-9][a-i]" -> "move [0-9][a-i] to [0-9][a-i]",
    "pmv [0-9][a-i]" -> "possible moves of [0-9][a-i]"
  )
  var menuMap = menuMapStart

  def processInputLine(input: String): Unit = {
    if (input.length < 1) {
      return
    }
    printString("Input was: " + input)
    val inputArray = input.split(" ")

    inputArray(0) match {
      case "q" =>
      case "n" => {
        controller.createNewBoard()
        menuMap = menuMapInGame
      }
      case "mv" =>
      case "pmv" => printPossibleMoves(controller.possibleMoves(0, 1))
      case default => printString(default + "Is not a valid input!\n")
    }
  }

  def printPossibleMoves(moveList: List[(Int, Int)]): Unit = {
    val moveListString = new StringBuilder
    for ((k, v) <- moveList) moveListString.append("(").append(k).append(", ").append(v).append(")").append("---")
    moveListString.append("\n")
    printString(moveListString.toString())
  }

  def printInputMenu(): Unit = {
    val menuString = new StringBuilder
    for ((k, v) <- menuMap) menuString.append(k).append(": ").append(v).append("\n").append("------\n")
    printString(menuString.toString())
  }

  def printString(stringToPrint: String): Boolean = {
    println(stringToPrint)
    true
  }

  override def update: Unit = printString(controller.boardToString)
}
