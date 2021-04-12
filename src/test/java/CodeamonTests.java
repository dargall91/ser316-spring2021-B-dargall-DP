import codeamon.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CodeamonTests {
    @AfterEach
    public void afterEach() throws Exception {
        System.out.println();
    }

    /**
     * Test that a Codeamon's attributes are properly set
     *
     * @throws Exception
     */
    @Test
    public void TestCodeamonAttributes() throws Exception {
        System.out.println("TestCodeamonAttributes");

        Type type = Type.Fire;
        int level = 10;
        Codeamon mon = CodeamonFactory.getCodeamon(type, level);

        System.out.println("Name: " + mon.getName());
        System.out.println("Level: " + mon.getLevel());
        System.out.println("Type: " + mon.getType());

        assertAll("Confirm a Codeamon's initial attributes are properly set",
                () -> assertEquals(mon.getSpeciesName(), mon.getName()),
                () -> assertEquals(level, mon.getLevel()),
                () -> assertEquals(type, mon.getType()));
    }

    /**
     * Test that a Codeamon created below level 1 is level 1
     *
     * @throws Exception
     */
    @Test
    public void TestInvalidLevelCodeamon() throws Exception {
        System.out.println("TestInvalidLevelCodeamon");

        Type type = Type.Fire;
        int level = 1;
        Codeamon mon = CodeamonFactory.getCodeamon(type, 0);

        System.out.println("Level: " + mon.getLevel());

        assertEquals(level, mon.getLevel());
    }

    /**
     * A test that ensures that a Codeamon's nickname is properly set
     *
     * @throws Exception
     */
    @Test
    public void TestCodeamonNickname() throws Exception {
        System.out.println("TestCodeamonNickname");

        Type type = Type.Fire;
        int level = 10;
        String nickname = "Billy Bob Jones";

        Codeamon mon = CodeamonFactory.getCodeamon(type, level);
        mon.setNickname(nickname);

        System.out.println("Nickname: " + mon.getName());

        assertEquals(nickname, mon.getName());
    }

    /**
     * Test that a Stat object is properly initialized with the correct values
     *
     * @throws Exception
     */
    @Test
    public void TestStatValues() throws Exception {
        System.out.println("TestStatValues");

        Type type = Type.Fire;
        int level = 10;
        CodeamonStats stats = StatsFactory.getStats(type, level);

        System.out.println("Max Hit Points: " + stats.getMaxHitPoints());
        System.out.println("Current Hit Points: " + stats.getCurrentHitPoints());
        System.out.println("Attack: " + stats.getAttack());
        System.out.println("Defense: " + stats.getDefense());

        assertAll("Confirm a Codeamon's initial attributes are properly set",
                () -> assertEquals(2 * stats.getBaseHitPoints() * level / 100
                        + level + 10, stats.getMaxHitPoints()),
                () -> assertEquals(stats.getMaxHitPoints(), stats.getCurrentHitPoints()),
                () -> assertEquals(2 * stats.getBaseAttack() * level / 100 + 5, stats.getAttack()),
                () -> assertEquals(2 * stats.getBaseDefense() * level / 100 + 5, stats.getDefense()));
    }

    /**
     * Test that a Stats created below level 1 generate level 1 stats
     *
     * @throws Exception
     */
    @Test
    public void TestInvalidLevelStatValues() throws Exception {
        System.out.println("TestInvalidLevelStatValues");

        Type type = Type.Fire;
        int level = 1;
        CodeamonStats stats = StatsFactory.getStats(type, 0);

        System.out.println("Max Hit Points: " + stats.getMaxHitPoints());
        System.out.println("Current Hit Points: " + stats.getCurrentHitPoints());
        System.out.println("Attack: " + stats.getAttack());
        System.out.println("Defense: " + stats.getDefense());

        assertAll("Confirm a Codeamon's initial attributes are properly set",
                () -> assertEquals(2 * stats.getBaseHitPoints() * 1 / 100
                        + level + 10, stats.getMaxHitPoints()),
                () -> assertEquals(stats.getMaxHitPoints(), stats.getCurrentHitPoints()),
                () -> assertEquals(2 * stats.getBaseAttack() * level / 100 + 5, stats.getAttack()),
                () -> assertEquals(2 * stats.getBaseDefense() * level / 100 + 5, stats.getDefense()));
    }
}
