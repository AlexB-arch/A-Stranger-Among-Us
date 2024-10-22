import static org.junit.Assert.assertEquals;

import org.junit.Test;

import text_adventure.objects.NPC;
import text_adventure.objects.Player;
import text_adventure.objects.Room;

public class PartyTests {

    // Initialize Player
    private Player player = new Player();
    // Initialize NPC
    private NPC aliceNpc = new NPC("alice", new Room("Test Room", "Test Description", null));

    @Test
    public void testEmptyParty() {
        // Check that the party is empty
        int expected = 0;

        assertEquals(player.getParty().size(), expected);
    }

    @Test
    public void testAddToParty() {
        // Add NPC to party
        player.addPartyMember(aliceNpc);

        // Check that the party has one NPC
        int expected = 1;

        assertEquals(player.getParty().size(), expected);
    }

    @Test
    public void testRemoveFromParty() {
        // Remove NPC from party
        player.removePartyMember(aliceNpc);
        

        // Check that the party is empty
        int expected = 0;

        assertEquals(player.getParty().size(), expected);
    }

    @Test
    public void testAddMultipleToParty() {
        // Declare two more NPCs
        NPC bobNpc = new NPC("bob", new Room("Test Room", "Test Description", null));
        NPC charlieNpc = new NPC("charlie", new Room("Test Room", "Test Description", null));
        
        // Add NPCs to party
        player.addPartyMember(aliceNpc);
        player.addPartyMember(bobNpc);
        player.addPartyMember(charlieNpc);

        // Check that the party has three NPCs
        int expected = 3;

        assertEquals(player.getParty().size(), expected);
    }

    @Test
    public void testRemoveMultipleFromParty() {
        // Declare two more NPCs
        NPC bobNpc = new NPC("bob", new Room("Test Room", "Test Description", null));
        NPC charlieNpc = new NPC("charlie", new Room("Test Room", "Test Description", null));
        
        // Add NPCs to party
        player.addPartyMember(aliceNpc);
        player.addPartyMember(bobNpc);
        player.addPartyMember(charlieNpc);

        // Check that the party has the expected amount NPCs
        int expected = 3;
        assertEquals(player.getParty().size(), expected);

        // Remove NPCs from party
        player.removePartyMember(aliceNpc);
        player.removePartyMember(bobNpc);

        // Check that the party is empty
        expected = 1;

        assertEquals(player.getParty().size(), expected);
    }
}
