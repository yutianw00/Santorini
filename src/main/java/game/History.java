package game;

import java.util.List;

public interface History<T> {

    /**
     * add a new state/event into the current history
     * @param newHistory the new state/event to be added to the history
     */
    void addHistory(T newHistory);

    /**
     * undo the previous step, remove the previous step from the history
     * @return true if undo successful, false if no history is stored
     */
    boolean undo();

    /**
     * get the most recent history stored
     * @return The most recent history stored
     */
    T getLast();

    /**
     * get a list of all histories
     * @return the list of all histories
     */
    List<T> getHistory();
}
