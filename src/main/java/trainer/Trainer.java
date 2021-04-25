package trainer;

import codeamon.Codeamon;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;

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
    private boolean playable;
    private int codeaDollars;
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
        private int codeaDollars = 3000;
        private boolean playable = false;

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
         * Flags this trainer as a playable character.
         *
         * @return The TrainerBuilder
         */
        public TrainerBuilder playable() {
            playable = true;

            return this;
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
        party = builder.party;
        codeaDollars = builder.codeaDollars;
        playable = builder.playable;
    }

    /**
     * Checks if this Trainer is playable or not.
     *
     * @return True if they are playable, otherwise false
     */
    public boolean isPlayable() {
        return playable;
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
        } else if (party.size() == MAX_PARTY) {
            System.out.println(codeamon.getName() + " not added. Party is full.");
            return false;
        }

        if (playable) {
            Scanner scan = new Scanner(System.in);
            int choice;

            System.out.println("Give a nickname to " + codeamon.getName() + "?");
            System.out.println("1. Yes");
            System.out.println("2. No");

            do {
                try {
                    choice = scan.nextInt();
                    scan.nextLine();

                    if (choice < 1 || choice > 2) {
                        System.out.println("Please enter 1 for yes or 2 for no.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Please enter 1 for yes or 2 for no.");
                    choice = -1;
                    scan.next();
                }
            } while (choice < 1 || choice > 2);

            if (choice == 1) {
                System.out.print("Enter your " + codeamon.getName() + "'s nickname: ");
                String nickname = scan.nextLine();
                codeamon.setNickname(nickname);
            }
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

        if (playable) {
            Scanner scan = new Scanner(System.in);
            int choice;

            do {
                System.out.println("Your lead Codeamon is currently " + party.get(0).getName() + ".");
                System.out.println("Would you like to select another Codeamon to lead?");
                System.out.println("1. Yes");
                System.out.println("2. No");

                try {
                    choice = scan.nextInt();
                    scan.nextLine();

                    if (choice < 1 || choice > 2) {
                        System.out.println("Please enter 1 for yes or 2 for no.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Please enter 1 for yes or 2 for no.");
                    choice = -1;
                    scan.next();
                }
            } while (choice < 1 || choice > 2);

            if (choice == 1) {
                Collections.swap(party, 0, party.indexOf(chooseCodeamon()));
            }
        } else {
            Collections.sort(party);
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

        if (playable && getRemainingPartySize() != party.size()) {
            return chooseCodeamon();
        }

        return party.get(party.size() - getRemainingPartySize());
    }

    public Codeamon chooseCodeamon() {
        for (int i = 0; i < party.size(); i++) {
            System.out.print((i + 1) + ". " + party.get(i).getName() + " Lv: "
                    + party.get(i).getLevel() + " Type: " + party.get(i).getType());
            if (party.get(i).isFainted()) {
                System.out.print(" (FAINTED)");
            }

            System.out.println();
        }

        int choice;
        Scanner scan = new Scanner(System.in);

        do {
            System.out.print("Choose a Codeamon: ");

            try {
                choice = scan.nextInt();
                scan.nextLine();

                if (choice < 1 || choice > party.size()) {
                    System.out.println("Please select one of the above valid numbers.");
                } else if (party.get(choice - 1).isFainted()) {
                    System.out.println("The Codeamon you selected has fainted."
                            + " Please select another one.");
                    choice = -1;
                }
            } catch (InputMismatchException e) {
                System.out.println("Please select a one of the above valid numbers.");
                choice = -1;
                scan.next();
            }
        } while (choice < 1 || choice > party.size());

        return party.get(choice - 1);
    }

    /**
     * Gets this Trainer's Codeamon party.
     *
     * @return The Codeamon party
     */
    public ArrayList<Codeamon> getParty() {
        return party;
    }

    /**
     * Prints the Trainer's name and a representation of how many Codeamon they have remaining. "O"
     * represents a non-fainted Codeamon and "X" represents a fainted Codeamon. For example, a
     * Trainer "Derek" with two fainted and three non-fainted Codeamon would be displayed as
     * "Derek: XXOOO".
     */
    public void printPartyStatus() {
        System.out.print(getName() + ": ");

        for (Codeamon c : party) {
            if (c.isFainted()) {
                System.out.print("X");
            } else {
                System.out.print("O");
            }
        }

        System.out.println();
    }
}
