package de.htwg.se.ShoShogi.model

abstract class Piece(name: String, player: Player) {

  def promotePiece: Option[Piece]

  var hasPromotion: Boolean

  var token: String

  def getMoveSet(pos: (Int, Int)): List[(Int, Int)]
}

//region King

/*Author:   Mert, Nick
* Role:     Erstellt eine King-Figur */
case class King(player: Player)
  extends Piece("King", player: Player) {
  var hasPromotion: Boolean = false

  /*Author:   Mert, Nick
  * Role:     Stuft das Piece auf
  * Return:   Gibt das Promotete Piece zurueck*/
  def promotePiece: Option[Piece] = {
    None
  }

  var token: String = "K"

  /*Author:   Mert, Nick
  * Role:     Gibt die moeglichen Bewegungsfelder zurueck
  * Return:   List[(Int, Int)] mit den Koordinaten */
  def getMoveSet(pos: (Int, Int)): List[(Int, Int)] = {
    var a: List[(Int, Int)] = a :+ ((9999, 9999))
    a
  }
}

//endregion

//region GoldenGeneral

/*Author:   Mert, Nick
* Role:     Erstellt eine GoldenGeneral-Figur */
case class GoldenGeneral(player: Player)
  extends Piece("GoldenGeneral", player: Player) {
  var hasPromotion: Boolean = false

  /*Author:   Mert, Nick
  * Role:     Stuft das Piece auf
  * Return:   Gibt das Promotete Piece zurueck*/
  def promotePiece: Option[Piece] = {
    None
  }

  var token: String = "GG"

  /*Author:   Mert, Nick
  * Role:     Gibt die moeglichen Bewegungsfelder zurueck
  * Return:   List[(Int, Int)] mit den Koordinaten */
  def getMoveSet(pos: (Int, Int)): List[(Int, Int)] = {
    var a: List[(Int, Int)] = a :+ ((9999, 9999))
    a
  }
}

//endregion

//region SilverGeneral

/*Author:   Mert, Nick
* Role:     Erstellt eine SilverGeneral-Figur */
case class SilverGeneral(player: Player)
  extends Piece("SilverGeneral", player: Player) {
  var hasPromotion: Boolean = true

  /*Author:   Mert, Nick
  * Role:     Stuft das Piece auf
  * Return:   Gibt das Promotete Piece zurueck*/
  def promotePiece: Option[Piece] = {
    Some(new PromotedSilver(player))
  }

  var token: String = "SG"

  /*Author:   Mert, Nick
  * Role:     Gibt die moeglichen Bewegungsfelder zurueck
  * Return:   List[(Int, Int)] mit den Koordinaten */
  def getMoveSet(pos: (Int, Int)): List[(Int, Int)] = {
    var a: List[(Int, Int)] = a :+ ((9999, 9999))
    a
  }
}


case class PromotedSilver(player: Player)
  extends Piece("PromotedSilver", player: Player) {
  var hasPromotion: Boolean = false

  def promotePiece: Option[Piece] = {
    None
  }

  var token: String = "PS"

  def getMoveSet(pos: (Int, Int)): List[(Int, Int)] = {
    var a: List[(Int, Int)] = a :+ ((9999, 9999))
    a
  }
}

//endregion

//region Knight

/*Author:   Mert, Nick
* Role:     Erstellt eine Knight-Figur */
case class Knight(player: Player)
  extends Piece("Knight", player: Player) {
  var hasPromotion: Boolean = false

  /*Author:   Mert, Nick
  * Role:     Stuft das Piece auf
  * Return:   Gibt das Promotete Piece zurueck*/
  def promotePiece: Option[Piece] = {
    Some(new PromotedKnight(player))
  }

  var token: String = "KN"

  /*Author:   Mert, Nick
  * Role:     Gibt die moeglichen Bewegungsfelder zurueck
  * Return:   List[(Int, Int)] mit den Koordinaten */
  def getMoveSet(pos: (Int, Int)): List[(Int, Int)] = {
    var a: List[(Int, Int)] = a :+ ((9999, 9999))
    a
  }
}


case class PromotedKnight(player: Player)
  extends Piece("PromotedKnight", player: Player) {
  var hasPromotion: Boolean = false

  def promotePiece: Option[Piece] = {
    None
  }

  var token: String = "PK"

  def getMoveSet(pos: (Int, Int)): List[(Int, Int)] = {
    var a: List[(Int, Int)] = a :+ ((9999, 9999))
    a
  }
}

//endregion