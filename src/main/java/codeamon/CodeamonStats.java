package codeamon;

/**
 * An abstract data structure that represents a Codeamon's stats. Has the necessary methods for
 * getting a Codeamon's Hit Points, Attack, and Defense stats, inflicting damage, healing damage,
 * and recalculating stats after leveling up.
 */
public abstract class CodeamonStats {
    private int maxHitPoints;
    private int currentHitPoints;
    private int attack;
    private int defense;
    private int speed;

    /**
     * Constructs a Stat object based on the Codeamon's level. The stats will be influenced by
     * the species's Base Stats from the corresponding TypedStat class.
     *
     * @param level The Codeamon's level. If this value is less than 1, it will calculate stats
     *              as though it were level 1
     */
    public CodeamonStats(int level) {
        if (level < 1) {
            level = 1;
        }

        maxHitPoints = calculateMaxHitPoints(getBaseHitPoints(), level);
        currentHitPoints = maxHitPoints;
        attack = calculateStat(getBaseAttack(), level);
        defense = calculateStat(getBaseDefense(), level);
        speed = calculateStat(getBaseSpeed(), level);
    }

    /**
     * Gets this Codeamon's Hit Point maximum.
     *
     * @return The Hit Point maximum
     */
    public int getMaxHitPoints() {
        return maxHitPoints;
    }

    /**
     * Gets this Codeamon's current Hit Points.
     *
     * @return The Codeamon's current hit points
     */
    public int getCurrentHitPoints() {
        return currentHitPoints;
    }

    /**
     * Heals the Codeamon by a specified amount.
     *
     * @param heal The amount to heal
     */
    public void heal(int heal) {
        currentHitPoints += heal;

        if (currentHitPoints > maxHitPoints) {
            currentHitPoints = maxHitPoints;
        }
    }

    /**
     * This Codeamon rests and fully recovers all hit points.
     */
    public void rest() {
        currentHitPoints = maxHitPoints;
    }

    /**
     * Damages the codeamon by the specified amount.
     *
     * @param damage The amount of damage inflicted
     */
    public void damage(int damage) {
        currentHitPoints -= damage;

        if (currentHitPoints < 0) {
            currentHitPoints = 0;
        }
    }

    /**
     * Gets this Codeamon's attack stat.
     *
     * @return The Codeamon's Attack
     */
    public int getAttack() {
        return attack;
    }

    /**
     * Gets this Codeamon's defense stat.
     *
     * @return The Codeamon's Defense
     */
    public int getDefense() {
        return defense;
    }

    /**
     * Gets this Codeamon's speed stat.
     *
     * @return The Codeamon's Speed
     */
    public int getSpeed() {
        return defense;
    }

    /**
     * Calculates a Codeamon's maximum hit point stat based on it's current level.
     *
     * @param baseHitPoints The Codeamon's base hit point stat
     * @param level The Codeamon's current level
     * @return Hit Point Maximum
     */
    private int calculateMaxHitPoints(int baseHitPoints, int level) {
        return (2 * baseHitPoints * level) / 100 + level + 10;
    }

    /**
     * Calculates a Codeamon's non-hit point stat based on it's current level.
     *
     * @param baseStat The Codeamon's base stat for the stat to calculate
     * @param level The Codeamon's current level
     * @return Calculated stat total
     */
    private int calculateStat(int baseStat, int level) {
        return (2 * baseStat * level) / 100 + 5;
    }

    /**
     * Gets this species of Codeamon's Base Hit Point value.
     *
     * @return The Base Hit Point Value
     */
    public abstract int getBaseHitPoints();

    /**
     * Gets this species of Codeamon's Base Attack value.
     *
     * @return The Base Attack Value
     */
    public abstract int getBaseAttack();

    /**
     * Gets this species of Codeamon's Base Defense value.
     *
     * @return The Base Defense Value
     */
    public abstract int getBaseDefense();

    /**
     * Gets this species of Codeamon's Base Speed value.
     *
     * @return The Base Speed Value
     */
    public abstract int getBaseSpeed();
}
