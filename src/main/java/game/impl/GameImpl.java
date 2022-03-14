package game.impl;

import game.*;
import game.utils.Pos;
import game.utils.State;

import java.util.Arrays;
import java.util.List;

public class GameImpl implements Game {

    private int nextAction;

    History<State> history = new HistoryImpl<>();

    private Board board;
    private Player p1;
    private Player p2;
    private Player winner;
    private int nextPlayerId;

    @Override
    public void setBoard(Board newBoard) {
        this.board = newBoard;
    }

    @Override
    public boolean undo() {
        return history.undo();
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

    public GameImpl() {
        Worker w11 = new WorkerImpl();
        Worker w12 = new WorkerImpl();
        p1 = new PlayerImpl(w11, w12);

        Worker w21 = new WorkerImpl();
        Worker w22 = new WorkerImpl();
        p2 = new PlayerImpl(w21, w22);

        board = new BoardImpl(w11, w12, w21, w22);

        winner = null;
        nextPlayerId = 1;
        nextAction = SETUP;
    }

    private State createState(Board board) {
        return new State(board, nextPlayerId, nextAction);
    }

    @Override
    // TODO: may need to change for the Minotaur case
    public boolean isFinished() {
        Pos p1APos = p1.getWorkerA().getPos();
        Pos p1BPos = p1.getWorkerB().getPos();
        Pos p2APos = p2.getWorkerA().getPos();
        Pos p2BPos = p2.getWorkerB().getPos();

        for (Pos pos : Arrays.asList(p1APos, p1BPos)) {
            if (board.getGrid(pos).getLevels() == WINTOWERLEVEL) {
                winner = p1;
                return true;
            }
        }

        for (Pos pos : Arrays.asList(p2APos, p2BPos)) {
            if (board.getGrid(pos).getLevels() == WINTOWERLEVEL) {
                winner = p2;
                return true;
            }
        }

        return false;

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
        if (winner == null) {
            return -1;
        } else {
            return (winner == p1) ? 1 : 2;
        }
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

    @Override
    public State move(int playerId, int workerId, Pos pos) {
        Worker worker = getWorker(playerId, workerId);
        if (!board.checkMove(worker, pos)) {
            return null;
        }

        Board newBoard = board.setBoardWorker(playerId, workerId, pos); // delegation
        this.board = newBoard; // assign the newBoard to be the board of the game

        this.setNextAction(BUILD);
        State newState = createState(newBoard);
        history.addHistory(newState);
        return newState;
    }

//    @Override
//    public State moveWithoutCheck(int playerId, int workerId, Pos pos) {
//        Board newBoard = board.copyBoard();
//        newBoard.setBoardWorker(playerId, workerId, pos); // delegation
//        this.setNextAction(BUILD);
//        State newState = createState(newBoard);
//        history.addHistory(newState);
//        return newState;
//    }

    @Override
    public State build(int playerId, int workerId, Pos pos) {
        Worker worker = getWorker(playerId, workerId);
        if (!board.checkBuild(worker, pos)) {
            return null;
        }
        Board newBoard = board.build(pos);
        this.board = newBoard;

        this.setNextAction(MOVE);
        this.flipPlayer();
        State newState = createState(newBoard);
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

        Player currPlayer = (playerId == 1) ? p1 : p2;


        currPlayer.setWorker(pos, workerId);

        if (playerId == 2 && playerId == 2) {
            this.setNextAction(MOVE);
        }

        if (workerId == 2) {
            flipPlayer();
        }

        State newState = createState(board.setBoardWorker(playerId, workerId, pos));
        history.addHistory(newState);
        return newState;

    }
}
