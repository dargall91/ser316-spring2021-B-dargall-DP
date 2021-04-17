import codeamon.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit Tests for Attack
 */
public class AttackTests {
    @AfterEach
    public void afterEach() throws Exception {
        System.out.println();
    }

    /**
     * Test that the AttackBuilder properly builds attacks.
     *
     * @throws Exception
     */
    @Test
    public void TestAttackBuilder() throws Exception {
        System.out.println("TestAttackBuilder");

        int num = 50;

        Attack attack = new Attack.AttackBuilder("Test Attack", Type.Normal).power(num)
                .critChance(num).accuracy(num).statusEffect(num, Stat.Attack, 1, true)
                .heal(num).build();

        System.out.println("Name: " + attack.getName());
        System.out.println("Type: " + attack.getType());
        System.out.println("Power: " + attack.getPower());
        System.out.println("Crit Chance: " + attack.getCritChance());
        System.out.println("Accuracy: " + attack.getAccuracy());
        System.out.println("Effect Chance: " + attack.getEffectChance());
        System.out.println("Stat: " + attack.getStat());
        System.out.println("Stages: " + attack.getStages());
        System.out.println("Self: " + attack.getSelf());
        System.out.println("Heal: " + attack.getHeal());
        
        assertAll(() -> assertEquals("Test Attack", attack.getName()),
                () -> assertEquals(Type.Normal, attack.getType()),
                () -> assertEquals(num, attack.getPower()),
                () -> assertEquals(num, attack.getCritChance()),
                () -> assertEquals(num, attack.getAccuracy()),
                () -> assertEquals(num, attack.getEffectChance()),
                () -> assertEquals(Stat.Attack, attack.getStat()),
                () -> assertEquals(1, attack.getStages()),
                () -> assertEquals(true, attack.getSelf()),
                () -> assertEquals(num, attack.getHeal()));
    }

    /**
     * Test that the Attack Builder with parameters below the minimum values sets the correct values
     *
     * @throws Exception
     */
    @Test
    public void TestAttackBuilderLowValues() throws Exception{
        System.out.println("TestAttackBuilderLowValues");

        Attack attack = new Attack.AttackBuilder("Test Attack", Type.Normal).accuracy(0).critChance(0)
                .statusEffect(0, Stat.Attack, -7, true).heal(0).build();

        System.out.println("Crit Chance: " + attack.getCritChance());
        System.out.println("Accuracy: " + attack.getAccuracy());
        System.out.println("Effect Chance: " + attack.getEffectChance());
        System.out.println("Stages: " + attack.getStages());
        System.out.println("Heal: " + attack.getHeal());

        assertAll(() -> assertEquals(0, attack.getCritChance()),
                () -> assertEquals(1, attack.getAccuracy()),
                () -> assertEquals(1, attack.getEffectChance()),
                () -> assertEquals(-6, attack.getStages()),
                () -> assertEquals(1, attack.getHeal()));
    }

    /**
     * Test that the Attack Builder with parameters above the maximum values sets the correct values
     *
     * @throws Exception
     */
    @Test
    public void TestAttackBuilderHighValues() throws Exception{
        System.out.println("TestAttackBuilderHighValues");

        Attack attack = new Attack.AttackBuilder("Test Attack", Type.Normal).accuracy(200)
                .critChance(200).statusEffect(200, Stat.Attack, 7, true).heal(200).build();

        System.out.println("Crit Chance: " + attack.getCritChance());
        System.out.println("Accuracy: " + attack.getAccuracy());
        System.out.println("Effect Chance: " + attack.getEffectChance());
        System.out.println("Stages: " + attack.getStages());
        System.out.println("Heal: " + attack.getHeal());

        assertAll(() -> assertEquals(100, attack.getCritChance()),
                () -> assertEquals(100, attack.getAccuracy()),
                () -> assertEquals(100, attack.getEffectChance()),
                () -> assertEquals(6, attack.getStages()),
                () -> assertEquals(100, attack.getHeal()));
    }

