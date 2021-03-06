package game.impl;

import game.Board;
import game.Game;
import game.History;
import game.Player;
import game.Worker;
import game.utils.Pos;
import game.utils.State;

import java.util.Arrays;
import java.util.List;

public class GameImpl implements Game {

    private int nextAction;

    private History<State> history = new HistoryImpl<>();

    private Board board;
    private Player p1;
    private Player p2;
    private int winnerId;
    private int nextPlayerId;

    private int chosenWorkerId = 1;

    private boolean hasFinished = false;

    public GameImpl() {
        Worker w11 = new WorkerImpl();
        Worker w12 = new WorkerImpl();
        p1 = new PlayerImpl(w11, w12);

        Worker w21 = new WorkerImpl();
        Worker w22 = new WorkerImpl();
        p2 = new PlayerImpl(w21, w22);

        board = new BoardImpl(w11, w12, w21, w22);

        winnerId = -1; // no winner yet
        nextPlayerId = 1;
        nextAction = SETUP;
        hasFinished = false;

        history.addHistory(createState(board, chosenWorkerId));
    }

    @Override
    public void restore(State state) {
        this.board = state.getBoard();

        p1.linkWorker(board, 1);
        p2.linkWorker(board, 2);
        this.winnerId = state.getPlayerId();
        this.nextPlayerId = state.getPlayerId();
        this.hasFinished = state.getNextAction() == Game.FINISH;
    }

    @Override
    public Player getPlayer(int playerId) {
        return (playerId == 1 ) ? p1 : p2;
    }

    @Override
    public int getChosenWorkerId() {
        return this.chosenWorkerId;
    }

    @Override
    public State chooseMove(Pos pos) {
        Worker w1 = getWorker(nextPlayerId, 1);
        Worker w2 = getWorker(nextPlayerId, 2);
        if (w1.getPos().equals(pos)) {
            chosenWorkerId = 1;
        } else if (w2.getPos().equals(pos)) {
            chosenWorkerId = 2;
        } else {
            System.out.println("ChooseMove error: the pos is not player worker's position!");
            return null;
        }
        Board newBoard = board.chooseGrid(pos);
        this.board = newBoard;
        this.nextAction = MOVE;
        State newState = createState(board, chosenWorkerId);
        history.addHistory(newState);
        return newState;
    }


    @Override
    public void setBoard(Board newBoard) {
        this.board = newBoard;
    }

    @Override
    public State getCurrState() {
        return history.getLast();
    }

    @Override
    public State undo() {
        return history.undo();
    }

    @Override
    public void setWinner(int playerId) {
        this.winnerId = playerId;
    }

    @Override
    public List<State> getHistory() {
        return history.getHistory();
    }

    @Override
    public void setNextAction(int action) {
        this.nextAction = action;
    }

    @Override
    public int getNextAction() {
        return this.nextAction;
    }

    @Override
    public Board getBoard() {
        return this.board;
    }

    @Override
    public State createState(Board board, int setWorkerId) {
        return new State(board, nextPlayerId, nextAction, setWorkerId);
    }

    @Override
    public boolean isFinished() {
        return this.hasFinished;
    }

    @Override
    public int getNextPlayerId() {
        return nextPlayerId;
    }

    /* by design, this function is called automatically within after the build
     * is successful and finished.
     * It can also be called with Godpower in special cases.
     */
    @Override
    public void flipPlayer() {
        if (nextPlayerId == 1) {
            nextPlayerId = 2;
        } else {
            nextPlayerId = 1;
        }

    }

    @Override
    public int getWinner() {
        return winnerId;
    }

    Worker getWorker(int playerId, int workerId) {
        if (playerId == 1) {
            if (workerId == 1) {
                return p1.getWorkerA();
            } else {
                return p1.getWorkerB();
            }
        } else {
            if (workerId == 1) {
                return p2.getWorkerA();
            } else {
                return p2.getWorkerB();
            }
        }
    }

    private void checkWinCondition(int playerId, Pos prevPos, Pos nextPos) {
        if (board.getGrid(prevPos).getLevels() != WINTOWERLEVEL
                && board.getGrid(nextPos).getLevels() == WINTOWERLEVEL) {
            this.hasFinished = true;
            this.winnerId = playerId;
        }
    }

    @Override
    public State move(int playerId, int workerId, Pos pos) {
        Worker worker = getWorker(playerId, workerId);
        if (!board.checkMove(worker, pos)) {
            return null;
        }

        checkWinCondition(playerId, worker.getPos(), pos);

        this.chosenWorkerId = workerId;

        Board newBoard = board.setBoardWorker(playerId, workerId, pos); // delegation
        p1.linkWorker(newBoard, 1);
        p2.linkWorker(newBoard, 2);

        newBoard = newBoard.chooseGrid(pos); // highlight the moved position

        this.board = newBoard; // assign the newBoard to be the board of the game

        if (this.hasFinished) {
            this.setNextAction(FINISH);
        } else {
            this.setNextAction(BUILD);
        }

        State newState = createState(newBoard, chosenWorkerId);

        history.addHistory(newState);
        return newState;
    }

    @Override
    public State build(int playerId, int workerId, Pos pos) {
        Worker worker = getWorker(playerId, workerId);
        if (!board.checkBuild(worker, pos)) {
            System.out.println("Board checkBuild: build with worker" + workerId + "at pos "
                    + worker.getPos() + "and pos " + pos.toString() + " invalid!");
            return null;
        }
        Board newBoard = board.build(pos);
        this.board = newBoard;

        this.setNextAction(CHOOSEMOVE);
        this.flipPlayer();
        State newState = createState(newBoard, chosenWorkerId);
        history.addHistory(newState);
        return newState;
    }

    @Override
    public State setWorker(int playerId, int workerId, Pos pos) {

        if (!board.isInBound(pos)) {
            System.out.println("Game.setWorker: position is out of bound.");
            return null;
        }
        if (board.hasWorker(pos)) {
            System.out.println("Game.setWorker: position is occupied.");
            return null;
        }

        if (playerId == 2 && workerId == 2) {
            this.setNextAction(CHOOSEMOVE);
        }

        if (workerId == 2) {
            flipPlayer();
        }

        this.chosenWorkerId = workerId == 1 ? 2 : 1;

        Board newBoard = board.setBoardWorker(playerId, workerId, pos);
        p1.linkWorker(newBoard, 1);
        p2.linkWorker(newBoard, 2);
        this.board = newBoard;

        State newState = createState(newBoard, chosenWorkerId);
        history.addHistory(newState);
        return newState;

    }

    @Override
    public State usePower(int playerId, int workerId, Pos pos) {
        // in normal mode, no god exists and no power can be used
        return null;
    }

    @Override
    public State setupPower(int playerId, int workerId) {
        // in normal mode, no god exists and no power can be used
        return null;
    }
}
