package codeamon;

import java.util.Random;

/**
 * Data structure for a Codeamon's attack. Attacks are created using a Builder Design Pattern.
 * Contains a method for applying the effects of the attack the appropriate target. Attacks are
 * fully customizable. An attack can do any combination of the following: deal damage, have a
 * chance to buff or debuff either the user or the opponent, and heal the user. The only things
 * that every Attack must have is a name and attack type. Attack power, Accuracy and chance to
 * apply the buffs and debuffs are customizable, as it the self-healing amount. An attack that
 * deals damage must hit the opponent in order to apply any additional effects. A non-damaging
 * Attack that buffs, debuffs, or heals the user always succeeds. A non-damaging attack the targets
 * the opponent must hit the opponent in order to apply any additional effects.
 */
public class Attack {
    private final String NAME;
    private final Type TYPE;
    private final int POWER;
    private final int ACCURACY;
    private final int CRIT_CHANCE;
    private final int EFFECT_CHANCE;
    private final double HEAL;
    private final Stat STAT;
    private final int STAGES;
    private final boolean SELF;
    private static final int ONE = 1;
    private static final double MIN_HEAL = 0.01;
    private static final int MAX_STAGE = 6;
    private static final int MIN_STAGE = -6;
    private static final int ONE_HUNDRED = 100;

    /**
     * A Builder Factory Method for constructing an Attack. Allows full customization of the Attack
     * by provide methods to set the power, accuracy, the chance of applying stat effects, and any
     * self healing.
     */
    public static class Builder {
        private final String NAME;
        private final Type TYPE;

        //Default values
        private int power = 0;
        private int accuracy = 100;
        private int critChance = 12;
        private int effectChance = 0;
        private double heal = 0.0;
        private Stat stat = null;
        private int stages = 0;
        private boolean self = false;

        /**
         * Entry point for an Attack Builder that sets the required attributes for an attack,
         * the name and type.
         *
         * @param name The name of the attack
         * @param type The type of the attack
         */
        public Builder(String name, Type type) {
            this.NAME = name;
            this.TYPE = type;
        }

        /**
         * Sets the Power of the attack.
         *
         * @param power The power of the attack. If set to 0 or less, the attack will be flagged as
         *          a non-damaging move.
         * @return The Attack Builder
         */
        public Builder power(int power) {
            this.power = power;

            return this;
        }

        public Builder critChance(int critChance) {
            this.critChance = critChance;

            return this;
        }

        /**
         * Sets the accuracy of the attack.
         *
         * @param accuracy The accuracy of the attack. If greater than 100, it will be 100. If less
         *                 than 1, it will be 1.
         * @return The Attack Builder
         */
        public Builder accuracy(int accuracy) {
            if (accuracy < ONE) {
                this.accuracy = ONE;
            } else if (accuracy > ONE_HUNDRED) {
                this.accuracy = ONE_HUNDRED;
            } else {
                this.accuracy = accuracy;
            }

            return this;
        }

        /**
         * Using this method flags this Attack as one that can apply stat changes by setting the
         * chance of applying a buff or debuff to a stat, which stat to apply it to, and how many
         * stages to buff or debuff the stat.
         *
         * @param chance The percent chance of applying the buff or debuff. If less than 1 it will
         *             be set to 1
         * @param stat The stat to be buffed or debuffed
         * @param stages How many stages to buff or debuff. If greater than 6 or less than -6 this
         *             value will be set to 6 or -6, respectively.
         * @param self Flag that determines if the stat changes are to applied to the user or to
         *             to it's opponent. Set to true to target the user
         * @return The Attack Builder
         */
        public Builder statusEffect(int chance, Stat stat, int stages, boolean self) {
            if (chance < ONE) {
                this.effectChance = ONE;
            } else {
                this.effectChance = chance;
            }

            this.stat = stat;

            if (stages > MAX_STAGE) {
                this.stages = MAX_STAGE;
            } else if (stages < MIN_STAGE) {
                this.stages = MIN_STAGE;
            } else {
                this.stages = stages;
            }

            this.self = self;

            return this;
        }

        /**
         * Using this method flags that this attack heals the user.
         *
         * @param heal The fraction of the user's Hit Point maximum that will be healed after
         *             using this attack. If less than 0.01, this will be set to 0.01
         * @return The Attack Builder
         */
        public Builder heal(double heal) {
            if (heal < MIN_HEAL) {
                this.heal = MIN_HEAL;
            } else {
                this.heal = heal;
            }

            return this;
        }

        /**
         * Gets the Attack build by this Builder
         *
         * @return The Attack
         */
        public Attack build() {
            return new Attack(this);
        }
    }

    private Attack(Builder builder) {
        NAME = builder.NAME;
        TYPE = builder.TYPE;
        POWER = builder.power;
        CRIT_CHANCE = builder.critChance;
        ACCURACY = builder.accuracy;
        EFFECT_CHANCE = builder.effectChance;
        HEAL = builder.heal;
        STAT = builder.stat;
        STAGES = builder.stages;
        SELF = builder.self;
    }


