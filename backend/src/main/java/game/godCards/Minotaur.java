package game.godCards;

import game.Board;
import game.Game;
import game.Player;
import game.Worker;
import game.utils.Pos;
import game.utils.State;

/**
 * Minotaur: Bull-headed Monster
 *  Your Move: Your Worker may move into an opponent Worker’s space,
 *  if their Worker can be forced one space straight backwards to an
 *  unoccupied space at any level
 */
public class Minotaur implements GodPower{

    private Game game;
    private int playerId;
    private int otherPlayerId;
    private boolean isUsing = false;

    public Minotaur(int playerId, Game game) {
        this.game = game;
        this.playerId = playerId;
        this.otherPlayerId = (playerId == 1) ? 2 : 1;
    }

    @Override
    public String description() {
        return "Minotaur";
    }

    @Override
    public boolean canDoAction(State state) {
        /*
         * The action can only be performed if it's player's turn and
         * the player is about to perform a move operation
         */
        this.isUsing = false;
        if (state == null) {
            return false;
        }
        if (state.getNextAction() != Game.MOVE) {
            return false;
        } else if (state.getPlayerId() != this.playerId) {
            return false;
        }
        return true;
    }

    @Override
    public boolean checkWin(int playerId, int workerId, Pos pos) {
        return false;
    }

    /**
     * checking the condition of if a move is valid in the Minotaur case
     * Side effect: if a minotaur scenario is formed, the forced worker is moved in advance
     *      *  to give space to the minotaur.
     *
     * @param game The current Game
     * @param workerId The id of the worker that will perform the move
     * @param pos The position to be moved
     * @return if force condition is not formed, null is returned
     *  otherwise return a new {@link Board} with the forced worker moving to its forced
     *  position in advance
     */
    private Board checkMinotaurCondition(Game game, int workerId, Pos pos) {
        Board board = game.getBoard();
        Worker currWorker = board.getWorker(playerId, workerId);
        Pos currPos = currWorker.getPos();
        if (!board.isInBound(pos)) {
            return null;
        } else if (!Board.isAdjacent(pos, currPos)) {
            return null;
        } else if (board.hasWorker(pos)) {
            int otherPlayerId = (playerId == 1) ? 2 : 1;
            Worker otherWorker1 = board.getWorker(otherPlayerId, 1);
            Worker otherWorker2 = board.getWorker(otherPlayerId, 2);
            Board newBoard1, newBoard2;
            newBoard1 = validForce(board, currPos, pos, otherWorker1, 1);
            newBoard2 = validForce(board, currPos, pos, otherWorker2, 2);
            if (newBoard1 != null) {
                return newBoard1;
            } else if (newBoard2 != null) {
                return newBoard2;
            } else {
                return null;
            }
        } else {
            return board.copyBoard();
        }
    }

    /**
     * Valid if a board force scenario is formed for the other worker.
     * Side effect: if a minotaur scenario is formed, the forced worker is moved in advance
     *  to give space to the minotaur.
     * @param board The board that the valid force condition will be examined on
     * @param currPos The current position of the worker
     * @param targetPos The target position that the worker is planning to move to
     * @param otherWorker The other player's worker that could possibly take the target position's place
     * @return if force condition is not formed, null is returned
     *  otherwise return a new {@link Board} with the forced worker moving to its forced
     *  position in advance
     */
    private Board validForce(Board board, Pos currPos, Pos targetPos, Worker otherWorker,
                             int otherWorkerId) {
        Pos otherPos = otherWorker.getPos();

        /* if the target position does hot have a worker occupied,
         * the minotaur force move condition doesn't hold
         */
        if (!targetPos.equals(otherPos)) {
            return null;
        }

        int r1 = currPos.getRow();
        int c1 = currPos.getCol();
        int r2 = otherPos.getRow();
        int c2 = otherPos.getCol();

        int forcedR = r2 * 2 - r1;
        int forcedC = c2 * 2 - c1;
        Pos forcedPos = new Pos(forcedR, forcedC);

        /* if the forced position is not on the board, or if it is
         * occupied by other workers, such minotaur move is not valid
         */
        if (!board.isInBound(forcedPos) || board.hasWorker(forcedPos)) {
            return null;
        }

        /*
         * otherwise if a minotaur condition is valid, the forced worker is moved in advance
         * to its forced place to give space to the minotaur worker
         */
        Board newBoard = board.setBoardWorker(otherPlayerId, otherWorkerId, forcedPos);
        game.getPlayer(1).linkWorker(newBoard, 1);
        game.getPlayer(2).linkWorker(newBoard, 2);

        game.setBoard(newBoard);

        return newBoard;

    }

    @Override
    public State action(int workerId, Pos pos) {
        Board newBoard = checkMinotaurCondition(game, workerId, pos);
        if (newBoard != null) {
            game.setBoard(newBoard);
        }
        this.isUsing = false;
        return game.move(playerId, workerId, pos);

    }

    @Override
    public void storeInfo(Pos pos) {
        // no need to store, dummy function
    }

    @Override
    public State setupPower(int workerId) {
        game.setNextAction(Game.USEPOWER);

        Board board = game.getBoard();
        int playerId = game.getNextPlayerId();
        Worker selectedWorker = board.getWorker(playerId, workerId);
        Board newBoard = board.chooseGrid(selectedWorker.getPos());

        this.isUsing = true;
        State newState = new State(newBoard, playerId, game.getNextAction(), workerId);
        newState.usePower(game.getNextPlayerId());

        game.getHistory().add(newState);
        return newState;
    }

    @Override
    public boolean isUsing() {
        return this.isUsing;
    }
}
