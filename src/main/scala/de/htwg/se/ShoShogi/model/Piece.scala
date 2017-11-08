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
  var hasPromotion: Boolean = true

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

//region Lance

/*Author:   Mert, Nick
* Role:     Erstellt eine Lance-Figur */
case class Lance(player: Player)
  extends Piece("Lance", player: Player) {
  var hasPromotion: Boolean = true

  /*Author:   Mert, Nick
  * Role:     Stuft das Piece auf
  * Return:   Gibt das Promotete Piece zurueck*/
  def promotePiece: Option[Piece] = {
    Some(new PromotedLance(player))
  }

  var token: String = "L"

  /*Author:   Mert, Nick
  * Role:     Gibt die moeglichen Bewegungsfelder zurueck
  * Return:   List[(Int, Int)] mit den Koordinaten */
  def getMoveSet(pos: (Int, Int)): List[(Int, Int)] = {
    var a: List[(Int, Int)] = a :+ ((9999, 9999))
    a
  }
}


case class PromotedLance(player: Player)
  extends Piece("PromotedLance", player: Player) {
  var hasPromotion: Boolean = false

  def promotePiece: Option[Piece] = {
    None
  }

  var token: String = "PL"

  def getMoveSet(pos: (Int, Int)): List[(Int, Int)] = {
    var a: List[(Int, Int)] = a :+ ((9999, 9999))
    a
  }
}

//endregion

//region Bishop

/*Author:   Mert, Nick
* Role:     Erstellt eine Bishop-Figur */
case class Bishop(player: Player)
  extends Piece("Bishop", player: Player) {
  var hasPromotion: Boolean = true

  /*Author:   Mert, Nick
  * Role:     Stuft das Piece auf
  * Return:   Gibt das Promotete Piece zurueck*/
  def promotePiece: Option[Piece] = {
    Some(new PromotedBishop(player))
  }

  var token: String = "B"

  /*Author:   Mert, Nick
  * Role:     Gibt die moeglichen Bewegungsfelder zurueck
  * Return:   List[(Int, Int)] mit den Koordinaten */
  def getMoveSet(pos: (Int, Int)): List[(Int, Int)] = {
    var a: List[(Int, Int)] = a :+ ((9999, 9999))
    a
  }
}


case class PromotedBishop(player: Player)
  extends Piece("PromotedBishop", player: Player) {
  var hasPromotion: Boolean = false

  def promotePiece: Option[Piece] = {
    None
  }

  var token: String = "PB"

  def getMoveSet(pos: (Int, Int)): List[(Int, Int)] = {
    var a: List[(Int, Int)] = a :+ ((9999, 9999))
    a
  }
}

//endregion

//region Rook

/*Author:   Mert, Nick
* Role:     Erstellt eine Rook-Figur */
case class Rook(player: Player)
  extends Piece("Rook", player: Player) {
  var hasPromotion: Boolean = true

  /*Author:   Mert, Nick
  * Role:     Stuft das Piece auf
  * Return:   Gibt das Promotete Piece zurueck*/
  def promotePiece: Option[Piece] = {
    Some(new PromotedRook(player))
  }

  var token: String = "R"

  /*Author:   Mert, Nick
  * Role:     Gibt die moeglichen Bewegungsfelder zurueck
  * Return:   List[(Int, Int)] mit den Koordinaten */
  def getMoveSet(pos: (Int, Int)): List[(Int, Int)] = {
    var a: List[(Int, Int)] = a :+ ((9999, 9999))
    a
  }
}


case class PromotedRook(player: Player)
  extends Piece("PromotedRook", player: Player) {
  var hasPromotion: Boolean = false

  def promotePiece: Option[Piece] = {
    None
  }

  var token: String = "PR"

  def getMoveSet(pos: (Int, Int)): List[(Int, Int)] = {
    var a: List[(Int, Int)] = a :+ ((9999, 9999))
    a
  }
}

//endregion

//region Pawn

/*Author:   Mert, Nick
* Role:     Erstellt eine Pawn-Figur */
case class Pawn(player: Player)
  extends Piece("Pawn", player: Player) {
  var hasPromotion: Boolean = true

  /*Author:   Mert, Nick
  * Role:     Stuft das Piece auf
  * Return:   Gibt das Promotete Piece zurueck*/
  def promotePiece: Option[Piece] = {
    Some(new PromotedPawn(player))
  }

  var token: String = "P"

  /*Author:   Mert, Nick
  * Role:     Gibt die moeglichen Bewegungsfelder zurueck
  * Return:   List[(Int, Int)] mit den Koordinaten */
  def getMoveSet(pos: (Int, Int)): List[(Int, Int)] = {
    var a: List[(Int, Int)] = a :+ ((9999, 9999))
    a
  }
}


case class PromotedPawn(player: Player)
  extends Piece("PromotedPawn", player: Player) {
  var hasPromotion: Boolean = false

  def promotePiece: Option[Piece] = {
    None
  }

  var token: String = "PP"

  def getMoveSet(pos: (Int, Int)): List[(Int, Int)] = {
    var a: List[(Int, Int)] = a :+ ((9999, 9999))
    a
  }
}
//endregion