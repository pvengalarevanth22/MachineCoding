package strategies.BotPlaying;

import models.Board;
import models.Cell;
import models.CellState;
import models.Move;
import strategies.BotPlayingStrategy;

import java.util.List;

public class EasyBotPlayingStrategy implements BotPlayingStrategy {
    @Override
    public Move makeMove(Board board) {
        for(List<Cell> row:board.getBoard()){
            for(Cell cell:row){
                if(cell.getCellState().equals(CellState.EMPTY)){
                    return new Move(cell,null);
                }
            }
        }
        return null;
    }
}
