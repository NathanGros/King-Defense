package kingdefense.frontend.upgrades;

import static com.raylib.Raylib.BeginDrawing;
import static com.raylib.Raylib.ClearBackground;
import static com.raylib.Raylib.EndDrawing;

import java.util.ArrayList;

import kingdefense.frontend.Colors;
import kingdefense.frontend.ui.TextButton;
import kingdefense.frontend.ui.UpgradeButton;

public class UpgradesDrawer {

    public UpgradesDrawer() {
    }

    public void draw(TextButton exitButton, ArrayList<UpgradeButton> upgradeButtons) {
        BeginDrawing();
        ClearBackground(Colors.backgroundColor);
        exitButton.draw();
        for (UpgradeButton upgradeButton: upgradeButtons) {
            upgradeButton.draw();
        }
        EndDrawing();
    }
}
