package cli;

import game.Game;
import game.Player;
import game.impl.GameImpl;

public class ControlImpl implements Control{
    Game game;
    public ControlImpl() {
        game = new GameImpl();
    }

    @Override
    public void play() {
        setup();
        playerPickPos(1);
        playerPickPos(2);
        boolean gameEnds = false;
        while (!gameEnds) {
            Player player = game.getPlayer();
            playerTurn(player);
            gameEnds = game.isFinished();
            game.flipPlayer();
        }
        endGame(game.getWinner());
    }

    @Override
    public void setup() {

    }

    @Override
    public void playerPickPos(int playerId) {

    }

    @Override
    public void playerTurn(Player player) {

    }

    @Override
    public void endGame(Player player) {

    }
}
