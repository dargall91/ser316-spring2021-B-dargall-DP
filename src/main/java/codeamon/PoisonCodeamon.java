package codeamon;

/**
 * Data structure for a Fire type species of Codeamon.
 */
public class PoisonCodeamon extends Codeamon {
    private static final String NAME = "Coronavirusmon";
    private static final Type TYPE = Type.Poison;
    private Attack[] attacks;
    private static int MAX_ATTACKS = 4;

    /**
     * Constructs a Fire Species of Codemon initializing its stats based on its level.
     *
     * @param level The level of the Codeamon
     */
    public PoisonCodeamon(int level) {
        super(CodeamonStatsFactory.getStats(TYPE, level), level);
        //Codeamon have different attacks depending on their initial level
        attacks = new Attack[MAX_ATTACKS];
        if (level <= 15) {
            attacks[0] = new Attack.AttackBuilder("Acid", Type.Poison)
                    .power(40)
                    .statusEffect(10, Stat.Defense, -1, false)
                    .build();
            attacks[1] = new Attack.AttackBuilder("Infestation", Type.Bug)
                    .power(20)
                    .statusEffect(10, Stat.Speed, -1, false)
                    .build();
            attacks[2] = new Attack.AttackBuilder("Defense Curl", Type.Normal)
                    .statusEffect(100, Stat.Defense, 1, true)
                    .build();
            attacks[3] = new Attack.AttackBuilder("Confusion", Type.Psychic)
                    .power(50)
                    .statusEffect(15, Stat.Attack, -1, false)
                    .build();
        } else if (level <= 30) {
            attacks[0] = new Attack.AttackBuilder("Acid Spray", Type.Poison)
                    .power(40)
                    .statusEffect(10, Stat.Defense, -2, false)
                    .build();
            attacks[1] = new Attack.AttackBuilder("Infestation", Type.Bug)
                    .power(20)
                    .statusEffect(10, Stat.Speed, -1, false)
                    .build();
            attacks[2] = new Attack.AttackBuilder("Defense Curl", Type.Normal)
                    .statusEffect(100, Stat.Defense, 1, true)
                    .build();
            attacks[3] = new Attack.AttackBuilder("Psybeam", Type.Psychic)
                    .power(65)
                    .statusEffect(20, Stat.Attack, -1, false)
                    .build();
        } else {
            attacks[0] = new Attack.AttackBuilder("Gunk Shot", Type.Poison)
                    .power(120)
                    .accuracy(80)
                    .statusEffect(30, Stat.Defense, -1, false)
                    .build();
            attacks[1] = new Attack.AttackBuilder("Leech Life", Type.Bug)
                    .power(80)
                    .heal(30)
                    .build();
            attacks[2] = new Attack.AttackBuilder("Defense Curl", Type.Normal)
                    .statusEffect(100, Stat.Defense, 1, true)
                    .build();
            attacks[3] = new Attack.AttackBuilder("Psychic", Type.Psychic)
                    .power(90)
                    .statusEffect(30, Stat.Attack, -1, false)
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
