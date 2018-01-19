package de.htwg.se.ShoShogi.model

import de.htwg.se.ShoShogi.model.boardComponent.boardBaseImpl.Board
import de.htwg.se.ShoShogi.model.pieceComponent._
import de.htwg.se.ShoShogi.model.playerComponent.Player
import org.junit.runner.RunWith
import org.scalatest._
import org.scalatest.junit.JUnitRunner

//noinspection ScalaStyle
@RunWith(classOf[JUnitRunner])
class PieceSpec extends WordSpec with Matchers {
  val boardSize = 9
  val player_1 = Player("Player 1", true)
  val player_2 = Player("Player 2", false)
  var board = new Board(boardSize, pieceFactory.apply("EmptyPiece", player_1))

  //Steine fuer Spieler 1
  board = board.replaceCell(0, 0, pieceFactory.apply("Lancer", player_1))
  board = board.replaceCell(1, 0, pieceFactory.apply("Knight", player_1))
  board = board.replaceCell(2, 0, pieceFactory.apply("SilverGeneral", player_1))
  board = board.replaceCell(3, 0, pieceFactory.apply("GoldenGeneral", player_1))
  board = board.replaceCell(4, 0, pieceFactory.apply("King", player_1))
  board = board.replaceCell(5, 0, pieceFactory.apply("GoldenGeneral", player_1))
  board = board.replaceCell(6, 0, pieceFactory.apply("SilverGeneral", player_1))
  board = board.replaceCell(7, 0, pieceFactory.apply("Knight", player_1))
  board = board.replaceCell(8, 0, pieceFactory.apply("Lancer", player_1))
  board = board.replaceCell(7, 1, pieceFactory.apply("Bishop", player_1))
  board = board.replaceCell(1, 1, pieceFactory.apply("Rook", player_1))
  for (i <- 0 to 8) {
    board = board.replaceCell(i, 2, pieceFactory.apply("Pawn", player_1))
  }

  //Steine fuer Spieler 2
  board = board.replaceCell(0, 8, pieceFactory.apply("Lancer", player_2))
  board = board.replaceCell(1, 8, pieceFactory.apply("Knight", player_2))
  board = board.replaceCell(2, 8, pieceFactory.apply("SilverGeneral", player_2))
  board = board.replaceCell(3, 8, pieceFactory.apply("GoldenGeneral", player_2))
  board = board.replaceCell(4, 8, pieceFactory.apply("King", player_2))
  board = board.replaceCell(5, 8, pieceFactory.apply("GoldenGeneral", player_2))
  board = board.replaceCell(6, 8, pieceFactory.apply("SilverGeneral", player_2))
  board = board.replaceCell(7, 8, pieceFactory.apply("Knight", player_2))
  board = board.replaceCell(8, 8, pieceFactory.apply("Lancer", player_2))
  board = board.replaceCell(1, 7, pieceFactory.apply("Bishop", player_2))
  board = board.replaceCell(7, 7, pieceFactory.apply("Rook", player_2))
  for (i <- 0 to 8) {
    board = board.replaceCell(i, 6, pieceFactory.apply("Pawn", player_2))
  }

