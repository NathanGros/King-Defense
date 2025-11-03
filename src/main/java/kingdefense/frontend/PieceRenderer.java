package kingdefense.frontend;

import static com.raylib.Colors.*;
import static com.raylib.Raylib.*;

public class PieceRenderer {
    private Camera3D pieceCamera;
    
    public PieceRenderer() {
        pieceCamera = new Camera3D();
        pieceCamera._position(new Vector3().x(0).y(2.5f).z(7));
        pieceCamera.target(new Vector3().x(0).y(0.3f).z(0));
        pieceCamera.up(new Vector3().x(0).y(1).z(0));
        pieceCamera.fovy(30.0f);
        pieceCamera.projection(CAMERA_PERSPECTIVE);
    }
    
    public RenderTexture renderPiece(Model pieceModel, int pieceWidth, int pieceHeight, Shader shadowShader, RenderTexture shadowMap) {
        RenderTexture pieceRenderTexture = LoadRenderTexture(pieceWidth, pieceHeight);
        BeginTextureMode(pieceRenderTexture);
            ClearBackground(BLANK);
            BeginMode3D(pieceCamera);
                DrawModel(pieceModel, new Vector3().x(0).y(0).z(0), 1.0f, WHITE);
            EndMode3D();
        EndTextureMode();
        return pieceRenderTexture;
    }
}
