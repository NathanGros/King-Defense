package kingdefense.backend.pieces;

import kingdefense.backend.Game;
import kingdefense.backend.board.Board;
import kingdefense.backend.board.Tile;

public class WhiteBishop extends WhitePiece {
    private Float poisonDamage;
    private Integer poisonDuration;

    public WhiteBishop(Integer x, Integer y, Float poisonDamage, Integer poisonDuration) {
        super(x, y);
        this.poisonDamage = poisonDamage;
        this.poisonDuration = poisonDuration;
    }
    public WhiteBishop(Integer x, Integer y) {
        this(x, y, 1.f, 2);
    }
    public WhiteBishop(Float poisonDamage, Integer poisonDuration) {
        this(0, 0, poisonDamage, poisonDuration);
    }
    public WhiteBishop() {
        this(0, 0);
    }

	@Override
	public String getPieceType() {
        return "WhiteBishop";
	}

	@Override
	public void activate(Board board, Game game) {
        int x = tile.getX();
        int y = tile.getY();
        for (int i = x-1, j = y-1; i >= 0 && j >= 0 && !board.isWhite(new Tile(i, j)); i--, j--) {
            if (board.isEmpty(new Tile(i, j)))
                continue;
            board.poisonBlackAtPos(new Tile(i, j), poisonDamage * (1.f + queenBoost - queenNerf), poisonDuration);
            break;
        }
        for (int i = x-1, j = y+1; i >= 0 && j < 8 && !board.isWhite(new Tile(i, j)); i--, j++) {
            if (board.isEmpty(new Tile(i, j)))
                continue;
            board.poisonBlackAtPos(new Tile(i, j), poisonDamage * (1.f + queenBoost - queenNerf), poisonDuration);
            break;
        }
        for (int i = x+1, j = y-1; i < 8 && j >= 0 && !board.isWhite(new Tile(i, j)); i++, j--) {
            if (board.isEmpty(new Tile(i, j)))
                continue;
            board.poisonBlackAtPos(new Tile(i, j), poisonDamage * (1.f + queenBoost - queenNerf), poisonDuration);
            break;
        }
        for (int i = x+1, j = y+1; i < 8 && j < 8 && !board.isWhite(new Tile(i, j)); i++, j++) {
            if (board.isEmpty(new Tile(i, j)))
                continue;
            board.poisonBlackAtPos(new Tile(i, j), poisonDamage * (1.f + queenBoost - queenNerf), poisonDuration);
            break;
        }
	}
}
