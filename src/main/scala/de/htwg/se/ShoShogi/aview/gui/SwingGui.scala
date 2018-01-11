package de.htwg.se.ShoShogi.aview.gui

import java.awt.Color
import javax.swing.{ ImageIcon, WindowConstants }

import scala.swing.GridBagPanel.Anchor
import de.htwg.se.ShoShogi.controller.{ Controller, StartNewGame, UpdateAll }
import de.htwg.se.ShoShogi.model.Piece

import scala.swing.Swing.LineBorder
import scala.swing._
import scala.swing.event.{ Key, MouseClicked }
// scalastyle:off magic.number

class SwingGui(controller: Controller) extends Frame {
  listenTo(controller)

  var boardPanel: GridPanel = new GridPanel(controller.boardSize, controller.boardSize) {}
  var containerPanel_1: BoxPanel = new BoxPanel(Orientation.Vertical) {}
  var containerPanel_2: BoxPanel = new BoxPanel(Orientation.Vertical) {}
  var highlightedPiece: (Int, Int) = (-1, -1)
  val boardColor: Color = getColorFromRGB(Array[Int](255, 222, 162))
  val pieceColor: Color = getColorFromRGB(Array[Int](249, 250, 242))
  val containerBorderColor: Color = getColorFromRGB(Array[Int](153, 51, 0))
  val containerBackgroundColor: Color = getColorFromRGB(Array[Int](246, 217, 157))
  val backgroundPath = "/home/mert/MEGAsync/HTWG/E_2017_2018_WS_HTWG/SE/ShoShogi_Repo/ShoShogi" +
    "/src/main/scala/de/htwg/se/ShoShogi/zresources/pieceImages/background.jpg"

  def getBoardArray: Array[Array[Piece]] = controller.boardToArray()

  def getColorFromRGB(xyz: Array[Int]): Color = {
    val ret: Array[Float] = Array.ofDim[Float](3)
    Color.RGBtoHSB(xyz(0), xyz(1), xyz(2), ret)
    Color.getHSBColor(ret(0), ret(1), ret(2))
  }

  object Panels extends Enumeration {
    type EnumType = Value
    val boardP, containerP_1, containerP_2, All = Value
  }

  title = "Shogi"
  maximize()

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

  initPanel(Panels.All)
  iconImage = new ImageIcon(backgroundPath).getImage

  contents = new GridBagPanel {
    def constraints(x: Int, y: Int,
      gridWidth: Int = 1, gridHeight: Int = 1,
      weightX: Double = 1.0, weightY: Double = 1.0,
      fill: GridBagPanel.Fill.Value = GridBagPanel.Fill.Both,
      anchor: Anchor.Value = Anchor.Center): Constraints = {
      val c = new Constraints
      c.gridx = x
      c.gridy = y
      c.gridwidth = gridWidth
      c.gridheight = gridHeight
      c.weightx = weightX
      c.weighty = weightY
      c.fill = fill
      c.anchor = anchor
      c
    }

    add(containerPanel_1, constraints(0, 0, gridHeight = 3, weightX = 0.0, anchor = Anchor.NorthEast))
    add(boardPanel, constraints(1, 0, gridHeight = 3))
    add(containerPanel_2, constraints(2, 0, gridHeight = 3, weightX = 0.0, anchor = Anchor.SouthWest))
    add(statisticsPanel, constraints(3, 0, gridHeight = 3))

    override def paintComponent(g: java.awt.Graphics2D) {
      g.drawImage(new ImageIcon(backgroundPath).getImage, 0, 0, null)
    }
  }

