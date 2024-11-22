package models;

import java.util.Scanner;

public class Player {
    private Long id;
    private String name;
    private Symbol symbol;
    private PlayerType playerType;
    private Scanner scanner;

    public Player(Long id, String name, Symbol symbol,PlayerType playerType) {
        this.id = id;
        this.name = name;
        this.symbol = symbol;
        this.playerType=playerType;
        this.scanner = new Scanner(System.in);
    }

    public Move makeMove(Board board) {
        System.out.println("Please tell row where u want to move");
        int row = scanner.nextInt();

        System.out.println("Please tell column where u want to move");
        int column = scanner.nextInt();

        return new Move(new Cell(row,column),this);

    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public void setSymbol(Symbol symbol) {
        this.symbol = symbol;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
    }

}
