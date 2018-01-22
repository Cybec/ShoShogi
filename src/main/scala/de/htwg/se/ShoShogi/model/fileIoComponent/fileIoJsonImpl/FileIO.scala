package de.htwg.se.ShoShogi.model.fileIoComponent.fileIoJsonImpl

import java.nio.file.{Files, Paths}

import com.google.inject.name.Names
import com.google.inject.{Guice, Injector}
import de.htwg.se.ShoShogi.model.boardComponent.BoardInterface
import de.htwg.se.ShoShogi.model.fileIoComponent.FileIOInterface
import de.htwg.se.ShoShogi.model.pieceComponent.PieceInterface
import de.htwg.se.ShoShogi.model.pieceComponent.pieceBaseImpl.{PieceFactory, PiecesEnum}
import de.htwg.se.ShoShogi.model.playerComponent.Player
import de.htwg.se.ShoShogi.{ShoShogiModule, ShoShogiModuleConf}
import net.codingwell.scalaguice.InjectorExtensions._
import play.api.libs.json._

import scala.io.Source

class FileIO extends FileIOInterface {

  override def load: Option[(BoardInterface, Boolean, Player, Player)] =
    if (!Files.exists(Paths.get("board.json"))) None else {
      var loadReturnOption: Option[(BoardInterface, Boolean, Player, Player)] = None
      val source: String = Source.fromFile("board.json").getLines.mkString
      val json: JsValue = Json.parse(source)
      val size = (json \ "board" \ "size").get.toString.toInt
      val state = (json \ "board" \ "state").get.toString.toBoolean
      val player1 = new Player((json \ "board" \ "playerFirstName").get.toString, true)
      val player2 = new Player((json \ "board" \ "playerSecondName").get.toString, true)
      val injector: Injector = Guice.createInjector(new ShoShogiModule)

      loadReturnOption = getBoardBySize(size, injector) match {
        case Some(board) => {
          val newBoard = board.setContainer(
            getConqueredPieces((json \\ "playerFirstConquered").toArray),
            getConqueredPieces((json \\ "playerSecondConquered").toArray)
          )
          Some((newBoard, state, player1, player2))
        }
        case _ => None
      }

      loadReturnOption match {
        case Some((board, state, player_1, player_2)) => {
          var _board = board
          for (index <- 0 until size * size) {
            val row = (json \\ "row") (index).as[Int]
            val col = (json \\ "col") (index).as[Int]
            val piece = (json \\ "piece") (index)
            val pieceName = (piece \ "pieceName").as[String]
            val firstPlayer = (piece \ "firstPlayer").as[Boolean]
            PiecesEnum.withNameOpt(pieceName) match {
              case Some(pieceEnum) =>
                _board = _board.replaceCell(col, row, PieceFactory.apply(pieceEnum, firstPlayer))
              case None =>
            }
          }
          loadReturnOption = Some(_board, state, player_1, player_2)
        }
        case None =>
      }
      loadReturnOption
    }

  override def save(board: BoardInterface, state: Boolean, player_1: Player, player_2: Player): Unit = {
    import java.io._
    val pw = new PrintWriter(new File("board.json"))
    pw.write(Json.prettyPrint(gridToJson(board, state, player_1, player_2)))
    pw.close
  }

  def gridToJson(board: BoardInterface, state: Boolean, player_1: Player, player_2: Player): JsValue = {
    Json.obj(
      "board" -> Json.obj(
        "size" -> JsNumber(board.size),
        "state" -> JsBoolean(state),
        "playerFirstName" -> JsString(player_1.name),
        "playerSecondName" -> JsString(player_2.name),
        "playerFirstConquered" -> Json.toJson(board.getContainer()._1.distinct),
        "playerSecondConquered" -> Json.toJson(board.getContainer()._2.distinct),
        "cell" -> Json.toJson(
          for {
            col <- 0 until board.size;
            row <- 0 until board.size
          } yield {
            Json.obj(
              "row" -> row,
              "col" -> col,
              "piece" -> Json.toJson(board.cell(col, row))
            )
          }
        )
      )
    )
  }

  implicit val cellWrites = new Writes[PieceInterface] {
    def writes(piece: PieceInterface) = Json.obj(
      "pieceName" -> piece.toStringLong,
      "firstPlayer" -> piece.isFirstOwner
    )
  }

  def getBoardBySize(size: Int, injector: Injector): Option[BoardInterface] = {
    size match {
      case ShoShogiModuleConf.defaultBoardSize =>
        Some(injector.instance[BoardInterface](Names.named(ShoShogiModuleConf.defaultBoard)).createNewBoard())
      case ShoShogiModuleConf.smallBoardSize =>
        Some(injector.instance[BoardInterface](Names.named(ShoShogiModuleConf.smallBoard)).createNewBoard())
      case ShoShogiModuleConf.tinyBoardSize =>
        Some(injector.instance[BoardInterface](Names.named(ShoShogiModuleConf.tinyBoard)).createNewBoard())
      case _ => None
    }
  }

  def getConqueredPieces(jsArray: Array[JsValue]): List[PieceInterface] = {
    var stringList: List[String] = List[String]()
    var pieceList: List[PieceInterface] = List[PieceInterface]()

    for (x <- jsArray) yield (x \\ "pieceName").foreach(i => stringList = stringList :+ i.as[String])

    for (x: String <- stringList) {
      PiecesEnum.withNameOpt(x) match {
        case Some(pieceEnum) => pieceList = pieceList :+ PieceFactory.apply(pieceEnum, false)
        case None =>
      }
    }
    pieceList
  }
}
