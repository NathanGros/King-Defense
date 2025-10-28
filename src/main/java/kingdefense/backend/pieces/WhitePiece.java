package kingdefense.backend.pieces;

public abstract class WhitePiece {
    private Integer x;
    private Integer y;

    public WhitePiece(Integer x, Integer y) {
        this.x = x;
        this.y = y;
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
    public String getPieceType() {
        return "WhitePiece";
    }

    public abstract void activate();

    @Override
    public String toString() {
        return this.getPieceType() + ", x:" + x + ", y:" + y;
    }
}

