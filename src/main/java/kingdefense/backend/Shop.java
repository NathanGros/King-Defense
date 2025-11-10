package kingdefense.backend;

import kingdefense.frontend.shop.ShopScreen;

public class Shop {
    private ShopScreen shopScreen;
    private boolean isShopRunning;

    public Shop() {
        shopScreen = new ShopScreen();
        isShopRunning = true;
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
            shopScreen.drawScreen();
            shopScreen.interactScreen(this);
        }
    }
}
