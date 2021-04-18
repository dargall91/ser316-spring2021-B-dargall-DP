package codeamon;

/**
 * Contains the Base Stats for a Fire type species of Codeamon.
 */
public class FireStats extends CodeamonStats {
    private static final int BASE_HP = 75;
    private static final int BASE_ATTACK = 85;
    private static final int BASE_DEFENSE = 85;
    private static final int BASE_SPEED = 105;

    public FireStats(int level) {
        super(level);
    }

    /**
     * Gets this species of Codeamon's Base Hit Point value.
     *
     * @return The Base Hit Point Value
     */
    public int getBaseHitPoints() {
        return BASE_HP;
    }

    /**
     * Gets this species of Codeamon's Base Attack value.
     *
     * @return The Base Attack Value
     */
    public int getBaseAttack() {
        return BASE_ATTACK;
    }

    /**
     * Gets this species of Codeamon's Base Defense value.
     *
     * @return The Base Defense Value
     */
    public int getBaseDefense() {
        return BASE_DEFENSE;
    }

    /**
     * Gets this species of Codeamon's Base Speed value.
     *
     * @return The Base Speed Value
     */
    public int getBaseSpeed() {
        return BASE_SPEED;
    }
}
