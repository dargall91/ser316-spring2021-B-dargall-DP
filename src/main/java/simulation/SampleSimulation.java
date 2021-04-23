package simulation;

import codeamon.GrassCodeamon;
import codeamon.NormalCodeamon;
import codeamon.PsychicCodeamon;
import codeamon.WaterCodeamon;
import java.util.ArrayList;
import trainer.Trainer;

/**
 * Contains the Data needed to run a Codeamon Simulation.Simulation without json input. Each
 * Trainer begins with a single level 20 Codeamon and will battle 10 Wild Codeamon that start at
 * Level 16 in this simulation.
 */
public class SampleSimulation implements Simulation {
    private ArrayList<Trainer> trainers;
    private int wildLevel;
    private int wildBattles;

    public SampleSimulation(int wildBattles, int wildLevel, ArrayList<Trainer> trainers) {
        this.wildBattles = wildBattles;
        this.wildLevel = wildLevel;
        this.trainers = trainers;
    }

    /**
     * Creates the trainers to be used in the Sample Simulation.Simulation, and sets the level of
     * the wild Codeamon and the number of Wild Codeamon to battle.
     */
    public SampleSimulation() {
        trainers = new ArrayList<>();

        trainers.add(new Trainer.TrainerBuilder("Derek")
                .codeamon(new GrassCodeamon(20))
                .build());
        trainers.add(new Trainer.TrainerBuilder("Ben")
                .codeamon(new WaterCodeamon(20))
                .build());
        trainers.add(new Trainer.TrainerBuilder("Chris")
                .codeamon(new PsychicCodeamon(20))
                .build());
        trainers.add(new Trainer.TrainerBuilder("Burgundy")
                .codeamon(new NormalCodeamon(20))
                .build());

        wildLevel = 16;
        wildBattles = 10;
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
