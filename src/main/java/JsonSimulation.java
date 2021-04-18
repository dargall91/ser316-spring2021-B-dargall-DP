import trainer.Trainer;

import java.util.ArrayList;

public class JsonSimulation implements Simulation {
    private ArrayList<Trainer> trainers;
    private int wildLevel;
    private int wildBattles;

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
