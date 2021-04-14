import codeamon.CodeamonStats;
import codeamon.Stat;
import codeamon.CodeamonStatsFactory;
import codeamon.Type;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit Tests for CodeamonStats
 */
public class CodeamonStatsTests {
    @AfterEach
    public void afterEach() throws Exception {
        System.out.println();
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
        CodeamonStats stats = CodeamonStatsFactory.getStats(type, level);

        System.out.println("Max Hit Points: " + stats.getMaxHitPoints());
        System.out.println("Current Hit Points: " + stats.getCurrentHitPoints());
        System.out.println("Attack: " + stats.getAttack());
        System.out.println("Defense: " + stats.getDefense());
        System.out.println("Speed: " + stats.getSpeed());

        assertAll(() -> assertEquals(2 * stats.getBaseHitPoints() * level / 100
                        + level + 10, stats.getMaxHitPoints()),
                () -> assertEquals(stats.getMaxHitPoints(), stats.getCurrentHitPoints()),
                () -> assertEquals(2 * stats.getBaseAttack() * level / 100 + 5, stats.getAttack()),
                () -> assertEquals(2 * stats.getBaseDefense() * level / 100 + 5, stats.getDefense()),
                () -> assertEquals(2 * stats.getBaseSpeed() * level / 100 + 5, stats.getSpeed()));
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
        CodeamonStats stats = CodeamonStatsFactory.getStats(type, 0);

        System.out.println("Max Hit Points: " + stats.getMaxHitPoints());
        System.out.println("Current Hit Points: " + stats.getCurrentHitPoints());
        System.out.println("Attack: " + stats.getAttack());
        System.out.println("Defense: " + stats.getDefense());
        System.out.println("Speed: " + stats.getSpeed());

        assertAll(() -> assertEquals(2 * stats.getBaseHitPoints() * 1 / 100
                        + level + 10, stats.getMaxHitPoints()),
                () -> assertEquals(stats.getMaxHitPoints(), stats.getCurrentHitPoints()),
                () -> assertEquals(2 * stats.getBaseAttack() * level / 100 + 5, stats.getAttack()),
                () -> assertEquals(2 * stats.getBaseDefense() * level / 100 + 5, stats.getDefense()),
                () -> assertEquals(2 * stats.getBaseSpeed() * level / 100 + 5, stats.getSpeed()));
    }

    /**
     * Test that the damage method works properly.
     *
     * @throws Exception
     */
    @Test
    public void TestDamage() throws Exception{
        System.out.println("TestDamage");

        Type type = Type.Fire;
        CodeamonStats stats = CodeamonStatsFactory.getStats(type, 1);

        int damage = 2;

        stats.damage(damage);

        System.out.println("Hit Points: " + stats.getCurrentHitPoints() + "/" + stats.getMaxHitPoints());

        assertEquals(stats.getMaxHitPoints() - damage, stats.getCurrentHitPoints());
    }

    /**
     * Test that if the damage dealt is grater than the Codeamon's current Hit Points, the current
     * Hit Points will be set to 0.
     *
     * @throws Exception
     */
    @Test
    public void TestDamageGreaterThanHitPoints() throws Exception{
        System.out.println("TestDamageGreaterThanHitPoints");

        Type type = Type.Fire;
        CodeamonStats stats = CodeamonStatsFactory.getStats(type, 1);

        stats.damage(stats.getMaxHitPoints() + 1);

        System.out.println("Hit Points: " + stats.getCurrentHitPoints() + "/" + stats.getMaxHitPoints());

        assertEquals(0, stats.getCurrentHitPoints());
    }

    /**
     * Test that if an attack deals no damage to a Codeamon the damage dealt is 1.
     *
     * @throws Exception
     */
    @Test
    public void TestDamageIsZero() throws Exception{
        System.out.println("TestDamageIsZero");

        Type type = Type.Fire;
        CodeamonStats stats = CodeamonStatsFactory.getStats(type, 1);

        stats.damage(0);

        System.out.println("Hit Points: " + stats.getCurrentHitPoints() + "/" + stats.getMaxHitPoints());

        assertEquals(stats.getMaxHitPoints() - 1, stats.getCurrentHitPoints());
    }

