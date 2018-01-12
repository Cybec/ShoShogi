package de.htwg.se.ShoShogi.controller

import de.htwg.se.ShoShogi.model._

//noinspection ScalaStyle
class playerRounds() {

  trait roundState {
    def changeState()
    def possibleMovesConqueredPiece(piece: String): List[(Int, Int)]
    def moveConqueredPiece(pieceAbbreviation: String, destination: (Int, Int)): Boolean
    def possibleMoves(pos: (Int, Int)): List[(Int, Int)]
    def movePiece(currentPos: (Int, Int), destination: (Int, Int))
    def getPossibleMvConPlayer(piece: String): List[(Int, Int)]
  }
  case class playerOneRound(controller: Controller) extends roundState {

    def changeState() = controller.currentState = controller.playerTwosTurn

    def possibleMoves(pos: (Int, Int)): List[(Int, Int)] = {
      board.cell(pos._1, pos._2) match {
        case Some(piece) => {
          if (controller.currentState.isInstanceOf[playerOneRound]) {
            piece.getMoveSet((pos._1, pos._2), board)
          } else {
            List()
          }
        }
        case None => List()
      }
    }

    def movePiece(currentPos: (Int, Int), destination: (Int, Int)): controller.MoveResult.Value = {
      if (possibleMoves(currentPos).contains(destination)) {

        val tempPieceDestination = board.cell(destination._1, destination._2).getOrElse(return MoveResult.invalidMove)
        val tempPieceCurrent = board.cell(currentPos._1, currentPos._2).getOrElse(return MoveResult.invalidMove)

        if (controller.currentState.isInstanceOf[playerOneRound]) {

          board = board.replaceCell(destination._1, destination._2, tempPieceCurrent)
          board = board.replaceCell(currentPos._1, currentPos._2, pieceFactory.apply("EmptyPiece", player_1))

          board = board.addToPlayerContainer(tempPieceCurrent.player, tempPieceDestination)
          publish(new UpdateAll)

          if (tempPieceDestination.isInstanceOf[King]) {
            MoveResult.kingSlain
          } else {
            MoveResult.validMove
          }
        } else {
          MoveResult.invalidMove
        }
      } else {
        MoveResult.invalidMove
      }
    }

    def possibleMovesConqueredPiece(piece: String): List[(Int, Int)] = movesPossible = getPossibleMvConPlayer(piece)

    def moveConqueredPiece(pieceAbbreviation: String, destination: (Int, Int)): Boolean = {
      if (possibleMovesConqueredPiece(pieceAbbreviation).contains(destination)) {

        var tempPiece: Piece = pieceFactory.apply("EmptyPiece", player_1)

        val success = board.getFromPlayerContainer(player_1) {
          _.typeEquals(pieceAbbreviation)
        } match {
          case Some((newBoard, piece)) =>
            board = newBoard
            tempPiece = piece
            true
          case None => false
        }
        if (success) {
          board = board.replaceCell(destination._1, destination._2, tempPiece)
          publish(new UpdateAll)
          true
        } else {
          false
        }
      } else {
        false
      }
    }

    def getPossibleMvConPlayer(piece: String): List[(Int, Int)] = {
      var possibleMoves = List[(Int, Int)]()

      if (piece == "P") {
        for (column: Int <- 0 until board.size) {
          if (!board.getPiecesInColumn(column, true).find((x: Piece) => x.typeEquals("P") || x.typeEquals("P°"))) {
            if (!board.getPiecesInColumn(column, true).exists((x: Piece) => x.typeEquals("K") || x.typeEquals("K°"))) {
              possibleMoves = possibleMoves ::: board.getEmptyCellsInColumn(column, (0, 7))
            } else {
              for (row <- 0 to 8) {
                board.cell(column, row) match {
                  case Some(piece) => if (piece.isInstanceOf[EmptyPiece]) {
                    possibleMoves = possibleMoves :+ (column, row)
                  } else if (piece.isInstanceOf[King] && !piece.player.first) {
                    possibleMoves = possibleMoves.filter(_ != (column, row - 1))
                  }
                }
              }
            }
          }
        }
      } else if (piece == "KN" || piece == "L") {
        for (x <- 0 until board.size) {
          possibleMoves = possibleMoves ::: board.getEmptyCellsInColumn(x, (0, 7))
        }
      } else {
        for (x <- 0 until board.size) {
          possibleMoves = possibleMoves ::: board.getEmptyCellsInColumn(x, (0, 8))
        }
      }
      possibleMoves
    }

  }

