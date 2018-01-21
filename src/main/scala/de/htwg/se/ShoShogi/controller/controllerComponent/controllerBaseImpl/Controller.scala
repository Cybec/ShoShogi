package de.htwg.se.ShoShogi.controller.controllerComponent.controllerBaseImpl

import com.google.inject.name.Names
import com.google.inject.{ Guice, Inject }
import de.htwg.se.ShoShogi.ShoShogiModule
import de.htwg.se.ShoShogi.controller.controllerComponent._
import de.htwg.se.ShoShogi.model.boardComponent.BoardInterface
import de.htwg.se.ShoShogi.model.fileIoComponent.FileIOInterface
import de.htwg.se.ShoShogi.model.pieceComponent.pieceBaseImpl.{ Piece, PieceFactory, PiecesEnum }
import de.htwg.se.ShoShogi.model.playerComponent.Player
import de.htwg.se.ShoShogi.util.UndoManager
import net.codingwell.scalaguice.InjectorExtensions._

class Controller @Inject() extends RoundState with ControllerInterface {
  val injector = Guice.createInjector(new ShoShogiModule)
  val fileIo = injector.instance[FileIOInterface]
  var board: BoardInterface = injector.instance[BoardInterface](Names.named("normal")).createNewBoard()
  var player_1: Player = new Player("Player1", true)
  var player_2: Player = new Player("Player2", false)

  override def getPlayers: (Player, Player) = {
    (new Player(player_1.name, player_1.first), new Player(player_2.name, player_2.first))
  }

  private val undoManager = new UndoManager

  val playerOnesTurn: RoundState = new playerOneRound(this)
  val playerTwosTurn: RoundState = new playerTwoRound(this)
  var currentState: RoundState = playerOnesTurn
  override val boardSize = 9

  override def changeNamePlayer1(newName: String): Unit = player_1 = new Player(newName, player_1.first)

  override def changeNamePlayer2(newName: String): Unit = player_2 = new Player(newName, player_2.first)

  override def getContainer: (List[Piece], List[Piece]) = board.getContainer()

  override def setContainer(container: (List[Piece], List[Piece])): Unit = {
    board = board.setContainer(container)
  }

  override def saveState: Unit = {
    undoManager.saveStep(new SolveCommand(this))
  }

  override def undoCommand: Unit = {
    undoManager.undoStep
    publish(new UpdateAll)
  }

  override def redoCommand: Unit = {
    undoManager.redoStep
    publish(new UpdateAll)
  }

  override def save: Unit = {
    fileIo.save(board, currentState, player_1, player_2)
  }

  override def load: Unit = {
    val boardOption = fileIo.load
    boardOption match {
      case None => {
        createEmptyBoard()
      }
      case Some((_board, _currentState, _player1, _player2)) => {
        board = _board
        currentState = _currentState
        player_1 = _player1
        player_2 = _player2
      }
    }
    publish(new UpdateAll)
  }

  override def createEmptyBoard(): Unit = {
    board = injector.instance[BoardInterface](Names.named("normal")).createNewBoard()
    //    board = new Board(boardSize, PieceFactory.apply(PiecesEnum.EmptyPiece, player_1))

    currentState = playerOnesTurn
    publish(new UpdateAll)
  }

  def getBoardClone: BoardInterface = board.copyBoard()

  def replaceBoard(newBoard: BoardInterface): Unit = board = newBoard

  override def createNewBoard(): Unit = {
    board = injector.instance[BoardInterface](Names.named("normal")).createNewBoard()
    //    board = new Board(boardSize, PieceFactory.apply(PiecesEnum.EmptyPiece, player_1))

    //Steine fuer Spieler 1
    board = board.replaceCell(0, 0, PieceFactory.apply(PiecesEnum.Lancer, player_1))
    board = board.replaceCell(1, 0, PieceFactory.apply(PiecesEnum.Knight, player_1))
    board = board.replaceCell(2, 0, PieceFactory.apply(PiecesEnum.SilverGeneral, player_1))
    board = board.replaceCell(3, 0, PieceFactory.apply(PiecesEnum.GoldenGeneral, player_1))
    board = board.replaceCell(4, 0, PieceFactory.apply(PiecesEnum.King, player_1))
    board = board.replaceCell(5, 0, PieceFactory.apply(PiecesEnum.GoldenGeneral, player_1))
    board = board.replaceCell(6, 0, PieceFactory.apply(PiecesEnum.SilverGeneral, player_1))
    board = board.replaceCell(7, 0, PieceFactory.apply(PiecesEnum.Knight, player_1))
    board = board.replaceCell(8, 0, PieceFactory.apply(PiecesEnum.Lancer, player_1))
    board = board.replaceCell(7, 1, PieceFactory.apply(PiecesEnum.Bishop, player_1))
    board = board.replaceCell(1, 1, PieceFactory.apply(PiecesEnum.Rook, player_1))
    for (i <- 0 to 8) {
      board = board.replaceCell(i, 2, PieceFactory.apply(PiecesEnum.Pawn, player_1))
    }

    //Steine fuer Spieler 2
    board = board.replaceCell(0, 8, PieceFactory.apply(PiecesEnum.Lancer, player_2))
    board = board.replaceCell(1, 8, PieceFactory.apply(PiecesEnum.Knight, player_2))
    board = board.replaceCell(2, 8, PieceFactory.apply(PiecesEnum.SilverGeneral, player_2))
    board = board.replaceCell(3, 8, PieceFactory.apply(PiecesEnum.GoldenGeneral, player_2))
    board = board.replaceCell(4, 8, PieceFactory.apply(PiecesEnum.King, player_2))
    board = board.replaceCell(5, 8, PieceFactory.apply(PiecesEnum.GoldenGeneral, player_2))
    board = board.replaceCell(6, 8, PieceFactory.apply(PiecesEnum.SilverGeneral, player_2))
    board = board.replaceCell(7, 8, PieceFactory.apply(PiecesEnum.Knight, player_2))
    board = board.replaceCell(8, 8, PieceFactory.apply(PiecesEnum.Lancer, player_2))
    board = board.replaceCell(1, 7, PieceFactory.apply(PiecesEnum.Bishop, player_2))
    board = board.replaceCell(7, 7, PieceFactory.apply(PiecesEnum.Rook, player_2))
    for (i <- 0 to 8) {
      board = board.replaceCell(i, 6, PieceFactory.apply(PiecesEnum.Pawn, player_2))
    }

    publish(new StartNewGame)
    currentState = playerOnesTurn
    saveState
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

  override def boardToArray(): Array[Array[Piece]] = board.toArray

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
    currentState.getPossibleMvConPlayer(piece)
  }

  override def getPossibleMvConPlayer(piece: String): List[(Int, Int)] = {
    currentState.getPossibleMvConPlayer(piece)
  }

  override def promotable(position: (Int, Int)): Boolean = {
    val piece = board.cell(position._1, position._2).getOrElse(return false)
    piece.hasPromotion && ((piece.player == player_1 && position._2 > 5) || (piece.player == player_2 && position._2 < 3))
  }

  override def promotePiece(piecePosition: (Int, Int)): Boolean = {
    var piece = board.cell(piecePosition._1, piecePosition._2).getOrElse(return false)
    piece = piece.promotePiece.getOrElse(return false)
    board = board.replaceCell(piecePosition._1, piecePosition._2, piece)
    publish(new UpdateAll)
    true
  }

  override def changeState(): Unit = {
    currentState.changeState()
  }

  def getBoard = board
}
