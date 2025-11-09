package kingdefense.frontend.ui;

import static com.raylib.Raylib.DrawRectangle;
import static com.raylib.Raylib.DrawText;

import kingdefense.backend.Game;
import kingdefense.frontend.Colors;

public class TextButton extends Button {
    private String text;

    public TextButton(Integer x, Integer y, Integer width, Integer height, String text) {
        super(x, y, width, height);
        this.text = text;
    }
    public TextButton(Integer x, Integer y, Integer width, Integer height) {
        this(x, y, width, height, "");
    }

    public void draw() {
        DrawRectangle(x, y, width, height, Colors.buttonBackgroundColor);
        DrawText(text, x, y, 35, Colors.textColor);
    }

	public void activate(Game game) {
	}
}