    /**
     * Gets the name of this attack.
     *
     * @return The name
     */
    public String getName() {
        return NAME;
    }

    /**
     * Gets the type of this attack.
     *
     * @return The type
     */
    public Type getType() {
        return TYPE;
    }

    /**
     * Gets the power of this attack.
     *
     * @return The power
     */
    public int getPower() {
        return POWER;
    }

    /**
     * Applies the effects of the attack.
     *
     * @param user The Codeamon using the attack
     * @param opponent The Codeamon the user if battling
     */
    public void applyAttack(Codeamon user, Codeamon opponent) {
        //Stat changes that are to be applied by damaging Attacks will not be applied if the
        //Attack missed. Hit tracks if the target was hit by the attack so
        boolean hit = isHit();

        //If Attack hits and is a damaging attack, apply the damage
        if (hit && POWER > ONE) {
            applyDamage(user, opponent);

            //apply stat changes for damaging moves
            if (SELF) {
                applyEffect(user);
            } else if (EFFECT_CHANCE > ONE) {
                applyEffect(opponent);
            }

            //apply healing for damaging moves
            applyHeal(user);
        } else if (POWER < ONE) { //Attack is non-damaging
            //apply effects and healing self-targeting non-damaging moves
            if (SELF) {
                applyEffect(user);
                applyHeal(user);
            } else if (hit) {
                //apply effects and healing for opponent targeting non-damaging moves if it hit
                applyEffect(opponent);
                applyHeal(user);
            }
        }
    }

    /**
     * Applies the effects of the attack to the target.
     *
     * @param target The Codeamon to apply the stat changes to
     */
    private void applyEffect(Codeamon target) {
        //if event doesn't trigger, no effects applied
        if (!effectTriggered()) {
            return;
        }

        target.applyStatStageChange(STAT, STAGES);
    }

    /**
     * Applies the effects of a self-healing attack.
     *
     * @param user The user of the attack
     */
    private void applyHeal(Codeamon user) {
        if (HEAL >= MIN_HEAL) {
            user.heal((int) (user.getMaxHitPoints() * HEAL));
        }
    }

    /**
     * Calculates damage inflicted by this attack and applies it to the target.
     *
     * @param user The attacker
     * @param opponent The Codeamon being attacked
     */
    private void applyDamage(Codeamon user, Codeamon opponent) {
        boolean isCrit = isCritical();
        double crit;
        double damage;

        if (isCrit) {
            damage = ((((2.0 * user.getLevel() / 5.0) + 2.0) * POWER * user.getAttackCritical() / opponent.getDefenseCritical()) / 50) + 2.0;
            crit = 1.5;
        } else {
            damage = ((((2.0 * user.getLevel() / 5.0) + 2.0) * POWER * user.getAttack() / opponent.getDefense()) / 50) + 2.0;
            crit = 1.0;
        }

        double stab = 1.0;

        if (user.getType() == TYPE) {
            stab = 1.5;
        }

        double effective = TypeEffectiveness.getEffectiveness(TYPE, opponent.getType());

        //TODO: Weather and weather modifier
        damage *= crit * stab * effective;

        if (damage < ONE) {
            damage = 1.0;
        }

        opponent.damage((int) damage);
    }

    /**
     * Accuracy check to see if the attack lands.
     *
     * @return True if the Attack lands, false otherwise
     */
    private boolean isHit() {
        //Attacks with 100% accuracy always hit
        if (ACCURACY == ONE_HUNDRED) {
            return true;
        }

        //Get random number from 0-99, add one, then if it is <= Accuracy, the effect triggers
        Random random = new Random();

        return (random.nextInt(ONE_HUNDRED) + 1 <= ACCURACY);
    }

    /**
     * Check to see if a non-damaging effect is applied.
     *
     * @return True if the effect triggers, false otherwise
     */
    private boolean effectTriggered() {
        //An attack with a 100% effect chance always triggers and one with a 0% chance always fails
        if (EFFECT_CHANCE == ONE_HUNDRED) {
            return true;
        } else if (EFFECT_CHANCE < ONE) {
            return false;
        }

        //Get random number from 0-99, add one, then if it is <= Effect Chance, the effect triggers
        Random random = new Random();

        return (random.nextInt(ONE_HUNDRED) + 1 <= EFFECT_CHANCE);
    }

    /**
     * Check to see if the attack is a Critical Hit.
     *
     * @return True if the attack crits, false otherwise
     */
    private boolean isCritical() {
        //An attack with a 100% crit chance always crits and one with a 0% chance never does
        if (CRIT_CHANCE == ONE_HUNDRED) {
            return true;
        } else if (CRIT_CHANCE < ONE) {
            return false;
        }

        //Get random number from 0-99, add one, then if it is <= Crit Chance, it crits
        Random random = new Random();

        return (random.nextInt(ONE_HUNDRED) + 1 <= CRIT_CHANCE);
    }
}
