package de.htwg.se.ShoShogi.model

import de.htwg.se.ShoShogi.aview.Tui
import de.htwg.se.ShoShogi.controller.Controller
import org.scalatest._
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class PieceSpec extends WordSpec with Matchers {
  val boardSize = 9
  val player_1 = new Player("Player 1", true)
  val player_2 = new Player("Player 2", false)
  var board = new Board[Piece](boardSize, new EmptyPiece)

  //Steine fuer Spieler 1
  board = board.replaceCell(0, 0, Lancer(player_1))
  board = board.replaceCell(1, 0, Knight(player_1))
  board = board.replaceCell(2, 0, SilverGeneral(player_1))
  board = board.replaceCell(3, 0, GoldenGeneral(player_1))
  board = board.replaceCell(4, 0, King(player_1))
  board = board.replaceCell(5, 0, GoldenGeneral(player_1))
  board = board.replaceCell(6, 0, SilverGeneral(player_1))
  board = board.replaceCell(7, 0, Knight(player_1))
  board = board.replaceCell(8, 0, Lancer(player_1))
  board = board.replaceCell(7, 1, Bishop(player_1))
  board = board.replaceCell(1, 1, Rook(player_1))
  for (i <- 0 to 8) {
    board = board.replaceCell(i, 2, Pawn(player_1))
  }

  //Steine fuer Spieler 2
  board = board.replaceCell(0, 8, Lancer(player_2))
  board = board.replaceCell(1, 8, Knight(player_2))
  board = board.replaceCell(2, 8, SilverGeneral(player_2))
  board = board.replaceCell(3, 8, GoldenGeneral(player_2))
  board = board.replaceCell(4, 8, King(player_2))
  board = board.replaceCell(5, 8, GoldenGeneral(player_2))
  board = board.replaceCell(6, 8, SilverGeneral(player_2))
  board = board.replaceCell(7, 8, Knight(player_2))
  board = board.replaceCell(8, 8, Lancer(player_2))
  board = board.replaceCell(1, 7, Bishop(player_2))
  board = board.replaceCell(7, 7, Rook(player_2))
  for (i <- 0 to 8) {
    board = board.replaceCell(i, 6, Pawn(player_2))
  }

  "A King" when {
    "new" should {
      val piece = King(player_1)
      val piece2 = King(player_2)
      "have a Player" in {
        piece.player should be(Player("Player 1", true))
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
      "have a List of Moves (4, 0)" in {
        piece.getMoveSet((4, 0), board) should be(List[(Int, Int)]((3, 1), (4, 1), (5, 1)))
      }
      "have a List of Moves (4, 8)" in {
        piece2.getMoveSet((4, 8), board) should be(List[(Int, Int)]((3, 7), (4, 7), (5, 7)))
      }
      "have a List of Moves (4,4) " in {
        piece2.getMoveSet((4, 4), board) should be(List[(Int, Int)]((3, 3), (3, 4), (3, 5), (4, 3), (4, 5), (5, 3), (5, 4), (5, 5)))
      }
    }
  }

  "A GoldenGeneral" when {
    "new" should {
      val piece = GoldenGeneral(player_1)
      val piece2 = GoldenGeneral(player_2)
      "have a Player" in {
        piece.player should be(Player("Player 1", true))
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
      "have a List of Moves (3, 0)" in {
        piece.getMoveSet((3, 0), board) should be(List[(Int, Int)]((2, 1), (3, 1), (4, 1)))
      }
      "have a List of Moves (3, 8)" in {
        piece2.getMoveSet((3, 8), board) should be(List[(Int, Int)]((2, 7), (3, 7), (4, 7)))
      }
      "have a List of Moves (4,4) " in {
        piece2.getMoveSet((4, 4), board) should be(List[(Int, Int)]((3, 4), (3, 3), (4, 3), (5, 3), (5, 4), (4, 5)))
      }
    }
  }

  "A SilverGeneral" when {
    "new" should {
      val piece = SilverGeneral(player_1)
      val piece2 = SilverGeneral(player_2)
      "have a Player" in {
        piece.player should be(Player("Player 1", true))
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
      "have a List of Moves (2, 0)" in {
        piece.getMoveSet((2, 0), board) should be(List[(Int, Int)]((3, 1), (2, 1)))
      }
      "have a List of Moves (2, 8)" in {
        piece2.getMoveSet((2, 8), board) should be(List[(Int, Int)]((3, 7), (2, 7)))
      }
      "have a List of Moves (4,4) " in {
        piece2.getMoveSet((4, 4), board) should be(List[(Int, Int)]((3, 3), (3, 5), (5, 5), (5, 3), (4, 3)))
      }
    }
  }

  "A PromotedSilver" when {
    "new" should {
      val piece = PromotedSilver(player_1)
      val piece2 = PromotedSilver(player_2)
      "have a Player" in {
        piece.player should be(Player("Player 1", true))
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
      "have a List of Moves (3, 0)" in {
        piece.getMoveSet((3, 0), board) should be(List[(Int, Int)]((2, 1), (3, 1), (4, 1)))
      }
      "have a List of Moves (3, 8)" in {
        piece2.getMoveSet((3, 8), board) should be(List[(Int, Int)]((2, 7), (3, 7), (4, 7)))
      }
      "have a List of Moves (4,4) " in {
        piece2.getMoveSet((4, 4), board) should be(List[(Int, Int)]((3, 4), (3, 3), (4, 3), (5, 3), (5, 4), (4, 5)))
      }
    }
  }

  "A Knight" when {
    "new" should {
      val piece = Knight(player_1)
      val piece2 = Knight(player_2)
      "have a Player" in {
        piece.player should be(Player("Player 1", true))
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
      val piece = PromotedKnight(player_1)
      val piece2 = PromotedKnight(player_2)
      "have a Player" in {
        piece.player should be(Player("Player 1", true))
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
      val piece = Lancer(player_1)
      val piece2 = Lancer(player_2)
      "have a Player" in {
        piece.player should be(Player("Player 1", true))
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
      "have a List of Moves (0, 0)" in {
        piece.getMoveSet((0, 0), board) should be(List[(Int, Int)]((0, 1)))
      }
      "have a List of Moves (0, 8)" in {
        piece2.getMoveSet((0, 8), board) should be(List[(Int, Int)]((0, 7)))
      }
      "have a List of Moves (0, 5) " in {
        piece2.getMoveSet((0, 5), board) should be(List[(Int, Int)]((0, 4), (0, 3), (0, 2)))
      }
    }
  }

  "A PromotedLancer" when {
    "new" should {
      val piece = PromotedLancer(player_1)
      val piece2 = PromotedLancer(player_2)
      "have a Player" in {
        piece.player should be(Player("Player 1", true))
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
      val piece = Bishop(player_1)
      val piece2 = Bishop(player_2)
      "have a Player" in {
        piece.player should be(Player("Player 1", true))
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
      val piece = PromotedBishop(player_1)
      val piece2 = PromotedBishop(player_2)
      "have a Player" in {
        piece.player should be(Player("Player 1", true))
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
      val piece = Rook(player_1)
      val piece2 = Rook(player_2)
      "have a Player" in {
        piece.player should be(Player("Player 1", true))
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
      val piece = PromotedRook(player_1)
      val piece2 = PromotedRook(player_2)
      "have a Player" in {
        piece.player should be(Player("Player 1", true))
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
      val piece = Pawn(player_1)
      val piece2 = Pawn(player_2)
      "have a Player" in {
        piece.player should be(Player("Player 1", true))
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
      val piece = PromotedPawn(player_1)
      val piece2 = PromotedPawn(player_2)
      "have a Player" in {
        piece.player should be(Player("Player 1", true))
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

  //  "A EmptyPiece" when {
  //    "new" should {
  //      val piece = EmptyPiece()
  //      "have a Player" in {
  //        piece.player should be(new Player("", false))
  //      }
  //      "should not have a promotion" in {
  //        piece.hasPromotion should be(false)
  //      }
  //      "promotion should be none" in {
  //        piece.promotePiece should be(None)
  //      }
  //      "have a nice String representation" in {
  //        piece.toString should be("  ")
  //      }
  //      "MoveSet should be" in {
  //        piece.getMoveSet(0, 0) should be(List())
  //      }
  //    }
  //  }
}