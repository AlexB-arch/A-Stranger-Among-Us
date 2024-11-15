import org.junit.Test;

import text_adventure.items.Key;

import static org.junit.Assert.*;

import java.util.ArrayList;


public class KeyTest {

    @Test
    public void testGetName() {
        Key key = new Key();
        assertEquals("Key", key.getName());
    }

    @Test
    public void testGetDescription() {
        Key key = new Key();
        assertEquals("A small, rusty key.", key.getDescription());
    }

    @Test
    public void testGetActions() {
        Key key = new Key();
        ArrayList<String> actions = key.getActions();
        assertTrue(actions.contains("Use"));
    }

    @Test
    public void testPerformAction_Use() {
        Key key = new Key();
        assertTrue(key.performAction("Use"));
    }

    @Test
    public void testPerformAction_NotUse() {
        Key key = new Key();
        assertFalse(key.performAction("Open"));
    }

    @Test
    public void testToString() {
        Key key = new Key();
        String expectedString = "Name: Key\nDescription: A small, rusty key.\nValue: $10.0\nWeight: 1.0\nActions: [Use]";
        assertEquals(expectedString, key.toString());
    }
}
