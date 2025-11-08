package kingdefense.backend.pieces;

import java.util.ArrayList;

import kingdefense.backend.board.Tile;
import kingdefense.frontend.ui.WaveBox;

public class BlackKing {
    private Tile tile;
    private ArrayList<BlackPiece> stockPieces;

	public BlackKing(Integer x, Integer y) {
        this.tile = new Tile(x, y);
        stockPieces = new ArrayList<>();
    }
	public BlackKing() {
        this(0, 0);
    }

	public Tile getTile() {
		return tile;
	}
	public void setTile(Tile tile) {
		this.tile = tile;
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
        return "BlackKing" + tile;
    }
}
