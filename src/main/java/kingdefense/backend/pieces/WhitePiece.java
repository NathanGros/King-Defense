package kingdefense.backend.pieces;

import kingdefense.backend.Game;
import kingdefense.backend.board.Board;

public abstract class WhitePiece {
    protected Integer x;
    protected Integer y;
    protected Float queenBoost;

	public WhitePiece(Integer x, Integer y) {
        this.x = x;
        this.y = y;
        queenBoost = 0.f;
    }
    public WhitePiece() {
        this(0, 0);
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
    public Float getQueenBoost() {
		return queenBoost;
	}
	public void setQueenBoost(Float queenBoost) {
		this.queenBoost = queenBoost;
	}
	public void addQueenBoost(Float queenBoost) {
		this.queenBoost += queenBoost;
	}
    public abstract String getPieceType();

    public abstract void activate(Board board, Game game);

    @Override
    public String toString() {
        return this.getPieceType() + ", x:" + x + ", y:" + y;
    }
}

