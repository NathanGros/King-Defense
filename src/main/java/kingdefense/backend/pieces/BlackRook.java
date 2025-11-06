package kingdefense.backend.pieces;

import java.util.ArrayList;
import java.util.Arrays;

import kingdefense.backend.board.Board;
import kingdefense.backend.logic.PathFindingTile;

public class BlackRook extends BlackPiece {
	public BlackRook(Integer x, Integer y, Float health, Integer attack) {
		super(x, y, health, attack);
        this.priority = 3;
        this.coinDropNb = 5;
	}
    public BlackRook(Float health, Integer attack) {
        this(0, 0, health, attack);
    }
    public BlackRook() {
        this(0, 0, 5.f, 5);
    }

    public ArrayList<PathFindingTile> getNeighbors(Board board, PathFindingTile tile) {
        int x = tile.getTileX();
        int y = tile.getTileY();
        ArrayList<Integer> attainableX = new ArrayList<>();
        ArrayList<Integer> attainableY = new ArrayList<>();
        for (int i = x-1; i >= 0 && !board.isWhite(i, y); i--) {
            attainableX.add(i);
            attainableY.add(y);
        }
        for (int i = x+1; i < 8 && !board.isWhite(i, y); i++) {
            attainableX.add(i);
            attainableY.add(y);
        }
        for (int i = y-1; i >= 0 && !board.isWhite(x, i); i--) {
            attainableX.add(x);
            attainableY.add(i);
        }
        for (int i = y+1; i < 8 && !board.isWhite(x, i); i++) {
            attainableX.add(x);
            attainableY.add(i);
        }
        attainableX = new ArrayList<>(attainableX.reversed());
        attainableY = new ArrayList<>(attainableY.reversed());
        ArrayList<PathFindingTile> neighbors = new ArrayList<>();
        for (int i = 0; i < attainableX.size(); i++) {
            if (board.isInBound(attainableX.get(i), attainableY.get(i)))
                neighbors.add(new PathFindingTile(attainableX.get(i), attainableY.get(i)));
        }
        return neighbors;

    }

    public ArrayList<PathFindingTile> getEmptyNeighbors(Board board, PathFindingTile tile) {
        int x = tile.getTileX();
        int y = tile.getTileY();
        ArrayList<Integer> attainableX = new ArrayList<>();
        ArrayList<Integer> attainableY = new ArrayList<>();
        for (int i = x-1; i >= 0 && board.isEmpty(i, y); i--) {
            attainableX.add(i);
            attainableY.add(y);
        }
        for (int i = x+1; i < 8 && board.isEmpty(i, y); i++) {
            attainableX.add(i);
            attainableY.add(y);
        }
        for (int i = y-1; i >= 0 && board.isEmpty(x, i); i--) {
            attainableX.add(x);
            attainableY.add(i);
        }
        for (int i = y+1; i < 8 && board.isEmpty(x, i); i++) {
            attainableX.add(x);
            attainableY.add(i);
        }
        attainableX = new ArrayList<>(attainableX.reversed());
        attainableY = new ArrayList<>(attainableY.reversed());
        ArrayList<PathFindingTile> neighbors = new ArrayList<>();
        for (int i = 0; i < attainableX.size(); i++) {
            if (board.isEmpty(attainableX.get(i), attainableY.get(i)))
                neighbors.add(new PathFindingTile(attainableX.get(i), attainableY.get(i)));
        }
        return neighbors;
    }


	@Override
	public String getPieceType() {
        return "BlackRook";
	}
}
