package world;

import java.util.ArrayList;
import trainer.Trainer;

public class Tournament {
    private ArrayList<Trainer> bracket;
    private int currentRound;
    private final int rounds;

    public Tournament(ArrayList<Trainer> trainers) {
        bracket = trainers;
        currentRound = 1;
        rounds = (int) Math.ceil(Math.log(trainers.size())/Math.log(2));
    }

    /**
     * Runs the next round of the tournament
     */
    public void runRound() {
        int byes = 0;

        /*
        Determine byes. Byes are only given out in the first round, and enough are given out
        to ensure that byes are not needed in further rounds

        Math Explanation:

        For a single elimination tournament with P participants and byes in only the first
        round, the number of rounds N in the tournament is the ceiling of log(P)/log(2). To
        get the number of byes, the formula is P - 2 * (P - 2 ^ (N -1)). 2 ^ (N - 1) is the
        number of players remaining in the next round to not need byes. P - that result is the
        number of matches that will take place in the next round. Multiple that number by 2 to
        get the number of remaining players there should be in that round. P - that number is
        the number of players that need byes in the first round. Using the distributive property,
        the formula can be simplified into P - (2 * P - 2 ^ N)

        Wow, I just realized this can greatly simplified. P - 2 ^ 9 = byes. I feel dumb now
         */
        if (currentRound == 1) {
            byes = (int) (bracket.size() - Math.pow(2, rounds));
        }

        if (currentRound < rounds) {
            System.out.println("Round " + currentRound + " of the Tournament is now underway!");
        } else {
            System.out.println("The final round of the Tournament is now underway!");
        }

        //track the winners
        ArrayList<Trainer> winners = new ArrayList<>();

        /*
        Trainers are paired up by their placement in the list. The first Trainer battles the last
        Trainer, the second Trainer battles the second to last Trainer, and so on until all
        Trainers have battled. If there are byes, the byes go to the last X Trainers in the list,
        where X is the number of byes.
         */
        for (int i = 0; i < bracket.size() - byes / 2; i++) {
            Trainer trainerOne = bracket.get(i);
            Trainer trainerTwo = bracket.get(bracket.size() - 1 - byes - i);
            Trainer winner = Battle.trainerBattle(trainerOne,
                    trainerTwo);

            if (winner == trainerOne) {
                System.out.println(trainerTwo + " was eliminated from the Tournament!");
                winners.add(trainerOne);
            } else {
                System.out.println(trainerOne + " was eliminated from the Tournament!");
                winners.add(trainerTwo);
            }
        }

        bracket = winners;
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
}
