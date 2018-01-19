package de.htwg.se.ShoShogi.model.pieceComponent

import de.htwg.se.ShoShogi.model.boardComponent.BoardInterface
import de.htwg.se.ShoShogi.model.playerComponent.Player

abstract class Piece(name: String, val player: Player) {

  def promotePiece: Option[Piece]

  def isFirstOwner: Boolean = player.first

  val hasPromotion: Boolean

  override def toString: String

  def toStringLong: String

  def getMoveSet(pos: (Int, Int), board: BoardInterface): List[(Int, Int)]

  def rekMoveSet(board: BoardInterface, newPos: (Int, Int), rek: Int, value: (Int, Int)): List[(Int, Int)] = {
    var l = List[(Int, Int)]()

    if (rek != 0) {
      board.cell(newPos._1, newPos._2).foreach(i => if (i.player.name != this.player.name) {
        l = List[(Int, Int)]((newPos._1, newPos._2))
        if (i.isInstanceOf[EmptyPiece]) {
          l = l ::: rekMoveSet(board, ((newPos._1 + value._1), (newPos._2 + value._2)), rek - 1, value)
        }
      })
      l
    } else {
      List[(Int, Int)]()
    }
  }

  def cloneToNewPlayer(player: Player): Piece

  def typeEquals(piecesAbbreviation: String): Boolean = this.toString().trim == piecesAbbreviation.trim
}

object pieceFactory {
  def apply(pieceType: String, player: Player): Piece = pieceType match {
    case "King" => new King(player)
    case "GoldenGeneral" => new GoldenGeneral(player)
    case "SilverGeneral" => new SilverGeneral(player)
    case "PromotedSilver" => new PromotedSilver(player)
    case "Knight" => new Knight(player)
    case "PromotedKnight" => new PromotedKnight(player)
    case "Lancer" => new Lancer(player)
    case "PromotedLancer" => new PromotedLancer(player)
    case "Bishop" => new Bishop(player)
    case "PromotedBishop" => new PromotedBishop(player)
    case "Rook" => new Rook(player)
    case "PromotedRook" => new PromotedRook(player)
    case "Pawn" => new Pawn(player)
    case "PromotedPawn" => new PromotedPawn(player)
    case "EmptyPiece" => new EmptyPiece
  }
}

//region King

/*Author:   Mert, Nick
* Role:     Erstellt eine King-Figur */
case class King(override val player: Player)
    extends Piece("King", player: Player) {
  val hasPromotion: Boolean = false

  /*Author:   Mert, Nick
  * Role:     Stuft das Piece auf
  * Return:   Gibt das Promotete Piece zurueck*/
  def promotePiece: Option[Piece] = {
    None
  }

  override def toString: String = {
    "K" + (if (this.player.first) {
      "° "
    } else {
      "  "
    })
  }

  def toStringLong: String = "King"

  /*Author:   Mert, Nick
  * Role:     Gibt die moeglichen Bewegungsfelder zurueck
  * Return:   List[(Int, Int)] mit den Koordinaten */
  def getMoveSet(pos: (Int, Int), board: BoardInterface): List[(Int, Int)] = {
    //    Jede Richtung ein Feld
    rekMoveSet(board, (pos._1 - 1, pos._2 - 1), 1, (-1, -1)) ::: //Hinten Links
      rekMoveSet(board, (pos._1 - 1, pos._2), 1, (-1, 0)) ::: //Links
      rekMoveSet(board, (pos._1 - 1, pos._2 + 1), 1, (-1, 1)) ::: //Vorne Links
      rekMoveSet(board, (pos._1, pos._2 - 1), 1, (0, -1)) ::: //Hinten
      rekMoveSet(board, (pos._1, pos._2 + 1), 1, (0, 1)) ::: //Vorne
      rekMoveSet(board, (pos._1 + 1, pos._2 - 1), 1, (1, -1)) ::: //Hinten Rechts
      rekMoveSet(board, (pos._1 + 1, pos._2), 1, (1, 0)) ::: //Rechts
      rekMoveSet(board, (pos._1 + 1, pos._2 + 1), 1, (1, 1)) //Vorne Rechts

  }

  override def cloneToNewPlayer(player: Player): King = King(player)
}

//endregion

//region GoldenGeneral

