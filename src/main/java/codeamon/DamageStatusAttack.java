package codeamon;

/**
 * A DamageStatusAttack is a move that deals damage and can buff or debuff stats.
 */
public class DamageStatusAttack extends Attack {
    private final int STAGES;
    private final Stats STAT;
    private final boolean SELF_TARGET;
    /**
     * Creates an attack with the specified attributes.
     *
     * @param name The name of the attack
     * @param type The type of this attack
     * @param power The base power of this attack
     * @param stages The number of stages the targted stat will be raised or lowered
     * @param stat The stat to be raised or lowered
     */
    public DamageStatusAttack(String name, Type type, int power, int stages, Stats stat, int chance, int accuracy) {
        super(name, type, accuracy, power);
        STAGES = stages;
        STAT = stat;
        SELF_TARGET = false;
    }

    /**
     * Applies the effects of the attack.
     *
     * @param user The Codeamon using the attack
     * @param opponent The Codeamon the user if battling
     */
    @Override
    public void applyAttack(Codeamon user, Codeamon opponent) {
        //if attack did not miss, apply the status effects
        if (SELF_TARGET) {
            applyEffect(user);
        } else {
            applyEffect(opponent);
        }
    }

    private void applyEffect(Codeamon target) {
        if (STAT == Stats.Attack) {
            //apply modifer to mon
        } else if (STAT == Stats.Defense) {

        } else if (STAT == Stats.Speed) {

        }
    }
}
