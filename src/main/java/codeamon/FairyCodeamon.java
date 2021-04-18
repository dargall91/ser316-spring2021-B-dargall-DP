package codeamon;

/**
 * Data structure for a Fire type species of Codeamon.
 */
public class FairyCodeamon extends Codeamon {
    private static final String NAME = "Pixiemon";
    private static final Type TYPE = Type.Fairy;
    private Attack[] attacks;
    private static int MAX_ATTACKS = 4;

    /**
     * Constructs a Fire Species of Codemon initializing its stats based on its level.
     *
     * @param level The level of the Codeamon
     */
    public FairyCodeamon(int level) {
        super(CodeamonStatsFactory.getStats(TYPE, level), level);
        //Codeamon have different attacks depending on their initial level
        attacks = new Attack[MAX_ATTACKS];
        if (level <= 15) {
            attacks[0] = new Attack.AttackBuilder("Fairy Wind", Type.Fairy)
                    .power(40)
                    .build();
            attacks[1] = new Attack.AttackBuilder("Wing Attack", Type.Flying)
                    .power(60)
                    .build();
            attacks[2] = new Attack.AttackBuilder("Tail Whip", Type.Normal)
                    .statusEffect(100, Stat.Defense, -1, false)
                    .build();
            attacks[3] = new Attack.AttackBuilder("Bite", Type.Dark)
                    .power(60)
                    .build();
        } else if (level <= 30) {
            attacks[0] = new Attack.AttackBuilder("Draining Kiss", Type.Fairy)
                    .power(50)
                    .heal(10)
                    .build();
            attacks[1] = new Attack.AttackBuilder("Wing Attack", Type.Flying)
                    .power(60)
                    .build();
            attacks[2] = new Attack.AttackBuilder("Tail Whip", Type.Normal)
                    .statusEffect(100, Stat.Defense, -1, false)
                    .build();
            attacks[3] = new Attack.AttackBuilder("Bite", Type.Dark)
                    .power(60)
                    .build();
        } else {
            attacks[0] = new Attack.AttackBuilder("Moonblast", Type.Fairy)
                    .power(95)
                    .statusEffect(30, Stat.Attack, -1, false)
                    .build();
            attacks[1] = new Attack.AttackBuilder("Wing Attack", Type.Flying)
                    .power(60)
                    .build();
            attacks[2] = new Attack.AttackBuilder("Tail Whip", Type.Normal)
                    .statusEffect(100, Stat.Defense, -1, false)
                    .build();
            attacks[3] = new Attack.AttackBuilder("Moonlight", Type.Fairy)
                    .heal(25)
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
