package de.htwg.se.ShoShogi.model

import org.scalatest._
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class PieceSpec extends WordSpec with Matchers {
  "A King" when {
    "new" should {
      val piece = King(Player("Your Name", true))
      "have a Player" in {
        piece.player should be(Player("Your Name", true))
      }
      "should not have a promotion" in {
        piece.hasPromotion should be(false)
      }
      "promotion should be none" in {
        piece.promotePiece should be(None)
      }
      "have a nice String representation" in {
        piece.toString should be("K")
      }
    }
  }
}

@RunWith(classOf[JUnitRunner])
class PieceSpec extends WordSpec with Matchers {
  "A King" when {
    "new" should {
      val piece = King(Player("Your Name", true))
      "have a Player" in {
        piece.player should be(Player("Your Name", true))
      }
      "should not have a promotion" in {
        piece.hasPromotion should be(false)
      }
      "promotion should be none" in {
        piece.promotePiece should be(None)
      }
      "have a nice String representation" in {
        piece.toString should be("K")
      }
    }
  }
}
