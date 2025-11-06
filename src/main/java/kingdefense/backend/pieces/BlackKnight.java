package kingdefense.backend.pieces;

import java.util.ArrayList;
import java.util.Arrays;

import kingdefense.backend.board.Board;
import kingdefense.backend.logic.PathFindingTile;

public class BlackKnight extends BlackPiece {
    public BlackKnight(Integer x, Integer y, Float health, Integer attack) {
        super(x, y, health, attack);
        this.priority = 2;
        this.coinDropNb = 3;
    }
    public BlackKnight(Float health, Integer attack) {
        this(0, 0, health, attack);
    }
    public BlackKnight() {
        this(0, 0, 3.f, 3);
    }

    public ArrayList<PathFindingTile> getNeighbors(Board board, PathFindingTile tile) {
        int x = tile.getTileX();
        int y = tile.getTileY();
        ArrayList<Integer> attainableX = new ArrayList<>(Arrays.asList(2, 2, -2, -2, 1, -1, 1, -1, 1, -1, 0, 0));
        ArrayList<Integer> attainableY = new ArrayList<>(Arrays.asList(1, -1, 1, -1, 2, 2, -2, -2, 0, 0, 1, -1));
        ArrayList<PathFindingTile> neighbors = new ArrayList<>();
        for (int i = 0; i < attainableX.size(); i++) {
            if (board.isInBound(x + attainableX.get(i), y + attainableY.get(i)))
                neighbors.add(new PathFindingTile(x + attainableX.get(i), y + attainableY.get(i)));
        }
        return neighbors;
    }

    public ArrayList<PathFindingTile> getEmptyNeighbors(Board board, PathFindingTile tile) {
        int x = tile.getTileX();
        int y = tile.getTileY();
        ArrayList<Integer> attainableX = new ArrayList<>(Arrays.asList(2, 2, -2, -2, 1, -1, 1, -1, 1, -1, 0, 0));
        ArrayList<Integer> attainableY = new ArrayList<>(Arrays.asList(1, -1, 1, -1, 2, 2, -2, -2, 0, 0, 1, -1));
        ArrayList<PathFindingTile> neighbors = new ArrayList<>();
        for (int i = 0; i < attainableX.size(); i++) {
            if (board.isEmpty(x + attainableX.get(i), y + attainableY.get(i)))
                neighbors.add(new PathFindingTile(x + attainableX.get(i), y + attainableY.get(i)));
        }
        return neighbors;
    }

	@Override
	public String getPieceType() {
        return "BlackKnight";
	}
}
