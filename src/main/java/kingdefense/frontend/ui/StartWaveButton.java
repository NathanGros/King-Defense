package kingdefense.frontend.ui;

import static com.raylib.Colors.*;
import static com.raylib.Raylib.*;

import kingdefense.backend.Game;

public class StartWaveButton extends Button {
	public StartWaveButton(Integer x, Integer y, Integer width, Integer height) {
        super(x, y, width, height);
    }
    
    public void draw(Color color) {
        DrawRectangle(x, y, width, height, color);
        DrawText("Start wave", x + 20, y + 5, 35, RAYWHITE);
    }

    @Override
    public void activate(Game game) {
        if (isClicked()) {
            game.startWave();
        }
    }
}
