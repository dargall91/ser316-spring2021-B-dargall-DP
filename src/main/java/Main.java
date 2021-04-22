import simulation.JsonSimulation;
import simulation.SampleSimulation;
import simulation.Simulation;
import world.TimeCycleContext;

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
            }
        } else {
            System.out.println("Loading Sample Simulation");
            sim = new SampleSimulation();
        }

        TimeCycleContext cycle = new TimeCycleContext(sim);

        while (!cycle.getTournament().isConcluded()) {
            cycle.runEvents();
        }

        System.out.println(cycle.getTournament().getWinner().getName() + " wins!");


    }
}
