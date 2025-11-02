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
    Model coin1Model;
    Model coin2Model;
    Model coin3Model;
    Model coin4Model;
    Model coin5Model;

    public ModelsManager() {
    }

    public void loadModels() {
        whitePawnModel = LoadModel("src/main/resources/models/pieces/whitePawnModel.obj");
        whiteBishopModel = LoadModel("src/main/resources/models/pieces/whiteBishopModel.obj");
        whiteKnightModel = LoadModel("src/main/resources/models/pieces/whiteKnightModel.obj");
        whiteRookModel = LoadModel("src/main/resources/models/pieces/whiteRookModel.obj");
        whiteQueenModel = LoadModel("src/main/resources/models/pieces/whiteQueenModel.obj");
        whiteKingModel = LoadModel("src/main/resources/models/pieces/whiteKingModel.obj");
        blackPawnModel = LoadModel("src/main/resources/models/pieces/blackPawnModel.obj");
        blackBishopModel = LoadModel("src/main/resources/models/pieces/blackBishopModel.obj");
        blackKnightModel = LoadModel("src/main/resources/models/pieces/blackKnightModel.obj");
        blackRookModel = LoadModel("src/main/resources/models/pieces/blackRookModel.obj");
        blackQueenModel = LoadModel("src/main/resources/models/pieces/blackQueenModel.obj");
        blackKingModel = LoadModel("src/main/resources/models/pieces/blackKingModel.obj");
        coin1Model = LoadModel("src/main/resources/models/coins/coin1Model.obj");
        coin2Model = LoadModel("src/main/resources/models/coins/coin2Model.obj");
        coin3Model = LoadModel("src/main/resources/models/coins/coin3Model.obj");
        coin4Model = LoadModel("src/main/resources/models/coins/coin4Model.obj");
        coin5Model = LoadModel("src/main/resources/models/coins/coin5Model.obj");
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
        UnloadModel(coin1Model);
        UnloadModel(coin2Model);
        UnloadModel(coin3Model);
        UnloadModel(coin4Model);
        UnloadModel(coin5Model);
    }

    public Model getPieceModel(String pieceType) {
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

    public Model getCoinsModel(Integer nbCoins) {
        switch (nbCoins) {
            case 1:
                return coin1Model;
            case 2:
                return coin2Model;
            case 3:
                return coin3Model;
            case 4:
                return coin4Model;
            case 5:
                return coin5Model;
            default:
                return coin5Model;
        }
    }
}
