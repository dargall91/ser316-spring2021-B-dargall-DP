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

        Simulation sample = new SampleSimulation();
        TimeCycleContext cycle = new TimeCycleContext(sample.getWildBattles(),
                sample.getWildLevel(), sample.getTrainers());

        while (!cycle.getTournament().isConcluded()) {
            cycle.runEvents(sample.getTrainers());
        }

        System.out.println(cycle.getTournament().getWinner().getName() + " wins!");
    }
}
