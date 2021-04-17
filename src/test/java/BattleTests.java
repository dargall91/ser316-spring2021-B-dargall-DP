import codeamon.Codeamon;
import codeamon.CodeamonFactory;
import codeamon.Type;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import trainer.Trainer;
import world.Battle;

import static org.junit.jupiter.api.Assertions.*;

public class BattleTests {
    @AfterEach
    public void afterEach() throws Exception {
        System.out.println();
    }

    /**
     * Test a battle against a Wild Codeamon where the Trainer wins. For this Test, a Trainer
     * with a single Level 99 Codeamon will be put against a Wild Level 1 Codeamon. After the
     * battle, the Trainer should have two Codeamon in their party, their initial Codeamon should
     * have gained EXP, and the Wild Codeamon should not have gained EXP.
     *
     * @throws Exception
     */
    @Test
    public void TestWildBattleTrainerWins() throws Exception {
        System.out.println("TestWildBattleTrainerWins");

        Codeamon trainerMon = new CodeamonFactory().createCodeamon(Type.Fire, 99);
        trainerMon.setNickname("Jack");
        Codeamon wildCodeamon = new CodeamonFactory().createCodeamon(Type.Fire, 1);
        Trainer trainer = new Trainer.TrainerBuilder("Derek").codeamon(trainerMon).build();

        int initialExp = trainerMon.getExperiencePoints();

        assertAll(() -> assertTrue(Battle.wildBattle(trainer, wildCodeamon)),
                () -> assertTrue(trainerMon.getExperiencePoints() > initialExp),
                () -> assertEquals(2, trainer.getPartySize()),
                () -> assertEquals(0, wildCodeamon.getExperiencePoints()));
    }

    /**
     * Test a battle against a Wild Codeamon where the Wild Codeamon wins. For this Test, a Trainer
     * with a single Level 1 Codeamon will be put against a Wild Level 99 Codeamon. After the
     * battle, the Trainer should have one Codeamon in their party and Codeamon should not have
     * gained EXP.
     *
     * @throws Exception
     */
    @Test
    public void TestWildBattleWildWins() throws Exception {
        System.out.println("TestWildBattleWildWins");

        Codeamon trainerMon = new CodeamonFactory().createCodeamon(Type.Fire, 1);
        trainerMon.setNickname("Jack");
        Codeamon wildCodeamon = new CodeamonFactory().createCodeamon(Type.Fire, 99);
        Trainer trainer = new Trainer.TrainerBuilder("Derek").codeamon(trainerMon).build();

        int initialExp = trainerMon.getExperiencePoints();

        assertAll(() -> assertFalse(Battle.wildBattle(trainer, wildCodeamon)),
                () -> assertEquals(initialExp, trainerMon.getExperiencePoints()),
                () -> assertEquals(1, trainer.getPartySize()));
    }

    /**
     * Test a battle against a Wild Codeamon where one of the Trainer's Codeamon faints. For this
     * Test, a Trainer with a Level 1 and Level 99 Codeamon will be put against a Wild Level 30
     * Codeamon. After the battle, the Trainer should have three Codeamon in their party, and only
     * the level 99 Codeamon should have gained EXP.
     *
     * @throws Exception
     */
    @Test
    public void TestWildBattleCodeamonFaints() throws Exception {
        System.out.println("TestWildBattleCodeamonFaints");

        Codeamon tMonOne = new CodeamonFactory().createCodeamon(Type.Fire, 1);
        tMonOne.setNickname("Jack");
        Codeamon tMonTwo = new CodeamonFactory().createCodeamon(Type.Fire, 99);
        tMonTwo.setNickname("Jill");
        Codeamon wildCodeamon = new CodeamonFactory().createCodeamon(Type.Fire, 30);
        Trainer trainer = new Trainer.TrainerBuilder("Derek").codeamon(tMonOne)
                .codeamon(tMonTwo).build();

        int initialExpOne = tMonOne.getExperiencePoints();
        int initialExpTwo = tMonTwo.getExperiencePoints();

        assertAll(() -> assertTrue(Battle.wildBattle(trainer, wildCodeamon)),
                () -> assertEquals(initialExpOne, tMonOne.getExperiencePoints()),
                () -> assertTrue(tMonTwo.getExperiencePoints() > initialExpTwo),
                () -> assertEquals(3, trainer.getPartySize()));
    }

