package codeamon;

/**
 * A Factory Method class for getting the stats for a specific type of Codeamon.
 */
public class StatsFactory {
    /**
     * Creates stats for a specified type and level of Codeamon.
     *
     * @param type The Codeamon's type
     * @param level The Codeamon's level
     *
     * @return The new stats of the Codeamon
     */
    public static Stats getStats(Type type, int level) {
        if (type == Type.Fire) {
            return new FireStats(level);
        }

        return null;
    }
}
