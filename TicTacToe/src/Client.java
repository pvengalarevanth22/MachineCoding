import controller.GameController;
import exceptions.duplicateSymbolException;
import exceptions.moreThanOneBotException;
import exceptions.playersCountDimensionException;
import models.Game;
import models.GameState;

import java.util.ArrayList;

public class Client {
    public static void main(String[] args) throws moreThanOneBotException, duplicateSymbolException, playersCountDimensionException {
        GameController gameController = new GameController();

        Game game=gameController.startGame(3,new ArrayList<>(), new ArrayList<>());

        while (gameController.checkState(game).equals(GameState.IN_PROGRESS)){
            gameController.printBoard(game);
            gameController.makeMove(game);
        }

    }
}
