package game;

import game.utils.Pos;

public interface Player {

    /**
     * Set the first worker for the player
     * Effect: update that worker's position
     * Precondition: pos is a valid position
     *
     * @param pos The updated {@link Pos} position of that worker
     */
    void setWorkerA(Pos pos);

    /**
     * Set the second worker for the player
     * Effect: update that worker's position
     *Precondition: pos is a valid position
     *
     * @param pos The updated {@link Pos} position of that worker
     */
    void setWorkerB(Pos pos);

    /**
     * Get the first worker of the player
     * @return The first {@link Worker} of the player
     */
    Worker getWorkerA();

    /**
     * Get the second worker of the player
     * @return The second {@link Worker} of the player
     */
    Worker getWorkerB();
}
