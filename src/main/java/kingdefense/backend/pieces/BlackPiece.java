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

    public abstract void computeShortestPath(Board board);

    public abstract ArrayList<PathFindingTile> getNeighbors(PathFindingTile tile);

    public void move(Integer x, Integer y) {
        this.x = x;
        this.y = y;
        setHasMoved();
    }

    public void move() {
        move(targetX, targetY);
    }

    @Override
    public String toString() {
        return this.getPieceType() + ", x:" + x + ", y:" + y;
    }
}
