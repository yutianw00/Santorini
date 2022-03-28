package game.impl;

import game.History;
import game.utils.State;

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
    public T undo() {
        if (history.size() > 1) {
            history.remove(history.size()-1);
            return history.get(history.size()-1);
        } else {
            return null;
        }

    }

    @Override
    public T getLast() {
        if (history.size() == 0) {
            return null;
        }
        return history.get(history.size()-1);
    }

    @Override
    public List<T> getHistory() {
        return this.history;
    }
}
