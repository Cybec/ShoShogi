package de.htwg.se.ShoShogi.aview.gui

import java.awt.Color
import java.io.File
import javax.swing.{ ImageIcon, WindowConstants }

import scala.swing.event._
import scala.swing.GridBagPanel.Anchor
import de.htwg.se.ShoShogi.controller._
import de.htwg.se.ShoShogi.model.{ EmptyPiece, Piece }
import de.htwg.se.ShoShogi.controller.{ Controller, StartNewGame, UpdateAll }
import de.htwg.se.ShoShogi.model.{ EmptyPiece, Piece }

import scala.swing._
import scala.swing.event.{ Key, MouseClicked }
// scalastyle:off magic.number

class SwingGui(controller: ControllerInterface) extends Frame {
  listenTo(controller)

  var boardPanel: GridPanel = new GridPanel(controller.boardSize, controller.boardSize) {}
  var containerPanel_1: BoxPanel = new BoxPanel(Orientation.Vertical) {}
  var containerPanel_2: BoxPanel = new BoxPanel(Orientation.Vertical) {}
  var highlightedPiece: (Int, Int) = (-1, -1)
  var containerPiece: Piece = new EmptyPiece
  val boardColor: Color = getColorFromRGB(Array[Int](255, 235, 182))
  val pieceColor: Color = getColorFromRGB(Array[Int](249, 250, 242))
  val containerBorderColor: Color = getColorFromRGB(Array[Int](153, 51, 0))
  val containerBackgroundColor: Color = getColorFromRGB(Array[Int](246, 217, 157))
  val resourcesPath: String = new File(".").getCanonicalPath() + "/src/main/scala/de/htwg/se/ShoShogi/zresources"
  val backgroundPath = resourcesPath + "/images/background.jpg"

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
        val dialog = new ChangeNameDialog()
        val res = dialog.showDialog()

        if (res == Dialog.Result.Ok) {
          controller.createNewBoard()
          val p1Name: String = if (dialog.getNames()._1.size == 0) dialog.p1DefaultName else dialog.getNames()._1
          val p2Name: String = if (dialog.getNames()._2.size == 0) dialog.p2DefaultName else dialog.getNames()._2
          controller.changeNamePlayer1(p1Name)
          controller.changeNamePlayer1(p2Name)
        }

      })
      contents += new MenuItem(Action("Quit") {
        System.exit(0)
      })
    }
  }

  initPanel(Panels.All)
  iconImage = new ImageIcon(backgroundPath).getImage

  visible = true
  peer.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE)

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

  override def closeOperation() {
    Dialog.showConfirmation(
      parent = null,
      title = "Exit",
      message = "Are you sure you want to quit?"
    ) match {
      case Dialog.Result.Ok => System.exit(0)
      case _ => ()
    }
  }

  def initPanel(panel: Panels.Value, scale: String = "100x100"): Unit = {
    if (panel == Panels.boardP || panel == Panels.All) {
      boardPanel = new GridPanel(controller.boardSize, controller.boardSize) {
        background = boardColor
        fillDataBoard(scale)
        listenTo(this)
        reactions += {
          case UIElementResized(_) => {
            redrawPanel(Panels.boardP, if (this.size.width < 1000 || this.size.height < 800) "50x50" else "100x100")
          }
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

  def redrawPanel(panel: Panels.Value, scale: String = "100x100"): Unit = {
    if (panel == Panels.boardP || panel == Panels.All) {
      boardPanel.contents.clear()
      fillDataBoard(scale)
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

  def fillDataBoard(scale: String): Unit = {
    val tempArray = getBoardArray
    for {
      row: Int <- 0 until controller.boardSize
      col: Int <- 0 until controller.boardSize
    } {
      boardPanel.contents += newPieceButton(tempArray(col)(row), false, (col, row), scale)
    }
  }

  def fillDataContainer1(): Unit = {
    controller.getContainer._1.foreach(x => {
      containerPanel_1.contents += Swing.VStrut(5)
      containerPanel_1.contents += newPieceButton(x, true, scale = "50x50")
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
      containerPanel_2.contents += Swing.VStrut(5)
      containerPanel_2.contents += newPieceButton(x, true, scale = "50x50")
      containerPanel_2.border = Swing.LineBorder(containerBorderColor, 2)
      containerPanel_2.background = containerBackgroundColor
      containerPanel_2.opaque = true
    })
    if (controller.getContainer._2.size == 0) {
      containerPanel_2.opaque = false
      containerPanel_2.border = Swing.EmptyBorder(0, 0, 0, 0)
    }
  }

  def newPieceButton(piece: Piece, container: Boolean, pos: (Int, Int) = (-1, -1), scale: String): Button = {
    new PieceClickedReaction.CustomButton(piece, pos, container) {
      if (piece.toString.trim.size > 0) {
        val player = if (piece.isFirstOwner) "1" else "2"
        icon = new ImageIcon(resourcesPath + "/images/player" + player + "/" + scale + "/"
          + piece.toStringLong + "_" + scale + ".png")
        background = boardColor
      } else {
        background = boardColor
      }
      listenTo(mouse.clicks)

      reactions += {
        case MouseClicked(src, pt, mod, clicks, pops) => {
          PieceClickedReaction.movePiece(controller, pos) match {
            case MoveResult.validMove => promoteQuery(controller, pos)
            case MoveResult.kingSlain => showWonDialog
            case MoveResult.invalidMove =>
            case MoveResult.validMoveContainer =>
          }
          highlightCells(PieceClickedReaction.getMoves(this, controller))
        }
      }
    }
  }

  private def showWonDialog = {
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

  private def promoteQuery(controller: ControllerInterface, desPos: (Int, Int)): Unit = {
    if (controller.promotable(desPos._1, desPos._2)) {
      val res = Dialog.showConfirmation(
        contents.head,
        "Do you want to promote your piece?",
        optionType = Dialog.Options.YesNo,
        title = "Promotion"
      )

      if (res == Dialog.Result.Ok) {
        controller.promotePiece(desPos._1, desPos._2)
      }
    }
  }

  def statisticsPanel: GridPanel = new GridPanel(5, 1) {
    //    background = java.awt.Color.BLACK
    opaque = false
  }

  def highlightCells(cells: List[(Int, Int)]): Unit = {
    redrawPanel(Panels.All)

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