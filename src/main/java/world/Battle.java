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
     * @return True if the Trainer won the battle, otherwise false
     */
    public static boolean wildBattle(Trainer trainer, Codeamon wildCodeamon) {
        Codeamon trainerCodeamon = trainer.getNextCodeamon();

        System.out.println("A wild " + wildCodeamon.getName() + " appeared!");
        printTrainerParty(trainer);
        System.out.println(trainer.getName() + " sent out " + trainerCodeamon.getName() + "!");

        while (trainer.getRemainingPartySize() > 0 && !wildCodeamon.isFainted()) {
            fight(trainerCodeamon, wildCodeamon);

            //if trainer's Codeamon faints and they have more Codeamon, bring in the next one
            if (trainerCodeamon.isFainted() && trainer.getRemainingPartySize() > 0) {
                printTrainerParty(trainer);
                trainerCodeamon = trainer.getNextCodeamon();
                System.out.println(trainer.getName() + " sent out " + trainerCodeamon.getName() + "!");
            }
        }

        if (trainer.getRemainingPartySize() > 0) {
            wildCodeamon.giveExperience(trainer.getCodeamonParty());

            if (trainer.getPartySize() < 6) {
                System.out.println("The Wild " + wildCodeamon.getName() + " joined "
                        + trainer.getName() + "'s party!");
                trainer.addCodeamon(wildCodeamon);
            }

            return true;
        }

        printTrainerParty(trainer);
        System.out.println(trainer.getName() + " is out of usable Codeamon!");
        System.out.println(trainer.getName() + " fled from the Wild " + wildCodeamon.getName()
                + "!");

        return false;
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
    public static Trainer trainerBattle(Trainer trainerOne, Trainer trainerTwo) {
        Codeamon tOneMon = trainerOne.getNextCodeamon();
        Codeamon tTwoMon = trainerTwo.getNextCodeamon();

        System.out.println("The Battle Between " + trainerOne.getName() + " and "
                + trainerTwo.getName() + " is now underway!");

        printTrainerParty(trainerOne);
        printTrainerParty(trainerTwo);

        System.out.println(trainerOne.getName() + " sent out " + tOneMon.getName() + "!");
        System.out.println(trainerTwo.getName() + " sent out " + tTwoMon.getName() + "!");

        while (trainerOne.getRemainingPartySize() > 0 && trainerTwo.getRemainingPartySize() > 0) {
            fight(tOneMon, tTwoMon);

            //check if either Codeamon has fainted, then give out EXP and swap to next Codeamon
            if (tOneMon.isFainted()) {
                tOneMon.giveExperience(trainerTwo.getCodeamonParty());

                printTrainerParty(trainerOne);
                printTrainerParty(trainerTwo);

                if (trainerOne.getRemainingPartySize() > 0) {
                    tOneMon = trainerOne.getNextCodeamon();
                    System.out.println(trainerOne.getName() + " sent out " + tOneMon.getName() + "!");
                }
            } else if (tTwoMon.isFainted()) {
                tTwoMon.giveExperience(trainerOne.getCodeamonParty());

                printTrainerParty(trainerOne);
                printTrainerParty(trainerTwo);

                if (trainerTwo.getRemainingPartySize() > 0) {
                    tTwoMon = trainerTwo.getNextCodeamon();
                    System.out.println(trainerTwo.getName() + " sent out " + tTwoMon.getName() + "!");
                }
            }
        }

        //Get the winner
        if (trainerOne.getRemainingPartySize() == 0) {
            System.out.println(trainerTwo.getName() + " defeated " + trainerOne.getName() + "!");
            trainerOne.payout(trainerTwo);

            return trainerTwo;
        } else {
            System.out.println(trainerOne.getName() + " defeated " + trainerTwo.getName() + "!");
            trainerTwo.payout(trainerOne);

            return trainerOne;
        }
    }

    /**
     * Handles the logic for two Codeamon battling each other. First determines the turn order
     * for the round, then the two Codeamon use their attacks.
     *
     * @param monOne The first Codeamon in the battle
     * @param monTwo The second Codeamon in the battle
     */
    private static void fight(Codeamon monOne, Codeamon monTwo) {
        if (monOne.getSpeedStat() > monTwo.getSpeedStat()) {
            attack(monOne, monTwo);
        } else if (monOne.getSpeedStat() < monTwo.getSpeedStat()) {
            attack(monTwo, monOne);
        } else {
            //For speed ties, select a Codeamon at random
            //If 0, monOne goes first, if 1 monTwo goes first
            Random rand = new Random();

            int turn = rand.nextInt(2);

            if (turn == 0) {
                attack(monOne, monTwo);
            } else {
                attack(monTwo, monOne);
            }
        }
    }

    /**
     * The two Codeamon in the battle exchange attacks in turn order.
     *
     * @param first The first Codeamon to act in this round
     * @param second The second Codeamon to act in this round
     */
    private static void attack(Codeamon first, Codeamon second) {
        //first attacks
        first.attack(second);

        if (second.isFainted()) {
            return;
        }

        second.attack(first);
    }

    /**
     * Prints a representation of how many Codeamon a trainer has remaining. "O" represents a
     * non-fainted Codeamon and "X" represents a fainted Codeamon.
     *
     * @param trainer The Trainer to display
     */
    private static void printTrainerParty(Trainer trainer) {
        System.out.print(trainer.getName() + ": ");

        for (Codeamon c : trainer.getCodeamonParty()) {
            if (c.isFainted()) {
                System.out.print("X");
            } else {
                System.out.print("O");
            }
        }

        System.out.println();
    }
}
