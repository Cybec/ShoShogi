package de.htwg.se.ShoShogi.model.pieceComponent.pieceBaseImpl

import de.htwg.se.ShoShogi.model.boardComponent.BoardInterface
import de.htwg.se.ShoShogi.model.pieceComponent.PieceInterface
import de.htwg.se.ShoShogi.model.playerComponent.Player

abstract class Piece(name: String, val player: Player) extends PieceInterface {

  override def isFirstOwner: Boolean = player.first

  override def rekMoveSet(board: BoardInterface, newPos: (Int, Int), rek: Int, value: (Int, Int)): List[(Int, Int)] = {
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

  override def typeEquals(piecesAbbreviation: String): Boolean = this.toString().trim == piecesAbbreviation.trim
}

//region King

/*Author:   Mert, Nick
* Role:     Erstellt eine King-Figur */
protected case class King(override val player: Player)
    extends Piece("King", player: Player) {
  override val hasPromotion: Boolean = false

  /*Author:   Mert, Nick
  * Role:     Stuft das Piece auf
  * Return:   Gibt das Promotete Piece zurueck*/
  override def promotePiece: Option[Piece] = {
    None
  }

  override def toString: String = {
    "K" + (if (this.player.first) {
      "° "
    } else {
      "  "
    })
  }

  override def toStringLong: String = "King"

  /*Author:   Mert, Nick
  * Role:     Gibt die moeglichen Bewegungsfelder zurueck
  * Return:   List[(Int, Int)] mit den Koordinaten */
  override def getMoveSet(pos: (Int, Int), board: BoardInterface): List[(Int, Int)] = {
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
protected case class GoldenGeneral(override val player: Player)
    extends Piece("GoldenGeneral", player: Player) {
  override val hasPromotion: Boolean = false

  /*Author:   Mert, Nick
  * Role:     Stuft das Piece auf
  * Return:   Gibt das Promotete Piece zurueck*/
  override def promotePiece: Option[Piece] = {
    None
  }

  override def toString: String = {
    "GG" + (if (this.player.first) {
      "°"
    } else {
      " "
    })
  }

  override def toStringLong: String = "GoldenGeneral"

  /*Author:   Mert, Nick
  * Role:     Gibt die moeglichen Bewegungsfelder zurueck
  * Return:   List[(Int, Int)] mit den Koordinaten */
  override def getMoveSet(pos: (Int, Int), board: BoardInterface): List[(Int, Int)] = {
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
protected case class SilverGeneral(override val player: Player)
    extends Piece("SilverGeneral", player: Player) {
  override val hasPromotion: Boolean = true

  /*Author:   Mert, Nick
  * Role:     Stuft das Piece auf
  * Return:   Gibt das Promotete Piece zurueck*/
  override def promotePiece: Option[Piece] = {
    Some(PromotedSilver(player))
  }

  override def toString: String = {
    "SG" + (if (this.player.first) {
      "°"
    } else {
      " "
    })
  }

  override def toStringLong: String = "SilverGeneral"

  /*Author:   Mert, Nick
  * Role:     Gibt die moeglichen Bewegungsfelder zurueck
  * Return:   List[(Int, Int)] mit den Koordinaten */
  override def getMoveSet(pos: (Int, Int), board: BoardInterface): List[(Int, Int)] = {
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

protected case class PromotedSilver(override val player: Player)
    extends Piece("PromotedSilver", player: Player) {
  override val hasPromotion: Boolean = false

  override def promotePiece: Option[Piece] = {
    None
  }

  override def toString: String = {
    "PS" + (if (this.player.first) {
      "°"
    } else {
      " "
    })
  }

  override def toStringLong: String = "PromotedSilver"

  override def getMoveSet(pos: (Int, Int), board: BoardInterface): List[(Int, Int)] = {
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
protected case class Knight(override val player: Player)
    extends Piece("Knight", player: Player) {
  override val hasPromotion: Boolean = true

  /*Author:   Mert, Nick
  * Role:     Stuft das Piece auf
  * Return:   Gibt das Promotete Piece zurueck*/
  override def promotePiece: Option[Piece] = {
    Some(PieceFactory.apply(PiecesEnum.PromotedKnight, player))
  }

  override def toString: String = {
    "KN" + (if (this.player.first) {
      "°"
    } else {
      " "
    })
  }

  override def toStringLong: String = "Knight"

  /*Author:   Mert, Nick
  * Role:     Gibt die moeglichen Bewegungsfelder zurueck
  * Return:   List[(Int, Int)] mit den Koordinaten */
  override def getMoveSet(pos: (Int, Int), board: BoardInterface): List[(Int, Int)] = {
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

protected case class PromotedKnight(override val player: Player)
    extends Piece("PromotedKnight", player: Player) {
  override val hasPromotion: Boolean = false

  override def promotePiece: Option[Piece] = {
    None
  }

  override def toString: String = {
    "PK" + (if (this.player.first) {
      "°"
    } else {
      " "
    })
  }

  override def toStringLong: String = "PromotedKnight"

  override def getMoveSet(pos: (Int, Int), board: BoardInterface): List[(Int, Int)] = {
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
protected case class Lancer(override val player: Player)
    extends Piece("Lancer", player: Player) {
  override val hasPromotion: Boolean = true

  /*Author:   Mert, Nick
  * Role:     Stuft das Piece auf
  * Return:   Gibt das Promotete Piece zurueck*/
  override def promotePiece: Option[Piece] = {
    Some(PromotedLancer(player))
  }

  override def toString: String = {
    "L" + (if (this.player.first) {
      "° "
    } else {
      "  "
    })
  }

  override def toStringLong: String = "Lancer"

  /*Author:   Mert, Nick
  * Role:     Gibt die moeglichen Bewegungsfelder zurueck
  * Return:   List[(Int, Int)] mit den Koordinaten */
  override def getMoveSet(pos: (Int, Int), board: BoardInterface): List[(Int, Int)] = {
    if (this.player.first) {
      rekMoveSet(board, (pos._1, pos._2 + 1), board.size, (0, 1))
    } else {
      rekMoveSet(board, (pos._1, pos._2 - 1), board.size, (0, -1))
    }
  }

  override def cloneToNewPlayer(player: Player): Lancer = Lancer(player)
}

protected case class PromotedLancer(override val player: Player)
    extends Piece("PromotedLancer", player: Player) {
  override val hasPromotion: Boolean = false

  override def promotePiece: Option[Piece] = {
    None
  }

  override def toString: String = {
    "PL" + (if (this.player.first) {
      "°"
    } else {
      " "
    })
  }

  override def toStringLong: String = "PromotedLancer"

  override def getMoveSet(pos: (Int, Int), board: BoardInterface): List[(Int, Int)] = {
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
protected case class Bishop(override val player: Player)
    extends Piece("Bishop", player: Player) {
  override val hasPromotion: Boolean = true

  /*Author:   Mert, Nick
  * Role:     Stuft das Piece auf
  * Return:   Gibt das Promotete Piece zurueck*/
  override def promotePiece: Option[Piece] = {
    Some(PromotedBishop(player))
  }

  override def toString: String = {
    "B" + (if (this.player.first) {
      "° "
    } else {
      "  "
    })
  }

  override def toStringLong: String = "Bishop"

  /*Author:   Mert, Nick
  * Role:     Gibt die moeglichen Bewegungsfelder zurueck
  * Return:   List[(Int, Int)] mit den Koordinaten */
  override def getMoveSet(pos: (Int, Int), board: BoardInterface): List[(Int, Int)] = {
    rekMoveSet(board, (pos._1 - 1, pos._2 - 1), board.size, (-1, -1)) :::
      rekMoveSet(board, (pos._1 - 1, pos._2 + 1), board.size, (-1, 1)) :::
      rekMoveSet(board, (pos._1 + 1, pos._2 + 1), board.size, (1, 1)) :::
      rekMoveSet(board, (pos._1 + 1, pos._2 - 1), board.size, (1, -1))
  }

  override def cloneToNewPlayer(player: Player): Bishop = Bishop(player)
}

protected case class PromotedBishop(override val player: Player)
    extends Piece("PromotedBishop", player: Player) {
  override val hasPromotion: Boolean = false

  override def promotePiece: Option[Piece] = {
    None
  }

  override def toString: String = {
    "PB" + (if (this.player.first) {
      "°"
    } else {
      " "
    })
  }

  override def toStringLong: String = "PromotedBishop"

  override def getMoveSet(pos: (Int, Int), board: BoardInterface): List[(Int, Int)] = {
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
protected case class Rook(override val player: Player)
    extends Piece("Rook", player: Player) {
  override val hasPromotion: Boolean = true

  /*Author:   Mert, Nick
  * Role:     Stuft das Piece auf
  * Return:   Gibt das Promotete Piece zurueck*/
  override def promotePiece: Option[Piece] = {
    Some(PromotedRook(player))
  }

  override def toString: String = {
    "R" + (if (this.player.first) {
      "° "
    } else {
      "  "
    })
  }

  override def toStringLong: String = "Rook"

  /*Author:   Mert, Nick
  * Role:     Gibt die moeglichen Bewegungsfelder zurueck
  * Return:   List[(Int, Int)] mit den Koordinaten */
  override def getMoveSet(pos: (Int, Int), board: BoardInterface): List[(Int, Int)] = {
    rekMoveSet(board, (pos._1, pos._2 + 1), board.size, (0, 1)) :::
      rekMoveSet(board, (pos._1 + 1, pos._2), board.size, (1, 0)) :::
      rekMoveSet(board, (pos._1, pos._2 - 1), board.size, (0, -1)) :::
      rekMoveSet(board, (pos._1 - 1, pos._2), board.size, (-1, 0))
  }

  override def cloneToNewPlayer(player: Player): Rook = Rook(player)
}

protected case class PromotedRook(override val player: Player)
    extends Piece("PromotedRook", player: Player) {
  override val hasPromotion: Boolean = false

  override def promotePiece: Option[Piece] = {
    None
  }

  override def toString: String = {
    "PR" + (if (this.player.first) {
      "°"
    } else {
      " "
    })
  }

  override def toStringLong: String = "PromotedRook"

  override def getMoveSet(pos: (Int, Int), board: BoardInterface): List[(Int, Int)] = {
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
protected case class Pawn(override val player: Player)
    extends Piece("Pawn", player: Player) {
  override val hasPromotion: Boolean = true

  /*Author:   Mert, Nick
  * Role:     Stuft das Piece auf
  * Return:   Gibt das Promotete Piece zurueck*/
  override def promotePiece: Option[Piece] = {
    Some(PromotedPawn(player))
  }

  override def toString: String = {
    "P" + (if (this.player.first) {
      "° "
    } else {
      "  "
    })
  }

  override def toStringLong: String = "Pawn"

  /*Author:   Mert, Nick
  * Role:     Gibt die moeglichen Bewegungsfelder zurueck
  * Return:   List[(Int, Int)] mit den Koordinaten */
  override def getMoveSet(pos: (Int, Int), board: BoardInterface): List[(Int, Int)] = {
    if (this.player.first) {
      rekMoveSet(board, (pos._1, pos._2 + 1), 1, (0, 1))
    } else {
      rekMoveSet(board, (pos._1, pos._2 - 1), 1, (0, -1))
    }
  }

  override def cloneToNewPlayer(player: Player): Pawn = Pawn(player)
}

protected case class PromotedPawn(override val player: Player)
    extends Piece("PromotedPawn", player: Player) {
  override val hasPromotion: Boolean = false

  override def promotePiece: Option[Piece] = {
    None
  }

  override def toString: String = {
    "PP" + (if (this.player.first) {
      "°"
    } else {
      " "
    })
  }

  override def toStringLong: String = "PromotedPawn"

  override def getMoveSet(pos: (Int, Int), board: BoardInterface): List[(Int, Int)] = {
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
protected case class EmptyPiece()
    extends Piece("", Player("", false)) {
  override val hasPromotion: Boolean = false

  /*Author:   Mert, Nick
  * Role:     Stuft das Piece auf
  * Return:   Gibt das Promotete Piece zurueck*/
  override def promotePiece: Option[Piece] = {
    None
  }

  override def toString: String = "   "

  override def toStringLong: String = "   "

  /*Author:   Mert, Nick
  * Role:     Gibt die moeglichen Bewegungsfelder zurueck
  * Return:   List[(Int, Int)] mit den Koordinaten */
  override def getMoveSet(pos: (Int, Int), board: BoardInterface): List[(Int, Int)] = {
    List()
  }

  override def cloneToNewPlayer(player: Player): EmptyPiece = EmptyPiece()
}

//endregion