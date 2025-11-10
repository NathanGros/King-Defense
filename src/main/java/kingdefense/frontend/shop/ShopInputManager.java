package kingdefense.frontend.shop;

import kingdefense.backend.Shop;
import kingdefense.frontend.ui.TextButton;

public class ShopInputManager {
    public ShopInputManager() {
    }

    public void checkInputs(Shop shop, TextButton exitButton) {
        if (exitButton.isClicked()) {
            shop.stopShop();
            return;
        }
    }
}