    /**
     * Test a battle between two Trainers where the first Trainer wins. For this Test, a Trainer
     * with a single Level 99 Codeamon will be put against a Trainer with a single level 1
     * Codeamon. After the battle, the first Trainer should be the winner, the second Trainer
     * should have paid out half their money to the winner, and the winning Trainer's Codeamon
     * should have gained EXP.
     *
     * @throws Exception
     */
    @Test
    public void TestTrainerBattle() throws Exception {
        System.out.println("TestTrainerBattle");

        Codeamon tOneMon = new CodeamonFactory().createCodeamon(Type.Fire, 99);
        tOneMon.setNickname("Jack");
        Codeamon tTwoMon = new CodeamonFactory().createCodeamon(Type.Fire, 1);
        tTwoMon.setNickname("Jill");

        Trainer trainerOne = new Trainer.TrainerBuilder("Derek").codeamon(tOneMon).build();
        Trainer trainerTwo = new Trainer.TrainerBuilder("Ben").codeamon(tTwoMon).build();

        int initialExpOne = tOneMon.getExperiencePoints();
        int initialExpTwo = tTwoMon.getExperiencePoints();

        assertAll(() -> assertEquals(trainerOne, Battle.trainerBattle(trainerOne, trainerTwo)),
                () -> assertTrue(tOneMon.getExperiencePoints() > initialExpOne),
                () -> assertEquals(initialExpTwo, tTwoMon.getExperiencePoints()),
                () -> assertEquals(4500, trainerOne.getCodeaDollars()),
                () -> assertEquals(1500, trainerTwo.getCodeaDollars()));
    }

    /**
     * Test a battle between two Trainers where the first Trainer wins. For this Test, a Trainer
     * with a single Level 30 Codeamon will be put against a Trainer with a level 1 and a level 99
     * Codeamon. After the battle, the second Trainer should be the winner, the first Trainer's
     * Codeamon should have gained EXP, only the second Trainer's level 99 Codeamon should have
     * gained EXP, and the first Trainer should have paid out half their money to the second.
     *
     * @throws Exception
     */
    @Test
    public void TestTrainerBattleCodeamonFaints() throws Exception {
        System.out.println("TestTrainerBattleCodeamonFaints");

        Codeamon tOneMon = new CodeamonFactory().createCodeamon(Type.Fire, 30);
        tOneMon.setNickname("Jack");
        Codeamon tTwoMonOne = new CodeamonFactory().createCodeamon(Type.Fire, 1);
        tTwoMonOne.setNickname("Jill");
        Codeamon tTwoMonTwo = new CodeamonFactory().createCodeamon(Type.Fire, 99);
        tTwoMonTwo.setNickname("James");


        Trainer trainerOne = new Trainer.TrainerBuilder("Derek").codeamon(tOneMon).build();
        Trainer trainerTwo = new Trainer.TrainerBuilder("Ben").codeamon(tTwoMonOne)
                .codeamon(tTwoMonTwo).build();

        int initialExpOne = tOneMon.getExperiencePoints();
        int initialExpMonOne = tTwoMonOne.getExperiencePoints();
        int initialExpMonTwo = tTwoMonTwo.getExperiencePoints();

        assertAll(() -> assertEquals(trainerTwo, Battle.trainerBattle(trainerOne, trainerTwo)),
                () -> assertTrue(tOneMon.getExperiencePoints() > initialExpOne),
                () -> assertTrue(tTwoMonTwo.getExperiencePoints() > initialExpMonTwo),
                () -> assertEquals(initialExpMonOne, tTwoMonOne.getExperiencePoints()),
                () -> assertEquals(1500, trainerOne.getCodeaDollars()),
                () -> assertEquals(4500, trainerTwo.getCodeaDollars()));
    }
}
