package text_adventure.resources;

import java.util.Arrays;
import java.util.List;
import java.util.Random;


/*
 *  A class that randomly chooses ambience as part of the game end timer.
 */
public class AmbienceSelector {
    private static final Random random = new Random();
    
    private static final List<String> ambienceDescriptions = Arrays.asList(
        "A distant metallic creaking echoes through the corridor, like the station is slowly twisting in space",
        "Flickering emergency lights cast dancing shadows that seem to move when you're not looking directly at them",
        "The gentle hum of failing life support systems creates an unsettling undertone to the silence",
        "Drops of condensation fall rhythmically from exposed pipes, each drop echoing like a ghostly footstep",
        "A torn piece of paper floats by in zero gravity, tumbling end over end in an endless dance",
        "The viewport shows nothing but an infinite void, making the station feel impossibly small and alone",
        "Ancient coffee cups and personal belongings float freely, frozen in time since the evacuation",
        "Warning lights pulse silently behind cracked display screens, their purpose long forgotten",
        "The air feels thick with isolation, as if no human has breathed it for decades",
        "A loose panel somewhere in the darkness knocks against the wall with irregular, haunting intervals",
        "The station's solar arrays creak and groan as they adjust to catch the distant starlight",
        "Frost patterns on the windows resemble alien writing, slowly expanding in the cold of space",
        "The occasional spark from damaged circuitry illuminates abandoned workstations like lightning",
        "Long shadows stretch across the floor as external debris passes in front of the distant sun",
        "The artificial gravity fluctuates subtly, making your stomach lurch at random moments",
        "Torn wires dangle from the ceiling, swaying gently in non-existent wind",
        "The smell of ozone and aged metal permeates the stale recycled air",
        "A forgotten datapad flickers to life momentarily, displaying fragmented emergency protocols",
        "The walls seem to absorb sound in an unnatural way, making even your breathing sound muffled",
        "Temperature variations create subtle popping sounds in the station's metal skeleton",
        "Patches of frost glisten on the walls where thermal insulation has failed",
        "The station's rotation creates a barely perceptible vibration that sets your teeth on edge",
        "Emergency bulkheads cast prison-like shadows across the empty corridors",
        "The distant sound of cooling systems resembles whispered conversations",
        "Holographic warning signs flicker in and out of existence, their power supply failing",
        "The vacuum of space seems to press against the viewport windows with malevolent intent",
        "Old hydroponic gardens have become overgrown with twisted, pale vegetation",
        "The station's automated announcements play at random, distorted by damaged speakers",
        "Loose insulation material drifts through the air like ghostly cobwebs",
        "The contrast between light and shadow seems sharper than it should be, defying normal physics"
    );
    
    public static String getRandomAmbience() {
        return ambienceDescriptions.get(random.nextInt(ambienceDescriptions.size()));
    }
    

}