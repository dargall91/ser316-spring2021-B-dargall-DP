package world;

import trainer.Trainer;

import java.util.ArrayList;

/**
 * TimeCycleContext keeps track of the current state of TimeCycle, determines which state's
 * implementation of runEvents() to execute when called, and dtermines how many Wild Codeamon each
 * Trainer will battle before the Tournament begins.
 */
public class TimeCycleContext {
    private TimeCycle state;
    private int battleCount = 0;
    private final int WILD_BATTLES;

    /**
     * Constructor that sets the initial state as Day and determines the number of Wild Codeamon
     * each Trainer will battle during the Day before starting the tournament.
     *
     * @param wildCount The number of wild Codeamon battles to occur before the tournament begins
     */
    public TimeCycleContext(int wildCount) {
        WILD_BATTLES = wildCount;
        setState(new Day());
    }

    /**
     * Sets the current state of the TimeCycle. Should not be called from outside of the TimeCycle
     * states, Day and Night.
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

    /**
     * Gets the number of Wild Codeamon battles to be completed by each Trainer before the
     * Tournament begins.
     *
     * @return The number of Wild Codeamon battles to be completed by each Trainer
     */
    public int getWildBattleLimit() {
        return WILD_BATTLES;
    }

    /**
     * Gets the number of Wild Codeamon battles to be completed by each Trainer before the
     * Tournament begins.
     *
     * @return The number of Wild Codeamon battles to be completed by each Trainer
     */
    public int getWildBattleCount() {
        return battleCount;
    }

    /**
     * Increments the number of Wild Codeamon battles that have been completed by each Trainer by 1.
     */
    public void incrementBattleCount() {
        battleCount++;
    }
}