  visible = true
  peer.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE)

  def initPanel(panel: Panels.Value): Unit = {
    if (panel == Panels.boardP || panel == Panels.All) {
      boardPanel = new GridPanel(controller.boardSize, controller.boardSize) {
        border = LineBorder(java.awt.Color.BLACK, 0)
        background = boardColor

        val tempArray = getBoardArray

        for {
          row: Int <- 0 until controller.boardSize
          col: Int <- 0 until controller.boardSize
        } {
          contents += newPieceButton(tempArray(col)(row), (col, row))
        }
      }
      boardPanel.revalidate()
    }

    if (panel == Panels.containerP_1 || panel == Panels.All) {
      containerPanel_1 = new BoxPanel(Orientation.Vertical) {
        fillDataContainer1
        opaque = false
        revalidate()
      }
    }

    if (panel == Panels.containerP_2 || panel == Panels.All) {
      containerPanel_2 = new BoxPanel(Orientation.Vertical) {
        fillDataContainer2
        opaque = false
        revalidate()
      }
    }
  }

  def redrawPanel(panel: Panels.Value): Unit = {
    if (panel == Panels.boardP || panel == Panels.All) {
      boardPanel.contents.clear()
      val tempArray = getBoardArray

      for {
        row: Int <- 0 until controller.boardSize
        col: Int <- 0 until controller.boardSize
      } {
        boardPanel.contents += newPieceButton(tempArray(col)(row), (col, row))
      }
      boardPanel.revalidate()
    }

    if (panel == Panels.containerP_1 || panel == Panels.All) {
      containerPanel_1.contents.clear()
      fillDataContainer1
      containerPanel_1.revalidate()
    }

    if (panel == Panels.containerP_2 || panel == Panels.All) {
      containerPanel_2.contents.clear()
      fillDataContainer2
      containerPanel_2.revalidate()
    }
  }

  def fillDataContainer1(): Unit = {
    controller.getContainer._1.foreach(x => {
      containerPanel_1.xLayoutAlignment = 0.0
      containerPanel_1.yLayoutAlignment = 0.0
      containerPanel_1.contents += Swing.VStrut(5)
      containerPanel_1.contents += newPieceButton(x)
      containerPanel_1.border = Swing.LineBorder(containerBorderColor, 2)
      containerPanel_1.background = containerBackgroundColor
      containerPanel_1.opaque = true
    })
    if (controller.getContainer._1.size == 0) {
      containerPanel_1.opaque = false
      containerPanel_1.border = Swing.EmptyBorder(0, 0, 0, 0)
    }
  }

  def fillDataContainer2(): Unit = {
    controller.getContainer._2.foreach(x => {
      containerPanel_2.contents += newPieceButton(x)
      containerPanel_2.xLayoutAlignment = 0.0
      containerPanel_2.yLayoutAlignment = 1.0
      containerPanel_2.contents += Swing.VStrut(5)
      containerPanel_2.border = Swing.LineBorder(containerBorderColor, 2)
      containerPanel_2.background = containerBackgroundColor
      containerPanel_2.opaque = true
    })
    if (controller.getContainer._2.size == 0) {
      containerPanel_2.opaque = false
      containerPanel_2.border = Swing.EmptyBorder(0, 0, 0, 0)
    }
  }

  case class CustomButton(currentPiece: Piece, pos: (Int, Int) = (-1, -1)) extends Button

  def newPieceButton(piece: Piece, pos: (Int, Int) = (-1, -1)): Button = new CustomButton(piece, pos) {
    text = piece.toString

    if (piece.toString.trim.size > 0) {
      //      icon = new ImageIcon("de/htwg/se/ShoShogi/zresources/pieceImages/shogiExample.png")

      background = pieceColor
    } else {
      background = boardColor

    }

    listenTo(mouse.clicks)

    reactions += {
      case MouseClicked(src, pt, mod, clicks, pops) => {
        println("pos_1: " + pos._1)
        println("pos_2: " + pos._2)
        println(controller.possibleMoves(pos._1, pos._2))

        val pmv = controller.possibleMoves(pos._1, pos._2)

        if (pmv.size > 0) {
          highlightedPiece = (pos._1, pos._2)
        }

        if (background == Color.BLUE) {
          controller.movePiece(highlightedPiece, (pos._1, pos._2)) match {
            case controller.MoveResult.validMove => promoteQuery((pos._1, pos._2))
            case controller.MoveResult.kingSlain => {
              val res = Dialog.showConfirmation(
                contents.head,
                "You Won! Do you want to start a new game?",
                optionType = Dialog.Options.YesNo,
                title = "End"
              )

              if (res == Dialog.Result.Ok) {
                controller.createNewBoard()
              } else {
                System.exit(0)
              }
            }
          }
        }

        highlightCells(pmv)
      }
    }
  }

  private def promoteQuery(value: (Int, Int)): Unit = {
    if (controller.promotable(value._1, value._2)) {
      val res = Dialog.showConfirmation(
        contents.head,
        "Do you want to promote your piece?",
        optionType = Dialog.Options.YesNo,
        title = "Promotion"
      )

      if (res == Dialog.Result.Ok) {
        controller.promotePiece(value._1, value._2)
      }
    }
  }

  def statisticsPanel: GridPanel = new GridPanel(5, 1) {
    //    background = java.awt.Color.BLACK
    opaque = false
  }

  def highlightCells(cells: List[(Int, Int)]): Unit = {
    boardPanel.contents.foreach(x =>
      if (x.background == Color.BLUE) {
        x.background = boardColor
      })

    for (cell <- cells) {
      boardPanel.contents((cell._1 + (cell._2 * 9))).background = Color.BLUE
    }
    boardPanel.revalidate()

  }

  reactions += {
    case _: UpdateAll => {
      redrawPanel(Panels.All)
    }
    case _: StartNewGame => {
      redrawPanel(Panels.All)
    }
  }
}