package codeamon;

/**
 * An abstract data structure that represents a Codeamon's stats. Has the necessary methods for
 * getting a Codeamon's Hit Points, Attack, and Defense stats, inflicting damage, healing damage,
 * and recalculating stats after leveling up.
 */
public abstract class CodeamonStats {
    //current and max hit points must be zero for initial call of setStats() to function
    private int maxHitPoints = 0;
    private int currentHitPoints = 0;
    private int attack;
    private int defense;
    private int speed;
    private int attackStage;
    private int defenseStage;
    private int speedStage;
    private static final int MAX_STAGE = 6;
    private static final int MIN_STAGE = -6;

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

        setStats(level);
        resetStatStages();
    }

    /**
     * Sets the stats for the Codeamon based on its current level.
     *
     * @param level The Codeamon's current level
     */
    private void setStats(int level) {
        int damage = maxHitPoints - currentHitPoints;

        maxHitPoints = calculateMaxHitPoints(getBaseHitPoints(), level);
        currentHitPoints = maxHitPoints - damage;
        attack = calculateStat(getBaseAttack(), level);
        defense = calculateStat(getBaseDefense(), level);
        speed = calculateStat(getBaseSpeed(), level);

    }

    /**
     * The Codeamon levels up and recalculates this Codeamon's stats based on it's new level.
     * The difference between it's current and maximum hit points will remain the same after
     * leveling up.
     *
     * @param level The Codeamon's new level
     */
    public void levelUp(int level) {
        setStats(level);
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
     * Heals the Codeamon by a specified amount. If the amount of healing is 0 or less, the
     * Codeamon will be healed for 1 hit point.
     *
     * @param heal The amount of Hit Points to heal
     */
    public void heal(int heal) {
        if (heal < 1) {
            heal = 1;
        }

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
        resetStatStages();
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

        currentHitPoints -= damage;

        if (currentHitPoints < 0) {
            currentHitPoints = 0;
        }
    }

    /**
     * Gets this Codeamon's attack stat.
     *
     * @return The Codeamon's Attack after modifiers
     */
    public int getAttackStat() {
        return (int) (attack * getModifier(attackStage));
    }

    /**
     * Gets the Attack stat to be used when landing a critical hit (crits ignore negative
     * attack changes).
     *
     * @return The attack for a crit
     */
    public int getAttackCritical() {
        if (attackStage > 0) {
            return getAttackStat();
        } else {
            return attack;
        }
    }

    /**
     * Gets this Codeamon's defense stat.
     *
     * @return The Codeamon's Defense after modifiers
     */
    public int getDefenseStat() {
        return (int) (defense * getModifier(defenseStage));
    }

    /**
     * Gets the Defense stat to be used when receiving a critical hit (crits ignore positive
     * defense changes).
     *
     * @return The defense for a crit
     */
    public int getDefenseCritical() {
        if (defenseStage > 0) {
            return defense;
        } else {
            return (int) (defense * getModifier(defenseStage));
        }
    }

    /**
     * Gets this Codeamon's speed stat.
     *
     * @return The Codeamon's Speed after modifiers
     */
    public int getSpeedStat() {
        return (int) (speed * getModifier(speedStage));
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

    /**
     * Resets the stages of this Codeamon's stats.
     */
    public void resetStatStages() {
        attackStage = 0;
        defenseStage = 0;
        speedStage = 0;
    }

    /**
     * Applies stat stage changes to a Stat. A stat's stage cannot go higher than 6 or less than
     * -6.
     *
     * @param stat The Stat to apply the changes to
     * @param stages The number of stages to be applied
     */
    public void applyStatStageChange(String name, Stat stat, int stages) {
        //if stages to change is 0
        if (stages == 0) {
            System.out.println(name + "'s stats were unchanged!");
            return;
        }

        if (stat == Stat.Attack) {
            //display a message of how the stages were changed
            if (stages > 0 && attackStage != MAX_STAGE) {
                System.out.println(name + "'s Attack increased!");
            } else if (stages > 0 && attackStage == MAX_STAGE) {
                System.out.println(name + "'s Attack can't go any higher!");
                return;
            } else if (stages < 0 && attackStage != MIN_STAGE) {
                System.out.println(name + "'s Attack decreased!");
            } else if (stages < 0 && attackStage == MIN_STAGE) {
                System.out.println(name + "'s Attack can't go any lower!");
                return;
            }

            attackStage += stages;

            //do not let the stages go above or below the max and min
            if (attackStage > MAX_STAGE) {
                attackStage = MAX_STAGE;
            }

            if (attackStage < MIN_STAGE) {
                attackStage = MIN_STAGE;
            }

        } else if (stat == Stat.Defense) {
            //display a message of how the stages were changed
            if (stages > 0 && defenseStage != MAX_STAGE) {
                System.out.println(name + "'s Defense increased!");
            } else if (stages > 0 && defenseStage == MAX_STAGE) {
                System.out.println(name + "'s Defense can't go any higher!");
                return;
            } else if (stages < 0 && defenseStage != MIN_STAGE) {
                System.out.println(name + "'s Defense decreased!");
            } else if (stages < 0 && defenseStage == MIN_STAGE) {
                System.out.println(name + "'s Defense can't go any lower!");
                return;
            }

            defenseStage += stages;

            //do not let the stages go above or below the max and min
            if (defenseStage > MAX_STAGE) {
                defenseStage = MAX_STAGE;
            }

            if (defenseStage < MIN_STAGE) {
                defenseStage = MIN_STAGE;
            }
        } else {
            //display a message of how the stages were changed
            if (stages > 0 && speedStage != MAX_STAGE) {
                System.out.println(name + "'s Speed increased!");
            } else if (stages > 0 && speedStage == MAX_STAGE) {
                System.out.println(name + "'s Speed can't go any higher!");
                return;
            } else if (stages < 0 && speedStage != MIN_STAGE) {
                System.out.println(name + "'s Speed decreased!");
            } else if (stages < 0 && speedStage == MIN_STAGE) {
                System.out.println(name + "'s Speed can't go any lower!");
                return;
            }

            speedStage += stages;

            //do not let the stages go above or below the max and min
            if (speedStage > MAX_STAGE) {
                speedStage = MAX_STAGE;
            }

            if (speedStage < MIN_STAGE) {
                speedStage = MIN_STAGE;
            }
        }
    }

    /**
     * Gets the multiplier for a stat based on the stage changes. The formula for the multiplier is
     * as follows: If the stage changes is 0, then the result is 1.0. If the stage changes is
     * negative, then the result is 2.0 / (2.0 + -1 * stages). If the stage changes is positive,
     * then the result is (2.0 + stages) / 2.0.
     *
     * @param stages The stat's stage change level
     * @return The multiplier
     */
    private double getModifier(int stages) {
        if (stages < 0) {
            return 2.0 / (2.0 + -1 * stages);
        } else if (stages == 0) {
            return 1.0;
        } else {
            return (2.0 + stages) / 2.0;
        }
    }
}
