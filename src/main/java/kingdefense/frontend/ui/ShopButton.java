package kingdefense.frontend.ui;

import static com.raylib.Raylib.DrawRectangle;
import static com.raylib.Raylib.DrawText;

import kingdefense.backend.Game;
import kingdefense.frontend.Colors;

public class ShopButton extends Button {
    private Integer id;
    private Integer cost;

    public ShopButton(Integer id, Integer x, Integer y, Integer width, Integer height, Integer cost) {
        super(x, y, width, height);
        this.id = id;
        this.cost = cost;
    }
    public ShopButton(Integer id, Integer x, Integer y, Integer width, Integer height) {
        this(id, x, y, width, height, 1);
    }

    public Integer getId() {
        return id;
    }
    public Integer getCost() {
        return cost;
    }

    public void draw() {
        DrawRectangle(x, y, width, height, Colors.buttonBackgroundColor);
        DrawText(cost.toString(), x, y, 35, Colors.yellow);
    }

	public void activate(Game game) {
	}
}
