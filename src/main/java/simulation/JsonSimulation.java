package simulation;

import java.util.ArrayList;
import trainer.Trainer;

/**
 * Contains the Data for a Codeamon Simulation that is read from a .json file.
 */
public class JsonSimulation implements Simulation {
    private ArrayList<Trainer> trainers;
    private int wildLevel;
    private int wildBattles;

    /**
     * Sets the the data for the simulation by reading a .json file.
     *
     * @param file The .json file that has the simulation data
     */
    public JsonSimulation(String file) {
        trainers = new ArrayList<>();
    }

    @Override
    public ArrayList<Trainer> getTrainers() {
        return trainers;
    }

    @Override
    public int getWildLevel() {
        return wildLevel;
    }

    @Override
    public int getWildBattles() {
        return wildBattles;
    }
}
