package codeamon;

/**
 * An abstract data structure that represents a Codeamon. Has the methods necessary for getting a
 * Codeamon's stats,  * name, and level, inflicting damage, healing damage, getting its EXP yield,
 * and applying gained EXP.
 */
public abstract class Codeamon {
    private CodeamonStats stats;
    private int level;
    private String nickname;

    /**
     * Constructs a Codeamon with the specified stats and level.
     *
     * @param stats The Stats for the Codeamon
     * @param level The Codeamon's level. If this value is less than 1, it will be set to 1
     */
    public Codeamon(CodeamonStats stats, int level) {
        if (level < 1) {
            level = 1;
        }

        this.stats = stats;
        this.level = level;
        nickname = null;
    }

    /**
     * Gets this Codeamon's name.
     *
     * @return The name
     */
    public String getName() {
        if (nickname != null) {
            return nickname;
        }

        return getSpeciesName();
    }

    /**
     * Gets this Codeamon's level.
     *
     * @return The level
     */
    public int getLevel() {
        return level;
    }

    /**
     * Gets this Codeamon's Hit Point maximum.
     *
     * @return The Hit Point maximum
     */
    public int getMaxHitPoints() {
        return stats.getMaxHitPoints();
    }

    /**
     * Gets this Codeamon's current Hit Points.
     *
     * @return The Codeamon's current hit points
     */
    public int getCurrentHitPoints() {
        return stats.getCurrentHitPoints();
    }

    /**
     * This Codeamon rests and fully recovers all hit points.
     */
    public void rest() {
        stats.rest();
    }

    /**
     * Heals the Codeamon by a specified amount.
     *
     * @param heal The amount to heal
     */
    public void heal(int heal) {
        stats.heal(heal);
    }

    /**
     * Damages the codeamon by the specified amount.
     *
     * @param damage The amount of damage inflicted
     */
    public void damage(int damage) {
        stats.damage(damage);
    }

    /**
     * Sets a nickname for this Codeamon.
     *
     * @param nickname The new nickname for the Codeamon. Passing null will reset it's name back
     *                 to its species name
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * Gets this Codeamon's attack stat.
     *
     * @return The Hit Point maximum
     */
    public int getAttack() {
        return stats.getAttack();
    }

    /**
     * Gets this Codeamon's defense stat.
     *
     * @return The Hit Point maximum
     */
    public int getDefense() {
        return stats.getDefense();
    }

    /**
     * Gets this Codeamon's species's name.
     *
     * @return The type of this Codeamon Species
     */
    public abstract String getSpeciesName();

    /**
     * Gets this Codeamon's species's type.
     *
     * @return The type
     */
    public abstract Type getType();
}
