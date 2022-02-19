package game;

import game.utils.Pos;

public interface Worker {
    /**
     * Move the worker to the corresponding position
     *
     * Prerequisite: {@code checkMove()} of the {@link Board} class is
     * performed with a return value of {@code true}
     *
     * @param pos The {@link Pos} position that the worker is moving to
     */
    void move(Pos pos);

    /**
     * Get the current position of the worker
     * @return The current {@link Pos} position of the worker
     */
    Pos getPos();
}
