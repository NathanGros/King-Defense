package kingdefense.backend.pieces;

import kingdefense.backend.board.Board;
import kingdefense.backend.board.Tile;

public class WhiteKing {
    private Tile tile;
	private Integer maxHealth;
    private Integer health;
    private boolean isPlaced;

	public WhiteKing(Integer x, Integer y, Integer health) {
        this.tile = new Tile(x, y);
        this.maxHealth = health;
        this.health = health;
        this.isPlaced = false;
    }
	public WhiteKing(Integer health) {
        this(0, 0, health);
    }

	public Tile getTile() {
		return tile;
	}
	public void setTile(Tile tile) {
		this.tile = tile;
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

    public void damage(Board board, BlackPiece blackPiece) {
        health -= blackPiece.getAttack();
        if (health <= 0)
            health = 0;
        board.removeBlackPiece(blackPiece);
    }

    @Override
    public String toString() {
        return "WhiteKing" + tile + ", health:" + health;
    }
}
