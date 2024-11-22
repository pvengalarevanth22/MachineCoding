import controller.GameController;
import exceptions.duplicateSymbolException;
import exceptions.moreThanOneBotException;
import exceptions.playersCountDimensionException;
import models.*;

import strategies.WinningStrategy;
import strategies.WinningStrategies.ColWinningStrategy;
import strategies.WinningStrategies.RowWinningStrategy;
import strategies.WinningStrategies.DiagnolWinningStrategy;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws moreThanOneBotException, duplicateSymbolException, playersCountDimensionException {
        GameController gameController = new GameController();

        Scanner scanner = new Scanner(System.in);


        int dimensionOfGame = 3;
        List<Player> players = new ArrayList<>();
        players.add(
                new Player(1L, "Revanth", new Symbol('X'), PlayerType.HUMAN)
        );

        players.add(
                new Bot(2L, "Bot", new Symbol('O'), BotDifficultyLevel.EASY)
        );

        List<WinningStrategy> winningStrategies = new ArrayList<>();
        winningStrategies.add(new RowWinningStrategy());
        winningStrategies.add(new ColWinningStrategy());
        winningStrategies.add(new DiagnolWinningStrategy());

        Game game=gameController.startGame(dimensionOfGame, players, winningStrategies);

        while(gameController.checkGameState(game).equals(GameState.IN_PROGRESS)){
            gameController.printBoard(game);

            System.out.println("Do you want to undo.Press y/n to undo");
            String undoStatus = scanner.next();
            if(undoStatus.equals("y")){
                gameController.undoLastMove(game);
                continue;
            }

            gameController.makeMove(game);
        }

        gameController.printBoard(game);

        if(gameController.checkGameState(game).equals(GameState.WIN)){
            System.out.println("Congratulations! " + gameController.getWinner(game) + " won the game!");
        } else if (gameController.checkGameState(game).equals(GameState.DRAW)) {
            System.out.println("Game Draw");
        }
    }
}
