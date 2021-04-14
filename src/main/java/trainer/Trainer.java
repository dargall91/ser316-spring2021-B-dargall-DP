package trainer;

import codeamon.Codeamon;
import java.util.ArrayList;

/**
 * A Trainer is someone who captures and tames Codeamon. A trainer is created using a Builder
 * Design Pattern. A trainer can have a maximum of 6 Codeamon in their party. A trainer can
 * participate in battles against either other trainers or Wild Codeamon.
 */
public class Trainer {
    private String name;
    private int money;
    private ArrayList<Codeamon> party;
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
        private int money = 3000;

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
        public TrainerBuilder addCodeamon(Codeamon codeamon) {
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
         * @param money The starting amount of ¢ (CodeaDollars).
         * @return The TrainerBuilder
         */
        public TrainerBuilder money(int money) {
            this.money = money;
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
        party = builder.party;
        money = builder.money;
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
     * Gets the amount of money this trainer has as a String with the CodeaDollar symbol appended
     * to the front.
     *
     * @return The amount of CodeaDollars
     */
    public String getMoneyString() {
        return "¢" + money;
    }

    /**
     * Adds or subtracts the specified amount of money to this Trainer's total.
     *
     * @param amount The amount of money to add or subtract from this Trainer's total. If this
     *               is positive, it will be added to the total. If this is negative, the it will
     *               be subtracted from the total. If the amount to subtract is greater than the
     *               total money this trainer has, it will not be subtracted.
     * @return True if the amount was successfully added or subtracted from the Trainer's total.
     *         False if it was not.
     */
    public boolean adjustMoney(int amount) {
        if (amount > money) {
            System.out.println(getName() + " doesn't have ¢" + amount + " to spend!");
            return false;
        }

        money += amount;

        return true;
    }

    /**
     * Gets the number of Codeamon in this Trainer's party.
     *
     * @return The party size
     */
    public int getPartySize() {
        return party.size();
    }

    /**
     * Gets the number of Codeamon in this Trainer's party that have not fainted.
     *
     * @return The number of non-fainted Codeamon
     */
    public int getRemainingPartySize() {
        int remaining = 0;

        for (Codeamon i : party) {
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
        System.out.println(name + " paid " + money / 2 + " to " + winner.getName() + " !");
        winner.adjustMoney(money / 2);
        money /= 2;
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
        } else if (party.size() == MAX_PARTY) {
            System.out.println(codeamon.getName() + " not added. Party is full.");
            return false;
        }

        party.add(codeamon);
        return true;
    }

    /**
     * All the Codeamon in this trainer's party rest and fully recover any lost Hit Points.
     */
    public void restParty() {
        for (Codeamon i : party) {
            i.rest();
        }
    }
}
