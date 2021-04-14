import codeamon.Codeamon;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import trainer.Trainer;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit Tests for Trainer
 */
public class TrainerTests {
    @AfterEach
    public void afterEach() throws Exception {
        System.out.println();
    }

    /**
     * Test that the TrainerBuilder with only the constructor properly builds a Trainer.
     *
     * @throws Exception
     */
    @Test
    public void TestBaseTrainerBuilder() throws Exception {
        
    }

    /**
     * Test that the TrainerBuilder's additional methods work properly.
     *
     * @throws Exception
     */
    @Test
    public void TestTrainerBuilderMethods() throws Exception {

    }

    /**
     * Test that the TrainerBuilder does not add a null Codeamon to the Trainer's party.
     *
     * @throws Exception
     */
    @Test
    public void TestTrainerBuilderNullCodeamon() throws Exception {

    }

    /**
     * Test that the TrainerBuilder does not add a Codeamon to the Trainer's party when the party
     * is full.
     *
     * @throws Exception
     */
    @Test
    public void TestTrainerBuilderFullParty() throws Exception {

    }
}