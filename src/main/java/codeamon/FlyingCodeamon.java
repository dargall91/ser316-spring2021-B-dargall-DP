package codeamon;

/**
 * Data structure for a Fire type species of Codeamon.
 */
public class FlyingCodeamon extends Codeamon {
    private static final String NAME = "Birdmon";
    private static final Type TYPE = Type.Ice;
    private Attack[] attacks;
    private static int MAX_ATTACKS = 4;

    /**
     * Constructs a Fire Species of Codemon initializing its stats based on its level.
     *
     * @param level The level of the Codeamon
     */
    public FlyingCodeamon(int level) {
        super(CodeamonStatsFactory.getStats(TYPE, level), level);
        //Codeamon have different attacks depending on their initial level
        attacks = new Attack[MAX_ATTACKS];
        if (level <= 15) {
            attacks[0] = new Attack.AttackBuilder("Peck", Type.Flying)
                    .power(40)
                    .build();
            attacks[1] = new Attack.AttackBuilder("Quick Attack", Type.Normal)
                    .power(40)
                    .statusEffect(100, Stat.Speed, 1, true)
                    .build();
            attacks[2] = new Attack.AttackBuilder("Feather Dance", Type.Normal)
                    .statusEffect(100, Stat.Attack, -2, false)
                    .build();
            attacks[3] = new Attack.AttackBuilder("Storm Throw", Type.Fighting)
                    .power(60)
                    .critChance(100)
                    .build();
        } else if (level <= 30) {
            attacks[0] = new Attack.AttackBuilder("Wing Attack", Type.Flying)
                    .power(60)
                    .build();
            attacks[1] = new Attack.AttackBuilder("Quick Attack", Type.Normal)
                    .power(40)
                    .statusEffect(100, Stat.Speed, 1, true)
                    .build();
            attacks[2] = new Attack.AttackBuilder("Feather Dance", Type.Normal)
                    .statusEffect(100, Stat.Attack, -2, false)
                    .build();
            attacks[3] = new Attack.AttackBuilder("Storm Throw", Type.Fighting)
                    .power(60)
                    .critChance(100)
                    .build();
        } else {
            attacks[0] = new Attack.AttackBuilder("Oblivion Wing", Type.Flying)
                    .power(80)
                    .heal(25)
                    .build();
            attacks[1] = new Attack.AttackBuilder("Extreme Speed", Type.Normal)
                    .power(80)
                    .statusEffect(100, Stat.Speed, 1, true)
                    .build();
            attacks[2] = new Attack.AttackBuilder("Feather Dance", Type.Normal)
                    .statusEffect(100, Stat.Attack, -2, false)
                    .build();
            attacks[3] = new Attack.AttackBuilder("Storm Throw", Type.Fighting)
                    .power(60)
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
