import codeamon.*;
import simulation.JsonSimulation;
import simulation.SampleSimulation;
import simulation.Simulation;
import trainer.Trainer;
import world.TimeCycleContext;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Main {
    /**
     * Entry point to the Codeamon simulation. A json file can be provided as a command line
     * argument to use the file's data in the simulation.
     *
     * @param args The json file, or null to use the default simulation data
     */
    public static void main(String[] args) {
        System.out.println("Gotta code 'em all!");

        Simulation sim;
        TimeCycleContext cycle;

        //If there are command line arguments, use them for the simulation.
        //Otherwise, use the Sample Simulation
        if (args.length > 0) {
            System.out.println("Loading " + args[0]);
            sim = new JsonSimulation(args[0]);

            //If there is an error parsing the json file, there will be no trainers. In this case
            //the sample simulation should be used. Also, if there are no trainers in the
            //simulation, the simulation will do nothing, so the sample should be used anyway
            if (sim.getTrainers().size() == 0) {
                System.out.println("Error loading " + args[0] + " Simulation or it contained no "
                        + "trainers. Switching to Sample Simulation.");
                sim = new SampleSimulation();
            }

            cycle = new TimeCycleContext(sim);
        } else {
            Scanner scan = new Scanner(System.in);
            int choice = -1;

            do {
                System.out.println("Choose a mode:");
                System.out.println("1. Play Codeamon");
                System.out.println("2. Run simulation");

                try {
                    choice = scan.nextInt();
                    scan.nextLine();

                    if (choice < 1 || choice > 2) {
                        System.out.println("Please select an option 1 or 2.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Please select an option 1 or 2.");
                    choice = -1;
                    scan.next();
                }
            } while (choice < 1 || choice > 2);

            if (choice == 2) {
                System.out.println("Loading Sample Simulation");
                sim = new SampleSimulation();
                cycle = new TimeCycleContext(sim);
            } else {
                System.out.println("Enter your name:");
                String name = scan.nextLine();

                Type[] types = Type.values();

                do {
                    for (int i = 0; i < types.length; i++) {
                        Codeamon mon = CodeamonFactory.createCodeamon(types[i], 1);
                        CodeamonStats stats = CodeamonStatsFactory.getStats(types[i], 1);
                        System.out.println((i + 1) + ". " + mon.getName());
                        System.out.println("Type: " + mon.getType());
                        System.out.println("Base Hit Points: " + stats.getBaseHitPoints());
                        System.out.println("Base Attack: " + stats.getBaseAttack());
                        System.out.println("Base Defense: " + stats.getBaseDefense());
                        System.out.println("Base Speed: " + stats.getBaseSpeed());
                        System.out.println();
                    }

                    System.out.println("Enter the number of your Starter Codeamon:");

                    try {
                        choice = scan.nextInt();

                        if (choice < 1 || choice > 18) {
                            System.out.println("Please select an option 1-18.");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Please select an option 1-18.");
                        choice = -1;
                    }
                } while (choice < 1 || choice > 18);

                int level = -1;

                do {
                    System.out.println("Enter your Codeamon's level (from 1-100):");

                    try {
                        level = scan.nextInt();

                        if (level < 1 || level > 100) {
                            System.out.println("Please select an option 1-100.");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Please select an option 1-100.");
                        level = -1;
                    }
                } while (level < 1 || level > 100);

                Codeamon starter = CodeamonFactory.createCodeamon(types[choice], level);
                Trainer player = new Trainer.TrainerBuilder(name).codeamon(starter).build();
                ArrayList<Trainer> trainers = new ArrayList<>();
                trainers.add(player);

                int wildBattles = -1;

                do {
                    System.out.println("Enter the number of Wild Codeamon to battle (from 0-10): ");

                    try {
                        wildBattles = scan.nextInt();

                        if (wildBattles < 0 || wildBattles > 10) {
                            System.out.println("Please select an option from 0-10.");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Please select an option from 0-10.");
                        wildBattles = -1;
                    }
                } while (wildBattles < 0 || wildBattles > 10);

                int numTrainers = 0;

                do {
                    System.out.println("Enter the number of Trainers (including you) who will "
                            + "compete in the tournament (2-8):");

                    try {
                        numTrainers = scan.nextInt();

                        if (numTrainers < 2 || numTrainers > 8) {
                            System.out.println("Please select an option from 1-8.");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Please select an option from 1-8.");
                        numTrainers = -1;
                    }
                } while (numTrainers < 2 || numTrainers > 8);

                Random rand = new Random();
                ArrayList<Trainer> npcs = new ArrayList<>();

                npcs.add(new Trainer.TrainerBuilder("Derek").codeamon(CodeamonFactory
                        .createCodeamon(Type.Grass, level)).build());
                npcs.add(new Trainer.TrainerBuilder("Ben").codeamon(CodeamonFactory
                        .createCodeamon(Type.Water, level)).build());
                npcs.add(new Trainer.TrainerBuilder("Chris").codeamon(CodeamonFactory
                        .createCodeamon(Type.Psychic, level)).build());
                npcs.add(new Trainer.TrainerBuilder("Burgundy").codeamon(CodeamonFactory
                        .createCodeamon(Type.Normal, level)).build());
                npcs.add(new Trainer.TrainerBuilder("Billy Bob Jones").codeamon(CodeamonFactory
                        .createCodeamon(Type.Dark, level)).build());
                npcs.add(new Trainer.TrainerBuilder("Joe-Anne Fabrics").codeamon(CodeamonFactory
                        .createCodeamon(Type.Bug, level)).build());
                npcs.add(new Trainer.TrainerBuilder("Warrick Oul").codeamon(CodeamonFactory
                        .createCodeamon(Type.Fairy, level)).build());
                npcs.add(new Trainer.TrainerBuilder("Grug Smith").codeamon(CodeamonFactory
                        .createCodeamon(Type.Ground, level)).build());

                for (int i = 0; i < numTrainers - 1; i++) {
                    int randomNpc = rand.nextInt(npcs.size());
                    trainers.add(npcs.get(randomNpc));
                    npcs.remove(npcs.get(randomNpc));
                }

                cycle = new TimeCycleContext(wildBattles, level - 2, trainers);
            }
        }

        while (!cycle.getTournament().isConcluded()) {
            cycle.runEvents();
        }

        System.out.println(cycle.getTournament().getWinner().getName() + " wins!");
    }
}
