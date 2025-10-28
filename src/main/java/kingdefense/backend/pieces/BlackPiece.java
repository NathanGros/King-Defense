package kingdefense.backend.pieces;

public abstract class BlackPiece {
    private Integer x;
    private Integer y;
    private Integer health;
    private Integer attack;

	public BlackPiece(Integer x, Integer y, Integer health, Integer attack) {
        this.x = x;
        this.y = y;
        this.health = health;
        this.attack = attack;
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

    public String getPieceType() {
        return "BlackPiece";
    }

    public void move(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return this.getPieceType() + ", x:" + x + ", y:" + y;
    }
}
