package kingdefense.frontend.ui;

import static com.raylib.Colors.*;
import static com.raylib.Raylib.DrawRectangle;
import static com.raylib.Raylib.DrawText;

import com.raylib.Raylib.Color;

import kingdefense.backend.Game;

public class TextButton extends Button {
    private String text;

    public TextButton(Integer x, Integer y, Integer width, Integer height, String text) {
        super(x, y, width, height);
        this.text = text;
    }
    public TextButton(Integer x, Integer y, Integer width, Integer height) {
        this(x, y, width, height, "");
    }

    public void draw(Color color) {
        DrawRectangle(x, y, width, height, color);
        DrawText(text, x, y, 35, RAYWHITE);
    }

	public void activate(Game game) {
	}
}
