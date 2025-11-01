package kingdefense.backend.pieces;

import kingdefense.backend.board.Board;

public class WhiteKing {
    private Integer x;
    private Integer y;
	private Integer maxHealth;
    private Integer health;
    private boolean isPlaced;

	public WhiteKing(Integer x, Integer y, Integer health) {
        this.x = x;
        this.y = y;
        this.maxHealth = health;
        this.health = health;
        this.isPlaced = false;
    }
	public WhiteKing(Integer health) {
        this(0, 0, health);
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
    public Integer getMaxHealth() {
		return maxHealth;
	}
	public void setMaxHealth(Integer maxHealth) {
		this.maxHealth = maxHealth;
	}
    public Integer getHealth() {
		return health;
	}
	public void setHealth(Integer health) {
		this.health = health;
	}
	public boolean isPlaced() {
		return isPlaced;
	}
	public void place() {
		this.isPlaced = true;
	}
	public void unplace() {
		this.isPlaced = false;
	}

    public boolean isAdjacent(BlackPiece blackPiece) {
        Integer x = blackPiece.getX();
        Integer y = blackPiece.getY();
        if (this.x == x + 1 && this.y == y)
            return true;
        if (this.x == x - 1 && this.y == y)
            return true;
        if (this.x == x && this.y == y + 1)
            return true;
        if (this.x == x && this.y == y - 1)
            return true;
        return false;
    }

    public void damage(Board board, BlackPiece blackPiece) {
        health -= blackPiece.getAttack();
        if (health <= 0)
            health = 0;
        board.removeBlackPiece(blackPiece);
    }

    @Override
    public String toString() {
        return "WhiteKing" + ", x:" + x + ", y:" + y + ", health:" + health;
    }
}
