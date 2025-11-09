package kingdefense.backend;

import kingdefense.frontend.globalgame.GlobalGameScreen;

public class GlobalGame {
    private GlobalGameScreen globalGameScreen;
    private boolean isGlobalGameRunning;
    private Game game;
    private boolean isGameRunning;
    private Upgrades upgrades;
    private boolean isUpgradesRunning;

    public GlobalGame() {
        globalGameScreen = new GlobalGameScreen();
        isGlobalGameRunning = true;
        isGameRunning = false;
        isUpgradesRunning = false;
    }

    public void startGlobalGame() {
        isGlobalGameRunning = true;
        isGameRunning = false;
        isUpgradesRunning = false;
        globalGameLoop();
    }

    public void stopGlobalGame() {
        isGlobalGameRunning = false;
    }

    public void startGame() {
        game = new Game();
        isGameRunning = true;
        isUpgradesRunning = false;
    }

    public void startUpgrades() {
        upgrades = new Upgrades();
        isUpgradesRunning = true;
        isGameRunning = false;
    }

    public void globalGameLoop() {
        while (isGlobalGameRunning) {
            globalGameScreen.drawScreen();
            globalGameScreen.interactScreen(this);
            if (isGameRunning) {
                game.startGame();
                isGameRunning = false;
                continue;
            }
            if (isUpgradesRunning) {
                upgrades.startUpgrades();
                isUpgradesRunning = false;
                continue;
            }
        }
    }
}
