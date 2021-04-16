package codeamon;

/**
 * A Factory Method class for creating a Codeamon. The minimum level a Codeamon can be is 1 and the
 * maximum level a COdeamon can be is 100.
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

        if (type == Type.Fire) {
            return new FireCodeamon(level);
        }

        return null;
    }
}
