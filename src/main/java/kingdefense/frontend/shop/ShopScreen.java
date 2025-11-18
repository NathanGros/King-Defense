package kingdefense.frontend.shop;

import static com.raylib.Raylib.*;

import java.util.ArrayList;

import kingdefense.backend.Game;
import kingdefense.backend.Shop;
import kingdefense.frontend.ui.ShopButton;
import kingdefense.frontend.ui.TextButton;

public class ShopScreen {
    ShopDrawer shopDrawer;
    ShopInputManager shopInputManager;
    TextButton exitButton;
    ArrayList<ShopButton> shopButtons;

    public ShopScreen() {
        shopDrawer = new ShopDrawer();
        shopInputManager = new ShopInputManager();
        Integer exitButtonWidth = GetScreenWidth() / 3;
        Integer exitButtonHeight = GetScreenHeight() / 10;
        Integer exitButtonX = GetScreenWidth() / 3;
        Integer exitButtonY = GetScreenHeight() * 8 / 10;
        exitButton = new TextButton(exitButtonX, exitButtonY, exitButtonWidth, exitButtonHeight, "Back");
        initShopButtons();
    }

    private void initShopButtons() {
        Integer boxButtonNbWidth = 2;
        Integer boxButtonNbHeight = 1;
        Integer boxX = GetScreenWidth() / 10;
        Integer boxY = GetScreenHeight() / 6;
        Integer boxWidth = GetScreenWidth() - boxX * 2;
        Integer boxHeight = GetScreenHeight() - boxY * 2;
        shopButtons = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            shopButtons.add(new ShopButton(i, 
                boxX + (i % boxButtonNbWidth) * boxWidth / boxButtonNbWidth,
                boxY + (i / boxButtonNbWidth) * boxHeight / boxButtonNbHeight,
                boxWidth / boxButtonNbWidth,
                boxHeight / boxButtonNbHeight
            ));
        }
    }

    public void drawScreen() {
        shopDrawer.draw(exitButton, shopButtons);
    }

    public void interactScreen(Shop shop, Game game) {
        shopInputManager.checkInputs(shop, game, exitButton, shopButtons);
    }
}


