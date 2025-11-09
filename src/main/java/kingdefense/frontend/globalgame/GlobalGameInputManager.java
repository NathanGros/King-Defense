package kingdefense.frontend.globalgame;

import java.util.ArrayList;

import kingdefense.backend.GlobalGame;
import kingdefense.frontend.ui.TextButton;

public class GlobalGameInputManager {
    public GlobalGameInputManager() {
    }

    public void checkInputs(GlobalGame globalGame, ArrayList<TextButton> globalGameButtons) {
        if (globalGameButtons.get(0).isClicked()) {
            globalGame.startGame();
            return;
        }
        if (globalGameButtons.get(1).isClicked()) {
            globalGame.startUpgrades();
            return;
        }
        if (globalGameButtons.get(2).isClicked()) {
            globalGame.stopGlobalGame();
            return;
        }
    }
}
