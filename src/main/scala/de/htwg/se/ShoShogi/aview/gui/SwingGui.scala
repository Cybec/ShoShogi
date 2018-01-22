package de.htwg.se.ShoShogi.aview.gui

import java.io.File
import javax.swing.{Icon, ImageIcon, WindowConstants}

import de.htwg.se.ShoShogi.controller.controllerComponent.controllerBaseImpl.{StartNewGame, UpdateAll}
import de.htwg.se.ShoShogi.controller.controllerComponent.{ControllerInterface, MoveResult}
import de.htwg.se.ShoShogi.model.pieceComponent.PieceInterface
import de.htwg.se.ShoShogi.model.pieceComponent.pieceBaseImpl.PieceFactory

import scala.swing.GridBagPanel.Anchor
import scala.swing._
import scala.swing.event.{Key, MouseClicked, _}

class SwingGui(controller: ControllerInterface) extends Frame {
  listenTo(controller)

  var boardPanel: GridPanel = new GridPanel(controller.boardSize, controller.boardSize) {}
  var containerPanel_1: BoxPanel = new BoxPanel(Orientation.Vertical) {}
  var containerPanel_2: BoxPanel = new BoxPanel(Orientation.Vertical) {}
  var highlightedPiece: (Int, Int) = (-1, -1)
  var containerPiece: PieceInterface = PieceFactory.getEmptyPiece
  //noinspection ScalaStyle
  val boardColor: java.awt.Color = getColorFromRGB(255, 235, 182)
  //noinspection ScalaStyle
  val pieceColor: java.awt.Color = getColorFromRGB(249, 250, 242)
  val markedColor: java.awt.Color = java.awt.Color.BLUE
  //noinspection ScalaStyle
  val containerBorderColor: Color = getColorFromRGB(153, 51, 0)
  //noinspection ScalaStyle
  val containerBackgroundColor: Color = getColorFromRGB(246, 217, 157)
  val resourcesPath: String = new File(".").getCanonicalPath + "/src/main/scala/de/htwg/se/ShoShogi/zresources"
  val backgroundPath: String = resourcesPath + "/images/background.jpg"

  def getBoardArray: Array[Array[PieceInterface]] = controller.boardToArray()

  val width = 500

  object Panels extends Enumeration {
    type EnumType = Value
    val boardP, containerP_1, containerP_2, All = Value
  }

  title = "Shogi"
  val height = 500

  def getColorFromRGB(r: Int, g: Int, b: Int): Color = {
    val ret: Array[Float] = Array.ofDim[Float](3)
    java.awt.Color.RGBtoHSB(r, g, b, ret)
    java.awt.Color.getHSBColor(ret(0), ret(1), ret(2))
  }

  minimumSize = new Dimension(width, height)
  maximize()

