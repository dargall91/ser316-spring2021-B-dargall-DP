package codeamon;

/**
 * Data structure for a Codeamon's attack. Attacks are created using a Builder Design Pattern.
 * Contains a method for applying the effects of the attack the appropriate target.
 */
public class Attack {
    private final String NAME;
    private final Type TYPE;
    private final int POWER;
    private final int ACCURACY;
    private final int CHANCE;
    private final double HEAL;
    private final Stat STAT;
    private final int STAGES;
    private final boolean SELF;
    private static final int ONE = 1;
    private static final double MIN_HEAL = 0.01;
    private static final int MAX_STAGE = 6;
    private static final int MIN_STAGE = -6;

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
        private int chance = 0;
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

        /**
         * Sets the accuracy of the attack.
         *
         * @param accuracy The accuracy of the attack
         * @return The Attack Builder
         */
        public Builder accuracy(int accuracy) {
            this.accuracy = accuracy;
            return this;
        }

        /**
         * Using this method flags this Attack as one that can apply stat changes by setting the
         * chance of applying a buff or debuff to a stat, which stat to apply it to, and how many
         * stages to buff or debuff the stat.
         *
         * @param chance The percent chance of applying the buff or debuff. If less than 1 it will
         *               be set to 1
         * @param stat The stat to be buffed or debuffed
         * @param stages How many stages to buff or debuff. If greater than 6 or less than -6 this
         *               value will be set to 6 or -6, respectively.
         * @param self Flag that determines if the stat changes are to applied to the user or to
         *             to it's opponent. Set to true to target the user
         * @return The Attack Builder
         */
        public Builder statusEffect(int chance, Stat stat, int stages, boolean self) {
            if (chance < ONE) {
                this.chance = ONE;
            } else {
                this.chance = chance;
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
        ACCURACY = builder.accuracy;
        CHANCE = builder.chance;
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
        boolean hit = false;

        if (POWER > ONE) {
            //hit is needed because effects that are applied to opponents only happen if the attack hits
            hit = applyDamage(user, opponent);
        } else if (!SELF) {
            //accuracy check for non-damaging not targeting self
        } else {
            hit = false;
        }

        if (CHANCE >= ONE) {
            if (SELF) {
                applyEffect(user);
            } else if (hit) {
                applyEffect(opponent);
            }
        }

        if (HEAL >= MIN_HEAL) {
            applyHeal(user);
        }
    }

    /**
     * Applies the effects of the attack to the target.
     *
     * @param target The Codeamon to apply the stat changes to
     */
    private void applyEffect(Codeamon target) {

    }

    /**
     * Applies the effects of a self-healing attack.
     *
     * @param target The user of the attack
     */
    private void applyHeal(Codeamon target) {
        target.heal((int) (target.getMaxHitPoints() * HEAL));
    }

    /**
     * Calculates damage inflicted by this attack and applies it to the target.
     *
     * @param user The attacker
     * @param opponent THe Codeamon being attacked
     * @return True if the attack hit and did damage, false if it missed
     */
    private boolean applyDamage(Codeamon user, Codeamon opponent) {
        return true;
    }
}
