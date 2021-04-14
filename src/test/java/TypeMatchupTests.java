import codeamon.Type;
import codeamon.TypeMatchup;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit Tests for TypeMatchup
 */
public class TypeMatchupTests {
    @AfterEach
    public void afterEach() throws Exception {
        System.out.println();
    }

    /**
     * Tests that an attack that is neither strong or weak against the target has a 1.0 damage
     * multiplier
     *
     * @throws Exception
     */
    @Test
    public void TestTypeNeutralTypeMatchup() throws Exception {
        System.out.println("TestTypeNeutralTypeMatchup");

        double target = 1.0;
        
        double normal = TypeMatchup.getEffectiveness(Type.Normal, Type.Normal);
        double fire = TypeMatchup.getEffectiveness(Type.Fire, Type.Normal);
        double water = TypeMatchup.getEffectiveness(Type.Water, Type.Normal);
        double grass = TypeMatchup.getEffectiveness(Type.Grass, Type.Normal);
        double electric = TypeMatchup.getEffectiveness(Type.Electric, Type.Normal);
        double ice = TypeMatchup.getEffectiveness(Type.Ice, Type.Normal);
        double fighting = TypeMatchup.getEffectiveness(Type.Fighting, Type.Fighting);
        double poison = TypeMatchup.getEffectiveness(Type.Poison, Type.Normal);
        double ground = TypeMatchup.getEffectiveness(Type.Ground, Type.Normal);
        double flying = TypeMatchup.getEffectiveness(Type.Flying, Type.Normal);
        double psychic = TypeMatchup.getEffectiveness(Type.Psychic, Type.Normal);
        double bug = TypeMatchup.getEffectiveness(Type.Bug, Type.Normal);
        double rock = TypeMatchup.getEffectiveness(Type.Rock, Type.Normal);
        double ghost = TypeMatchup.getEffectiveness(Type.Ghost, Type.Fighting);
        double dragon = TypeMatchup.getEffectiveness(Type.Dragon, Type.Normal);
        double dark = TypeMatchup.getEffectiveness(Type.Dark, Type.Normal);
        double steel = TypeMatchup.getEffectiveness(Type.Steel, Type.Normal);
        double fairy = TypeMatchup.getEffectiveness(Type.Fairy, Type.Normal);

        assertAll(() -> assertEquals(target, normal), () ->assertEquals(target, fire),
                () -> assertEquals(target, water), () ->assertEquals(target, grass),
                () -> assertEquals(target, electric), () ->assertEquals(target, ice),
                () -> assertEquals(target, fighting), () ->assertEquals(target, poison),
                () -> assertEquals(target, ground), () ->assertEquals(target, flying),
                () -> assertEquals(target, psychic), () ->assertEquals(target, bug),
                () -> assertEquals(target, rock), () ->assertEquals(target, ghost),
                () -> assertEquals(target, dragon), () ->assertEquals(target, dark),
                () -> assertEquals(target, steel), () ->assertEquals(target, fairy));
    }