    /**
     * Test that the Heal method works properly.
     *
     * @throws Exception
     */
    @Test
    public void TestHeal() throws Exception{
        System.out.println("TestHeal");

        Type type = Type.Fire;
        CodeamonStats stats = CodeamonStatsFactory.getStats(type, 1);

        int damage = 2;
        int heal = 1;

        stats.damage(damage);
        stats.heal(heal);

        System.out.println("Hit Points: " + stats.getCurrentHitPoints() + "/" + stats.getMaxHitPoints());

        assertEquals(stats.getMaxHitPoints() - damage + heal, stats.getCurrentHitPoints());
    }

    /**
     * Test that if a Codeamon is healed for 0 Hit Points it is healed by 1.
     *
     * @throws Exception
     */
    @Test
    public void TestHealIsZero() throws Exception{
        System.out.println("TestHealIsZero");

        Type type = Type.Fire;
        CodeamonStats stats = CodeamonStatsFactory.getStats(type, 1);

        int damage = 2;

        stats.damage(damage);
        stats.heal(0);

        System.out.println("Hit Points: " + stats.getCurrentHitPoints() + "/" + stats.getMaxHitPoints());

        assertEquals(stats.getMaxHitPoints() - damage + 1, stats.getCurrentHitPoints());
    }

    /**
     * Test that if Codeamon is past it's Hit Point Maximum, its current Hit Points becomes the max
     *
     * @throws Exception
     */
    @Test
    public void TestHealPastFull() throws Exception{
        System.out.println("TestHealPastFull");

        Type type = Type.Fire;
        CodeamonStats stats = CodeamonStatsFactory.getStats(type, 1);

        int damage = 2;

        stats.damage(damage);
        stats.heal(3);

        System.out.println("Hit Points: " + stats.getCurrentHitPoints() + "/" + stats.getMaxHitPoints());

        assertEquals(stats.getMaxHitPoints(), stats.getCurrentHitPoints());
    }

    /**
     * Test that the rest method properly restores Hit Points.
     *
     * @throws Exception
     */
    @Test
    public void TestRest() {
        System.out.println("TestRest");

        Type type = Type.Fire;
        CodeamonStats stats = CodeamonStatsFactory.getStats(type, 1);

        int damage = 10000;

        stats.damage(damage);
        stats.rest();

        System.out.println("Hit Points: " + stats.getCurrentHitPoints() + "/" + stats.getMaxHitPoints());

        assertEquals(stats.getMaxHitPoints(), stats.getCurrentHitPoints());
    }

    /**
     * Test that negative stat stages are properly applied.
     *
     * @throws Exception
     */
    @Test
    public void TestApplyNegativeStatStageChange() throws Exception {
        System.out.println("TestApplyNegativeStatStageChange");

        Type type = Type.Fire;
        CodeamonStats stats = CodeamonStatsFactory.getStats(type, 10);

        int attack = stats.getAttack();
        int defense = stats.getDefense();
        int speed = stats.getSpeed();

        System.out.println("Before Changes:");
        System.out.println("Attack: " + stats.getAttack());
        System.out.println("Defense: " + stats.getDefense());
        System.out.println("Speed: " + stats.getSpeed());

        stats.applyStatStageChange(Stat.Attack, -7);
        stats.applyStatStageChange(Stat.Defense, -7);
        stats.applyStatStageChange(Stat.Speed, -7);

        System.out.println("\nAfter Changes:");
        System.out.println("Attack: " + stats.getAttack());
        System.out.println("Defense: " + stats.getDefense());
        System.out.println("Speed: " + stats.getSpeed());

        assertAll(() -> assertEquals((int) (attack * (2.0 / 8.0)), stats.getAttack()),
                () -> assertEquals((int) (defense * (2.0 / 8.0)), stats.getDefense()),
                () -> assertEquals((int) (speed * (2.0 / 8.0)), stats.getSpeed()));
    }

    /**
     * Test that positive stat stages are properly applied.
     *
     * @throws Exception
     */
    @Test
    public void TestApplyPositiveStatStageChange() throws Exception {
        System.out.println("TestApplyPositiveStatStageChange");

        Type type = Type.Fire;
        CodeamonStats stats = CodeamonStatsFactory.getStats(type, 10);

        int attack = stats.getAttack();
        int defense = stats.getDefense();
        int speed = stats.getSpeed();

        System.out.println("Before Changes:");
        System.out.println("Attack: " + stats.getAttack());
        System.out.println("Defense: " + stats.getDefense());
        System.out.println("Speed: " + stats.getSpeed());

        stats.applyStatStageChange(Stat.Attack, 7);
        stats.applyStatStageChange(Stat.Defense, 7);
        stats.applyStatStageChange(Stat.Speed, 7);

        System.out.println("\nAfter Changes:");
        System.out.println("Attack: " + stats.getAttack());
        System.out.println("Defense: " + stats.getDefense());
        System.out.println("Speed: " + stats.getSpeed());

        assertAll(() -> assertEquals((int) (attack * (8.0 / 2.0)), stats.getAttack()),
                () -> assertEquals((int) (defense * (8.0 / 2.0)), stats.getDefense()),
                () -> assertEquals((int) (speed * (8.0 / 2.0)), stats.getSpeed()));
    }

