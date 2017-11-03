package de.htwg.se.ShoShogi.model

case class Player(name: String, first:Boolean) {
  override def toString: String = name
}

