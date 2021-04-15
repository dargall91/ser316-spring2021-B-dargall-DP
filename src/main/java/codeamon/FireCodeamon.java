package codeamon;

import java.util.ArrayList;

/**
 * Data structure for a Fire type species of Codeamon.
 */
public class FireCodeamon extends Codeamon {
    private static final String NAME = "Fire Name";
    private static final Type TYPE = Type.Fire;
    //private Attack[] attacks;
    private ArrayList<Attack> attacks;
    private int MAX_ATTACKS = 4;

    /**
     * Constructs a Fire Species of Codemon initializing its stats based on its level.
     *
     * @param level The level of the Codeamon
     */
    public FireCodeamon(int level/*, ArrayList<Attack> attacks*/) {
        super(CodeamonStatsFactory.getStats(Type.Fire, level), level);
        /*
        attacks = new Attack[4];
        attacks[0] = new Attack.AttackBuilder("Ember", Type.Fire).power(40)
                .statusEffect(10, Stat.Attack, 1, false).build();
        attacks[1] = new Attack.AttackBuilder("Scratch", Type.Normal).power(40).build();*/

        /*
        if (attacks.size() > MAX_ATTACKS) {
            System.out.println("Error: A Codeamon can have at most 4 Attacks. Some attacks will not be added");

        attacks = new ArrayList<>();
        for (int i = 0; i < MAX_ATTACKS; i++) {
            attacks.add(a);
        }
        */
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

    //TODO: Best way to get attacks? Should be defined in this class, but extended class needs access
    public ArrayList<Attack> getAttacks() {
        return attacks;
    }
}
