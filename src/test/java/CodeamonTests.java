import codeamon.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit Tests for Codeamon
 */
public class CodeamonTests {
    @AfterEach
    public void afterEach() throws Exception {
        System.out.println();
    }

    //Codeamon Tests
    /**
     * Test that a Codeamon's attributes are properly set
     *
     * @throws Exception
     */
    @Test
    public void TestCodeamonAttributes() throws Exception {
        System.out.println("TestCodeamonAttributes");

        Type type = Type.Grass;
        int level = 10;
        Codeamon mon = CodeamonFactory.createCodeamon(type, level);

        System.out.println("Name: " + mon.getName());
        System.out.println("Level: " + mon.getLevel());
        System.out.println("Type: " + mon.getType());

        assertAll(() -> assertEquals(mon.getSpeciesName(), mon.getName()),
                () -> assertEquals(level, mon.getLevel()),
                () -> assertEquals(type, mon.getType()));
    }

    /**
     * Test that a Codeamon created below level 1 is level 1
     *
     * @throws Exception
     */
    @Test
    public void TestInvalidLevelCodeamon() throws Exception {
        System.out.println("TestInvalidLevelCodeamon");

        Type type = Type.Normal;
        int level = 1;
        Codeamon mon = CodeamonFactory.createCodeamon(type, 0);

        System.out.println("Level: " + mon.getLevel());

        assertEquals(level, mon.getLevel());
    }

    /**
     * A test that ensures that a Codeamon's nickname is properly set
     *
     * @throws Exception
     */
    @Test
    public void TestCodeamonNickname() throws Exception {
        System.out.println("TestCodeamonNickname");

        Type type = Type.Water;
        int level = 10;
        String nickname = "Billy Bob Jones";

        Codeamon mon = CodeamonFactory.createCodeamon(type, level);
        mon.setNickname(nickname);

        System.out.println("Name: " + mon.getName());

        assertEquals(nickname, mon.getName());
    }
}