/*Author:   Mert, Nick
* Role:     Erstellt eine GoldenGeneral-Figur */
case class GoldenGeneral(override val player: Player)
    extends Piece("GoldenGeneral", player: Player) {
  val hasPromotion: Boolean = false

  /*Author:   Mert, Nick
  * Role:     Stuft das Piece auf
  * Return:   Gibt das Promotete Piece zurueck*/
  def promotePiece: Option[Piece] = {
    None
  }

  override def toString: String = {
    "GG" + (if (this.player.first) {
      "°"
    } else {
      " "
    })
  }

  def toStringLong: String = "GoldenGeneral"

  /*Author:   Mert, Nick
  * Role:     Gibt die moeglichen Bewegungsfelder zurueck
  * Return:   List[(Int, Int)] mit den Koordinaten */
  def getMoveSet(pos: (Int, Int), board: BoardInterface): List[(Int, Int)] = {
    //    Jede Richtung ein Feld
    if (this.player.first) {
      rekMoveSet(board, (pos._1 - 1, pos._2), 1, (-1, 0)) :::
        rekMoveSet(board, (pos._1 - 1, pos._2 + 1), 1, (-1, 1)) :::
        rekMoveSet(board, (pos._1, pos._2 + 1), 1, (0, 1)) :::
        rekMoveSet(board, (pos._1 + 1, pos._2 + 1), 1, (-1, 1)) :::
        rekMoveSet(board, (pos._1 + 1, pos._2), 1, (1, 0)) :::
        rekMoveSet(board, (pos._1, pos._2 - 1), 1, (1, 1))
    } else {

      rekMoveSet(board, (pos._1 - 1, pos._2), 1, (-1, 0)) :::
        rekMoveSet(board, (pos._1 - 1, pos._2 - 1), 1, (-1, -1)) :::
        rekMoveSet(board, (pos._1, pos._2 - 1), 1, (0, -1)) :::
        rekMoveSet(board, (pos._1 + 1, pos._2 - 1), 1, (1, -1)) :::
        rekMoveSet(board, (pos._1 + 1, pos._2), 1, (1, 0)) :::
        rekMoveSet(board, (pos._1, pos._2 + 1), 1, (0, 1))
    }
  }

  override def cloneToNewPlayer(player: Player): GoldenGeneral = GoldenGeneral(player)
}

//endregion

//region SilverGeneral

/*Author:   Mert, Nick
* Role:     Erstellt eine SilverGeneral-Figur */
case class SilverGeneral(override val player: Player)
    extends Piece("SilverGeneral", player: Player) {
  val hasPromotion: Boolean = true

  /*Author:   Mert, Nick
  * Role:     Stuft das Piece auf
  * Return:   Gibt das Promotete Piece zurueck*/
  def promotePiece: Option[Piece] = {
    Some(PromotedSilver(player))
  }

  override def toString: String = {
    "SG" + (if (this.player.first) {
      "°"
    } else {
      " "
    })
  }

  def toStringLong: String = "SilverGeneral"

  /*Author:   Mert, Nick
  * Role:     Gibt die moeglichen Bewegungsfelder zurueck
  * Return:   List[(Int, Int)] mit den Koordinaten */
  def getMoveSet(pos: (Int, Int), board: BoardInterface): List[(Int, Int)] = {
    if (this.player.first) {
      rekMoveSet(board, (pos._1 - 1, pos._2 + 1), 1, (-1, 1)) ::: //Vorne Links
        rekMoveSet(board, (pos._1 - 1, pos._2 - 1), 1, (-1, -1)) ::: //Hinten Links
        rekMoveSet(board, (pos._1 + 1, pos._2 - 1), 1, (1, -1)) ::: //Hinten Rechts
        rekMoveSet(board, (pos._1 + 1, pos._2 + 1), 1, (1, 1)) ::: //Vorne Rechts
        rekMoveSet(board, (pos._1, pos._2 + 1), 1, (0, 1)) //Vorne
    } else {
      rekMoveSet(board, (pos._1 - 1, pos._2 - 1), 1, (-1, 1)) ::: //Vorne Links
        rekMoveSet(board, (pos._1 - 1, pos._2 + 1), 1, (-1, -1)) ::: //Hinten Links
        rekMoveSet(board, (pos._1 + 1, pos._2 + 1), 1, (1, -1)) ::: //Hinten Rechts
        rekMoveSet(board, (pos._1 + 1, pos._2 - 1), 1, (1, 1)) ::: //Vorne Rechts
        rekMoveSet(board, (pos._1, pos._2 - 1), 1, (0, 1)) //Vorne
    }
  }

  override def cloneToNewPlayer(player: Player): SilverGeneral = SilverGeneral(player)
}

