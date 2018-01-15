package de.htwg.se.ShoShogi.aview.gui

import de.htwg.se.ShoShogi.controller.{ Controller, ControllerInterface, MoveResult }
import de.htwg.se.ShoShogi.model.{ EmptyPiece, Piece }

import scala.swing.Button

trait PieceClickedInterface {

  trait State {
    def move(controller: ControllerInterface, desPos: (Int, Int)): MoveResult.Value
  }

  case class InitialState() extends State {
    override def move(controller: ControllerInterface, desPos: (Int, Int)): MoveResult.Value = {
      MoveResult.invalidMove
    }
  }

  case class OnBoardMarkedState() extends State {
    override def move(controller: ControllerInterface, desPos: (Int, Int)): MoveResult.Value = {
      val temp = controller.movePiece(piecePosOnBoard, desPos)

      if (temp == MoveResult.validMove ||
        temp == MoveResult.kingSlain) {
        resetPiecePosOnBoard
        currentState = new InitialState
      }
      temp
    }
  }

  case class ContainerMarkedState() extends State {
    override def move(controller: ControllerInterface, desPos: (Int, Int)): MoveResult.Value = {
      controller.moveConqueredPiece(pieceContainer.toString.trim, desPos)
      resetPieceContainer
      currentState = new InitialState
      MoveResult.validMoveContainer
    }
  }

  var currentState: State = new InitialState

  case class CustomButton(currentPiece: Piece, pos: (Int, Int) = (-1, -1), isInContainer: Boolean) extends Button

  private var piecePosOnBoard: (Int, Int) = (-1, -1)
  private var pieceContainer: Piece = new EmptyPiece

  def setPiecePosOnBoard(pos: (Int, Int)): Unit = piecePosOnBoard = pos

  def setPieceContainer(piece: Piece): Unit = pieceContainer = piece

  def resetPiecePosOnBoard: Unit = piecePosOnBoard = (-1, -1)

  def resetPieceContainer: Unit = pieceContainer = new EmptyPiece

}

object PieceClickedReaction extends PieceClickedInterface {
  def movePiece(controller: ControllerInterface, desPos: (Int, Int)): MoveResult.Value = {
    currentState.move(controller, desPos: (Int, Int))
  }

  def getMoves(customButton: CustomButton, controller: ControllerInterface): List[(Int, Int)] = {
    if (customButton.isInContainer) {
      setPieceContainer(customButton.currentPiece)
      currentState = new ContainerMarkedState
      controller.possibleMovesConqueredPiece(customButton.currentPiece.toString.trim)
    } else {
      val temp = controller.possibleMoves(customButton.pos)
      if (temp.size > 0) {
        setPiecePosOnBoard(customButton.pos)
        currentState = new OnBoardMarkedState
      } else {
        resetPiecePosOnBoard
        currentState = new InitialState
      }
      temp
    }
  }
}
