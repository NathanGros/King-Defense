package kingdefense.backend.pieces;

import java.util.ArrayList;

import kingdefense.backend.board.Board;
import kingdefense.backend.logic.PathFindingTile;

public abstract class BlackPiece {
    protected Integer x;
    protected Integer y;
    protected Integer health;
    protected Integer attack;
    protected Integer priority;
	protected boolean hasMoved;
    private Integer targetX;
    private Integer targetY;
    private Integer shortestPathLength;

	public BlackPiece(Integer x, Integer y, Integer health, Integer attack) {
        this.x = x;
        this.y = y;
        this.health = health;
        this.attack = attack;
        this.hasMoved = false;
        this.targetX = -1;
        this.targetY = -1;
        this.shortestPathLength = -1;
    }

	public void setHealth(Integer health) {
		this.health = health;
	}
	public Integer getX() {
		return x;
	}
	public void setX(Integer x) {
		this.x = x;
	}
	public Integer getY() {
		return y;
	}
	public void setY(Integer y) {
		this.y = y;
	}
	public Integer getHealth() {
		return health;
	}
	public void damage(Integer damage) {
		health -= damage;
	}
    public Integer getAttack() {
		return attack;
	}
	public void setAttack(Integer attack) {
		this.attack = attack;
	}
    public Integer getPriority() {
		return priority;
	}
	public void setPriority(Integer priority) {
		this.priority = priority;
	}
	public boolean hasMoved() {
		return hasMoved;
	}
	public void setHasMoved(boolean hasMoved) {
		this.hasMoved = hasMoved;
	}
	public void setHasMoved() {
		this.hasMoved = true;
	}
	public Integer getTargetX() {
		return targetX;
	}
	public void setTargetX(Integer targetX) {
		this.targetX = targetX;
	}
	public Integer getTargetY() {
		return targetY;
	}
	public void setTargetY(Integer targetY) {
		this.targetY = targetY;
	}
	public Integer getShortestPathLength() {
		return shortestPathLength;
	}
	public void setShortestPathLength(Integer shortestPathLength) {
		this.shortestPathLength = shortestPathLength;
	}


    public abstract String getPieceType();

    public abstract boolean canMove(Board board);

    public abstract ArrayList<PathFindingTile> getNeighbors(PathFindingTile tile);

    public void move(Integer x, Integer y) {
        this.x = x;
        this.y = y;
        setHasMoved();
    }

    public void move() {
        move(targetX, targetY);
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

    @Override
    public String toString() {
        return this.getPieceType() + ", x:" + x + ", y:" + y;
    }
}
