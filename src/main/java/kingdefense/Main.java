package kingdefense;

import kingdefense.backend.GameMenu;
import kingdefense.frontend.WindowManager;

public class Main {
    public static void main(String[] args) {
        WindowManager windowManager = new WindowManager();
        windowManager.launchWindow();
        GameMenu gameMenu = new GameMenu();
        gameMenu.startMenu();
        windowManager.closeWindow();
    }
}
