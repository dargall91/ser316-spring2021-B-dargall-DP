package codeamon;

import java.util.ArrayList;
import java.util.Random;

/**
 * An abstract data structure that represents a Codeamon. Has the methods necessary for getting a
 * Codeamon's stats, name, and level, inflicting damage, healing damage, getting its EXP yield,
 * and applying gained EXP.
 */
public abstract class Codeamon implements Comparable<Codeamon> {
    private CodeamonStats stats;
    private int level;
    private String nickname;
    int exp;

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

        //starting experience points is equals to the minimum number of EXP required to reach
        //the current level, which is level^3. The exception is level 1 Codeamon, which have 0 EXP.
        //based on this formula: https://bulbapedia.bulbagarden.net/wiki/Experience#Medium_Fast

        if (level == 1) {
            exp = 0;
        } else {
            exp = (int) Math.pow(level, 3);
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
     * Applies stat stage changes to a Stat.
     *
     * @param stat The Stat to apply the changes to
     * @param stages The number of stages to be applied
     */
    public void applyStatStageChange(Stat stat, int stages) {
        stats.applyStatStageChange(getName(), stat, stages);
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
     * Heals the Codeamon by a specified amount. If the amount of healing is 0 or less, the
     * Codeamon will be healed for 1 hit point.
     *
     * @param heal The amount of Hit Points to heal
     */
    public void heal(int heal) {
        if (heal < 1) {
            heal = 1;
        }

        System.out.println(getName() + " recovered " + heal + " Hit Points!");

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
    public int getAttackStat() {
        return stats.getAttackStat();
    }

    /**
     * Gets this Codeamon's defense stat.
     *
     * @return The Defense stat (after any modifiers)
     */
    public int getDefenseStat() {
        return stats.getDefenseStat();
    }

    /**
     * Gets this Codeamon's speed stat.
     *
     * @return The Speed Stat (after any modifiers)
     */
    public int getSpeedStat() {
        return stats.getSpeedStat();
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
        Attack[] attackArr = getAttacks();

        //Get a random attack an apply it's effect
        Random rand = new Random();
        Attack attack = attackArr[rand.nextInt(attackArr.length)];

        attack.applyAttack(this, opponent);
    }

    /**
     * Gets the list of this Codeamon's attacks.
     *
     * @return An array of this Codeamon's attacks
     */
    public abstract Attack[] getAttacks();

    /**
     * Gives experience to the Codeamon in the party of the Trainer that defeated it. Only
     * non-fainted Codeamon can gain experience.
     *
     * @param party The Codeamon party of the Trainer who defeated this Codeamon
     */
    public void giveExperience(ArrayList<Codeamon> party) {
        //loosely based on the equation here:
        //https://bulbapedia.bulbagarden.net/wiki/Experience#Gain_formula
        //All modifiers in that formula are set to 1, and base EXP Yield being used is 150
        int givenExp = 150 * level / 7;

        for (Codeamon c : party) {
            if (!c.isFainted()) {
                c.gainExperience(givenExp);
            }
        }
    }

    /**
     * Gives this Codeamon experience points then checks if it leveled up. The experience points
     * required to reach a given level is that level to the power of 3. Level 100 Codeamon cannot
     * gain levels.
     *
     * @param exp The amount of EXP to be gained.
     */
    public void gainExperience(int exp) {
        if (level == 100) {
            return;
        }

        System.out.println(getName() + " gained " + exp + " EXP Points!");

        this.exp += exp;

        if (Math.pow(level + 1, 3) <= this.exp) {
            level++;
            stats.levelUp(level);
            System.out.println(getName() + " grew to level " + level + "!");
        }
    }

    /**
     * Prints this Codeamon's name, current hit points, and max hit points. For example,
     */
    public void printBattleStatus() {
        System.out.println(getName() + ":");
        System.out.println("Level: " + level);
        System.out.println("HP: " + getCurrentHitPoints() + "/" + getMaxHitPoints() + " HP");
    }

    /**
     * Gets the total amount of EXP this Codeamon has.
     *
     * @return The total experience points
     */
    public int getExperiencePoints() {
        return exp;
    }

    @Override
    public int compareTo(Codeamon codeamon) {
        //this Codeamon is not fainted and other Codeamon is
        if (!isFainted() && codeamon.isFainted()) {
            return -1;
        }
        //this Codeamon is fainted and other Codeamon is not
        if (isFainted() && !codeamon.isFainted()) {
            return 1;
        }
        //bot Codeamon are fainted they considered to be equal for the purpose of sorting
        if (isFainted() && codeamon.isFainted()) {
            return 0;
        }

        //neither Codeamon are fainted
        if (level < codeamon.getLevel()) {
            return -1;
        }

        if (level > codeamon.getLevel()) {
            return 1;
        }

        return 0;

    }
}
