import trainer.Trainer;

import java.util.ArrayList;

public interface Simulation {
    /**
     * Gets the list of trainers to use in the simulation.
     *
     * @return The list of trainers
     */
    ArrayList<Trainer> getTrainers();

    /**
     * Gets the level of the first Wild Codeamon to be encountered.
     *
     * @return The level of the first Wild Codeamon
     */
    int getWildLevel();

    /**
     * Gets the number of Wild Codeamon each Trainer will have to battle.
     *
     * @return The number of Wild Battles to be completed
     */
    int getWildBattles();
}