    /**
     * Test that speed stat stages are properly applied.
     *
     * @throws Exception
     */
    @Test
    public void TestResetStatStages() throws Exception {
        System.out.println("TestApplyPositiveStatChange");

        Type type = Type.Fire;
        CodeamonStats stats = CodeamonStatsFactory.getStats(type, 10);

        int attack = stats.getAttack();
        int defense = stats.getDefense();
        int speed = stats.getSpeed();

        System.out.println("Before Changes:");
        System.out.println("Attack: " + stats.getAttack());
        System.out.println("Defense: " + stats.getDefense());
        System.out.println("Speed: " + stats.getSpeed());

        stats.applyStatStageChange(Stat.Attack, 7);
        stats.applyStatStageChange(Stat.Defense, 7);
        stats.applyStatStageChange(Stat.Speed, 7);

        System.out.println("\nAfter Changes:");
        System.out.println("Attack: " + stats.getAttack());
        System.out.println("Defense: " + stats.getDefense());
        System.out.println("Speed: " + stats.getSpeed());

        stats.resetStatStages();

        System.out.println("\nAfter Reset:");
        System.out.println("Attack: " + stats.getAttack());
        System.out.println("Defense: " + stats.getDefense());
        System.out.println("Speed: " + stats.getSpeed());

        assertAll(() -> assertEquals(attack, stats.getAttack()),
                () -> assertEquals(defense, stats.getDefense()),
                () -> assertEquals(speed, stats.getSpeed()));
    }

    /**
     * Test the proper attack and defense stat are used when a critical hit lands and the stats
     * have positive stage changes.
     *
     * @throws Exception
     */
    @Test
    public void TestCriticalStatsPositiveStages() throws Exception {
        System.out.println("TestCriticalStatsPositiveStages");

        Type type = Type.Fire;
        CodeamonStats stats = CodeamonStatsFactory.getStats(type, 10);

        int defense = stats.getDefense();

        System.out.println("Before Changes:");
        System.out.println("Attack: " + stats.getAttack());
        System.out.println("Defense: " + stats.getDefense());

        stats.applyStatStageChange(Stat.Attack, 2);
        stats.applyStatStageChange(Stat.Defense, 2);

        int attack = stats.getAttack();

        System.out.println("\nAfter Changes:");
        System.out.println("Attack: " + stats.getAttack());
        System.out.println("Defense: " + stats.getDefense());

        System.out.println("\nCritcal Values:");
        System.out.println("Attack: " + stats.getAttackCritical());
        System.out.println("Defense: " + stats.getDefenseCritical());

        assertAll(() -> assertEquals(attack, stats.getAttackCritical()),
                () -> assertEquals(defense, stats.getDefenseCritical()));
    }

    /**
     * Test the proper attack and defense stat are used when a critical hit lands and the stats
     * have negative stage changes.
     *
     * @throws Exception
     */
    @Test
    public void TestCriticalStatsNegativeStages() throws Exception {
        System.out.println("TestCriticalStatsNegativeStages");

        Type type = Type.Fire;
        CodeamonStats stats = CodeamonStatsFactory.getStats(type, 10);

        int attack = stats.getAttack();

        System.out.println("Before Changes:");
        System.out.println("Attack: " + stats.getAttack());
        System.out.println("Defense: " + stats.getDefense());

        stats.applyStatStageChange(Stat.Attack, -2);
        stats.applyStatStageChange(Stat.Defense, -2);

        int defense = stats.getDefense();

        System.out.println("\nAfter Changes:");
        System.out.println("Attack: " + stats.getAttack());
        System.out.println("Defense: " + stats.getDefense());

        System.out.println("\nCritcal Values:");
        System.out.println("Attack: " + stats.getAttackCritical());
        System.out.println("Defense: " + stats.getDefenseCritical());

        assertAll(() -> assertEquals(attack, stats.getAttackCritical()),
                () -> assertEquals(defense, stats.getDefenseCritical()));
    }
}
