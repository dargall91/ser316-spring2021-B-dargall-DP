package world;

import java.util.ArrayList;
import trainer.Trainer;

/**
 * TimeCycleContext keeps track of the current state of TimeCycle, determines which state's
 * implementation of runEvents() to execute when called, and dtermines how many Wild Codeamon each
 * Trainer will battle before the Tournament begins.
 */
public class TimeCycleContext {
    private TimeCycle state;
    private int battleCount = 0;
    private final int wildBattles;
    private Tournament tournament;

    /**
     * Constructor that sets the initial state as Day, determines the number of Wild Codeamon
     * each Trainer will battle during the Day before starting the tournament, and sets the list of
     * trainers who will compete in the tournament.
     *
     * @param wildCount The number of wild Codeamon battles to occur before the tournament begins
     * @param trainers The list of Trainers who will compete in the tournament
     */
    public TimeCycleContext(int wildCount, ArrayList<Trainer> trainers) {
        wildBattles = wildCount;
        tournament = new Tournament(trainers);
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
        return wildBattles;
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

    /**
     * Gets the tournament the Trainers are competing in.
     *
     * @return The tournament
     */
    public Tournament getTournament() {
        return tournament;
    }
}
