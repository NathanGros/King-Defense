package kingdefense.backend.pieces;

import java.util.ArrayList;

import kingdefense.backend.board.*;

public class BlackBishop extends BlackPiece {
	public BlackBishop(Float health, Integer attack) {
		super(health, attack);
        this.priority = 4;
        this.coinDropNb = 3;
	}
    public BlackBishop() {
        this(3.f, 3);
    }

	@Override
	public ArrayList<PathFindingTile> getNeighbors(Board board, PathFindingTile tile) {
        int x = tile.getX();
        int y = tile.getY();
        ArrayList<Tile> attainableTiles = new ArrayList<>();
        for (int i = x-1, j = y-1; i >= 0 && j >= 0 && !board.isWhite(new Tile(i, j)); i--, j--) {
            attainableTiles.add(new Tile(i, j));
        }
        for (int i = x-1, j = y+1; i >= 0 && j < 8 && !board.isWhite(new Tile(i, j)); i--, j++) {
            attainableTiles.add(new Tile(i, j));
        }
        for (int i = x+1, j = y-1; i < 8 && j >= 0 && !board.isWhite(new Tile(i, j)); i++, j--) {
            attainableTiles.add(new Tile(i, j));
        }
        for (int i = x+1, j = y+1; i < 8 && j < 8 && !board.isWhite(new Tile(i, j)); i++, j++) {
            attainableTiles.add(new Tile(i, j));
        }
        attainableTiles = new ArrayList<>(attainableTiles.reversed());
        if (!board.isWhite(new Tile(x + 1, y))) {
            attainableTiles.add(new Tile(x + 1, y));
        }
        if (!board.isWhite(new Tile(x - 1, y))) {
            attainableTiles.add(new Tile(x - 1, y));
        }
        if (!board.isWhite(new Tile(x, y + 1))) {
            attainableTiles.add(new Tile(x, y + 1));
        }
        if (!board.isWhite(new Tile(x, y - 1))) {
            attainableTiles.add(new Tile(x, y - 1));
        }
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
        for (int i = x-1, j = y-1; i >= 0 && j >= 0 && board.isEmpty(new Tile(i, j)); i--, j--) {
            attainableTiles.add(new Tile(i, j));
        }
        for (int i = x-1, j = y+1; i >= 0 && j < 8 && board.isEmpty(new Tile(i, j)); i--, j++) {
            attainableTiles.add(new Tile(i, j));
        }
        for (int i = x+1, j = y-1; i < 8 && j >= 0 && board.isEmpty(new Tile(i, j)); i++, j--) {
            attainableTiles.add(new Tile(i, j));
        }
        for (int i = x+1, j = y+1; i < 8 && j < 8 && board.isEmpty(new Tile(i, j)); i++, j++) {
            attainableTiles.add(new Tile(i, j));
        }
        attainableTiles = new ArrayList<>(attainableTiles.reversed());
        if (board.isEmpty(new Tile(x + 1, y))) {
            attainableTiles.add(new Tile(x + 1, y));
        }
        if (board.isEmpty(new Tile(x - 1, y))) {
            attainableTiles.add(new Tile(x - 1, y));
        }
        if (board.isEmpty(new Tile(x, y + 1))) {
            attainableTiles.add(new Tile(x, y + 1));
        }
        if (board.isEmpty(new Tile(x, y - 1))) {
            attainableTiles.add(new Tile(x, y - 1));
        }
        ArrayList<PathFindingTile> neighbors = new ArrayList<>();
        for (int i = 0; i < attainableTiles.size(); i++) {
            neighbors.add(new PathFindingTile(attainableTiles.get(i)));
        }
        return neighbors;
	}

	@Override
	public String getPieceType() {
        return "BlackBishop";
	}
}
