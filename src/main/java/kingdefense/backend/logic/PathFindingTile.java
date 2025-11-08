package kingdefense.backend.logic;

public class PathFindingTile {
    private Integer tileX;
	private Integer tileY;
	private Integer distance;

	public PathFindingTile(Integer tileX, Integer tileY) {
        this.tileX = tileX;
        this.tileY = tileY;
        distance = -1;
    }

    public Integer getTileX() {
		return tileX;
	}
	public void setTileX(Integer tileX) {
		this.tileX = tileX;
	}
	public Integer getTileY() {
		return tileY;
	}
	public void setTileY(Integer tileY) {
		this.tileY = tileY;
	}
    public Integer getDistance() {
		return distance;
	}
	public void setDistance(Integer distance) {
		this.distance = distance;
	}

    public boolean isSamePlace(PathFindingTile tile) {
        return tileX.equals(tile.getTileX()) && tileY.equals(tile.getTileY());
    }

    @Override
    public String toString() {
        return "x: " + tileX + ", y: " + tileY + ", d: " + distance;
    }
}
