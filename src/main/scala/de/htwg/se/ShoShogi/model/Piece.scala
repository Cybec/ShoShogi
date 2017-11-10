package de.htwg.se.ShoShogi.model

abstract class Piece(name: String, player: Player) {

  def promotePiece: Option[Piece]

  var hasPromotion: Boolean

  override def toString: String

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

  override def toString: String = "K "

  /*Author:   Mert, Nick
  * Role:     Gibt die moeglichen Bewegungsfelder zurueck
  * Return:   List[(Int, Int)] mit den Koordinaten */
  def getMoveSet(pos: (Int, Int)): List[(Int, Int)] = {
    //Jede Richtung ein Feld
    List((pos._1, pos._2 + 1), (pos._1 - 1, pos._2 + 1), (pos._1 - 1, pos._2),
      (pos._1 - 1, pos._2 - 1), (pos._1, pos._2 - 1), (pos._1 - 1, pos._2 + 1),
      (pos._1 + 1, pos._2), (pos._1 + 1, pos._2 + 1))
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

  override def toString: String = "GG"

  /*Author:   Mert, Nick
  * Role:     Gibt die moeglichen Bewegungsfelder zurueck
  * Return:   List[(Int, Int)] mit den Koordinaten */
  def getMoveSet(pos: (Int, Int)): List[(Int, Int)] = {
    List((pos._1, pos._2 + 1), (pos._1 - 1, pos._2 + 1), (pos._1 - 1, pos._2),
      (pos._1, pos._2 - 1), (pos._1 + 1, pos._2), (pos._1 + 1, pos._2 + 1))
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
    Some(PromotedSilver(player))
  }

  override def toString: String = "SG"

  /*Author:   Mert, Nick
  * Role:     Gibt die moeglichen Bewegungsfelder zurueck
  * Return:   List[(Int, Int)] mit den Koordinaten */
  def getMoveSet(pos: (Int, Int)): List[(Int, Int)] = {
    List((pos._1, pos._2 + 1), (pos._1 - 1, pos._2 + 1), (pos._1 - 1, pos._2 - 1),
      (pos._1 - 1, pos._2 + 1), (pos._1 + 1, pos._2 + 1))
  }
}

case class PromotedSilver(player: Player)
    extends Piece("PromotedSilver", player: Player) {
  var hasPromotion: Boolean = false

  def promotePiece: Option[Piece] = {
    None
  }

  override def toString: String = "PS"

  def getMoveSet(pos: (Int, Int)): List[(Int, Int)] = {
    List((pos._1, pos._2 + 1), (pos._1 - 1, pos._2 + 1), (pos._1 - 1, pos._2),
      (pos._1, pos._2 - 1), (pos._1 + 1, pos._2), (pos._1 + 1, pos._2 + 1))
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
    Some(PromotedKnight(player))
  }

  override def toString: String = "KN"

  /*Author:   Mert, Nick
  * Role:     Gibt die moeglichen Bewegungsfelder zurueck
  * Return:   List[(Int, Int)] mit den Koordinaten */
  def getMoveSet(pos: (Int, Int)): List[(Int, Int)] = {
    List((pos._1 - 1, pos._2 + 2), (pos._1 + 1, pos._2 + 2))
  }
}

case class PromotedKnight(player: Player)
    extends Piece("PromotedKnight", player: Player) {
  var hasPromotion: Boolean = false

  def promotePiece: Option[Piece] = {
    None
  }

  override def toString: String = "PK"

  def getMoveSet(pos: (Int, Int)): List[(Int, Int)] = {
    List((pos._1, pos._2 + 1), (pos._1 - 1, pos._2 + 1), (pos._1 - 1, pos._2),
      (pos._1, pos._2 - 1), (pos._1 + 1, pos._2), (pos._1 + 1, pos._2 + 1))
  }
}

//endregion

//region Lancer

/*Author:   Mert, Nick
* Role:     Erstellt eine Lancer-Figur */
case class Lancer(player: Player)
    extends Piece("Lancer", player: Player) {
  var hasPromotion: Boolean = true

  /*Author:   Mert, Nick
  * Role:     Stuft das Piece auf
  * Return:   Gibt das Promotete Piece zurueck*/
  def promotePiece: Option[Piece] = {
    Some(PromotedLancer(player))
  }

  override def toString: String = "L "

  /*Author:   Mert, Nick
  * Role:     Gibt die moeglichen Bewegungsfelder zurueck
  * Return:   List[(Int, Int)] mit den Koordinaten */
  def getMoveSet(pos: (Int, Int)): List[(Int, Int)] = {
    List((pos._1, pos._2 + 1), (pos._1, pos._2 + 2), (pos._1, pos._2 + 3),
      (pos._1, pos._2 + 4), (pos._1, pos._2 + 5), (pos._1, pos._2 + 6),
      (pos._1, pos._2 + 7), (pos._1, pos._2 + 8))
  }
}

case class PromotedLancer(player: Player)
    extends Piece("PromotedLancer", player: Player) {
  var hasPromotion: Boolean = false

  def promotePiece: Option[Piece] = {
    None
  }

  override def toString: String = "PL"

  def getMoveSet(pos: (Int, Int)): List[(Int, Int)] = {
    List((pos._1, pos._2 + 1), (pos._1 - 1, pos._2 + 1), (pos._1 - 1, pos._2),
      (pos._1, pos._2 - 1), (pos._1 + 1, pos._2), (pos._1 + 1, pos._2 + 1))
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
    Some(PromotedBishop(player))
  }

  override def toString: String = "B "

  /*Author:   Mert, Nick
  * Role:     Gibt die moeglichen Bewegungsfelder zurueck
  * Return:   List[(Int, Int)] mit den Koordinaten */
  def getMoveSet(pos: (Int, Int)): List[(Int, Int)] = {
    var moves: List[(Int, Int)] = List()
    val maxMoveRange: Int = 9
    for (i <- 1 to maxMoveRange) {
      moves :::= List((pos._1 - i, pos._2 + i))
      moves :::= List((pos._1 + i, pos._2 + i))
      moves :::= List((pos._1 - i, pos._2 - i))
      moves :::= List((pos._1 + i, pos._2 - i))
    }
    moves
  }
}

case class PromotedBishop(player: Player)
    extends Piece("PromotedBishop", player: Player) {
  var hasPromotion: Boolean = false

  def promotePiece: Option[Piece] = {
    None
  }

  override def toString: String = "PB"

  def getMoveSet(pos: (Int, Int)): List[(Int, Int)] = {
    var moves: List[(Int, Int)] = List()
    val maxMoveRange: Int = 9
    for (i <- 1 to maxMoveRange) {
      moves :::= List((pos._1 - i, pos._2 + i))
      moves :::= List((pos._1 + i, pos._2 + i))
      moves :::= List((pos._1 - i, pos._2 - i))
      moves :::= List((pos._1 + i, pos._2 - i))
    }
    moves :::= List((pos._1, pos._2 + 1), (pos._1 - 1, pos._2), (pos._1, pos._2 - 1), (pos._1 + 1, pos._2))
    moves
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
    Some(PromotedRook(player))
  }

  override def toString: String = "R "

  /*Author:   Mert, Nick
  * Role:     Gibt die moeglichen Bewegungsfelder zurueck
  * Return:   List[(Int, Int)] mit den Koordinaten */
  def getMoveSet(pos: (Int, Int)): List[(Int, Int)] = {
    var moves: List[(Int, Int)] = List()
    val maxMoveRange: Int = 9
    for (i <- 1 to maxMoveRange) {
      moves :::= List((pos._1, pos._2 + i))
      moves :::= List((pos._1 - i, pos._2))
      moves :::= List((pos._1, pos._2 - i))
      moves :::= List((pos._1 + i, pos._2))
    }
    moves
  }
}

case class PromotedRook(player: Player)
    extends Piece("PromotedRook", player: Player) {
  var hasPromotion: Boolean = false

  def promotePiece: Option[Piece] = {
    None
  }

  override def toString: String = "PR"

  def getMoveSet(pos: (Int, Int)): List[(Int, Int)] = {
    var moves: List[(Int, Int)] = List()
    val maxMoveRange: Int = 9
    for (i <- 1 to maxMoveRange) {
      moves :::= List((pos._1, pos._2 + i))
      moves :::= List((pos._1 - i, pos._2))
      moves :::= List((pos._1, pos._2 - i))
      moves :::= List((pos._1 + i, pos._2))
    }
    moves :::= List((pos._1 - 1, pos._2 + 1), (pos._1 - 1, pos._2 + 1), (pos._1 + 1, pos._2 - 1), (pos._1 + 1, pos._2 + 1))
    moves
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
    Some(PromotedPawn(player))
  }

  override def toString: String = "P "

  /*Author:   Mert, Nick
  * Role:     Gibt die moeglichen Bewegungsfelder zurueck
  * Return:   List[(Int, Int)] mit den Koordinaten */
  def getMoveSet(pos: (Int, Int)): List[(Int, Int)] = {
    List((pos._1, pos._2 + 1))
  }
}

case class PromotedPawn(player: Player)
    extends Piece("PromotedPawn", player: Player) {
  var hasPromotion: Boolean = false

  def promotePiece: Option[Piece] = {
    None
  }

  override def toString: String = "PP"

  def getMoveSet(pos: (Int, Int)): List[(Int, Int)] = {
    List((pos._1, pos._2 + 1), (pos._1 - 1, pos._2 + 1), (pos._1 - 1, pos._2),
      (pos._1, pos._2 - 1), (pos._1 + 1, pos._2), (pos._1 + 1, pos._2 + 1))
  }
}

//endregion

//region EmptyPiece
case class EmptyPiece()
    extends Piece("", new Player("", false)) {
  var hasPromotion: Boolean = false

  /*Author:   Mert, Nick
  * Role:     Stuft das Piece auf
  * Return:   Gibt das Promotete Piece zurueck*/
  def promotePiece: Option[Piece] = {
    None
  }

  override def toString: String = "  "

  /*Author:   Mert, Nick
  * Role:     Gibt die moeglichen Bewegungsfelder zurueck
  * Return:   List[(Int, Int)] mit den Koordinaten */
  def getMoveSet(pos: (Int, Int)): List[(Int, Int)] = {
    List()
  }
}

//endregion