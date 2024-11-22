package strategies.WinningStrategies;

import models.Board;
import models.Move;
import models.Symbol;
import strategies.WinningStrategy;

import java.util.HashMap;
import java.util.Map;

public class DiagnolWinningStrategy implements WinningStrategy {

    Map<Symbol,Integer> leftDiagnolMap = new HashMap<>();
    Map<Symbol,Integer> rightDiagnolMap = new HashMap<>();

    @Override
    public boolean checkWinner(Board board, Move move) {
        int col=move.getCell().getCol();
        int row=move.getCell().getRow();
        Symbol symbol=move.getPlayer().getSymbol();

        if(row==col){
            leftDiagnolMap.put(symbol,leftDiagnolMap.getOrDefault(symbol,0)+1);

            if(leftDiagnolMap.get(symbol).equals(board.getSize())){
                return true;
            }
        }



        if(row+col==board.getSize()-1){
            rightDiagnolMap.put(symbol,rightDiagnolMap.getOrDefault(symbol,0)+1);

            if(rightDiagnolMap.get(symbol).equals(board.getSize())){
                return true;
            }
        }

        return false;
    }

    @Override
    public void undoGame(Board board, Move move) {
        int col=move.getCell().getCol();
        int row=move.getCell().getRow();
        Symbol symbol=move.getPlayer().getSymbol();

        if(row==col){
            leftDiagnolMap.put(symbol,leftDiagnolMap.get(symbol)-1);
        }
        if(row+col==board.getSize()-1){
            rightDiagnolMap.put(symbol,rightDiagnolMap.get(symbol)-1);
        }
    }

}
