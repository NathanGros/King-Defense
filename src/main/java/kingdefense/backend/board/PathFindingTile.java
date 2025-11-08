package kingdefense.backend.board;

public class PathFindingTile extends Tile {
	private Integer distance;

	public PathFindingTile(Integer tileX, Integer tileY) {
        super(tileX, tileY);
        distance = -1;
    }
	public PathFindingTile() {
        super();
        distance = -1;
    }
	public PathFindingTile(Tile tile) {
        super(tile.getX(), tile.getY());
        distance = -1;
    }

    public Integer getDistance() {
		return distance;
	}
	public void setDistance(Integer distance) {
		this.distance = distance;
	}

    @Override
    public String toString() {
        return "x: " + x + ", y: " + y + ", distance: " + distance;
    }
}
