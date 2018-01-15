package de.htwg.se.ShoShogi.controller

import de.htwg.se.ShoShogi.model._
import de.htwg.se.ShoShogi.util.UndoManager

// TODO 1: schauen ob vals und vars aus dem Klassen parameter entfernt werden koennen

//noinspection ScalaStyle
class Controller(var board: Board, var player_1: Player, var player_2: Player) extends RoundState with ControllerInterface {
  private val undoManager = new UndoManager

  val playerOnesTurn: RoundState = new playerOneRound(this)
  val playerTwosTurn: RoundState = new playerTwoRound(this)
  var currentState: RoundState = playerOnesTurn
  override val boardSize = 9

  override def changeNamePlayer1(newName: String): Unit = player_1 = new Player(newName, player_1.first)

  override def changeNamePlayer2(newName: String): Unit = player_2 = new Player(newName, player_2.first)

  override def getContainer: (List[Piece], List[Piece]) = board.getContainer()

  def setContainer(container: (List[Piece], List[Piece])): Unit = board = board.setContainer(container)

  def solve: Unit = {
    undoManager.doStep(new SolveCommand(this))
    currentState.changeState()
    publish(new UpdateAll)
  }

  def undo: Unit = {
    undoManager.undoStep
    currentState.changeState()
    publish(new UpdateAll)
  }

  def redo: Unit = {
    undoManager.redoStep
    currentState.changeState()
    publish(new UpdateAll)
  }

  def getBoardClone: Board = board.clone()

  def replaceBoard(newBoard: Board): Unit = board = newBoard

  override def createEmptyBoard(): Unit = {
    board = new Board(boardSize, pieceFactory.apply("EmptyPiece", player_1))
    publish(new UpdateAll)
  }

  override def createNewBoard(): Unit = {
    board = new Board(boardSize, pieceFactory.apply("EmptyPiece", player_1))

    //Steine fuer Spieler 1
    board = board.replaceCell(0, 0, pieceFactory.apply("Lancer", player_1))
    board = board.replaceCell(1, 0, pieceFactory.apply("Knight", player_1))
    board = board.replaceCell(2, 0, pieceFactory.apply("SilverGeneral", player_1))
    board = board.replaceCell(3, 0, pieceFactory.apply("GoldenGeneral", player_1))
    board = board.replaceCell(4, 0, pieceFactory.apply("King", player_1))
    board = board.replaceCell(5, 0, pieceFactory.apply("GoldenGeneral", player_1))
    board = board.replaceCell(6, 0, pieceFactory.apply("SilverGeneral", player_1))
    board = board.replaceCell(7, 0, pieceFactory.apply("Knight", player_1))
    board = board.replaceCell(8, 0, pieceFactory.apply("Lancer", player_1))
    board = board.replaceCell(7, 1, pieceFactory.apply("Bishop", player_1))
    board = board.replaceCell(1, 1, pieceFactory.apply("Rook", player_1))
    for (i <- 0 to 8) {
      board = board.replaceCell(i, 2, pieceFactory.apply("Pawn", player_1))
    }

    //Steine fuer Spieler 2
    board = board.replaceCell(0, 8, pieceFactory.apply("Lancer", player_2))
    board = board.replaceCell(1, 8, pieceFactory.apply("Knight", player_2))
    board = board.replaceCell(2, 8, pieceFactory.apply("SilverGeneral", player_2))
    board = board.replaceCell(3, 8, pieceFactory.apply("GoldenGeneral", player_2))
    board = board.replaceCell(4, 8, pieceFactory.apply("King", player_2))
    board = board.replaceCell(5, 8, pieceFactory.apply("GoldenGeneral", player_2))
    board = board.replaceCell(6, 8, pieceFactory.apply("SilverGeneral", player_2))
    board = board.replaceCell(7, 8, pieceFactory.apply("Knight", player_2))
    board = board.replaceCell(8, 8, pieceFactory.apply("Lancer", player_2))
    board = board.replaceCell(1, 7, pieceFactory.apply("Bishop", player_2))
    board = board.replaceCell(7, 7, pieceFactory.apply("Rook", player_2))
    for (i <- 0 to 8) {
      board = board.replaceCell(i, 6, pieceFactory.apply("Pawn", player_2))
    }

    publish(new StartNewGame)
    currentState = playerOnesTurn
  }

  override def boardToString(): String = board.toString

  override def boardToArray(): Array[Array[Piece]] = board.toArray

  override def possibleMoves(pos: (Int, Int)): List[(Int, Int)] = {
    currentState.possibleMoves(pos)
  }

  override def movePiece(currentPos: (Int, Int), destination: (Int, Int)): MoveResult.Value = {
    val result: MoveResult.Value = currentState.movePiece(currentPos, destination)
    publish(new UpdateAll)
    currentState.changeState()
    result
  }

  override def possibleMovesConqueredPiece(piece: String): List[(Int, Int)] = {
    currentState.possibleMovesConqueredPiece(piece)
  }

  override def moveConqueredPiece(pieceAbbreviation: String, destination: (Int, Int)): Boolean = {
    val result: Boolean = currentState.moveConqueredPiece(pieceAbbreviation, destination)
    publish(new UpdateAll)
    currentState.changeState()
    result
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
