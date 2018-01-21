package de.htwg.se.ShoShogi.model.playerComponent

import com.google.inject.Inject

case class Player @Inject() (name: String, first: Boolean) {
  override def toString: String = name
}