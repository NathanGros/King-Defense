package kingdefense.frontend.menu;

import java.util.ArrayList;

import kingdefense.backend.GameMenu;
import kingdefense.frontend.ui.TextButton;

public class MenuInputManager {
    public MenuInputManager() {
    }

    public void checkInputs(GameMenu gameMenu, ArrayList<TextButton> menuButtons) {
        if (menuButtons.get(0).isClicked()) {
            gameMenu.startGlobalGame();
            return;
        }
        if (menuButtons.get(1).isClicked()) {
            gameMenu.stopMenu();
            return;
        }
    }
}
