package kingdefense.frontend.menu;

import static com.raylib.Raylib.*;

import java.util.ArrayList;

import kingdefense.backend.GameMenu;
import kingdefense.frontend.ui.TextButton;

public class MenuScreen {
    MenuDrawer menuDrawer;
    MenuInputManager menuInputManager;
    ArrayList<TextButton> menuButtons;

    public MenuScreen() {
        menuDrawer = new MenuDrawer();
        menuInputManager = new MenuInputManager();
        Integer buttonWidth = GetScreenWidth() / 3;
        Integer buttonHeight = GetScreenHeight() / 10;
        Integer boxHeight = GetScreenHeight() / 3;
        Integer boxX = GetScreenWidth() / 3;
        Integer boxY = GetScreenHeight() / 3;
        Integer button1X = boxX;
        Integer button1Y = boxY;
        Integer button2X = boxX;
        Integer button2Y = boxY + boxHeight - buttonHeight;
        menuButtons = new ArrayList<>();
        menuButtons.add(new TextButton(button1X, button1Y, buttonWidth, buttonHeight, "Start game"));
        menuButtons.add(new TextButton(button2X, button2Y, buttonWidth, buttonHeight, "Exit"));
    }

    public void drawScreen() {
        menuDrawer.draw(menuButtons);
    }

    public void interactScreen(GameMenu gameMenu) {
        menuInputManager.checkInputs(gameMenu, menuButtons);
    }
}
