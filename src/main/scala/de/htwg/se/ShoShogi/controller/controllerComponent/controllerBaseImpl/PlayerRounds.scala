package de.htwg.se.ShoShogi.controller.controllerComponent.controllerBaseImpl

import de.htwg.se.ShoShogi.controller.controllerComponent.MoveResult
import de.htwg.se.ShoShogi.model.boardComponent.BoardInterface
import de.htwg.se.ShoShogi.model.pieceComponent.pieceBaseImpl._

//noinspection ScalaStyle
trait RoundState {

  def changeState()

  def moveConqueredPiece(pieceAbbreviation: String, destination: (Int, Int)): Boolean

  def getPossibleMoves(pos: (Int, Int)): List[(Int, Int)]

  def movePiece(currentPos: (Int, Int), destination: (Int, Int)): MoveResult.Value

  def getPossibleMvConPlayer(piece: String): List[(Int, Int)]
}

case class playerOneRound(controller: Controller) extends RoundState {

  override def changeState() = controller.currentState = controller.playerTwosTurn

  override def getPossibleMoves(pos: (Int, Int)): List[(Int, Int)] = {
    controller.board.cell(pos._1, pos._2) match {
      case Some(piece) => {
        if (piece.player.first) {
          piece.getMoveSet((pos._1, pos._2), controller.board)
        } else {
          List()
        }
      }
      case None => List()
    }
  }

  override def movePiece(currentPos: (Int, Int), destination: (Int, Int)): MoveResult.Value = {
    if (getPossibleMoves(currentPos).contains(destination) && controller.currentState.isInstanceOf[playerOneRound]) {
      controller.saveState

      val tempPieceDestination = controller.board.cell(destination._1, destination._2).getOrElse(return MoveResult.invalidMove)
      val tempPieceCurrent = controller.board.cell(currentPos._1, currentPos._2).getOrElse(return MoveResult.invalidMove)

      controller.board = controller.board.replaceCell(destination._1, destination._2, tempPieceCurrent)
      controller.board = controller.board.replaceCell(currentPos._1, currentPos._2, PieceFactory.apply(PiecesEnum.EmptyPiece, controller.player_1))

      controller.board = controller.board.addToPlayerContainer(tempPieceCurrent.player, tempPieceDestination)

      if (PieceFactory.isInstanceOfPiece(PiecesEnum.King, tempPieceDestination)) {
        MoveResult.kingSlain
      } else {
        MoveResult.validMove
      }
    } else {
      MoveResult.invalidMove
    }
  }

  override def moveConqueredPiece(pieceAbbreviation: String, destination: (Int, Int)): Boolean = {
    if (getPossibleMvConPlayer(pieceAbbreviation).contains(destination)) {

      controller.board.getFromPlayerContainer(controller.player_1) {
        _.typeEquals(pieceAbbreviation)
      } match {
        case Some((newBoard: BoardInterface, piece: Piece)) =>
          controller.saveState

          controller.board = newBoard
          controller.board = controller.board.replaceCell(destination._1, destination._2, piece)
          true
        case None => false
      }
    } else {
      false
    }
  }

  override def getPossibleMvConPlayer(piece: String): List[(Int, Int)] = {
    var possibleMoves = List[(Int, Int)]()

    if (!piece.endsWith("°")) {
      return possibleMoves
    }

    if (piece == "P°") {
      for (column: Int <- 0 until controller.board.size) {
        if (!controller.board.getPiecesInColumn(column, stateTurn = true).exists((x: Piece) => x.typeEquals("P°"))) {
          if (!controller.board.getPiecesInColumn(column, stateTurn = true).exists((x: Piece) => x.typeEquals("K°"))) {
            possibleMoves = possibleMoves ::: controller.board.getEmptyCellsInColumn(column, (0, 7))
          } else {
            for (row <- 0 to 8) {
              controller.board.cell(column, row) match {
                case Some(piece) => if (PieceFactory.isInstanceOfPiece(PiecesEnum.EmptyPiece, piece)) {
                  possibleMoves = possibleMoves :+ (column, row)
                } else if (PieceFactory.isInstanceOfPiece(PiecesEnum.King, piece) && !piece.player.first) {
                  possibleMoves = possibleMoves.filter(_ != (column, row - 1))
                }
                case None => {
                }
              }
            }
          }
        }
      }
    } else if (piece == "KN°" || piece == "L°") {
      for (x <- 0 until controller.board.size) {
        possibleMoves = possibleMoves ::: controller.board.getEmptyCellsInColumn(x, (0, 7))
      }
    } else {
      for (x <- 0 until controller.board.size) {
        possibleMoves = possibleMoves ::: controller.board.getEmptyCellsInColumn(x, (0, 8))
      }
    }
    possibleMoves
  }

}

