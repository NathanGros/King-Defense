package kingdefense.backend.pieces;

import java.util.ArrayList;

import kingdefense.frontend.ui.WaveBox;

public class BlackKing {
    private Integer x;
    private Integer y;
    private ArrayList<BlackPiece> stockPieces;

	public BlackKing(Integer x, Integer y) {
        this.x = x;
        this.y = y;
        stockPieces = new ArrayList<>();
    }
	public BlackKing() {
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
	public ArrayList<BlackPiece> getStockPieces() {
		return stockPieces;
	}
	public void addStockPiece(BlackPiece stockPiece, WaveBox frontWaveBox) {
		this.stockPieces.add(stockPiece);
        frontWaveBox.addBlackPiece(stockPiece);
	}
	public BlackPiece popStockPiece(WaveBox frontWaveBox) {
        BlackPiece nextPiece = stockPieces.getFirst();
		stockPieces.removeFirst();
        frontWaveBox.removeFirst();
        return nextPiece;
	}

    @Override
    public String toString() {
        return "BlackKing" + ", x:" + x + ", y:" + y;
    }
}
