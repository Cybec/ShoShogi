package de.htwg.se.ShoShogi.model

import de.htwg.se.ShoShogi.model.boardComponent.boardBaseImpl.Board
import de.htwg.se.ShoShogi.model.pieceComponent.pieceBaseImpl.{PieceFactory, PiecesEnum}
import de.htwg.se.ShoShogi.model.playerComponent.Player
import org.junit.runner.RunWith
import org.scalatest._
import org.scalatest.junit.JUnitRunner

//noinspection ScalaStyle
@RunWith(classOf[JUnitRunner])
class PieceSpec extends WordSpec with Matchers {
  val boardSize = 9
  val player_1: Player = Player("Player 1", first = true)
  val player_2: Player = Player("Player 2", first = false)
  var board = new Board(boardSize, PieceFactory.apply(PiecesEnum.EmptyPiece, player_1.first))

  board = board.replaceCell(0, 0, PieceFactory.apply(PiecesEnum.Lancer, player_1.first))
  board = board.replaceCell(1, 0, PieceFactory.apply(PiecesEnum.Knight, player_1.first))
  board = board.replaceCell(2, 0, PieceFactory.apply(PiecesEnum.SilverGeneral, player_1.first))
  board = board.replaceCell(3, 0, PieceFactory.apply(PiecesEnum.GoldenGeneral, player_1.first))
  board = board.replaceCell(4, 0, PieceFactory.apply(PiecesEnum.King, player_1.first))
  board = board.replaceCell(5, 0, PieceFactory.apply(PiecesEnum.GoldenGeneral, player_1.first))
  board = board.replaceCell(6, 0, PieceFactory.apply(PiecesEnum.SilverGeneral, player_1.first))
  board = board.replaceCell(7, 0, PieceFactory.apply(PiecesEnum.Knight, player_1.first))
  board = board.replaceCell(8, 0, PieceFactory.apply(PiecesEnum.Lancer, player_1.first))
  board = board.replaceCell(7, 1, PieceFactory.apply(PiecesEnum.Bishop, player_1.first))
  board = board.replaceCell(1, 1, PieceFactory.apply(PiecesEnum.Rook, player_1.first))
  for (i <- 0 to 8) {
    board = board.replaceCell(i, 2, PieceFactory.apply(PiecesEnum.Pawn, player_1.first))
  }

  board = board.replaceCell(0, 8, PieceFactory.apply(PiecesEnum.Lancer, player_2.first))
  board = board.replaceCell(1, 8, PieceFactory.apply(PiecesEnum.Knight, player_2.first))
  board = board.replaceCell(2, 8, PieceFactory.apply(PiecesEnum.SilverGeneral, player_2.first))
  board = board.replaceCell(3, 8, PieceFactory.apply(PiecesEnum.GoldenGeneral, player_2.first))
  board = board.replaceCell(4, 8, PieceFactory.apply(PiecesEnum.King, player_2.first))
  board = board.replaceCell(5, 8, PieceFactory.apply(PiecesEnum.GoldenGeneral, player_2.first))
  board = board.replaceCell(6, 8, PieceFactory.apply(PiecesEnum.SilverGeneral, player_2.first))
  board = board.replaceCell(7, 8, PieceFactory.apply(PiecesEnum.Knight, player_2.first))
  board = board.replaceCell(8, 8, PieceFactory.apply(PiecesEnum.Lancer, player_2.first))
  board = board.replaceCell(1, 7, PieceFactory.apply(PiecesEnum.Bishop, player_2.first))
  board = board.replaceCell(7, 7, PieceFactory.apply(PiecesEnum.Rook, player_2.first))
  for (i <- 0 to 8) {
    board = board.replaceCell(i, 6, PieceFactory.apply(PiecesEnum.Pawn, player_2.first))
  }

