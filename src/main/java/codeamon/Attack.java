package codeamon;

import java.util.Random;

/**
 * Data structure for a Codeamon's attack. Attacks are created using a Builder Design Pattern.
 * Contains a method for applying the effects of the attack the appropriate target. Attacks are
 * fully customizable. An attack can do any combination of the following: deal damage, have a
 * chance to buff or debuff either the user or the opponent, and heal the user. The only things
 * that every Attack must have is a name and attack type. Attack power, Accuracy and chance to
 * apply the buffs and debuffs are customizable, as it the self-healing amount. An attack that
 * deals damage must hit the opponent in order to apply any additional effects or healing. A
 * non-damaging Attack that buffs, debuffs, or heals the user always succeeds. A non-damaging
 * attack the targets the opponent must hit the opponent in order to apply any additional effects.
 */
public class Attack {
    //TODO: is it really necessary for these to be final? No setters implicitly makes them final
    private final String name;
    private final Type type;
    private final int power;
    private final int accuracy;
    private final int critChance;
    private final int effectChance;
    private final double heal;
    private final Stat stat;
    private final int stages;
    private final boolean self;
    private static final int ONE = 1;
    private static final double MIN_HEAL = 0.01;
    private static final int MAX_STAGE = 6;
    private static final int MIN_STAGE = -6;
    private static final int ONE_HUNDRED = 100;

    /**
     * A Builder Method for constructing an Attack. Allows full customization of the Attack by
     * providing methods to set the power, accuracy, the chance of applying stat effects, and any
     * self healing. At a minimum, an Attack must have a name and a Type. If no other attributes
     * are set, this Attack will do nothing.
     */
    public static class AttackBuilder {
        private final String name;
        private final Type type;

        //Default values
        private int power = 0;
        private int accuracy = 100;
        private int critChance = 15;
        private int effectChance = 0;
        private double heal = 0.0;
        private Stat stat = null;
        private int stages = 0;
        private boolean self = false;

        /**
         * Entry point for an AttackBuilder that sets the required attributes for an attack,
         * the name and type.
         * <p>
         *     Default values if no other Builder methods are used:
         *     <li>Power: 0</li>
         *     <li>Accruacy: 100%</li>
         *     <li>Critical Hit Chance: 15%</li>
         *     <li>Effect Chance: 0%</li>
         *     <li>Self Healing: 0%</li>
         *     <li>Stats Changed: None</li>
         *     <li>Stat Levels Applied: 0</li>
         *     <li>Effect Targets Self: false</li>
         * </p>

         * @param name The name of the attack
         * @param type The type of the attack
         */
        public AttackBuilder(String name, Type type) {
            this.name = name;
            this.type = type;
        }

        /**
         * Sets the Power of the attack.
         *
         * @param power The power of the attack. If set to 0 or less, the attack will be flagged as
         *          a non-damaging move.
         * @return The AttackBuilder
         */
        public AttackBuilder power(int power) {
            this.power = power;

            return this;
        }

        /**
         * Sets the chance for this attack to be a critical hit.
         *
         * @param critChance The percent chance for the attack to become a critical hit. If greater
         *                   than 100, it will be 100. If less than 1, it will be 0.
         * @return The AttackBuilder
         */
        public AttackBuilder critChance(int critChance) {
            if (critChance < ONE) {
                this.critChance = 0;
            } else if (critChance > ONE_HUNDRED) {
                this.critChance = ONE_HUNDRED;
            } else {
                this.critChance = critChance;
            }

            return this;
        }

