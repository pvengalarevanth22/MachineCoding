package controller;

import exceptions.duplicateSymbolException;
import exceptions.moreThanOneBotException;
import exceptions.playersCountDimensionException;
import models.Game;
import models.GameState;
import models.Player;
import strategies.WinningStrategy;

import java.util.List;

public class GameController { //Stateless

    public Game startGame(int dimension, List<Player> players, List<WinningStrategy> winningStrategies) throws moreThanOneBotException, duplicateSymbolException, playersCountDimensionException {
        return Game.getBuilder()
                .SetPlayers(players)
                .setWinningStrategies(winningStrategies)
                .setDimension(dimension)
                .build();
    }

    public void printBoard(Game game) {
        game.printBoard();
    }

    public String getWinner(Game game) {
        return game.getWinner().getName();
    }
    public void makeMove(Game game) {
        game.makeMove();
    }

    public GameState checkGameState(Game game) {
        return game.getGameState();
    }

    public void undoLastMove(Game game) {
        game.undoMove();
    }
}
