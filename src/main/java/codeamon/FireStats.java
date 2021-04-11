package codeamon;

public class FireStats extends Stats {
    private final int BASE_HP = 100;
    private final int BASE_ATTACK = 150;
    private final int BASE_DEFENSE = 100;

    public FireStats(int level) {
        super(level);
    }

    /**
     * Gets this Codeamon's base Hit Point value
     *
     * @return Base Hit Point value
     */
    public int getBaseHitPoints() {
        return BASE_HP;
    }

    /**
     * Gets this Codeamon's base Hit Point value
     *
     * @return Base Attack value
     */
    public int getBaseAttack() {
        return BASE_ATTACK;
    }

    /**
     * Gets this Codeamon's base Defense value
     *
     * @return Base Defense value
     */
    public int getBaseDefense() {
        return BASE_DEFENSE;
    }
}
