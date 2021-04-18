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

        assertAll(() -> assertEquals(4, sim.getTrainers().size()),
                () -> assertEquals(10, sim.getWildBattles()),
                () -> assertEquals(16, sim.getWildLevel()));
    }

    /**
     * Test that the JsonSimulation is properly reading the data from the json file.
     *
     * @throws Exception
     */
    @Test
    public void TestJsonSimulation() throws Exception {
        System.out.println("TestJsonSimulation");
        Simulation sim = new JsonSimulation("simulation_one.json");

        assertAll(() -> assertEquals(4, sim.getTrainers().size()),
                () -> assertEquals(5, sim.getWildBattles()),
                () -> assertEquals(10, sim.getWildLevel()));
    }
}
