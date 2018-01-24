package de.htwg.se.ShoShogi.aview.gui

import de.htwg.se.ShoShogi.controller.controllerComponent.{ ControllerInterface, MoveResult }
import de.htwg.se.ShoShogi.model.pieceComponent.PieceInterface
import de.htwg.se.ShoShogi.model.pieceComponent.pieceBaseImpl.PieceFactory

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

  val initialState: State = new InitialState
  val onBoardMarkedState: State = new OnBoardMarkedState
  val containerMarkedState: State = new ContainerMarkedState

  def resetPiecePosOnBoard(): Unit = piecePosOnBoard = (-1, -1)

  def resetPieceContainer(): Unit = pieceContainer = PieceFactory.getEmptyPiece

  var currentState: State = initialState

  case class CustomButton(currentPiece: PieceInterface, pos: (Int, Int) = (-1, -1), isInContainer: Boolean) extends Button

  var piecePosOnBoard: (Int, Int) = (-1, -1)
  var pieceContainer: PieceInterface = PieceFactory.getEmptyPiece

  case class OnBoardMarkedState() extends State {
    override def move(controller: ControllerInterface, desPos: (Int, Int)): MoveResult.Value = {
      val temp = controller.movePiece(piecePosOnBoard, desPos)

      if (temp == MoveResult.validMove ||
        temp == MoveResult.kingSlain) {
        resetPiecePosOnBoard()
        currentState = initialState
      }
      temp
    }
  }

  case class ContainerMarkedState() extends State {
    override def move(controller: ControllerInterface, desPos: (Int, Int)): MoveResult.Value = {
      controller.moveConqueredPiece(pieceContainer.toString.trim, desPos)
      resetPieceContainer()
      currentState = initialState
      MoveResult.validMoveContainer
    }
  }

}

object PieceClickedReaction extends PieceClickedInterface {
  def movePiece(controller: ControllerInterface, desPos: (Int, Int)): MoveResult.Value = {
    currentState.move(controller, desPos: (Int, Int))
  }

  def getPossibleMoves(customButton: CustomButton, controller: ControllerInterface): List[(Int, Int)] = {
    var returnList = List[(Int, Int)]()

    if (customButton.isInContainer) {
      resetPiecePosOnBoard()
      resetPieceContainer()
      pieceContainer = customButton.currentPiece
      currentState = containerMarkedState
      returnList = controller.getPossibleMovesConqueredPiece(customButton.currentPiece.toString.trim)
    } else {
      if (piecePosOnBoard == (-1, -1) || piecePosOnBoard == customButton.pos) {
        returnList = controller.getPossibleMoves(customButton.pos)
        if (returnList.nonEmpty) {
          resetPiecePosOnBoard()
          resetPieceContainer()
          piecePosOnBoard = customButton.pos
          currentState = onBoardMarkedState
        }
      } else {
        resetPiecePosOnBoard()
        resetPieceContainer()
        currentState = initialState
      }
    }

    returnList
  }
}