package de.htwg.se.ShoShogi.model.playerComponent

import com.google.inject.Inject

/**
  * Object of Player for the game
  *
  * @param name  Name of Player
  * @param first true if the player is the first one to start
  */
case class Player @Inject() (name: String, first: Boolean) {
  override def toString: String = name
}