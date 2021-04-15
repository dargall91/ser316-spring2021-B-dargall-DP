package world;

import codeamon.Codeamon;
import trainer.Trainer;

import java.util.Random;

/**
 * The Battle class is for conducting Codeamon Battles, either between two Trainers or between a
 * Trainer and a Wild Codeamon.
 */
public class Battle {
    /**
     * Conducts a battle between a Trainer and a Wild Codeamon. The battle will run until either
     * the Trainer or the Wild Codeamon is defeated. A Trainer is considered to have been defeated
     * if all Codeamon in their party have fainted. A Wild Codeamon is considered to have been
     * defeated if it is fainted. If a Trainer defeats a Wild Codeamon, it will join the Trainer's
     * party, provided the Trainer's party is not full.
     *
     * @param trainer The Codeamon Trainer
     * @param wildCodeamon The Wild Codeamon
     */
    public static void battle(Trainer trainer, Codeamon wildCodeamon) {
        Codeamon trainerCodeamon = trainer.getNextCodeamon();

        while (trainer.getRemainingPartySize() > 0 && !wildCodeamon.isFainted()) {
            if (trainerCodeamon.getSpeed() > wildCodeamon.getSpeed()) {
                fight(trainerCodeamon, wildCodeamon);
            } else if (trainerCodeamon.getSpeed() < wildCodeamon.getSpeed()) {
                fight(wildCodeamon, trainerCodeamon);
            } else {
                //For speed ties, select a Codeamon at random
                //If 0, trainer goes first, if 1 wild goes first
                Random rand = new Random();

                int first = rand.nextInt(1);

                if (first == 0) {
                    fight(trainerCodeamon, wildCodeamon);
                } else {
                    fight(wildCodeamon, trainerCodeamon);
                }
            }
        }
    }

    /**
     * Conducts a battle between two Codeamon Trainers. The battle will run until one Trainer is
     * has been defeated. A Trainer is considered to have been defeated if all Codeamon in their
     * party have fained.
     *
     * @param trainerOne The first Trainer in the battle
     * @param trainerTwo The second Trainer in the battle
     * @return The Trainer who won the battle
     */
    public static Trainer battle(Trainer trainerOne, Trainer trainerTwo) {
        Codeamon tOneMon = trainerOne.getNextCodeamon();
        Codeamon tTwoMon = trainerTwo.getNextCodeamon();

        while (trainerOne.getRemainingPartySize() > 0 && trainerTwo.getRemainingPartySize() > 0) {
            if (tOneMon.getSpeed() > tTwoMon.getSpeed()) {
                fight(tOneMon, tTwoMon);
            } else if (tOneMon.getSpeed() < tTwoMon.getSpeed()) {
                fight(tTwoMon, tOneMon);
            } else {
                //For speed ties, select a Codeamon at random
                //If 0, trainerOne goes first, if 1 trainerTwo goes first
                Random rand = new Random();

                int first = rand.nextInt(1);

                if (first == 0) {
                    fight(tOneMon, tTwoMon);
                } else {
                    fight(tTwoMon, tOneMon);
                }
            }
        }

        //Get the winner
        if (trainerOne.getRemainingPartySize() == 0) {
            return trainerTwo;
        } else {
            return trainerOne;
        }
    }



    /**
     * Handles the logic for two Codeamon battling each other.
     *
     * @param first The first Codeamon to act in this round
     * @param second The second Codeamon to act in this round
     */
    private static void fight(Codeamon first, Codeamon second) {
        if (first.isFainted()) {
            System.out.println(first.getName() + " fainted!");
            return;
        }

        if (second.isFainted()) {
            System.out.println(first.getName() + " fainted!");;
        }
    }
}
