package de.htwg.se.ShoShogi.model

import com.google.inject.name.Names
import com.google.inject.{Guice, Injector}
import de.htwg.se.ShoShogi.ShoShogiModule
import de.htwg.se.ShoShogi.controller.controllerComponent.MoveResult
import de.htwg.se.ShoShogi.controller.controllerComponent.controllerBaseImpl.Controller
import de.htwg.se.ShoShogi.model.boardComponent.BoardInterface
import de.htwg.se.ShoShogi.model.boardComponent.boardBaseImpl.Board
import de.htwg.se.ShoShogi.model.fileIoComponent.FileIOInterface
import de.htwg.se.ShoShogi.model.pieceComponent.pieceBaseImpl.{PieceFactory, PiecesEnum}
import de.htwg.se.ShoShogi.model.playerComponent.Player
import net.codingwell.scalaguice.InjectorExtensions._
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{Matchers, WordSpec}

@RunWith(classOf[JUnitRunner])
class JasonFileIOSpec extends WordSpec with Matchers {
  "A JasonFileIO" when {
    val injector: Injector = Guice.createInjector(new ShoShogiModule)
    val controller: Controller = new Controller()
    var player_1: Player = Player("Player1", first = true)
    var player_2: Player = Player("Player2", first = false)
    var smallBoard: BoardInterface = injector.instance[BoardInterface](Names.named("small")).createNewBoard()
    var tinyBoard: BoardInterface = injector.instance[BoardInterface](Names.named("tiny")).createNewBoard()

    val fileIo: FileIOInterface = injector.instance[FileIOInterface]
    "called save and load" should {
      "reload an board(normal) with in the state it was saved" in {
        controller.createNewBoard()
        fileIo.save(controller.board, true, player_1, player_2)
        controller.movePiece((0, 2), (0, 3)) should be(MoveResult.validMove)
        val (board: BoardInterface, state: Boolean, p1, p2) = fileIo.load.getOrElse(controller.createEmptyBoard())
        controller.replaceBoard(board)
        controller.currentState = if (state) {
          controller.playerOnesTurn
        } else {
          controller.playerTwosTurn
        }
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
        controller.movePiece((0, 2), (0, 3)) should be(MoveResult.validMove)
        controller.movePiece((0, 6), (0, 5)) should be(MoveResult.validMove)
        controller.movePiece((0, 3), (0, 4)) should be(MoveResult.validMove)
        controller.movePiece((0, 5), (0, 4)) should be(MoveResult.validMove)
        controller.movePiece((0, 0), (0, 4)) should be(MoveResult.validMove)
        val currentState: Boolean = if (controller.currentState == controller.playerOnesTurn) {
          true
        } else {
          false
        }
        fileIo.save(controller.board, currentState, player_1, player_2)
        controller.movePiece((8, 6), (8, 5)) should be(MoveResult.validMove)
        val (board2: BoardInterface, state2: Boolean, p12, p22) = fileIo.load.getOrElse(controller.createEmptyBoard())
        controller.replaceBoard(board2)
        controller.currentState = if (state2) {
          controller.playerOnesTurn
        } else {
          controller.playerTwosTurn
        }
        println(controller.boardToString())
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
            "|     |     |     |     |     |     |     |     |     | \tf\n" +
            "---------------------------------------------------------\n " +
            "|     | P   | P   | P   | P   | P   | P   | P   | P   | \tg\n" +
            "---------------------------------------------------------\n " +
            "|     | B   |     |     |     |     |     | R   |     | \th\n" +
            "---------------------------------------------------------\n " +
            "| L   | KN  | SG  | GG  | K   | GG  | SG  | KN  | L   | \ti\n" +
            "---------------------------------------------------------\n" +
            "Captured: P     \n"
        )

      }

      "reload an board(small) with in the state it was saved" in {
        fileIo.save(smallBoard, true, player_1, player_2)
        smallBoard.replaceCell(0, 2, PieceFactory.apply(PiecesEnum.King, player_1.first))
        controller.load
        controller.boardToString() should be(
          "Captured: \n" +
            "    0     1     2     3     4     5     6     7     8 \n \n" +
            "---------------------------------------------------------\n " +
            "|     |     |     |     |     |     | \ta\n" +
            "---------------------------------------------------------\n " +
            "|     |     |     |     |     |     | \tb\n" +
            "---------------------------------------------------------\n " +
            "|     |     |     |     |     |     | \tc\n" +
            "---------------------------------------------------------\n " +
            "|     |     |     |     |     |     | \td\n" +
            "---------------------------------------------------------\n " +
            "|     |     |     |     |     |     | \te\n" +
            "---------------------------------------------------------\n " +
            "|     |     |     |     |     |     | \tf\n" +
            "---------------------------------------------------------\n " +
            "| \tg\n" +
            "---------------------------------------------------------\n " +
            "| \th\n" +
            "---------------------------------------------------------\n " +
            "| \ti\n" +
            "---------------------------------------------------------\n" +
            "Captured: \n"
        )
      }

      "reload an board(tiny) with the state it was saved" in {
        fileIo.save(tinyBoard, true, player_1, player_2)
        smallBoard.replaceCell(0, 0, PieceFactory.apply(PiecesEnum.King, player_1.first))
        controller.load
        controller.boardToString() should be(
          "Captured: \n" +
            "    0     1     2     3     4     5     6     7     8 \n \n" +
            "---------------------------------------------------------\n " +
            "|     |     |     | \ta\n" +
            "---------------------------------------------------------\n " +
            "|     |     |     | \tb\n" +
            "---------------------------------------------------------\n " +
            "|     |     |     | \tc\n" +
            "---------------------------------------------------------\n " +
            "| \td\n" +
            "---------------------------------------------------------\n " +
            "| \te\n" +
            "---------------------------------------------------------\n " +
            "| \tf\n" +
            "---------------------------------------------------------\n " +
            "| \tg\n" +
            "---------------------------------------------------------\n " +
            "| \th\n" +
            "---------------------------------------------------------\n " +
            "| \ti\n" +
            "---------------------------------------------------------\n" +
            "Captured: \n"
        )
      }

      "getBoardBySize will return None if no default board size is given" in {
        val unrealisticBoardSize = 60
        val board: BoardInterface = new Board(unrealisticBoardSize, PieceFactory.apply(PiecesEnum.EmptyPiece, player_1.first))
        fileIo.save(board, true, player_1, player_2)
        fileIo.load should be(None)
      }

      //"return None when no board.json was found" in {
      //  import java.io._
      //  val fileTemp = new File("Z:/SE/ShoShogi/board.json")
      //  if (fileTemp.exists) {
      //   fileTemp.delete() should be(true)
      //  }
      //  fileIo.load should be(None)
      //}
    }
  }
}