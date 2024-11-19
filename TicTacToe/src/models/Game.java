package models;

import exceptions.duplicateSymbolException;
import exceptions.moreThanOneBotException;
import exceptions.playersCountDimensionException;
import strategies.WinningStrategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Game {
    private List<Player> players;
    private List<WinningStrategy> winningStrategies;
    private Board board;
    private GameState gameState;
    private int nextMovePlayerIndex;
    private Player winner;
    private List<Move> moves;

    private Game(int dimension,List<Player> players,List<WinningStrategy> winningStrategies) {
        this.players = players;
        this.winningStrategies = winningStrategies;
        this.nextMovePlayerIndex=0;
        this.gameState=GameState.IN_PROGRESS;
        this.moves=new ArrayList<>();
        this.board=new Board(dimension);
    }

    public static Builder getBuilder() {
        return new Builder();
    }

    public static class Builder {
        private List<Player> players;
        private List<WinningStrategy> winningStrategies;
        private int dimension;

        public Builder() {
            this.players = new ArrayList<>();
            this.winningStrategies = new ArrayList<>();
            this.dimension = 0;
        }

        public Builder SetPlayers(List<Player> players) {
            this.players = players;
            return this;
        }

        public Builder addPlayer(Player player) {
            this.players.add(player);
            return this;
        }

        public  Builder setWinningStrategies(List<WinningStrategy> winningStrategy) {
            this.winningStrategies=winningStrategy;
            return  this;
        }

        public Builder addWinningStrategy(WinningStrategy winningStrategy) {
            this.winningStrategies.add(winningStrategy);
            return this;
        }

        public Builder setDimension(int dimension) {
            this.dimension = dimension;
            return this;
        }

        public Game build() throws moreThanOneBotException, duplicateSymbolException, playersCountDimensionException {
            validate();
            return new Game(dimension, players, winningStrategies);
        }
        private void validate() throws moreThanOneBotException, playersCountDimensionException, duplicateSymbolException {
            validateBotCount();
            validateDimensionsAndPlayerCount();
            validateUniquePlayerSymbol();
        }

        private void validateBotCount() throws moreThanOneBotException {
            int botCount=0;
            for(Player p:players){
                if(p.getPlayerType().equals(PlayerType.BOT)){
                    botCount++;
                }
            }
            if(botCount>1){
                throw new moreThanOneBotException();
            }
        }

        private  void validateDimensionsAndPlayerCount() throws playersCountDimensionException {
            if(players.size()!=dimension-1){
                throw new playersCountDimensionException();
            }
        }

        private void validateUniquePlayerSymbol() throws duplicateSymbolException {
            Map<Character,Integer> symbolsCount=new HashMap<>();
            for(Player p:players){
                if(!symbolsCount.containsKey(p.getSymbol().getAchar())){
                    symbolsCount.put(p.getSymbol().getAchar(),1);
                }
                else{
                    symbolsCount.put(p.getSymbol().getAchar(),symbolsCount.get(p.getSymbol().getAchar())+1);
                }

                if(symbolsCount.get(p.getSymbol().getAchar())>1){
                    throw new duplicateSymbolException();
                }
            }
        }
    }

    public void printBoard() {
        board.printBoard();
    }

    public GameState getGameState() {
        return gameState;
    }

    public void makeMove() {
        Player currentMovePlayer=players.get(nextMovePlayerIndex);

        System.out.println("It is "+currentMovePlayer.getName() +"s turn" + ".Please make a move"  );

        Move move =currentMovePlayer.makeMove();

        System.out.println("Current move is "+move.getCell().getRow() + " | "+move.getCell().getCol());

        //TODO //Validation

        int row=move.getCell().getRow();
        int col=move.getCell().getCol();

        Cell cellToChange=board.getBoard().get(row).get(col);
        cellToChange.setCellState(CellState.FILLED);
        cellToChange.setPlayer(currentMovePlayer);

        Move finalMove=new Move(cellToChange,currentMovePlayer);
        moves.add(finalMove);
    }
}
