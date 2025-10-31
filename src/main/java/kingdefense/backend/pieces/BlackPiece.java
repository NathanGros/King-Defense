package kingdefense.backend.pieces;

import java.util.ArrayList;

import kingdefense.backend.board.Board;
import kingdefense.backend.logic.PathFindingTile;

public abstract class BlackPiece {
    protected Integer x;
    protected Integer y;
    protected Float health;
    protected Integer attack;
    protected Integer priority;
    protected ArrayList<Float> poisonDamageList;
    protected ArrayList<Integer> poisonTurnsLeftList;
	protected boolean hasMoved;
    protected Integer targetX;
    protected Integer targetY;
    protected Integer shortestPathLength;
    protected ArrayList<Integer> attainableX;
	protected ArrayList<Integer> attainableY;
    protected Integer attainableNb;

	public BlackPiece(Integer x, Integer y, Float health, Integer attack) {
        this.x = x;
        this.y = y;
        this.health = health;
        this.attack = attack;
        this.priority = 0;
        this.poisonDamageList = new ArrayList<>();
        this.poisonTurnsLeftList = new ArrayList<>();
        this.hasMoved = false;
        this.targetX = -1;
        this.targetY = -1;
        this.shortestPathLength = -1;
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
	public Float getHealth() {
		return health;
	}
	public void setHealth(Float health) {
		this.health = health;
	}
	public void damage(Float damage) {
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
	public void poison(Float poisonDamage, Integer nbTurns) {
        poisonDamageList.add(poisonDamage);
        poisonTurnsLeftList.add(nbTurns);
	}
    public ArrayList<Float> getPoisonDamageList() {
        return poisonDamageList;
    }
    public ArrayList<Integer> getPoisonTurnsLeftList() {
        return poisonTurnsLeftList;
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
	public void setAttainableX(ArrayList<Integer> attainableX) {
		this.attainableX = attainableX;
	}
	public void setAttainableY(ArrayList<Integer> attainableY) {
		this.attainableY = attainableY;
	}

    public abstract String getPieceType();

	public boolean canMove(Board board) {
        for (int i = 0; i < attainableNb; i++) {
            if (board.isEmpty(x + attainableX.get(i), y + attainableY.get(i)))
                return true;
        }
        return false;
	}

    public ArrayList<PathFindingTile> getNeighbors(Board board, PathFindingTile tile) {
        int x = tile.getTileX();
        int y = tile.getTileY();
        ArrayList<PathFindingTile> neighbors = new ArrayList<>();
        for (int i = 0; i < attainableNb; i++) {
            if (board.isInBound(x + attainableX.get(i), y + attainableY.get(i)))
                neighbors.add(new PathFindingTile(x + attainableX.get(i), y + attainableY.get(i)));
        }
        return neighbors;
    }

    public ArrayList<PathFindingTile> getEmptyNeighbors(Board board, PathFindingTile tile) {
        int x = tile.getTileX();
        int y = tile.getTileY();
        ArrayList<PathFindingTile> neighbors = new ArrayList<>();
        for (int i = 0; i < attainableNb; i++) {
            if (board.isEmpty(x + attainableX.get(i), y + attainableY.get(i)))
                neighbors.add(new PathFindingTile(x + attainableX.get(i), y + attainableY.get(i)));
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

    private boolean isNeighborValid(Board board, PathFindingTile neighbor, ArrayList<PathFindingTile> visited, ArrayList<PathFindingTile> visiting) {
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
        // Check if neighbor is not already in visiting queue (BFS property)
        for (PathFindingTile visitingTile: visiting) {
            if (neighbor.getTileX() == visitingTile.getTileX() && neighbor.getTileY() == visitingTile.getTileY())
                isNeighborValid = false;
        }
        return isNeighborValid;
    }

    private void bfs(Board board, PathFindingTile startTile, WhiteKing whiteKing) {
        ArrayList<PathFindingTile> visiting = new ArrayList<>();
        ArrayList<PathFindingTile> visited = new ArrayList<>();
        visited.add(new PathFindingTile(this.x, this.y));
        visited.getFirst().setDistance(0);
        startTile.setDistance(1);
        visiting.add(startTile);
        boolean foundKing = false;
        while (!visiting.isEmpty()) {
            Integer tileDistance = visiting.getFirst().getDistance();
            // End BFS
            if (visiting.getFirst().getTileX() == whiteKing.getX() && visiting.getFirst().getTileY() == whiteKing.getY()) {
                startTile.setDistance(tileDistance);
                foundKing = true;
                break;
            }
            ArrayList<PathFindingTile> neighbors = getNeighbors(board, visiting.getFirst());
            // Mark Tile as visited
            visited.add(visiting.getFirst());
            visiting.remove(0);
            // Add valid neighbors to visiting queue
            for (PathFindingTile neighbor: neighbors) {
                if (isNeighborValid(board, neighbor, visited, visiting)) {
                    neighbor.setDistance(tileDistance + 1);
                    visiting.add(neighbor);
                }
            }
        }
        if (!foundKing)
            startTile.setDistance(-1);
    }

    private void setShortestPathsResults(ArrayList<PathFindingTile> startTiles) {
        // Find smallest path among all the initial spots calculated if there is one
        // A tile with distance -1 means the king can't be reach from there
        Integer smallestPath = -1;
        Integer smallestPathIndex = -1;
        for (int i = 0; i < startTiles.size(); i++) {
            Integer distance = startTiles.get(i).getDistance();
            if (distance != -1 && (smallestPath == -1 || distance < smallestPath)) {
                smallestPath = distance;
                smallestPathIndex = i;
            }
        }
        if (smallestPath == -1) {
            this.setShortestPathLength(-1);
            this.setTargetX(x);
            this.setTargetY(y);
        }
        else {
            this.setShortestPathLength(smallestPath);
            this.setTargetX(startTiles.get(smallestPathIndex).getTileX());
            this.setTargetY(startTiles.get(smallestPathIndex).getTileY());
        }
    }

	public void computeShortestPath(Board board) {
        // Find the empty spots around the piece (there is at least one)
        ArrayList<PathFindingTile> startTiles = getEmptyNeighbors(board, new PathFindingTile(this.x, this.y));
        WhiteKing whiteKing = board.getWhiteKing();
        // For each spots, compute shortest path to white king using BFS, path made of non white occupied tiles
        for (PathFindingTile startTile: startTiles) {
            bfs(board, startTile, whiteKing);
        }
        // Then choose which initial empty spot becomes the target to move
        setShortestPathsResults(startTiles);
	}

    @Override
    public String toString() {
        return this.getPieceType() + ", x:" + x + ", y:" + y + ", health:" + health;
    }
}
