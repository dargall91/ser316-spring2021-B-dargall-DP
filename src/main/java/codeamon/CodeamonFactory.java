package codeamon;

import java.util.Random;

/**
 * A Factory Method class for creating a Codeamon. Codeamon can either be created at random or with
 * a specific type, either way the level of the Codeamon must be specified. The minimum level a
 * Codeamon can be is 1 and the maximum level a Codeamon can be is 100.
 *
 * <p>
 *     The following requirements are fulfilled by this Design Pattern and any related classes (this
 *     Design Pattern works directly with the CodeamonStatsFactory to fulfill more requirements):
 *     <li>Codeamon have four attacks, which are determined by it's level when created</li>
 *     <li>Codeamon have a type that is resistant to or weak against certain other attack types
 *     (enforced by the TypeMatchup class)</li>
 * </p>
 */
public class CodeamonFactory {
    /**
     * Creates a Codeamon with the specified Type and level.
     *
     * @param type The Codeamon's type
     * @param level The Codeamon's level. If this is less than 1, the Codeamon will be level 1. If
     *              this is greater than 100, it will be level 100.
     *
     * @return The new Codeamon object of the specified type and level
     */
    public static Codeamon createCodeamon(Type type, int level) {
        if (level < 1) {
            level = 1;
        } else if (level > 100) {
            level = 100;
        }

        if (type == Type.Normal) {
            return new NormalCodeamon(level);
        }

        if (type == Type.Fire) {
            return new FireCodeamon(level);
        }

        if (type == Type.Water) {
            return new WaterCodeamon(level);
        }

        if (type == Type.Grass) {
            return new GrassCodeamon(level);
        }

        if (type == Type.Electric) {
            return new ElectricCodeamon(level);
        }

        if (type == Type.Ice) {
            return new IceCodeamon(level);
        }

        if (type == Type.Fighting) {
            return new FightingCodeamon(level);
        }

        if (type == Type.Poison) {
            return new PoisonCodeamon(level);
        }

        if (type == Type.Ground) {
            return new GroundCodeamon(level);
        }

        if (type == Type.Flying) {
            return new FlyingCodeamon(level);
        }

        if (type == Type.Psychic) {
            return new PsychicCodeamon(level);
        }

        if (type == Type.Bug) {
            return new BugCodeamon(level);
        }

        if (type == Type.Rock) {
            return new RockCodeamon(level);
        }

        if (type == Type.Ghost) {
            return new GhostCodeamon(level);
        }

        if (type == Type.Dragon) {
            return new DragonCodeamon(level);
        }

        if (type == Type.Dark) {
            return new DarkCodeamon(level);
        }

        if (type == Type.Steel) {
            return new SteelCodeamon(level);
        }

        if (type == Type.Fairy) {
            return new FairyCodeamon(level);
        }

        return null;
    }

    /**
     * Creates a random Codeamon species with the specified level.
     *
     * @param level The Codeamon's level. If this is less than 1, the Codeamon will be level 1. If
     *              this is greater than 100, it will be level 100.
     * @return A random Codeamon species of the specified level
     */
    public static Codeamon createRandomCodeamon(int level) {
        Type[] typeList = Type.values();
        Random rand = new Random();

        //TODO: Add Glitch Codeamon (1 in 1000 odds?)

        return createCodeamon(typeList[rand.nextInt(typeList.length)], level);
    }
}