case class PromotedSilver(override val player: Player)
    extends Piece("PromotedSilver", player: Player) {
  val hasPromotion: Boolean = false

  def promotePiece: Option[Piece] = {
    None
  }

  override def toString: String = {
    "PS" + (if (this.player.first) {
      "°"
    } else {
      " "
    })
  }

  def toStringLong: String = "PromotedSilver"

  def getMoveSet(pos: (Int, Int), board: BoardInterface): List[(Int, Int)] = {
    //    Jede Richtung ein Feld
    if (this.player.first) {
      rekMoveSet(board, (pos._1 - 1, pos._2), 1, (-1, 0)) :::
        rekMoveSet(board, (pos._1 - 1, pos._2 + 1), 1, (-1, 1)) :::
        rekMoveSet(board, (pos._1, pos._2 + 1), 1, (0, 1)) :::
        rekMoveSet(board, (pos._1 + 1, pos._2 + 1), 1, (-1, 1)) :::
        rekMoveSet(board, (pos._1 + 1, pos._2), 1, (1, 0)) :::
        rekMoveSet(board, (pos._1, pos._2 - 1), 1, (1, 1))
    } else {

      rekMoveSet(board, (pos._1 - 1, pos._2), 1, (-1, 0)) :::
        rekMoveSet(board, (pos._1 - 1, pos._2 - 1), 1, (-1, -1)) :::
        rekMoveSet(board, (pos._1, pos._2 - 1), 1, (0, -1)) :::
        rekMoveSet(board, (pos._1 + 1, pos._2 - 1), 1, (1, -1)) :::
        rekMoveSet(board, (pos._1 + 1, pos._2), 1, (1, 0)) :::
        rekMoveSet(board, (pos._1, pos._2 + 1), 1, (0, 1))
    }
  }

  override def cloneToNewPlayer(player: Player): SilverGeneral = SilverGeneral(player)
}

//endregion

//region Knight

//noinspection ScalaStyle
/*Author:   Mert, Nick
* Role:     Erstellt eine Knight-Figur */
case class Knight(override val player: Player)
    extends Piece("Knight", player: Player) {
  val hasPromotion: Boolean = true

  /*Author:   Mert, Nick
  * Role:     Stuft das Piece auf
  * Return:   Gibt das Promotete Piece zurueck*/
  def promotePiece: Option[Piece] = {
    Some(pieceFactory.apply("PromotedKnight", player))
  }

  override def toString: String = {
    "KN" + (if (this.player.first) {
      "°"
    } else {
      " "
    })
  }

  def toStringLong: String = "Knight"

  /*Author:   Mert, Nick
  * Role:     Gibt die moeglichen Bewegungsfelder zurueck
  * Return:   List[(Int, Int)] mit den Koordinaten */
  def getMoveSet(pos: (Int, Int), board: BoardInterface): List[(Int, Int)] = {
    if (this.player.first) {
      rekMoveSet(board, (pos._1 - 1, pos._2 + 2), 1, (-1, 2)) :::
        rekMoveSet(board, (pos._1 + 1, pos._2 + 2), 1, (1, 2))
    } else {
      rekMoveSet(board, (pos._1 - 1, pos._2 - 2), 1, (-1, -2)) :::
        rekMoveSet(board, (pos._1 + 1, pos._2 - 2), 1, (1, -2))
    }

  }

  override def cloneToNewPlayer(player: Player): Knight = Knight(player)
}

case class PromotedKnight(override val player: Player)
    extends Piece("PromotedKnight", player: Player) {
  val hasPromotion: Boolean = false

  def promotePiece: Option[Piece] = {
    None
  }

  override def toString: String = {
    "PK" + (if (this.player.first) {
      "°"
    } else {
      " "
    })
  }

  def toStringLong: String = "PromotedKnight"

  def getMoveSet(pos: (Int, Int), board: BoardInterface): List[(Int, Int)] = {
    if (this.player.first) {
      rekMoveSet(board, (pos._1 - 1, pos._2), 1, (-1, 0)) :::
        rekMoveSet(board, (pos._1 - 1, pos._2 + 1), 1, (-1, 1)) :::
        rekMoveSet(board, (pos._1, pos._2 + 1), 1, (0, 1)) :::
        rekMoveSet(board, (pos._1 + 1, pos._2 + 1), 1, (-1, 1)) :::
        rekMoveSet(board, (pos._1 + 1, pos._2), 1, (1, 0)) :::
        rekMoveSet(board, (pos._1, pos._2 - 1), 1, (1, 1))
    } else {

      rekMoveSet(board, (pos._1 - 1, pos._2), 1, (-1, 0)) :::
        rekMoveSet(board, (pos._1 - 1, pos._2 - 1), 1, (-1, -1)) :::
        rekMoveSet(board, (pos._1, pos._2 - 1), 1, (0, -1)) :::
        rekMoveSet(board, (pos._1 + 1, pos._2 - 1), 1, (1, -1)) :::
        rekMoveSet(board, (pos._1 + 1, pos._2), 1, (1, 0)) :::
        rekMoveSet(board, (pos._1, pos._2 + 1), 1, (0, 1))
    }
  }

  override def cloneToNewPlayer(player: Player): Knight = Knight(player)
}

