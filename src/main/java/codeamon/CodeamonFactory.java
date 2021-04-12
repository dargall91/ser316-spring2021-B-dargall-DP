package codeamon;

/**
 * A Factory Method class for creating a Codeamon.
 */
public class CodeamonFactory {
    /**
     * Creates a Codeamon with the specified Type and level.
     *
     * @param type The Codeamon's type
     * @param level The Codeamon's level
     *
     * @return The new Codeamon object of the specified type and level
     */
    public static Codeamon getCodeamon(Type type, int level) {
        if (type == Type.Fire) {
            return new FireCodeamon(level);
        }

        return null;
    }
}
