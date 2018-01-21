package de.htwg.se.ShoShogi.model.pieceComponent.pieceBaseImpl

import de.htwg.se.ShoShogi.model.pieceComponent.PieceInterface

object PiecesEnum extends Enumeration {
  type EnumType = Value
  val King, GoldenGeneral, SilverGeneral, PromotedSilver, Knight, PromotedKnight, Lancer, PromotedLancer, Bishop, PromotedBishop, Rook, PromotedRook, Pawn, PromotedPawn, EmptyPiece = Value

  def withNameOpt(s: String): Option[Value] = values.find(_.toString == s)
}

//noinspection ScalaStyle
object PieceFactory {
  def apply(pieceType: PiecesEnum.Value, isFirstOwner: Boolean): PieceInterface = pieceType match {
    case PiecesEnum.King => new King(isFirstOwner)
    case PiecesEnum.GoldenGeneral => new GoldenGeneral(isFirstOwner)
    case PiecesEnum.SilverGeneral => new SilverGeneral(isFirstOwner)
    case PiecesEnum.PromotedSilver => new PromotedSilver(isFirstOwner)
    case PiecesEnum.Knight => new Knight(isFirstOwner)
    case PiecesEnum.PromotedKnight => new PromotedKnight(isFirstOwner)
    case PiecesEnum.Lancer => new Lancer(isFirstOwner)
    case PiecesEnum.PromotedLancer => new PromotedLancer(isFirstOwner)
    case PiecesEnum.Bishop => new Bishop(isFirstOwner)
    case PiecesEnum.PromotedBishop => new PromotedBishop(isFirstOwner)
    case PiecesEnum.Rook => new Rook(isFirstOwner)
    case PiecesEnum.PromotedRook => new PromotedRook(isFirstOwner)
    case PiecesEnum.Pawn => new Pawn(isFirstOwner)
    case PiecesEnum.PromotedPawn => new PromotedPawn(isFirstOwner)
    case PiecesEnum.EmptyPiece => getEmptyPiece
  }

  def getEmptyPiece = new EmptyPiece

  def isInstanceOfPiece(pieceType: PiecesEnum.Value, piece: PieceInterface): Boolean = pieceType match {
    case PiecesEnum.King => piece.isInstanceOf[King]
    case PiecesEnum.GoldenGeneral => piece.isInstanceOf[GoldenGeneral]
    case PiecesEnum.SilverGeneral => piece.isInstanceOf[SilverGeneral]
    case PiecesEnum.PromotedSilver => piece.isInstanceOf[PromotedSilver]
    case PiecesEnum.Knight => piece.isInstanceOf[Knight]
    case PiecesEnum.PromotedKnight => piece.isInstanceOf[PromotedKnight]
    case PiecesEnum.Lancer => piece.isInstanceOf[Lancer]
    case PiecesEnum.PromotedLancer => piece.isInstanceOf[PromotedLancer]
    case PiecesEnum.Bishop => piece.isInstanceOf[Bishop]
    case PiecesEnum.PromotedBishop => piece.isInstanceOf[PromotedBishop]
    case PiecesEnum.Rook => piece.isInstanceOf[Rook]
    case PiecesEnum.PromotedRook => piece.isInstanceOf[PromotedRook]
    case PiecesEnum.Pawn => piece.isInstanceOf[Pawn]
    case PiecesEnum.PromotedPawn => piece.isInstanceOf[PromotedPawn]
    case PiecesEnum.EmptyPiece => piece.isInstanceOf[EmptyPiece]
  }
}

