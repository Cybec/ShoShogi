package de.htwg.se.ShoShogi.model

import com.google.inject.name.Names
import com.google.inject.{ Guice, Injector }
import de.htwg.se.ShoShogi.ShoShogiModule
import de.htwg.se.ShoShogi.controller.controllerComponent.{ ControllerInterface, MoveResult }
import de.htwg.se.ShoShogi.model.boardComponent.BoardInterface
import de.htwg.se.ShoShogi.model.fileIoComponent.FileIOInterface
import de.htwg.se.ShoShogi.model.pieceComponent.pieceBaseImpl.{ PieceFactory, PiecesEnum }
import de.htwg.se.ShoShogi.model.playerComponent.Player
import org.junit.runner.RunWith
import org.scalatest.{ Matchers, WordSpec }
import org.scalatest.junit.JUnitRunner
import net.codingwell.scalaguice.InjectorExtensions._

@RunWith(classOf[JUnitRunner])
class FileIOSpec extends WordSpec with Matchers {
  "A JasonFileIO" when {
    val injector: Injector = Guice.createInjector(new ShoShogiModule)
    val controller: ControllerInterface = injector.getInstance(classOf[ControllerInterface])
    var player_1: Player = Player("Player1", first = true)
    var player_2: Player = Player("Player2", first = false)
    var smallBoard: BoardInterface = injector.instance[BoardInterface](Names.named("small")).createNewBoard()
    var tinyBoard: BoardInterface = injector.instance[BoardInterface](Names.named("tiny")).createNewBoard()

    val fileIo: FileIOInterface = injector.instance[FileIOInterface]
    "called save and load" should {
      "reload an board(normal) with in the state it was saved" in {
        import java.io._
        val fileTemp = new File("Z:/SE/ShoShogi/board.json")
        controller.createNewBoard()
        fileIo.save(controller.board, true, player_1, player_2)
        controller.movePiece((0, 2), (0, 3)) should be(MoveResult.validMove)
        val result = fileIo.load.get
        controller.board = result._1
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
      "reload an board(small) with in the state it was saved" in {
        fileIo.save(smallBoard, true, player_1, player_2)
        smallBoard.replaceCell(0, 2, PieceFactory.apply(PiecesEnum.King, player_1.first))
        val result = fileIo.load.get
        controller.board = result._1
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