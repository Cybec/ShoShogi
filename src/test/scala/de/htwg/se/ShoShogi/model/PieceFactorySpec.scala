package de.htwg.se.ShoShogi.model

import com.google.inject.Guice
import de.htwg.se.ShoShogi.ShoShogiModule
import de.htwg.se.ShoShogi.controller.controllerComponent.ControllerInterface
import de.htwg.se.ShoShogi.model.pieceComponent.pieceBaseImpl._
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{Matchers, WordSpec}

//noinspection ScalaStyle
@RunWith(classOf[JUnitRunner])
class PieceFactorySpec extends WordSpec with Matchers {
  val injector = Guice.createInjector(new ShoShogiModule)
  val controller = injector.getInstance(classOf[ControllerInterface])

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
}