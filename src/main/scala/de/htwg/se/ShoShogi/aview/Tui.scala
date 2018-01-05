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
              promoteQuery(value)
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

  class MoveConqueredPiece(val successor: Option[Handler]) extends Handler {
    override def handleEvent(event: Event): Unit = {
      event match {
        case e if e.command == "mvcp" =>
          parseArgumentsFromConquered(e.input) match {
            case Some((pieceAbbreviation, destination)) => controller.moveConqueredPiece(pieceAbbreviation, destination)
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
    "pmv [0-9][a-i]" -> "possible moves of [0-9][a-i]",
    "mvcp [piece abbreviation] [0-9][a-i]" -> "move [piece abbreviation] to [0-9][a-i]"
  )
  var menuMap = menuMapStart

  def processInputLine(input: String): Unit = {
    if (input.length > 0) {
      printString("Input was: " + input)
      val inputArray = input.split("\\ ", -1)

      val moveConqueredPiece = new MoveConqueredPiece(None)
      val possibleMoves = new PossibleMoves(Some(moveConqueredPiece))
      val movePiece = new MovePiece(Some(possibleMoves))
      val newGame = new New(Some(movePiece))
      val quit = new Quit(Some(newGame))

      quit.handleEvent(Event(inputArray(0), inputArray))
    }
  }

  def parseArguments(inputArray: Array[String]): Option[Vector[(Int, Int)]] = {
    val position = inputArray.mkString("").replace("pmv", "").replace("mv", "").replace("mvcp", "").trim.toList
    try {
      if (position.length == 2 && "1234567890".contains(position(0))) {
        Some(Vector[(Int, Int)]((position(0).toInt - '0', yAxis.getOrElse(position(1), -1))))

      } else if (position.length == 4 && "1234567890".contains(position(0)) && "1234567890".contains(position(2))) {
        var tempVec = Vector.empty[(Int, Int)]
        tempVec = tempVec :+ (position(0).toInt - '0', yAxis.getOrElse(position(1), -1))
        tempVec = tempVec :+ (position(2).toInt - '0', yAxis.getOrElse(position(3), -1))

        Some(tempVec)
      } else {
        None
      }
    } catch {
      case _: Throwable => None
    }
  }

  def parseArgumentsFromConquered(inputArray: Array[String]): Option[(String, (Int, Int))] = {
    val position = inputArray.mkString("").replace("mvcp", "").trim.toList
    try {
      if (position.length > 2 && (('a' to 'z') ++ ('A' to 'Z')).contains(position(0))) {
        var pieceAbbreviation: String = ""
        var pieceDestination: (Int, Int) = (-1, -1)

        if ((('a' to 'z') ++ ('A' to 'Z')).contains(position(1))) {
          pieceAbbreviation = position.slice(0, 1).mkString("")
          pieceDestination = (position(2).toInt - '0', yAxis.getOrElse(position(3), -1))
        } else {
          pieceAbbreviation = position(0).toString()
          pieceDestination = (position(1).toInt - '0', yAxis.getOrElse(position(2), -1))
        }

        Some(pieceAbbreviation, pieceDestination)

      } else {
        None
      }
    } catch {
      case _: Throwable => None
    }
  }

  private def promoteQuery(value: Vector[(Int, Int)]) = {
    if (controller.promotable((value(0)._1, value(0)._2), (value(1)._1, value(1)._2))) {
      printString("Do you want to promote your piece? (y/n)")
      val input = scala.io.StdIn.readLine()
      if (input == "y") {
        controller.promotePiece(value(0)._1, value(0)._2)
      }
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
