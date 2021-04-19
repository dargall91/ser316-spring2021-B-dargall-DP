package trainer;

import codeamon.Codeamon;
import java.util.ArrayList;
import java.util.Collections;

/**
 * A Trainer is someone who captures and tames Codeamon. A trainer is created using a Builder
 * Design Pattern. A trainer can have a maximum of 6 Codeamon in their party. A trainer can
 * participate in battles against either other trainers or Wild Codeamon.
 *
 * <p>
 *     The following requirements are fulfilled by this Design Pattern and any related classes:
 *     <li>A Trainer can recruit new Codeamon to their party</li>
 *     <li>A Trainer can have no more than 6 Codeamon</li>
 * </p>
 */
public class Trainer {
    private final String name;
    private int codeaDollars;
    private ArrayList<Codeamon> codeamonParty;
    private static final int MAX_PARTY = 6;

    /**
     * A Builder Method for constructing at attack. Contains methods for adding Codeamon to the
     * Trainer's party and determining how much money the Trainer has. At a minimum, a Trainer
     * must have a name. If no other values are set, the Trainer will begin with ¢3000
     * (CodeaDollars) and an empty party of Codeamon. A Trainer with no Codeamon in their party
     * cannot participate in battles.
     */
    public static class TrainerBuilder {
        private final String name;
        private ArrayList<Codeamon> party;

        //Default values
        private int codeaDollars = 3000;

        /**
         * Entry point for a TrainerBuilder that sets the Trainer's name.
         *
         * @param name The Trainer's name
         */
        public TrainerBuilder(String name) {
            this.name = name;
            party = new ArrayList<>();
        }

        /**
         * Adds a Codeamon to this Trainer's party. The Codeamon will not be added if the Trainer
         * already has 6 Codeamon in their party or if the Codeamon is null.
         *
         * @param codeamon The Codeamon to add to the party
         * @return The TrainerBuilder
         */
        public TrainerBuilder codeamon(Codeamon codeamon) {
            if (codeamon == null) {
                System.out.println("Cannot add a null Codeamon to a Trainer's party.");
                return this;
            } else if (party.size() == MAX_PARTY) {
                System.out.println(codeamon.getName() + " not added. Party is full.");
                return this;
            }

            party.add(codeamon);
            return this;
        }

        /**
         * Sets the Trainer's starting amount of ¢ (CodeaDollars).
         *
         * @param codeaDollars The starting amount of ¢ (CodeaDollars).
         * @return The TrainerBuilder
         */
        public TrainerBuilder codeaDollars(int codeaDollars) {
            this.codeaDollars = codeaDollars;
            return this;
        }

        /**
         * Gets the Trainer built by the TrainerBuilder.
         *
         * @return The Trainer
         */
        public Trainer build() {
            return new Trainer(this);
        }
    }

    private Trainer(TrainerBuilder builder) {
        name = builder.name;
        codeamonParty = builder.party;
        codeaDollars = builder.codeaDollars;
        Collections.sort(codeamonParty);
    }

    /**
     * Gets the name of this Trainer.
     *
     * @return The name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the amount of CodeaDollars this trainer has.
     *
     * @return The amount of CodeaDollars
     */
    public int getCodeaDollars() {
        return codeaDollars;
    }

    /**
     * Gets the amount of money this trainer has as a String with the CodeaDollar symbol appended
     * to the front.
     *
     * @return The amount of CodeaDollars
     */
    public String getCodeaDollarsAsString() {
        return "¢" + codeaDollars;
    }

    /**
     * Adds or subtracts the specified amount of money to this Trainer's total.
     *
     * @param amount The amount of money to add or subtract from this Trainer's total. If this
     *               is positive, it will be added to the total. If this is negative, the it will
     *               be subtracted from the total. If the amount to subtract is greater than the
     *               total money this trainer has, it will not be subtracted.
     * @return False if the Trainer doesn't have enough money to be subctracted, otherwise true
     */
    public boolean adjustCodeaDollars(int amount) {
        if (amount > codeaDollars) {
            System.out.println(getName() + " doesn't have ¢" + amount + " to spend!");
            return false;
        }

        codeaDollars += amount;

        return true;
    }

    /**
     * Gets the number of Codeamon in this Trainer's party.
     *
     * @return The party size
     */
    public int getPartySize() {
        return codeamonParty.size();
    }

    /**
     * Gets the number of Codeamon in this Trainer's party that have not fainted.
     *
     * @return The number of non-fainted Codeamon
     */
    public int getRemainingPartySize() {
        int remaining = 0;

        for (Codeamon i : codeamonParty) {
            if (!i.isFainted()) {
                remaining++;
            }
        }

        return remaining;
    }

    /**
     * Pays the winning Trainer of a Codeamon battle half of this Trainer's money.
     *
     * @param winner The winning Trainer whom to payout
     */
    public void payout(Trainer winner) {
        System.out.println(name + " paid ¢" + codeaDollars / 2 + " to " + winner.getName() + ".");
        winner.adjustCodeaDollars(codeaDollars / 2);
        codeaDollars /= 2;
    }

    /**
     * Adds a new Codeamon to this Trainer's party. The Codeamon will not be added if the Trainer
     * already has 6 Codeamon in their party or if the Codeamon is null.
     *
     * @param codeamon The Codeamon to add to the Trainer's party
     * @return True if the Codeamon was successfully added, false if it was not.
     */
    public boolean addCodeamon(Codeamon codeamon) {
        if (codeamon == null) {
            System.out.println("Cannot add a null Codeamon to a Trainer's party.");
            return false;
        } else if (codeamonParty.size() == MAX_PARTY) {
            System.out.println(codeamon.getName() + " not added. Party is full.");
            return false;
        }

        codeamonParty.add(codeamon);
        Collections.sort(codeamonParty);

        return true;
    }

    /**
     * All the Codeamon in this trainer's party rest and fully recover any lost Hit Points.
     */
    public void restParty() {
        for (Codeamon i : codeamonParty) {
            i.rest();
        }
    }

    /**
     * Selects the next Codeamon this trainer will send into battle. A Trainer will always
     * selected their lowest level Codeamon first.
     *
     * @return The selected Codeamon. If the trainer has no remaining Codeamon it will return null
     */
    public Codeamon getNextCodeamon() {
        if (getRemainingPartySize() == 0) {
            return null;
        }

        return codeamonParty.get(codeamonParty.size() - getRemainingPartySize());
    }

    /**
     * Gets this Trainer's Codeamon party.
     *
     * @return The Codeamon party
     */
    public ArrayList<Codeamon> getCodeamonParty() {
        return codeamonParty;
    }

    /**
     * Prints the Trainer's name and a representation of how many Codeamon they have remaining. "O"
     * represents a non-fainted Codeamon and "X" represents a fainted Codeamon. For example, a
     * Trainer "Derek" with two fainted and three non-fainted Codeamon would be displayed as
     * "Derek: XXOOO".
     */
    public void printPartyStatus() {
        System.out.print(getName() + ": ");

        for (Codeamon c : codeamonParty) {
            if (c.isFainted()) {
                System.out.print("X");
            } else {
                System.out.print("O");
            }
        }

        System.out.println();
    }
}
