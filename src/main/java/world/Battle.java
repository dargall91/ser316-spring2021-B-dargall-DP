package world;

import codeamon.Codeamon;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

import trainer.Trainer;

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
        trainer.printPartyStatus();
        System.out.println(trainer.getName() + " sent out " + trainerCodeamon.getName() + "!");

        while (trainer.getRemainingPartySize() > 0 && !wildCodeamon.isFainted()) {
            System.out.print(trainer.getName() + "'s ");
            trainerCodeamon.printBattleStatus();

            System.out.print("Wild ");
            wildCodeamon.printBattleStatus();

            fight(trainerCodeamon, wildCodeamon);

            //if trainer's Codeamon faints and they have more Codeamon, bring in the next one
            if (trainerCodeamon.isFainted() && trainer.getRemainingPartySize() > 0) {
                trainer.printPartyStatus();
                trainerCodeamon = trainer.getNextCodeamon();
                System.out.println(trainer.getName() + " sent out "
                        + trainerCodeamon.getName() + "!");
            }

            System.out.println();
        }

        if (trainer.getRemainingPartySize() > 0) {
            wildCodeamon.giveExperience(trainer.getParty());

            if (trainer.getPartySize() < 6) {
                System.out.println("The Wild " + wildCodeamon.getName() + " joined "
                        + trainer.getName() + "'s party!");
                trainer.addCodeamon(wildCodeamon);
            }

            return true;
        }

        trainer.printPartyStatus();
        System.out.println(trainer.getName() + " is out of usable Codeamon!");
        System.out.println(trainer.getName() + " fled from the Wild " + wildCodeamon.getName()
                + "!");

        return false;
    }

    /**
     * A playable battle between a Trainer and a Wild Codeamon. The battle will run until either
     * the Trainer or the Wild Codeamon is defeated. A Trainer is considered to have been defeated
     * if all Codeamon in their party have fainted. A Wild Codeamon is considered to have been
     * defeated if it is fainted. If a Trainer defeats a Wild Codeamon, it will join the Trainer's
     * party, provided the Trainer's party is not full.
     *
     * @param player The player's Trainer
     * @param wildMon The Wild Codeamon
     * @return True if the Trainer won the battle, otherwise false
     */
    public static boolean playableWildBattle(Trainer player, Codeamon wildMon) {
        Codeamon playerMon = player.getNextCodeamon();
        System.out.println("A wild " + wildMon.getName() + " appeared!");
        player.printPartyStatus();
        System.out.println(player.getName() + " sent out " + playerMon.getName() + "!");

        while (player.getRemainingPartySize() > 0 && !wildMon.isFainted()) {
            System.out.print(player.getName() + "'s ");
            playerMon.printBattleStatus();

            System.out.print("Wild ");
            wildMon.printBattleStatus();

            int choice = playerChoice(player, playerMon);

            if (choice == 5) {
                playerMon = swapCodeamon(player, playerMon);
                playableAttackSwitch(playerMon, wildMon);
            } else {
                playableFight(playerMon, choice, wildMon);
            }

            //if trainer's Codeamon faints and they have more Codeamon, bring in the next one
            if (playerMon.isFainted() && player.getRemainingPartySize() > 0) {
                player.printPartyStatus();
                playerMon = player.getNextCodeamon();
                System.out.println(player.getName() + " sent out "
                        + playerMon.getName() + "!");
            }

            System.out.println();
        }

        if (player.getRemainingPartySize() > 0) {
            wildMon.giveExperience(player.getParty());

            if (player.getPartySize() < 6) {
                System.out.println("The Wild " + wildMon.getName() + " joined "
                        + player.getName() + "'s party!");
                player.addCodeamon(wildMon);
            }

            return true;
        }

        player.printPartyStatus();
        System.out.println(player.getName() + " is out of usable Codeamon!");
        System.out.println(player.getName() + " fled from the Wild " + wildMon.getName()
                + "!");

        System.out.println("Press enter to continue.");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }

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
        System.out.println("The Battle Between " + trainerOne.getName() + " and "
                + trainerTwo.getName() + " is now underway!");

        trainerOne.printPartyStatus();
        trainerTwo.printPartyStatus();

        Codeamon codeamonOne = trainerOne.getNextCodeamon();
        Codeamon codeamonTwo = trainerTwo.getNextCodeamon();

        System.out.println(trainerOne.getName() + " sent out " + codeamonOne.getName() + "!");
        System.out.println(trainerTwo.getName() + " sent out " + codeamonTwo.getName() + "!");

        while (trainerOne.getRemainingPartySize() > 0 && trainerTwo.getRemainingPartySize() > 0) {
            System.out.print(trainerOne.getName() + "'s ");
            codeamonOne.printBattleStatus();
            System.out.print(trainerTwo.getName() + "'s ");
            codeamonTwo.printBattleStatus();

            fight(codeamonOne, codeamonTwo);

            //check if either Codeamon has fainted, then give out EXP and swap to next Codeamon
            if (codeamonOne.isFainted()) {
                codeamonOne.giveExperience(trainerTwo.getParty());

                System.out.println();
                trainerOne.printPartyStatus();
                trainerTwo.printPartyStatus();

                if (trainerOne.getRemainingPartySize() > 0) {
                    codeamonOne = trainerOne.getNextCodeamon();
                    System.out.println(trainerOne.getName() + " sent out "
                            + codeamonOne.getName() + "!");
                }
            } else if (codeamonTwo.isFainted()) {
                codeamonTwo.giveExperience(trainerOne.getParty());

                System.out.println();
                trainerOne.printPartyStatus();
                trainerTwo.printPartyStatus();

                if (trainerTwo.getRemainingPartySize() > 0) {
                    codeamonTwo = trainerTwo.getNextCodeamon();
                    System.out.println(trainerTwo.getName() + " sent out "
                            + codeamonTwo.getName() + "!");
                }
            }

            System.out.println();
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
     * A playable battle between two Codeamon Trainers. The battle will run until one Trainer is
     * has been defeated. A Trainer is considered to have been defeated if all Codeamon in their
     * party have fained. The player controls player.
     *
     * @param player The player's Trainer
     * @param opponent The second Trainer in the battle
     * @return The Trainer who won the battle
     */
    public static Trainer playableTrainerBattle(Trainer player, Trainer opponent) {
        System.out.println("The Battle Between " + player.getName() + " and "
                + opponent.getName() + " is now underway!");

        player.printPartyStatus();
        opponent.printPartyStatus();

        Codeamon playerMon = player.getNextCodeamon();
        Codeamon opponentMon = opponent.getNextCodeamon();

        System.out.println(player.getName() + " sent out " + playerMon.getName() + "!");
        System.out.println(opponent.getName() + " sent out " + opponentMon.getName() + "!");

        while (player.getRemainingPartySize() > 0 && opponent.getRemainingPartySize() > 0) {
            System.out.print(player.getName() + "'s ");
            playerMon.printBattleStatus();
            System.out.print(opponent.getName() + "'s ");
            opponentMon.printBattleStatus();

            int choice = playerChoice(player, playerMon);

            if (choice == 5) {
                playerMon = swapCodeamon(player, playerMon);
                playableAttackSwitch(playerMon, opponentMon);
            } else {
                playableFight(playerMon, choice, opponentMon);
            }

            //check if either Codeamon has fainted, then give out EXP and swap to next Codeamon
            if (playerMon.isFainted()) {
                playerMon.giveExperience(opponent.getParty());

                System.out.println();
                player.printPartyStatus();
                opponent.printPartyStatus();

                if (player.getRemainingPartySize() > 0) {
                    playerMon = player.getNextCodeamon();
                    System.out.println(player.getName() + " sent out "
                            + playerMon.getName() + "!");
                }
            } else if (opponentMon.isFainted()) {
                opponentMon.giveExperience(player.getParty());

                System.out.println();
                player.printPartyStatus();
                opponent.printPartyStatus();

                if (opponent.getRemainingPartySize() > 0) {
                    opponentMon = opponent.getNextCodeamon();
                    System.out.println(opponent.getName() + " sent out "
                            + opponentMon.getName() + "!");
                }
            }

            System.out.println();
        }

        //Get the winner
        if (player.getRemainingPartySize() == 0) {
            System.out.println(opponent.getName() + " defeated " + player.getName() + "!");
            player.payout(opponent);

            return opponent;
        } else {
            System.out.println(player.getName() + " defeated " + opponent.getName() + "!");
            opponent.payout(player);

            return player;
        }
    }

    /**
     * Handles the logic for two Codeamon attacking each other. First determines the turn order
     * for the round, then the two Codeamon use their attacks.
     *
     * @param monOne The first Codeamon in the battle
     * @param monTwo The second Codeamon in the battle
     */
    private static void fight(Codeamon monOne, Codeamon monTwo) {
        System.out.println();

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
     * Handles the logic for two Codeamon attacking each other when one is controlled by the
     * player. First determines the turn order for the round, then the two Codeamon use their
     * attacks.
     *
     * @param playableMon The first Codeamon in the battle
     * @param attack The number of the attack for the player's Codeamon
     * @param opponent The second Codeamon in the battle
     */
    private static void playableFight(Codeamon playableMon, int attack, Codeamon opponent) {
        System.out.println();

        if (playableMon.getSpeedStat() > opponent.getSpeedStat()) {
            playableAttackFirst(playableMon, attack, opponent);
        } else if (playableMon.getSpeedStat() < opponent.getSpeedStat()) {
            playableAttackSecond(playableMon, attack, opponent);
        } else {
            //For speed ties, select a Codeamon at random
            //If 0, playableMon goes first, if 1 opponent goes first
            Random rand = new Random();

            int turn = rand.nextInt(2);

            if (turn == 0) {
                playableAttackFirst(playableMon, attack, opponent);
            } else {
                playableAttackSecond(playableMon, attack, opponent);
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
        first.attack(second);

        if (second.isFainted()) {
            return;
        }

        second.attack(first);
    }

    /**
     * Handles the Player's Codeamon attacking the opponent first.
     *
     * @param playableMon The player's Codeamon
     * @param attack The number of the attack for the player's Codeamon
     * @param opponent The player's opponent
     */
    private static void playableAttackFirst(Codeamon playableMon, int attack, Codeamon opponent) {
        System.out.print("Your ");
        playableMon.attack(opponent, attack);

        if (opponent.isFainted()) {
            return;
        }

        System.out.print("Enemy ");
        opponent.attack(playableMon);
    }

    /**
     * Handles the opponent attacking the Player's Codeamon first.
     *
     * @param playableMon The player's Codeamon
     * @param attack The number of the attack for the player's Codeamon
     * @param opponent The player's opponent
     */
    private static void playableAttackSecond(Codeamon playableMon, int attack, Codeamon opponent) {
        System.out.print("Enemy ");
        opponent.attack(playableMon);

        if (playableMon.isFainted()) {
            return;
        }

        System.out.print("Your ");
        playableMon.attack(opponent, attack);
    }

    /**
     * Handles the opponents attack if the player chooses to switch Codeamon.
     *
     * @param playableMon The player's Codeamon
     * @param opponent The player's opponent
     */
    private static void playableAttackSwitch(Codeamon playableMon, Codeamon opponent) {
        System.out.print("Enemy ");
        opponent.attack(playableMon);
    }

    /**
     * The player chooses either an attack or to swap Codeamon.
     *
     * @param player The player's Trainer
     * @param codeamon The player's active Codeamon
     * @return
     */
    private static int playerChoice(Trainer player, Codeamon codeamon) {
        Scanner scan = new Scanner(System.in);
        int choice = -1;

        do {
            System.out.println();
            codeamon.printAttacks();
            System.out.println("5. Switch Codeamon");
            System.out.print("Choose an attack or to switch Codeamon: ");

            try {
                choice = scan.nextInt();
                scan.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Please select a number from 1-5.");
                choice = -1;
                scan.next();
            }

            if (choice < 1 || choice > 5) {
                System.out.println("Please select a number from 1-5.");
            } else if (choice == 5 && player.getRemainingPartySize() == 1) {
                System.out.println("You don't have any other Codeamon to switch to.");
                choice = -1;
            }
        } while ((choice < 1 || choice > 5));

        return choice;
    }

    /**
     * Handles the player swapping Codeamon.
     *
     * @param player The player's Trainer
     * @param playerMon The player's active Codeamon
     */
    private static Codeamon swapCodeamon(Trainer player, Codeamon playerMon) {
        System.out.println();

        Codeamon swap;

        do {
            swap = player.chooseCodeamon();

            if (swap == playerMon) {
                System.out.println(swap.getName() + " is already in battle!");
            } else {
                System.out.println("You recalled " + playerMon.getName() + " and sent out "
                        + swap.getName() + "!");
                playerMon.resetStages();
            }
        } while (swap == playerMon);

        System.out.println();

        return swap;
    }
}
