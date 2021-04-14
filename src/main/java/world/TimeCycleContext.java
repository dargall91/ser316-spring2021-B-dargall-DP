package world;

import trainer.Trainer;

import java.util.ArrayList;

/**
 * TimeCycleContext keeps track of the current state of TimeCycle and determines which state's
 * implementation of runEvents() to execute when called.
 */
public class TimeCycleContext {
    private TimeCycle state;

    /**
     * Constructor that sets the initial state as Day.
     */
    public TimeCycleContext() {
        setState(new Day());
    }

    /**
     * Sets the current state of the TimeCycle. SHould not be called from outside of the TimeCycle
     * states Day and Night.
     *
     * @param state The new state to be set
     */
    public void setState(final TimeCycle state) {
        this.state = state;
    }

    /**
     * Executes the current state's implementation of runEvents().
     *
     * @param trainers A list of Trainers participating in the simulation.
     */
    public void runEvents(ArrayList<Trainer> trainers) {
        state.runEvents(this, trainers);
    }
}
