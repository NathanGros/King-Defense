package kingdefense.frontend.upgrades;

import java.util.ArrayList;

import kingdefense.backend.Upgrades;
import kingdefense.frontend.ui.TextButton;
import kingdefense.frontend.ui.UpgradeButton;

public class UpgradesInputManager {
    public UpgradesInputManager() {
    }

    private void buttonAction(UpgradeButton upgradeButton) {
    }

    public void checkInputs(Upgrades upgrades, TextButton exitButton, ArrayList<UpgradeButton> upgradeButtons) {
        if (exitButton.isClicked()) {
            upgrades.stopUpgrades();
            return;
        }
        for (UpgradeButton upgradeButton: upgradeButtons) {
            if (upgradeButton.isClicked()) {
                buttonAction(upgradeButton);
            }
        }
    }
}
