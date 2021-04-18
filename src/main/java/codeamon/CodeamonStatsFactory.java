package codeamon;

/**
 * A Factory Method class for getting the stats for a specific type of Codeamon.
 */
public class CodeamonStatsFactory {
    /**
     * Creates stats for a specified type and level of Codeamon.
     *
     * @param type The Codeamon's type
     * @param level The Codeamon's level
     *
     * @return The new stats of the Codeamon
     */
    public static CodeamonStats getStats(Type type, int level) {
        if (type == Type.Normal) {
            return new NormalStats(level);
        }

        if (type == Type.Fire) {
            return new FireStats(level);
        }

        if (type == Type.Water) {
            return new WaterStats(level);
        }

        if (type == Type.Grass) {
            return new GrassStats(level);
        }

        if (type == Type.Electric) {
            return new ElectricStats(level);
        }

        if (type == Type.Ice) {
            return new IceStats(level);
        }

        if (type == Type.Fighting) {
            return new FightingStats(level);
        }

        if (type == Type.Poison) {
            return new PoisonStats(level);
        }

        if (type == Type.Ground) {
            return new GroundStats(level);
        }

        if (type == Type.Flying) {
            return new FlyingStats(level);
        }

        if (type == Type.Psychic) {
            return new PsychicStats(level);
        }

        if (type == Type.Bug) {
            return new BugStats(level);
        }

        if (type == Type.Rock) {
            return new RockStats(level);
        }

        if (type == Type.Ghost) {
            return new GhostStats(level);
        }

        if (type == Type.Dragon) {
            return new DarkStats(level);
        }

        if (type == Type.Dark) {
            return new DarkStats(level);
        }

        if (type == Type.Steel) {
            return new SteelStats(level);
        }

        if (type == Type.Fairy) {
            return new FairyStats(level);
        }

        return null;
    }
}
