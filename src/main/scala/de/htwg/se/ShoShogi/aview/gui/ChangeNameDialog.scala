package de.htwg.se.ShoShogi.aview.gui

import scala.swing.Dialog
import javax.swing.JPasswordField
import javax.swing.JTextField
import javax.swing.JComponent
import javax.swing.JLabel
import javax.swing.JOptionPane

class ChangeNameDialog() extends Dialog {
  val p1DefaultName = "Player 1"
  val p2DefaultName = "Player 2"

  val player1NameTextBox = new JTextField
  val player2NameTextBox = new JTextField
  val password = new JPasswordField

  val inputs: Array[JComponent] = Array[JComponent](
    new JLabel(p1DefaultName),
    player1NameTextBox,
    new JLabel(p2DefaultName),
    player2NameTextBox
  )

  def getNames(): (String, String) = (player1NameTextBox.getText(), player2NameTextBox.getText())

  def showDialog(): Dialog.Result.Value = {
    val result = JOptionPane.showConfirmDialog(null, inputs, "New Game", JOptionPane.OK_CANCEL_OPTION)
    if (result == 0) Dialog.Result.Ok else Dialog.Result.Cancel
  }
}
