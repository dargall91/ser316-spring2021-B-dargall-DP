package codeamon;

/**
 * Data structure for a Fire type species of Codeamon.
 */
public class ElectricCodeamon extends Codeamon {
    private static final String NAME = "Ampmon";
    private static final Type TYPE = Type.Electric;
    private Attack[] attacks;
    private static int MAX_ATTACKS = 4;

    /**
     * Constructs a Fire Species of Codemon initializing its stats based on its level.
     *
     * @param level The level of the Codeamon
     */
    public ElectricCodeamon(int level) {
        super(CodeamonStatsFactory.getStats(TYPE, level), level);
        //Codeamon have different attacks depending on their initial level
        attacks = new Attack[MAX_ATTACKS];
        if (level <= 15) {
            attacks[0] = new Attack.AttackBuilder("Thunder Shock", Type.Electric)
                    .power(40)
                    .statusEffect(25, Stat.Speed, -1, false)
                    .build();
            attacks[1] = new Attack.AttackBuilder("Tackle", Type.Normal)
                    .power(40)
                    .build();
            attacks[2] = new Attack.AttackBuilder("Charge", Type.Electric)
                    .statusEffect(100, Stat.Defense, 1, true)
                    .heal(10)
                    .build();
            attacks[3] = new Attack.AttackBuilder("Magnet Bomb", Type.Steel)
                    .power(60)
                    .build();
        } else if (level <= 30) {
            attacks[0] = new Attack.AttackBuilder("Shock Wave", Type.Electric)
                    .power(60)
                    .statusEffect(25, Stat.Speed, -1, false)
                    .build();
            attacks[1] = new Attack.AttackBuilder("Swift", Type.Normal)
                    .power(60)
                    .build();
            attacks[2] = new Attack.AttackBuilder("Charge", Type.Electric)
                    .statusEffect(100, Stat.Defense, 1, true)
                    .heal(10)
                    .build();
            attacks[3] = new Attack.AttackBuilder("Magnet Bomb", Type.Steel)
                    .power(60)
                    .build();
        } else {
            attacks[0] = new Attack.AttackBuilder("Thunder", Type.Electric)
                    .power(110)
                    .accuracy(70)
                    .statusEffect(30, Stat.Speed, -1, false)
                    .build();
            attacks[1] = new Attack.AttackBuilder("Mystical Fire", Type.Fire)
                    .power(75)
                    .statusEffect(100, Stat.Attack, -1, false)
                    .build();
            attacks[2] = new Attack.AttackBuilder("Charge", Type.Electric)
                    .statusEffect(100, Stat.Defense, 1, true)
                    .heal(10)
                    .build();
            attacks[3] = new Attack.AttackBuilder("Flash Cannon", Type.Steel)
                    .power(80)
                    .statusEffect(20, Stat.Defense, -1, false)
                    .build();
        }
    }

    /**
     * Gets this Codeamon's species's name.
     *
     * @return The name of this Codeamon Species
     */
    public String getSpeciesName() {
        return NAME;
    }

    /**
     * Gets this Codeamon's species's type.
     *
     * @return The type
     */
    public Type getType() {
        return TYPE;
    }

    /**
     * Gets the list of this Codeamon's attacks.
     *
     * @return An array of this Codeamon's attacks
     */
    public Attack[] getAttacks() {
        Attack[] copy = attacks;
        return copy;
    }
}
