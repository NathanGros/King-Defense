package kingdefense.backend.pieces;

import kingdefense.backend.Game;
import kingdefense.backend.board.Board;

public abstract class WhitePiece {
    protected Integer x;
    protected Integer y;

    public WhitePiece(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }

	public Integer getX() {
		return x;
	}
	public void setX(Integer x) {
		this.x = x;
	}
	public Integer getY() {
		return y;
	}
	public void setY(Integer y) {
		this.y = y;
	}
    public abstract String getPieceType();

    public abstract void activate(Board board, Game game);

    @Override
    public String toString() {
        return this.getPieceType() + ", x:" + x + ", y:" + y;
    }
}

