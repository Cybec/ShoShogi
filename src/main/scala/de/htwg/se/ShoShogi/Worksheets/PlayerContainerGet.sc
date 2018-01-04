import de.htwg.se.ShoShogi.model._


val board = new Board(9, new EmptyPiece)




var containerPlayer_0: List[Piece] = List[Piece]()
var containerPlayer_1: List[Piece] = List[Piece]()
val player_0 = Player("Nick", true)
val player_1 = Player("Mert", false)

containerPlayer_0 = containerPlayer_0 :+ Lancer(player_1)
containerPlayer_0 = containerPlayer_0 :+ Lancer(player_1)
containerPlayer_0 = containerPlayer_0 :+ Lancer(player_1)
containerPlayer_0 = containerPlayer_0 :+ King(player_1)
containerPlayer_0 = containerPlayer_0 :+ King(player_1)
containerPlayer_1 = containerPlayer_1 :+ Lancer(player_0)
containerPlayer_1 = containerPlayer_1 :+ King(player_0)

println(containerPlayer_0)




getFromPlayerContainer(containerPlayer_0, player_0) {
  _.isInstanceOf[King]
} match {
  case Some((list, piece)) =>
    containerPlayer_0 = list
    println(containerPlayer_0)
    println(piece)
  case _ =>
}

getFromPlayerContainer(containerPlayer_0, player_0) {
  _.isInstanceOf[Lancer]
} match {
  case Some((list, piece)) =>
    containerPlayer_0 = list
    println(containerPlayer_0)
    println(piece)
  case _ =>
}

getFromPlayerContainer(containerPlayer_0, player_0) {
  _.isInstanceOf[Pawn]
} match {
  case Some((list, piece)) =>
    containerPlayer_0 = list
    println(containerPlayer_0)
    println(piece)
  case _ =>
}




def getFromPlayerContainer(list: List[Piece], player: Player)(pred: (Piece) => Boolean): Option[(List[Piece], Piece)] = {
  def buildNewList(before: List[Piece], atAndAfter: List[Piece]): Option[(List[Piece], Piece)] ={
    if (atAndAfter.size > 0) {
      val getPiece = atAndAfter(0)
      val newCon = before ::: atAndAfter.drop(1)
      Some((newCon, getPiece))
    } else {
      None
    }
  }

  if (player.first) {
    val (before, atAndAfter) = list span (x => !pred(x))
    buildNewList(before, atAndAfter)
  } else {
    val (before, atAndAfter) = list span (x => !pred(x))
    buildNewList(before, atAndAfter)
  }
}