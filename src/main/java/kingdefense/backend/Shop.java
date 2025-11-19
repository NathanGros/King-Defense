package kingdefense.backend;

import kingdefense.frontend.shop.ShopScreen;

public class Shop {
    private ShopScreen shopScreen;
    private boolean isShopRunning;
    private Game game;

    public Shop(Game game) {
        shopScreen = new ShopScreen();
        isShopRunning = true;
        this.game = game;
    }

    public void startShop() {
        isShopRunning = true;
        shopLoop();
    }

    public void stopShop() {
        isShopRunning = false;
    }

    public void shopLoop() {
        while (isShopRunning) {
            shopScreen.drawScreen(game);
            shopScreen.interactScreen(this, game);
        }
    }
}
