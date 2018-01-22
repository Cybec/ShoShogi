package de.htwg.se.ShoShogi.model

import com.google.inject.{Guice, Injector}
import de.htwg.se.ShoShogi.ShoShogiModule
import de.htwg.se.ShoShogi.controller.controllerComponent.ControllerInterface
import de.htwg.se.ShoShogi.model.pieceComponent.pieceBaseImpl._
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{Matchers, WordSpec}

//noinspection ScalaStyle
@RunWith(classOf[JUnitRunner])
class PieceFactorySpec extends WordSpec with Matchers {
  val injector: Injector = Guice.createInjector(new ShoShogiModule)
  val controller: ControllerInterface = injector.getInstance(classOf[ControllerInterface])

  "A PieceFactory" when {
    "called apply" should {
      val (player_1, _) = controller.getPlayers
      "create King for given PiecesEnum.King" in {
        val king = PieceFactory.apply(PiecesEnum.King, player_1.first)
        PieceFactory.isInstanceOfPiece(PiecesEnum.King, king) should be(true)
      }
      "create GoldenGeneral for given PiecesEnum.GoldenGeneral" in {
        val goldenGeneral = PieceFactory.apply(PiecesEnum.GoldenGeneral, player_1.first)
        PieceFactory.isInstanceOfPiece(PiecesEnum.GoldenGeneral, goldenGeneral) should be(true)
      }
      "create SilverGeneral for given PiecesEnum.SilverGeneral" in {
        val silverGeneral = PieceFactory.apply(PiecesEnum.SilverGeneral, player_1.first)
        PieceFactory.isInstanceOfPiece(PiecesEnum.SilverGeneral, silverGeneral) should be(true)
      }
      "create PromotedSilver for given PiecesEnum.PromotedSilver" in {
        val promotedSilver = PieceFactory.apply(PiecesEnum.PromotedSilver, player_1.first)
        PieceFactory.isInstanceOfPiece(PiecesEnum.PromotedSilver, promotedSilver) should be(true)
      }
      "create Knight for given PiecesEnum.Knight" in {
        val knight = PieceFactory.apply(PiecesEnum.Knight, player_1.first)
        PieceFactory.isInstanceOfPiece(PiecesEnum.Knight, knight) should be(true)
      }
      "create PromotedKnight for given PiecesEnum.PromotedKnight" in {
        val promotedKnight = PieceFactory.apply(PiecesEnum.PromotedKnight, player_1.first)
        PieceFactory.isInstanceOfPiece(PiecesEnum.PromotedKnight, promotedKnight) should be(true)
      }
      "create Lancer for given PiecesEnum.Lancer" in {
        val lancer = PieceFactory.apply(PiecesEnum.Lancer, player_1.first)
        PieceFactory.isInstanceOfPiece(PiecesEnum.Lancer, lancer) should be(true)
      }
      "create PromotedLancer for given PiecesEnum.PromotedLancer" in {
        val promotedLancer = PieceFactory.apply(PiecesEnum.PromotedLancer, player_1.first)
        PieceFactory.isInstanceOfPiece(PiecesEnum.PromotedLancer, promotedLancer) should be(true)
      }
      "create Bishop for given PiecesEnum.Bishop" in {
        val bishop = PieceFactory.apply(PiecesEnum.Bishop, player_1.first)
        PieceFactory.isInstanceOfPiece(PiecesEnum.Bishop, bishop) should be(true)
      }
      "create PromotedBishop for given PiecesEnum.PromotedBishop" in {
        val promotedBishop = PieceFactory.apply(PiecesEnum.PromotedBishop, player_1.first)
        PieceFactory.isInstanceOfPiece(PiecesEnum.PromotedBishop, promotedBishop) should be(true)
      }
      "create Rook for given PiecesEnum.Rook" in {
        val rook = PieceFactory.apply(PiecesEnum.Rook, player_1.first)
        PieceFactory.isInstanceOfPiece(PiecesEnum.Rook, rook) should be(true)
      }
      "create PromotedRook for given PiecesEnum.PromotedRook" in {
        val promotedRook = PieceFactory.apply(PiecesEnum.PromotedRook, player_1.first)
        PieceFactory.isInstanceOfPiece(PiecesEnum.PromotedRook, promotedRook) should be(true)
      }
      "create Pawn for given PiecesEnum.Pawn" in {
        val pawn = PieceFactory.apply(PiecesEnum.Pawn, player_1.first)
        PieceFactory.isInstanceOfPiece(PiecesEnum.Pawn, pawn) should be(true)
      }
      "create PromotedPawn for given PiecesEnum.PromotedPawn" in {
        val promotedPawn = PieceFactory.apply(PiecesEnum.PromotedPawn, player_1.first)
        PieceFactory.isInstanceOfPiece(PiecesEnum.PromotedPawn, promotedPawn) should be(true)
      }
      "create EmptyPiece for given PiecesEnum.EmptyPiece" in {
        val emptyPiece = PieceFactory.apply(PiecesEnum.EmptyPiece, player_1.first)
        PieceFactory.isInstanceOfPiece(PiecesEnum.EmptyPiece, emptyPiece) should be(true)
      }
    }
  }

