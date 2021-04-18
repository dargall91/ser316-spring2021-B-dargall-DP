package simulation;

import codeamon.GrassCodeamon;
import codeamon.NormalCodeamon;
import codeamon.PsychicCodeamon;
import codeamon.WaterCodeamon;
import java.util.ArrayList;
import trainer.Trainer;

/**
 * Contains the Data needed to run a Codeamon Simulation.Simulation without json input.
 */
public class SampleSimulation implements Simulation {
    private ArrayList<Trainer> trainers;
    private int wildLevel;
    private int wildBattles;

    /**
     * Creates the trainers to be used in the Sample Simulation.Simulation, and sets the level of the wild
     * Codeamon and the number of Wild Codeamon to battle.
     */
    public SampleSimulation() {
        trainers = new ArrayList<>();

        trainers.add(new Trainer.TrainerBuilder("Derek")
                .codeamon(new GrassCodeamon(10))
                .build());
        trainers.add(new Trainer.TrainerBuilder("Ben")
                .codeamon(new WaterCodeamon(10))
                .build());
        trainers.add(new Trainer.TrainerBuilder("Chris")
                .codeamon(new PsychicCodeamon(10))
                .build());
        trainers.add(new Trainer.TrainerBuilder("Burgundy")
                .codeamon(new NormalCodeamon(10))
                .build());

        wildLevel = 8;
        wildBattles = 5;
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