  menuBar = new MenuBar {
    contents += new Menu("Game") {
      mnemonic = Key.G
      contents += new MenuItem(Action("New") {
        val dialog = new ChangeNameDialog()
        val res = dialog.showDialog()

        if (res == Dialog.Result.Ok) {
          controller.createNewBoard()
          val p1Name: String = if (dialog.getNames._1.isEmpty) dialog.p1DefaultName else dialog.getNames._1
          val p2Name: String = if (dialog.getNames._2.isEmpty) dialog.p2DefaultName else dialog.getNames._2
          controller.changeNamePlayer1(p1Name)
          controller.changeNamePlayer2(p2Name)
        }
      })
      contents += new Separator
      contents += new MenuItem(Action("Save") {
        controller.save()
      })
      contents += new MenuItem(Action("Load") {
        controller.load()
      })
      contents += new Separator
      contents += new MenuItem(Action("Quit") {
        closeOperation()
      })
    }
    contents += new Menu("Edit") {
      mnemonic = Key.E
      contents += new MenuItem(Action("undo") {
        controller.undoCommand()
      })
      contents += new MenuItem(Action("redo") {
        controller.redoCommand()
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

    override def paintComponent(g: java.awt.Graphics2D) {
      //noinspection ScalaStyle
      g.drawImage(new ImageIcon(backgroundPath).getImage, 0, 0, null)
    }
  }

  override def closeOperation() {
    //noinspection ScalaStyle
    Dialog.showConfirmation(
      parent = null,
      title = "Exit",
      message = "Are you sure you want to quit?"
    ) match {
      case Dialog.Result.Ok => System.exit(0)
      case _ => ()
    }
  }

  def initPanel(panel: Panels.Value): Unit = {
    if (panel == Panels.boardP || panel == Panels.All) {
      boardPanel = new GridPanel(controller.boardSize, controller.boardSize) {
        background = boardColor
        fillDataBoard()
        listenTo(this)
        reactions += {
          case UIElementResized(_) =>
            redrawPanel(Panels.boardP)
        }
      }
      boardPanel.revalidate()
    }
    if (panel == Panels.containerP_1 || panel == Panels.All) {
      containerPanel_1 = new BoxPanel(Orientation.Vertical) {
        fillDataContainer1()
        opaque = false
        revalidate()
      }
    }
    if (panel == Panels.containerP_2 || panel == Panels.All) {
      containerPanel_2 = new BoxPanel(Orientation.Vertical) {
        fillDataContainer2()
        opaque = false
        revalidate()
      }
    }
  }

  def redrawPanel(panel: Panels.Value): Unit = {
    if (panel == Panels.boardP || panel == Panels.All) {
      boardPanel.contents.clear()
      fillDataBoard()
      boardPanel.revalidate()
    }

    if (panel == Panels.containerP_1 || panel == Panels.All) {
      containerPanel_1.contents.clear()
      fillDataContainer1()
      containerPanel_1.revalidate()
    }

    if (panel == Panels.containerP_2 || panel == Panels.All) {
      containerPanel_2.contents.clear()
      fillDataContainer2()
      containerPanel_2.revalidate()
    }
  }

  def fillDataBoard(): Unit = {
    val tempArray = getBoardArray
    for {
      row: Int <- 0 until controller.boardSize
      col: Int <- 0 until controller.boardSize
    } {
      boardPanel.contents += newPieceButton(
        tempArray(col)(row),
        container = false,
        (col, row),
        if (boardPanel.size.width < 1000 || boardPanel.size.height < 800) "50x50" else "100x100"
      )
    }
  }

  def fillDataContainer1(): Unit = {
    val distanceTop = 5
    controller.getContainer._1.foreach(x => {
      containerPanel_1.contents += Swing.VStrut(distanceTop)
      containerPanel_1.contents += newPieceButton(x, container = true, scale = "50x50")
      containerPanel_1.border = Swing.LineBorder(containerBorderColor, 2)
      containerPanel_1.background = containerBackgroundColor
      containerPanel_1.opaque = true
    })
    if (controller.getContainer._1.isEmpty) {
      containerPanel_1.opaque = false
      containerPanel_1.border = Swing.EmptyBorder(0, 0, 0, 0)
    }
  }

  def fillDataContainer2(): Unit = {
    val distanceTop = 5
    controller.getContainer._2.foreach(x => {
      containerPanel_2.contents += Swing.VStrut(distanceTop)
      containerPanel_2.contents += newPieceButton(x, container = true, scale = "50x50")
      containerPanel_2.border = Swing.LineBorder(containerBorderColor, 2)
      containerPanel_2.background = containerBackgroundColor
      containerPanel_2.opaque = true
    })
    if (controller.getContainer._2.isEmpty) {
      containerPanel_2.opaque = false
      containerPanel_2.border = Swing.EmptyBorder(0, 0, 0, 0)
    }
  }

  def newPieceButton(piece: PieceInterface, container: Boolean, pos: (Int, Int) = (-1, -1), scale: String): Button = {
    new PieceClickedReaction.CustomButton(piece, pos, container) {
      icon = getPieceIcon(piece, scale)
      background = boardColor
      listenTo(mouse.clicks)

      reactions += {
        case MouseClicked(_, _, _, _, _) =>
          if (this.background == markedColor) {
            PieceClickedReaction.movePiece(controller, pos) match {
              case MoveResult.validMove => promoteQuery(controller, pos)
              case MoveResult.kingSlain => showWonDialog()
              case MoveResult.invalidMove =>
              case MoveResult.validMoveContainer =>
            }
          }
          highlightCells(PieceClickedReaction.getPossibleMoves(this, controller))
      }
    }
  }

  def getPieceIcon(piece: PieceInterface, scale: String): Icon = {
    if (piece.toString.trim.nonEmpty) {
      val player = if (piece.isFirstOwner) "1" else "2"
      new ImageIcon(resourcesPath + "/images/player" +
        player + "/" + scale + "/" + piece.toStringLong + "_" + scale + ".png")
    } else {
      new ImageIcon()
    }
  }

  def highlightCells(cells: List[(Int, Int)]): Unit = {
    redrawPanel(Panels.All)

    for (cell <- cells) {
      boardPanel.contents(cell._1 + (cell._2 * 9)).background = markedColor
    }
    boardPanel.revalidate()
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

  private def showWonDialog(): Unit = {
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

  reactions += {
    case _: UpdateAll =>
      redrawPanel(Panels.All)
    case _: StartNewGame =>
      redrawPanel(Panels.All)
  }
}