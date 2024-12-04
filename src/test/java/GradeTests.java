import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import text_adventure.Game;
import text_adventure.objects.Player;

public class GradeTests {

    private Game game;
    private Player player;


    @Before
    public void setUp() {
        game = new Game();
        player = Game.player;
    }

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

    @Test
    public void testDynamicLinksForB() {
        assertTrue(game.hasDynamicLinks());
    }
}
