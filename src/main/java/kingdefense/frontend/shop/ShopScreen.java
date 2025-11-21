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
        Integer boxNbX = 3;
        Integer boxNbY = 2;
        Integer buttonBorder = GetScreenWidth() / 20;
        Integer boxX = GetScreenWidth() / 10;
        Integer boxY = GetScreenHeight() / 6;
        Integer boxWidth = GetScreenWidth() - boxX * 2;
        Integer boxHeight = GetScreenHeight() - boxY * 3;
        shopButtons = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            shopButtons.add(new ShopButton(i, 
                boxX + (i % boxNbX) * boxWidth / boxNbX + buttonBorder / 2,
                boxY + (i / boxNbX) * boxHeight / boxNbY + buttonBorder / 2,
                boxWidth / boxNbX - buttonBorder,
                boxHeight / boxNbY - buttonBorder
            ));
        }
        shopButtons.get(0).setCost(3);
        shopButtons.get(1).setCost(8);
        shopButtons.get(2).setCost(10);
        shopButtons.get(3).setCost(15);
        shopButtons.get(4).setCost(20);
    }

    public void drawScreen(Game game) {
        shopDrawer.draw(exitButton, shopButtons, game.getBank());
    }

    public void interactScreen(Shop shop, Game game) {
        shopInputManager.checkInputs(shop, game, exitButton, shopButtons);
    }
}


