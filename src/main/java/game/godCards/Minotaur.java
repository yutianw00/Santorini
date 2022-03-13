package game.godCards;

import game.Board;
import game.Game;
import game.Worker;
import game.utils.Pos;
import game.utils.State;

public class Minotaur implements GodPower{

    private Game game;
    private int playerId;
    private int otherPlayerId;

    public Minotaur(Game game, int playerId) {
        this.game = game;
        this.playerId = playerId;
        this.otherPlayerId = (playerId == 1) ? 2 : 1;
    }

    @Override
    public String description() {
        return "Minotaur: Bull-headed Monster\n" +
                "Your Move: Your Worker may " +
                "move into an opponent Worker’s " +
                "space, if their Worker can be " +
                "forced one space straight backwards to an " +
                "unoccupied space at any level.";
    }

    @Override
    public boolean canDoAction(State state) {
        /*
         * The action can only be performed if it's player's turn and
         * the player is about to perform a move operation
         */
        if (state.getNextAction() != Game.MOVE) {
            return false;
        } else if (state.getPlayerId() != this.playerId) {
            return false;
        }
        return true;
    }

    /**
     * checking the condition of if a move is valid in the Minotaur case
     *
     * @param game The current Game
     * @param workerId The id of the worker that will perform the move
     * @param pos The position to be moved
     * @return -1 if invalid, 0 if normal move condition, 1 if other player's
     *  worker is forced backward
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
     * Valid if a board force senario is formed.
     * Side effect: if a minotaur senario is formed, the forced worker is moved in advance
     *  to give space to the minotaur.
     * @param board
     * @param currPos
     * @param targetPos
     * @param otherWorker
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

        return newBoard;

    }

    @Override
    public State action(int workerId, Pos pos) {
        Board newBoard = checkMinotaurCondition(game, workerId, pos);
        if (newBoard != null) {
            game.setBoard(newBoard);
        }
        return game.move(playerId, workerId, pos);

    }
}
