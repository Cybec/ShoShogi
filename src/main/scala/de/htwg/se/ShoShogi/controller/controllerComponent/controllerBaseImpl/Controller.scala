package de.htwg.se.ShoShogi.controller.controllerComponent.controllerBaseImpl

import com.google.inject.name.Names
import com.google.inject.{Guice, Inject, Injector}
import de.htwg.se.ShoShogi.ShoShogiModule
import de.htwg.se.ShoShogi.controller.controllerComponent._
import de.htwg.se.ShoShogi.model.boardComponent.BoardInterface
import de.htwg.se.ShoShogi.model.fileIoComponent.FileIOInterface
import de.htwg.se.ShoShogi.model.pieceComponent.PieceInterface
import de.htwg.se.ShoShogi.model.pieceComponent.pieceBaseImpl.{PieceFactory, PiecesEnum}
import de.htwg.se.ShoShogi.model.playerComponent.Player
import de.htwg.se.ShoShogi.util.UndoManager
import net.codingwell.scalaguice.InjectorExtensions._

class Controller @Inject() extends RoundState with ControllerInterface {
  val injector: Injector = Guice.createInjector(new ShoShogiModule)
  val fileIo: FileIOInterface = injector.instance[FileIOInterface]
  var board: BoardInterface = injector.instance[BoardInterface](Names.named("normal")).createNewBoard()
  val playerOnesTurn: RoundState = playerOneRound(this)
  val playerTwosTurn: RoundState = playerTwoRound(this)
  var player_1: Player = Player("Player1", first = true)

  private val undoManager = new UndoManager
  var player_2: Player = Player("Player2", first = false)

  override def getPlayers: (Player, Player) = {
    (Player(player_1.name, player_1.first), Player(player_2.name, player_2.first))
  }

  var currentState: RoundState = playerOnesTurn
  override val boardSize = 9

  override def changeNamePlayer1(Name: String): Unit = player_1 = Player(Name, player_1.first)

  override def changeNamePlayer2(Name: String): Unit = player_2 = Player(Name, player_2.first)

  override def getContainer: (List[PieceInterface], List[PieceInterface]) = board.getContainer

  override def setContainer(container: (List[PieceInterface], List[PieceInterface])): Unit = {
    board = board.setContainer(container)
  }

  override def undoCommand(): Unit = {
    undoManager.undoStep()
    publish(new UpdateAll)
  }

  override def redoCommand(): Unit = {
    undoManager.redoStep()
    publish(new UpdateAll)
  }

  override def save(): Unit = {
    val state = if (currentState.isInstanceOf[playerOneRound]) true else false
    fileIo.save(board, state, player_1, player_2)
  }

  override def load(): Unit = {
    val boardOption = fileIo.load
    boardOption match {
      case None =>
        createEmptyBoard()
      case Some((_board, state, _player1, _player2)) =>
        board = _board
        currentState = if (state) playerOnesTurn else playerTwosTurn
        player_1 = _player1
        player_2 = _player2
    }
    publish(new UpdateAll)
  }

  override def createEmptyBoard(): Unit = {
    board = injector.instance[BoardInterface](Names.named("normal")).createNewBoard()
    //    board =  Board(boardSize, PieceFactory.apply(PiecesEnum.EmptyPiece, player_1))

    currentState = playerOnesTurn
    publish(new UpdateAll)
  }

  def replaceBoard(Board: BoardInterface): Unit = board = Board

  def getBoardClone: BoardInterface = board.copyBoard()

