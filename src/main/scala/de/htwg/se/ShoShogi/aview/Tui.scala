package de.htwg.se.ShoShogi.aview

import de.htwg.se.ShoShogi.controller.Controller
import de.htwg.se.ShoShogi.util.Observer

class Tui(controller: Controller) extends Observer {
  controller.add(this)
  val yAxis = Map(
    'a' -> 0,
    'b' -> 1,
    'c' -> 2,
    'd' -> 3,
    'e' -> 4,
    'f' -> 5,
    'g' -> 6,
    'h' -> 7,
    'i' -> 8
  )
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
    val inputArray = input.split("\\ ", -1)

    inputArray(0) match {
      case "q" =>
      case "n" => {
        controller.createNewBoard()
        menuMap = menuMapInGame
      }
      case "mv" =>
      case "pmv" =>
        parseArguments(inputArray) match {
          case Some(value) => printPossibleMoves(controller.possibleMoves(value._1, value._2))
          case _ => printString("Could not read input: ".concat(input))
        }
      case default => printString("\"" + default + "\" is not a valid input!\n")
    }
  }

  def parseArguments(inputArray: Array[String]): Option[(Int, Int)] = {
    val position = inputArray.mkString(" ").replace("pmv", "").trim.toList
    if (position.length == 2) {
      try {
        if ("1234567890".contains(position(0))) {
          Some(position(0).toInt - '0', yAxis.getOrElse(position(1), -1))
        } else {
          None
        }
      }
    } else {
      None
    }
  }

  def printPossibleMoves(moveList: List[(Int, Int)]): Unit = {
    val moveListString = new StringBuilder
    moveListString.append("Possible moves: ")
    for ((k, v) <- moveList) moveListString.append("(").append(k).append(", ").append(v).append(")").append("   ")
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
