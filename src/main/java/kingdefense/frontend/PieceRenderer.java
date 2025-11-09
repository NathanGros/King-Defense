package kingdefense.frontend;

import static com.raylib.Raylib.*;

public class PieceRenderer {
    private Camera3D pieceCamera;
    
    public PieceRenderer() {
        pieceCamera = new Camera3D();
        pieceCamera._position(new Vector3().x(0).y(2.2f).z(7));
        pieceCamera.target(new Vector3().x(0).y(0.3f).z(0));
        pieceCamera.up(new Vector3().x(0).y(1).z(0));
        pieceCamera.fovy(30.0f);
        pieceCamera.projection(CAMERA_PERSPECTIVE);
    }
    
    public RenderTexture renderPiece(Model pieceModel, int pieceWidth, int pieceHeight, Shader shadowShader, RenderTexture shadowMap, float distance) {
        RenderTexture pieceRenderTexture = LoadRenderTexture(pieceWidth, pieceHeight);
        pieceCamera._position(Vector3Add(pieceCamera.target(), Vector3Scale(new Vector3().x(0).y(2.5f).z(7), distance)));
        BeginTextureMode(pieceRenderTexture);
            ClearBackground(Colors.transparent);
            BeginMode3D(pieceCamera);
                DrawModel(pieceModel, new Vector3().x(0).y(0).z(0), 1.0f, Colors.white);
            EndMode3D();
        EndTextureMode();
        return pieceRenderTexture;
    }
}