  override def createNewBoard(): Unit = {
    board = injector.instance[BoardInterface](Names.named("normal")).createNewBoard()

    val col_0, row_0 = 0
    val col_1, row_1 = 1
    val col_2, row_2 = 2
    val col_3 = 3
    val col_4 = 4
    val col_5 = 5
    val col_6, row_6 = 6
    val col_7, row_7 = 7
    val col_8, row_8 = 8

    board = board.replaceCell(col_0, row_0, PieceFactory.apply(PiecesEnum.Lancer, player_1.first))
    board = board.replaceCell(col_1, row_0, PieceFactory.apply(PiecesEnum.Knight, player_1.first))
    board = board.replaceCell(col_2, row_0, PieceFactory.apply(PiecesEnum.SilverGeneral, player_1.first))
    board = board.replaceCell(col_3, row_0, PieceFactory.apply(PiecesEnum.GoldenGeneral, player_1.first))
    board = board.replaceCell(col_4, row_0, PieceFactory.apply(PiecesEnum.King, player_1.first))
    board = board.replaceCell(col_5, row_0, PieceFactory.apply(PiecesEnum.GoldenGeneral, player_1.first))
    board = board.replaceCell(col_6, row_0, PieceFactory.apply(PiecesEnum.SilverGeneral, player_1.first))
    board = board.replaceCell(col_7, row_0, PieceFactory.apply(PiecesEnum.Knight, player_1.first))
    board = board.replaceCell(col_8, row_0, PieceFactory.apply(PiecesEnum.Lancer, player_1.first))
    board = board.replaceCell(col_7, row_1, PieceFactory.apply(PiecesEnum.Bishop, player_1.first))
    board = board.replaceCell(col_1, row_1, PieceFactory.apply(PiecesEnum.Rook, player_1.first))
    for (i <- 0 to 8) {
      board = board.replaceCell(i, row_2, PieceFactory.apply(PiecesEnum.Pawn, player_1.first))
    }

    board = board.replaceCell(col_0, row_8, PieceFactory.apply(PiecesEnum.Lancer, player_2.first))
    board = board.replaceCell(col_1, row_8, PieceFactory.apply(PiecesEnum.Knight, player_2.first))
    board = board.replaceCell(col_2, row_8, PieceFactory.apply(PiecesEnum.SilverGeneral, player_2.first))
    board = board.replaceCell(col_3, row_8, PieceFactory.apply(PiecesEnum.GoldenGeneral, player_2.first))
    board = board.replaceCell(col_4, row_8, PieceFactory.apply(PiecesEnum.King, player_2.first))
    board = board.replaceCell(col_5, row_8, PieceFactory.apply(PiecesEnum.GoldenGeneral, player_2.first))
    board = board.replaceCell(col_6, row_8, PieceFactory.apply(PiecesEnum.SilverGeneral, player_2.first))
    board = board.replaceCell(col_7, row_8, PieceFactory.apply(PiecesEnum.Knight, player_2.first))
    board = board.replaceCell(col_8, row_8, PieceFactory.apply(PiecesEnum.Lancer, player_2.first))
    board = board.replaceCell(col_1, row_7, PieceFactory.apply(PiecesEnum.Bishop, player_2.first))
    board = board.replaceCell(col_7, row_7, PieceFactory.apply(PiecesEnum.Rook, player_2.first))
    for (i <- 0 to 8) {
      board = board.replaceCell(i, row_6, PieceFactory.apply(PiecesEnum.Pawn, player_2.first))
    }

    publish(new StartNewGame)
    currentState = playerOnesTurn
    saveState()
  }

  override def saveState(): Unit = {
    undoManager.saveStep(new SolveCommand(this))
  }

  override def movePiece(currentPos: (Int, Int), destination: (Int, Int)): MoveResult.Value = {
    val result: MoveResult.Value = currentState.movePiece(currentPos, destination)
    publish(new UpdateAll)
    if (result == MoveResult.validMove ||
      result == MoveResult.kingSlain ||
      result == MoveResult.validMoveContainer) {
      currentState.changeState()
    }
    result
  }

  override def boardToString(): String = board.toString

  override def boardToArray(): Array[Array[PieceInterface]] = board.toArray

  override def getPossibleMoves(pos: (Int, Int)): List[(Int, Int)] = {
    currentState.getPossibleMoves(pos)
  }

  override def moveConqueredPiece(pieceAbbreviation: String, destination: (Int, Int)): Boolean = {
    val result: Boolean = currentState.moveConqueredPiece(pieceAbbreviation, destination)
    if (result) {
      publish(new UpdateAll)
      currentState.changeState()
    }
    result
  }

  override def getPossibleMovesConqueredPiece(piece: String): List[(Int, Int)] = {
    currentState.getPossibleMovesConqueredPiece(piece)
  }

  override def promotable(position: (Int, Int)): Boolean = {
    //noinspection ScalaStyle
    val piece = board.cell(position._1, position._2).getOrElse(return false)
    piece.hasPromotion && ((piece.isFirstOwner == player_1.first && position._2 > 5) ||
      (piece.isFirstOwner == player_2.first && position._2 < 3))
  }

  override def promotePiece(piecePosition: (Int, Int)): Boolean = {
    //noinspection ScalaStyle
    var piece = board.cell(piecePosition._1, piecePosition._2).getOrElse(return false)
    //noinspection ScalaStyle
    piece = piece.promotePiece.getOrElse(return false)
    board = board.replaceCell(piecePosition._1, piecePosition._2, piece)
    publish(new UpdateAll)
    true
  }

  override def changeState(): Unit = {
    currentState.changeState()
  }

  override def startSimulation: Unit = Simulator.start(this)
}
