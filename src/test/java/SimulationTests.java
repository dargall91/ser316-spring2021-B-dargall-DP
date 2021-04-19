import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import simulation.JsonSimulation;
import simulation.SampleSimulation;
import simulation.Simulation;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SimulationTests {
    @AfterEach
    public void afterEach() throws Exception {
        System.out.println();
    }

    /**
     * Test that the SampleSimulation is properly creating data.
     *
     * @throws Exception
     */
    @Test
    public void TestSampleSimulation() throws Exception {
        System.out.println("TestSampleSimulation");
        Simulation sim = new SampleSimulation();

        System.out.println("Trainers " + sim.getTrainers().size());
        System.out.println("Trainers " + sim.getWildBattles());
        System.out.println("Trainers " + sim.getWildLevel());

        assertAll(() -> assertEquals(4, sim.getTrainers().size()),
                () -> assertEquals(10, sim.getWildBattles()),
                () -> assertEquals(16, sim.getWildLevel()));
    }

    /**
     * Test that the JsonSimulation is properly reading the data from simulation_one.json
     *
     * @throws Exception
     */
    @Test
    public void TestJsonSimulationOne() throws Exception {
        System.out.println("TestJsonSimulationOne");
        Simulation sim = new JsonSimulation("simulation_one.json");

        System.out.println("Trainers " + sim.getTrainers().size());
        System.out.println("Trainers " + sim.getWildBattles());
        System.out.println("Trainers " + sim.getWildLevel());

        assertAll(() -> assertEquals(4, sim.getTrainers().size()),
                () -> assertEquals(5, sim.getWildBattles()),
                () -> assertEquals(10, sim.getWildLevel()));
    }

    /**
     * Test that the JsonSimulation is properly reading the data from simulation_two.json
     *
     * @throws Exception
     */
    @Test
    public void TestJsonSimulationTwo() throws Exception {
        System.out.println("TestJsonSimulationTwo");
        Simulation sim = new JsonSimulation("simulation_two.json");

        System.out.println("Trainers " + sim.getTrainers().size());
        System.out.println("Trainers " + sim.getWildBattles());
        System.out.println("Trainers " + sim.getWildLevel());

        assertAll(() -> assertEquals(8, sim.getTrainers().size()),
                () -> assertEquals(0, sim.getWildBattles()),
                () -> assertEquals(0, sim.getWildLevel()));
    }
}
