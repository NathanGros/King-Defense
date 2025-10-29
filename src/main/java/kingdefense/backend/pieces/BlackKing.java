package kingdefense.backend.pieces;

import java.util.ArrayList;

public class BlackKing {
    private Integer x;
    private Integer y;
    private ArrayList<BlackPiece> stockPieces;

	public BlackKing(Integer x, Integer y, Integer orientation) {
        this.x = x;
        this.y = y;
        stockPieces = new ArrayList<>();
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
	public ArrayList<BlackPiece> getStockPieces() {
		return stockPieces;
	}
	public void addStockPiece(BlackPiece stockPiece) {
		this.stockPieces.add(stockPiece);
	}
	public BlackPiece popStockPiece() {
        BlackPiece nextPiece = stockPieces.getFirst();
		stockPieces.removeFirst();
        return nextPiece;
	}

    @Override
    public String toString() {
        return "BlackKing" + ", x:" + x + ", y:" + y;
    }
}
