package kingdefense.frontend;

import static com.raylib.Raylib.*;

import kingdefense.backend.pieces.WhitePiece;

public class ModelsManager {
    Model whitePawnModel;
    Model whiteBishopModel;
    Model whiteKnightModel;
    Model whiteRookModel;
    Model whiteQueenModel;
    Model whiteKingModel;

    public ModelsManager() {
    }

    public void loadModels() {
        whitePawnModel = LoadModel("src/main/resources/models/whitePawnModel.obj");
        whiteBishopModel = LoadModel("src/main/resources/models/whiteBishopModel.obj");
        whiteKnightModel = LoadModel("src/main/resources/models/whiteKnightModel.obj");
        whiteRookModel = LoadModel("src/main/resources/models/whiteRookModel.obj");
        whiteQueenModel = LoadModel("src/main/resources/models/whiteQueenModel.obj");
        whiteKingModel = LoadModel("src/main/resources/models/whiteKingModel.obj");
    }

    public void unloadModels() {
        UnloadModel(whitePawnModel);
        UnloadModel(whiteBishopModel);
        UnloadModel(whiteKnightModel);
        UnloadModel(whiteRookModel);
        UnloadModel(whiteQueenModel);
        UnloadModel(whiteKingModel);
    }

    public Model getWhiteModel(String pieceType) {
        switch (pieceType) {
            case "WhitePawn":
                return whitePawnModel;
            case "WhiteBishop":
                return whiteBishopModel;
            case "WhiteKnight":
                return whiteKnightModel;
            case "WhiteRook":
                return whiteRookModel;
            case "WhiteQueen":
                return whiteQueenModel;
            case "WhiteKing":
                return whiteKingModel;
            default:
                return whitePawnModel;
        }
    }
}
