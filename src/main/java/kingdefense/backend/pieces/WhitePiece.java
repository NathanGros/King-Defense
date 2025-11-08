package kingdefense.backend.pieces;

import kingdefense.backend.Game;
import kingdefense.backend.board.*;

public abstract class WhitePiece {
    protected Tile tile;
    protected Float queenBoost;
    protected Float queenNerf;

	public WhitePiece(Integer x, Integer y) {
        this.tile = new Tile(x, y);
        queenBoost = 0.f;
        queenNerf = 0.f;
    }
    public WhitePiece() {
        this(0, 0);
    }

	public Tile getTile() {
		return tile;
	}
	public void setTile(Tile tile) {
		this.tile = tile;
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
            int pieceX = blackQueen.getTile().getX();
            int pieceY = blackQueen.getTile().getY();
            if (pieceX >= tile.getX() - 1 && pieceX <= tile.getX() + 1 && pieceY >= tile.getY() - 1 && pieceY <= tile.getY() + 1) {
                addQueenNerf(blackQueen.getNerf());
            }
        }
        if (queenNerf > 1.f)
            queenNerf = 1.f;
    }

    @Override
    public String toString() {
        return this.getPieceType() + tile;
    }
}
