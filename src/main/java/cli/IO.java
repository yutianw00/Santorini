package cli;

import game.Board;

public interface IO {
    void printWelcome();
    void printPrompt(int playeId);
    void printBoard(Board board);
    void printWinner(int playerId);
}
