package kingdefense.backend.pieces;

import java.util.ArrayList;

import kingdefense.backend.board.*;

public class BlackQueen extends BlackPiece {
    private Float nerf;

	public BlackQueen(Float health, Integer attack) {
		super(health, attack);
        this.priority = 6;
        this.coinDropNb = 10;
        this.nerf = 0.2f;
	}
    public BlackQueen() {
        this(10.f, 10);
	}

    public Float getNerf() {
        return nerf;
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
        ArrayList<PathFindingTile> neighbors = new ArrayList<>();
        for (int i = 0; i < attainableTiles.size(); i++) {
            neighbors.add(new PathFindingTile(attainableTiles.get(i)));
        }
        return neighbors;
	}

	@Override
	public String getPieceType() {
        return "BlackQueen";
	}
}