  case class playerTwoRound(controller: Controller) extends roundState {

    override def changeState() = controller.currentState = controller.playerOnesTurn

    def possibleMoves(pos: (Int, Int)): List[(Int, Int)] = {
      board.cell(pos._1, pos._2) match {
        case Some(piece) => {
          if (controller.currentState.isInstanceOf[playerTwoRound]) {
            piece.getMoveSet((pos._1, pos._2), board)
          } else {
            List()
          }
        }
        case None => List()
      }
    }

    def movePiece(currentPos: (Int, Int), destination: (Int, Int)): controller.MoveResult.Value = {
      if (possibleMoves(currentPos).contains(destination)) {

        val tempPieceDestination = board.cell(destination._1, destination._2).getOrElse(return MoveResult.invalidMove)
        val tempPieceCurrent = board.cell(currentPos._1, currentPos._2).getOrElse(return MoveResult.invalidMove)

        if (controller.currentState.isInstanceOf[playerTwoRound]) {

          board = board.replaceCell(destination._1, destination._2, tempPieceCurrent)
          board = board.replaceCell(currentPos._1, currentPos._2, pieceFactory.apply("EmptyPiece", player_2))

          board = board.addToPlayerContainer(tempPieceCurrent.player, tempPieceDestination)
          publish(new UpdateAll)

          if (tempPieceDestination.isInstanceOf[King]) {
            MoveResult.kingSlain
          } else {
            MoveResult.validMove
          }
        } else {
          MoveResult.invalidMove
        }
      } else {
        MoveResult.invalidMove
      }
    }

    def possibleMovesConqueredPiece(piece: String): List[(Int, Int)] = movesPossible = getPossibleMvConPlayer2(piece)

    def moveConqueredPiece(pieceAbbreviation: String, destination: (Int, Int)): Boolean = {
      if (possibleMovesConqueredPiece(pieceAbbreviation).contains(destination)) {

        var tempPiece: Piece = pieceFactory.apply("EmptyPiece", player_2)

        val success = board.getFromPlayerContainer(player_2) {
          _.typeEquals(pieceAbbreviation)
        } match {
          case Some((newBoard, piece)) =>
            board = newBoard
            tempPiece = piece
            true
          case None => false
        }
        if (success) {
          board = board.replaceCell(destination._1, destination._2, tempPiece)
          publish(new UpdateAll)
          true
        } else {
          false
        }
      } else {
        false
      }
    }

    def getPossibleMvConPlayer(piece: String): List[(Int, Int)] = {
      var possibleMoves = List[(Int, Int)]()
      var count = 0
      if (piece == "P°") {
        for (column: Int <- 0 until board.size) {
          if (!controller.board.getPiecesInColumn(column, false).exists((x: Piece) => x.typeEquals("P") || x.typeEquals("P°"))) {
            if (!board.getPiecesInColumn(column, false).exists((x: Piece) => x.typeEquals("K") || x.typeEquals("K°"))) {
              possibleMoves = possibleMoves ::: board.getEmptyCellsInColumn(column, (1, 8))
            } else {
              for (row <- 0 to 8) {
                board.cell(column, row + board.size - 1 - count) match {
                  case Some(piece) => if (piece.isInstanceOf[EmptyPiece] && row != 8) {
                    possibleMoves = possibleMoves :+ (column, row + board.size - 1 - count)
                  } else if (piece.isInstanceOf[King] && piece.player.first) {
                    possibleMoves = possibleMoves.filter(_ != (column, row + board.size - count))
                  }
                }
                count = count + 2
              }
            }
          }
        }

      } else if (piece == "KN°" || piece == "L°") {
        for (x: Int <- 0 until board.size) {
          possibleMoves = possibleMoves ::: board.getEmptyCellsInColumn(x, (1, 8))
        }
      } else {
        for (x: Int <- 0 until board.size) {
          possibleMoves = possibleMoves ::: board.getEmptyCellsInColumn(x, (0, 8))
        }
      }
      possibleMoves
    }

  }
}

