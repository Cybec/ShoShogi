import de.htwg.se.ShoShogi.ShoShogi.boardSize
import de.htwg.se.ShoShogi.model._

val player_1 = Player("Nick", true)
val player_2 = Player("Mert", false)

var board = new Board(boardSize, new EmptyPiece)

//Steine fuer Spieler 1
board = board.replaceCell(0, 0, Lancer(player_1))
board = board.replaceCell(1, 0, Knight(player_1))
board = board.replaceCell(2, 0, SilverGeneral(player_1))
board = board.replaceCell(3, 0, GoldenGeneral(player_1))
board = board.replaceCell(4, 0, King(player_1))
board = board.replaceCell(5, 0, GoldenGeneral(player_1))
board = board.replaceCell(6, 0, SilverGeneral(player_1))
board = board.replaceCell(7, 0, Knight(player_1))
board = board.replaceCell(8, 0, Lancer(player_1))
board = board.replaceCell(7, 1, Bishop(player_1))
board = board.replaceCell(1, 1, Rook(player_1))
for (i <- 0 to 8) {
  board = board.replaceCell(i, 2, Pawn(player_1))
}

//Steine fuer Spieler 2
board = board.replaceCell(0, 8, Lancer(player_2))
board = board.replaceCell(1, 8, Knight(player_2))
board = board.replaceCell(2, 8, SilverGeneral(player_2))
board = board.replaceCell(3, 8, GoldenGeneral(player_2))
board = board.replaceCell(4, 8, King(player_2))
board = board.replaceCell(5, 8, GoldenGeneral(player_2))
board = board.replaceCell(6, 8, SilverGeneral(player_2))
board = board.replaceCell(7, 8, Knight(player_2))
board = board.replaceCell(8, 8, Lancer(player_2))
board = board.replaceCell(1, 7, Bishop(player_2))
board = board.replaceCell(7, 7, Rook(player_2))
for (i <- 0 to 8) {
  board = board.replaceCell(i, 6, Pawn(player_2))
}


println(board)

!board.getPiecesInColumn(0, false).exists(x => x.typeEquals("P"))



for (i <- 0 to 7) {
  println(
    i
  )
}
