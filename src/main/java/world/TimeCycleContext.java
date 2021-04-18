package world;

import java.util.ArrayList;
import trainer.Trainer;

/**
 * TimeCycleContext keeps track of the current state of TimeCycle, determines which state's
 * implementation of runEvents() to execute when called, determines how many Wild Codeamon each
 * Trainer will battle before the Tournament begins, and determines the level of the Wild Codeamon.
 *
 * The following requirements are fulfilled by this Design Pattern and any related classes:
 *
 * The simulation runs on cycles that consist of Day and Night
 *
 * During the day, Trainers can battle
 *
 * Trainers battle Wild Codeamon or other Trainers in the Tournament
 *
 * Trainers can recruit Wild Codeamon onto their team by defeating them
 *
 * Defeating a Codeamon earns EXP for all non-fainted Codeamon in the party
 *
 * Defeating another Trainer earns half of that Trainer's money
 *
 * During the Night all Codeamon fully heal
 *
 * Only one battle takes place at a time
 *
 * Trainers can only battle with one Codeamon at a time, but they can use another if one faints
 *
 * Attacks are turn based, with speed determining who goes first
 *
 * A Trainer battle is not over until all of one Trainer's Codeamon have fainted
 *
 *
 *
 */
public class TimeCycleContext {
    private TimeCycle state;
    private int battleCount = 0;
    private final int wildBattles;
    private final int initialLevel;
    private Tournament tournament;

    /**
     * Constructor that sets the initial state as Day, determines the number of Wild Codeamon
     * each Trainer will battle during the Day before starting the tournament, and sets the list of
     * trainers who will compete in the tournament. The Wild Codeamon will start at a specified
     * level, and that level will increase every Day.
     *
     * @param wildBattles The number of wild Codeamon battles to occur before the tournament begins
     * @param initialLevel The level of the first Wild Codeamon. A Wild Codeamon will no tbe below
     *                  level 1 or above level 100, regardless of this value.
     * @param trainers The list of Trainers who will compete in the tournament
     */
    public TimeCycleContext(int wildBattles, int initialLevel, ArrayList<Trainer> trainers) {
        this.wildBattles = wildBattles;
        this.initialLevel = initialLevel;
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
}