  "A King" when {
    "new" should {
      val piece = pieceFactory.apply("King", player_1)
      val piece2 = pieceFactory.apply("King", player_2)
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
        piece.toString should be("K° ")
      }
      "have a full String representation" in {
        piece.toStringLong should be("King")
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
      val piece = pieceFactory.apply("GoldenGeneral", player_1)
      val piece2 = pieceFactory.apply("GoldenGeneral", player_2)
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
        piece.toString should be("GG°")
        piece2.toString should be("GG ")
      }
      "have a full String representation" in {
        piece.toStringLong should be("GoldenGeneral")
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
      val piece = pieceFactory.apply("SilverGeneral", player_1)
      val piece2 = pieceFactory.apply("SilverGeneral", player_2)
      "have a Player" in {
        piece.player should be(Player("Player 1", true))
      }
      "should not have a promotion" in {
        piece.hasPromotion should be(true)
      }
      "promotion should be none" in {
        piece.promotePiece should be(Some(pieceFactory.apply("PromotedSilver", piece.player)))
      }
      "have a nice String representation" in {
        piece.toString should be("SG°")
        piece2.toString should be("SG ")
      }
      "have a full String representation" in {
        piece.toStringLong should be("SilverGeneral")
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
      val piece = pieceFactory.apply("PromotedSilver", player_1)
      val piece2 = pieceFactory.apply("PromotedSilver", player_2)
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
        piece.toString should be("PS°")
        piece2.toString should be("PS ")
      }
      "have a full String representation" in {
        piece.toStringLong should be("PromotedSilver")
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
      val piece = pieceFactory.apply("Knight", player_1)
      val piece2 = pieceFactory.apply("Knight", player_2)
      "have a Player" in {
        piece.player should be(Player("Player 1", true))
      }
      "should not have a promotion" in {
        piece.hasPromotion should be(true)
      }
      "promotion should be none" in {
        piece.promotePiece should be(Some(pieceFactory.apply("PromotedKnight", piece.player)))
      }
      "have a nice String representation" in {
        piece.toString should be("KN°")
        piece2.toString should be("KN ")
      }
      "have a full String representation" in {
        piece.toStringLong should be("Knight")
      }
      "have a List of Moves (1, 0)" in {
        piece.getMoveSet((1, 0), board) should be(List[(Int, Int)]())
      }
      "have a List of Moves (1, 8)" in {
        piece2.getMoveSet((1, 8), board) should be(List[(Int, Int)]())
      }
      "have a List of Moves (4, 5) " in {
        piece2.getMoveSet((4, 5), board) should be(List[(Int, Int)]((3, 3), (5, 3)))
      }
    }
  }

  "A PromotedKnight" when {
    "new" should {
      val piece = pieceFactory.apply("PromotedKnight", player_1)
      val piece2 = pieceFactory.apply("PromotedKnight", player_2)
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
        piece.toString should be("PK°")
        piece2.toString should be("PK ")
      }
      "have a full String representation" in {
        piece.toStringLong should be("PromotedKnight")
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

  "A Lancer" when {
    "new" should {
      val piece = pieceFactory.apply("Lancer", player_1)
      val piece2 = pieceFactory.apply("Lancer", player_2)
      "have a Player" in {
        piece.player should be(Player("Player 1", true))
      }
      "should not have a promotion" in {
        piece.hasPromotion should be(true)
      }
      "promotion should be none" in {
        piece.promotePiece should be(Some(pieceFactory.apply("PromotedLancer", piece.player)))
      }
      "have a nice String representation" in {
        piece.toString should be("L° ")
      }
      "have a full String representation" in {
        piece.toStringLong should be("Lancer")
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
      val piece = pieceFactory.apply("PromotedLancer", player_1)
      val piece2 = pieceFactory.apply("PromotedLancer", player_2)
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
        piece.toString should be("PL°")
        piece2.toString should be("PL ")
      }
      "have a full String representation" in {
        piece.toStringLong should be("PromotedLancer")
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

  "A Bishop" when {
    "new" should {
      val piece = pieceFactory.apply("Bishop", player_1)
      val piece2 = pieceFactory.apply("Bishop", player_2)
      "have a Player" in {
        piece.player should be(Player("Player 1", true))
      }
      "should not have a promotion" in {
        piece.hasPromotion should be(true)
      }
      "promotion should be none" in {
        piece.promotePiece should be(Some(pieceFactory.apply("PromotedBishop", piece.player)))
      }
      "have a nice String representation" in {
        piece.toString should be("B° ")
      }
      "have a full String representation" in {
        piece.toStringLong should be("Bishop")
      }
      "have a List of Moves (7, 0)" in {
        piece.getMoveSet((7, 1), board) should be(List[(Int, Int)]())
      }
      "have a List of Moves (1, 7)" in {
        piece2.getMoveSet((1, 7), board) should be(List[(Int, Int)]())
      }
      "have a List of Moves (4,4) " in {
        piece2.getMoveSet((4, 4), board) should be(List[(Int, Int)]((3, 3), (2, 2), (3, 5), (5, 5), (5, 3), (6, 2)))
      }
    }
  }

  "A PromotedBishop" when {
    "new" should {
      val piece = pieceFactory.apply("PromotedBishop", player_1)
      val piece2 = pieceFactory.apply("PromotedBishop", player_2)
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
        piece.toString should be("PB°")
        piece2.toString should be("PB ")
      }
      "have a full String representation" in {
        piece.toStringLong should be("PromotedBishop")
      }
      "have a List of Moves (7, 0)" in {
        piece.getMoveSet((7, 1), board) should be(List[(Int, Int)]((8, 1), (6, 1)))
      }
      "have a List of Moves (1, 7)" in {
        piece2.getMoveSet((1, 7), board) should be(List[(Int, Int)]((2, 7), (0, 7)))
      }
      "have a List of Moves (4,4) " in {
        piece2.getMoveSet((4, 4), board) should be(List[(Int, Int)]((3, 3), (2, 2), (3, 5), (5, 5), (5, 3), (6, 2), (4, 5), (5, 4), (4, 3), (3, 4)))
      }
    }
  }

  "A Rook" when {
    "new" should {
      val piece = pieceFactory.apply("Rook", player_1)
      val piece2 = pieceFactory.apply("Rook", player_2)
      "have a Player" in {
        piece.player should be(Player("Player 1", true))
      }
      "should not have a promotion" in {
        piece.hasPromotion should be(true)
      }
      "promotion should be none" in {
        piece.promotePiece should be(Some(pieceFactory.apply("PromotedRook", piece.player)))
      }
      "have a nice String representation" in {
        piece.toString should be("R° ")
      }
      "have a full String representation" in {
        piece.toStringLong should be("Rook")
      }
      "have a List of Moves (1, 1)" in {
        piece.getMoveSet((1, 1), board) should be(List[(Int, Int)]((2, 1), (3, 1), (4, 1), (5, 1), (6, 1), (0, 1)))
      }
      "have a List of Moves (7, 7)" in {
        piece2.getMoveSet((7, 7), board) should be(List[(Int, Int)]((8, 7), (6, 7), (5, 7), (4, 7), (3, 7), (2, 7)))
      }
      "have a List of Moves (4,4) " in {
        piece2.getMoveSet((4, 4), board) should be(List[(Int, Int)]((4, 5), (5, 4), (6, 4), (7, 4), (8, 4), (4, 3), (4, 2), (3, 4), (2, 4), (1, 4), (0, 4)))
      }
    }
  }

  "A PromotedRook" when {
    "new" should {
      val piece = pieceFactory.apply("PromotedRook", player_1)
      val piece2 = pieceFactory.apply("PromotedRook", player_2)
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
        piece.toString should be("PR°")
        piece2.toString should be("PR ")
      }
      "have a full String representation" in {
        piece.toStringLong should be("PromotedRook")
      }
      "have a List of Moves (1, 1)" in {
        piece.getMoveSet((1, 1), board) should be(List[(Int, Int)]((2, 1), (3, 1), (4, 1), (5, 1), (6, 1), (0, 1)))
      }
      "have a List of Moves (7, 7)" in {
        piece2.getMoveSet((7, 7), board) should be(List[(Int, Int)]((8, 7), (6, 7), (5, 7), (4, 7), (3, 7), (2, 7)))
      }
      "have a List of Moves (4,4) " in {
        piece2.getMoveSet((4, 4), board) should be(List[(Int, Int)]((4, 5), (5, 4), (6, 4), (7, 4), (8, 4), (4, 3), (4, 2), (3, 4), (2, 4), (1, 4), (0, 4), (3, 3), (3, 5), (5, 5), (5, 3)))
      }
    }
  }

  "A Pawn" when {
    "new" should {
      val piece = pieceFactory.apply("Pawn", player_1)
      val piece2 = pieceFactory.apply("Pawn", player_2)
      "have a Player" in {
        piece.player should be(Player("Player 1", true))
      }
      "should not have a promotion" in {
        piece.hasPromotion should be(true)
      }
      "promotion should be none" in {
        piece.promotePiece should be(Some(pieceFactory.apply("PromotedPawn", piece.player)))
      }
      "have a nice String representation" in {
        piece.toString should be("P° ")
      }
      "have a full String representation" in {
        piece.toStringLong should be("Pawn")
      }
      "have a List of Moves (1, 1)" in {
        piece.getMoveSet((1, 2), board) should be(List[(Int, Int)]((1, 3)))
      }
      "have a List of Moves (7, 7)" in {
        piece2.getMoveSet((1, 6), board) should be(List[(Int, Int)]((1, 5)))
      }
      "have a List of Moves (4,4) " in {
        piece2.getMoveSet((4, 4), board) should be(List[(Int, Int)]((4, 3)))
      }
    }
  }

  "A PromotedPawn" when {
    "new" should {
      val piece = pieceFactory.apply("PromotedPawn", player_1)
      val piece2 = pieceFactory.apply("PromotedPawn", player_2)
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
        piece.toString should be("PP°")
        piece2.toString should be("PP ")
      }
      "have a full String representation" in {
        piece.toStringLong should be("PromotedPawn")
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

  "A EmptyPiece" when {
    "new" should {
      val piece = pieceFactory.apply("EmptyPiece", player_1)
      "have a Player" in {
        piece.player should be(Player("", false))
      }
      "should not have a promotion" in {
        piece.hasPromotion should be(false)
      }
      "promotion should be none" in {
        piece.promotePiece should be(None)
      }
      "have a nice String representation" in {
        piece.toString should be("   ")
      }
      "have a full String representation" in {
        piece.toStringLong should be("   ")
      }
      "have a List of Moves (3, 0)" in {
        piece.getMoveSet((3, 0), board) should be(List[(Int, Int)]())
      }
    }
  }

  "A Piece" when {
    "called cloneToNewPlayer" should {
      "clone King(player_1) to King(player_2)" in {
        val p: Piece = pieceFactory.apply("King", player_1).cloneToNewPlayer(player_2)
        p shouldBe a[King]
        p.player should be(player_2)
      }
      "clone GoldenGeneral(player_1) to GoldenGeneral(player_2)" in {
        val p: Piece = pieceFactory.apply("GoldenGeneral", player_1).cloneToNewPlayer(player_2)
        p shouldBe a[GoldenGeneral]
        p.player should be(player_2)
      }
      "clone SilverGeneral(player_1) to SilverGeneral(player_2)" in {
        val p: Piece = pieceFactory.apply("SilverGeneral", player_1).cloneToNewPlayer(player_2)
        p shouldBe a[SilverGeneral]
        p.player should be(player_2)
      }
      "clone PromotedSilver(player_1) to SilverGeneral(player_2)" in {
        val p: Piece = pieceFactory.apply("PromotedSilver", player_1).cloneToNewPlayer(player_2)
        p shouldBe a[SilverGeneral]
        p.player should be(player_2)
      }
      "clone Knight(player_1) to Knight(player_2)" in {
        val p: Piece = pieceFactory.apply("Knight", player_1).cloneToNewPlayer(player_2)
        p shouldBe a[Knight]
        p.player should be(player_2)
      }
      "clone PromotedKnight(player_1) to Knight(player_2)" in {
        val p: Piece = pieceFactory.apply("PromotedKnight", player_1).cloneToNewPlayer(player_2)
        p shouldBe a[Knight]
        p.player should be(player_2)
      }
      "clone Lancer(player_1) to Lancer(player_2)" in {
        val p: Piece = pieceFactory.apply("Lancer", player_1).cloneToNewPlayer(player_2)
        p shouldBe a[Lancer]
        p.player should be(player_2)
      }
      "clone PromotedLancer(player_1) to Lancer(player_2)" in {
        val p: Piece = pieceFactory.apply("PromotedLancer", player_1).cloneToNewPlayer(player_2)
        p shouldBe a[Lancer]
        p.player should be(player_2)
      }
      "clone Bishop(player_1) to Bishop(player_2)" in {
        val p: Piece = pieceFactory.apply("Bishop", player_1).cloneToNewPlayer(player_2)
        p shouldBe a[Bishop]
        p.player should be(player_2)
      }
      "clone PromotedBishop(player_1) to Bishop(player_2)" in {
        val p: Piece = pieceFactory.apply("PromotedBishop", player_1).cloneToNewPlayer(player_2)
        p shouldBe a[Bishop]
        p.player should be(player_2)
      }
      "clone Rook(player_1) to Rook(player_2)" in {
        val p: Piece = pieceFactory.apply("Rook", player_1).cloneToNewPlayer(player_2)
        p shouldBe a[Rook]
        p.player should be(player_2)
      }
      "clone PromotedRook(player_1) to Rook(player_2)" in {
        val p: Piece = pieceFactory.apply("PromotedRook", player_1).cloneToNewPlayer(player_2)
        p shouldBe a[Rook]
        p.player should be(player_2)
      }
      "clone Pawn(player_1) to Pawn(player_2)" in {
        val p: Piece = pieceFactory.apply("Pawn", player_1).cloneToNewPlayer(player_2)
        p shouldBe a[Pawn]
        p.player should be(player_2)
      }
      "clone PromotedPawn(player_1) to Pawn(player_2)" in {
        val p: Piece = pieceFactory.apply("PromotedPawn", player_1).cloneToNewPlayer(player_2)
        p shouldBe a[Pawn]
        p.player should be(player_2)
      }
      "clone EmptyPiece(player_1) to EmptyPiece(player_2)" in {
        val p: Piece = pieceFactory.apply("EmptyPiece", player_1).cloneToNewPlayer(player_2)
        p shouldBe a[EmptyPiece]
      }
    }
  }

  "A Piece" when {
    "called isForstOwner" should {
      "be true when Piece belongs to first player" in {
        val piecePlayer1: Piece = pieceFactory.apply("King", player_1)
        piecePlayer1.isFirstOwner should be(true)
      }
      "be false when Piece belongs to second player" in {
        val piecePlayer2: Piece = pieceFactory.apply("King", player_2)
        piecePlayer2.isFirstOwner should be(false)
      }
    }
  }
  "A Piece" when {
    "called typeEquals" should {
      "compare a Piece with a String and determine if the String indicates the Piece(King)" in {
        val p: Piece = pieceFactory.apply("King", player_1)
        p.typeEquals("K°") should be(true)
      }
      "compare a Piece with a String and determine if the String indicates the Piece(GoldenGeneral)" in {
        val p: Piece = pieceFactory.apply("GoldenGeneral", player_1)
        p.typeEquals("GG°") should be(true)
      }
      "compare a Piece with a String and determine if the String indicates the Piece(SilverGeneral)" in {
        val p: Piece = pieceFactory.apply("SilverGeneral", player_1)
        p.typeEquals("SG°") should be(true)
      }
      "compare a Piece with a String and determine if the String indicates the Piece(PromotedSilver)" in {
        val p: Piece = pieceFactory.apply("PromotedSilver", player_1)
        p.typeEquals("PS°") should be(true)
      }
      "compare a Piece with a String and determine if the String indicates the Piece(Knight)" in {
        val p: Piece = pieceFactory.apply("Knight", player_1)
        p.typeEquals("KN°") should be(true)
      }
      "compare a Piece with a String and determine if the String indicates the Piece(PromotedKnight)" in {
        val p: Piece = pieceFactory.apply("PromotedKnight", player_1)
        p.typeEquals("PK°") should be(true)
      }
      "compare a Piece with a String and determine if the String indicates the Piece(Lancer)" in {
        val p: Piece = pieceFactory.apply("Lancer", player_1)
        p.typeEquals("L°") should be(true)
      }
      "compare a Piece with a String and determine if the String indicates the Piece(PromotedLancer)" in {
        val p: Piece = pieceFactory.apply("PromotedLancer", player_1)
        p.typeEquals("PL°") should be(true)
      }
      "compare a Piece with a String and determine if the String indicates the Piece(Bishop)" in {
        val p: Piece = pieceFactory.apply("Bishop", player_1)
        p.typeEquals("B°") should be(true)
      }
      "compare a Piece with a String and determine if the String indicates the Piece(PromotedBishop)" in {
        val p: Piece = pieceFactory.apply("PromotedBishop", player_1)
        p.typeEquals("PB°") should be(true)
      }
      "compare a Piece with a String and determine if the String indicates the Piece(Rook)" in {
        val p: Piece = pieceFactory.apply("Rook", player_1)
        p.typeEquals("R°") should be(true)
      }
      "compare a Piece with a String and determine if the String indicates the Piece(PromotedRook)" in {
        val p: Piece = pieceFactory.apply("PromotedRook", player_1)
        p.typeEquals("PR°") should be(true)
      }
      "compare a Piece with a String and determine if the String indicates the Piece(Pawn)" in {
        val p: Piece = pieceFactory.apply("Pawn", player_1)
        p.typeEquals("P°") should be(true)
      }
      "compare a Piece with a String and determine if the String indicates the Piece(PromotedPawn)" in {
        val p: Piece = pieceFactory.apply("PromotedPawn", player_1)
        p.typeEquals("PP°") should be(true)
      }
      "compare a Piece with a String and determine if the String indicates the Piece(EmptyPiece)" in {
        val p: Piece = pieceFactory.apply("EmptyPiece", player_1)
        p.typeEquals("") should be(true)
      }
    }
  }
}