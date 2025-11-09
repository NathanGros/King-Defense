package kingdefense.frontend.ui;

import static com.raylib.Raylib.*;

import kingdefense.backend.Game;
import kingdefense.frontend.Colors;

public class StartWaveButton extends Button {
	public StartWaveButton(Integer x, Integer y, Integer width, Integer height) {
        super(x, y, width, height);
    }
    
    public void draw() {
        DrawRectangle(x, y, width, height, Colors.red);
        DrawText("Start wave", x + 20, y + 5, 35, Colors.textColor);
    }

    public void activate(Game game) {
        if (isClicked()) {
            game.startWave();
        }
    }
}
