import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

public class TypeMatchupTests {
    @AfterEach
    public void afterEach() throws Exception {
        System.out.println();
    }

    /**
     * Tests that an attack that is neither strong or weak against the target has a 1.0 damage
     * multiplier
     *
     * @throws Exception
     */
    @Test
    public void TestTypeNeutralTypeMatchup() throws Exception {
        System.out.println("TestTypeNeutralTypeMatchup");
    }

    /**
     * Tests that an attack that is strong against the target has a 2.0 damage multiplier
     *
     * @throws Exception
     */
    @Test
    public void TestTypeStrongTypeMatchup() throws Exception {
        System.out.println("TestTypeStrongTypeMatchup");
    }

    /**
     * Tests that an attack that is weak against the target has a 0.5 damage multiplier
     *
     * @throws Exception
     */
    @Test
    public void TestTypeWeakTypeMatchup() throws Exception {
        System.out.println("TestTypeWeakTypeMatchup");
    }
}
