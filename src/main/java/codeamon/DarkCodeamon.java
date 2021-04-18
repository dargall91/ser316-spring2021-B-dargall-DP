package codeamon;

/**
 * Data structure for a Fire type species of Codeamon.
 */
public class DarkCodeamon extends Codeamon {
    private static final String NAME = "Darkmon";
    private static final Type TYPE = Type.Dark;
    private Attack[] attacks;
    private static int MAX_ATTACKS = 4;

    /**
     * Constructs a Fire Species of Codemon initializing its stats based on its level.
     *
     * @param level The level of the Codeamon
     */
    public DarkCodeamon(int level) {
        super(CodeamonStatsFactory.getStats(TYPE, level), level);
        //Codeamon have different attacks depending on their initial level
        attacks = new Attack[MAX_ATTACKS];
        if (level <= 15) {
            attacks[0] = new Attack.AttackBuilder("Pursuit", Type.Dark)
                    .power(40)
                    .build();
            attacks[1] = new Attack.AttackBuilder("Tackle", Type.Normal)
                    .power(40)
                    .build();
            attacks[2] = new Attack.AttackBuilder("Howl", Type.Normal)
                    .statusEffect(100, Stat.Attack, 1, true)
                    .build();
            attacks[3] = new Attack.AttackBuilder("Mud Shot", Type.Ground)
                    .power(55)
                    .accuracy(95)
                    .statusEffect(100, Stat.Speed, -1, false)
                    .build();
        } else if (level <= 30) {
            attacks[0] = new Attack.AttackBuilder("Snarl", Type.Dark)
                    .power(55)
                    .accuracy(95)
                    .statusEffect(100, Stat.Attack, -1, false)
                    .build();
            attacks[1] = new Attack.AttackBuilder("Cut", Type.Normal)
                    .power(50)
                    .accuracy(95)
                    .build();
            attacks[2] = new Attack.AttackBuilder("Howl", Type.Normal)
                    .statusEffect(100, Stat.Attack, 1, true)
                    .build();
            attacks[3] = new Attack.AttackBuilder("Mud Shot", Type.Ground)
                    .power(55)
                    .accuracy(95)
                    .statusEffect(100, Stat.Speed, -1, false)
                    .build();
        } else {
            attacks[0] = new Attack.AttackBuilder("Crunch", Type.Dark)
                    .power(70)
                    .statusEffect(20, Stat.Defense, -1, false)
                    .build();
            attacks[1] = new Attack.AttackBuilder("Play Rough", Type.Fairy)
                    .power(90)
                    .accuracy(90)
                    .build();
            attacks[2] = new Attack.AttackBuilder("Howl", Type.Normal)
                    .statusEffect(100, Stat.Attack, 1, true)
                    .build();
            attacks[3] = new Attack.AttackBuilder("Bulldoze", Type.Ground)
                    .power(60)
                    .statusEffect(100, Stat.Speed, -1, false)
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
