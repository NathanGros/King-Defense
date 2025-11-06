package kingdefense.backend.pieces;

import java.util.ArrayList;

import kingdefense.backend.board.Board;
import kingdefense.backend.logic.PathFindingTile;

public class BlackBishop extends BlackPiece {
	public BlackBishop(Integer x, Integer y, Float health, Integer attack) {
		super(x, y, health, attack);
        this.priority = 4;
        this.coinDropNb = 3;
	}
    public BlackBishop(Float health, Integer attack) {
        this(0, 0, health, attack);
    }
    public BlackBishop() {
        this(0, 0, 3.f, 3);
    }

	@Override
	public ArrayList<PathFindingTile> getNeighbors(Board board, PathFindingTile tile) {
        int x = tile.getTileX();
        int y = tile.getTileY();
        ArrayList<Integer> attainableX = new ArrayList<>();
        ArrayList<Integer> attainableY = new ArrayList<>();
        for (int i = x-1, j = y-1; i >= 0 && j >= 0 && !board.isWhite(i, j); i--, j--) {
            attainableX.add(i);
            attainableY.add(j);
        }
        for (int i = x-1, j = y+1; i >= 0 && j < 8 && !board.isWhite(i, j); i--, j++) {
            attainableX.add(i);
            attainableY.add(j);
        }
        for (int i = x+1, j = y-1; i < 8 && j >= 0 && !board.isWhite(i, j); i++, j--) {
            attainableX.add(i);
            attainableY.add(j);
        }
        for (int i = x+1, j = y+1; i < 8 && j < 8 && !board.isWhite(i, j); i++, j++) {
            attainableX.add(i);
            attainableY.add(j);
        }
        attainableX = new ArrayList<>(attainableX.reversed());
        attainableY = new ArrayList<>(attainableY.reversed());
        if (!board.isWhite(x + 1, y)) {
            attainableX.add(x + 1);
            attainableY.add(y);
        }
        if (!board.isWhite(x - 1, y)) {
            attainableX.add(x - 1);
            attainableY.add(y);
        }
        if (!board.isWhite(x, y + 1)) {
            attainableX.add(x);
            attainableY.add(y + 1);
        }
        if (!board.isWhite(x, y - 1)) {
            attainableX.add(x);
            attainableY.add(y - 1);
        }
        ArrayList<PathFindingTile> neighbors = new ArrayList<>();
        for (int i = 0; i < attainableX.size(); i++) {
            if (board.isInBound(attainableX.get(i), attainableY.get(i)))
                neighbors.add(new PathFindingTile(attainableX.get(i), attainableY.get(i)));
        }
        return neighbors;
	}

	@Override
	public ArrayList<PathFindingTile> getEmptyNeighbors(Board board, PathFindingTile tile) {
        int x = tile.getTileX();
        int y = tile.getTileY();
        ArrayList<Integer> attainableX = new ArrayList<>();
        ArrayList<Integer> attainableY = new ArrayList<>();
        for (int i = x-1, j = y-1; i >= 0 && j >= 0 && board.isEmpty(i, j); i--, j--) {
            attainableX.add(i);
            attainableY.add(j);
        }
        for (int i = x-1, j = y+1; i >= 0 && j < 8 && board.isEmpty(i, j); i--, j++) {
            attainableX.add(i);
            attainableY.add(j);
        }
        for (int i = x+1, j = y-1; i < 8 && j >= 0 && board.isEmpty(i, j); i++, j--) {
            attainableX.add(i);
            attainableY.add(j);
        }
        for (int i = x+1, j = y+1; i < 8 && j < 8 && board.isEmpty(i, j); i++, j++) {
            attainableX.add(i);
            attainableY.add(j);
        }
        attainableX = new ArrayList<>(attainableX.reversed());
        attainableY = new ArrayList<>(attainableY.reversed());
        if (board.isEmpty(x + 1, y)) {
            attainableX.add(x + 1);
            attainableY.add(y);
        }
        if (board.isEmpty(x - 1, y)) {
            attainableX.add(x - 1);
            attainableY.add(y);
        }
        if (board.isEmpty(x, y + 1)) {
            attainableX.add(x);
            attainableY.add(y + 1);
        }
        if (board.isEmpty(x, y - 1)) {
            attainableX.add(x);
            attainableY.add(y - 1);
        }
        ArrayList<PathFindingTile> neighbors = new ArrayList<>();
        for (int i = 0; i < attainableX.size(); i++) {
            neighbors.add(new PathFindingTile(attainableX.get(i), attainableY.get(i)));
        }
        return neighbors;
	}

	@Override
	public String getPieceType() {
        return "BlackBishop";
	}
}
