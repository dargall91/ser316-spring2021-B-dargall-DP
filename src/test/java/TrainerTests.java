import codeamon.Codeamon;
import codeamon.CodeamonFactory;
import codeamon.Type;
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
        System.out.println("TestBaseTrainerBuilder");

        Trainer trainer = new Trainer.TrainerBuilder("Derek").build();

        System.out.println("Name: " + trainer.getName());
        System.out.println("CodeaDollars: " + trainer.getCodeaDollarsAsString());
        System.out.println("Party Size: " + trainer.getPartySize());

        assertAll(() -> assertEquals("Derek", trainer.getName()),
                () -> assertEquals(3000, trainer.getCodeaDollars()),
                () -> assertEquals(0, trainer.getPartySize()));
    }

    /**
     * Test that the TrainerBuilder's additional methods work properly.
     *
     * @throws Exception
     */
    @Test
    public void TestTrainerBuilderMethods() throws Exception {
        System.out.println("TestTrainerBuilderMethods");

        Codeamon mon = CodeamonFactory.getCodeamon(Type.Fire, 1);
        Trainer trainer = new Trainer.TrainerBuilder("Derek").codeamon(mon).codeaDollars(500).build();

        System.out.println("CodeaDollars: " + trainer.getCodeaDollarsAsString());
        System.out.println("Party Size: " + trainer.getPartySize());

        assertAll(() -> assertEquals(500, trainer.getCodeaDollars()),
                () -> assertEquals(1, trainer.getPartySize()));
    }

    /**
     * Test that the TrainerBuilder does not add a null Codeamon to the Trainer's party.
     *
     * @throws Exception
     */
    @Test
    public void TestTrainerBuilderNullCodeamon() throws Exception {
        System.out.println("TestTrainerBuilderNullCodeamon");

        Trainer trainer = new Trainer.TrainerBuilder("Derek").codeamon(null).build();

        System.out.println("Party Size: " + trainer.getPartySize());

        assertEquals(0, trainer.getPartySize());
    }

    /**
     * Test that the TrainerBuilder does not add a Codeamon to the Trainer's party when the party
     * is full.
     *
     * @throws Exception
     */
    @Test
    public void TestTrainerBuilderFullParty() throws Exception {
        System.out.println("TestTrainerBuilderFullParty");

        Codeamon mon = CodeamonFactory.getCodeamon(Type.Fire, 1);
        Trainer trainer = new Trainer.TrainerBuilder("Derek").codeamon(mon).codeamon(mon)
                .codeamon(mon).codeamon(mon).codeamon(mon).codeamon(mon).codeamon(mon).build();

        System.out.println("Party Size: " + trainer.getPartySize());

        assertEquals(6, trainer.getPartySize());
    }

    /**
     * Test that money is properly adjusted.
     *
     * @throws Exception
     */
    @Test
    public void TestAdjustCodeaDollars() throws Exception {
        System.out.println("TestAdjustCodeaDollars");

        Trainer addMoney = new Trainer.TrainerBuilder("Add").build();
        Trainer subMoney = new Trainer.TrainerBuilder("Subtract").build();
        Trainer insufficient = new Trainer.TrainerBuilder("Insufficient Funds").build();

        System.out.println("Trainer: " + addMoney.getName());
        System.out.println("CodeaDollars Before: " + addMoney.getCodeaDollarsAsString());

        System.out.println("\nTrainer: " + subMoney.getName());
        System.out.println("CodeaDollars Before: " + subMoney.getCodeaDollarsAsString());

        System.out.println("\nTrainer: " + insufficient.getName());
        System.out.println("CodeaDollars Before: " + insufficient.getCodeaDollarsAsString());


        assertAll(() -> assertTrue(addMoney.adjustCodeaDollars(1000)),
                () -> assertEquals(4000, addMoney.getCodeaDollars()),
                () -> assertTrue(subMoney.adjustCodeaDollars(-1000)),
                () -> assertEquals(2000, subMoney.getCodeaDollars()),
                () -> assertFalse(insufficient.adjustCodeaDollars(3001)),
                () -> assertEquals(3000, insufficient.getCodeaDollars()));

        System.out.println("\nTrainer: " + addMoney.getName());
        System.out.println("CodeaDollars After: " + addMoney.getCodeaDollarsAsString());

        System.out.println("\nTrainer: " + subMoney.getName());
        System.out.println("CodeaDollars After: " + subMoney.getCodeaDollarsAsString());

        System.out.println("\nTrainer: " + insufficient.getName());
        System.out.println("CodeaDollars After: " + insufficient.getCodeaDollarsAsString());
    }

    /**
     * Test that a Trainer pays out the proper amount of money.
     *
     * @throws Exception
     */
    @Test
    public void TestPayout() throws Exception {
        System.out.println("TestPayout");

        Trainer winner = new Trainer.TrainerBuilder("Winner").build();
        Trainer loser = new Trainer.TrainerBuilder("Loser").build();

        System.out.println("Trainer: " + winner.getName());
        System.out.println("Before Payout: " + winner.getCodeaDollarsAsString());

        System.out.println("\nTrainer: " + loser.getName());
        System.out.println("Before Payout: " + winner.getCodeaDollarsAsString());

        loser.payout(winner);

        assertAll(() -> assertEquals(4500, winner.getCodeaDollars()),
                () -> assertEquals(1500, loser.getCodeaDollars()));

        System.out.println("\nTrainer: " + winner.getName());
        System.out.println("After Payout: " + winner.getCodeaDollarsAsString());

        System.out.println("\nTrainer: " + loser.getName());
        System.out.println("After Payout: " + winner.getCodeaDollarsAsString());
    }

    /**
     * Test that a Trainer's non-fainted Codeamon are properly counted.
     *
     * @throws Exception
     */
    @Test
    public void TestReamingPartySize() throws Exception {
        System.out.println("TestReamingPartySize");

        Codeamon living = CodeamonFactory.getCodeamon(Type.Fire, 1);
        Codeamon fainted = CodeamonFactory.getCodeamon(Type.Fire, 1);
        fainted.damage(1000);

        Trainer all = new Trainer.TrainerBuilder("6/6 Remaining").codeamon(living)
                .codeamon(living).codeamon(living).codeamon(living).codeamon(living)
                .codeamon(living).build();
        Trainer oneOfSix = new Trainer.TrainerBuilder("1/6 Remaining").codeamon(living)
                .codeamon(fainted).codeamon(fainted).codeamon(fainted).codeamon(fainted)
                .codeamon(fainted).build();

        System.out.println("Trainer: " + all.getName());
        System.out.println("Remaining Codeamon: " + all.getRemainingPartySize() + "/"
                + all.getPartySize());

        System.out.println("\nTrainer: " + oneOfSix.getName());
        System.out.println("Remaining Codeamon: " + oneOfSix.getRemainingPartySize() + "/"
                + oneOfSix.getPartySize());

        assertAll(() -> assertEquals(all.getPartySize(), all.getRemainingPartySize()),
                () -> assertEquals(1, oneOfSix.getRemainingPartySize()));
    }

    /**
     * Test that after resting all Codeamon in a Trainer's party are not fainted.
     *
     * @throws Exception
     */
    @Test
    public void TestRestParty() throws Exception {
        System.out.println("TestRestParty");

        Codeamon mon1 = CodeamonFactory.getCodeamon(Type.Fire, 1);
        Codeamon mon2 = CodeamonFactory.getCodeamon(Type.Fire, 1);
        mon1.damage(1000);

        Trainer trainer = new Trainer.TrainerBuilder("Derek").codeamon(mon1)
                .codeamon(mon2).build();

        System.out.println("Remaining Before Rest: " + trainer.getRemainingPartySize() + "/"
                + trainer.getPartySize());

        trainer.restParty();

        assertEquals(trainer.getPartySize(), trainer.getRemainingPartySize());

        System.out.println("Remaining After Rest: " + trainer.getRemainingPartySize() + "/"
                + trainer.getPartySize());
    }

    /**
     * Test that a Codeamon is properly added to a Trainer's party outside of the Builder method.
     *
     * @throws Exception
     */
    @Test
    public void TestAddCodeamon() throws Exception {
        System.out.println("TestAddCodeamon");

        Codeamon mon = CodeamonFactory.getCodeamon(Type.Fire, 1);
        Trainer trainer = new Trainer.TrainerBuilder("Derek").build();

        System.out.println("Party Size Before: " + trainer.getPartySize());

        assertAll(() -> assertTrue(trainer.addCodeamon(mon)),
                () -> assertEquals(1, trainer.getPartySize()));

        System.out.println("Party Size After: " + trainer.getPartySize());
    }

    /**
     * Test that a null Codeamon is not added to a Trainer's party outside of the Builder method.
     *
     * @throws Exception
     */
    @Test
    public void TestAddNullCodeamon() throws Exception {
        System.out.println("TestAddNullCodeamon");

        Trainer trainer = new Trainer.TrainerBuilder("Derek").build();

        System.out.println("Party Size Before: " + trainer.getPartySize());

        assertAll(() -> assertFalse(trainer.addCodeamon(null)),
                () -> assertEquals(0, trainer.getPartySize()));

        System.out.println("Party Size After: " + trainer.getPartySize());
    }

    /**
     * Test that a null Codeamon is not added to a Trainer's party outside of the Builder method.
     *
     * @throws Exception
     */
    @Test
    public void TestAddCodeamonPartyFull() throws Exception {
        System.out.println("TestAddNullCodeamon");

        Codeamon mon = CodeamonFactory.getCodeamon(Type.Fire, 1);
        Trainer trainer = new Trainer.TrainerBuilder("Derek").build();

        for (int i = 0; i < 6; i++) {
            trainer.addCodeamon(mon);
        }

        System.out.println("Party Size Before: " + trainer.getPartySize());

        assertAll(() -> assertFalse(trainer.addCodeamon(mon)),
                () -> assertEquals(6, trainer.getPartySize()));

        System.out.println("Party Size After: " + trainer.getPartySize());
    }

    //TODO: test getNextCodeamon

}