package kingdefense.backend.pieces;

import java.util.ArrayList;
import java.util.Collections;

import kingdefense.backend.board.*;

public class BlackPawn extends BlackPiece {
    public BlackPawn(Float health, Integer attack) {
        super(health, attack);
        this.priority = 1;
        this.coinDropNb = 1;
    }
    public BlackPawn() {
        this(1.f, 1);
    }

    private ArrayList<Tile> getAttainableTiles(Tile tile) {
        ArrayList<Tile> attainableTiles = new ArrayList<>();
        attainableTiles.add(tile.addWithTile(new Tile(1, 0)));
        attainableTiles.add(tile.addWithTile(new Tile(-1, 0)));
        attainableTiles.add(tile.addWithTile(new Tile(0, 1)));
        attainableTiles.add(tile.addWithTile(new Tile(0, -1)));
        Collections.shuffle(attainableTiles);
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
        return "BlackPawn";
    }
}
