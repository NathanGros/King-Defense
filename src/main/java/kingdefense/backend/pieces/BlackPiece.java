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
    protected Integer targetX;
    protected Integer targetY;
    protected Integer shortestPathLength;
    protected ArrayList<Integer> accessibleX;
	protected ArrayList<Integer> accessibleY;
    protected Integer accessibleNb;

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
	public void setAccessibleX(ArrayList<Integer> accessibleX) {
		this.accessibleX = accessibleX;
	}
	public void setAccessibleY(ArrayList<Integer> accessibleY) {
		this.accessibleY = accessibleY;
	}

    public abstract String getPieceType();

	public boolean canMove(Board board) {
        for (int i = 0; i < accessibleNb; i++) {
            if (board.isEmpty(x + accessibleX.get(i), y + accessibleY.get(i)))
                return true;
        }
        return false;
	}

    public ArrayList<PathFindingTile> getNeighbors(Board board, PathFindingTile tile) {
        int x = tile.getTileX();
        int y = tile.getTileY();
        ArrayList<PathFindingTile> neighbors = new ArrayList<>();
        for (int i = 0; i < accessibleNb; i++) {
            if (board.isInBound(x + accessibleX.get(i), y + accessibleY.get(i)))
                neighbors.add(new PathFindingTile(x + accessibleX.get(i), y + accessibleY.get(i)));
        }
        return neighbors;
    }

    public ArrayList<PathFindingTile> getEmptyNeighbors(Board board, PathFindingTile tile) {
        int x = tile.getTileX();
        int y = tile.getTileY();
        ArrayList<PathFindingTile> neighbors = new ArrayList<>();
        for (int i = 0; i < accessibleNb; i++) {
            if (board.isEmpty(x + accessibleX.get(i), y + accessibleY.get(i)))
                neighbors.add(new PathFindingTile(x + accessibleX.get(i), y + accessibleY.get(i)));
        }
        return neighbors;
    }

    public void move(Integer x, Integer y) {
        this.x = x;
        this.y = y;
        setHasMoved();
    }

    public void move() {
        move(targetX, targetY);
    }

    // Find the empty spots around (there is at least one)
    // From these compute shortest path to white king using BFS, made of non white occupied tiles
    // set this.shortestPathLength
    // set this.targetX and this.targetY (the empty tile)
	public void computeShortestPath(Board board) {
        ArrayList<PathFindingTile> startTiles = getEmptyNeighbors(board, new PathFindingTile(this.x, this.y));
        Integer kingX = 0;
        Integer kingY = 0;
        for (WhitePiece whitePiece: board.getWhitePieces()) {
            if (whitePiece.getPieceType().equals("WhiteKing")) {
                kingX = whitePiece.getX();
                kingY = whitePiece.getY();
            }
        }

        for (PathFindingTile startTile: startTiles) {
            ArrayList<PathFindingTile> visiting = new ArrayList<>();
            ArrayList<PathFindingTile> visited = new ArrayList<>();
            visited.add(new PathFindingTile(this.x, this.y));
            visited.getFirst().setDistance(0);
            // Add fake startTiles to the visited list to prevent issues
            for (PathFindingTile startTile2: startTiles) {
                PathFindingTile fakeStartTile = new PathFindingTile(startTile2.getTileX(), startTile2.getTileY());
                fakeStartTile.setDistance(1);
                visited.add(fakeStartTile);
            }
            startTile.setDistance(1);
            visiting.add(startTile);
            boolean foundEnd= false;
            while (!foundEnd && !visiting.isEmpty()) {
                Integer tileDistance = visiting.getFirst().getDistance();
                ArrayList<PathFindingTile> neighbors = getNeighbors(board, visiting.getFirst());
                // Mark Tile as visited
                visited.add(visiting.getFirst());
                visiting.remove(0);
                // Add valid neighbors to visiting queue
                for (PathFindingTile neighbor: neighbors) {
                    // End pathfinding for this start tile if white king is a neighbor
                    if (neighbor.getTileX() == kingX && neighbor.getTileY() == kingY) {
                        startTile.setDistance(tileDistance + 1);
                        foundEnd = true;
                        break;
                    }
                    boolean isNeighborValid = true;
                    // Check if neighbor is not a white piece
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
            if (!foundEnd)
                startTile.setDistance(-1);
        }
        // Find smallest path among all the initial spots calculated
        Integer smallestPath = -1;
        Integer smallestPathIndex = -1;
        for (int i = 0; i < startTiles.size(); i++) {
            Integer distance = startTiles.get(i).getDistance();
            if (distance != -1 && (smallestPath == -1 || distance < smallestPath)) {
                smallestPath = distance;
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
