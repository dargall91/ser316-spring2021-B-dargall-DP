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
     * have gained EXP, and the Wild Codeamon should not have gained EXP. In theory, the RNG could
     * choose the non-damaging move of the Trainer's Codeamon every turn, causing the Trainer to
     * lose the battle. However, it is very unlikely that the RNG would not select a damaging move
     * before the level 1 Codeamon wins because it will be doing very little damage per attack.
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
     * gained EXP. In theory, the RNG could choose the non-damaging move of the Wild Codeamon every
     * turn, causing the Wild Codeamon to lose the battle. However, it is very unlikely that the
     * RNG would not select a damaging move before the level 1 Codeamon wins because it will be
     * doing very little damage per attack.
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
                () -> assertTrue(trainerMon.getExperiencePoints() == initialExp),
                () -> assertEquals(1, trainer.getPartySize()));
    }
}
