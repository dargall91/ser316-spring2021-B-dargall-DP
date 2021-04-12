package codeamon;

/**
 * A DamageAttack is an attack that deals damage and has no additional effects.
 */
public class DamageAttack extends Attack {
    /**
     * Creates an attack with the specified attributes.
     *
     * @param name The name of the attack
     * @param type The type of this attack
     * @param power The base power of this attack
     */
    public DamageAttack(String name, Type type, int power, int accuracy) {
        super(name, type, accuracy, power);
    }

    /**
     * Applies the effects of the attack.
     *
     * @param user The Codeamon using the attack
     * @param opponent The Codeamon the user if battling
     */
    @Override
    public void applyAttack(Codeamon user, Codeamon opponent) {

    }
}
