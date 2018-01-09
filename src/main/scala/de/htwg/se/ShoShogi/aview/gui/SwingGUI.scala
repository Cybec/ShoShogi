package de.htwg.se.ShoShogi.aview.gui

import javax.swing.WindowConstants

import de.htwg.se.ShoShogi.controller.{ Controller, UpdateAll }
import de.htwg.se.ShoShogi.model.{ EmptyPiece, Piece }

import scala.swing.Swing.LineBorder
import scala.swing._
import scala.swing.event.Key
// scalastyle:off magic.number

class SwingGui(controller: Controller) extends Frame {

  var viewedBoardSize: Dimension = new Dimension(
    (java.awt.Toolkit.getDefaultToolkit.getScreenSize.width * (2.0 / 3.0)).toInt,
    java.awt.Toolkit.getDefaultToolkit.getScreenSize.height
  )

  title = "Shogi"
  size = java.awt.Toolkit.getDefaultToolkit.getScreenSize
  listenTo(controller)

  menuBar = new MenuBar {
    contents += new Menu("Game") {
      mnemonic = Key.N
      contents += new MenuItem(Action("New") {
        controller.createNewBoard()
      })
      contents += new MenuItem(Action("Quit") {
        System.exit(0)
      })
    }
  }

  contents = new BorderPanel {
    add(mainPanel, BorderPanel.Position.West)
    add(statisticsPanel, BorderPanel.Position.East)
  }

  visible = true
  peer.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE)

  reactions += {
    case _: UpdateAll => {
      contents = new BorderPanel {
        add(mainPanel, BorderPanel.Position.West)
        add(statisticsPanel, BorderPanel.Position.East)
      }

      repaint
    }
  }

  def pieceButton(piece: Piece): Button = new Button {
    text = piece.toString
    preferredSize_=(new Dimension(30, 30))
  }

  def container(list: List[Piece]): FlowPanel = new FlowPanel {
    list.foreach(x => contents += pieceButton(x))
    preferredSize_=(new Dimension((viewedBoardSize.width * (1.0 / 4.0)).toInt, viewedBoardSize.height))
    background = java.awt.Color.BLACK
  }

  def boardPanel: GridPanel = new GridPanel(controller.boardSize, controller.boardSize) {
    border = LineBorder(java.awt.Color.BLACK, 2)
    controller.boardToList().foreach(piece => contents += pieceButton(piece))
  }

  def mainPanel: BorderPanel = new BorderPanel {
    add(container(controller.container._1), BorderPanel.Position.West)
    add(boardPanel, BorderPanel.Position.Center)
    add(container(controller.container._2), BorderPanel.Position.East)
    preferredSize = new Dimension(viewedBoardSize)
  }

  def statisticsPanel: GridPanel = new GridPanel(5, 1) {
    preferredSize = new Dimension(
      (viewedBoardSize.width * (1.0 / 3.0)).toInt,
      viewedBoardSize.height
    )
  }
}