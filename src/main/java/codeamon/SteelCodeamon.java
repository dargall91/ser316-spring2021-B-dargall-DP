package codeamon;

/**
 * Data structure for a Fire type species of Codeamon.
 */
public class SteelCodeamon extends Codeamon {
    private static final String NAME = "Steelmon";
    private static final Type TYPE = Type.Steel;
    private Attack[] attacks;
    private static int MAX_ATTACKS = 4;

    /**
     * Constructs a Fire Species of Codemon initializing its stats based on its level.
     *
     * @param level The level of the Codeamon
     */
    public SteelCodeamon(int level) {
        super(CodeamonStatsFactory.getStats(TYPE, level), level);
        //Codeamon have different attacks depending on their initial level
        attacks = new Attack[MAX_ATTACKS];
        if (level <= 15) {
            attacks[0] = new Attack.AttackBuilder("Metal Claw", Type.Steel)
                    .power(50)
                    .statusEffect(10, Stat.Attack, 1, true)
                    .build();
            attacks[1] = new Attack.AttackBuilder("Tackle", Type.Normal)
                    .power(40)
                    .build();
            attacks[2] = new Attack.AttackBuilder("Shift Gear", Type.Steel)
                    .statusEffect(100, Stat.Speed, 2, true)
                    .build();
            attacks[3] = new Attack.AttackBuilder("Rock Throw", Type.Rock)
                    .power(50)
                    .accuracy(90)
                    .build();
        } else if (level <= 30) {
            attacks[0] = new Attack.AttackBuilder("Metal Claw", Type.Steel)
                    .power(50)
                    .statusEffect(10, Stat.Attack, 1, true)
                    .build();
            attacks[1] = new Attack.AttackBuilder("Cut", Type.Normal)
                    .power(50)
                    .accuracy(95)
                    .build();
            attacks[2] = new Attack.AttackBuilder("Shift Gear", Type.Steel)
                    .statusEffect(100, Stat.Speed, 2, true)
                    .build();
            attacks[3] = new Attack.AttackBuilder("Rock Throw", Type.Rock)
                    .power(50)
                    .accuracy(90)
                    .build();
        } else {
            attacks[0] = new Attack.AttackBuilder("Meteor Mash", Type.Steel)
                    .power(90)
                    .accuracy(90)
                    .statusEffect(30, Stat.Attack, 1, false)
                    .build();
            attacks[1] = new Attack.AttackBuilder("Slash", Type.Normal)
                    .power(70)
                    .critChance(50)
                    .build();
            attacks[2] = new Attack.AttackBuilder("Shift Gear", Type.Steel)
                    .statusEffect(100, Stat.Speed, 2, true)
                    .build();
            attacks[3] = new Attack.AttackBuilder("Stone Edge", Type.Rock)
                    .power(100)
                    .accuracy(80)
                    .critChance(50)
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