//endregion

//region Lancer

/*Author:   Mert, Nick
* Role:     Erstellt eine Lancer-Figur */
case class Lancer(override val player: Player)
    extends Piece("Lancer", player: Player) {
  val hasPromotion: Boolean = true

  /*Author:   Mert, Nick
  * Role:     Stuft das Piece auf
  * Return:   Gibt das Promotete Piece zurueck*/
  def promotePiece: Option[Piece] = {
    Some(PromotedLancer(player))
  }

  override def toString: String = {
    "L" + (if (this.player.first) {
      "° "
    } else {
      "  "
    })
  }

  def toStringLong: String = "Lancer"

  /*Author:   Mert, Nick
  * Role:     Gibt die moeglichen Bewegungsfelder zurueck
  * Return:   List[(Int, Int)] mit den Koordinaten */
  def getMoveSet(pos: (Int, Int), board: BoardInterface): List[(Int, Int)] = {
    if (this.player.first) {
      rekMoveSet(board, (pos._1, pos._2 + 1), board.size, (0, 1))
    } else {
      rekMoveSet(board, (pos._1, pos._2 - 1), board.size, (0, -1))
    }
  }

  override def cloneToNewPlayer(player: Player): Lancer = Lancer(player)
}

case class PromotedLancer(override val player: Player)
    extends Piece("PromotedLancer", player: Player) {
  val hasPromotion: Boolean = false

  def promotePiece: Option[Piece] = {
    None
  }

  override def toString: String = {
    "PL" + (if (this.player.first) {
      "°"
    } else {
      " "
    })
  }

  def toStringLong: String = "PromotedLancer"

  def getMoveSet(pos: (Int, Int), board: BoardInterface): List[(Int, Int)] = {
    if (this.player.first) {
      rekMoveSet(board, (pos._1 - 1, pos._2), 1, (-1, 0)) :::
        rekMoveSet(board, (pos._1 - 1, pos._2 + 1), 1, (-1, 1)) :::
        rekMoveSet(board, (pos._1, pos._2 + 1), 1, (0, 1)) :::
        rekMoveSet(board, (pos._1 + 1, pos._2 + 1), 1, (-1, 1)) :::
        rekMoveSet(board, (pos._1 + 1, pos._2), 1, (1, 0)) :::
        rekMoveSet(board, (pos._1, pos._2 - 1), 1, (1, 1))
    } else {

      rekMoveSet(board, (pos._1 - 1, pos._2), 1, (-1, 0)) :::
        rekMoveSet(board, (pos._1 - 1, pos._2 - 1), 1, (-1, -1)) :::
        rekMoveSet(board, (pos._1, pos._2 - 1), 1, (0, -1)) :::
        rekMoveSet(board, (pos._1 + 1, pos._2 - 1), 1, (1, -1)) :::
        rekMoveSet(board, (pos._1 + 1, pos._2), 1, (1, 0)) :::
        rekMoveSet(board, (pos._1, pos._2 + 1), 1, (0, 1))
    }
  }

  override def cloneToNewPlayer(player: Player): Lancer = Lancer(player)
}

//endregion

//region Bishop