  "A King" when {
    "new" should {
      val piece = PieceFactory.apply(PiecesEnum.King, player_1.first)
      val piece2 = PieceFactory.apply(PiecesEnum.King, player_2.first)
      "have a Player" in {
        piece.isFirstOwner should be(true)
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
      "have a nice Long String representation" in {
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
      "be true when typEquals is King" in {
        piece.typeEquals("K°") should be(true)
      }
      "be false when typEquals is not King" in {
        piece.typeEquals("Pwn") should be(false)
      }
    }
  }

  "A GoldenGeneral" when {
    "new" should {
      val piece = PieceFactory.apply(PiecesEnum.GoldenGeneral, player_1.first)
      val piece2 = PieceFactory.apply(PiecesEnum.GoldenGeneral, player_2.first)
      "have a Player" in {
        piece.isFirstOwner should be(true)
      }
      "should not have a promotion" in {
        piece.hasPromotion should be(false)
      }
      "promotion should be none" in {
        piece.promotePiece should be(None)
      }
      "have a nice String representation" in {
        piece.toString should be("GG°")
      }
      "have a nice Long String representation" in {
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
      "be true when typEquals is GoldenGeneral" in {
        piece.typeEquals("GG°") should be(true)
      }
      "be false when typEquals is not GoldenGeneral" in {
        piece.typeEquals("Pawn") should be(false)
      }
    }
  }

  "A SilverGeneral" when {
    "new" should {
      val piece = PieceFactory.apply(PiecesEnum.SilverGeneral, player_1.first)
      val piece2 = PieceFactory.apply(PiecesEnum.SilverGeneral, player_2.first)
      "have a Player" in {
        piece.isFirstOwner should be(true)
      }
      "should not have a promotion" in {
        piece.hasPromotion should be(true)
      }
      "promotion should be PromotedSilver" in {
        piece.promotePiece should be(Some(PieceFactory.apply(PiecesEnum.PromotedSilver, piece.isFirstOwner)))
      }
      "have a nice String representation" in {
        piece.toString should be("SG°")
      }
      "have a nice Long String representation" in {
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
      "be true when typEquals is SilverGeneral" in {
        piece.typeEquals("SG°") should be(true)
      }
      "be false when typEquals is not SilverGeneral" in {
        piece.typeEquals("Pawn") should be(false)
      }
    }
  }

  "A PromotedSilver" when {
    "new" should {
      val piece = PieceFactory.apply(PiecesEnum.PromotedSilver, player_1.first)
      val piece2 = PieceFactory.apply(PiecesEnum.PromotedSilver, player_2.first)
      "have a Player" in {
        piece.isFirstOwner should be(true)
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
      "have a nice Long String representation" in {
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
      "be true when typEquals is PromotedSilver" in {
        piece.typeEquals("PS°") should be(true)
      }
      "be false when typEquals is not PromotedSilver" in {
        piece.typeEquals("Pawn") should be(false)
      }
    }
  }

  "A Knight" when {
    "new" should {
      val piece = PieceFactory.apply(PiecesEnum.Knight, player_1.first)
      val piece2 = PieceFactory.apply(PiecesEnum.Knight, player_2.first)
      "have a Player" in {
        piece.isFirstOwner should be(true)
      }
      "should not have a promotion" in {
        piece.hasPromotion should be(true)
      }
      "promotion should be PromotedKnight" in {
        piece.promotePiece should be(Some(PieceFactory.apply(PiecesEnum.PromotedKnight, piece.isFirstOwner)))
      }
      "have a nice String representation" in {
        piece.toString should be("KN°")
      }
      "have a nice Long String representation" in {
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
      "be true when typEquals is Knight" in {
        piece.typeEquals("KN°") should be(true)
      }
      "be false when typEquals is not Knight" in {
        piece.typeEquals("Pawn") should be(false)
      }
    }
  }

  "A PromotedKnight" when {
    "new" should {
      val piece = PieceFactory.apply(PiecesEnum.PromotedKnight, player_1.first)
      val piece2 = PieceFactory.apply(PiecesEnum.PromotedKnight, player_2.first)
      "have a Player" in {
        piece.isFirstOwner should be(true)
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
      "have a nice Long String representation" in {
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
      "be true when typEquals is PromotedKnight" in {
        piece.typeEquals("PK°") should be(true)
      }
      "be false when typEquals is not PromotedKnight" in {
        piece.typeEquals("Pawn") should be(false)
      }
    }
  }

  "A Lancer" when {
    "new" should {
      val piece = PieceFactory.apply(PiecesEnum.Lancer, player_1.first)
      val piece2 = PieceFactory.apply(PiecesEnum.Lancer, player_2.first)
      "have a Player" in {
        piece.isFirstOwner should be(true)
      }
      "should not have a promotion" in {
        piece.hasPromotion should be(true)
      }
      "promotion should be PromotedLancer" in {
        piece.promotePiece should be(Some(PieceFactory.apply(PiecesEnum.PromotedLancer, piece.isFirstOwner)))
      }
      "have a nice String representation" in {
        piece.toString should be("L° ")
      }
      "have a nice Long String representation" in {
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
      "be true when typEquals is Lancer" in {
        piece.typeEquals("L°") should be(true)
      }
      "be false when typEquals is not Lancer" in {
        piece.typeEquals("Pawn") should be(false)
      }
    }
  }

  "A PromotedLancer" when {
    "new" should {
      val piece = PieceFactory.apply(PiecesEnum.PromotedLancer, player_1.first)
      val piece2 = PieceFactory.apply(PiecesEnum.PromotedLancer, player_2.first)
      "have a Player" in {
        piece.isFirstOwner should be(true)
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
      "have a nice Long String representation" in {
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
      "be true when typEquals is PromotedLancer" in {
        piece.typeEquals("PL°") should be(true)
      }
      "be false when typEquals is not PromotedLancer" in {
        piece.typeEquals("Pawn") should be(false)
      }
    }
  }

  "A Bishop" when {
    "new" should {
      val piece = PieceFactory.apply(PiecesEnum.Bishop, player_1.first)
      val piece2 = PieceFactory.apply(PiecesEnum.Bishop, player_2.first)
      "have a Player" in {
        piece.isFirstOwner should be(true)
      }
      "should not have a promotion" in {
        piece.hasPromotion should be(true)
      }
      "promotion should be PromotedBishop" in {
        piece.promotePiece should be(Some(PieceFactory.apply(PiecesEnum.PromotedBishop, piece.isFirstOwner)))
      }
      "have a nice String representation" in {
        piece.toString should be("B° ")
      }
      "have a nice Long String representation" in {
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
      "be true when typEquals is Bishop" in {
        piece.typeEquals("B°") should be(true)
      }
      "be false when typEquals is not Bishop" in {
        piece.typeEquals("Pawn") should be(false)
      }
    }
  }

  "A PromotedBishop" when {
    "new" should {
      val piece = PieceFactory.apply(PiecesEnum.PromotedBishop, player_1.first)
      val piece2 = PieceFactory.apply(PiecesEnum.PromotedBishop, player_2.first)
      "have a Player" in {
        piece.isFirstOwner should be(true)
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
      "have a nice Long String representation" in {
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
      "be true when typEquals is PromotedBishop" in {
        piece.typeEquals("PB°") should be(true)
      }
      "be false when typEquals is not PromotedBishop" in {
        piece.typeEquals("Pawn") should be(false)
      }
    }
  }

  "A Rook" when {
    "new" should {
      val piece = PieceFactory.apply(PiecesEnum.Rook, player_1.first)
      val piece2 = PieceFactory.apply(PiecesEnum.Rook, player_2.first)
      "have a Player" in {
        piece.isFirstOwner should be(true)
      }
      "should not have a promotion" in {
        piece.hasPromotion should be(true)
      }
      "promotion should be PromotedRook" in {
        piece.promotePiece should be(Some(PieceFactory.apply(PiecesEnum.PromotedRook, piece.isFirstOwner)))
      }
      "have a nice String representation" in {
        piece.toString should be("R° ")
      }
      "have a nice Long String representation" in {
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
      "be true when typEquals is Rook" in {
        piece.typeEquals("R°") should be(true)
      }
      "be false when typEquals is not Rook" in {
        piece.typeEquals("Pawn") should be(false)
      }
    }
  }

  "A PromotedRook" when {
    "new" should {
      val piece = PieceFactory.apply(PiecesEnum.PromotedRook, player_1.first)
      val piece2 = PieceFactory.apply(PiecesEnum.PromotedRook, player_2.first)
      "have a Player" in {
        piece.isFirstOwner should be(true)
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
      "have a nice Long String representation" in {
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
      "be true when typEquals is PromotedRook" in {
        piece.typeEquals("PR°") should be(true)
      }
      "be false when typEquals is not PromotedRook" in {
        piece.typeEquals("Pawn") should be(false)
      }
    }
  }

  "A Pawn" when {
    "new" should {
      val piece = PieceFactory.apply(PiecesEnum.Pawn, player_1.first)
      val piece2 = PieceFactory.apply(PiecesEnum.Pawn, player_2.first)
      "have a Player" in {
        piece.isFirstOwner should be(true)
      }
      "should not have a promotion" in {
        piece.hasPromotion should be(true)
      }
      "promotion should be PromotedPawn" in {
        piece.promotePiece should be(Some(PieceFactory.apply(PiecesEnum.PromotedPawn, piece.isFirstOwner)))
      }
      "have a nice String representation" in {
        piece.toString should be("P° ")
        piece2.toString should be("P  ")
      }
      "have a nice Long String representation" in {
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
      "be true when typEquals is Pawn" in {
        piece.typeEquals("P°") should be(true)
      }
      "be false when typEquals is not Pawn" in {
        piece.typeEquals("King") should be(false)
      }
    }
  }

  "A PromotedPawn" when {
    "new" should {
      val piece = PieceFactory.apply(PiecesEnum.PromotedPawn, player_1.first)
      val piece2 = PieceFactory.apply(PiecesEnum.PromotedPawn, player_2.first)
      "have a Player" in {
        piece.isFirstOwner should be(true)
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
      "have a nice Long String representation" in {
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
      "be true when typEquals is PromotedPawn" in {
        piece.typeEquals("PP°") should be(true)
      }
      "be false when typEquals is not PromotedPawn" in {
        piece.typeEquals("King") should be(false)
      }
    }
  }

  "A EmptyPiece" when {
    "new" should {
      val piece = PieceFactory.apply(PiecesEnum.EmptyPiece, player_1.first)
      "have a Player" in {
        piece.isFirstOwner should be(false)
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
      "have a nice Long String representation" in {
        piece.toStringLong should be("EmptyPiece")
      }
      "have a List of Moves (3, 0)" in {
        piece.getMoveSet((3, 0), board) should be(List[(Int, Int)]())
      }
      "be true when typEquals is EmptyPiece" in {
        piece.typeEquals("") should be(true)
      }
      "be false when typEquals is not EmptyPiece" in {
        piece.typeEquals("King") should be(false)
      }
    }
  }

  "A Piece" when {
    "called cloneToNewPlayer" should {
      "clone King(player_1.first) to King(player_2.first)" in {
        val p = PieceFactory.apply(PiecesEnum.King, player_1.first).cloneToNewPlayer(player_2.first)
        PieceFactory.isInstanceOfPiece(PiecesEnum.King, p) should be(true)
        p.isFirstOwner should be(player_2.first)
      }
      "clone GoldenGeneral(player_1.first) to GoldenGeneral(player_2.first)" in {
        val p = PieceFactory.apply(PiecesEnum.GoldenGeneral, player_1.first).cloneToNewPlayer(player_2.first)
        PieceFactory.isInstanceOfPiece(PiecesEnum.GoldenGeneral, p) should be(true)
        p.isFirstOwner should be(player_2.first)
      }
      "clone SilverGeneral(player_1.first) to SilverGeneral(player_2.first)" in {
        val p = PieceFactory.apply(PiecesEnum.SilverGeneral, player_1.first).cloneToNewPlayer(player_2.first)
        PieceFactory.isInstanceOfPiece(PiecesEnum.SilverGeneral, p) should be(true)
        p.isFirstOwner should be(player_2.first)
      }
      "clone PromotedSilver(player_1.first) to SilverGeneral(player_2.first)" in {
        val p = PieceFactory.apply(PiecesEnum.PromotedSilver, player_1.first).cloneToNewPlayer(player_2.first)
        PieceFactory.isInstanceOfPiece(PiecesEnum.SilverGeneral, p) should be(true)
        p.isFirstOwner should be(player_2.first)
      }
      "clone Knight(player_1.first) to Knight(player_2.first)" in {
        val p = PieceFactory.apply(PiecesEnum.Knight, player_1.first).cloneToNewPlayer(player_2.first)
        PieceFactory.isInstanceOfPiece(PiecesEnum.Knight, p) should be(true)
        p.isFirstOwner should be(player_2.first)
      }
      "clone PromotedKnight(player_1.first) to Knight(player_2.first)" in {
        val p = PieceFactory.apply(PiecesEnum.PromotedKnight, player_1.first).cloneToNewPlayer(player_2.first)
        PieceFactory.isInstanceOfPiece(PiecesEnum.Knight, p) should be(true)
        p.isFirstOwner should be(player_2.first)
      }
      "clone Lancer(player_1.first) to Lancer(player_2.first)" in {
        val p = PieceFactory.apply(PiecesEnum.Lancer, player_1.first).cloneToNewPlayer(player_2.first)
        PieceFactory.isInstanceOfPiece(PiecesEnum.Lancer, p) should be(true)
        p.isFirstOwner should be(player_2.first)
      }
      "clone PromotedLancer(player_1.first) to Lancer(player_2.first)" in {
        val p = PieceFactory.apply(PiecesEnum.PromotedLancer, player_1.first).cloneToNewPlayer(player_2.first)
        PieceFactory.isInstanceOfPiece(PiecesEnum.Lancer, p) should be(true)
        p.isFirstOwner should be(player_2.first)
      }
      "clone Bishop(player_1.first) to Bishop(player_2.first)" in {
        val p = PieceFactory.apply(PiecesEnum.Bishop, player_1.first).cloneToNewPlayer(player_2.first)
        PieceFactory.isInstanceOfPiece(PiecesEnum.Bishop, p) should be(true)
        p.isFirstOwner should be(player_2.first)
      }
      "clone PromotedBishop(player_1.first) to Bishop(player_2.first)" in {
        val p = PieceFactory.apply(PiecesEnum.PromotedBishop, player_1.first).cloneToNewPlayer(player_2.first)
        PieceFactory.isInstanceOfPiece(PiecesEnum.Bishop, p) should be(true)
        p.isFirstOwner should be(player_2.first)
      }
      "clone Rook(player_1.first) to Rook(player_2.first)" in {
        val p = PieceFactory.apply(PiecesEnum.Rook, player_1.first).cloneToNewPlayer(player_2.first)
        PieceFactory.isInstanceOfPiece(PiecesEnum.Rook, p) should be(true)
        p.isFirstOwner should be(player_2.first)
      }
      "clone PromotedRook(player_1.first) to Rook(player_2.first)" in {
        val p = PieceFactory.apply(PiecesEnum.PromotedRook, player_1.first).cloneToNewPlayer(player_2.first)
        PieceFactory.isInstanceOfPiece(PiecesEnum.Rook, p) should be(true)
        p.isFirstOwner should be(player_2.first)
      }
      "clone Pawn(player_1.first) to Pawn(player_2.first)" in {
        val p = PieceFactory.apply(PiecesEnum.Pawn, player_1.first).cloneToNewPlayer(player_2.first)
        PieceFactory.isInstanceOfPiece(PiecesEnum.Pawn, p) should be(true)
        p.isFirstOwner should be(player_2.first)
      }
      "clone PromotedPawn(player_1.first) to Pawn(player_2.first)" in {
        val p = PieceFactory.apply(PiecesEnum.PromotedPawn, player_1.first).cloneToNewPlayer(player_2.first)
        PieceFactory.isInstanceOfPiece(PiecesEnum.Pawn, p) should be(true)
        p.isFirstOwner should be(player_2.first)
      }
      "clone EmptyPiece(player_1.first) to EmptyPiece(player_2.first)" in {
        val p = PieceFactory.apply(PiecesEnum.EmptyPiece, player_1.first).cloneToNewPlayer(player_2.first)
        PieceFactory.isInstanceOfPiece(PiecesEnum.EmptyPiece, p) should be(true)
      }
    }
  }

  "A Piece" when {
    "called isFirstOwner" should {
      "be true when Piece belongs to first player" in {
        val piecePlayer1 = PieceFactory.apply(PiecesEnum.King, player_1.first)
        piecePlayer1.isFirstOwner should be(true)
      }
      "be false when Piece belongs to second player" in {
        val piecePlayer2 = PieceFactory.apply(PiecesEnum.King, player_2.first)
        piecePlayer2.isFirstOwner should be(false)
      }
    }
  }
}