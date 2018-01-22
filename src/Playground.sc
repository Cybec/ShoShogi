import de.htwg.se.ShoShogi.model.boardComponent.BoardInterface

def cellToXml(board: BoardInterface, row: Int, col: Int) = {
  <cell
  row={row.toString}
  col={col.toString}>
  </cell>
}
