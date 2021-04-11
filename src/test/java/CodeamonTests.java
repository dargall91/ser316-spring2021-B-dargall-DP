import codeamon.Codeamon;
import codeamon.CodeamonFactory;
import codeamon.Type;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Constructor;

import static org.junit.Assert.assertEquals;

public class CodeamonTests {

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void CreateCodeamon() throws Exception {
        Codeamon fire = CodeamonFactory.getCodeamon(Type.Fire, 1);

        System.out.println("Name: " + fire.getName());
        System.out.println("Level: " + fire.getLevel());
        System.out.println("Type: " + fire.getType());
        System.out.println("Max Hit Points: " + fire.getMaxHitPoints());
        fire.damage(2);
        System.out.println("Current Hit Points (2 damage): " + fire.getCurrentHitPoints());
        fire.heal(1);
        System.out.println("Current Hit Points (1 heal): " + fire.getCurrentHitPoints());
        fire.rest();
        System.out.println("Current Hit Points (1 heal): " + fire.getCurrentHitPoints());
        System.out.println("Attack: " + fire.getAttack());
        System.out.println("Defense: " + fire.getDefense());

        assertEquals(fire.getType(), Type.Fire);
    }
}
