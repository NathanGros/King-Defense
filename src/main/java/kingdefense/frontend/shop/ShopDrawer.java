package kingdefense.frontend.shop;

import static com.raylib.Raylib.*;

import java.util.ArrayList;

import kingdefense.frontend.Colors;
import kingdefense.frontend.ui.ShopButton;
import kingdefense.frontend.ui.TextButton;

public class ShopDrawer {

    public ShopDrawer() {
    }

    private void drawEarnedCoins(Integer coins) {
        DrawText(coins.toString(), GetScreenWidth() * 17 / 20, GetScreenHeight() / 13, 30, Colors.yellow);
    }

    public void draw(TextButton exitButton, ArrayList<ShopButton> shopButtons, Integer bank) {
        BeginDrawing();
        ClearBackground(Colors.backgroundColor);
        exitButton.draw();
        for (ShopButton shopButton: shopButtons) {
            shopButton.draw();
        }
        drawEarnedCoins(bank);
        EndDrawing();
    }
}
