package kingdefense.backend.pieces;

import kingdefense.backend.Game;
import kingdefense.backend.board.Board;

public class WhitePawn extends WhitePiece {
    public WhitePawn(Integer x, Integer y) {
        super(x, y);
    }
    public WhitePawn() {
        this(0, 0);
    }

	@Override
	public String getPieceType() {
        return "WhitePawn";
	}

	@Override
	public void activate(Board board, Game game) {
	}
}
