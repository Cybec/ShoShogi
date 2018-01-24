package de.htwg.se.ShoShogi.controller.controllerComponent

import de.htwg.se.ShoShogi.controller.controllerComponent.controllerBaseImpl.RoundState
import de.htwg.se.ShoShogi.model.boardComponent.BoardInterface
import de.htwg.se.ShoShogi.model.pieceComponent.PieceInterface
import de.htwg.se.ShoShogi.model.playerComponent.Player

/**
 * An Enumeration which consists of the different results the moving of
 * a Piece can produce.
 */
object MoveResult extends Enumeration {
  type EnumType = Value
  val invalidMove, validMove, kingSlain, validMoveContainer = Value
}

/**
 * A Interface to implement the controll unit for the game Shoshogi.
 */
trait ControllerInterface extends scala.swing.Publisher {

  /**
   * Gets the Copy of the current game board
   * @return a game board
   */
  def getBoardClone: BoardInterface

  /**
   * The typical size of a Shoshogi board is 9*9.
   */
  val boardSize = 9

  /**
   * Saves all moves which change the state of the game on a command stack.
   */
  def saveState: Unit

  /**
   * Sets the game to the same state before the last done move.
   * Removes the last move from the command stack.
   */
  def undoCommand: Boolean

  /**
   * Sets the game to the state before the last undo and adds.
   * the move to the command stack
   */
  def redoCommand: Boolean

  /**
   * Saves the current state of the game in a file.
   */
  def save: Unit

  /**
   * Loads the state of a game from a file.
   */
  def load: Unit

  /**
   * Used to get both players playing the game.
   *
   * @return the two players playing the game.
   */
  def getPlayers: (Player, Player)

  /**
   * Used to change the containers of each player.
   *
   * @param container the List which pieces each player has taken and
   *                  can play on the field again.
   */
  def setContainer(container: (List[PieceInterface], List[PieceInterface])): Unit

  /**
   * Sets the name of the first player to given name
   *
   * @param newName the name the first player should have
   */
  def changeNamePlayer1(newName: String): Unit

  /**
   * Sets the name of the second player to given name
   *
   * @param newName the name the second player should have
   */
  def changeNamePlayer2(newName: String): Unit

  /**
   * Returns the containers of each player consisting of the pieces
   * eacht player took from the other
   *
   * @return two Lists with Pieces in them
   */
  def getContainer: (List[PieceInterface], List[PieceInterface])

  /**
   * Creates a board with the standard size of 9*9 and fills it
   * with EmptyPieces
   */
  def createEmptyBoard(): Unit

  /**
   * Exchanges the current board in use for the given one
   *
   * @param newBoard the board to which the current board will be changed
   */
  def replaceBoard(newBoard: BoardInterface): Unit

  /**
   * Creates a new board, which looks like the standard Shoshogi boards
   */
  def createNewBoard(): Unit

  /**
   * Changes the matrix of the board with filling into a String
   *
   * @return the board in String representation
   */
  def boardToString(): String

  /**
   * Cahnges the matrix of the board with filling into a
   * Array of Arrays with Pieces
   *
   * @return the Array of Arrays of Pieces still placed on the board
   */
  def boardToArray(): Array[Array[PieceInterface]]

  /**
   * Returns a List of all possible fields a piece can move to
   *
   * @param pos the position of the Piece u want to ge the moves
   * @return all moves the Piece on the filed of pos can make
   */
  def getPossibleMoves(pos: (Int, Int)): List[(Int, Int)]

  /**
   * Moves a the Piece on the field currentPos to the destination.
   * If you are allowed to do so
   *
   * @param currentPos  the position on the board the Piece und want to
   *                    move is on
   * @param destination the position of the board u want to move the
   *                    piece to
   * @return if the move was valid, not valid or if the
   *         enemy king was slain
   */
  def movePiece(currentPos: (Int, Int), destination: (Int, Int)): MoveResult.Value

  /**
   * Returns a List of all possible fields a piece that is in
   * a players container can make
   *
   * @param piece the piece u want to know the moves of
   * @return all fields the piece can be placed
   */
  def getPossibleMovesConqueredPiece(piece: String): List[(Int, Int)]

  /**
   * Moves a the Piece in a container to the destination.
   * If you are allowed to do so.
   *
   * @param pieceAbbreviation the String representation of the Piece
   *                          you want to move
   * @param destination       the position of the board u want to place the
   *                          piece on
   * @return true if the move was valid or false if not
   */
  def moveConqueredPiece(pieceAbbreviation: String, destination: (Int, Int)): Boolean

  /**
   * Checks if an Piece is promotable
   *
   * @param position the position of the Piece you want to check
   *                 if a promotion is possible
   * @return true if the piece fulfills all conditions to be promoted
   *         otherwise return false
   */
  def promotable(position: (Int, Int)): Boolean

  /**
   * Promote the Piece on the given position
   *
   * @param piecePosition the position the piece is on
   * @return true if the piece has a promotion, false when not
   */
  def promotePiece(piecePosition: (Int, Int)): Boolean

  def startSimulation: Unit

  /**
   * returns the current state of the controller
   * @return the state of the controller
   */
  def getCurrentStat(): RoundState

  /**
   * changes the currentState to given State
   * @param newState is the state the controller should be
   */
  def setCurrentStat(newState: RoundState): Unit
}
