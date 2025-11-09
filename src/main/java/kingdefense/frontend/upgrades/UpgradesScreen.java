package kingdefense.frontend.upgrades;

import static com.raylib.Raylib.*;

import kingdefense.backend.Upgrades;
import kingdefense.frontend.ui.TextButton;

public class UpgradesScreen {
    UpgradesDrawer upgradesDrawer;
    UpgradesInputManager upgradesInputManager;
    TextButton exitButton;

    public UpgradesScreen() {
        upgradesDrawer = new UpgradesDrawer();
        upgradesInputManager = new UpgradesInputManager();
        Integer exitButtonWidth = GetScreenWidth() / 3;
        Integer exitButtonHeight = GetScreenHeight() / 10;
        Integer exitButtonX = GetScreenWidth() / 3;
        Integer exitButtonY = GetScreenHeight() * 8 / 10;
        exitButton = new TextButton(exitButtonX, exitButtonY, exitButtonWidth, exitButtonHeight, "Back");
    }

    public void drawScreen() {
        upgradesDrawer.draw(exitButton);
    }

    public void interactScreen(Upgrades upgrades) {
        upgradesInputManager.checkInputs(upgrades, exitButton);
    }
}

