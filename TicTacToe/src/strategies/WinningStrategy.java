package strategies;

import models.Board;
import models.Move;

public interface WinningStrategy {
    boolean checkWinner(Board board, Move move);

    void undoGame(Board board, Move move);
}