    /**
     * Test an Attack that deals no damage, does no healing, and applies no stat changes.
     *
     * @throws Exception
     */
    @Test
    public void TestEmptyAttack() throws Exception{
        System.out.println("TestEmptyAttack");

        Codeamon user = CodeamonFactory.createCodeamon(Type.Fire, 10);
        Codeamon opponent = CodeamonFactory.createCodeamon(Type.Fire, 10);

        Attack attack = new Attack.AttackBuilder("Test Attack", Type.Normal).build();

        System.out.println("Name: " + attack.getName());
        System.out.println("Type: " + attack.getType());

        assertFalse(attack.applyAttack(user, opponent));
    }

    /**
     * Test that a damaging attack deals the proper amount of damage.
     *
     * @throws Exception
     */
    @Test
    public void TestDamagingAttack() throws Exception{
        System.out.println("TestDamagingAttack");

        Codeamon user = CodeamonFactory.createCodeamon(Type.Fire, 10);
        Codeamon opponent = CodeamonFactory.createCodeamon(Type.Fire, 10);

        //Set crit chance to 0 to ensure that the attack does a set amount of damage every time
        Attack attack = new Attack.AttackBuilder("Test Attack", Type.Normal).power(50).critChance(0).build();

        attack.applyAttack(user, opponent);

        assertEquals(10, opponent.getMaxHitPoints() - opponent.getCurrentHitPoints());
    }

    /**
     * Test that a damaging attack that applies stat changes to the user applies the changes.
     *
     * @throws Exception
     */
    @Test
    public void TestDamagingAttackSelf() throws Exception{
        System.out.println("TestDamagingAttackSelf");

        Codeamon user = CodeamonFactory.createCodeamon(Type.Fire, 10);
        Codeamon opponent = CodeamonFactory.createCodeamon(Type.Fire, 10);
        int attackStat = user.getAttackStat();

        //Set crit chance to 0 to ensure that the attack does a set amount of damage every time
        Attack attack = new Attack.AttackBuilder("Test Attack", Type.Normal).power(50).critChance(0)
                .statusEffect(100, Stat.Attack, 1, true).build();

        attack.applyAttack(user, opponent);

        assertTrue(user.getAttackStat() > attackStat);
    }

    /**
     * Test that a damaging attack that applies stat changes to the opponent applies the changes.
     *
     * @throws Exception
     */
    @Test
    public void TestDamagingAttackOpponent() throws Exception{
        System.out.println("TestDamagingAttackOpponent");

        Codeamon user = CodeamonFactory.createCodeamon(Type.Fire, 10);
        Codeamon opponent = CodeamonFactory.createCodeamon(Type.Fire, 10);
        int attackStat = opponent.getAttackStat();

        //Set crit chance to 0 to ensure that the attack does a set amount of damage every time
        Attack attack = new Attack.AttackBuilder("Test Attack", Type.Normal).power(50).critChance(0)
                .statusEffect(100, Stat.Attack, 1, false).build();

        attack.applyAttack(user, opponent);

        assertTrue(opponent.getAttackStat() > attackStat);
    }

    /**
     * Test that an non-damaging attack that targets the user has stat stage changes applies the
     * changes.
     *
     * @throws Exception
     */
    @Test
    public void TestNonDamagingAttackSelf() throws Exception{
        System.out.println("TestNonDamagingAttackSelf");

        Codeamon user = CodeamonFactory.createCodeamon(Type.Fire, 10);
        Codeamon opponent = CodeamonFactory.createCodeamon(Type.Fire, 10);
        int attackStat = user.getAttackStat();

        //Set crit chance to 0 to ensure that the attack does a set amount of damage every time
        Attack attack = new Attack.AttackBuilder("Test Attack", Type.Normal).statusEffect(100,
                Stat.Attack, 1, true).build();

        attack.applyAttack(user, opponent);

        assertTrue(user.getAttackStat() > attackStat);
    }

