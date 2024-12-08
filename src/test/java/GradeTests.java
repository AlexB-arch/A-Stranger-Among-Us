import static org.junit.Assert.assertTrue;
import org.junit.Test;

import text_adventure.Game;

public class GradeTests {
    Game game = new Game();

    @Test
    public void testMinimumRoomsForC() {
        int expected = 20;
        assertTrue(game.getRoomCount() >= expected);
    }

    @Test
    public void testMinimumItemsForC() {
        int expected = 10;
        assertTrue(game.getItemCount() >= expected);
    }

    @Test
    public void testMinimumLinksForC() {
        int expected = 30;
        assertTrue(game.getLinks() >= expected);
    }

    // @Test
    // public void testTriggersForC() throws FileNotFoundException {
    //     ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    //     System.setOut(new PrintStream(outContent));
    //     game.runCommands("go south");
    //     game.runCommands("go east");
    //     game.runCommands("go west");

    //     String expected = "Hi I'm Alice";
        
    //     // Print the output
    //     System.out.println(outContent);

    //     assertTrue(outContent.toString().contains(expected));
        
    // }

    @Test
    public void testMinimumRoomsForB() {
        int expected = 30;
        assertTrue(game.getRoomCount() >= expected);
    }

    @Test
    public void testMinimumItemsForB() {
        int expected = 15;
        assertTrue(game.getItemCount() >= expected);
    }

    @Test
    public void testMinimumLinksForB() {
        int expected = 40;
        assertTrue(game.getLinks() >= expected);
    }

    // Timers already tested in their own file.
    // @Test
    // public void testTimersForB() {
    //     assertNotNull(game.getTimers());
    // }

    // @Test
    // public void testDynamicLinksForB() {
    //     assertTrue(game.hasDynamicLinks());
    // }

    @Test
    public void testMinimumRoomsForA() {
        int expected = 40;
        assertTrue(game.getRoomCount() >= expected);
    }

    @Test
    public void testMinimumItemsForA() {
        int expected = 20;
        assertTrue(game.getItemCount() >= expected);
    }

    // Not doing this
    // @Test
    // public void testMinimumLinksForA() {
    //     int expected = 50;
    //     assertEquals(expected, game.getLinks());
    // }
}
