package codeamon;

/**
 * Data structure for a Fire type species of Codeamon.
 */
public class DragonCodeamon extends Codeamon {
    private static final String NAME = "Dragomon";
    private static final Type TYPE = Type.Dragon;
    private Attack[] attacks;
    private static int MAX_ATTACKS = 4;

    /**
     * Constructs a Fire Species of Codemon initializing its stats based on its level.
     *
     * @param level The level of the Codeamon
     */
    public DragonCodeamon(int level) {
        super(CodeamonStatsFactory.getStats(TYPE, level), level);
        //Codeamon have different attacks depending on their initial level
        attacks = new Attack[MAX_ATTACKS];
        if (level <= 15) {
            attacks[0] = new Attack.AttackBuilder("Twister", Type.Dragon)
                    .power(40)
                    .build();
            attacks[1] = new Attack.AttackBuilder("Tackle", Type.Normal)
                    .power(40)
                    .build();
            attacks[2] = new Attack.AttackBuilder("Dragon Dance", Type.Normal)
                    .statusEffect(100, Stat.Speed, 1, true)
                    .build();
            attacks[3] = new Attack.AttackBuilder("Mud Shot", Type.Ground)
                    .power(55)
                    .accuracy(95)
                    .statusEffect(100, Stat.Speed, -1, false)
                    .build();
        } else if (level <= 30) {
            attacks[0] = new Attack.AttackBuilder("Dragon Tail", Type.Dragon)
                    .power(60)
                    .build();
            attacks[1] = new Attack.AttackBuilder("Stomp", Type.Normal)
                    .power(65)
                    .build();
            attacks[2] = new Attack.AttackBuilder("Dragon Dance", Type.Normal)
                    .statusEffect(100, Stat.Speed, 1, true)
                    .build();
            attacks[3] = new Attack.AttackBuilder("Mud Shot", Type.Ground)
                    .power(55)
                    .accuracy(95)
                    .statusEffect(100, Stat.Speed, -1, false)
                    .build();
        } else {
            attacks[0] = new Attack.AttackBuilder("Dragon Rage", Type.Dragon)
                    .power(80)
                    .statusEffect(100, Stat.Attack, 1, true)
                    .build();
            attacks[1] = new Attack.AttackBuilder("Iron Tail", Type.Steel)
                    .power(100)
                    .accuracy(75)
                    .statusEffect(30, Stat.Defense, -1, false)
                    .build();
            attacks[2] = new Attack.AttackBuilder("Dragon Dance", Type.Normal)
                    .statusEffect(100, Stat.Speed, 1, true)
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
