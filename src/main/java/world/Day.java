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
            //do wild battles
            for (Trainer t : trainers) {
                //The level of the wild Codeamon is the level of the trainer's first Codeamom in
                //their party - 2 + the number of days that have passed. The first wild Codeamon
                //will therefore be 2 levels below the Trainer's first Codeamon
                int wildLevel = t.getCodeamonParty().get(0).getLevel() - 2
                        + context.getWildBattleCount();
                Battle.wildBattle(t, CodeamonFactory.createCodeamon(Type.Fire, wildLevel));
            }

            context.incrementBattleCount();
            context.setState(new Night());
        } else {
            Tournament tournament = context.getTournament();

            tournament.runRound();

            if (!tournament.isConcluded()) {
                context.setState(new Night());
            }
        }
    }
}
