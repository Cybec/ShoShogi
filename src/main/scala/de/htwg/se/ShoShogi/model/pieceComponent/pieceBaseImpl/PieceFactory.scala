package de.htwg.se.ShoShogi.model.pieceComponent.pieceBaseImpl

import de.htwg.se.ShoShogi.model.pieceComponent.PieceInterface
import de.htwg.se.ShoShogi.model.playerComponent.Player

object PiecesEnum extends Enumeration {
  type EnumType = Value
  val King, GoldenGeneral, SilverGeneral, PromotedSilver, Knight, PromotedKnight, Lancer, PromotedLancer, Bishop, PromotedBishop, Rook, PromotedRook, Pawn, PromotedPawn, EmptyPiece = Value

  def withNameOpt(s: String): Option[Value] = values.find(_.toString == s)
}

//noinspection ScalaStyle
object PieceFactory {
  def apply(pieceType: PiecesEnum.Value, player: Player): PieceInterface = pieceType match {
    case PiecesEnum.King => new King(player)
    case PiecesEnum.GoldenGeneral => new GoldenGeneral(player)
    case PiecesEnum.SilverGeneral => new SilverGeneral(player)
    case PiecesEnum.PromotedSilver => new PromotedSilver(player)
    case PiecesEnum.Knight => new Knight(player)
    case PiecesEnum.PromotedKnight => new PromotedKnight(player)
    case PiecesEnum.Lancer => new Lancer(player)
    case PiecesEnum.PromotedLancer => new PromotedLancer(player)
    case PiecesEnum.Bishop => new Bishop(player)
    case PiecesEnum.PromotedBishop => new PromotedBishop(player)
    case PiecesEnum.Rook => new Rook(player)
    case PiecesEnum.PromotedRook => new PromotedRook(player)
    case PiecesEnum.Pawn => new Pawn(player)
    case PiecesEnum.PromotedPawn => new PromotedPawn(player)
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
    case PiecesEnum.PromotedBishop => piece.isInstanceOf[PromotedSilver]
    case PiecesEnum.Rook => piece.isInstanceOf[Rook]
    case PiecesEnum.PromotedRook => piece.isInstanceOf[PromotedRook]
    case PiecesEnum.Pawn => piece.isInstanceOf[Pawn]
    case PiecesEnum.PromotedPawn => piece.isInstanceOf[PromotedPawn]
    case PiecesEnum.EmptyPiece => piece.isInstanceOf[EmptyPiece]
  }
}

