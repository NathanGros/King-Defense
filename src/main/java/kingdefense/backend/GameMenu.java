package kingdefense.backend;

import kingdefense.frontend.menu.MenuScreen;

public class GameMenu {
    private MenuScreen menuScreen;
    private boolean isMenuRunning;
    private GlobalGame globalGame;
    private boolean isGlobalGameRunning;

    public GameMenu() {
        menuScreen = new MenuScreen();
        isMenuRunning = true;
        isGlobalGameRunning = false;
    }

    public void startMenu() {
        isMenuRunning = true;
        isGlobalGameRunning = false;
        gameMenuLoop();
    }

    public void stopMenu() {
        isMenuRunning = false;
    }

    public void startGlobalGame() {
        globalGame = new GlobalGame();
        isGlobalGameRunning = true;
    }

    public void gameMenuLoop() {
        while (isMenuRunning) {
            menuScreen.drawScreen();
            menuScreen.interactScreen(this);
            if (isGlobalGameRunning) {
                globalGame.startGlobalGame();
                isGlobalGameRunning = false;
            }
        }
    }
}
