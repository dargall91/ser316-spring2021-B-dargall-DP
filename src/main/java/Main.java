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

        //If there are no command line arguments, use the sample data. Otherwise, read from the JsonFile
        if (args.length > 0) {
            System.out.println("Loading " + args[0] + "Simulation.");
            sim = new JsonSimulation(args[0]);

            //If there is an error parsing the json file, there will be no trainers. In this case
            //the sample simulation should be used. Also, if there are no trainers in the
            //simulation, the simulation will do nothing, so the sample should be used anyway
            if (sim.getTrainers().size() == 0) {
                System.out.println("Error loading " + args[0] + "Simulation o rit contained no "
                        + "trainers. Switching to Sample Simulation.");
            }
        } else {
            System.out.println("Loading Sample Simulation");
            sim = new SampleSimulation();
        }

        TimeCycleContext cycle = new TimeCycleContext(sim.getWildBattles(),
                sim.getWildLevel(), sim.getTrainers());

        while (!cycle.getTournament().isConcluded()) {
            cycle.runEvents(sim.getTrainers());
        }

        System.out.println(cycle.getTournament().getWinner().getName() + " wins!");


    }
}
