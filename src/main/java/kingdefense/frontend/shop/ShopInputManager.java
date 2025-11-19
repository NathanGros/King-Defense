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

    private void buttonAction(Game game, ShopButton shopButton) {
        switch (shopButton.getId()) {
            case 0:
                try {
                    game.removeCoins(shopButton.getCost());
                    game.addAvailableWhitePiece(new WhitePawn());
                } catch (IllegalArgumentException e) {}
                break;
            case 1:
                try {
                    game.removeCoins(shopButton.getCost());
                    game.addAvailableWhitePiece(new WhiteKnight());
                } catch (IllegalArgumentException e) {}
                break;
            case 2:
                try {
                    game.removeCoins(shopButton.getCost());
                    game.addAvailableWhitePiece(new WhiteBishop());
                } catch (IllegalArgumentException e) {}
                break;
            case 3:
                try {
                    game.removeCoins(shopButton.getCost());
                    game.addAvailableWhitePiece(new WhiteRook());
                } catch (IllegalArgumentException e) {}
                break;
            case 4:
                try {
                    game.removeCoins(shopButton.getCost());
                    game.addAvailableWhitePiece(new WhiteQueen());
                } catch (IllegalArgumentException e) {}
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
                buttonAction(game, shopButton);
            }
        }
    }
}
