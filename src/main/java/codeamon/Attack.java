package codeamon;

/**
 * Data structure for a Codeamon's attack. It stores the attack's name, type, and base power.
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
         * Entry point for an ATtack Builder that sets the required fields, type and name
         */
        public Builder(String name, Type type) {
            this.NAME = name;
            this.TYPE = type;
        }

        public Builder power(int power) {
            this.power = power;
            return this;
        }

        public Builder accuracy(int accuracy) {
            this.accuracy = accuracy;
            return this;
        }

        public Builder chance(int chance) {
            this.chance = chance;
            return this;
        }

        public Builder heal(double heal) {
            this.heal = heal;
            return this;
        }

        public Builder stat(Stat stat) {
            this.stat = stat;
            return this;
        }

        public Builder stages(int stages) {
            this.stages = stages;
            return this;
        }

        public Builder self(boolean self) {
            this.self = self;
            return this;
        }

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
    }
}