    /**
     * Tests that an attack that is strong against the target has a 2.0 damage multiplier
     *
     * @throws Exception
     */
    @Test
    public void TestTypeStrongTypeMatchup() throws Exception {
        System.out.println("TestTypeStrongTypeMatchup");

        System.out.println("TestTypeNeutralTypeMatchup");

        double target = 2.0;

        double fire = TypeMatchup.getEffectiveness(Type.Fire, Type.Grass);
        double water = TypeMatchup.getEffectiveness(Type.Water, Type.Fire);
        double grass = TypeMatchup.getEffectiveness(Type.Grass, Type.Water);
        double electric = TypeMatchup.getEffectiveness(Type.Electric, Type.Water);
        double ice = TypeMatchup.getEffectiveness(Type.Ice, Type.Grass);
        double fighting = TypeMatchup.getEffectiveness(Type.Fighting, Type.Normal);
        double poison = TypeMatchup.getEffectiveness(Type.Poison, Type.Grass);
        double ground = TypeMatchup.getEffectiveness(Type.Ground, Type.Fire);
        double flying = TypeMatchup.getEffectiveness(Type.Flying, Type.Grass);
        double psychic = TypeMatchup.getEffectiveness(Type.Psychic, Type.Poison);
        double bug = TypeMatchup.getEffectiveness(Type.Bug, Type.Dark);
        double rock = TypeMatchup.getEffectiveness(Type.Rock, Type.Ice);
        double ghost = TypeMatchup.getEffectiveness(Type.Ghost, Type.Ghost);
        double dragon = TypeMatchup.getEffectiveness(Type.Dragon, Type.Dragon);
        double dark = TypeMatchup.getEffectiveness(Type.Dark, Type.Psychic);
        double steel = TypeMatchup.getEffectiveness(Type.Steel, Type.Fairy);
        double fairy = TypeMatchup.getEffectiveness(Type.Fairy, Type.Dragon);

        assertAll(() ->assertEquals(target, fire), () -> assertEquals(target, water),
                () ->assertEquals(target, grass),   () -> assertEquals(target, electric),
                () ->assertEquals(target, ice), () -> assertEquals(target, fighting),
                () ->assertEquals(target, poison), () -> assertEquals(target, ground),
                () ->assertEquals(target, flying), () -> assertEquals(target, psychic),
                () ->assertEquals(target, bug), () -> assertEquals(target, rock),
                () ->assertEquals(target, ghost), () -> assertEquals(target, dragon),
                () ->assertEquals(target, dark), () -> assertEquals(target, steel),
                () ->assertEquals(target, fairy));
    }

    /**
     * Tests that an attack that is weak against the target has a 0.5 damage multiplier
     *
     * @throws Exception
     */
    @Test
    public void TestTypeWeakTypeMatchup() throws Exception {
        System.out.println("TestTypeWeakTypeMatchup");

        System.out.println("TestTypeNeutralTypeMatchup");

        double target = 0.5;

        double normal = TypeMatchup.getEffectiveness(Type.Normal, Type.Steel);
        double fire = TypeMatchup.getEffectiveness(Type.Fire, Type.Water);
        double water = TypeMatchup.getEffectiveness(Type.Water, Type.Grass);
        double grass = TypeMatchup.getEffectiveness(Type.Grass, Type.Fire);
        double electric = TypeMatchup.getEffectiveness(Type.Electric, Type.Grass);
        double ice = TypeMatchup.getEffectiveness(Type.Ice, Type.Fire);
        double fighting = TypeMatchup.getEffectiveness(Type.Fighting, Type.Flying);
        double poison = TypeMatchup.getEffectiveness(Type.Poison, Type.Poison);
        double ground = TypeMatchup.getEffectiveness(Type.Ground, Type.Grass);
        double flying = TypeMatchup.getEffectiveness(Type.Flying, Type.Rock);
        double psychic = TypeMatchup.getEffectiveness(Type.Psychic, Type.Psychic);
        double bug = TypeMatchup.getEffectiveness(Type.Bug, Type.Fire);
        double rock = TypeMatchup.getEffectiveness(Type.Rock, Type.Fighting);
        double ghost = TypeMatchup.getEffectiveness(Type.Ghost, Type.Dark);
        double dragon = TypeMatchup.getEffectiveness(Type.Dragon, Type.Steel);
        double dark = TypeMatchup.getEffectiveness(Type.Dark, Type.Dark);
        double steel = TypeMatchup.getEffectiveness(Type.Steel, Type.Fire);
        double fairy = TypeMatchup.getEffectiveness(Type.Fairy, Type.Steel);

        assertAll(() -> assertEquals(target, normal), () ->assertEquals(target, fire),
                () -> assertEquals(target, water), () ->assertEquals(target, grass),
                () -> assertEquals(target, electric), () ->assertEquals(target, ice),
                () -> assertEquals(target, fighting), () ->assertEquals(target, poison),
                () -> assertEquals(target, ground), () ->assertEquals(target, flying),
                () -> assertEquals(target, psychic), () ->assertEquals(target, bug),
                () -> assertEquals(target, rock), () ->assertEquals(target, ghost),
                () -> assertEquals(target, dragon), () ->assertEquals(target, dark),
                () -> assertEquals(target, steel), () ->assertEquals(target, fairy));
    }
}
