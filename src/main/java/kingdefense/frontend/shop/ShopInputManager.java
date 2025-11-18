package kingdefense.frontend.shop;

import java.util.ArrayList;

import kingdefense.backend.Game;
import kingdefense.backend.Shop;
import kingdefense.backend.pieces.*;
import kingdefense.frontend.ui.ShopButton;
import kingdefense.frontend.ui.TextButton;

public class ShopInputManager {
    public ShopInputManager() {
    }

    private void buttonAction(Game game, Integer id) {
        switch (id) {
            case 0:
                game.addAvailableWhitePiece(new WhitePawn());
                break;
            case 1:
                game.addAvailableWhitePiece(new WhiteKnight());
                break;
            default:
                break;
        }
    }

    public void checkInputs(Shop shop, Game game, TextButton exitButton, ArrayList<ShopButton> shopButtons) {
        if (exitButton.isClicked()) {
            shop.stopShop();
            return;
        }
        for (ShopButton shopButton: shopButtons) {
            if (shopButton.isClicked()) {
                buttonAction(game, shopButton.getId());
            }
        }
    }
}
