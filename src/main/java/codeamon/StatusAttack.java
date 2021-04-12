package codeamon;

/**
 * A StatusAttack is a non-damaging attack that buffs or debuffs the targeted Codeamon's stats.
 */
public class StatusAttack extends Attack {
    private final int STAGES;
    private final Stats STAT;
    private final boolean SELF_TARGET;

    /**
     * Creates an attack with the specified attributes.
     *
     * @param name The name of the attack
     * @param type The type of this attack
     * @param stages The number of stages the targted stat will be raised or lowered
     * @param stat The stat to be raised or lowered
     */
    public StatusAttack(String name, Type type, int accuracy, int stages, Stats stat) {
        super(name, type, accuracy);
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
