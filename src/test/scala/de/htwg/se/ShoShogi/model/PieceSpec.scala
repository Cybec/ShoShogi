package de.htwg.se.ShoShogi.model

import org.junit.runner.RunWith
import org.scalatest._
import org.scalatest.junit.JUnitRunner

//noinspection ScalaStyle
@RunWith(classOf[JUnitRunner])
class PieceSpec extends WordSpec with Matchers {
  //  val boardSize = 9
  //  val player_1 = Player("Player 1", true)
  //  val player_2 = Player("Player 2", false)
  //  var board = new Board(boardSize, PieceFactory.apply("EmptyPiece", player_1))
  //
  //  //Steine fuer Spieler 1
  //  board = board.replaceCell(0, 0, PieceFactory.apply("Lancer", player_1))
  //  board = board.replaceCell(1, 0, PieceFactory.apply("Knight", player_1))
  //  board = board.replaceCell(2, 0, PieceFactory.apply("SilverGeneral", player_1))
  //  board = board.replaceCell(3, 0, PieceFactory.apply("GoldenGeneral", player_1))
  //  board = board.replaceCell(4, 0, PieceFactory.apply("King", player_1))
  //  board = board.replaceCell(5, 0, PieceFactory.apply("GoldenGeneral", player_1))
  //  board = board.replaceCell(6, 0, PieceFactory.apply("SilverGeneral", player_1))
  //  board = board.replaceCell(7, 0, PieceFactory.apply("Knight", player_1))
  //  board = board.replaceCell(8, 0, PieceFactory.apply("Lancer", player_1))
  //  board = board.replaceCell(7, 1, PieceFactory.apply("Bishop", player_1))
  //  board = board.replaceCell(1, 1, PieceFactory.apply("Rook", player_1))
  //  for (i <- 0 to 8) {
  //    board = board.replaceCell(i, 2, PieceFactory.apply("Pawn", player_1))
  //  }
  //
  //  //Steine fuer Spieler 2
  //  board = board.replaceCell(0, 8, PieceFactory.apply("Lancer", player_2))
  //  board = board.replaceCell(1, 8, PieceFactory.apply("Knight", player_2))
  //  board = board.replaceCell(2, 8, PieceFactory.apply("SilverGeneral", player_2))
  //  board = board.replaceCell(3, 8, PieceFactory.apply("GoldenGeneral", player_2))
  //  board = board.replaceCell(4, 8, PieceFactory.apply("King", player_2))
  //  board = board.replaceCell(5, 8, PieceFactory.apply("GoldenGeneral", player_2))
  //  board = board.replaceCell(6, 8, PieceFactory.apply("SilverGeneral", player_2))
  //  board = board.replaceCell(7, 8, PieceFactory.apply("Knight", player_2))
  //  board = board.replaceCell(8, 8, PieceFactory.apply("Lancer", player_2))
  //  board = board.replaceCell(1, 7, PieceFactory.apply("Bishop", player_2))
  //  board = board.replaceCell(7, 7, PieceFactory.apply("Rook", player_2))
  //  for (i <- 0 to 8) {
  //    board = board.replaceCell(i, 6, PieceFactory.apply("Pawn", player_2))
  //  }
  //
  //  "A King" when {
  //    "new" should {
  //      val piece = PieceFactory.apply("King", player_1)
  //      val piece2 = PieceFactory.apply("King", player_2)
  //      "have a Player" in {
  //        piece.player should be(Player("Player 1", true))
  //      }
  //      "should not have a promotion" in {
  //        piece.hasPromotion should be(false)
  //      }
  //      "promotion should be none" in {
  //        piece.promotePiece should be(None)
  //      }
  //      "have a nice String representation" in {
  //        piece.toString should be("K° ")
  //      }
  //      "have a List of Moves (4, 0)" in {
  //        piece.getMoveSet((4, 0), board) should be(List[(Int, Int)]((3, 1), (4, 1), (5, 1)))
  //      }
  //      "have a List of Moves (4, 8)" in {
  //        piece2.getMoveSet((4, 8), board) should be(List[(Int, Int)]((3, 7), (4, 7), (5, 7)))
  //      }
  //      "have a List of Moves (4,4) " in {
  //        piece2.getMoveSet((4, 4), board) should be(List[(Int, Int)]((3, 3), (3, 4), (3, 5), (4, 3), (4, 5), (5, 3), (5, 4), (5, 5)))
  //      }
  //    }
  //  }
  //
  //  "A GoldenGeneral" when {
  //    "new" should {
  //      val piece = PieceFactory.apply("GoldenGeneral", player_1)
  //      val piece2 = PieceFactory.apply("GoldenGeneral", player_2)
  //      "have a Player" in {
  //        piece.player should be(Player("Player 1", true))
  //      }
  //      "should not have a promotion" in {
  //        piece.hasPromotion should be(false)
  //      }
  //      "promotion should be none" in {
  //        piece.promotePiece should be(None)
  //      }
  //      "have a nice String representation" in {
  //        piece.toString should be("GG°")
  //      }
  //      "have a List of Moves (3, 0)" in {
  //        piece.getMoveSet((3, 0), board) should be(List[(Int, Int)]((2, 1), (3, 1), (4, 1)))
  //      }
  //      "have a List of Moves (3, 8)" in {
  //        piece2.getMoveSet((3, 8), board) should be(List[(Int, Int)]((2, 7), (3, 7), (4, 7)))
  //      }
  //      "have a List of Moves (4,4) " in {
  //        piece2.getMoveSet((4, 4), board) should be(List[(Int, Int)]((3, 4), (3, 3), (4, 3), (5, 3), (5, 4), (4, 5)))
  //      }
  //    }
  //  }
  //
  //  "A SilverGeneral" when {
  //    "new" should {
  //      val piece = PieceFactory.apply("SilverGeneral", player_1)
  //      val piece2 = PieceFactory.apply("SilverGeneral", player_2)
  //      "have a Player" in {
  //        piece.player should be(Player("Player 1", true))
  //      }
  //      "should not have a promotion" in {
  //        piece.hasPromotion should be(true)
  //      }
  //      "promotion should be none" in {
  //        piece.promotePiece should be(Some(PieceFactory.apply("PromotedSilver", piece.player)))
  //      }
  //      "have a nice String representation" in {
  //        piece.toString should be("SG°")
  //      }
  //      "have a List of Moves (2, 0)" in {
  //        piece.getMoveSet((2, 0), board) should be(List[(Int, Int)]((3, 1), (2, 1)))
  //      }
  //      "have a List of Moves (2, 8)" in {
  //        piece2.getMoveSet((2, 8), board) should be(List[(Int, Int)]((3, 7), (2, 7)))
  //      }
  //      "have a List of Moves (4,4) " in {
  //        piece2.getMoveSet((4, 4), board) should be(List[(Int, Int)]((3, 3), (3, 5), (5, 5), (5, 3), (4, 3)))
  //      }
  //    }
  //  }
  //
  //  "A PromotedSilver" when {
  //    "new" should {
  //      val piece = PieceFactory.apply("PromotedSilver", player_1)
  //      val piece2 = PieceFactory.apply("PromotedSilver", player_2)
  //      "have a Player" in {
  //        piece.player should be(Player("Player 1", true))
  //      }
  //      "should not have a promotion" in {
  //        piece.hasPromotion should be(false)
  //      }
  //      "promotion should be none" in {
  //        piece.promotePiece should be(None)
  //      }
  //      "have a nice String representation" in {
  //        piece.toString should be("PS°")
  //        piece2.toString should be("PS ")
  //      }
  //      "have a List of Moves (3, 0)" in {
  //        piece.getMoveSet((3, 0), board) should be(List[(Int, Int)]((2, 1), (3, 1), (4, 1)))
  //      }
  //      "have a List of Moves (3, 8)" in {
  //        piece2.getMoveSet((3, 8), board) should be(List[(Int, Int)]((2, 7), (3, 7), (4, 7)))
  //      }
  //      "have a List of Moves (4,4) " in {
  //        piece2.getMoveSet((4, 4), board) should be(List[(Int, Int)]((3, 4), (3, 3), (4, 3), (5, 3), (5, 4), (4, 5)))
  //      }
  //    }
  //  }
  //
  //  "A Knight" when {
  //    "new" should {
  //      val piece = PieceFactory.apply("Knight", player_1)
  //      val piece2 = PieceFactory.apply("Knight", player_2)
  //      "have a Player" in {
  //        piece.player should be(Player("Player 1", true))
  //      }
  //      "should not have a promotion" in {
  //        piece.hasPromotion should be(true)
  //      }
  //      "promotion should be none" in {
  //        piece.promotePiece should be(Some(PieceFactory.apply("PromotedKnight", piece.player)))
  //      }
  //      "have a nice String representation" in {
  //        piece.toString should be("KN°")
  //      }
  //      "have a List of Moves (1, 0)" in {
  //        piece.getMoveSet((1, 0), board) should be(List[(Int, Int)]())
  //      }
  //      "have a List of Moves (1, 8)" in {
  //        piece2.getMoveSet((1, 8), board) should be(List[(Int, Int)]())
  //      }
  //      "have a List of Moves (4, 5) " in {
  //        piece2.getMoveSet((4, 5), board) should be(List[(Int, Int)]((3, 3), (5, 3)))
  //      }
  //    }
  //  }
  //
  //  "A PromotedKnight" when {
  //    "new" should {
  //      val piece = PieceFactory.apply("PromotedKnight", player_1)
  //      val piece2 = PieceFactory.apply("PromotedKnight", player_2)
  //      "have a Player" in {
  //        piece.player should be(Player("Player 1", true))
  //      }
  //      "should not have a promotion" in {
  //        piece.hasPromotion should be(false)
  //      }
  //      "promotion should be none" in {
  //        piece.promotePiece should be(None)
  //      }
  //      "have a nice String representation" in {
  //        piece.toString should be("PK°")
  //        piece2.toString should be("PK ")
  //      }
  //      "have a List of Moves (3, 0)" in {
  //        piece.getMoveSet((3, 0), board) should be(List[(Int, Int)]((2, 1), (3, 1), (4, 1)))
  //      }
  //      "have a List of Moves (3, 8)" in {
  //        piece2.getMoveSet((3, 8), board) should be(List[(Int, Int)]((2, 7), (3, 7), (4, 7)))
  //      }
  //      "have a List of Moves (4,4) " in {
  //        piece2.getMoveSet((4, 4), board) should be(List[(Int, Int)]((3, 4), (3, 3), (4, 3), (5, 3), (5, 4), (4, 5)))
  //      }
  //    }
  //  }
  //
  //  "A Lancer" when {
  //    "new" should {
  //      val piece = PieceFactory.apply("Lancer", player_1)
  //      val piece2 = PieceFactory.apply("Lancer", player_2)
  //      "have a Player" in {
  //        piece.player should be(Player("Player 1", true))
  //      }
  //      "should not have a promotion" in {
  //        piece.hasPromotion should be(true)
  //      }
  //      "promotion should be none" in {
  //        piece.promotePiece should be(Some(PieceFactory.apply("PromotedLancer", piece.player)))
  //      }
  //      "have a nice String representation" in {
  //        piece.toString should be("L° ")
  //      }
  //      "have a List of Moves (0, 0)" in {
  //        piece.getMoveSet((0, 0), board) should be(List[(Int, Int)]((0, 1)))
  //      }
  //      "have a List of Moves (0, 8)" in {
  //        piece2.getMoveSet((0, 8), board) should be(List[(Int, Int)]((0, 7)))
  //      }
  //      "have a List of Moves (0, 5) " in {
  //        piece2.getMoveSet((0, 5), board) should be(List[(Int, Int)]((0, 4), (0, 3), (0, 2)))
  //      }
  //    }
  //  }
  //
  //  "A PromotedLancer" when {
  //    "new" should {
  //      val piece = PieceFactory.apply("PromotedLancer", player_1)
  //      val piece2 = PieceFactory.apply("PromotedLancer", player_2)
  //      "have a Player" in {
  //        piece.player should be(Player("Player 1", true))
  //      }
  //      "should not have a promotion" in {
  //        piece.hasPromotion should be(false)
  //      }
  //      "promotion should be none" in {
  //        piece.promotePiece should be(None)
  //      }
  //      "have a nice String representation" in {
  //        piece.toString should be("PL°")
  //        piece2.toString should be("PL ")
  //      }
  //      "have a List of Moves (3, 0)" in {
  //        piece.getMoveSet((3, 0), board) should be(List[(Int, Int)]((2, 1), (3, 1), (4, 1)))
  //      }
  //      "have a List of Moves (3, 8)" in {
  //        piece2.getMoveSet((3, 8), board) should be(List[(Int, Int)]((2, 7), (3, 7), (4, 7)))
  //      }
  //      "have a List of Moves (4,4) " in {
  //        piece2.getMoveSet((4, 4), board) should be(List[(Int, Int)]((3, 4), (3, 3), (4, 3), (5, 3), (5, 4), (4, 5)))
  //      }
  //    }
  //  }
  //
  //  "A Bishop" when {
  //    "new" should {
  //      val piece = PieceFactory.apply("Bishop", player_1)
  //      val piece2 = PieceFactory.apply("Bishop", player_2)
  //      "have a Player" in {
  //        piece.player should be(Player("Player 1", true))
  //      }
  //      "should not have a promotion" in {
  //        piece.hasPromotion should be(true)
  //      }
  //      "promotion should be none" in {
  //        piece.promotePiece should be(Some(PieceFactory.apply("PromotedBishop", piece.player)))
  //      }
  //      "have a nice String representation" in {
  //        piece.toString should be("B° ")
  //      }
  //      "have a List of Moves (7, 0)" in {
  //        piece.getMoveSet((7, 1), board) should be(List[(Int, Int)]())
  //      }
  //      "have a List of Moves (1, 7)" in {
  //        piece2.getMoveSet((1, 7), board) should be(List[(Int, Int)]())
  //      }
  //      "have a List of Moves (4,4) " in {
  //        piece2.getMoveSet((4, 4), board) should be(List[(Int, Int)]((3, 3), (2, 2), (3, 5), (5, 5), (5, 3), (6, 2)))
  //      }
  //    }
  //  }
  //
  //  "A PromotedBishop" when {
  //    "new" should {
  //      val piece = PieceFactory.apply("PromotedBishop", player_1)
  //      val piece2 = PieceFactory.apply("PromotedBishop", player_2)
  //      "have a Player" in {
  //        piece.player should be(Player("Player 1", true))
  //      }
  //      "should not have a promotion" in {
  //        piece.hasPromotion should be(false)
  //      }
  //      "promotion should be none" in {
  //        piece.promotePiece should be(None)
  //      }
  //      "have a nice String representation" in {
  //        piece.toString should be("PB°")
  //        piece2.toString should be("PB ")
  //      }
  //      "have a List of Moves (7, 0)" in {
  //        piece.getMoveSet((7, 1), board) should be(List[(Int, Int)]((8, 1), (6, 1)))
  //      }
  //      "have a List of Moves (1, 7)" in {
  //        piece2.getMoveSet((1, 7), board) should be(List[(Int, Int)]((2, 7), (0, 7)))
  //      }
  //      "have a List of Moves (4,4) " in {
  //        piece2.getMoveSet((4, 4), board) should be(List[(Int, Int)]((3, 3), (2, 2), (3, 5), (5, 5), (5, 3), (6, 2), (4, 5), (5, 4), (4, 3), (3, 4)))
  //      }
  //    }
  //  }
  //
  //  "A Rook" when {
  //    "new" should {
  //      val piece = PieceFactory.apply("Rook", player_1)
  //      val piece2 = PieceFactory.apply("Rook", player_2)
  //      "have a Player" in {
  //        piece.player should be(Player("Player 1", true))
  //      }
  //      "should not have a promotion" in {
  //        piece.hasPromotion should be(true)
  //      }
  //      "promotion should be none" in {
  //        piece.promotePiece should be(Some(PieceFactory.apply("PromotedRook", piece.player)))
  //      }
  //      "have a nice String representation" in {
  //        piece.toString should be("R° ")
  //      }
  //      "have a List of Moves (1, 1)" in {
  //        piece.getMoveSet((1, 1), board) should be(List[(Int, Int)]((2, 1), (3, 1), (4, 1), (5, 1), (6, 1), (0, 1)))
  //      }
  //      "have a List of Moves (7, 7)" in {
  //        piece2.getMoveSet((7, 7), board) should be(List[(Int, Int)]((8, 7), (6, 7), (5, 7), (4, 7), (3, 7), (2, 7)))
  //      }
  //      "have a List of Moves (4,4) " in {
  //        piece2.getMoveSet((4, 4), board) should be(List[(Int, Int)]((4, 5), (5, 4), (6, 4), (7, 4), (8, 4), (4, 3), (4, 2), (3, 4), (2, 4), (1, 4), (0, 4)))
  //      }
  //    }
  //  }
  //
  //  "A PromotedRook" when {
  //    "new" should {
  //      val piece = PieceFactory.apply("PromotedRook", player_1)
  //      val piece2 = PieceFactory.apply("PromotedRook", player_2)
  //      "have a Player" in {
  //        piece.player should be(Player("Player 1", true))
  //      }
  //      "should not have a promotion" in {
  //        piece.hasPromotion should be(false)
  //      }
  //      "promotion should be none" in {
  //        piece.promotePiece should be(None)
  //      }
  //      "have a nice String representation" in {
  //        piece.toString should be("PR°")
  //        piece2.toString should be("PR ")
  //      }
  //      "have a List of Moves (1, 1)" in {
  //        piece.getMoveSet((1, 1), board) should be(List[(Int, Int)]((2, 1), (3, 1), (4, 1), (5, 1), (6, 1), (0, 1)))
  //      }
  //      "have a List of Moves (7, 7)" in {
  //        piece2.getMoveSet((7, 7), board) should be(List[(Int, Int)]((8, 7), (6, 7), (5, 7), (4, 7), (3, 7), (2, 7)))
  //      }
  //      "have a List of Moves (4,4) " in {
  //        piece2.getMoveSet((4, 4), board) should be(List[(Int, Int)]((4, 5), (5, 4), (6, 4), (7, 4), (8, 4), (4, 3), (4, 2), (3, 4), (2, 4), (1, 4), (0, 4), (3, 3), (3, 5), (5, 5), (5, 3)))
  //      }
  //    }
  //  }
  //
  //  "A Pawn" when {
  //    "new" should {
  //      val piece = PieceFactory.apply("Pawn", player_1)
  //      val piece2 = PieceFactory.apply("Pawn", player_2)
  //      "have a Player" in {
  //        piece.player should be(Player("Player 1", true))
  //      }
  //      "should not have a promotion" in {
  //        piece.hasPromotion should be(true)
  //      }
  //      "promotion should be none" in {
  //        piece.promotePiece should be(Some(PieceFactory.apply("PromotedPawn", piece.player)))
  //      }
  //      "have a nice String representation" in {
  //        piece.toString should be("P° ")
  //      }
  //      "have a List of Moves (1, 1)" in {
  //        piece.getMoveSet((1, 2), board) should be(List[(Int, Int)]((1, 3)))
  //      }
  //      "have a List of Moves (7, 7)" in {
  //        piece2.getMoveSet((1, 6), board) should be(List[(Int, Int)]((1, 5)))
  //      }
  //      "have a List of Moves (4,4) " in {
  //        piece2.getMoveSet((4, 4), board) should be(List[(Int, Int)]((4, 3)))
  //      }
  //    }
  //  }
  //
  //  "A PromotedPawn" when {
  //    "new" should {
  //      val piece = PieceFactory.apply("PromotedPawn", player_1)
  //      val piece2 = PieceFactory.apply("PromotedPawn", player_2)
  //      "have a Player" in {
  //        piece.player should be(Player("Player 1", true))
  //      }
  //      "should not have a promotion" in {
  //        piece.hasPromotion should be(false)
  //      }
  //      "promotion should be none" in {
  //        piece.promotePiece should be(None)
  //      }
  //      "have a nice String representation" in {
  //        piece.toString should be("PP°")
  //        piece2.toString should be("PP ")
  //      }
  //      "have a List of Moves (3, 0)" in {
  //        piece.getMoveSet((3, 0), board) should be(List[(Int, Int)]((2, 1), (3, 1), (4, 1)))
  //      }
  //      "have a List of Moves (3, 8)" in {
  //        piece2.getMoveSet((3, 8), board) should be(List[(Int, Int)]((2, 7), (3, 7), (4, 7)))
  //      }
  //      "have a List of Moves (4,4) " in {
  //        piece2.getMoveSet((4, 4), board) should be(List[(Int, Int)]((3, 4), (3, 3), (4, 3), (5, 3), (5, 4), (4, 5)))
  //      }
  //    }
  //  }
  //
  //  "A EmptyPiece" when {
  //    "new" should {
  //      val piece = PieceFactory.apply("EmptyPiece", player_1)
  //      "have a Player" in {
  //        piece.player should be(Player("", false))
  //      }
  //      "should not have a promotion" in {
  //        piece.hasPromotion should be(false)
  //      }
  //      "promotion should be none" in {
  //        piece.promotePiece should be(None)
  //      }
  //      "have a nice String representation" in {
  //        piece.toString should be("   ")
  //      }
  //      "have a List of Moves (3, 0)" in {
  //        piece.getMoveSet((3, 0), board) should be(List[(Int, Int)]())
  //      }
  //    }
  //  }
  //
  //  "A Piece" when {
  //    "called cloneToNewPlayer" should {
  //      "clone King(player_1) to King(player_2)" in {
  //        val p: Piece = PieceFactory.apply("King", player_1).cloneToNewPlayer(player_2)
  //        p shouldBe a[King]
  //        p.player should be(player_2)
  //      }
  //      "clone GoldenGeneral(player_1) to GoldenGeneral(player_2)" in {
  //        val p: Piece = PieceFactory.apply("GoldenGeneral", player_1).cloneToNewPlayer(player_2)
  //        p shouldBe a[GoldenGeneral]
  //        p.player should be(player_2)
  //      }
  //      "clone SilverGeneral(player_1) to SilverGeneral(player_2)" in {
  //        val p: Piece = PieceFactory.apply("SilverGeneral", player_1).cloneToNewPlayer(player_2)
  //        p shouldBe a[SilverGeneral]
  //        p.player should be(player_2)
  //      }
  //      "clone PromotedSilver(player_1) to SilverGeneral(player_2)" in {
  //        val p: Piece = PieceFactory.apply("PromotedSilver", player_1).cloneToNewPlayer(player_2)
  //        p shouldBe a[SilverGeneral]
  //        p.player should be(player_2)
  //      }
  //      "clone Knight(player_1) to Knight(player_2)" in {
  //        val p: Piece = PieceFactory.apply("Knight", player_1).cloneToNewPlayer(player_2)
  //        p shouldBe a[Knight]
  //        p.player should be(player_2)
  //      }
  //      "clone PromotedKnight(player_1) to Knight(player_2)" in {
  //        val p: Piece = PieceFactory.apply("PromotedKnight", player_1).cloneToNewPlayer(player_2)
  //        p shouldBe a[Knight]
  //        p.player should be(player_2)
  //      }
  //      "clone Lancer(player_1) to Lancer(player_2)" in {
  //        val p: Piece = PieceFactory.apply("Lancer", player_1).cloneToNewPlayer(player_2)
  //        p shouldBe a[Lancer]
  //        p.player should be(player_2)
  //      }
  //      "clone PromotedLancer(player_1) to Lancer(player_2)" in {
  //        val p: Piece = PieceFactory.apply("PromotedLancer", player_1).cloneToNewPlayer(player_2)
  //        p shouldBe a[Lancer]
  //        p.player should be(player_2)
  //      }
  //      "clone Bishop(player_1) to Bishop(player_2)" in {
  //        val p: Piece = PieceFactory.apply("Bishop", player_1).cloneToNewPlayer(player_2)
  //        p shouldBe a[Bishop]
  //        p.player should be(player_2)
  //      }
  //      "clone PromotedBishop(player_1) to Bishop(player_2)" in {
  //        val p: Piece = PieceFactory.apply("PromotedBishop", player_1).cloneToNewPlayer(player_2)
  //        p shouldBe a[Bishop]
  //        p.player should be(player_2)
  //      }
  //      "clone Rook(player_1) to Rook(player_2)" in {
  //        val p: Piece = PieceFactory.apply("Rook", player_1).cloneToNewPlayer(player_2)
  //        p shouldBe a[Rook]
  //        p.player should be(player_2)
  //      }
  //      "clone PromotedRook(player_1) to Rook(player_2)" in {
  //        val p: Piece = PieceFactory.apply("PromotedRook", player_1).cloneToNewPlayer(player_2)
  //        p shouldBe a[Rook]
  //        p.player should be(player_2)
  //      }
  //      "clone Pawn(player_1) to Pawn(player_2)" in {
  //        val p: Piece = PieceFactory.apply("Pawn", player_1).cloneToNewPlayer(player_2)
  //        p shouldBe a[Pawn]
  //        p.player should be(player_2)
  //      }
  //      "clone PromotedPawn(player_1) to Pawn(player_2)" in {
  //        val p: Piece = PieceFactory.apply("PromotedPawn", player_1).cloneToNewPlayer(player_2)
  //        p shouldBe a[Pawn]
  //        p.player should be(player_2)
  //      }
  //      "clone EmptyPiece(player_1) to EmptyPiece(player_2)" in {
  //        val p: Piece = PieceFactory.apply("EmptyPiece", player_1).cloneToNewPlayer(player_2)
  //        p shouldBe a[EmptyPiece]
  //      }
  //    }
  //  }
  //
  //  "A Piece" when {
  //    "called isForstOwner" should {
  //      "be true when Piece belongs to first player" in {
  //        val piecePlayer1: Piece = PieceFactory.apply("King", player_1)
  //        piecePlayer1.isFirstOwner should be(true)
  //      }
  //      "be false when Piece belongs to second player" in {
  //        val piecePlayer2: Piece = PieceFactory.apply("King", player_2)
  //        piecePlayer2.isFirstOwner should be(false)
  //      }
  //    }
  //  }
}