  "A PiecesEnum" when {
    "called withNameOpt" should {
      val (player_1, _) = controller.getPlayers
      "return an Option with PiecesEnum.King if the string given is King" in {
        val king = PiecesEnum.withNameOpt("King").getOrElse(PiecesEnum.EmptyPiece)
        val king2 = PieceFactory.apply(king, player_1)
        PieceFactory.isInstanceOfPiece(PiecesEnum.King, king2) should be(true)
      }
      "return an Option with PiecesEnum.GoldenGeneral if the string given is GoldenGeneral" in {
        val gg = PiecesEnum.withNameOpt("GoldenGeneral").getOrElse(PiecesEnum.EmptyPiece)
        val gg2 = PieceFactory.apply(gg, player_1)
        PieceFactory.isInstanceOfPiece(PiecesEnum.GoldenGeneral, gg2) should be(true)
      }
      "return an Option with PiecesEnum.SilverGeneral if the string given is SilverGenera" in {
        val sg = PiecesEnum.withNameOpt("SilverGeneral").getOrElse(PiecesEnum.EmptyPiece)
        val sg2 = PieceFactory.apply(sg, player_1)
        PieceFactory.isInstanceOfPiece(PiecesEnum.SilverGeneral, sg2) should be(true)
      }
      "return an Option with PiecesEnum.PromotedSilver if the string given is PromotedSilver" in {
        val ps = PiecesEnum.withNameOpt("PromotedSilver").getOrElse(PiecesEnum.EmptyPiece)
        val ps2 = PieceFactory.apply(ps, player_1)
        PieceFactory.isInstanceOfPiece(PiecesEnum.PromotedSilver, ps2) should be(true)
      }
      "return an Option with PiecesEnum.Lancer if the string given is Lancer" in {
        val lancer = PiecesEnum.withNameOpt("Lancer").getOrElse(PiecesEnum.EmptyPiece)
        val lancer2 = PieceFactory.apply(lancer, player_1)
        PieceFactory.isInstanceOfPiece(PiecesEnum.Lancer, lancer2) should be(true)
      }
      "return an Option with PiecesEnum.PromotedLancer if the string given is PromotedLancer" in {
        val promotedLancer = PiecesEnum.withNameOpt("PromotedLancer").getOrElse(PiecesEnum.EmptyPiece)
        val promotedLancer2 = PieceFactory.apply(promotedLancer, player_1)
        PieceFactory.isInstanceOfPiece(PiecesEnum.PromotedLancer, promotedLancer2) should be(true)
      }
      "return an Option with PiecesEnum.Knight if the string given is Knight" in {
        val knight = PiecesEnum.withNameOpt("Knight").getOrElse(PiecesEnum.EmptyPiece)
        val knight2 = PieceFactory.apply(knight, player_1)
        PieceFactory.isInstanceOfPiece(PiecesEnum.Knight, knight2) should be(true)
      }
      "return an Option with PiecesEnum.PromotedKnight if the string given is PromotedKnight" in {
        val promotedKnight = PiecesEnum.withNameOpt("PromotedKnight").getOrElse(PiecesEnum.EmptyPiece)
        val promotedKnight2 = PieceFactory.apply(promotedKnight, player_1)
        PieceFactory.isInstanceOfPiece(PiecesEnum.PromotedKnight, promotedKnight2) should be(true)
      }
      "return an Option with PiecesEnum.Bishop if the string given is Bishop" in {
        val bishop = PiecesEnum.withNameOpt("Bishop").getOrElse(PiecesEnum.EmptyPiece)
        val bishop2 = PieceFactory.apply(bishop, player_1)
        PieceFactory.isInstanceOfPiece(PiecesEnum.Bishop, bishop2) should be(true)
      }
      "return an Option with PiecesEnum.PromotedBishop if the string given is PromotedBishop" in {
        val promotedBishop = PiecesEnum.withNameOpt("PromotedBishop").getOrElse(PiecesEnum.EmptyPiece)
        val promotedBishop2 = PieceFactory.apply(promotedBishop, player_1)
        PieceFactory.isInstanceOfPiece(PiecesEnum.PromotedBishop, promotedBishop2) should be(true)
      }
      "return an Option with PiecesEnum.Rook if the string given is Rook" in {
        val rook = PiecesEnum.withNameOpt("Rook").getOrElse(PiecesEnum.EmptyPiece)
        val rook2 = PieceFactory.apply(rook, player_1)
        PieceFactory.isInstanceOfPiece(PiecesEnum.Rook, rook2) should be(true)
      }
      "return an Option with PiecesEnum.PromotedRook if the string given is PromotedRook" in {
        val promotedRook = PiecesEnum.withNameOpt("PromotedRook").getOrElse(PiecesEnum.EmptyPiece)
        val promotedRook2 = PieceFactory.apply(promotedRook, player_1)
        PieceFactory.isInstanceOfPiece(PiecesEnum.PromotedRook, promotedRook2) should be(true)
      }
      "return an Option with PiecesEnum.Pawn if the string given is Pawn" in {
        val pawn = PiecesEnum.withNameOpt("Pawn").getOrElse(PiecesEnum.EmptyPiece)
        val pawn2 = PieceFactory.apply(pawn, player_1)
        PieceFactory.isInstanceOfPiece(PiecesEnum.Pawn, pawn2) should be(true)
      }
      "return an Option with PiecesEnum.PromotedPawn if the string given is PromotedPawn" in {
        val promotedPawn = PiecesEnum.withNameOpt("PromotedPawn").getOrElse(PiecesEnum.EmptyPiece)
        val promotedPawn2 = PieceFactory.apply(promotedPawn, player_1)
        PieceFactory.isInstanceOfPiece(PiecesEnum.PromotedPawn, promotedPawn2) should be(true)
      }
      "return an Option with PiecesEnum.EmptyPiece if the string given is EmptyPiece" in {
        val emptyPiece = PiecesEnum.withNameOpt("EmptyPiece").getOrElse(PiecesEnum.EmptyPiece)
        val emptyPiece2 = PieceFactory.apply(emptyPiece, player_1)
        PieceFactory.isInstanceOfPiece(PiecesEnum.EmptyPiece, emptyPiece2) should be(true)
      }
    }
  }
}