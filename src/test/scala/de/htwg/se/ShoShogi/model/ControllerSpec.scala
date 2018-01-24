package de.htwg.se.ShoShogi.model

import com.google.inject.name.Names
import com.google.inject.{Guice, Injector}
import de.htwg.se.ShoShogi.ShoShogiModule
import de.htwg.se.ShoShogi.controller.controllerComponent.controllerBaseImpl.{Controller, RoundState, playerOneRound, playerTwoRound}
import de.htwg.se.ShoShogi.controller.controllerComponent.{ControllerInterface, MoveResult}
import de.htwg.se.ShoShogi.model.boardComponent.BoardInterface
import de.htwg.se.ShoShogi.model.pieceComponent.PieceInterface
import de.htwg.se.ShoShogi.model.pieceComponent.pieceBaseImpl.{PieceFactory, PiecesEnum}
import net.codingwell.scalaguice.InjectorExtensions._
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{Matchers, WordSpec}

import scala.language.reflectiveCalls

//noinspection ScalaStyle
@RunWith(classOf[JUnitRunner])
class ControllerSpec extends WordSpec with Matchers {

  val injector: Injector = Guice.createInjector(new ShoShogiModule)
  val controller: ControllerInterface = injector.getInstance(classOf[ControllerInterface])
  val newController = new Controller()
  val playerOnesTurn: RoundState = new playerOneRound(newController)
  val playerTwosTurn: RoundState = new playerTwoRound(newController)

  controller.createNewBoard()
  controller.changeNamePlayer1("Nick")
  controller.changeNamePlayer2("Mert")

  val (player_1, player_2) = controller.getPlayers

