package kingdefense.backend.pieces;

import kingdefense.backend.Game;
import kingdefense.backend.board.Board;
import kingdefense.backend.board.Tile;

public class WhiteRook extends WhitePiece {
    private Float damage;

    public WhiteRook(Integer x, Integer y, Float damage) {
        super(x, y);
        this.damage = damage;
    }
    public WhiteRook(Integer x, Integer y) {
        this(x, y, 5.f);
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
        int x = tile.getX();
        int y = tile.getY();
        for (int i = x-1; i >= 0 && !board.isWhite(new Tile(i, y)); i--) {
            if (board.isEmpty(new Tile(i, y)))
                continue;
            board.shieldDamageBlackAtPos(new Tile(i, y), damage * (1.f + queenBoost - queenNerf));
            break;
        }
        for (int i = x+1; i < 8 && !board.isWhite(new Tile(i, y)); i++) {
            if (board.isEmpty(new Tile(i, y)))
                continue;
            board.shieldDamageBlackAtPos(new Tile(i, y), damage * (1.f + queenBoost - queenNerf));
            break;
        }
        for (int i = y-1; i >= 0 && !board.isWhite(new Tile(x, i)); i--) {
            if (board.isEmpty(new Tile(x, i)))
                continue;
            board.shieldDamageBlackAtPos(new Tile(x, i), damage * (1.f + queenBoost - queenNerf));
            break;
        }
        for (int i = y+1; i < 8 && !board.isWhite(new Tile(x, i)); i++) {
            if (board.isEmpty(new Tile(x, i)))
                continue;
            board.shieldDamageBlackAtPos(new Tile(x, i), damage * (1.f + queenBoost - queenNerf));
            break;
        }
	}
}
