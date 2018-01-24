package de.htwg.se.ShoShogi.model

import de.htwg.se.ShoShogi.controller.controllerComponent.Simulator
import de.htwg.se.ShoShogi.controller.controllerComponent.controllerBaseImpl.Controller
import de.htwg.se.ShoShogi.model.pieceComponent.pieceBaseImpl.{PieceFactory, PiecesEnum}
import org.junit.runner.RunWith
import org.scalatest._
import org.scalatest.junit.JUnitRunner

//noinspection ScalaStyle
@RunWith(classOf[JUnitRunner])
class SimulatorSpec extends WordSpec with Matchers {
  "The Sumulator" when {
    "Called Start" should {
      val controller = new Controller()
      controller.createNewBoard()
      Simulator.start(controller, waitTime = 0)

      while (!Simulator.threadEnd) {
        Thread.sleep(1000)
      }

      "Change Board" in {
        val board = controller.boardToArray()

        board(0)(0) should be(PieceFactory.apply(PiecesEnum.Lancer, true))
        board(0)(1) should be(PieceFactory.apply(PiecesEnum.EmptyPiece, false))
        board(0)(2) should be(PieceFactory.apply(PiecesEnum.Pawn, true))
        board(0)(3) should be(PieceFactory.apply(PiecesEnum.EmptyPiece, false))
        board(0)(4) should be(PieceFactory.apply(PiecesEnum.EmptyPiece, false))
        board(0)(5) should be(PieceFactory.apply(PiecesEnum.EmptyPiece, false))
        board(0)(6) should be(PieceFactory.apply(PiecesEnum.Pawn, false))
        board(0)(7) should be(PieceFactory.apply(PiecesEnum.EmptyPiece, false))
        board(0)(8) should be(PieceFactory.apply(PiecesEnum.Lancer, false))

        board(1)(0) should be(PieceFactory.apply(PiecesEnum.Knight, true))
        board(1)(1) should be(PieceFactory.apply(PiecesEnum.Rook, true))
        board(1)(2) should be(PieceFactory.apply(PiecesEnum.EmptyPiece, false))
        board(1)(3) should be(PieceFactory.apply(PiecesEnum.Pawn, true))
        board(1)(4) should be(PieceFactory.apply(PiecesEnum.EmptyPiece, false))
        board(1)(5) should be(PieceFactory.apply(PiecesEnum.EmptyPiece, false))
        board(1)(6) should be(PieceFactory.apply(PiecesEnum.Pawn, false))
        board(1)(7) should be(PieceFactory.apply(PiecesEnum.EmptyPiece, false))
        board(1)(8) should be(PieceFactory.apply(PiecesEnum.Knight, false))

        board(2)(0) should be(PieceFactory.apply(PiecesEnum.SilverGeneral, true))
        board(2)(1) should be(PieceFactory.apply(PiecesEnum.EmptyPiece, false))
        board(2)(2) should be(PieceFactory.apply(PiecesEnum.Pawn, true))
        board(2)(3) should be(PieceFactory.apply(PiecesEnum.EmptyPiece, false))
        board(2)(4) should be(PieceFactory.apply(PiecesEnum.EmptyPiece, false))
        board(2)(5) should be(PieceFactory.apply(PiecesEnum.Pawn, false))
        board(2)(6) should be(PieceFactory.apply(PiecesEnum.EmptyPiece, false))
        board(2)(7) should be(PieceFactory.apply(PiecesEnum.GoldenGeneral, false))
        board(2)(8) should be(PieceFactory.apply(PiecesEnum.SilverGeneral, false))

        board(3)(0) should be(PieceFactory.apply(PiecesEnum.GoldenGeneral, true))
        board(3)(1) should be(PieceFactory.apply(PiecesEnum.EmptyPiece, false))
        board(3)(2) should be(PieceFactory.apply(PiecesEnum.Pawn, true))
        board(3)(3) should be(PieceFactory.apply(PiecesEnum.EmptyPiece, false))
        board(3)(4) should be(PieceFactory.apply(PiecesEnum.EmptyPiece, false))
        board(3)(5) should be(PieceFactory.apply(PiecesEnum.EmptyPiece, false))
        board(3)(6) should be(PieceFactory.apply(PiecesEnum.Pawn, false))
        board(3)(7) should be(PieceFactory.apply(PiecesEnum.EmptyPiece, false))
        board(3)(8) should be(PieceFactory.apply(PiecesEnum.EmptyPiece, false))

        board(4)(0) should be(PieceFactory.apply(PiecesEnum.King, true))
        board(4)(1) should be(PieceFactory.apply(PiecesEnum.EmptyPiece, false))
        board(4)(2) should be(PieceFactory.apply(PiecesEnum.Pawn, true))
        board(4)(3) should be(PieceFactory.apply(PiecesEnum.EmptyPiece, false))
        board(4)(4) should be(PieceFactory.apply(PiecesEnum.EmptyPiece, false))
        board(4)(5) should be(PieceFactory.apply(PiecesEnum.EmptyPiece, false))
        board(4)(6) should be(PieceFactory.apply(PiecesEnum.Pawn, false))
        board(4)(7) should be(PieceFactory.apply(PiecesEnum.EmptyPiece, false))
        board(4)(8) should be(PieceFactory.apply(PiecesEnum.King, false))

        board(5)(0) should be(PieceFactory.apply(PiecesEnum.EmptyPiece, false))
        board(5)(1) should be(PieceFactory.apply(PiecesEnum.EmptyPiece, false))
        board(5)(2) should be(PieceFactory.apply(PiecesEnum.Pawn, true))
        board(5)(3) should be(PieceFactory.apply(PiecesEnum.EmptyPiece, false))
        board(5)(4) should be(PieceFactory.apply(PiecesEnum.EmptyPiece, false))
        board(5)(5) should be(PieceFactory.apply(PiecesEnum.EmptyPiece, false))
        board(5)(6) should be(PieceFactory.apply(PiecesEnum.Pawn, false))
        board(5)(7) should be(PieceFactory.apply(PiecesEnum.EmptyPiece, false))
        board(5)(8) should be(PieceFactory.apply(PiecesEnum.GoldenGeneral, false))

        board(6)(0) should be(PieceFactory.apply(PiecesEnum.EmptyPiece, false))
        board(6)(1) should be(PieceFactory.apply(PiecesEnum.GoldenGeneral, true))
        board(6)(2) should be(PieceFactory.apply(PiecesEnum.PromotedBishop, false))
        board(6)(3) should be(PieceFactory.apply(PiecesEnum.Pawn, true))
        board(6)(4) should be(PieceFactory.apply(PiecesEnum.EmptyPiece, false))
        board(6)(5) should be(PieceFactory.apply(PiecesEnum.EmptyPiece, false))
        board(6)(6) should be(PieceFactory.apply(PiecesEnum.Pawn, false))
        board(6)(7) should be(PieceFactory.apply(PiecesEnum.EmptyPiece, false))
        board(6)(8) should be(PieceFactory.apply(PiecesEnum.SilverGeneral, false))

        board(7)(0) should be(PieceFactory.apply(PiecesEnum.Knight, true))
        board(7)(1) should be(PieceFactory.apply(PiecesEnum.SilverGeneral, true))
        board(7)(2) should be(PieceFactory.apply(PiecesEnum.Pawn, true))
        board(7)(3) should be(PieceFactory.apply(PiecesEnum.EmptyPiece, false))
        board(7)(4) should be(PieceFactory.apply(PiecesEnum.Pawn, false))
        board(7)(5) should be(PieceFactory.apply(PiecesEnum.EmptyPiece, false))
        board(7)(6) should be(PieceFactory.apply(PiecesEnum.EmptyPiece, false))
        board(7)(7) should be(PieceFactory.apply(PiecesEnum.Rook, false))
        board(7)(8) should be(PieceFactory.apply(PiecesEnum.Knight, false))

        board(8)(0) should be(PieceFactory.apply(PiecesEnum.Lancer, true))
        board(8)(1) should be(PieceFactory.apply(PiecesEnum.EmptyPiece, false))
        board(8)(2) should be(PieceFactory.apply(PiecesEnum.Pawn, true))
        board(8)(3) should be(PieceFactory.apply(PiecesEnum.EmptyPiece, false))
        board(8)(4) should be(PieceFactory.apply(PiecesEnum.EmptyPiece, false))
        board(8)(5) should be(PieceFactory.apply(PiecesEnum.EmptyPiece, false))
        board(8)(6) should be(PieceFactory.apply(PiecesEnum.Pawn, false))
        board(8)(7) should be(PieceFactory.apply(PiecesEnum.EmptyPiece, false))
        board(8)(8) should be(PieceFactory.apply(PiecesEnum.Lancer, false))
      }
    }
  }
}
