package codeamon;

import javax.xml.crypto.dsig.spec.HMACParameterSpec;

public abstract class Stats {
    private int maxHitPoints;
    private int currentHitPoints;
    private int attack;
    private int defense;

    public Stats(int level) {
        if (level < 1) {
            level = 1;
        }

        maxHitPoints = calculateMaxHitPoints(getBaseHitPoints(), level);
        currentHitPoints = maxHitPoints;
        attack = calculateStat(getBaseAttack(), level);
        defense = calculateStat(getBaseDefense(), level);
    }

    /**
     * Gets this Codeamon's Hit Point maximum
     *
     * @return The Hit Point maximum
     */
    public int getMaxHitPoints() {
        return maxHitPoints;
    }

    /**
     * Gets this Codeamon's current Hit Points
     *
     * @return The Codeamon's current hit points
     */
    public int getCurrentHitPoints() {
        return currentHitPoints;
    }

    /**
     * Heals the Codeamon by a specified amount
     *
     * @param heal THe amount to heal
     */
    public void heal(int heal) {
        currentHitPoints += heal;

        if (currentHitPoints > maxHitPoints) {
            currentHitPoints = maxHitPoints;
        }
    }

    /**
     * This Codeamon rests and fully recovers all hit points
     */
    public void rest() {
        currentHitPoints = maxHitPoints;
    }

    /**
     * Damages the codeamon by the specified amount
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
     * Gets this Codeamon's attack stat
     *
     * @return The Hit Point maximum
     */
    public int getAttack() {
        return attack;
    }

    /**
     * Gets this Codeamon's defense stat
     *
     * @return The Hit Point maximum
     */
    public int getDefense() {
        return defense;
    }

    /**
     * Calculates a Codeamon's maximum hit point stat based on it's current level
     *
     * @param baseHitPoints The Codeamon's base hit point stat
     * @param level The Codeamon's current level
     * @return Hit Point Maximum
     */
    private int calculateMaxHitPoints(int baseHitPoints, int level) {
        return (2 * baseHitPoints * level) / 100 + level + 10;
    }

    /**
     * Calculates a Codeamon's non-hit point stat based on it's current level
     *
     * @param baseStat The Codeamon's base stat for the stat to calculate
     * @param level The Codeamon's current level
     * @return Calculated stat total
     */
    private int calculateStat(int baseStat, int level) {
        return (2 * baseStat * level) / 100 + 5;
    }

    public abstract int getBaseHitPoints();

    public abstract int getBaseAttack();

    public abstract int getBaseDefense();
}
