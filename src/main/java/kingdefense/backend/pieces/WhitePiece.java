package kingdefense.backend.pieces;

import kingdefense.backend.Game;
import kingdefense.backend.board.Board;

public abstract class WhitePiece {
    protected Integer x;
    protected Integer y;
    protected Float queenBoost;
    protected Float queenNerf;

	public WhitePiece(Integer x, Integer y) {
        this.x = x;
        this.y = y;
        queenBoost = 0.f;
        queenNerf = 0.f;
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
    public Float getQueenNerf() {
		return queenNerf;
	}
	public void setQueenNerf(Float queenNerf) {
		this.queenNerf = queenNerf;
	}
	public void addQueenNerf(Float queenNerf) {
		this.queenNerf += queenNerf;
	}
    public abstract String getPieceType();

    public abstract void activate(Board board, Game game);

    public void setQueenNerfs(Board board) {
        for (BlackPiece blackPiece: board.getBlackPieces()) {
            if (!blackPiece.getPieceType().equals("BlackQueen"))
                continue;
            BlackQueen blackQueen = (BlackQueen) blackPiece;
            int pieceX = blackQueen.getX();
            int pieceY = blackQueen.getY();
            if (pieceX >= x - 1 && pieceX <= x + 1 && pieceY >= y - 1 && pieceY <= y + 1) {
                addQueenNerf(blackQueen.getNerf());
            }
        }
        if (queenNerf > 1.f)
            queenNerf = 1.f;
    }

    @Override
    public String toString() {
        return this.getPieceType() + ", x:" + x + ", y:" + y;
    }
}
