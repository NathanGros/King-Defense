package kingdefense.backend.pieces;

import kingdefense.backend.Game;
import kingdefense.backend.board.Board;
import kingdefense.backend.board.Tile;

public class WhiteQueen extends WhitePiece {
    private Float boost;

    public WhiteQueen(Integer x, Integer y, Float boost) {
        super(x, y);
        this.boost = boost;
    }
    public WhiteQueen(Integer x, Integer y) {
        this(x, y, 0.5f);
    }
    public WhiteQueen() {
        this(0, 0);
    }

	@Override
	public String getPieceType() {
        return "WhiteQueen";
	}

	@Override
	public void activate(Board board, Game game) {
        int x = tile.getX();
        int y = tile.getY();
        // Straights
        for (int i = x-1; i >= 0; i--) {
            board.queenBoostWhiteAtPos(new Tile(i, y), boost * (1.f - queenNerf));
        }
        for (int i = x+1; i < 8; i++) {
            board.queenBoostWhiteAtPos(new Tile(i, y), boost * (1.f - queenNerf));
        }
        for (int i = y-1; i >= 0; i--) {
            board.queenBoostWhiteAtPos(new Tile(x, i), boost * (1.f - queenNerf));
        }
        for (int i = y+1; i < 8; i++) {
            board.queenBoostWhiteAtPos(new Tile(x, i), boost * (1.f - queenNerf));
        }
        // Diagonals
        for (int i = x-1, j = y-1; i >= 0 && j >= 0; i--, j--) {
            board.queenBoostWhiteAtPos(new Tile(i, j), boost * (1.f - queenNerf));
        }
        for (int i = x-1, j = y+1; i >= 0 && j < 8; i--, j++) {
            board.queenBoostWhiteAtPos(new Tile(i, j), boost * (1.f - queenNerf));
        }
        for (int i = x+1, j = y-1; i < 8 && j >= 0; i++, j--) {
            board.queenBoostWhiteAtPos(new Tile(i, j), boost * (1.f - queenNerf));
        }
        for (int i = x+1, j = y+1; i < 8 && j < 8; i++, j++) {
            board.queenBoostWhiteAtPos(new Tile(i, j), boost * (1.f - queenNerf));
        }
	}
}
