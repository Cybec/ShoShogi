package de.htwg.se.ShoShogi.model.pieceComponent.pieceBaseImpl

import de.htwg.se.ShoShogi.model.pieceComponent.PieceInterface

object PiecesEnum extends Enumeration {
  type EnumType = Value
  val King, GoldenGeneral, SilverGeneral, PromotedSilver, Knight = Value
  val PromotedKnight, Lancer, PromotedLancer, Bishop, PromotedBishop = Value
  val Rook, PromotedRook, Pawn, PromotedPawn, EmptyPiece = Value

  def withNameOpt(s: String): Option[Value] = values.find(_.toString == s)
}

//noinspection ScalaStyle
object PieceFactory {
  def apply(pieceType: PiecesEnum.Value, isFirstOwner: Boolean): PieceInterface = pieceType match {
    case PiecesEnum.King => King(isFirstOwner)
    case PiecesEnum.GoldenGeneral => GoldenGeneral(isFirstOwner)
    case PiecesEnum.SilverGeneral => SilverGeneral(isFirstOwner)
    case PiecesEnum.PromotedSilver => PromotedSilver(isFirstOwner)
    case PiecesEnum.Knight => Knight(isFirstOwner)
    case PiecesEnum.PromotedKnight => PromotedKnight(isFirstOwner)
    case PiecesEnum.Lancer => Lancer(isFirstOwner)
    case PiecesEnum.PromotedLancer => PromotedLancer(isFirstOwner)
    case PiecesEnum.Bishop => Bishop(isFirstOwner)
    case PiecesEnum.PromotedBishop => PromotedBishop(isFirstOwner)
    case PiecesEnum.Rook => Rook(isFirstOwner)
    case PiecesEnum.PromotedRook => PromotedRook(isFirstOwner)
    case PiecesEnum.Pawn => Pawn(isFirstOwner)
    case PiecesEnum.PromotedPawn => PromotedPawn(isFirstOwner)
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

