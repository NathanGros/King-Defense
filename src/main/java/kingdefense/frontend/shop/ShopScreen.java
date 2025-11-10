package kingdefense.frontend.shop;

import static com.raylib.Raylib.*;

import kingdefense.backend.Shop;
import kingdefense.frontend.ui.TextButton;

public class ShopScreen {
    ShopDrawer shopDrawer;
    ShopInputManager shopInputManager;
    TextButton exitButton;

    public ShopScreen() {
        shopDrawer = new ShopDrawer();
        shopInputManager = new ShopInputManager();
        Integer exitButtonWidth = GetScreenWidth() / 3;
        Integer exitButtonHeight = GetScreenHeight() / 10;
        Integer exitButtonX = GetScreenWidth() / 3;
        Integer exitButtonY = GetScreenHeight() * 8 / 10;
        exitButton = new TextButton(exitButtonX, exitButtonY, exitButtonWidth, exitButtonHeight, "Back");
    }

    public void drawScreen() {
        shopDrawer.draw(exitButton);
    }

    public void interactScreen(Shop shop) {
        shopInputManager.checkInputs(shop, exitButton);
    }
}


