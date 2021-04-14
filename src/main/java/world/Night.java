package world;

import trainer.Trainer;

import java.util.ArrayList;

/**
 * Night is a state of the TimeCycle in the Codeamon world. During the night, Trainers and their
 * Codeamon will rest, and their Codeamon will recover all lost Hit Points.
 */
public class Night implements TimeCycle {
    /**
     * Runs the Nighttime events of the Codeamon World. Each night a Trainer's Codeamon will rest
     * and recover all lost Hit Points, after which it will become Day.
     *
     * @param timeCycleContext The context that controls switching between states
     * @param trainers The trainers participating in the simulation
     */
    @Override
    public void runEvents(TimeCycleContext timeCycleContext, ArrayList<Trainer> trainers) {

    }
}
