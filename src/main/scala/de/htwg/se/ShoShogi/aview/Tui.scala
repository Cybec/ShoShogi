package de.htwg.se.ShoShogi.aview

import de.htwg.se.ShoShogi.controller.Controller
import de.htwg.se.ShoShogi.util.Observer

class Tui(controller: Controller) extends Observer {
  controller.add(this)

  case class Event(command: String, input: Array[String])

  //Chain of Responsibility Pattern
  //Base handler class
  abstract class Handler {
    val successor: Option[Handler]

    def handleEvent(event: Event): Unit
  }

  class Quit(val successor: Option[Handler]) extends Handler {
    override def handleEvent(event: Event): Unit = {
      event match {
        case e if e.command == "q" => printString("Quit")
        case e => {
          successor match {
            case Some(h: Handler) => h.handleEvent(e)
            case None => printString("Could not find command.")
          }
        }
      }
    }
  }

  class New(val successor: Option[Handler]) extends Handler {
    override def handleEvent(event: Event): Unit = {
      event match {
        case e if e.command == "n" =>
          controller.createNewBoard()
          menuMap = menuMapInGame
        case e => {
          successor match {
            case Some(h: Handler) => h.handleEvent(e)
            case None => printString("Could not find command.")
          }
        }
      }
    }
  }

  class MovePiece(val successor: Option[Handler]) extends Handler {
    override def handleEvent(event: Event): Unit = {
      event match {
        //TODO: ReadLine gewollte Lösung?
        case e if e.command == "mv" =>
          parseArguments(e.input) match {
            case Some(value) =>
              if (controller.promotable((value(0)._1, value(0)._2), (value(1)._1, value(1)._2))) {
                printString("Do you want to promote your piece? (y/n)")
                var input = scala.io.StdIn.readLine()
                if (input == "y") {
                  controller.promotePiece(value(0)._1, value(0)._2)
                }
              }
              controller.movePiece((value(0)._1, value(0)._2), (value(1)._1, value(1)._2))
            case _ => printString("Could not read input: ".concat(e.input.mkString(" ")))
          }
        case e => {
          successor match {
            case Some(h: Handler) => h.handleEvent(e)
            case None => printString("Could not find command.")
          }
        }
      }
    }
  }

  class PossibleMoves(val successor: Option[Handler]) extends Handler {
    override def handleEvent(event: Event): Unit = {
      event match {
        case e if e.command == "pmv" =>
          parseArguments(e.input) match {
            case Some(value) => printPossibleMoves(controller.possibleMoves(value(0)._1, value(0)._2))
            case _ => printString("Could not read input: ".concat(e.input.mkString(" ")))
          }
        case e => {
          successor match {
            case Some(h: Handler) => h.handleEvent(e)
            case None => printString("Could not find command.")
          }
        }
      }
    }
  }

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
    val possibleMoves = new PossibleMoves(None)
    val movePiece = new MovePiece(Some(possibleMoves))
    val newGame = new New(Some(movePiece))
    val quit = new Quit(Some(newGame))

    quit.handleEvent(Event(inputArray(0), inputArray))
  }

  def parseArguments(inputArray: Array[String]): Option[Vector[(Int, Int)]] = {
    val position = inputArray.mkString("").replace("pmv", "").replace("mv", "").trim.toList
    if (position.length == 2) {
      try {
        if ("1234567890".contains(position(0))) {
          Some(Vector[(Int, Int)]((position(0).toInt - '0', yAxis.getOrElse(position(1), -1))))
        } else {
          None
        }
      }
    } else if (position.length == 4) {
      try {
        if ("1234567890".contains(position(0)) && "1234567890".contains(position(2))) {
          var tempVec = Vector.empty[(Int, Int)]
          tempVec = tempVec :+ (position(0).toInt - '0', yAxis.getOrElse(position(1), -1))
          tempVec = tempVec :+ (position(2).toInt - '0', yAxis.getOrElse(position(3), -1))

          Some(tempVec)
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
    for ((k, v) <- moveList) moveListString.append("(").append(k).append(", ")
      .append(yAxis.find(_._2 == v).getOrElse((' ', -1))._1).append(")").append("   ")
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
