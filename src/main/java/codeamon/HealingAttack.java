package codeamon;

/**
 * A HealingAttack is a non-damaging attack heals the user.
 */
public class HealingAttack extends Attack {
    private final Stats STAT = Stats.HitPoints;
    private final double HEAL;

    /**
     * Creates an attack with the specified attributes.
     *
     * @param name The name of the attack
     * @param type The type of this attack
     * @param heal The percentage of the Codeamon's max hit points to be healed
     */
    public HealingAttack(String name, Type type, double heal) {
        super(name, type, 100);
        HEAL = heal;
    }

    /**
     * Applies the effects of the attack.
     *
     * @param user The Codeamon using the attack
     * @param opponent The Codeamon the user if battling
     */
    @Override
    public void applyAttack(Codeamon user, Codeamon opponent) {
        applyEffect(user);
    }

    private void applyEffect(Codeamon user) {
        user.heal((int) (user.getMaxHitPoints() * HEAL));
    }
}
