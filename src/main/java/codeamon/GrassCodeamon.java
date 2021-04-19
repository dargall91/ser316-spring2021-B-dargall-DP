package codeamon;

/**
 * Data structure for a Fire type species of Codeamon.
 */
public class GrassCodeamon extends Codeamon {
    private static final String NAME = "Flowermon";
    private static final Type TYPE = Type.Grass;
    private Attack[] attacks;
    private static int MAX_ATTACKS = 4;

    /**
     * Constructs a Fire Species of Codemon initializing its stats based on its level.
     *
     * @param level The level of the Codeamon
     */
    public GrassCodeamon(int level) {
        super(CodeamonStatsFactory.getStats(TYPE, level), level);
        //Codeamon have different attacks depending on their initial level
        attacks = new Attack[MAX_ATTACKS];
        if (level <= 15) {
            attacks[0] = new Attack.AttackBuilder("Vine Whip", Type.Grass)
                    .power(45)
                    .build();
            attacks[1] = new Attack.AttackBuilder("Acid", Type.Poison)
                    .power(40)
                    .statusEffect(10, Stat.Defense, -1, false)
                    .build();
            attacks[2] = new Attack.AttackBuilder("Growth", Type.Grass)
                    .statusEffect(100, Stat.Defense, 1, true)
                    .build();
            attacks[3] = new Attack.AttackBuilder("Mud Shot", Type.Ground)
                    .power(55)
                    .accuracy(95)
                    .statusEffect(100, Stat.Speed, -1, false)
                    .build();
        } else if (level <= 30) {
            attacks[0] = new Attack.AttackBuilder("Magical Leaf", Type.Grass)
                    .power(60)
                    .build();
            attacks[1] = new Attack.AttackBuilder("Acid", Type.Poison)
                    .power(40)
                    .statusEffect(10, Stat.Defense, -1, false)
                    .build();
            attacks[2] = new Attack.AttackBuilder("Growth", Type.Grass)
                    .statusEffect(100, Stat.Defense, 1, true)
                    .build();
            attacks[3] = new Attack.AttackBuilder("Mud Shot", Type.Ground)
                    .power(55)
                    .accuracy(95)
                    .statusEffect(100, Stat.Speed, -1, false)
                    .build();
        } else {
            attacks[0] = new Attack.AttackBuilder("Giga Drain", Type.Grass)
                    .power(75)
                    .heal(20)
                    .build();
            attacks[1] = new Attack.AttackBuilder("Cross Poison", Type.Poison)
                    .power(70)
                    .critChance(50)
                    .statusEffect(10, Stat.Defense, -1, false)
                    .build();
            attacks[2] = new Attack.AttackBuilder("Growth", Type.Grass)
                    .statusEffect(100, Stat.Defense, 1, true)
                    .build();
            attacks[3] = new Attack.AttackBuilder("Earth Power", Type.Ground)
                    .power(90)
                    .statusEffect(30, Stat.Defense, -1, false)
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
