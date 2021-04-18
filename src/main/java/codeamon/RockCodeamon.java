package codeamon;

/**
 * Data structure for a Fire type species of Codeamon.
 */
public class RockCodeamon extends Codeamon {
    private static final String NAME = "Bouldermon";
    private static final Type TYPE = Type.Rock;
    private Attack[] attacks;
    private static int MAX_ATTACKS = 4;

    /**
     * Constructs a Fire Species of Codemon initializing its stats based on its level.
     *
     * @param level The level of the Codeamon
     */
    public RockCodeamon(int level) {
        super(CodeamonStatsFactory.getStats(TYPE, level), level);
        //Codeamon have different attacks depending on their initial level
        attacks = new Attack[MAX_ATTACKS];
        if (level <= 15) {
            attacks[0] = new Attack.AttackBuilder("Rock Throw", Type.Rock)
                    .power(50)
                    .accuracy(90)
                    .build();
            attacks[1] = new Attack.AttackBuilder("Tackle", Type.Normal)
                    .power(40)
                    .build();
            attacks[2] = new Attack.AttackBuilder("Harden", Type.Normal)
                    .statusEffect(100, Stat.Defense, 1, true)
                    .build();
            attacks[3] = new Attack.AttackBuilder("Mach Punch", Type.Fighting)
                    .power(40)
                    .statusEffect(100, Stat.Speed, 1, true)
                    .build();
        } else if (level <= 30) {
            attacks[0] = new Attack.AttackBuilder("Rock Throw", Type.Rock)
                    .power(50)
                    .accuracy(90)
                    .build();
            attacks[1] = new Attack.AttackBuilder("Stomp", Type.Normal)
                    .power(65)
                    .accuracy(95)
                    .build();
            attacks[2] = new Attack.AttackBuilder("Harden", Type.Normal)
                    .statusEffect(100, Stat.Defense, 1, true)
                    .build();
            attacks[3] = new Attack.AttackBuilder("Brick Break", Type.Fighting)
                    .power(75)
                    .statusEffect(30, Stat.Defense, -1, true)
                    .build();
        } else {
            attacks[0] = new Attack.AttackBuilder("Head Smash", Type.Rock)
                    .power(150)
                    .accuracy(80)
                    .build();
            attacks[1] = new Attack.AttackBuilder("Sand Force ", Type.Ground)
                    .power(50)
                    .statusEffect(80, Stat.Attack, 2, true)
                    .build();
            attacks[2] = new Attack.AttackBuilder("Harden", Type.Normal)
                    .statusEffect(100, Stat.Defense, 1, true)
                    .build();
            attacks[3] = new Attack.AttackBuilder("Brick Break", Type.Fighting)
                    .power(75)
                    .statusEffect(30, Stat.Defense, -1, true)
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
