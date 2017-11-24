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
        piece.toString should be("K ")
      }
    }
  }

  "A GoldenGeneral" when {
    "new" should {
      val piece = GoldenGeneral(Player("Your Name", true))
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
        piece.toString should be("GG")
      }
    }
  }

  "A SilverGeneral" when {
    "new" should {
      val piece = SilverGeneral(Player("Your Name", true))
      "have a Player" in {
        piece.player should be(Player("Your Name", true))
      }
      "should not have a promotion" in {
        piece.hasPromotion should be(true)
      }
      "promotion should be none" in {
        piece.promotePiece should be(Some(PromotedSilver(piece.player)))
      }
      "have a nice String representation" in {
        piece.toString should be("SG")
      }
    }
  }

  "A PromotedSilver" when {
    "new" should {
      val piece = PromotedSilver(Player("Your Name", true))
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
        piece.toString should be("PS")
      }
    }
  }

  "A Knight" when {
    "new" should {
      val piece = Knight(Player("Your Name", true))
      "have a Player" in {
        piece.player should be(Player("Your Name", true))
      }
      "should not have a promotion" in {
        piece.hasPromotion should be(true)
      }
      "promotion should be none" in {
        piece.promotePiece should be(Some(PromotedKnight(piece.player)))
      }
      "have a nice String representation" in {
        piece.toString should be("KN")
      }
    }
  }

  "A PromotedKnight" when {
    "new" should {
      val piece = PromotedKnight(Player("Your Name", true))
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
        piece.toString should be("PK")
      }
    }
  }

  "A Lancer" when {
    "new" should {
      val piece = Lancer(Player("Your Name", true))
      "have a Player" in {
        piece.player should be(Player("Your Name", true))
      }
      "should not have a promotion" in {
        piece.hasPromotion should be(true)
      }
      "promotion should be none" in {
        piece.promotePiece should be(Some(PromotedLancer(piece.player)))
      }
      "have a nice String representation" in {
        piece.toString should be("L ")
      }
    }
  }

  "A PromotedLancer" when {
    "new" should {
      val piece = PromotedLancer(Player("Your Name", true))
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
        piece.toString should be("PL")
      }
    }
  }

  "A Bishop" when {
    "new" should {
      val piece = Bishop(Player("Your Name", true))
      "have a Player" in {
        piece.player should be(Player("Your Name", true))
      }
      "should not have a promotion" in {
        piece.hasPromotion should be(true)
      }
      "promotion should be none" in {
        piece.promotePiece should be(Some(PromotedBishop(piece.player)))
      }
      "have a nice String representation" in {
        piece.toString should be("B ")
      }
    }
  }

  "A PromotedBishop" when {
    "new" should {
      val piece = PromotedBishop(Player("Your Name", true))
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
        piece.toString should be("PB")
      }
    }
  }

  "A Rook" when {
    "new" should {
      val piece = Rook(Player("Your Name", true))
      "have a Player" in {
        piece.player should be(Player("Your Name", true))
      }
      "should not have a promotion" in {
        piece.hasPromotion should be(true)
      }
      "promotion should be none" in {
        piece.promotePiece should be(Some(PromotedRook(piece.player)))
      }
      "have a nice String representation" in {
        piece.toString should be("R ")
      }
    }
  }

  "A PromotedRook" when {
    "new" should {
      val piece = PromotedRook(Player("Your Name", true))
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
        piece.toString should be("PR")
      }
    }
  }

  "A Pawn" when {
    "new" should {
      val piece = Pawn(Player("Your Name", true))
      "have a Player" in {
        piece.player should be(Player("Your Name", true))
      }
      "should not have a promotion" in {
        piece.hasPromotion should be(true)
      }
      "promotion should be none" in {
        piece.promotePiece should be(Some(PromotedPawn(piece.player)))
      }
      "have a nice String representation" in {
        piece.toString should be("P ")
      }
    }
  }

  "A PromotedPawn" when {
    "new" should {
      val piece = PromotedPawn(Player("Your Name", true))
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
        piece.toString should be("PP")
      }
    }
  }
}