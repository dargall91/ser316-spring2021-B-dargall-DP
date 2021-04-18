package codeamon;

/**
 * Data structure for a Fire type species of Codeamon.
 */
public class IceCodeamon extends Codeamon {
    private static final String NAME = "Chillmon";
    private static final Type TYPE = Type.Ice;
    private Attack[] attacks;
    private static int MAX_ATTACKS = 4;

    /**
     * Constructs a Fire Species of Codemon initializing its stats based on its level.
     *
     * @param level The level of the Codeamon
     */
    public IceCodeamon(int level) {
        super(CodeamonStatsFactory.getStats(TYPE, level), level);
        //Codeamon have different attacks depending on their initial level
        attacks = new Attack[MAX_ATTACKS];
        if (level <= 15) {
            attacks[0] = new Attack.AttackBuilder("Ice Shard", Type.Ice)
                    .power(40)
                    .statusEffect(100, Stat.Speed, 1, true)
                    .build();
            attacks[1] = new Attack.AttackBuilder("Scratch", Type.Normal)
                    .power(40)
                    .build();
            attacks[2] = new Attack.AttackBuilder("Howl", Type.Normal)
                    .statusEffect(100, Stat.Attack, 1, true)
                    .build();
            attacks[3] = new Attack.AttackBuilder("Water Gun", Type.Water)
                    .power(40)
                    .build();
        } else if (level <= 30) {
            attacks[0] = new Attack.AttackBuilder("Ice Shard", Type.Ice)
                    .power(40)
                    .statusEffect(100, Stat.Speed, 1, true)
                    .build();
            attacks[1] = new Attack.AttackBuilder("Cut", Type.Normal)
                    .power(50)
                    .accuracy(95)
                    .build();
            attacks[2] = new Attack.AttackBuilder("Howl", Type.Normal)
                    .statusEffect(100, Stat.Attack, 1, true)
                    .build();
            attacks[3] = new Attack.AttackBuilder("Brine", Type.Water)
                    .power(65)
                    .build();
        } else {
            attacks[0] = new Attack.AttackBuilder("Ice Punch", Type.Ice)
                    .power(75)
                    .statusEffect(100, Stat.Speed, 1, true)
                    .build();
            attacks[1] = new Attack.AttackBuilder("Slash", Type.Normal)
                    .power(70)
                    .critChance(50)
                    .build();
            attacks[2] = new Attack.AttackBuilder("Howl", Type.Normal)
                    .statusEffect(100, Stat.Attack, 1, true)
                    .build();
            attacks[3] = new Attack.AttackBuilder("Aqua Tail", Type.Water)
                    .power(90)
                    .accuracy(90)
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
