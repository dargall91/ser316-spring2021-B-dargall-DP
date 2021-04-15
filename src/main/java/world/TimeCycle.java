package world;

import trainer.Trainer;

import java.util.ArrayList;

/**
 * TimeCycle is an interface for classes that switch between the two states for the time of day in
 * the Codeamon world, Day and Night. Different events can happen during different times of a day.
 */
public interface TimeCycle {
    void runEvents(TimeCycleContext timeCycleContext, ArrayList<Trainer> trainers);
}
