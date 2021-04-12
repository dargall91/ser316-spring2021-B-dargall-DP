import codeamon.Type;
import codeamon.TypeMatchups;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
        
        double normal = TypeMatchups.getEffectiveness(Type.Normal, Type.Normal);
        double fire = TypeMatchups.getEffectiveness(Type.Fire, Type.Normal);
        double water = TypeMatchups.getEffectiveness(Type.Water, Type.Normal);
        double grass = TypeMatchups.getEffectiveness(Type.Grass, Type.Normal);
        double electric = TypeMatchups.getEffectiveness(Type.Electric, Type.Normal);
        double ice = TypeMatchups.getEffectiveness(Type.Ice, Type.Normal);
        double fighting = TypeMatchups.getEffectiveness(Type.Fighting, Type.Fighting);
        double poison = TypeMatchups.getEffectiveness(Type.Poison, Type.Normal);
        double ground = TypeMatchups.getEffectiveness(Type.Ground, Type.Normal);
        double flying = TypeMatchups.getEffectiveness(Type.Flying, Type.Normal);
        double psychic = TypeMatchups.getEffectiveness(Type.Psychic, Type.Normal);
        double bug = TypeMatchups.getEffectiveness(Type.Bug, Type.Normal);
        double rock = TypeMatchups.getEffectiveness(Type.Rock, Type.Normal);
        double ghost = TypeMatchups.getEffectiveness(Type.Ghost, Type.Fighting);
        double dragon = TypeMatchups.getEffectiveness(Type.Dragon, Type.Normal);
        double dark = TypeMatchups.getEffectiveness(Type.Dark, Type.Normal);
        double steel = TypeMatchups.getEffectiveness(Type.Steel, Type.Normal);
        double fairy = TypeMatchups.getEffectiveness(Type.Fairy, Type.Normal);

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

        double fire = TypeMatchups.getEffectiveness(Type.Fire, Type.Grass);
        double water = TypeMatchups.getEffectiveness(Type.Water, Type.Fire);
        double grass = TypeMatchups.getEffectiveness(Type.Grass, Type.Water);
        double electric = TypeMatchups.getEffectiveness(Type.Electric, Type.Water);
        double ice = TypeMatchups.getEffectiveness(Type.Ice, Type.Grass);
        double fighting = TypeMatchups.getEffectiveness(Type.Fighting, Type.Normal);
        double poison = TypeMatchups.getEffectiveness(Type.Poison, Type.Grass);
        double ground = TypeMatchups.getEffectiveness(Type.Ground, Type.Fire);
        double flying = TypeMatchups.getEffectiveness(Type.Flying, Type.Grass);
        double psychic = TypeMatchups.getEffectiveness(Type.Psychic, Type.Poison);
        double bug = TypeMatchups.getEffectiveness(Type.Bug, Type.Dark);
        double rock = TypeMatchups.getEffectiveness(Type.Rock, Type.Ice);
        double ghost = TypeMatchups.getEffectiveness(Type.Ghost, Type.Ghost);
        double dragon = TypeMatchups.getEffectiveness(Type.Dragon, Type.Dragon);
        double dark = TypeMatchups.getEffectiveness(Type.Dark, Type.Psychic);
        double steel = TypeMatchups.getEffectiveness(Type.Steel, Type.Fairy);
        double fairy = TypeMatchups.getEffectiveness(Type.Fairy, Type.Dragon);

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

        double normal = TypeMatchups.getEffectiveness(Type.Normal, Type.Steel);
        double fire = TypeMatchups.getEffectiveness(Type.Fire, Type.Water);
        double water = TypeMatchups.getEffectiveness(Type.Water, Type.Grass);
        double grass = TypeMatchups.getEffectiveness(Type.Grass, Type.Fire);
        double electric = TypeMatchups.getEffectiveness(Type.Electric, Type.Grass);
        double ice = TypeMatchups.getEffectiveness(Type.Ice, Type.Fire);
        double fighting = TypeMatchups.getEffectiveness(Type.Fighting, Type.Flying);
        double poison = TypeMatchups.getEffectiveness(Type.Poison, Type.Poison);
        double ground = TypeMatchups.getEffectiveness(Type.Ground, Type.Grass);
        double flying = TypeMatchups.getEffectiveness(Type.Flying, Type.Rock);
        double psychic = TypeMatchups.getEffectiveness(Type.Psychic, Type.Psychic);
        double bug = TypeMatchups.getEffectiveness(Type.Bug, Type.Fire);
        double rock = TypeMatchups.getEffectiveness(Type.Rock, Type.Fighting);
        double ghost = TypeMatchups.getEffectiveness(Type.Ghost, Type.Dark);
        double dragon = TypeMatchups.getEffectiveness(Type.Dragon, Type.Steel);
        double dark = TypeMatchups.getEffectiveness(Type.Dark, Type.Dark);
        double steel = TypeMatchups.getEffectiveness(Type.Steel, Type.Fire);
        double fairy = TypeMatchups.getEffectiveness(Type.Fairy, Type.Steel);

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
