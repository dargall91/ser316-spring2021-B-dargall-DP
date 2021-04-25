package world;

import java.io.IOException;
import java.util.ArrayList;
import trainer.Trainer;

/**
 * A Single Elimination Tournament for Codeamon Trainers to compete in. Byes are given out only in
 * the first round.
 */
public class Tournament {
    private ArrayList<Trainer> bracket;
    private int currentRound;
    private final int rounds;

    /**
     * Constructor for a Tournament that sets the list of competing Codeamon Trainers.
     *
     * @param trainers The list of competing Codeamon Trainers
     */
    public Tournament(ArrayList<Trainer> trainers) {
        bracket = trainers;

        currentRound = 1;
        rounds = (int) Math.ceil(Math.log(bracket.size()) / Math.log(2));
    }

    /**
     * Runs the next round of the tournament.
     */
    public void executeNextRound() {
        int byes = 0;

        //give enough byes in the first round to avoid having to give more later
        if (currentRound == 1) {
            byes = (int) (Math.pow(2, rounds) - bracket.size());
        }

        if (currentRound < rounds) {
            System.out.println("Round " + currentRound + " of the Tournament is starting!");
        } else {
            System.out.println("The final round of the Tournament is starting!");
        }

        //track the winners
        ArrayList<Trainer> winners = new ArrayList<>();

        //Byes are considered to have won
        for (int i = 0; i < byes; i++) {
            System.out.println(bracket.get(bracket.size() - 1 - i).getName()
                    + " gets a Bye and advances straight to the next round!");
            winners.add(bracket.get(bracket.size() - 1 - i));

            for (Trainer t : winners) {
                System.out.println(t.getName());
            }
        }

        /*
        Trainers are paired up by their placement in the list. The first Trainer battles the last
        Trainer, the second Trainer battles the second to last Trainer, and so on until all
        Trainers have battled. If there are byes, the byes go to the last X Trainers in the list,
        where X is the number of byes, and the end of the list is considered to be the last Trainer
        to not get a bye.
         */
        for (int i = 0; i < (bracket.size() - byes) / 2; i++) {
            System.out.println();
            Trainer trainerOne = bracket.get(i);
            Trainer trainerTwo = bracket.get(bracket.size() - 1 - byes - i);
            Trainer winner;

            if (trainerOne.isPlayable()) {
                winner = Battle.playableTrainerBattle(trainerOne, trainerTwo);
                System.out.println("Press enter to continue.");
                try {
                    System.in.read();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (trainerTwo.isPlayable()) {
                winner = Battle.playableTrainerBattle(trainerTwo, trainerOne);
                System.out.println("Press enter to continue.");
                try {
                    System.in.read();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                winner = Battle.trainerBattle(trainerOne, trainerTwo);
            }

            if (winner == trainerOne) {
                System.out.println(trainerTwo.getName() + " was eliminated from the Tournament!");
            } else {
                System.out.println(trainerOne.getName() + " was eliminated from the Tournament!");
            }

            winners.add(winner);
        }

        bracket = winners;

        System.out.println();

        if (currentRound < rounds) {
            System.out.println("Round " + currentRound + " of the Tournament is now complete!");
        } else {
            System.out.println("The final round of the Tournament is now complete!");
        }

        currentRound++;
    }

    /**
     * Checks if the tournament has concluded. A Tournament is considered to have concluded
     * when only one Trainer remains in the bracket.
     *
     * @return True if it has concluded, false if it has not
     */
    public boolean isConcluded() {
        if (bracket.size() == 1) {
            return true;
        }

        return false;
    }

    /**
     * Gets the Winner of this Tournament. This method should not be called until the Tournament
     * has concluded, which can be checked with the isConcluded() method.
     *
     * @return The winning Trainer, or null if the Tournament has not yet concluded
     */
    public Trainer getWinner() {
        if (isConcluded()) {
            return bracket.get(0);
        }

        return null;
    }

    /**
     * Gets the list of Trainers who have not yet been eliminated from the bracket.
     *
     * @return The list of Trainers
     */
    public ArrayList<Trainer> getBracket() {
        return bracket;
    }
}
