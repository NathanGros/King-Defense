package kingdefense.backend.pieces;

import java.util.ArrayList;

import kingdefense.backend.board.Board;
import kingdefense.backend.logic.PathFindingTile;

public class BlackPawn extends BlackPiece {
    public BlackPawn(Integer x, Integer y, Integer health, Integer attack) {
        super(x, y, health, attack);
        this.priority = 1;
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

    // Find the empty spots around (there is at least one)
    // From there compute shortest path to white king using BFS, made of non white occupied tiles
    // set this.shortestPathLength
    // set this.targetX and this.targetY (the empty tile)
	public void computeShortestPath(Board board) {
        ArrayList<PathFindingTile> initialNeighbors = getNeighbors(new PathFindingTile(this.x, this.y));
        ArrayList<PathFindingTile> startTiles = new ArrayList<>();
        for (PathFindingTile initialNeighbor: initialNeighbors) {
            if (board.isEmpty(initialNeighbor.getTileX(), initialNeighbor.getTileY()))
                startTiles.add(initialNeighbor);
        }
        Integer endTileX = 0;
        Integer endTileY = 0;
        for (WhitePiece whitePiece: board.getWhitePieces()) {
            if (whitePiece.getPieceType().equals("WhiteKing")) {
                endTileX = whitePiece.getX();
                endTileY = whitePiece.getY();
            }
        }
        for (PathFindingTile startTile: startTiles) {
            ArrayList<PathFindingTile> visiting = new ArrayList<>();
            ArrayList<PathFindingTile> visited = new ArrayList<>();
            visited.add(new PathFindingTile(this.x, this.y));
            // Add fake startTiles to the visited list to prevent issues
            for (PathFindingTile startTile2: startTiles) {
                visited.add(new PathFindingTile(startTile2.getTileX(), startTile2.getTileY()));
            }
            visited.getFirst().setDistance(0);
            visiting.add(startTile);
            startTile.setDistance(1);
            while (!visiting.isEmpty()) {
                Integer tileDistance = visiting.getFirst().getDistance();
                ArrayList<PathFindingTile> neighbors = getNeighbors(visiting.getFirst());
                // Mark Tile as visited
                visited.add(visiting.getFirst());
                visiting.remove(0);
                // Add valid neighbors to visiting queue
                for (PathFindingTile neighbor: neighbors) {
                    // End pathfinding for this start tile if white king is a neighbor
                    if (neighbor.getTileX() == endTileX && neighbor.getTileY() == endTileY) {
                        startTile.setDistance(tileDistance + 1);
                        visiting = new ArrayList<>();
                        break;
                    }
                    // Check if neighbor is not a white piece
                    boolean isNeighborValid = true;
                    for (WhitePiece whitePiece: board.getWhitePieces()) {
                        if (neighbor.getTileX() == whitePiece.getX() && neighbor.getTileY() == whitePiece.getY())
                            isNeighborValid = false;
                    }
                    // Check if neighbor is not already visited
                    for (PathFindingTile visitedTile: visited) {
                        if (neighbor.getTileX() == visitedTile.getTileX() && neighbor.getTileY() == visitedTile.getTileY())
                            isNeighborValid = false;
                    }
                    // Add Neighbor to visiting queue
                    if (isNeighborValid) {
                        neighbor.setDistance(tileDistance + 1);
                        visiting.add(neighbor);
                    }
                }
            }
        }
        // Find smallest path among all the initial spots calculated
        Integer smallestPath = startTiles.getFirst().getDistance();
        Integer smallestPathIndex = 0;
        for (int i = 1; i < startTiles.size(); i++) {
            if (startTiles.get(i).getDistance() < smallestPath) {
                smallestPath = startTiles.get(i).getDistance();
                smallestPathIndex = i;
            }
        }
        this.setShortestPathLength(smallestPath);
        this.setTargetX(startTiles.get(smallestPathIndex).getTileX());
        this.setTargetY(startTiles.get(smallestPathIndex).getTileY());
	}
}
