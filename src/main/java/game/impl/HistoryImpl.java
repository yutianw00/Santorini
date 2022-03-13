package game.impl;

import game.History;

import java.util.ArrayList;
import java.util.List;

public class HistoryImpl<T> implements History<T> {

    private List<T> history;

    public HistoryImpl () {
        history = new ArrayList<>();
    }

    @Override
    public void addHistory(T newHistory) {
        history.add(newHistory);
    }

    @Override
    public boolean undo() {
        if (history.size() >= 1) {
            history.remove(history.size()-1);
            return true;
        } else {
            return false;
        }

    }

    @Override
    public T getLast() {
        return history.get(history.size()-1);
    }

    @Override
    public List<T> getHistory() {
        return this.history;
    }
}
