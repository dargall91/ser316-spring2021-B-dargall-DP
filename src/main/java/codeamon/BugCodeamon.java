package codeamon;

/**
 * Data structure for a Fire type species of Codeamon.
 */
public class BugCodeamon extends Codeamon {
    private static final String NAME = "Antmon";
    private static final Type TYPE = Type.Bug;
    private Attack[] attacks;
    private static int MAX_ATTACKS = 4;

    /**
     * Constructs a Fire Species of Codemon initializing its stats based on its level.
     *
     * @param level The level of the Codeamon
     */
    public BugCodeamon(int level) {
        super(CodeamonStatsFactory.getStats(TYPE, level), level);
        //Codeamon have different attacks depending on their initial level
        attacks = new Attack[MAX_ATTACKS];
        if (level <= 15) {
            attacks[0] = new Attack.AttackBuilder("Pin Missile", Type.Bug)
                    .power(50)
                    .accuracy(95)
                    .build();
            attacks[1] = new Attack.AttackBuilder("Scratch", Type.Normal)
                    .power(40)
                    .build();
            attacks[2] = new Attack.AttackBuilder("String Shot", Type.Bug)
                    .accuracy(95)
                    .statusEffect(100, Stat.Speed, -2, false)
                    .build();
            attacks[3] = new Attack.AttackBuilder("Bite", Type.Dark)
                    .power(60)
                    .build();
        } else if (level <= 30) {
            attacks[0] = new Attack.AttackBuilder("Struggle Bug", Type.Bug)
                    .power(50)
                    .statusEffect(100, Stat.Attack, -1, false)
                    .build();
            attacks[1] = new Attack.AttackBuilder("Scratch", Type.Normal)
                    .power(40)
                    .build();
            attacks[2] = new Attack.AttackBuilder("String Shot", Type.Bug)
                    .accuracy(95)
                    .statusEffect(100, Stat.Speed, -2, false)
                    .build();
            attacks[3] = new Attack.AttackBuilder("Bite", Type.Dark)
                    .power(60)
                    .build();
        } else {
            attacks[0] = new Attack.AttackBuilder("Leech Life", Type.Bug)
                    .power(80)
                    .heal(30)
                    .build();
            attacks[1] = new Attack.AttackBuilder("Slash", Type.Normal)
                    .power(70)
                    .critChance(50)
                    .build();
            attacks[2] = new Attack.AttackBuilder("String Shot", Type.Bug)
                    .accuracy(95)
                    .statusEffect(100, Stat.Speed, -2, false)
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
