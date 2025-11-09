package kingdefense.frontend.globalgame;

import static com.raylib.Raylib.*;

import java.util.ArrayList;

import kingdefense.backend.GlobalGame;
import kingdefense.frontend.ui.TextButton;

public class GlobalGameScreen {
    GlobalGameDrawer globalGameDrawer;
    GlobalGameInputManager globalGameInputManager;
    ArrayList<TextButton> globalGameButtons;

    public GlobalGameScreen() {
        globalGameDrawer = new GlobalGameDrawer();
        globalGameInputManager = new GlobalGameInputManager();
        Integer buttonWidth = GetScreenWidth() / 3;
        Integer buttonHeight = GetScreenHeight() / 10;
        Integer boxHeight = GetScreenHeight() / 2;
        Integer boxX = GetScreenWidth() / 3;
        Integer boxY = GetScreenHeight() / 4;
        Integer button1X = boxX;
        Integer button1Y = boxY;
        Integer button2X = boxX;
        Integer button2Y = boxY + (boxHeight - buttonHeight) / 2;
        Integer button3X = boxX;
        Integer button3Y = boxY + boxHeight - buttonHeight;
        globalGameButtons = new ArrayList<>();
        globalGameButtons.add(new TextButton(button1X, button1Y, buttonWidth, buttonHeight, "Play"));
        globalGameButtons.add(new TextButton(button2X, button2Y, buttonWidth, buttonHeight, "Upgrades"));
        globalGameButtons.add(new TextButton(button3X, button3Y, buttonWidth, buttonHeight, "Back"));
    }

    public void drawScreen() {
        globalGameDrawer.draw(globalGameButtons);
    }

    public void interactScreen(GlobalGame globalGame) {
        globalGameInputManager.checkInputs(globalGame, globalGameButtons);
    }
}
