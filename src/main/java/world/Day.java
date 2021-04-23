package world;

import codeamon.CodeamonFactory;
import codeamon.Type;
import java.util.ArrayList;
import trainer.Trainer;

/**
 * Day is a state of the TimeCycle in the Codeamon world. During the day, Trainers can wither
 * battle Wild Codeamon or participate in a Single Elimination Tournament until only one Trainer
 * remains.
 */
public class Day implements TimeCycle {
    /**
     * Runs the Daytime events of the Codeamon World. For the first X Days of the simulation, where
     * X is the TimeCycleContext, Trainers will battle and attempt to recruit Wild Codeamon onto
     * their parties. The level of the Wild Codeamon is determined by the number Days that have
     * passed and the level of the first Codeamon in the Trainer's party, and will increase each
     * Day. On each day after all Wild Battles have concluded, the Trainers will participate in a
     * Single Elimination Tournament until only one Trainer is left. After a Day's events have
     * concluded for each Trainer, it will become Night.
     *
     * @param context The context that controls switching between states
     * @param trainers The trainers participating in the simulation
     */
    @Override
    public void runEvents(TimeCycleContext context, ArrayList<Trainer> trainers) {
        if (context.getWildBattleCount() < context.getWildBattleLimit()) {
            for (int i = 0; i < trainers.size(); i++) {
                if (i == 0 && context.getPlayable()) {
                    Battle.playableWildBattle(trainers.get(i),
                            CodeamonFactory.createRandomCodeamon(context.getWildLevel()));
                } else {
                    Battle.wildBattle(trainers.get(i),
                            CodeamonFactory.createRandomCodeamon(context.getWildLevel()));
                }

                System.out.println();
            }
            //Increase the wild battle counter
            context.incrementBattleCount();
        } else {
            //Run the next round of the Tournament
            context.getTournament().executeNextRound();
        }

        System.out.println();
        context.setState(new Night());
    }
}
