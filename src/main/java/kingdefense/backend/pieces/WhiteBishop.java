package kingdefense.backend.pieces;

import kingdefense.backend.Game;
import kingdefense.backend.board.Board;

public class WhiteBishop extends WhitePiece {
    public WhiteBishop(Integer x, Integer y) {
        super(x, y);
    }

	@Override
	public String getPieceType() {
        return "WhiteBishop";
	}

	@Override
	public void activate(Board board, Game game) {
        for (int i = x-1, j = y-1; i >= 0 && j >= 0 && !board.isWhite(i, j); i--, j--) {
            board.poisonBlackAtPos(i, j);
        }
        for (int i = x-1, j = y+1; i >= 0 && j < 8 && !board.isWhite(i, j); i--, j++) {
            board.poisonBlackAtPos(i, j);
        }
        for (int i = x+1, j = y-1; i < 8 && j >= 0 && !board.isWhite(i, j); i++, j--) {
            board.poisonBlackAtPos(i, j);
        }
        for (int i = x+1, j = y+1; i < 8 && j < 8 && !board.isWhite(i, j); i++, j++) {
            board.poisonBlackAtPos(i, j);
        }
	}
}
