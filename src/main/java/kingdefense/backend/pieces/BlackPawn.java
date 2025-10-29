package kingdefense.backend.pieces;

import java.util.ArrayList;

import kingdefense.backend.board.Board;
import kingdefense.backend.logic.PathFindingTile;

public class BlackPawn extends BlackPiece {
    public BlackPawn(Integer x, Integer y, Integer health, Integer attack) {
        super(x, y, health, attack);
        this.priority = 1;
    }
    public BlackPawn(Integer health, Integer attack) {
        this(0, 0, health, attack);
    }
    public BlackPawn() {
        this(0, 0, 1, 1);
    }

    public String getPieceType() {
        return "BlackPawn";
    }

	public boolean canMove(Board board) {
        if (x + 1 < 8 && board.isEmpty(x + 1, y))
            return true;
        if (x - 1 >= 0 && board.isEmpty(x - 1, y))
            return true;
        if (y + 1 < 8 && board.isEmpty(x, y + 1))
            return true;
        if (y - 1 >= 0 && board.isEmpty(x, y - 1))
            return true;
        return false;
	}

    public ArrayList<PathFindingTile> getNeighbors(PathFindingTile tile) {
        ArrayList<PathFindingTile> neighbors = new ArrayList<>();
        if (!(tile.getTileX() + 1 > 7))
            neighbors.add(new PathFindingTile(tile.getTileX() + 1, tile.getTileY()));
        if (!(tile.getTileX() - 1 < 0))
            neighbors.add(new PathFindingTile(tile.getTileX() - 1, tile.getTileY()));
        if (!(tile.getTileY() + 1 > 7))
            neighbors.add(new PathFindingTile(tile.getTileX(), tile.getTileY() + 1));
        if (!(tile.getTileY() - 1 > 7))
            neighbors.add(new PathFindingTile(tile.getTileX(), tile.getTileY() - 1));
        return neighbors;
    }
}
