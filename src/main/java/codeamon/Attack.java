package codeamon;

/**
 * Data structure for a Codeamon's attack. It stores the attack's name, type, and base power.
 */
public abstract class Attack {
    private final String NAME;
    private final Type TYPE;
    private final int POWER;
    private final int ACCURACY;
    private final boolean HAS_EFFECT;
    private final int EFFECT_CHANCE;

    /**
     * Constructor for an attack with no additional effects.
     *
     * @param name The name of the attack
     * @param type The type of damage this attack inflicts
     * @param accuracy The percent chance of this attack hitting it's target. If this value is less
     *                than 1, it will be set to 1. If it is greater than 100, it will be set to 100
     * @param power The strength of the attack
     */
    public Attack(String name, Type type, int accuracy, int power) {
        NAME = name;
        TYPE = type;
        ACCURACY = accuracy;
        POWER = power;
        HAS_EFFECT = false;
        EFFECT_CHANCE = 0;
    }

    /**
     * Constructor for an attack with additional effects.
     *
     * @param name The name of the attack
     * @param type The type of damage this attack inflicts
     *             @param accuracy The percent chance of this attack hitting it's target. If this value is less
     *      *                than 1, it will be set to 1. If it is greater than 100, it will be set to 100
     * @param power The strength of the attack
     * @param chance The percent chance of an additional effect occurring. If this value is less
     *               than 1, it will be set to 1. If it is greater than 100, it will be set to 100
     */
    public Attack(String name, Type type, int accuracy, int power, int chance) {
        NAME = name;
        TYPE = type;
        ACCURACY = accuracy;
        POWER = power;
        HAS_EFFECT = true;
        EFFECT_CHANCE = chance;
    }

    /**
     * Constructor for a non-damaging attack
     *
     * @param name The name of the attack
     * @param type The type of the attack
     * @param chance The percent chance of this attack hitting it's target. If this value is less
     *              than 1, it will be set to 1. If it is greater than 100, it will be set to 100
     */
    public Attack(String name, Type type, int chance) {
        NAME = name;
        TYPE = type;
        ACCURACY = 100;
        POWER = 0;
        HAS_EFFECT = true;
        EFFECT_CHANCE = chance;
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
    public abstract void applyAttack(Codeamon user, Codeamon opponent);
}
