package de.htwg.se.ShoShogi.model.pieceComponent.pieceBaseImpl

import de.htwg.se.ShoShogi.model.boardComponent.BoardInterface
import de.htwg.se.ShoShogi.model.pieceComponent.PieceInterface

abstract class Piece(_name: String, _isFirstOwner: Boolean) extends PieceInterface {

  override val name: String = _name

  override def isFirstOwner: Boolean = _isFirstOwner

  override def rekMoveSet(board: BoardInterface, newPos: (Int, Int), rek: Int, value: (Int, Int)): List[(Int, Int)] = {
    var l = List[(Int, Int)]()

    if (rek != 0) {
      board.cell(newPos._1, newPos._2).foreach(i =>
        if (PieceFactory.isInstanceOfPiece(PiecesEnum.EmptyPiece, i) || i.isFirstOwner != this.isFirstOwner) {
          l = List[(Int, Int)]((newPos._1, newPos._2))
          if (i.isInstanceOf[EmptyPiece]) {
            l = l ::: rekMoveSet(board, (newPos._1 + value._1, newPos._2 + value._2), rek - 1, value)
          }
        })
      l
    } else {
      List[(Int, Int)]()
    }
  }

  override def typeEquals(piecesAbbreviation: String): Boolean = this.toString.trim == piecesAbbreviation.trim
}

//region King