/*Author:   Mert, Nick
* Role:     Erstellt eine Bishop-Figur */
case class Bishop(override val player: Player)
    extends Piece("Bishop", player: Player) {
  val hasPromotion: Boolean = true

  /*Author:   Mert, Nick
  * Role:     Stuft das Piece auf
  * Return:   Gibt das Promotete Piece zurueck*/
  def promotePiece: Option[Piece] = {
    Some(PromotedBishop(player))
  }

  override def toString: String = {
    "B" + (if (this.player.first) {
      "° "
    } else {
      "  "
    })
  }

  def toStringLong: String = "Bishop"

  /*Author:   Mert, Nick
  * Role:     Gibt die moeglichen Bewegungsfelder zurueck
  * Return:   List[(Int, Int)] mit den Koordinaten */
  def getMoveSet(pos: (Int, Int), board: BoardInterface): List[(Int, Int)] = {
    rekMoveSet(board, (pos._1 - 1, pos._2 - 1), board.size, (-1, -1)) :::
      rekMoveSet(board, (pos._1 - 1, pos._2 + 1), board.size, (-1, 1)) :::
      rekMoveSet(board, (pos._1 + 1, pos._2 + 1), board.size, (1, 1)) :::
      rekMoveSet(board, (pos._1 + 1, pos._2 - 1), board.size, (1, -1))
  }

  override def cloneToNewPlayer(player: Player): Bishop = Bishop(player)
}

case class PromotedBishop(override val player: Player)
    extends Piece("PromotedBishop", player: Player) {
  val hasPromotion: Boolean = false

  def promotePiece: Option[Piece] = {
    None
  }

  override def toString: String = {
    "PB" + (if (this.player.first) {
      "°"
    } else {
      " "
    })
  }

  def toStringLong: String = "PromotedBishop"

  def getMoveSet(pos: (Int, Int), board: BoardInterface): List[(Int, Int)] = {
    rekMoveSet(board, (pos._1 - 1, pos._2 - 1), board.size, (-1, -1)) :::
      rekMoveSet(board, (pos._1 - 1, pos._2 + 1), board.size, (-1, 1)) :::
      rekMoveSet(board, (pos._1 + 1, pos._2 + 1), board.size, (1, 1)) :::
      rekMoveSet(board, (pos._1 + 1, pos._2 - 1), board.size, (1, -1)) :::
      rekMoveSet(board, (pos._1, pos._2 + 1), 1, (0, 1)) :::
      rekMoveSet(board, (pos._1 + 1, pos._2), 1, (1, 0)) :::
      rekMoveSet(board, (pos._1, pos._2 - 1), 1, (0, -1)) :::
      rekMoveSet(board, (pos._1 - 1, pos._2), 1, (-1, 0))
  }

  override def cloneToNewPlayer(player: Player): Bishop = Bishop(player)
}

//endregion

//region Rook

/*Author:   Mert, Nick
* Role:     Erstellt eine Rook-Figur */
case class Rook(override val player: Player)
    extends Piece("Rook", player: Player) {
  val hasPromotion: Boolean = true

  /*Author:   Mert, Nick
  * Role:     Stuft das Piece auf
  * Return:   Gibt das Promotete Piece zurueck*/
  def promotePiece: Option[Piece] = {
    Some(PromotedRook(player))
  }

  override def toString: String = {
    "R" + (if (this.player.first) {
      "° "
    } else {
      "  "
    })
  }

  def toStringLong: String = "Rook"

  /*Author:   Mert, Nick
  * Role:     Gibt die moeglichen Bewegungsfelder zurueck
  * Return:   List[(Int, Int)] mit den Koordinaten */
  def getMoveSet(pos: (Int, Int), board: BoardInterface): List[(Int, Int)] = {
    rekMoveSet(board, (pos._1, pos._2 + 1), board.size, (0, 1)) :::
      rekMoveSet(board, (pos._1 + 1, pos._2), board.size, (1, 0)) :::
      rekMoveSet(board, (pos._1, pos._2 - 1), board.size, (0, -1)) :::
      rekMoveSet(board, (pos._1 - 1, pos._2), board.size, (-1, 0))
  }

  override def cloneToNewPlayer(player: Player): Rook = Rook(player)
}

case class PromotedRook(override val player: Player)
    extends Piece("PromotedRook", player: Player) {
  val hasPromotion: Boolean = false

  def promotePiece: Option[Piece] = {
    None
  }

  override def toString: String = {
    "PR" + (if (this.player.first) {
      "°"
    } else {
      " "
    })
  }

  def toStringLong: String = "PromotedRook"

  def getMoveSet(pos: (Int, Int), board: BoardInterface): List[(Int, Int)] = {
    rekMoveSet(board, (pos._1, pos._2 + 1), board.size, (0, 1)) :::
      rekMoveSet(board, (pos._1 + 1, pos._2), board.size, (1, 0)) :::
      rekMoveSet(board, (pos._1, pos._2 - 1), board.size, (0, -1)) :::
      rekMoveSet(board, (pos._1 - 1, pos._2), board.size, (-1, 0)) :::
      rekMoveSet(board, (pos._1 - 1, pos._2 - 1), 1, (-1, -1)) :::
      rekMoveSet(board, (pos._1 - 1, pos._2 + 1), 1, (-1, 1)) :::
      rekMoveSet(board, (pos._1 + 1, pos._2 + 1), 1, (1, 1)) :::
      rekMoveSet(board, (pos._1 + 1, pos._2 - 1), 1, (1, -1))
  }

  override def cloneToNewPlayer(player: Player): Rook = Rook(player)
}

