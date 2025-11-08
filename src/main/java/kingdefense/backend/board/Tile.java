package kingdefense.backend.board;

public class Tile {
    protected Integer x;
    protected Integer y;

    public Tile(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }
    public Tile() {
        this(0, 0);
    }
    public Tile(Tile tile) {
        this(tile.getX(), tile.getY());
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
    public void set(Tile tile) {
        this.x = tile.getX();
        this.y = tile.getY();
    }

    public boolean isSamePlace(Tile tile) {
        return x.equals(tile.getX()) && y.equals(tile.getY());
    }

    public Tile addWithTile(Tile tile) {
        return new Tile(x + tile.getX(), y + tile.getY());
    }

    @Override
    public String toString() {
        return "x: " + x + ", y: " + y;
    }
}