        /**
         * Sets the accuracy of the attack.
         *
         * @param accuracy The accuracy of the attack. If greater than 100, it will be 100. If less
         *                 than 1, it will be 1.
         * @return The AttackBuilder
         */
        public AttackBuilder accuracy(int accuracy) {
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
         * @param effectChance The percent chance of applying the buff or debuff. If less than 1 it
         *                     will be set to 1
         * @param stat The stat to be buffed or debuffed
         * @param stages How many stages to buff or debuff. If greater than 6 or less than -6 this
         *               value will be set to 6 or -6, respectively.
         * @param self Flag that determines if the stat changes are to applied to the user or to
         *             to it's opponent. Set to true to target the user
         * @return The AttackBuilder
         */
        public AttackBuilder statusEffect(int effectChance, Stat stat, int stages, boolean self) {
            if (effectChance < ONE) {
                this.effectChance = ONE;
            } else if (effectChance > ONE_HUNDRED) {
                this.effectChance = ONE_HUNDRED;
            } else {
                this.effectChance = effectChance;
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
         * @param heal The percentage of the user's Hit Point maximum that will be healed after
         *             using this attack. If less than 1, this will be set to 1
         * @return The AttackBuilder
         */
        public AttackBuilder heal(int heal) {
            if (heal < ONE) {
                this.heal = ONE / 100.0;
            } else if (heal > ONE_HUNDRED) {
                this.heal = 1.0;
            } else {
                this.heal = heal / 100.0;
            }

            return this;
        }

        /**
         * Gets the Attack built by the AttackBuilder.
         *
         * @return The Attack
         */
        public Attack build() {
            return new Attack(this);
        }
    }

    private Attack(AttackBuilder builder) {
        name = builder.name;
        type = builder.type;
        power = builder.power;
        critChance = builder.critChance;
        accuracy = builder.accuracy;
        effectChance = builder.effectChance;
        heal = builder.heal;
        stat = builder.stat;
        stages = builder.stages;
        self = builder.self;
    }

    /**
     * Gets the name of this attack.
     *
     * @return The name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the type of this attack.
     *
     * @return The type
     */
    public Type getType() {
        return type;
    }

    /**
     * Gets the power of this attack.
     *
     * @return The power
     */
    public int getPower() {
        return power;
    }

    public int getCritChance() {
        return critChance;
    }

    /**
     * Gets the accruacy of this attack.
     *
     * @return The percent chance of this attack hitting the target
     */
    public int getAccuracy() {
        return accuracy;
    }

    /**
     * Gets the chance of this attack applying an additional effect.
     *
     * @return The percent chance of applying an additional effect
     */
    public int getEffectChance() {
        return effectChance;
    }

    /**
     * Gets the amount of hit points this attack heals the user.
     *
     * @return The percentage of hit points healed with this attack
     */
    public int getHeal() {
        return (int) (heal * ONE_HUNDRED);
    }

    /**
     * Gets the Stat buffed or debuffed by this attack.
     *
     * @return The buffed or debuffed Stat, or null if this attack does not affect stats
     */
    public Stat getStat() {
        return stat;
    }

    /**
     * Gets the number of stat stage changes to be applied to the buffed or debuffed stat.
     *
     * @return The amount of stat stage changes to be applied by this attack, ranging from -6 to 6
     *         inclusively.
     */
    public int getStages() {
        return stages;
    }

    /**
     * Checks if this attack targets the user with any stat changes applied by this attack.
     *
     * @return True if the stat changes are applied to the user, false if not
     */
    public boolean getSelf() {
        return self;
    }

    /**
     * Applies the effects of the attack.
     *
     * @param user The Codeamon using the attack
     * @param opponent The Codeamon the user if battling
     * @return False if the attack delas no damage, applies no healing, and applies no stat
     *         changes, otherwise it returns true
     */
    public boolean applyAttack(Codeamon user, Codeamon opponent) {
        System.out.println(user.getName() + " used " + name + "!");

        //If Attack deals damage
        if (power > ONE) {
            if (applyDamage(user, opponent)) {
                //apply stat changes for damaging moves
                if (self) {
                    applyEffect(user);
                } else if (!opponent.isFainted()) {
                    //If the oppoennt didn't faint from the damage, apply any effects to them
                    applyEffect(opponent);
                }
            }
        } else if (self) { //Attack is non-damaging and targets self
            applyEffect(user);
            applyHeal(user);
        } else if (effectChance >= ONE && isHit() && !opponent.isFainted()) {
            //This is a non-damaging move that targets the opponent and it hit
            applyEffect(opponent);
            applyHeal(user);
        } else if (heal >= MIN_HEAL) {
            //This is a healing attack with no other effects
            applyHeal(user);
        } else {
            //This attack does nothing
            System.out.println("But nothing happened!");
            return false;
        }

        return true;
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

        target.applyStatStageChange(stat, stages);
    }

    /**
     * Applies the effects of a self-healing attack.
     *
     * @param user The user of the attack
     */
    private void applyHeal(Codeamon user) {
        if (heal >= MIN_HEAL) {
            user.heal((int) (user.getMaxHitPoints() * heal));
        }
    }

    /**
     * Calculates damage inflicted by this attack and applies it to the target.
     *
     * @param user The attacker
     * @param opponent The Codeamon being attacked
     * @return True if the attack hit, otherwise false
     */
    private boolean applyDamage(Codeamon user, Codeamon opponent) {
        //Check if the attack hit or missed, and display a message if it missed
        if (!isHit()) {
            System.out.println("The attack missed!");
            return false;
        }

        boolean isCrit = isCritical();
        double crit;
        double damage;

        if (isCrit) {
            damage = (((2.0 * user.getLevel() / 5.0 + 2.0) * power * user.getAttackCritical()
                    / opponent.getDefenseCritical()) / 50.0) + 2.0;
            crit = 1.5;
        } else {
            damage = (((2.0 * user.getLevel() / 5.0 + 2.0) * power * user.getAttackStat()
                    / opponent.getDefenseStat()) / 50.0) + 2.0;
            crit = 1.0;
        }

        double stab = 1.0;

        if (user.getType() == type) {
            stab = 1.5;
        }

        double effective = TypeMatchup.getEffectiveness(type, opponent.getType());

        //TODO: Weather and weather modifier
        damage *= crit * stab * effective;

        //Check if the attack did positive damage (If * 0 type damage modifiers are implemented it
        //would result in 0 damage being dealt here)
        if (damage > 0) {
            //apply the damage
            opponent.damage((int) damage);

            //apply healing for damaging moves
            applyHeal(user);

            return true;
        }

        return false;
    }

    /**
     * Accuracy check to see if the attack lands.
     *
     * @return True if the Attack lands, false otherwise
     */
    private boolean isHit() {
        //Attacks with 100% accuracy always hit
        if (accuracy == ONE_HUNDRED) {
            return true;
        }

        //Get random number from 0-99, add one, then if it is <= Accuracy, the attack hits
        Random random = new Random();

        return (random.nextInt(ONE_HUNDRED) + 1 <= accuracy);
    }

    /**
     * Check to see if a non-damaging effect is applied.
     *
     * @return True if the effect triggers, false otherwise
     */
    private boolean effectTriggered() {
        //An attack with a 100% effect chance always triggers and one with a 0% chance always fails
        if (effectChance == ONE_HUNDRED) {
            return true;
        } else if (effectChance < ONE) {
            return false;
        }

        //Get random number from 0-99, add one, then if it is <= Effect Chance, the effect triggers
        Random random = new Random();

        return (random.nextInt(ONE_HUNDRED) + 1 <= effectChance);
    }

    /**
     * Check to see if the attack is a Critical Hit.
     *
     * @return True if the attack crits, false otherwise
     */
    private boolean isCritical() {
        //An attack with a 100% crit chance always crits and one with a 0% chance never does
        if (critChance == ONE_HUNDRED) {
            System.out.println("Critical hit!");
            return true;
        } else if (critChance < ONE) {
            return false;
        }

        //Get random number from 0-99, add one, then if it is <= Crit Chance, it crits
        Random random = new Random();

        return (random.nextInt(ONE_HUNDRED) + 1 <= critChance);
    }
}
