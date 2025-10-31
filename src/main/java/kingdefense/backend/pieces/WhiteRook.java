package kingdefense.backend.pieces;

import kingdefense.backend.Game;
import kingdefense.backend.board.Board;

public class WhiteRook extends WhitePiece {
    public WhiteRook(Integer x, Integer y) {
        super(x, y);
    }
    public WhiteRook() {
        this(0, 0);
    }

	@Override
	public String getPieceType() {
        return "WhiteRook";
	}

	@Override
	public void activate(Board board, Game game) {
        for (int i = x-1; i >= 0 && !board.isWhite(i, y); i--) {
            board.damageBlackAtPos(i, y, 1.f);
        }
        for (int i = x+1; i < 8 && !board.isWhite(i, y); i++) {
            board.damageBlackAtPos(i, y, 1.f);
        }
        for (int i = y-1; i >= 0 && !board.isWhite(x, i); i--) {
            board.damageBlackAtPos(x, i, 1.f);
        }
        for (int i = y+1; i < 8 && !board.isWhite(x, i); i++) {
            board.damageBlackAtPos(x, i, 1.f);
        }
	}
}
