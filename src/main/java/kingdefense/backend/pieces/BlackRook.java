package kingdefense.backend.pieces;

import java.util.ArrayList;
import java.util.Collections;

import kingdefense.backend.board.*;

public class BlackRook extends BlackPiece {
	public BlackRook(Float health, Integer attack) {
		super(health, attack);
        this.priority = 5;
        this.coinDropNb = 5;
	}
    public BlackRook() {
        this(5.f, 5);
    }

	@Override
    public ArrayList<PathFindingTile> getNeighbors(Board board, PathFindingTile tile) {
        int x = tile.getX();
        int y = tile.getY();
        ArrayList<Tile> attainableTiles = new ArrayList<>();
        for (int i = x-1; i >= 0 && !board.isWhite(new Tile(i, y)); i--) {
            attainableTiles.add(new Tile(i, y));
        }
        for (int i = x+1; i < 8 && !board.isWhite(new Tile(i, y)); i++) {
            attainableTiles.add(new Tile(i, y));
        }
        for (int i = y-1; i >= 0 && !board.isWhite(new Tile(x, i)); i--) {
            attainableTiles.add(new Tile(x, i));
        }
        for (int i = y+1; i < 8 && !board.isWhite(new Tile(x, i)); i++) {
            attainableTiles.add(new Tile(x, i));
        }
        Collections.shuffle(attainableTiles);
        ArrayList<PathFindingTile> neighbors = new ArrayList<>();
        for (int i = 0; i < attainableTiles.size(); i++) {
            if (board.isInBound(attainableTiles.get(i)))
                neighbors.add(new PathFindingTile(attainableTiles.get(i)));
        }
        return neighbors;
    }

	@Override
    public ArrayList<PathFindingTile> getEmptyNeighbors(Board board, PathFindingTile tile) {
        int x = tile.getX();
        int y = tile.getY();
        ArrayList<Tile> attainableTiles = new ArrayList<>();
        for (int i = x-1; i >= 0 && board.isEmpty(new Tile(i, y)); i--) {
            attainableTiles.add(new Tile(i, y));
        }
        for (int i = x+1; i < 8 && board.isEmpty(new Tile(i, y)); i++) {
            attainableTiles.add(new Tile(i, y));
        }
        for (int i = y-1; i >= 0 && board.isEmpty(new Tile(x, i)); i--) {
            attainableTiles.add(new Tile(x, i));
        }
        for (int i = y+1; i < 8 && board.isEmpty(new Tile(x, i)); i++) {
            attainableTiles.add(new Tile(x, i));
        }
        Collections.shuffle(attainableTiles);
        ArrayList<PathFindingTile> neighbors = new ArrayList<>();
        for (int i = 0; i < attainableTiles.size(); i++) {
            neighbors.add(new PathFindingTile(attainableTiles.get(i)));
        }
        return neighbors;
    }

	@Override
	public String getPieceType() {
        return "BlackRook";
	}
}
