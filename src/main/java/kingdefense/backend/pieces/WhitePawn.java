package kingdefense.backend.pieces;

import kingdefense.backend.Game;
import kingdefense.backend.board.Board;

public class WhitePawn extends WhitePiece {
    public WhitePawn(Integer x, Integer y) {
        super(x, y);
    }

	@Override
	public String getPieceType() {
        return "WhitePawn";
	}

	@Override
	public void activate(Board board, Game game) {
	}
}
