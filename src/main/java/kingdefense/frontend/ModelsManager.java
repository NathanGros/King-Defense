package kingdefense.frontend;

import static com.raylib.Raylib.*;

public class ModelsManager {
    Model chessBoardModel;
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

    public void loadModels(Shader shadowShader) {
        chessBoardModel = LoadModel("src/main/resources/models/chessBoardModel.obj");
        chessBoardModel.materials().shader(shadowShader);
        whitePawnModel = LoadModel("src/main/resources/models/pieces/whitePawnModel.obj");
        whitePawnModel.materials().shader(shadowShader);
        whiteBishopModel = LoadModel("src/main/resources/models/pieces/whiteBishopModel.obj");
        whiteBishopModel.materials().shader(shadowShader);
        whiteKnightModel = LoadModel("src/main/resources/models/pieces/whiteKnightModel.obj");
        whiteKnightModel.materials().shader(shadowShader);
        whiteRookModel = LoadModel("src/main/resources/models/pieces/whiteRookModel.obj");
        whiteRookModel.materials().shader(shadowShader);
        whiteQueenModel = LoadModel("src/main/resources/models/pieces/whiteQueenModel.obj");
        whiteQueenModel.materials().shader(shadowShader);
        whiteKingModel = LoadModel("src/main/resources/models/pieces/whiteKingModel.obj");
        whiteKingModel.materials().shader(shadowShader);
        blackPawnModel = LoadModel("src/main/resources/models/pieces/blackPawnModel.obj");
        blackPawnModel.materials().shader(shadowShader);
        blackBishopModel = LoadModel("src/main/resources/models/pieces/blackBishopModel.obj");
        blackBishopModel.materials().shader(shadowShader);
        blackKnightModel = LoadModel("src/main/resources/models/pieces/blackKnightModel.obj");
        blackKnightModel.materials().shader(shadowShader);
        blackRookModel = LoadModel("src/main/resources/models/pieces/blackRookModel.obj");
        blackRookModel.materials().shader(shadowShader);
        blackQueenModel = LoadModel("src/main/resources/models/pieces/blackQueenModel.obj");
        blackQueenModel.materials().shader(shadowShader);
        blackKingModel = LoadModel("src/main/resources/models/pieces/blackKingModel.obj");
        blackKingModel.materials().shader(shadowShader);
        coin1Model = LoadModel("src/main/resources/models/coins/coin1Model.obj");
        coin1Model.materials().shader(shadowShader);
        coin2Model = LoadModel("src/main/resources/models/coins/coin2Model.obj");
        coin2Model.materials().shader(shadowShader);
        coin3Model = LoadModel("src/main/resources/models/coins/coin3Model.obj");
        coin3Model.materials().shader(shadowShader);
        coin4Model = LoadModel("src/main/resources/models/coins/coin4Model.obj");
        coin4Model.materials().shader(shadowShader);
        coin5Model = LoadModel("src/main/resources/models/coins/coin5Model.obj");
        coin5Model.materials().shader(shadowShader);
    }

    public void unloadModels() {
        UnloadModel(chessBoardModel);
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

    public Model getChessBoardModel() {
        return chessBoardModel;
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
