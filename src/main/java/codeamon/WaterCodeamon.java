package codeamon;

/**
 * Data structure for a Fire type species of Codeamon.
 */
public class WaterCodeamon extends Codeamon {
    private static final String NAME = "Bubblemon";
    private static final Type TYPE = Type.Water;
    private Attack[] attacks;
    private static int MAX_ATTACKS = 4;

    /**
     * Constructs a Fire Species of Codemon initializing its stats based on its level.
     *
     * @param level The level of the Codeamon
     */
    public WaterCodeamon(int level) {
        super(CodeamonStatsFactory.getStats(TYPE, level), level);
        //Codeamon have different attacks depending on their initial level
        attacks = new Attack[MAX_ATTACKS];
        if (level <= 15) {
            attacks[0] = new Attack.AttackBuilder("Bubble", Type.Water)
                    .power(40)
                    .statusEffect(10, Stat.Speed, -1, false)
                    .build();
            attacks[1] = new Attack.AttackBuilder("Tackle", Type.Normal)
                    .power(40)
                    .build();
            attacks[2] = new Attack.AttackBuilder("Leer", Type.Normal)
                    .statusEffect(100, Stat.Defense, -1, false)
                    .build();
            attacks[3] = new Attack.AttackBuilder("Bite", Type.Dark)
                    .power(60)
                    .build();
        } else if (level <= 30) {
            attacks[0] = new Attack.AttackBuilder("Water Pulse", Type.Water)
                    .power(60)
                    .statusEffect(20, Stat.Attack, 1, false)
                    .build();
            attacks[1] = new Attack.AttackBuilder("Rapid Spin", Type.Normal)
                    .power(50)
                    .statusEffect(100, Stat.Speed, 1, true)
                    .build();
            attacks[2] = new Attack.AttackBuilder("Leer", Type.Normal)
                    .statusEffect(100, Stat.Defense, -1, false)
                    .build();
            attacks[3] = new Attack.AttackBuilder("Bite", Type.Dark)
                    .power(60)
                    .build();
        } else {
            attacks[0] = new Attack.AttackBuilder("Hydro Pump", Type.Water)
                    .power(110)
                    .accuracy(70)
                    .build();
            attacks[1] = new Attack.AttackBuilder("Rapid Spin", Type.Normal)
                    .power(50)
                    .statusEffect(100, Stat.Speed, 1, true)
                    .build();
            attacks[2] = new Attack.AttackBuilder("Leer", Type.Normal)
                    .statusEffect(100, Stat.Defense, -1, false)
                    .build();
            attacks[3] = new Attack.AttackBuilder("Crunch", Type.Dark)
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
