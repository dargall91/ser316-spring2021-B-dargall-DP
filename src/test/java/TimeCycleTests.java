import codeamon.Codeamon;
import codeamon.CodeamonFactory;
import codeamon.Type;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import trainer.Trainer;
import world.TimeCycle;
import world.TimeCycleContext;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TimeCycleTests {
    @AfterEach
    public void afterEach() throws Exception {
        System.out.println();
    }

    /**
     * Test that a Day where Trainers battle Wild Codeamon works as intended. For this test, the
     * single Trainer should have recruited a single level 5 Codeamon to join their party.
     *
     * @throws Exception
     */
    @Test
    public void TestWildDay() throws Exception {
        System.out.println("TestWildDay");

        Codeamon mon = CodeamonFactory.createCodeamon(Type.Poison, 100);
        mon.setNickname("Jack");
        Trainer trainer = new Trainer.TrainerBuilder("Derek").codeamon(mon).build();

        ArrayList<Trainer> list = new ArrayList<>();
        list.add(trainer);

        TimeCycleContext timeCycle = new TimeCycleContext(1, 5, list);
        timeCycle.runEvents(list);

        System.out.println(trainer.getName() + ":");

        for (Codeamon c : trainer.getCodeamonParty()) {
            System.out.println(c.getName() + ": Level: " + c.getLevel());
        }

        assertAll(() -> assertEquals(2, trainer.getPartySize()),
                () -> assertEquals(5, trainer.getCodeamonParty().get(1).getLevel()));
    }

    /**
     * Test that a Night executes as intended. For this test, the Trainer's Codeamon should be at
     * full Hit Points despite faining in the previous wild battle.
     *
     * @throws Exception
     */
    @Test
    public void TestNight() throws Exception {
        System.out.println("TestNight");

        Codeamon mon = CodeamonFactory.createCodeamon(Type.Fire, 1);
        mon.setNickname("Jack");
        Trainer trainer = new Trainer.TrainerBuilder("Derek").codeamon(mon).build();

        ArrayList<Trainer> list = new ArrayList<>();
        list.add(trainer);

        TimeCycleContext timeCycle = new TimeCycleContext(1, 100, list);
        timeCycle.runEvents(list);
        timeCycle.runEvents(list);

        System.out.println(trainer.getName() + ":");

        System.out.println(mon.getName() + ": " + mon.getCurrentHitPoints() + "/"
                + mon.getMaxHitPoints());

        assertEquals(mon.getCurrentHitPoints(), mon.getMaxHitPoints());
    }

    /**
     * Test that a Day where Trainers compete in a Tournament works as intended. For this test,
     * trainerOne should be the winner of the Tournament.
     *
     * @throws Exception
     */
    @Test
    public void TestTournamentDay() throws Exception {
        System.out.println("TestTournamentDay");

        Codeamon monOne = CodeamonFactory.createCodeamon(Type.Dragon, 100);
        monOne.setNickname("Jack");
        Trainer trainerOne = new Trainer.TrainerBuilder("Derek").codeamon(monOne).build();

        Codeamon monTwo = CodeamonFactory.createCodeamon(Type.Flying, 1);
        monTwo.setNickname("Jill");
        Trainer trainerTwo = new Trainer.TrainerBuilder("Ben").codeamon(monTwo).build();

        ArrayList<Trainer> list = new ArrayList<>();
        list.add(trainerOne);
        list.add(trainerTwo);

        TimeCycleContext timeCycle = new TimeCycleContext(0, 5, list);
        timeCycle.runEvents(list);

        System.out.println("Winner: " + timeCycle.getTournament().getWinner().getName());

        assertEquals(trainerOne, timeCycle.getTournament().getWinner());
    }

    /**
     * Test the entire cycle of Day (Wild Battles) -> Night -> Day (Tournament) works as intended.
     * For this test, derek should have recruited 5 additional Codeamon to their party and won the
     * Tournament. All other Trainers should have recruited no additional Codeamon.
     *
     * @throws Exception
     */
    @Test
    public void TestFullCycle() throws Exception {
        System.out.println("TestFullCycle");

        Codeamon jack = CodeamonFactory.createCodeamon(Type.Water, 100);
        jack.setNickname("Jack");
        Trainer derek = new Trainer.TrainerBuilder("Derek").codeamon(jack).build();

        ArrayList<Trainer> list = new ArrayList<>();
        list.add(derek);

        for (int i = 0; i < 3; i++) {
            Codeamon mon = CodeamonFactory.createCodeamon(Type.Ground, 1);
            mon.setNickname("Mon " + i);
            Trainer trainerTwo = new Trainer.TrainerBuilder("Trainer" + i).codeamon(mon).build();

            list.add(trainerTwo);
        }

        TimeCycleContext timeCycle = new TimeCycleContext(5, 20, list);

        while (!timeCycle.getTournament().isConcluded()) {
            timeCycle.runEvents(list);
        }

        System.out.println("Winner: " + timeCycle.getTournament().getWinner().getName());

        assertEquals(derek, timeCycle.getTournament().getWinner());

        assertAll(() -> assertEquals(6, derek.getPartySize()),
                () ->  assertEquals(derek, timeCycle.getTournament().getWinner()),
                () -> assertEquals(1, list.get(1).getPartySize()),
                () -> assertEquals(1, list.get(2).getPartySize()),
                () -> assertEquals(1, list.get(3).getPartySize()));
    }
}
