package kingdefense.frontend.upgrades;

import kingdefense.backend.Upgrades;
import kingdefense.frontend.ui.TextButton;

public class UpgradesInputManager {
    public UpgradesInputManager() {
    }

    public void checkInputs(Upgrades upgrades, TextButton exitButton) {
        if (exitButton.isClicked()) {
            upgrades.stopUpgrades();
            return;
        }
    }
}
