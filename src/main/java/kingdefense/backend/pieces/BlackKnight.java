package kingdefense.backend.pieces;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import kingdefense.backend.board.*;

public class BlackKnight extends BlackPiece {
    public BlackKnight(Float health, Integer attack) {
        super(health, attack);
        this.priority = 2;
        this.coinDropNb = 3;
    }
    public BlackKnight() {
        this(3.f, 3);
    }

    private ArrayList<Tile> getAttainableTiles(Tile tile) {
        ArrayList<Tile> attainableTiles = new ArrayList<>();
        attainableTiles.add(tile.addWithTile(new Tile(2, 1)));
        attainableTiles.add(tile.addWithTile(new Tile(2, -1)));
        attainableTiles.add(tile.addWithTile(new Tile(-2, 1)));
        attainableTiles.add(tile.addWithTile(new Tile(-2, -1)));
        attainableTiles.add(tile.addWithTile(new Tile(1, 2)));
        attainableTiles.add(tile.addWithTile(new Tile(-1, 2)));
        attainableTiles.add(tile.addWithTile(new Tile(1, -2)));
        attainableTiles.add(tile.addWithTile(new Tile(-1, -2)));
        Collections.shuffle(attainableTiles);
        ArrayList<Tile> attainableFallbackTiles = new ArrayList<>();
        attainableFallbackTiles.add(tile.addWithTile(new Tile(1, 0)));
        attainableFallbackTiles.add(tile.addWithTile(new Tile(-1, 0)));
        attainableFallbackTiles.add(tile.addWithTile(new Tile(0, 1)));
        attainableFallbackTiles.add(tile.addWithTile(new Tile(0, -1)));
        Collections.shuffle(attainableFallbackTiles);
        attainableTiles.addAll(attainableFallbackTiles);
        return attainableTiles;
    }

    public ArrayList<PathFindingTile> getNeighbors(Board board, PathFindingTile tile) {
        ArrayList<Tile> attainableTiles = getAttainableTiles(tile);
        ArrayList<PathFindingTile> neighbors = new ArrayList<>();
        for (int i = 0; i < attainableTiles.size(); i++) {
            if (board.isInBound(attainableTiles.get(i)))
                neighbors.add(new PathFindingTile(attainableTiles.get(i)));
        }
        return neighbors;
    }

    public ArrayList<PathFindingTile> getEmptyNeighbors(Board board, PathFindingTile tile) {
        ArrayList<Tile> attainableTiles = getAttainableTiles(tile);
        ArrayList<PathFindingTile> neighbors = new ArrayList<>();
        for (int i = 0; i < attainableTiles.size(); i++) {
            if (board.isEmpty(attainableTiles.get(i)))
                neighbors.add(new PathFindingTile(attainableTiles.get(i)));
        }
        return neighbors;
    }

	@Override
	public String getPieceType() {
        return "BlackKnight";
	}
}
