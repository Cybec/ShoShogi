package de.htwg.se.ShoShogi

import de.htwg.se.ShoShogi.model.Player

object ShoShogi {
  def main(args: Array[String]): Unit = {
    val student = Player("Mert Zeybek")
    println("Hello, " + student.name)
  }
}
