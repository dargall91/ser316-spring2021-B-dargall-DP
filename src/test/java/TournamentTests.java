import codeamon.Codeamon;
import codeamon.CodeamonFactory;
import codeamon.Type;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import trainer.Trainer;
import world.Tournament;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit Tests for Attack
 */
public class TournamentTests {
    @AfterEach
    public void afterEach() throws Exception {
        System.out.println();
    }

    /**
     * Test that Trainers are eliminated from the bracket when they lose. After the first round,
     * there should be 4 Trainers remaining out of the original 8.
     *
     * @throws Exception
     */
    @Test
    public void TestTournamentEliminations() throws Exception {
        System.out.println("TestTournamentEliminations");

        ArrayList<Trainer> trainers = new ArrayList<>();

        for (int i = 0; i < 8; i++) {
            Codeamon mon = CodeamonFactory.createCodeamon(Type.Fire, i * 10);
            mon.setNickname("Mon " + i);
            trainers.add(new Trainer.TrainerBuilder("Trainer " + i).codeamon(mon).build());
        }

        Tournament tournament = new Tournament(trainers, false);

        System.out.println("Trainers Before Tournament Begins:");

        for (Trainer t : tournament.getBracket()) {
            System.out.println(t.getName());
        }

        tournament.executeNextRound();

        System.out.println("Trainers After First Round of Tournament:");

        for (Trainer t : tournament.getBracket()) {
            System.out.println(t.getName());
        }

        assertEquals(4, tournament.getBracket().size());
    }

    /**
     * Test that Trainers are eliminated from the bracket when they lose. After the first round,
     * there should be 4 Trainers remaining out of the original 8.
     *
     * @throws Exception
     */
    @Test
    public void TestTournamentEliminationsWithByes() throws Exception {
        System.out.println("TestTournamentEliminationsWithByes");

        ArrayList<Trainer> trainers = new ArrayList<>();

        for (int i = 0; i < 15; i++) {
            Codeamon mon = CodeamonFactory.createCodeamon(Type.Fire, i + 10);
            mon.setNickname("Mon " + i);
            trainers.add(new Trainer.TrainerBuilder("Trainer " + i).codeamon(mon).build());
        }

        Tournament tournament = new Tournament(trainers, false);

        System.out.println("Trainers Before Tournament Begins:");

        for (Trainer t : tournament.getBracket()) {
            System.out.println(t.getName());
        }

        tournament.executeNextRound();

        System.out.println("Trainers After First Round of Tournament:");

        for (Trainer t : tournament.getBracket()) {
            System.out.println(t.getName());
        }

        assertEquals(8, tournament.getBracket().size());
    }

    /**
     * Test that an entire tournament functions as intended. By the end of the tournament, the
     * Trainer Derek should be the winning Trainer. THis will be tested with a simple 4 Trainer
     * Tournament.
     *
     * @throws Exception
     */
    @Test
    public void TestFullTournament() throws Exception {
        System.out.println("TestFullTournament");

        Codeamon derekMon = CodeamonFactory.createCodeamon(Type.Fire, 100);
        Trainer derek = new Trainer.TrainerBuilder("Derek").codeamon(derekMon).build();

        ArrayList<Trainer> trainers = new ArrayList<>();

        trainers.add(derek);

        for (int i = 0; i < 3; i++) {
            Codeamon mon = CodeamonFactory.createCodeamon(Type.Fire, 1);
            mon.setNickname("Mon " + i);
            trainers.add(new Trainer.TrainerBuilder("Trainer " + i).codeamon(mon).build());
        }

        Tournament tournament = new Tournament(trainers, false);

        System.out.println("Trainers Before Tournament Begins:");

        for (Trainer t : tournament.getBracket()) {
            System.out.println(t.getName());
        }

        while (!tournament.isConcluded()) {
            tournament.executeNextRound();
            System.out.println("Trainers Remaining:");

            for (Trainer t : tournament.getBracket()) {
                System.out.println(t.getName());
            }
        }

        assertEquals(derek, tournament.getWinner());
    }
}