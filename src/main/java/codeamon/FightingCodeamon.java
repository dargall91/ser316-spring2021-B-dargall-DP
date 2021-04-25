package codeamon;

/**
 * Data structure for a Fire type species of Codeamon.
 */
public class FightingCodeamon extends Codeamon {
    private static final String NAME = "Karatemon";
    private static final Type TYPE = Type.Fighting;
    private Attack[] attacks;
    private static int MAX_ATTACKS = 4;

    /**
     * Constructs a Fire Species of Codemon initializing its stats based on its level.
     *
     * @param level The level of the Codeamon
     */
    public FightingCodeamon(int level) {
        super(CodeamonStatsFactory.getStats(TYPE, level), level);
        //Codeamon have different attacks depending on their initial level
        attacks = new Attack[MAX_ATTACKS];
        if (level <= 15) {
            attacks[0] = new Attack.AttackBuilder("Power-Up Punch", Type.Fighting)
                    .power(40)
                    .statusEffect(100, Stat.Attack, 1, true)
                    .build();
            attacks[1] = new Attack.AttackBuilder("Rock Throw", Type.Rock)
                    .power(50)
                    .accuracy(90)
                    .build();
            attacks[2] = new Attack.AttackBuilder("Agility", Type.Psychic)
                    .statusEffect(100, Stat.Speed, 2, true)
                    .build();
            attacks[3] = new Attack.AttackBuilder("Tackle", Type.Normal)
                    .power(40)
                    .build();
        } else if (level <= 30) {
            attacks[0] = new Attack.AttackBuilder("Power-Up Punch", Type.Fighting)
                    .power(40)
                    .statusEffect(100, Stat.Attack, 1, true)
                    .build();
            attacks[1] = new Attack.AttackBuilder("Rock Throw", Type.Normal)
                    .power(50)
                    .accuracy(90)
                    .build();
            attacks[2] = new Attack.AttackBuilder("Agility", Type.Psychic)
                    .statusEffect(100, Stat.Speed, 2, true)
                    .build();
            attacks[3] = new Attack.AttackBuilder("Slam", Type.Normal)
                    .power(80)
                    .accuracy(80)
                    .build();
        } else {
            attacks[0] = new Attack.AttackBuilder("Drain Punch", Type.Fighting)
                    .power(75)
                    .statusEffect(15, Stat.Attack, 1, false)
                    .heal(25)
                    .build();
            attacks[1] = new Attack.AttackBuilder("Stone Edge", Type.Rock)
                    .power(100)
                    .accuracy(80)
                    .critChance(50)
                    .build();
            attacks[2] = new Attack.AttackBuilder("Agility", Type.Psychic)
                    .statusEffect(100, Stat.Speed, 2, true)
                    .build();
            attacks[3] = new Attack.AttackBuilder("Mega Kick", Type.Normal)
                    .power(120)
                    .accuracy(75)
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
