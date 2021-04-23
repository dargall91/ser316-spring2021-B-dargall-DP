package world;

import java.util.ArrayList;

import simulation.Simulation;
import trainer.Trainer;

/**
 * TimeCycleContext keeps track of the current state of TimeCycle, determines which state's
 * implementation of runEvents() to execute when called, determines how many Wild Codeamon each
 * Trainer will battle before the Tournament begins, and determines the level of the Wild Codeamon.
 *
 * <p>
 *     The following requirements are fulfilled by this Design Pattern and any related classes:
 *     <li>The simulation runs on cycles that consist of Day and Night</li>
 *     <li>During the day, Trainers can battle</li>
 *     <li>Trainers battle Wild Codeamon or other Trainers in the Tournament</li>
 *     <li>Trainers can recruit Wild Codeamon onto their team by defeating them</li>
 *     <li>Defeating a Codeamon earns EXP for all non-fainted Codeamon in the party</li>
 *     <li>Defeating another Trainer earns half of that Trainer's money</li>
 *     <li>During the Night all Codeamon fully heal</li>
 *     <li>Only one battle takes place at a time</li>
 *     <li>Trainers can only battle with one Codeamon at a time, but they can use another if one
 *     faints</li>
 *     <li>Attacks are turn based, with speed determining who goes first</li>
 *     <li>A Trainer battle is not over until all of one Trainer's Codeamon have fainted</li>
 * </p>
 */
public class TimeCycleContext {
    private TimeCycle state;
    private int battleCount = 0;
    private final int wildBattles;
    private final int initialLevel;
    private Tournament tournament;
    private Simulation simulation;

    /**
     * Constructor for the playable version of Codeamon. It sets the number of Wild Codeamon
     * each Trainer will battle during the Day before starting the tournament, and sets the list of
     * trainers who will compete in the tournament. The Wild Codeamon will start at a specified
     * level, and that level will increase every Day.
     *
     * @param wildBattles The number of wild Codeamon battles to occur before the tournament begins
     * @param initialLevel The level of the first Wild Codeamon. A Wild Codeamon will not be below
     *                  level 1 or above level 100, regardless of this value.
     * @param trainers The list of Trainers who will compete in the tournament
     */
    public TimeCycleContext(int wildBattles, int initialLevel, ArrayList<Trainer> trainers) {
        this.wildBattles = wildBattles;

        if (initialLevel < 1) {
            this.initialLevel = 1;
        } else {
            this.initialLevel = initialLevel;
        }

        tournament = new Tournament(trainers, true);
        simulation = null;
        setState(new Day());
    }

    /**
     * Constructor for the simulated version of Codeamon that sets up the TimeCycle based on the
     * data saved within a provided Simulation.
     *
     * @param simulation The simulation that contains the data
     */
    public TimeCycleContext(Simulation simulation) {
        this.simulation = simulation;
        this.wildBattles = simulation.getWildBattles();
        this.initialLevel = simulation.getWildLevel();
        tournament = new Tournament(simulation.getTrainers(), false);
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
     */
    public void runEvents() {
        state.runEvents(this, tournament.getBracket());
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
     * Gets the level of the Wild Codeamon to be encountered on this Day.
     *
     * @return The level of the Wild Codeamon to be encounter.
     */
    public int getWildLevel() {
        return initialLevel + battleCount;
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

    /**
     * Checks if this cycle is for the Playable version of Codeamon or not.
     *
     * @return True if this cycle is playable, otherwise false
     */
    public boolean getPlayable() {
        if (simulation == null) {
            return true;
        }

        return false;
    }
}
