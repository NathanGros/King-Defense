package kingdefense.frontend.ui;

import static com.raylib.Colors.*;
import static com.raylib.Raylib.*;

import kingdefense.backend.Game;

public class WhitePieceButton extends Button {
    private String name;

	public WhitePieceButton(Integer x, Integer y, Integer width, Integer height, String name) {
        super(x, y, width, height);
        this.name = name;
    }

    public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

    public void draw(Integer nbAvailable) {
        int textHeight = 40;
        DrawText(name.substring(5), x + width / 2 - textHeight * 2 / 3, y + height - 4 * textHeight, textHeight / 2, RAYWHITE);
        DrawText("x" + nbAvailable.toString(), x + width / 2 - textHeight / 2, y + height - 2 * textHeight, textHeight, RAYWHITE);
    }

    @Override
    public void activate(Game game) {
        if (isClicked())
            game.setSelectedWhitePiece(name);
    }
}
