package kingdefense.backend.board;

public class CoinTile extends Tile {
    private Integer nbCoins;

	public CoinTile(Integer tileX, Integer tileY, Integer nbCoins) {
        super(tileX, tileY);
        this.nbCoins = nbCoins;
    }
	public CoinTile(Integer tileX, Integer tileY) {
        super(tileX, tileY);
        this.nbCoins = 0;
    }
    public CoinTile() {
        super(0, 0);
        this.nbCoins = 0;
    }
    public CoinTile(Tile tile, Integer nbCoins) {
        super(tile.getX(), tile.getY());
        this.nbCoins = nbCoins;
    }

	public Integer getNbCoins() {
		return nbCoins;
	}
	public void setNbCoins(Integer nbCoins) {
		this.nbCoins = nbCoins;
	}
    public void addCoins(Integer nbCoins) {
        this.nbCoins += nbCoins;
    }

    @Override
    public String toString() {
        return "x: " + x + ", y: " + y + ", nbCoins: " + nbCoins;
    }
}
