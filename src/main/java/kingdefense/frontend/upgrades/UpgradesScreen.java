package kingdefense.frontend.upgrades;

import static com.raylib.Raylib.*;

import java.util.ArrayList;

import kingdefense.backend.Upgrades;
import kingdefense.frontend.ui.TextButton;
import kingdefense.frontend.ui.UpgradeButton;

public class UpgradesScreen {
    UpgradesDrawer upgradesDrawer;
    UpgradesInputManager upgradesInputManager;
    TextButton exitButton;
    ArrayList<UpgradeButton> upgradeButtons;

    public UpgradesScreen() {
        upgradesDrawer = new UpgradesDrawer();
        upgradesInputManager = new UpgradesInputManager();
        Integer exitButtonWidth = GetScreenWidth() / 3;
        Integer exitButtonHeight = GetScreenHeight() / 10;
        Integer exitButtonX = GetScreenWidth() / 3;
        Integer exitButtonY = GetScreenHeight() * 8 / 10;
        exitButton = new TextButton(exitButtonX, exitButtonY, exitButtonWidth, exitButtonHeight, "Back");
        initUpgradeButtons();
    }

    private void initUpgradeButtons() {
        Integer boxNbX = 3;
        Integer boxNbY = 2;
        Integer buttonBorder = GetScreenWidth() / 20;
        Integer boxX = GetScreenWidth() / 10;
        Integer boxY = GetScreenHeight() / 6;
        Integer boxWidth = GetScreenWidth() - boxX * 2;
        Integer boxHeight = GetScreenHeight() - boxY * 3;
        upgradeButtons = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            upgradeButtons.add(new UpgradeButton(i, 
                boxX + (i % boxNbX) * boxWidth / boxNbX + buttonBorder / 2,
                boxY + (i / boxNbX) * boxHeight / boxNbY + buttonBorder / 2,
                boxWidth / boxNbX - buttonBorder,
                boxHeight / boxNbY - buttonBorder
            ));
        }
        upgradeButtons.get(0).setCost(3);
        upgradeButtons.get(1).setCost(8);
        upgradeButtons.get(2).setCost(10);
        upgradeButtons.get(3).setCost(15);
        upgradeButtons.get(4).setCost(20);
    }

    public void drawScreen() {
        upgradesDrawer.draw(exitButton, upgradeButtons);
    }

    public void interactScreen(Upgrades upgrades) {
        upgradesInputManager.checkInputs(upgrades, exitButton, upgradeButtons);
    }
}

