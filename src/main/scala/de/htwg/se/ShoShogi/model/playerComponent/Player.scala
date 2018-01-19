package de.htwg.se.ShoShogi.model.playerComponent

case class Player(name: String, first: Boolean) {
  override def toString: String = name
}