  "Controller" when {
    "called getContainer" should {
      "should return 2 Lists with the captured Piece of each player" in {
        controller.createNewBoard()
        controller.movePiece((0, 2), (0, 3)) should be(MoveResult.validMove) // player_1
        controller.movePiece((0, 6), (0, 5)) should be(MoveResult.validMove) // player_2
        controller.movePiece((0, 3), (0, 4)) should be(MoveResult.validMove) // player_1
        controller.movePiece((0, 5), (0, 4)) should be(MoveResult.validMove) // player_2
        controller.getContainer should be((
          List(),
          List(PieceFactory.apply(PiecesEnum.Pawn, player_2.first))
        ))
      }
    }
    "called setContainer" should {
      "change the containers of the 2 Players" in {
        controller.createNewBoard()
        val pawn = PieceFactory.apply(PiecesEnum.Pawn, player_1.first)
        controller.setContainer(List(pawn), List(pawn))
        controller.getContainer should be(
          List(PieceFactory.apply(PiecesEnum.Pawn, player_1.first)),
          List(PieceFactory.apply(PiecesEnum.Pawn, player_1.first))
        )
      }
    }

    "called getPossibleMoves" should {
      "be field (0,3) for Pawn at place (0,2)" in {
        controller.createNewBoard()
        controller.getPossibleMoves(0, 2) should be(List[(Int, Int)]((0, 3)))
        controller.movePiece((0, 2), (0, 3)) should be(MoveResult.validMove)
        controller.getPossibleMoves(0, 6) should be(List[(Int, Int)]((0, 5)))

      }
    }

    "called getPossibleMoves" should {
      "give empty List for a wrong column and row" in {
        controller.createNewBoard()
        controller.getPossibleMoves(-1, 0) should be(List())
        controller.getPossibleMoves(0, -1) should be(List())
        controller.getPossibleMoves(9, 0) should be(List())
        controller.getPossibleMoves(0, 9) should be(List())
        controller.getPossibleMoves(-1, 9) should be(List())
        controller.getPossibleMoves(9, -1) should be(List())
        controller.movePiece((0, 2), (0, 3)) should be(MoveResult.validMove)
        controller.getPossibleMoves(-1, 8) should be(List())
      }
    }

    "called boardToString" should {
      "create an String of the empty Board" in {
        controller.createEmptyBoard()
        controller.boardToString() should be(
          "Captured: \n" +
            "    0     1     2     3     4     5     6     7     8 \n \n" +
            "---------------------------------------------------------\n " +
            "|     |     |     |     |     |     |     |     |     | \ta\n" +
            "---------------------------------------------------------\n " +
            "|     |     |     |     |     |     |     |     |     | \tb\n" +
            "---------------------------------------------------------\n " +
            "|     |     |     |     |     |     |     |     |     | \tc\n" +
            "---------------------------------------------------------\n " +
            "|     |     |     |     |     |     |     |     |     | \td\n" +
            "---------------------------------------------------------\n " +
            "|     |     |     |     |     |     |     |     |     | \te\n" +
            "---------------------------------------------------------\n " +
            "|     |     |     |     |     |     |     |     |     | \tf\n" +
            "---------------------------------------------------------\n " +
            "|     |     |     |     |     |     |     |     |     | \tg\n" +
            "---------------------------------------------------------\n " +
            "|     |     |     |     |     |     |     |     |     | \th\n" +
            "---------------------------------------------------------\n " +
            "|     |     |     |     |     |     |     |     |     | \ti\n" +
            "---------------------------------------------------------\n" +
            "Captured: \n"
        )
      }
    }

    "called boardToString" should {
      "create an String of the filled Board" in {
        controller.createNewBoard()
        controller.boardToString() should be(
          "Captured: \n" +
            "    0     1     2     3     4     5     6     7     8 \n \n" +
            "---------------------------------------------------------\n " +
            "| L°  | KN° | SG° | GG° | K°  | GG° | SG° | KN° | L°  | \ta\n" +
            "---------------------------------------------------------\n " +
            "|     | R°  |     |     |     |     |     | B°  |     | \tb\n" +
            "---------------------------------------------------------\n " +
            "| P°  | P°  | P°  | P°  | P°  | P°  | P°  | P°  | P°  | \tc\n" +
            "---------------------------------------------------------\n " +
            "|     |     |     |     |     |     |     |     |     | \td\n" +
            "---------------------------------------------------------\n " +
            "|     |     |     |     |     |     |     |     |     | \te\n" +
            "---------------------------------------------------------\n " +
            "|     |     |     |     |     |     |     |     |     | \tf\n" +
            "---------------------------------------------------------\n " +
            "| P   | P   | P   | P   | P   | P   | P   | P   | P   | \tg\n" +
            "---------------------------------------------------------\n " +
            "|     | B   |     |     |     |     |     | R   |     | \th\n" +
            "---------------------------------------------------------\n " +
            "| L   | KN  | SG  | GG  | K   | GG  | SG  | KN  | L   | \ti\n" +
            "---------------------------------------------------------\n" +
            "Captured: \n"
        )
      }
    }

    "called boardToString" should {
      "create an String of the filled Board with captured" in {
        controller.createNewBoard()
        controller.movePiece((6, 2), (6, 3)) should be(MoveResult.validMove)
        controller.movePiece((8, 6), (8, 5)) should be(MoveResult.validMove)
        controller.movePiece((7, 1), (2, 6)) should be(MoveResult.validMove)
        controller.boardToString() should be(
          "Captured: P°    \n" +
            "    0     1     2     3     4     5     6     7     8 \n \n" +
            "---------------------------------------------------------\n " +
            "| L°  | KN° | SG° | GG° | K°  | GG° | SG° | KN° | L°  | \ta\n" +
            "---------------------------------------------------------\n " +
            "|     | R°  |     |     |     |     |     |     |     | \tb\n" +
            "---------------------------------------------------------\n " +
            "| P°  | P°  | P°  | P°  | P°  | P°  |     | P°  | P°  | \tc\n" +
            "---------------------------------------------------------\n " +
            "|     |     |     |     |     |     | P°  |     |     | \td\n" +
            "---------------------------------------------------------\n " +
            "|     |     |     |     |     |     |     |     |     | \te\n" +
            "---------------------------------------------------------\n " +
            "|     |     |     |     |     |     |     |     | P   | \tf\n" +
            "---------------------------------------------------------\n " +
            "| P   | P   | B°  | P   | P   | P   | P   | P   |     | \tg\n" +
            "---------------------------------------------------------\n " +
            "|     | B   |     |     |     |     |     | R   |     | \th\n" +
            "---------------------------------------------------------\n " +
            "| L   | KN  | SG  | GG  | K   | GG  | SG  | KN  | L   | \ti\n" +
            "---------------------------------------------------------\n" +
            "Captured: \n"
        )
      }
    }

    "called boardToString" should {
      "create an String of the filled Board with captured both" in {
        controller.createNewBoard()
        controller.movePiece((0, 2), (0, 3)) should be(MoveResult.validMove)
        controller.movePiece((8, 6), (8, 5)) should be(MoveResult.validMove)
        controller.movePiece((6, 2), (6, 3)) should be(MoveResult.validMove)
        controller.movePiece((8, 5), (8, 4)) should be(MoveResult.validMove)
        controller.movePiece((7, 1), (2, 6)) should be(MoveResult.validMove)
        controller.movePiece((1, 7), (2, 6)) should be(MoveResult.validMove)
        controller.boardToString() should be(
          "Captured: P°    \n" +
            "    0     1     2     3     4     5     6     7     8 \n \n" +
            "---------------------------------------------------------\n " +
            "| L°  | KN° | SG° | GG° | K°  | GG° | SG° | KN° | L°  | \ta\n" +
            "---------------------------------------------------------\n " +
            "|     | R°  |     |     |     |     |     |     |     | \tb\n" +
            "---------------------------------------------------------\n " +
            "|     | P°  | P°  | P°  | P°  | P°  |     | P°  | P°  | \tc\n" +
            "---------------------------------------------------------\n " +
            "| P°  |     |     |     |     |     | P°  |     |     | \td\n" +
            "---------------------------------------------------------\n " +
            "|     |     |     |     |     |     |     |     | P   | \te\n" +
            "---------------------------------------------------------\n " +
            "|     |     |     |     |     |     |     |     |     | \tf\n" +
            "---------------------------------------------------------\n " +
            "| P   | P   | B   | P   | P   | P   | P   | P   |     | \tg\n" +
            "---------------------------------------------------------\n " +
            "|     |     |     |     |     |     |     | R   |     | \th\n" +
            "---------------------------------------------------------\n " +
            "| L   | KN  | SG  | GG  | K   | GG  | SG  | KN  | L   | \ti\n" +
            "---------------------------------------------------------\n" +
            "Captured: B     \n"
        )
      }
    }

    "called movePiece" should {
      "return invalidMove if the Player 1 tries to move enemy piece" in {
        controller.createNewBoard()
        controller.movePiece((0, 8), (0, 7)) should be(MoveResult.invalidMove)
        controller.movePiece((1, 8), (1, 7)) should be(MoveResult.invalidMove)
        controller.movePiece((2, 8), (2, 7)) should be(MoveResult.invalidMove)
        controller.movePiece((3, 8), (3, 7)) should be(MoveResult.invalidMove)
        controller.movePiece((4, 8), (4, 7)) should be(MoveResult.invalidMove)
        controller.movePiece((5, 8), (5, 7)) should be(MoveResult.invalidMove)
        controller.movePiece((6, 8), (6, 7)) should be(MoveResult.invalidMove)
        controller.movePiece((7, 8), (7, 7)) should be(MoveResult.invalidMove)
        controller.movePiece((8, 8), (8, 7)) should be(MoveResult.invalidMove)
        controller.movePiece((0, 6), (0, 5)) should be(MoveResult.invalidMove)
      }
      "return invalidMove if the Player 2 tries to move enemy piece" in {
        controller.createNewBoard()

        controller.movePiece((0, 2), (0, 3)) should be(MoveResult.validMove)
        controller.movePiece((1, 2), (1, 3)) should be(MoveResult.invalidMove)
      }
      "return invalidMove, if the destination is invalide" in {
        controller.createNewBoard()
        controller.movePiece((1, 7), (0, 0)) should be(MoveResult.invalidMove)
        controller.movePiece((1, 7), (0, 1)) should be(MoveResult.invalidMove)
        controller.movePiece((1, 7), (0, 2)) should be(MoveResult.invalidMove)
        controller.movePiece((1, 7), (0, 3)) should be(MoveResult.invalidMove)
        controller.movePiece((1, 7), (0, 4)) should be(MoveResult.invalidMove)
        controller.movePiece((1, 7), (0, 5)) should be(MoveResult.invalidMove)
        controller.movePiece((1, 7), (0, 6)) should be(MoveResult.invalidMove)
        controller.movePiece((1, 7), (0, 7)) should be(MoveResult.invalidMove)
        controller.movePiece((1, 7), (0, 8)) should be(MoveResult.invalidMove)

        controller.movePiece((1, 7), (1, 0)) should be(MoveResult.invalidMove)
        controller.movePiece((1, 7), (1, 1)) should be(MoveResult.invalidMove)
        controller.movePiece((1, 7), (1, 2)) should be(MoveResult.invalidMove)
        controller.movePiece((1, 7), (1, 3)) should be(MoveResult.invalidMove)
        controller.movePiece((1, 7), (1, 4)) should be(MoveResult.invalidMove)
        controller.movePiece((1, 7), (1, 5)) should be(MoveResult.invalidMove)
        controller.movePiece((1, 7), (1, 6)) should be(MoveResult.invalidMove)
        controller.movePiece((1, 7), (1, 7)) should be(MoveResult.invalidMove)
        controller.movePiece((1, 7), (1, 8)) should be(MoveResult.invalidMove)

        controller.movePiece((1, 7), (2, 0)) should be(MoveResult.invalidMove)
        controller.movePiece((1, 7), (2, 1)) should be(MoveResult.invalidMove)
        controller.movePiece((1, 7), (2, 2)) should be(MoveResult.invalidMove)
        controller.movePiece((1, 7), (2, 3)) should be(MoveResult.invalidMove)
        controller.movePiece((1, 7), (2, 4)) should be(MoveResult.invalidMove)
        controller.movePiece((1, 7), (2, 5)) should be(MoveResult.invalidMove)
        controller.movePiece((1, 7), (2, 6)) should be(MoveResult.invalidMove)
        controller.movePiece((1, 7), (2, 7)) should be(MoveResult.invalidMove)
        controller.movePiece((1, 7), (2, 8)) should be(MoveResult.invalidMove)

        controller.movePiece((1, 7), (3, 0)) should be(MoveResult.invalidMove)
        controller.movePiece((1, 7), (3, 1)) should be(MoveResult.invalidMove)
        controller.movePiece((1, 7), (3, 2)) should be(MoveResult.invalidMove)
        controller.movePiece((1, 7), (3, 3)) should be(MoveResult.invalidMove)
        controller.movePiece((1, 7), (3, 4)) should be(MoveResult.invalidMove)
        controller.movePiece((1, 7), (3, 5)) should be(MoveResult.invalidMove)
        controller.movePiece((1, 7), (3, 6)) should be(MoveResult.invalidMove)
        controller.movePiece((1, 7), (3, 7)) should be(MoveResult.invalidMove)
        controller.movePiece((1, 7), (3, 8)) should be(MoveResult.invalidMove)

        controller.movePiece((1, 7), (4, 0)) should be(MoveResult.invalidMove)
        controller.movePiece((1, 7), (4, 1)) should be(MoveResult.invalidMove)
        controller.movePiece((1, 7), (4, 2)) should be(MoveResult.invalidMove)
        controller.movePiece((1, 7), (4, 3)) should be(MoveResult.invalidMove)
        controller.movePiece((1, 7), (4, 4)) should be(MoveResult.invalidMove)
        controller.movePiece((1, 7), (4, 5)) should be(MoveResult.invalidMove)
        controller.movePiece((1, 7), (4, 6)) should be(MoveResult.invalidMove)
        controller.movePiece((1, 7), (4, 7)) should be(MoveResult.invalidMove)
        controller.movePiece((1, 7), (4, 8)) should be(MoveResult.invalidMove)

        controller.movePiece((1, 7), (5, 0)) should be(MoveResult.invalidMove)
        controller.movePiece((1, 7), (5, 1)) should be(MoveResult.invalidMove)
        controller.movePiece((1, 7), (5, 2)) should be(MoveResult.invalidMove)
        controller.movePiece((1, 7), (5, 3)) should be(MoveResult.invalidMove)
        controller.movePiece((1, 7), (5, 4)) should be(MoveResult.invalidMove)
        controller.movePiece((1, 7), (5, 5)) should be(MoveResult.invalidMove)
        controller.movePiece((1, 7), (5, 6)) should be(MoveResult.invalidMove)
        controller.movePiece((1, 7), (5, 7)) should be(MoveResult.invalidMove)
        controller.movePiece((1, 7), (5, 8)) should be(MoveResult.invalidMove)

        controller.movePiece((1, 7), (6, 0)) should be(MoveResult.invalidMove)
        controller.movePiece((1, 7), (6, 1)) should be(MoveResult.invalidMove)
        controller.movePiece((1, 7), (6, 2)) should be(MoveResult.invalidMove)
        controller.movePiece((1, 7), (6, 3)) should be(MoveResult.invalidMove)
        controller.movePiece((1, 7), (6, 4)) should be(MoveResult.invalidMove)
        controller.movePiece((1, 7), (6, 5)) should be(MoveResult.invalidMove)
        controller.movePiece((1, 7), (6, 6)) should be(MoveResult.invalidMove)
        controller.movePiece((1, 7), (6, 7)) should be(MoveResult.invalidMove)
        controller.movePiece((1, 7), (6, 8)) should be(MoveResult.invalidMove)

        controller.movePiece((1, 7), (7, 0)) should be(MoveResult.invalidMove)
        controller.movePiece((1, 7), (7, 1)) should be(MoveResult.invalidMove)
        controller.movePiece((1, 7), (7, 2)) should be(MoveResult.invalidMove)
        controller.movePiece((1, 7), (7, 3)) should be(MoveResult.invalidMove)
        controller.movePiece((1, 7), (7, 4)) should be(MoveResult.invalidMove)
        controller.movePiece((1, 7), (7, 5)) should be(MoveResult.invalidMove)
        controller.movePiece((1, 7), (7, 6)) should be(MoveResult.invalidMove)
        controller.movePiece((1, 7), (7, 7)) should be(MoveResult.invalidMove)
        controller.movePiece((1, 7), (7, 8)) should be(MoveResult.invalidMove)

        controller.movePiece((1, 7), (8, 0)) should be(MoveResult.invalidMove)
        controller.movePiece((1, 7), (8, 1)) should be(MoveResult.invalidMove)
        controller.movePiece((1, 7), (8, 2)) should be(MoveResult.invalidMove)
        controller.movePiece((1, 7), (8, 3)) should be(MoveResult.invalidMove)
        controller.movePiece((1, 7), (8, 4)) should be(MoveResult.invalidMove)
        controller.movePiece((1, 7), (8, 5)) should be(MoveResult.invalidMove)
        controller.movePiece((1, 7), (8, 6)) should be(MoveResult.invalidMove)
        controller.movePiece((1, 7), (8, 7)) should be(MoveResult.invalidMove)
        controller.movePiece((1, 7), (8, 8)) should be(MoveResult.invalidMove)
        controller.movePiece((1, 7), (8, -8)) should be(MoveResult.invalidMove)
        controller.movePiece((-3, 2), (0, 3)) should be(MoveResult.invalidMove)
        controller.movePiece((0, 6), (0, 5)) should be(MoveResult.invalidMove)
      }
      "return kingSlain, if the destination is the enemy King Piece (Player 2)" in {
        controller.createNewBoard()
        controller.movePiece((4, 2), (4, 3)) should be(MoveResult.validMove)
        controller.movePiece((4, 6), (4, 5)) should be(MoveResult.validMove)
        controller.movePiece((4, 3), (4, 4)) should be(MoveResult.validMove)
        controller.movePiece((4, 5), (4, 4)) should be(MoveResult.validMove)
        controller.movePiece((1, 2), (1, 3)) should be(MoveResult.validMove)
        controller.movePiece((4, 4), (4, 3)) should be(MoveResult.validMove)
        controller.movePiece((1, 3), (1, 4)) should be(MoveResult.validMove)
        controller.movePiece((4, 3), (4, 2)) should be(MoveResult.validMove)
        controller.movePiece((1, 4), (1, 5)) should be(MoveResult.validMove)
        controller.movePiece((4, 2), (4, 1)) should be(MoveResult.validMove)
        controller.movePiece((1, 5), (1, 6)) should be(MoveResult.validMove)
        controller.movePiece((4, 1), (4, 0)) should be(MoveResult.kingSlain)
      }
      "return kingSlain, if the destination is the enemy King Piece (Player 1)" in {
        controller.createNewBoard()
        controller.movePiece((4, 2), (4, 3)) should be(MoveResult.validMove)
        controller.movePiece((4, 6), (4, 5)) should be(MoveResult.validMove)
        controller.movePiece((4, 3), (4, 4)) should be(MoveResult.validMove)
        controller.movePiece((2, 6), (2, 5)) should be(MoveResult.validMove)
        controller.movePiece((4, 4), (4, 5)) should be(MoveResult.validMove)
        controller.movePiece((2, 5), (2, 4)) should be(MoveResult.validMove)
        controller.movePiece((4, 5), (4, 6)) should be(MoveResult.validMove)
        controller.movePiece((2, 4), (2, 3)) should be(MoveResult.validMove)
        controller.movePiece((4, 6), (4, 7)) should be(MoveResult.validMove)
        controller.movePiece((2, 3), (2, 2)) should be(MoveResult.validMove)
        controller.movePiece((4, 7), (4, 8)) should be(MoveResult.kingSlain)
      }
    }

    "called promotable" should {
      controller.createNewBoard()

      "return false if Pawn of Player 1 is not in Promotionzone" in {
        controller.promotable(0, 5) should be(false)
      }
      "return false if Pawn of Player 2 is not in Promotionzone" in {
        controller.promotable(0, 3) should be(false)
      }
      "return false if Pawn of Player 1 is in Promotionzone" in {
        controller.promotable(0, 2) should be(false)
      }
      "return false if Pawn of Player 2 is in Promotionzone" in {
        controller.promotable(0, 6) should be(false)
      }
      "return false if no Piece was selected in the Algorithmen" in {
        controller.promotable(8, 4) should be(false)
      }
      "return false if a not existing Filed was selected" in {
        controller.promotable(-2, -9) should be(false)
      }
    }
    "called promotePiece" should {
      controller.createNewBoard()

      "return false if Piece of Player 1 has no Promotion" in {
        controller.promotePiece(8, 2) should be(true)
      }
      "return false if Piece of Player 2 has no Promotion" in {
        controller.promotePiece(0, 5) should be(false)
      }
      "return true if Piece of Player 1 has a Promotion" in {
        controller.promotePiece(0, 6) should be(true)
      }
      "return true if Piece of Player 2 has a Promotion" in {
        controller.promotePiece(0, 2) should be(true)
      }
      "return false because invalid cell" in {
        controller.promotePiece(0, -5) should be(false)
      }
    }
    "called promotePiece" should {
      "return true if the Piece is located in the promotionzone" in {
        controller.createNewBoard()

        controller.movePiece((0, 2), (0, 3)) should be(MoveResult.validMove)
        controller.movePiece((0, 6), (0, 5)) should be(MoveResult.validMove)
        controller.movePiece((0, 3), (0, 4)) should be(MoveResult.validMove)
        controller.movePiece((0, 5), (0, 4)) should be(MoveResult.validMove)
        controller.movePiece((1, 2), (1, 3)) should be(MoveResult.validMove)
        controller.movePiece((0, 4), (0, 3)) should be(MoveResult.validMove)
        controller.movePiece((1, 3), (1, 4)) should be(MoveResult.validMove)
        controller.movePiece((0, 3), (0, 2)) should be(MoveResult.validMove)

        controller.promotable(0, 2) should be(true)
        controller.promotePiece(0, 2) should be(true)
      }
    }

    "called possibleMovesConqueredPiece" should {
      "return a List of Moves a Conquered Pawn of each player can make when in column of King" in {
        controller.createNewBoard()
        controller.movePiece((5, 0), (5, 1)) should be(MoveResult.validMove) // player_1
        controller.movePiece((4, 6), (4, 5)) should be(MoveResult.validMove) // player_2
        controller.movePiece((4, 2), (4, 3)) should be(MoveResult.validMove) // player_1
        controller.movePiece((4, 5), (4, 4)) should be(MoveResult.validMove) // player_2
        controller.movePiece((6, 0), (6, 1)) should be(MoveResult.validMove) // player_1
        controller.movePiece((4, 4), (4, 3)) should be(MoveResult.validMove) // player_2
        controller.movePiece((8, 2), (8, 3)) should be(MoveResult.validMove) // player_1
        controller.movePiece((4, 3), (4, 2)) should be(MoveResult.validMove) // player_2
        controller.movePiece((5, 1), (4, 2)) should be(MoveResult.validMove) // player_1

        controller.getPossibleMovesConqueredPiece("P") should be(List[(Int, Int)]((4, 7), (4, 6), (4, 5), (4, 4), (4, 3))) // player_2
        controller.movePiece((8, 6), (8, 5)) should be(MoveResult.validMove) // player_2

        controller.getPossibleMovesConqueredPiece("P°") should be(List[(Int, Int)]((4, 1), (4, 3), (4, 4), (4, 5), (4, 6))) // player_1

        controller.boardToString() should be(
          "Captured: P°    \n" +
            "    0     1     2     3     4     5     6     7     8 \n \n" +
            "---------------------------------------------------------\n " +
            "| L°  | KN° | SG° | GG° | K°  |     |     | KN° | L°  | \ta\n" +
            "---------------------------------------------------------\n " +
            "|     | R°  |     |     |     |     | SG° | B°  |     | \tb\n" +
            "---------------------------------------------------------\n " +
            "| P°  | P°  | P°  | P°  | GG° | P°  | P°  | P°  |     | \tc\n" +
            "---------------------------------------------------------\n " +
            "|     |     |     |     |     |     |     |     | P°  | \td\n" +
            "---------------------------------------------------------\n " +
            "|     |     |     |     |     |     |     |     |     | \te\n" +
            "---------------------------------------------------------\n " +
            "|     |     |     |     |     |     |     |     | P   | \tf\n" +
            "---------------------------------------------------------\n " +
            "| P   | P   | P   | P   |     | P   | P   | P   |     | \tg\n" +
            "---------------------------------------------------------\n " +
            "|     | B   |     |     |     |     |     | R   |     | \th\n" +
            "---------------------------------------------------------\n " +
            "| L   | KN  | SG  | GG  | K   | GG  | SG  | KN  | L   | \ti\n" +
            "---------------------------------------------------------\n" +
            "Captured: P     \n"
        )
      }
      "return a List of Moves a Conquered Pawn of each player can make if not in column of King" in {
        controller.createNewBoard()
        controller.movePiece((0, 2), (0, 3)) should be(MoveResult.validMove) // player_1
        controller.movePiece((0, 6), (0, 5)) should be(MoveResult.validMove) // player_2
        controller.movePiece((0, 3), (0, 4)) should be(MoveResult.validMove) // player_1
        controller.movePiece((0, 5), (0, 4)) should be(MoveResult.validMove) // player_2
        controller.movePiece((0, 0), (0, 4)) should be(MoveResult.validMove) // player_1

        controller.getPossibleMovesConqueredPiece("P") should be(List[(Int, Int)]((0, 1), (0, 2), (0, 3), (0, 5), (0, 6), (0, 7))) // player_2
        controller.movePiece((8, 6), (8, 5)) should be(MoveResult.validMove) // player_2

        controller.getPossibleMovesConqueredPiece("P°") should be(List[(Int, Int)]((0, 0), (0, 1), (0, 2), (0, 3), (0, 5), (0, 6), (0, 7))) // player_1

        controller.boardToString() should be(
          "Captured: P°    \n" +
            "    0     1     2     3     4     5     6     7     8 \n \n" +
            "---------------------------------------------------------\n " +
            "|     | KN° | SG° | GG° | K°  | GG° | SG° | KN° | L°  | \ta\n" +
            "---------------------------------------------------------\n " +
            "|     | R°  |     |     |     |     |     | B°  |     | \tb\n" +
            "---------------------------------------------------------\n " +
            "|     | P°  | P°  | P°  | P°  | P°  | P°  | P°  | P°  | \tc\n" +
            "---------------------------------------------------------\n " +
            "|     |     |     |     |     |     |     |     |     | \td\n" +
            "---------------------------------------------------------\n " +
            "| L°  |     |     |     |     |     |     |     |     | \te\n" +
            "---------------------------------------------------------\n " +
            "|     |     |     |     |     |     |     |     | P   | \tf\n" +
            "---------------------------------------------------------\n " +
            "|     | P   | P   | P   | P   | P   | P   | P   |     | \tg\n" +
            "---------------------------------------------------------\n " +
            "|     | B   |     |     |     |     |     | R   |     | \th\n" +
            "---------------------------------------------------------\n " +
            "| L   | KN  | SG  | GG  | K   | GG  | SG  | KN  | L   | \ti\n" +
            "---------------------------------------------------------\n" +
            "Captured: P     \n"
        )
      }
    }

    "called possibleMovesConqueredPiece" should {
      "return a List of moves a conquered Lancer of each player can make" in {
        controller.createNewBoard()
        controller.movePiece((8, 2), (8, 3)) should be(MoveResult.validMove) // player_1
        controller.movePiece((0, 6), (0, 5)) should be(MoveResult.validMove) // player_2
        controller.movePiece((0, 2), (0, 3)) should be(MoveResult.validMove) // player_1
        controller.movePiece((0, 5), (0, 4)) should be(MoveResult.validMove) // player_2
        controller.movePiece((0, 3), (0, 4)) should be(MoveResult.validMove) // player_1
        controller.movePiece((0, 8), (0, 4)) should be(MoveResult.validMove) // player_2
        controller.movePiece((0, 0), (0, 4)) should be(MoveResult.validMove) // player_1
        controller.movePiece((1, 6), (1, 5)) should be(MoveResult.validMove) // player_2

        controller.getPossibleMovesConqueredPiece("L°") should be(List[(Int, Int)]((0, 0), (0, 1), (0, 2), (0, 3), (0, 5), (0, 6), (0, 7),
          (1, 3), (1, 4), (1, 6), (2, 1), (2, 3), (2, 4), (2, 5), (2, 7), (3, 1), (3, 3), (3, 4), (3, 5), (3, 7), (4, 1), (4, 3), (4, 4), (4, 5), (4, 7),
          (5, 1), (5, 3), (5, 4), (5, 5), (5, 7), (6, 1), (6, 3), (6, 4), (6, 5), (6, 7), (7, 3), (7, 4), (7, 5), (8, 1), (8, 2), (8, 4), (8, 5), (8, 7))) // player_1
        controller.boardToString() should be(
          "Captured: P°    L°    \n" +
            "    0     1     2     3     4     5     6     7     8 \n \n" +
            "---------------------------------------------------------\n " +
            "|     | KN° | SG° | GG° | K°  | GG° | SG° | KN° | L°  | \ta\n" +
            "---------------------------------------------------------\n " +
            "|     | R°  |     |     |     |     |     | B°  |     | \tb\n" +
            "---------------------------------------------------------\n " +
            "|     | P°  | P°  | P°  | P°  | P°  | P°  | P°  |     | \tc\n" +
            "---------------------------------------------------------\n " +
            "|     |     |     |     |     |     |     |     | P°  | \td\n" +
            "---------------------------------------------------------\n " +
            "| L°  |     |     |     |     |     |     |     |     | \te\n" +
            "---------------------------------------------------------\n " +
            "|     | P   |     |     |     |     |     |     |     | \tf\n" +
            "---------------------------------------------------------\n " +
            "|     |     | P   | P   | P   | P   | P   | P   | P   | \tg\n" +
            "---------------------------------------------------------\n " +
            "|     | B   |     |     |     |     |     | R   |     | \th\n" +
            "---------------------------------------------------------\n " +
            "|     | KN  | SG  | GG  | K   | GG  | SG  | KN  | L   | \ti\n" +
            "---------------------------------------------------------\n" +
            "Captured: P     \n"
        )

        controller.movePiece((0, 4), (0, 6)) should be(MoveResult.validMove) // player_1
        controller.movePiece((1, 7), (0, 6)) should be(MoveResult.validMove) // player_2
        controller.movePiece((8, 3), (8, 4)) should be(MoveResult.validMove) // player_1

        controller.getPossibleMovesConqueredPiece("L") should be(List[(Int, Int)]((0, 1), (0, 2), (0, 3), (0, 4), (0, 5), (0, 7), (0, 8),
          (1, 3), (1, 4), (1, 6), (1, 7), (2, 1), (2, 3), (2, 4), (2, 5), (2, 7), (3, 1), (3, 3), (3, 4), (3, 5), (3, 7), (4, 1), (4, 3), (4, 4),
          (4, 5), (4, 7), (5, 1), (5, 3), (5, 4), (5, 5), (5, 7), (6, 1), (6, 3), (6, 4), (6, 5), (6, 7), (7, 3), (7, 4), (7, 5), (8, 1), (8, 2),
          (8, 3), (8, 5), (8, 7))) // player_2
        controller.boardToString() should be(
          "Captured: P°    L°    \n" +
            "    0     1     2     3     4     5     6     7     8 \n \n" +
            "---------------------------------------------------------\n " +
            "|     | KN° | SG° | GG° | K°  | GG° | SG° | KN° | L°  | \ta\n" +
            "---------------------------------------------------------\n " +
            "|     | R°  |     |     |     |     |     | B°  |     | \tb\n" +
            "---------------------------------------------------------\n " +
            "|     | P°  | P°  | P°  | P°  | P°  | P°  | P°  |     | \tc\n" +
            "---------------------------------------------------------\n " +
            "|     |     |     |     |     |     |     |     |     | \td\n" +
            "---------------------------------------------------------\n " +
            "|     |     |     |     |     |     |     |     | P°  | \te\n" +
            "---------------------------------------------------------\n " +
            "|     | P   |     |     |     |     |     |     |     | \tf\n" +
            "---------------------------------------------------------\n " +
            "| B   |     | P   | P   | P   | P   | P   | P   | P   | \tg\n" +
            "---------------------------------------------------------\n " +
            "|     |     |     |     |     |     |     | R   |     | \th\n" +
            "---------------------------------------------------------\n " +
            "|     | KN  | SG  | GG  | K   | GG  | SG  | KN  | L   | \ti\n" +
            "---------------------------------------------------------\n" +
            "Captured: P     L     \n"
        )
      }
    }

    "called possibleMovesConqueredPiece" should {
      "return a List of moves a conquered normal piece of each player can make" in {
        controller.createNewBoard()
        controller.movePiece((8, 2), (8, 3)) should be(MoveResult.validMove)
        controller.movePiece((7, 6), (7, 5)) should be(MoveResult.validMove) // player_2
        controller.movePiece((7, 2), (7, 3)) should be(MoveResult.validMove) // player_1
        controller.movePiece((7, 5), (7, 4)) should be(MoveResult.validMove) // player_2
        controller.movePiece((7, 3), (7, 4)) should be(MoveResult.validMove) // player_1
        controller.movePiece((7, 7), (7, 4)) should be(MoveResult.validMove) // player_2

        controller.movePiece((1, 2), (1, 3)) should be(MoveResult.validMove) // player_1
        controller.movePiece((7, 4), (7, 1)) should be(MoveResult.validMove) // player_2
        controller.movePiece((1, 3), (1, 4)) should be(MoveResult.validMove) // player_1

        controller.getPossibleMovesConqueredPiece("B") should be(List[(Int, Int)]((0, 1), (0, 3), (0, 4), (0, 5), (0, 7),
          (1, 2), (1, 3), (1, 5), (2, 1), (2, 3), (2, 4), (2, 5), (2, 7), (3, 1), (3, 3), (3, 4), (3, 5), (3, 7), (4, 1), (4, 3),
          (4, 4), (4, 5), (4, 7), (5, 1), (5, 3), (5, 4), (5, 5), (5, 7), (6, 1), (6, 3), (6, 4), (6, 5), (6, 7), (7, 2), (7, 3),
          (7, 4), (7, 5), (7, 6), (7, 7), (8, 1), (8, 2), (8, 4), (8, 5), (8, 7))) // player_2
        controller.boardToString() should be(
          "Captured: P°    \n" +
            "    0     1     2     3     4     5     6     7     8 \n \n" +
            "---------------------------------------------------------\n " +
            "| L°  | KN° | SG° | GG° | K°  | GG° | SG° | KN° | L°  | \ta\n" +
            "---------------------------------------------------------\n " +
            "|     | R°  |     |     |     |     |     | R   |     | \tb\n" +
            "---------------------------------------------------------\n " +
            "| P°  |     | P°  | P°  | P°  | P°  | P°  |     |     | \tc\n" +
            "---------------------------------------------------------\n " +
            "|     |     |     |     |     |     |     |     | P°  | \td\n" +
            "---------------------------------------------------------\n " +
            "|     | P°  |     |     |     |     |     |     |     | \te\n" +
            "---------------------------------------------------------\n " +
            "|     |     |     |     |     |     |     |     |     | \tf\n" +
            "---------------------------------------------------------\n " +
            "| P   | P   | P   | P   | P   | P   | P   |     | P   | \tg\n" +
            "---------------------------------------------------------\n " +
            "|     | B   |     |     |     |     |     |     |     | \th\n" +
            "---------------------------------------------------------\n " +
            "| L   | KN  | SG  | GG  | K   | GG  | SG  | KN  | L   | \ti\n" +
            "---------------------------------------------------------\n" +
            "Captured: P     B     \n"
        )

        controller.movePiece((1, 6), (1, 5)) should be(MoveResult.validMove) // player_2
        controller.movePiece((2, 2), (2, 3)) should be(MoveResult.validMove) // player_1
        controller.movePiece((1, 5), (1, 4)) should be(MoveResult.validMove) // player_2
        controller.movePiece((1, 1), (1, 4)) should be(MoveResult.validMove) // player_1
        controller.movePiece((2, 6), (2, 5)) should be(MoveResult.validMove) // player_2
        controller.movePiece((1, 4), (1, 7)) should be(MoveResult.validMove) // player_1
        controller.movePiece((2, 5), (2, 4)) should be(MoveResult.validMove) // player_2

        controller.getPossibleMovesConqueredPiece("B°") should be(List[(Int, Int)]((0, 1), (0, 3), (0, 4), (0, 5), (0, 7),
          (1, 1), (1, 2), (1, 3), (1, 4), (1, 5), (1, 6), (2, 1), (2, 2), (2, 5), (2, 6), (2, 7), (3, 1), (3, 3), (3, 4), (3, 5),
          (3, 7), (4, 1), (4, 3), (4, 4), (4, 5), (4, 7), (5, 1), (5, 3), (5, 4), (5, 5), (5, 7), (6, 1), (6, 3), (6, 4), (6, 5),
          (6, 7), (7, 2), (7, 3), (7, 4), (7, 5), (7, 6), (7, 7), (8, 1), (8, 2), (8, 4), (8, 5), (8, 7))) // player_1
        controller.boardToString() should be(
          "Captured: P°    P°    B°    \n" +
            "    0     1     2     3     4     5     6     7     8 \n \n" +
            "---------------------------------------------------------\n " +
            "| L°  | KN° | SG° | GG° | K°  | GG° | SG° | KN° | L°  | \ta\n" +
            "---------------------------------------------------------\n " +
            "|     |     |     |     |     |     |     | R   |     | \tb\n" +
            "---------------------------------------------------------\n " +
            "| P°  |     |     | P°  | P°  | P°  | P°  |     |     | \tc\n" +
            "---------------------------------------------------------\n " +
            "|     |     | P°  |     |     |     |     |     | P°  | \td\n" +
            "---------------------------------------------------------\n " +
            "|     |     | P   |     |     |     |     |     |     | \te\n" +
            "---------------------------------------------------------\n " +
            "|     |     |     |     |     |     |     |     |     | \tf\n" +
            "---------------------------------------------------------\n " +
            "| P   |     |     | P   | P   | P   | P   |     | P   | \tg\n" +
            "---------------------------------------------------------\n " +
            "|     | R°  |     |     |     |     |     |     |     | \th\n" +
            "---------------------------------------------------------\n " +
            "| L   | KN  | SG  | GG  | K   | GG  | SG  | KN  | L   | \ti\n" +
            "---------------------------------------------------------\n" +
            "Captured: P     B     P     \n"
        )
      }
    }

    "called moveConqueredPiece" should {
      "be true when conquered pawn was set on column with no other row of the same player" in {
        controller.createNewBoard()
        controller.movePiece((5, 0), (5, 1)) should be(MoveResult.validMove) // player_1
        controller.movePiece((4, 6), (4, 5)) should be(MoveResult.validMove) // player_2
        controller.movePiece((4, 2), (4, 3)) should be(MoveResult.validMove) // player_1
        controller.movePiece((4, 5), (4, 4)) should be(MoveResult.validMove) // player_2
        controller.movePiece((6, 0), (6, 1)) should be(MoveResult.validMove) // player_1
        controller.movePiece((4, 4), (4, 3)) should be(MoveResult.validMove) // player_2
        controller.movePiece((8, 2), (8, 3)) should be(MoveResult.validMove) // player_1
        controller.movePiece((4, 3), (4, 2)) should be(MoveResult.validMove) // player_2
        controller.movePiece((5, 1), (4, 2)) should be(MoveResult.validMove) // player_1

        controller.moveConqueredPiece("P", (0, 2)) should be(false) // player_2
        controller.moveConqueredPiece("P", (4, 1)) should be(false) // player_2
        controller.moveConqueredPiece("P", (4, 3)) should be(true) // player_2
        controller.moveConqueredPiece("P°", (4, 7)) should be(false) // player_1
        controller.moveConqueredPiece("P°", (4, 4)) should be(true) // player_1

        controller.boardToString() should be(
          "Captured: \n" +
            "    0     1     2     3     4     5     6     7     8 \n \n" +
            "---------------------------------------------------------\n " +
            "| L°  | KN° | SG° | GG° | K°  |     |     | KN° | L°  | \ta\n" +
            "---------------------------------------------------------\n " +
            "|     | R°  |     |     |     |     | SG° | B°  |     | \tb\n" +
            "---------------------------------------------------------\n " +
            "| P°  | P°  | P°  | P°  | GG° | P°  | P°  | P°  |     | \tc\n" +
            "---------------------------------------------------------\n " +
            "|     |     |     |     | P   |     |     |     | P°  | \td\n" +
            "---------------------------------------------------------\n " +
            "|     |     |     |     | P°  |     |     |     |     | \te\n" +
            "---------------------------------------------------------\n " +
            "|     |     |     |     |     |     |     |     |     | \tf\n" +
            "---------------------------------------------------------\n " +
            "| P   | P   | P   | P   |     | P   | P   | P   | P   | \tg\n" +
            "---------------------------------------------------------\n " +
            "|     | B   |     |     |     |     |     | R   |     | \th\n" +
            "---------------------------------------------------------\n " +
            "| L   | KN  | SG  | GG  | K   | GG  | SG  | KN  | L   | \ti\n" +
            "---------------------------------------------------------\n" +
            "Captured: \n"
        )
      }

      "be false when a non existing piece wants to be moved" in {
        controller.createNewBoard()
        controller.movePiece((0, 2), (0, 3)) should be(MoveResult.validMove) // player_1
        controller.movePiece((0, 6), (0, 5)) should be(MoveResult.validMove) // player_2
        controller.movePiece((0, 3), (0, 4)) should be(MoveResult.validMove) // player_1
        controller.movePiece((1, 6), (1, 5)) should be(MoveResult.validMove) // player_2
        controller.movePiece((0, 4), (0, 5)) should be(MoveResult.validMove) // player_1
        controller.movePiece((1, 5), (1, 4)) should be(MoveResult.validMove) // player_2
        controller.moveConqueredPiece("Z", (0, 2)) should be(false)
      }
      "be false when conquered piece wants to be moved on a field its not allowed to be moved" in {
        controller.createNewBoard()
        controller.movePiece((0, 2), (0, 3)) should be(MoveResult.validMove) // player_1
        controller.movePiece((0, 6), (0, 5)) should be(MoveResult.validMove) // player_2
        controller.movePiece((0, 3), (0, 4)) should be(MoveResult.validMove) // player_1
        controller.movePiece((1, 6), (1, 5)) should be(MoveResult.validMove) // player_2
        controller.movePiece((0, 4), (0, 5)) should be(MoveResult.validMove) // player_1
        controller.movePiece((0, 8), (0, 5)) should be(MoveResult.validMove) // player_2
        controller.moveConqueredPiece("P", (0, -10)) should be(false)
      }
    }

    "called boardToArray" should {
      "return all Pieces in a 2 dimensional Array" in {
        controller.createNewBoard()
        val pawn1: PieceInterface = PieceFactory.apply(PiecesEnum.Pawn, player_1.first)
        val pawn2: PieceInterface = PieceFactory.apply(PiecesEnum.Pawn, player_2.first)
        val lancer1: PieceInterface = PieceFactory.apply(PiecesEnum.Lancer, player_1.first)
        val lancer2: PieceInterface = PieceFactory.apply(PiecesEnum.Lancer, player_2.first)
        val knight1: PieceInterface = PieceFactory.apply(PiecesEnum.Knight, player_1.first)
        val knight2: PieceInterface = PieceFactory.apply(PiecesEnum.Knight, player_2.first)
        val silverGeneral1: PieceInterface = PieceFactory.apply(PiecesEnum.SilverGeneral, player_1.first)
        val silverGeneral2: PieceInterface = PieceFactory.apply(PiecesEnum.SilverGeneral, player_2.first)
        val goldenGeneral1: PieceInterface = PieceFactory.apply(PiecesEnum.GoldenGeneral, player_1.first)
        val goldenGeneral2: PieceInterface = PieceFactory.apply(PiecesEnum.GoldenGeneral, player_2.first)
        val king1: PieceInterface = PieceFactory.apply(PiecesEnum.King, player_1.first)
        val king2: PieceInterface = PieceFactory.apply(PiecesEnum.King, player_2.first)
        val rook1: PieceInterface = PieceFactory.apply(PiecesEnum.Rook, player_1.first)
        val rook2: PieceInterface = PieceFactory.apply(PiecesEnum.Rook, player_2.first)
        val bishop1: PieceInterface = PieceFactory.apply(PiecesEnum.Bishop, player_1.first)
        val bishop2: PieceInterface = PieceFactory.apply(PiecesEnum.Bishop, player_2.first)
        val empty: PieceInterface = PieceFactory.apply(PiecesEnum.EmptyPiece, player_1.first)
        controller.boardToArray() should be(Array[Array[PieceInterface]](
          Array(lancer1, empty, pawn1, empty, empty, empty, pawn2, empty, lancer2),
          Array(knight1, rook1, pawn1, empty, empty, empty, pawn2, bishop2, knight2), Array(silverGeneral1, empty, pawn1, empty, empty, empty, pawn2, empty, silverGeneral2),
          Array(goldenGeneral1, empty, pawn1, empty, empty, empty, pawn2, empty, goldenGeneral2), Array(king1, empty, pawn1, empty, empty, empty, pawn2, empty, king2),
          Array(goldenGeneral1, empty, pawn1, empty, empty, empty, pawn2, empty, goldenGeneral2), Array(silverGeneral1, empty, pawn1, empty, empty, empty, pawn2, empty, silverGeneral2),
          Array(knight1, bishop1, pawn1, empty, empty, empty, pawn2, rook2, knight2), Array(lancer1, empty, pawn1, empty, empty, empty, pawn2, empty, lancer2)
        ))
      }
    }

    "called changeNamePlayer1" should {
      "set name of player_1 to Nick" in {
        controller.changeNamePlayer1("Nick")
        player_1.name should be("Nick")
      }
    }

    "called changeNamePlayer2" should {
      "set name of player_2 to Mert" in {
        controller.changeNamePlayer2("Mert")
        player_2.name should be("Mert")
      }
    }

    "called undoCommand" should {
      "undo the last move done and return true" in {
        controller.createNewBoard()
        controller.movePiece((0, 2), (0, 3)) should be(MoveResult.validMove) // player_1
        controller.undoCommand should be(true)
        controller.boardToString() should be(
          "Captured: \n" +
            "    0     1     2     3     4     5     6     7     8 \n \n" +
            "---------------------------------------------------------\n " +
            "| L°  | KN° | SG° | GG° | K°  | GG° | SG° | KN° | L°  | \ta\n" +
            "---------------------------------------------------------\n " +
            "|     | R°  |     |     |     |     |     | B°  |     | \tb\n" +
            "---------------------------------------------------------\n " +
            "| P°  | P°  | P°  | P°  | P°  | P°  | P°  | P°  | P°  | \tc\n" +
            "---------------------------------------------------------\n " +
            "|     |     |     |     |     |     |     |     |     | \td\n" +
            "---------------------------------------------------------\n " +
            "|     |     |     |     |     |     |     |     |     | \te\n" +
            "---------------------------------------------------------\n " +
            "|     |     |     |     |     |     |     |     |     | \tf\n" +
            "---------------------------------------------------------\n " +
            "| P   | P   | P   | P   | P   | P   | P   | P   | P   | \tg\n" +
            "---------------------------------------------------------\n " +
            "|     | B   |     |     |     |     |     | R   |     | \th\n" +
            "---------------------------------------------------------\n " +
            "| L   | KN  | SG  | GG  | K   | GG  | SG  | KN  | L   | \ti\n" +
            "---------------------------------------------------------\n" +
            "Captured: \n"
        )
      }
      "return false if no command was done yet" in {
        newController.undoCommand should be(false)
      }
    }
    "called redoCommand" should {
      "redo the last undone Command and return true" in {
        controller.createNewBoard()
        controller.movePiece((0, 2), (0, 3)) should be(MoveResult.validMove) // player_1
        controller.undoCommand should be(true)
        controller.redoCommand should be(true)
        controller.boardToString() should be(
          "Captured: \n" +
            "    0     1     2     3     4     5     6     7     8 \n \n" +
            "---------------------------------------------------------\n " +
            "| L°  | KN° | SG° | GG° | K°  | GG° | SG° | KN° | L°  | \ta\n" +
            "---------------------------------------------------------\n " +
            "|     | R°  |     |     |     |     |     | B°  |     | \tb\n" +
            "---------------------------------------------------------\n " +
            "|     | P°  | P°  | P°  | P°  | P°  | P°  | P°  | P°  | \tc\n" +
            "---------------------------------------------------------\n " +
            "| P°  |     |     |     |     |     |     |     |     | \td\n" +
            "---------------------------------------------------------\n " +
            "|     |     |     |     |     |     |     |     |     | \te\n" +
            "---------------------------------------------------------\n " +
            "|     |     |     |     |     |     |     |     |     | \tf\n" +
            "---------------------------------------------------------\n " +
            "| P   | P   | P   | P   | P   | P   | P   | P   | P   | \tg\n" +
            "---------------------------------------------------------\n " +
            "|     | B   |     |     |     |     |     | R   |     | \th\n" +
            "---------------------------------------------------------\n " +
            "| L   | KN  | SG  | GG  | K   | GG  | SG  | KN  | L   | \ti\n" +
            "---------------------------------------------------------\n" +
            "Captured: \n"
        )
      }
      "return true when no undo was done yet" in {
        controller.createNewBoard()
        controller.redoCommand should be(false)
      }
    }

    "called replaceBoard" should {
      "set the current Playboard to the board given" in {
        controller.createNewBoard()
        controller.movePiece((0, 2), (0, 3)) should be(MoveResult.validMove) // player_1
        val board1: BoardInterface = injector.instance[BoardInterface](Names.named("normal")).createNewBoard()
        controller.replaceBoard(board1)
        controller.boardToString() should be(
          "Captured: \n" +
            "    0     1     2     3     4     5     6     7     8 \n \n" +
            "---------------------------------------------------------\n " +
            "|     |     |     |     |     |     |     |     |     | \ta\n" +
            "---------------------------------------------------------\n " +
            "|     |     |     |     |     |     |     |     |     | \tb\n" +
            "---------------------------------------------------------\n " +
            "|     |     |     |     |     |     |     |     |     | \tc\n" +
            "---------------------------------------------------------\n " +
            "|     |     |     |     |     |     |     |     |     | \td\n" +
            "---------------------------------------------------------\n " +
            "|     |     |     |     |     |     |     |     |     | \te\n" +
            "---------------------------------------------------------\n " +
            "|     |     |     |     |     |     |     |     |     | \tf\n" +
            "---------------------------------------------------------\n " +
            "|     |     |     |     |     |     |     |     |     | \tg\n" +
            "---------------------------------------------------------\n " +
            "|     |     |     |     |     |     |     |     |     | \th\n" +
            "---------------------------------------------------------\n " +
            "|     |     |     |     |     |     |     |     |     | \ti\n" +
            "---------------------------------------------------------\n" +
            "Captured: \n"
        )
      }
    }
    "called changeState" should {
      "change the state from PlayerOnesTurn to PlayerTwosTurn and the other way around" in {
        newController.getCurrentStat() should be(playerOnesTurn)
        newController.changeState()
        newController.getCurrentStat() should be(playerTwosTurn)
      }
    }
    "called save/load" should {
      "save the board in first players turn" in {
        newController.createNewBoard()
        val oldState = newController.currentState
        newController.save
        newController.movePiece((0, 2), (0, 3)) should be(MoveResult.validMove)
        newController.getCurrentStat() should be(playerTwosTurn)
        newController.load
        newController.getCurrentStat() should be(oldState)
      }

      "save the board in second players turn" in {
        newController.createNewBoard()
        newController.movePiece((0, 2), (0, 3)) should be(MoveResult.validMove)
        val oldState = newController.currentState
        newController.save
        newController.movePiece((0, 6), (0, 5)) should be(MoveResult.validMove)
        newController.getCurrentStat() should be(playerOnesTurn)
        newController.load
        newController.getCurrentStat() should be(oldState)
      }

      "not saving the board load changes nothing" in {
        controller.createNewBoard()
        controller.movePiece((0, 2), (0, 3)) should be(MoveResult.validMove)
        controller.load
        controller.boardToString() should be(
          "Captured: \n" +
            "    0     1     2     3     4     5     6     7     8 \n \n" +
            "---------------------------------------------------------\n " +
            "| L°  | KN° | SG° | GG° | K°  | GG° | SG° | KN° | L°  | \ta\n" +
            "---------------------------------------------------------\n " +
            "|     | R°  |     |     |     |     |     | B°  |     | \tb\n" +
            "---------------------------------------------------------\n " +
            "|     | P°  | P°  | P°  | P°  | P°  | P°  | P°  | P°  | \tc\n" +
            "---------------------------------------------------------\n " +
            "| P°  |     |     |     |     |     |     |     |     | \td\n" +
            "---------------------------------------------------------\n " +
            "|     |     |     |     |     |     |     |     |     | \te\n" +
            "---------------------------------------------------------\n " +
            "|     |     |     |     |     |     |     |     |     | \tf\n" +
            "---------------------------------------------------------\n " +
            "| P   | P   | P   | P   | P   | P   | P   | P   | P   | \tg\n" +
            "---------------------------------------------------------\n " +
            "|     | B   |     |     |     |     |     | R   |     | \th\n" +
            "---------------------------------------------------------\n " +
            "| L   | KN  | SG  | GG  | K   | GG  | SG  | KN  | L   | \ti\n" +
            "---------------------------------------------------------\n" +
            "Captured: \n"
        )
      }
    }
  }
}