case class playerTwoRound(controller: Controller) extends RoundState {

  override def changeState() = controller.currentState = controller.playerOnesTurn

  override def getPossibleMoves(pos: (Int, Int)): List[(Int, Int)] = {
    controller.board.cell(pos._1, pos._2) match {
      case Some(piece: Piece) => {
        if (!piece.player.first) {
          piece.getMoveSet((pos._1, pos._2), controller.board)
        } else {
          List()
        }
      }
      case None => List()
    }
  }

  override def movePiece(currentPos: (Int, Int), destination: (Int, Int)): MoveResult.Value = {
    if (getPossibleMoves(currentPos).contains(destination) && controller.currentState.isInstanceOf[playerTwoRound]) {
      controller.saveState

      val tempPieceDestination = controller.board.cell(destination._1, destination._2).getOrElse(return MoveResult.invalidMove)
      val tempPieceCurrent = controller.board.cell(currentPos._1, currentPos._2).getOrElse(return MoveResult.invalidMove)

      controller.board = controller.board.replaceCell(destination._1, destination._2, tempPieceCurrent)
      controller.board = controller.board.replaceCell(currentPos._1, currentPos._2, PieceFactory.apply(PiecesEnum.EmptyPiece, controller.player_2))

      controller.board = controller.board.addToPlayerContainer(tempPieceCurrent.player, tempPieceDestination)

      if (PieceFactory.isInstanceOfPiece(PiecesEnum.King, tempPieceDestination)) {
        MoveResult.kingSlain
      } else {
        MoveResult.validMove
      }
    } else {
      MoveResult.invalidMove
    }
  }

  override def moveConqueredPiece(pieceAbbreviation: String, destination: (Int, Int)): Boolean = {
    if (getPossibleMvConPlayer(pieceAbbreviation).contains(destination)) {

      controller.board.getFromPlayerContainer(controller.player_2) {
        _.typeEquals(pieceAbbreviation)
      } match {
        case Some((newBoard: BoardInterface, piece: Piece)) =>
          controller.board = newBoard
          controller.board = controller.board.replaceCell(destination._1, destination._2, piece)
          true
        case None => false
      }
    } else {
      false
    }
  }

  override def getPossibleMvConPlayer(piece: String): List[(Int, Int)] = {
    var possibleMoves = List[(Int, Int)]()

    var count = 0
    if (piece == "P") {
      for (column: Int <- 0 until controller.board.size) {
        if (!controller.board.getPiecesInColumn(column, stateTurn = false).exists((x: Piece) => x.typeEquals("P"))) {
          if (!controller.board.getPiecesInColumn(column, stateTurn = false).exists((x: Piece) => x.typeEquals("K"))) {
            possibleMoves = possibleMoves ::: controller.board.getEmptyCellsInColumn(column, (1, 8))
          } else {
            for (row <- 0 to 8) {
              controller.board.cell(column, row + controller.board.size - 1 - count) match {
                case Some(piece) => if (PieceFactory.isInstanceOfPiece(PiecesEnum.EmptyPiece, piece) && row != 8) {
                  possibleMoves = possibleMoves :+ (column, row + controller.board.size - 1 - count)
                } else if (PieceFactory.isInstanceOfPiece(PiecesEnum.King, piece) && piece.player.first) {
                  possibleMoves = possibleMoves.filter(_ != (column, row + controller.board.size - count))
                }
                case None => {}
              }
              count = count + 2
            }
          }
        }
      }

    } else if (piece == "KN" || piece == "L") {
      for (x: Int <- 0 until controller.board.size) {
        possibleMoves = possibleMoves ::: controller.board.getEmptyCellsInColumn(x, (1, 8))
      }
    } else {
      for (x: Int <- 0 until controller.board.size) {
        possibleMoves = possibleMoves ::: controller.board.getEmptyCellsInColumn(x, (0, 8))
      }
    }
    possibleMoves
  }
}

