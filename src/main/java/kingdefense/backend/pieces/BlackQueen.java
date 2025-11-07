package kingdefense.backend.pieces;

import java.util.ArrayList;

import kingdefense.backend.board.Board;
import kingdefense.backend.logic.PathFindingTile;

public class BlackQueen extends BlackPiece {
    private Float nerf;

	public BlackQueen(Integer x, Integer y, Float health, Integer attack) {
		super(x, y, health, attack);
        this.priority = 6;
        this.coinDropNb = 10;
        this.nerf = 0.2f;
	}
    public BlackQueen(Float health, Integer attack) {
        this(0, 0, health, attack);
    }
    public BlackQueen() {
        this(0, 0, 10.f, 10);
	}

    public Float getNerf() {
        return nerf;
    }

	@Override
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
        ArrayList<PathFindingTile> neighbors = new ArrayList<>();
        for (int i = 0; i < attainableX.size(); i++) {
            neighbors.add(new PathFindingTile(attainableX.get(i), attainableY.get(i)));
        }
        return neighbors;
	}

	@Override
	public String getPieceType() {
        return "BlackQueen";
	}
}
