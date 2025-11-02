package kingdefense.backend.board;

public class CoinTile {
    private Integer x;
    private Integer y;
    private Integer nbCoins;

	public CoinTile(Integer x, Integer y, Integer nbCoins) {
        this.x = x;
        this.y = y;
        this.nbCoins = nbCoins;
    }
    public CoinTile(Integer x, Integer y) {
        this(x, y, 0);
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
	public Integer getNbCoins() {
		return nbCoins;
	}
	public void setNbCoins(Integer nbCoins) {
		this.nbCoins = nbCoins;
	}
    public void addCoins(Integer nbCoins) {
        this.nbCoins += nbCoins;
    }
}
