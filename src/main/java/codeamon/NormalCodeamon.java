package codeamon;

/**
 * Data structure for a Fire type species of Codeamon.
 */
public class NormalCodeamon extends Codeamon {
    private static final String NAME = "Regularmon";
    private static final Type TYPE = Type.Normal;
    private Attack[] attacks;
    private static int MAX_ATTACKS = 4;

    /**
     * Constructs a Fire Species of Codemon initializing its stats based on its level.
     *
     * @param level The level of the Codeamon
     */
    public NormalCodeamon(int level) {
        super(CodeamonStatsFactory.getStats(TYPE, level), level);
        //Codeamon have different attacks depending on their initial level
        attacks = new Attack[MAX_ATTACKS];
        if (level <= 15) {
            attacks[0] = new Attack.AttackBuilder("Scratch", Type.Fire)
                    .power(40)
                    .build();
            attacks[1] = new Attack.AttackBuilder("Disarming Voice", Type.Fairy)
                    .power(40)
                    .build();
            attacks[2] = new Attack.AttackBuilder("Defense Curl", Type.Normal)
                    .statusEffect(100, Stat.Defense, 1, true)
                    .build();
            attacks[3] = new Attack.AttackBuilder("Bite", Type.Dark)
                    .power(60)
                    .build();
        } else if (level <= 30) {
            attacks[0] = new Attack.AttackBuilder("Stomp", Type.Normal)
                    .power(65)
                    .build();
            attacks[1] = new Attack.AttackBuilder("Disarming Voice", Type.Fairy)
                    .power(40)
                    .build();
            attacks[2] = new Attack.AttackBuilder("Defense Curl", Type.Normal)
                    .statusEffect(100, Stat.Defense, 1, true)
                    .build();
            attacks[3] = new Attack.AttackBuilder("Bite", Type.Dark)
                    .power(60)
                    .build();
        } else {
            attacks[0] = new Attack.AttackBuilder("Body Slam", Type.Fire)
                    .power(85)
                    .statusEffect(30, Stat.Speed, -1, false)
                    .build();
            attacks[1] = new Attack.AttackBuilder("Shadow Claw", Type.Ghost)
                    .power(70)
                    .critChance(70)
                    .build();
            attacks[2] = new Attack.AttackBuilder("Defense Curl", Type.Normal)
                    .statusEffect(100, Stat.Defense, 1, true)
                    .build();
            attacks[3] = new Attack.AttackBuilder("Recover", Type.Normal)
                    .heal(40)
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
