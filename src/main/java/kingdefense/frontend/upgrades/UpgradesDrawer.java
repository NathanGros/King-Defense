package kingdefense.frontend.upgrades;

import static com.raylib.Raylib.BeginDrawing;
import static com.raylib.Raylib.ClearBackground;
import static com.raylib.Raylib.EndDrawing;
import static com.raylib.Raylib.Color;

import kingdefense.frontend.ui.TextButton;

public class UpgradesDrawer {
    private Color backgroundColor = new Color().r((byte) 51).g((byte) 51).b((byte) 51).a((byte) 255);
    private Color buttonBackgroundColor = new Color().r((byte) 37).g((byte) 37).b((byte) 37).a((byte) 255);

    public UpgradesDrawer() {
        backgroundColor = new Color().r((byte) 51).g((byte) 51).b((byte) 51).a((byte) 255);
        buttonBackgroundColor = new Color().r((byte) 37).g((byte) 37).b((byte) 37).a((byte) 255);
    }

    public void draw(TextButton exitButton) {
        BeginDrawing();
        ClearBackground(backgroundColor);
        exitButton.draw(buttonBackgroundColor);
        EndDrawing();
    }
}