//endregion

//region Pawn

/*Author:   Mert, Nick
* Role:     Erstellt eine Pawn-Figur */
case class Pawn(override val player: Player)
    extends Piece("Pawn", player: Player) {
  val hasPromotion: Boolean = true

  /*Author:   Mert, Nick
  * Role:     Stuft das Piece auf
  * Return:   Gibt das Promotete Piece zurueck*/
  def promotePiece: Option[Piece] = {
    Some(PromotedPawn(player))
  }

  override def toString: String = {
    "P" + (if (this.player.first) {
      "° "
    } else {
      "  "
    })
  }

  def toStringLong: String = "Pawn"

  /*Author:   Mert, Nick
  * Role:     Gibt die moeglichen Bewegungsfelder zurueck
  * Return:   List[(Int, Int)] mit den Koordinaten */
  def getMoveSet(pos: (Int, Int), board: BoardInterface): List[(Int, Int)] = {
    if (this.player.first) {
      rekMoveSet(board, (pos._1, pos._2 + 1), 1, (0, 1))
    } else {
      rekMoveSet(board, (pos._1, pos._2 - 1), 1, (0, -1))
    }
  }

  override def cloneToNewPlayer(player: Player): Pawn = Pawn(player)
}

case class PromotedPawn(override val player: Player)
    extends Piece("PromotedPawn", player: Player) {
  val hasPromotion: Boolean = false

  def promotePiece: Option[Piece] = {
    None
  }

  override def toString: String = {
    "PP" + (if (this.player.first) {
      "°"
    } else {
      " "
    })
  }

  def toStringLong: String = "PromotedPawn"

  def getMoveSet(pos: (Int, Int), board: BoardInterface): List[(Int, Int)] = {
    if (this.player.first) {
      rekMoveSet(board, (pos._1 - 1, pos._2), 1, (-1, 0)) :::
        rekMoveSet(board, (pos._1 - 1, pos._2 + 1), 1, (-1, 1)) :::
        rekMoveSet(board, (pos._1, pos._2 + 1), 1, (0, 1)) :::
        rekMoveSet(board, (pos._1 + 1, pos._2 + 1), 1, (-1, 1)) :::
        rekMoveSet(board, (pos._1 + 1, pos._2), 1, (1, 0)) :::
        rekMoveSet(board, (pos._1, pos._2 - 1), 1, (1, 1))
    } else {

      rekMoveSet(board, (pos._1 - 1, pos._2), 1, (-1, 0)) :::
        rekMoveSet(board, (pos._1 - 1, pos._2 - 1), 1, (-1, -1)) :::
        rekMoveSet(board, (pos._1, pos._2 - 1), 1, (0, -1)) :::
        rekMoveSet(board, (pos._1 + 1, pos._2 - 1), 1, (1, -1)) :::
        rekMoveSet(board, (pos._1 + 1, pos._2), 1, (1, 0)) :::
        rekMoveSet(board, (pos._1, pos._2 + 1), 1, (0, 1))
    }
  }

  override def cloneToNewPlayer(player: Player): Pawn = Pawn(player)
}

//endregion

//region EmptyPiece
case class EmptyPiece()
    extends Piece("", Player("", false)) {
  val hasPromotion: Boolean = false

  /*Author:   Mert, Nick
  * Role:     Stuft das Piece auf
  * Return:   Gibt das Promotete Piece zurueck*/
  def promotePiece: Option[Piece] = {
    None
  }

  override def toString: String = "   "

  def toStringLong: String = "   "

  /*Author:   Mert, Nick
  * Role:     Gibt die moeglichen Bewegungsfelder zurueck
  * Return:   List[(Int, Int)] mit den Koordinaten */
  def getMoveSet(pos: (Int, Int), board: BoardInterface): List[(Int, Int)] = {
    List()
  }

  override def cloneToNewPlayer(player: Player): EmptyPiece = EmptyPiece()
}

//endregion