    /**
     * Test that an non-damaging attack that targets the opponent has stat stage changes applies
     * the changes.
     *
     * @throws Exception
     */
    @Test
    public void TestNonDamagingAttackOpponent() throws Exception{
        System.out.println("TestNonDamagingAttackOpponent");

        Codeamon user = CodeamonFactory.createCodeamon(Type.Fire, 10);
        Codeamon opponent = CodeamonFactory.createCodeamon(Type.Fire, 10);
        int attackStat = opponent.getAttackStat();

        //Set crit chance to 0 to ensure that the attack does a set amount of damage every time
        Attack attack = new Attack.AttackBuilder("Test Attack", Type.Normal).statusEffect(100,
                Stat.Attack, 1, false).build();

        attack.applyAttack(user, opponent);

        assertEquals((int) (attackStat * 3.0 / 2.0), opponent.getAttackStat());
    }

    /**
     * Test that an attack that only heals the user properly applies the healing.
     *
     * @throws Exception
     */
    @Test
    public void TestHealingAttack() throws Exception{
        System.out.println("TestHealingAttack");

        Codeamon user = CodeamonFactory.createCodeamon(Type.Fire, 10);
        Codeamon opponent = CodeamonFactory.createCodeamon(Type.Fire, 10);

        //Set crit chance to 0 to ensure that the attack does a set amount of damage every time
        Attack attack = new Attack.AttackBuilder("Test Attack", Type.Normal).heal(100).build();

        //damage the user, the apply the effect of the attack to heal it
        user.damage(1000);
        attack.applyAttack(user, opponent);

        assertEquals(user.getMaxHitPoints(), user.getCurrentHitPoints());
    }

    //Best way to test methods with random numbers TBD. Maybe they can be fully covered through an
    //actual battle test once battles are implemented
    /**
     * Test critical hits
     *
     * @throws Exception
     */
    /*@Test
    public void TestCritical() throws Exception {
        System.out.println("TestCritical");
        Codeamon user = CodeamonFactory.getCodeamon(Type.Fire, 1);
        Codeamon opponent1 = CodeamonFactory.getCodeamon(Type.Fire, 10);
        Codeamon opponent2 = CodeamonFactory.getCodeamon(Type.Fire, 10);
        Codeamon opponent3 = CodeamonFactory.getCodeamon(Type.Fire, 10);

        Attack crit = new Attack.Builder("Test Attack", Type.Normal).power(50).critChance(100)
                .build();
        Attack notCrit = new Attack.Builder("Test Attack", Type.Normal).power(50).critChance(0)
                .build();
        Attack mightCrit = new Attack.Builder("Test Attack", Type.Normal).power(50).critChance(50)
                .build();

        crit.applyAttack(user, opponent1);
        notCrit.applyAttack(user, opponent2);
        //This one is really just here for code coverage
        mightCrit.applyAttack(user, opponent3);

        assertAll(() -> assertTrue(opponent1.getCurrentHitPoints()
                        < opponent2.getCurrentHitPoints()),
                () -> assertTrue(opponent3.getCurrentHitPoints() == opponent1.getCurrentHitPoints()
                || opponent3.getCurrentHitPoints() == opponent2.getCurrentHitPoints()));
    }*/

    /**
     * Test effect chance
     *
     * @throws Exception
     */
    /*@Test
    public void TestEffectChance() throws Exception {
        System.out.println("TestEffectChance");
        Codeamon user = CodeamonFactory.getCodeamon(Type.Fire, 1);
        Codeamon opponent1 = CodeamonFactory.getCodeamon(Type.Fire, 10);
        Codeamon opponent2 = CodeamonFactory.getCodeamon(Type.Fire, 10);
        Codeamon opponent3 = CodeamonFactory.getCodeamon(Type.Fire, 10);

        Attack effect = new Attack.Builder("Test Attack", Type.Normal).statusEffect(100, Stat.Attack, 1, false)
                .build();
        Attack noEffect = new Attack.Builder("Test Attack", Type.Normal).power(50).build();
        Attack mightEffect = new Attack.Builder("Test Attack", Type.Normal).power(50).critChance(50)
                .build();

        assertAll(() -> assertTrue(opponent1.getCurrentHitPoints()
                        < opponent2.getCurrentHitPoints()),
                () -> assertTrue(opponent3.getCurrentHitPoints() == opponent1.getCurrentHitPoints()
                        || opponent3.getCurrentHitPoints() == opponent2.getCurrentHitPoints()));
    }*/
}
