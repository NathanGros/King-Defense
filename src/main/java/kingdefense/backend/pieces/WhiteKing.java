package kingdefense.backend.pieces;

import kingdefense.backend.board.Board;

public class WhiteKing {
    private Integer x;
    private Integer y;
    private Integer health;

	public WhiteKing(Integer x, Integer y, Integer health) {
        this.x = x;
        this.y = y;
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
	public void setHealth(Integer health) {
		this.health = health;
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
