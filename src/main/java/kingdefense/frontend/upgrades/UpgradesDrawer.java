package kingdefense.frontend.upgrades;

import static com.raylib.Raylib.BeginDrawing;
import static com.raylib.Raylib.ClearBackground;
import static com.raylib.Raylib.EndDrawing;

import kingdefense.frontend.Colors;
import kingdefense.frontend.ui.TextButton;

public class UpgradesDrawer {

    public UpgradesDrawer() {
    }

    public void draw(TextButton exitButton) {
        BeginDrawing();
        ClearBackground(Colors.backgroundColor);
        exitButton.draw();
        EndDrawing();
    }
}
