package world;

import trainer.Trainer;

import java.util.ArrayList;

/**
 * Day is a state of the TimeCycle in the Codeamon world. During the day, Trainers can wither
 * battle Wild Codeamon or participate in a Single Elimination Tournament until only one Trainer
 * remains.
 */
public class Day implements TimeCycle {
    /**
     * Runs the Daytime events of the Codeamon World. For the first X Days of the simulation, where
     * X is the
     * Trainers will battle and recruit wild Codeamon. On each day after that. they will
     * participate in a Single Elimination Tournament until only one Trainer is left. After a day's
     * events have concluded for each Trainer, it will become Night.
     *
     * @param context The context that controls switching between states
     * @param trainers The trainers participating in the simulation
     */
    @Override
    public void runEvents(TimeCycleContext context, ArrayList<Trainer> trainers) {
        if (context.getWildBattleCount() < context.getWildBattleLimit()) {
            //do wild battles
            for (Trainer t : trainers) {
                //fight a random codeamon
            }

            context.incrementBattleCount();
        } else {
            //do tournament
        }

        context.setState(new Night());
    }
}