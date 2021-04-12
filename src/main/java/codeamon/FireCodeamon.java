package codeamon;

/**
 * Data structure for a Fire type species of Codeamon.
 */
public class FireCodeamon extends Codeamon {
    private static final String NAME = "Fire Name";
    private static final Type TYPE = Type.Fire;

    /**
     * Constructs a Fire Species of Codemon initializing its stats based on its level.
     *
     * @param level The level of the Codeamon
     */
    public FireCodeamon(int level) {
        super(CodeamonStatsFactory.getStats(Type.Fire, level), level);
    }

    /**
     * Gets this Codeamon's species's name.
     *
     * @return The name of this Codeamon Species
     */
    public String getSpeciesName() {
        return NAME;
    }

    /**
     * Gets this Codeamon's species's type.
     *
     * @return The type
     */
    public Type getType() {
        return TYPE;
    }
}
