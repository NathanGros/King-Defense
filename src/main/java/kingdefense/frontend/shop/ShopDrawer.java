package kingdefense.frontend.shop;

import static com.raylib.Raylib.BeginDrawing;
import static com.raylib.Raylib.ClearBackground;
import static com.raylib.Raylib.EndDrawing;

import java.util.ArrayList;

import kingdefense.frontend.Colors;
import kingdefense.frontend.ui.ShopButton;
import kingdefense.frontend.ui.TextButton;

public class ShopDrawer {

    public ShopDrawer() {
    }

    public void draw(TextButton exitButton, ArrayList<ShopButton> shopButtons) {
        BeginDrawing();
        ClearBackground(Colors.backgroundColor);
        exitButton.draw();
        for (ShopButton shopButton: shopButtons) {
            shopButton.draw();
        }
        EndDrawing();
    }
}
