package game;

import java.util.List;

public interface History<T> {
    void addHistory(T newHistory);
    boolean undo();
    T getLast();
    List<T> getHistory();
}
