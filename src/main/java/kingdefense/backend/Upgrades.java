package kingdefense.backend;

import kingdefense.frontend.upgrades.UpgradesScreen;

public class Upgrades {
    private UpgradesScreen upgradesScreen;
    private boolean isUpgradesRunning;

    public Upgrades() {
        upgradesScreen = new UpgradesScreen();
        isUpgradesRunning = true;
    }

    public void startUpgrades() {
        isUpgradesRunning = true;
        upgradesLoop();
    }

    public void stopUpgrades() {
        isUpgradesRunning = false;
    }

    public void upgradesLoop() {
        while (isUpgradesRunning) {
            upgradesScreen.drawScreen();
            upgradesScreen.interactScreen(this);
        }
    }
}
