package codeamon;

import java.util.ArrayList;

/**
 * An abstract data structure that represents a Codeamon. Has the methods necessary for getting a
 * Codeamon's stats, name, and level, inflicting damage, healing damage, getting its EXP yield,
 * and applying gained EXP.
 */
public abstract class Codeamon {
    private CodeamonStats stats;
    private int level;
    private String nickname;
    private ArrayList<Attack> attacks;

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

        attacks = getAttacks();

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
     * Applies stat stage changes to a Stat.
     *
     * @param stat The Stat to apply the changes to
     * @param stages The number of stages to be applied
     */
    public void applyStatStageChange(Stat stat, int stages) {
        stats.applyStatStageChange(stat, stages);
    }

    /**
     * Gets the Attack stat to be used when landing a critical hit (crits ignore negative
     * attack changes).
     *
     * @return The attack for a crit
     */
    public int getAttackCritical() {
        return stats.getAttackCritical();
    }

    /**
     * Gets the Defense stat to be used when receiving a critical hit (crits ignore positive
     * defense changes).
     *
     * @return The defense for a crit
     */
    public int getDefenseCritical() {
        return stats.getDefenseCritical();
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
     * This Codeamon resets all stat changes.
     */
    public void resetStatStages() {
        stats.resetStatStages();
    }

    /**
     * Heals the Codeamon by a specified amount. If the amount of healing is 0 or less, the
     * Codeamon will be healed for 1 hit point.
     *
     * @param heal The amount of Hit Points to heal
     */
    public void heal(int heal) {
        if (heal < 1) {
            heal = 1;
        }

        stats.heal(heal);
    }

    /**
     * Damages the codeamon by the specified amount.
     *
     * @param damage The amount of damage inflicted. If this value is less than 1, the damage dealt
     *               will be 1
     */
    public void damage(int damage) {
        if (damage < 1) {
            damage = 1;
        }

        System.out.println(getName() + " took " + damage + " damagae!");

        stats.damage(damage);

        if (isFainted()) {
            System.out.println(getName() + " fainted!");
        }
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
     * @return The Attack stat (after any modifiers)
     */
    public int getAttack() {
        return stats.getAttack();
    }

    /**
     * Gets this Codeamon's defense stat.
     *
     * @return The Defense stat (after any modifiers)
     */
    public int getDefense() {
        return stats.getDefense();
    }

    /**
     * Gets this Codeamon's speed stat.
     *
     * @return The Speed Stat (after any modifiers)
     */
    public int getSpeed() {
        return stats.getSpeed();
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

    /**
     * Checks if this Codeamon has fainted or not. A Codeamon is considered to have fainted when
     * it's Hit Points drop to 0.
     *
     * @return True if it has fainted, false if it has not
     */
    public boolean isFainted() {
        return getCurrentHitPoints() == 0;
    }

    /**
     * This Codeamon attacks another Codeamon. The attack to be used will be chosen at random.
     *
     * @param opponent This Codeamon's opponent
     */
    public void attack(Codeamon opponent) {
    }

    public abstract ArrayList<Attack> getAttacks();

    //TODO: Implement EXP
}
