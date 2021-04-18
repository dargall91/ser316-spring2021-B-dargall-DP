package codeamon;

/**
 * Data structure for a Fire type species of Codeamon.
 */
public class GhostCodeamon extends Codeamon {
    private static final String NAME = "Deadmon";
    private static final Type TYPE = Type.Ghost;
    private Attack[] attacks;
    private static int MAX_ATTACKS = 4;

    /**
     * Constructs a Fire Species of Codemon initializing its stats based on its level.
     *
     * @param level The level of the Codeamon
     */
    public GhostCodeamon(int level) {
        super(CodeamonStatsFactory.getStats(TYPE, level), level);
        //Codeamon have different attacks depending on their initial level
        attacks = new Attack[MAX_ATTACKS];
        if (level <= 15) {
            attacks[0] = new Attack.AttackBuilder("Shadow Sneak", Type.Ghost)
                    .power(40)
                    .statusEffect(100, Stat.Speed, 1, true)
                    .build();
            attacks[1] = new Attack.AttackBuilder("Acid", Type.Poison)
                    .power(40)
                    .statusEffect(10, Stat.Defense, -1, false)
                    .build();
            attacks[2] = new Attack.AttackBuilder("Leer", Type.Normal)
                    .statusEffect(100, Stat.Defense, -1, false)
                    .build();
            attacks[3] = new Attack.AttackBuilder("Disarming Voice", Type.Fairy)
                    .power(40)
                    .build();
        } else if (level <= 30) {
            attacks[0] = new Attack.AttackBuilder("Ominous Wind", Type.Ghost)
                    .power(60)
                    .statusEffect(15, Stat.Attack, 1, true)
                    .build();
            attacks[1] = new Attack.AttackBuilder("Acid", Type.Poison)
                    .power(40)
                    .statusEffect(10, Stat.Defense, -1, false)
                    .build();
            attacks[2] = new Attack.AttackBuilder("Leer", Type.Normal)
                    .statusEffect(100, Stat.Defense, -1, false)
                    .build();
            attacks[3] = new Attack.AttackBuilder("Disarming Voice", Type.Fairy)
                    .power(40)
                    .build();
        } else {
            attacks[0] = new Attack.AttackBuilder("Shadow Ball", Type.Ghost)
                    .power(80)
                    .statusEffect(30, Stat.Defense, -1, false)
                    .build();
            attacks[1] = new Attack.AttackBuilder("Sludge Bomb", Type.Poison)
                    .power(90)
                    .statusEffect(10, Stat.Speed, -1, false)
                    .build();
            attacks[2] = new Attack.AttackBuilder("Leer", Type.Normal)
                    .statusEffect(100, Stat.Defense, -1, false)
                    .build();
            attacks[3] = new Attack.AttackBuilder("Frost Breath", Type.Normal)
                    .power(60)
                    .accuracy(90)
                    .critChance(100)
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
