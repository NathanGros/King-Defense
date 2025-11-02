package kingdefense.frontend;

import static com.raylib.Raylib.*;

public class ModelsManager {
    Model whitePawnModel;
    Model whiteBishopModel;
    Model whiteKnightModel;
    Model whiteRookModel;
    Model whiteQueenModel;
    Model whiteKingModel;
    Model blackPawnModel;
    Model blackBishopModel;
    Model blackKnightModel;
    Model blackRookModel;
    Model blackQueenModel;
    Model blackKingModel;

    public ModelsManager() {
    }

    public void loadModels() {
        whitePawnModel = LoadModel("src/main/resources/models/whitePawnModel.obj");
        whiteBishopModel = LoadModel("src/main/resources/models/whiteBishopModel.obj");
        whiteKnightModel = LoadModel("src/main/resources/models/whiteKnightModel.obj");
        whiteRookModel = LoadModel("src/main/resources/models/whiteRookModel.obj");
        whiteQueenModel = LoadModel("src/main/resources/models/whiteQueenModel.obj");
        whiteKingModel = LoadModel("src/main/resources/models/whiteKingModel.obj");
        blackPawnModel = LoadModel("src/main/resources/models/blackPawnModel.obj");
        blackBishopModel = LoadModel("src/main/resources/models/blackBishopModel.obj");
        blackKnightModel = LoadModel("src/main/resources/models/blackKnightModel.obj");
        blackRookModel = LoadModel("src/main/resources/models/blackRookModel.obj");
        blackQueenModel = LoadModel("src/main/resources/models/blackQueenModel.obj");
        blackKingModel = LoadModel("src/main/resources/models/blackKingModel.obj");
    }

    public void unloadModels() {
        UnloadModel(whitePawnModel);
        UnloadModel(whiteBishopModel);
        UnloadModel(whiteKnightModel);
        UnloadModel(whiteRookModel);
        UnloadModel(whiteQueenModel);
        UnloadModel(whiteKingModel);
        UnloadModel(blackPawnModel);
        UnloadModel(blackBishopModel);
        UnloadModel(blackKnightModel);
        UnloadModel(blackRookModel);
        UnloadModel(blackQueenModel);
        UnloadModel(blackKingModel);
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
            case "BlackPawn":
                return blackPawnModel;
            case "BlackBishop":
                return blackBishopModel;
            case "BlackKnight":
                return blackKnightModel;
            case "BlackRook":
                return blackRookModel;
            case "BlackQueen":
                return blackQueenModel;
            case "BlackKing":
                return blackKingModel;
            default:
                return whitePawnModel;
        }
    }
}