/*Author:   Mert, Nick
* Role:     Erstellt eine King-Figur */
protected case class King(_isFirstOwner: Boolean)
    extends Piece("King", _isFirstOwner) {
  override val hasPromotion: Boolean = false

  /*Author:   Mert, Nick
  * Role:     Stuft das Piece auf
  * Return:   Gibt das Promotete Piece zurueck*/
  override def promotePiece: Option[PieceInterface] = {
    None
  }

  override def toString: String = {
    "K" + (if (this.isFirstOwner) {
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

  override def cloneToNewPlayer(first: Boolean): King = King(first)
}

//endregion

//region GoldenGeneral

/*Author:   Mert, Nick
* Role:     Erstellt eine GoldenGeneral-Figur */
protected case class GoldenGeneral(_isFirstOwner: Boolean)
    extends Piece("GoldenGeneral", _isFirstOwner) {
  override val hasPromotion: Boolean = false

  /*Author:   Mert, Nick
  * Role:     Stuft das Piece auf
  * Return:   Gibt das Promotete Piece zurueck*/
  override def promotePiece: Option[PieceInterface] = {
    None
  }

  override def toString: String = {
    "GG" + (if (this.isFirstOwner) {
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
    if (this.isFirstOwner) {
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

  override def cloneToNewPlayer(first: Boolean): GoldenGeneral = GoldenGeneral(first)
}

//endregion

//region SilverGeneral

/*Author:   Mert, Nick
* Role:     Erstellt eine SilverGeneral-Figur */
protected case class SilverGeneral(_isFirstOwner: Boolean)
    extends Piece("SilverGeneral", _isFirstOwner) {
  override val hasPromotion: Boolean = true

  /*Author:   Mert, Nick
  * Role:     Stuft das Piece auf
  * Return:   Gibt das Promotete Piece zurueck*/
  override def promotePiece: Option[PieceInterface] = {
    Some(PieceFactory.apply(PiecesEnum.PromotedSilver, _isFirstOwner))
  }

  override def toString: String = {
    "SG" + (if (this.isFirstOwner) {
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
    if (this.isFirstOwner) {
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

  override def cloneToNewPlayer(first: Boolean): SilverGeneral = SilverGeneral(first)
}

protected case class PromotedSilver(_isFirstOwner: Boolean)
    extends Piece("PromotedSilver", _isFirstOwner) {
  override val hasPromotion: Boolean = false

  override def promotePiece: Option[PieceInterface] = {
    None
  }

  override def toString: String = {
    "PS" + (if (this.isFirstOwner) {
      "°"
    } else {
      " "
    })
  }

  override def toStringLong: String = "PromotedSilver"

  override def getMoveSet(pos: (Int, Int), board: BoardInterface): List[(Int, Int)] = {
    //    Jede Richtung ein Feld
    if (this.isFirstOwner) {
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

  override def cloneToNewPlayer(first: Boolean): SilverGeneral = SilverGeneral(first)
}

//endregion

//region Knight

//noinspection ScalaStyle
/*Author:   Mert, Nick
* Role:     Erstellt eine Knight-Figur */
protected case class Knight(_isFirstOwner: Boolean)
    extends Piece("Knight", _isFirstOwner) {
  override val hasPromotion: Boolean = true

  /*Author:   Mert, Nick
  * Role:     Stuft das Piece auf
  * Return:   Gibt das Promotete Piece zurueck*/
  override def promotePiece: Option[PieceInterface] = {
    Some(PieceFactory.apply(PiecesEnum.PromotedKnight, _isFirstOwner))
  }

  override def toString: String = {
    "KN" + (if (this.isFirstOwner) {
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
    if (this.isFirstOwner) {
      rekMoveSet(board, (pos._1 - 1, pos._2 + 2), 1, (-1, 2)) :::
        rekMoveSet(board, (pos._1 + 1, pos._2 + 2), 1, (1, 2))
    } else {
      rekMoveSet(board, (pos._1 - 1, pos._2 - 2), 1, (-1, -2)) :::
        rekMoveSet(board, (pos._1 + 1, pos._2 - 2), 1, (1, -2))
    }

  }

  override def cloneToNewPlayer(first: Boolean): Knight = Knight(first)
}

protected case class PromotedKnight(_isFirstOwner: Boolean)
    extends Piece("PromotedKnight", _isFirstOwner) {
  override val hasPromotion: Boolean = false

  override def promotePiece: Option[PieceInterface] = {
    None
  }

  override def toString: String = {
    "PK" + (if (this.isFirstOwner) {
      "°"
    } else {
      " "
    })
  }

  override def toStringLong: String = "PromotedKnight"

  override def getMoveSet(pos: (Int, Int), board: BoardInterface): List[(Int, Int)] = {
    if (this.isFirstOwner) {
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

  override def cloneToNewPlayer(first: Boolean): Knight = Knight(first)
}

//endregion

//region Lancer

/*Author:   Mert, Nick
* Role:     Erstellt eine Lancer-Figur */
protected case class Lancer(_isFirstOwner: Boolean)
    extends Piece("Lancer", _isFirstOwner) {
  override val hasPromotion: Boolean = true

  /*Author:   Mert, Nick
  * Role:     Stuft das Piece auf
  * Return:   Gibt das Promotete Piece zurueck*/
  override def promotePiece: Option[PieceInterface] = {
    Some(PieceFactory.apply(PiecesEnum.PromotedLancer, _isFirstOwner))
  }

  override def toString: String = {
    "L" + (if (this.isFirstOwner) {
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
    if (this.isFirstOwner) {
      rekMoveSet(board, (pos._1, pos._2 + 1), board.size, (0, 1))
    } else {
      rekMoveSet(board, (pos._1, pos._2 - 1), board.size, (0, -1))
    }
  }

  override def cloneToNewPlayer(first: Boolean): Lancer = Lancer(first)
}

protected case class PromotedLancer(_isFirstOwner: Boolean)
    extends Piece("PromotedLancer", _isFirstOwner) {
  override val hasPromotion: Boolean = false

  override def promotePiece: Option[PieceInterface] = {
    None
  }

  override def toString: String = {
    "PL" + (if (this.isFirstOwner) {
      "°"
    } else {
      " "
    })
  }

  override def toStringLong: String = "PromotedLancer"

  override def getMoveSet(pos: (Int, Int), board: BoardInterface): List[(Int, Int)] = {
    if (this.isFirstOwner) {
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

  override def cloneToNewPlayer(first: Boolean): Lancer = Lancer(first)
}

//endregion

//region Bishop

/*Author:   Mert, Nick
* Role:     Erstellt eine Bishop-Figur */
protected case class Bishop(_isFirstOwner: Boolean)
    extends Piece("Bishop", _isFirstOwner) {
  override val hasPromotion: Boolean = true

  /*Author:   Mert, Nick
  * Role:     Stuft das Piece auf
  * Return:   Gibt das Promotete Piece zurueck*/
  override def promotePiece: Option[PieceInterface] = {
    Some(PieceFactory.apply(PiecesEnum.PromotedBishop, _isFirstOwner))
  }

  override def toString: String = {
    "B" + (if (this.isFirstOwner) {
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

  override def cloneToNewPlayer(first: Boolean): Bishop = Bishop(first)
}

protected case class PromotedBishop(_isFirstOwner: Boolean)
    extends Piece("PromotedBishop", _isFirstOwner) {
  override val hasPromotion: Boolean = false

  override def promotePiece: Option[PieceInterface] = {
    None
  }

  override def toString: String = {
    "PB" + (if (this.isFirstOwner) {
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

  override def cloneToNewPlayer(first: Boolean): Bishop = Bishop(first)
}

//endregion

//region Rook

/*Author:   Mert, Nick
* Role:     Erstellt eine Rook-Figur */
protected case class Rook(_isFirstOwner: Boolean)
    extends Piece("Rook", _isFirstOwner) {
  override val hasPromotion: Boolean = true

  /*Author:   Mert, Nick
  * Role:     Stuft das Piece auf
  * Return:   Gibt das Promotete Piece zurueck*/
  override def promotePiece: Option[PieceInterface] = {
    Some(PieceFactory.apply(PiecesEnum.PromotedRook, _isFirstOwner))
  }

  override def toString: String = {
    "R" + (if (this.isFirstOwner) {
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

  override def cloneToNewPlayer(first: Boolean): Rook = Rook(first)
}

protected case class PromotedRook(_isFirstOwner: Boolean)
    extends Piece("PromotedRook", _isFirstOwner) {
  override val hasPromotion: Boolean = false

  override def promotePiece: Option[PieceInterface] = {
    None
  }

  override def toString: String = {
    "PR" + (if (this.isFirstOwner) {
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

  override def cloneToNewPlayer(first: Boolean): Rook = Rook(first)
}

//endregion

//region Pawn

/*Author:   Mert, Nick
* Role:     Erstellt eine Pawn-Figur */
protected case class Pawn(_isFirstOwner: Boolean)
    extends Piece("Pawn", _isFirstOwner) {
  override val hasPromotion: Boolean = true

  /*Author:   Mert, Nick
  * Role:     Stuft das Piece auf
  * Return:   Gibt das Promotete Piece zurueck*/
  override def promotePiece: Option[PieceInterface] = {
    Some(PieceFactory.apply(PiecesEnum.PromotedPawn, _isFirstOwner))
  }

  override def toString: String = {
    "P" + (if (this.isFirstOwner) {
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
    if (this.isFirstOwner) {
      rekMoveSet(board, (pos._1, pos._2 + 1), 1, (0, 1))
    } else {
      rekMoveSet(board, (pos._1, pos._2 - 1), 1, (0, -1))
    }
  }

  override def cloneToNewPlayer(first: Boolean): Pawn = Pawn(first)
}

protected case class PromotedPawn(_isFirstOwner: Boolean)
    extends Piece("PromotedPawn", _isFirstOwner) {
  override val hasPromotion: Boolean = false

  override def promotePiece: Option[PieceInterface] = {
    None
  }

  override def toString: String = {
    "PP" + (if (this.isFirstOwner) {
      "°"
    } else {
      " "
    })
  }

  override def toStringLong: String = "PromotedPawn"

  override def getMoveSet(pos: (Int, Int), board: BoardInterface): List[(Int, Int)] = {
    if (this.isFirstOwner) {
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

  override def cloneToNewPlayer(first: Boolean): Pawn = Pawn(first)
}

//endregion

//region EmptyPiece
protected case class EmptyPiece()
    extends Piece("", false) {
  override val hasPromotion: Boolean = false

  /*Author:   Mert, Nick
  * Role:     Stuft das Piece auf
  * Return:   Gibt das Promotete Piece zurueck*/
  override def promotePiece: Option[PieceInterface] = {
    None
  }

  override def toString: String = "   "

  override def toStringLong: String = "EmptyPiece"

  /*Author:   Mert, Nick
  * Role:     Gibt die moeglichen Bewegungsfelder zurueck
  * Return:   List[(Int, Int)] mit den Koordinaten */
  override def getMoveSet(pos: (Int, Int), board: BoardInterface): List[(Int, Int)] = {
    List()
  }

  override def cloneToNewPlayer(first: Boolean): EmptyPiece = EmptyPiece()
}

//endregion