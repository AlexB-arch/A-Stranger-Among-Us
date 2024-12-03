package text_adventure;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import text_adventure.objects.Inventory;
import text_adventure.objects.Item;
import text_adventure.objects.NPC;
import text_adventure.objects.Room;
import text_adventure.objects.RoomVisualizer;

/**
 * World
 * This class defines our world and all the connections and rooms in it. 
 * It stores all of these rooms in a Map for easy lookup and traversal for other 
 * systems. 
 * 
 * Importantly it also populates the items in the world, builds the graphviz maps among other things.
 */
public class World {
    private Map<String, Room> rooms;

    public World() {
        this.rooms = new HashMap<>();
    }

    public Map<String, Room> initializeRooms() {
        initializeDecks();
        return rooms;
    }

    private void initializeDecks() {
        // Main Rooms
        Room messHall = createRoom("Mess Hall",
            "This is the main hall of the space station.\n" +
            "To the north is the Bridge. The door appears to have emergency locked.\n" +
            "To the east is the Botany Bay. The door appears to have emergency locked.\n" +
            "To the west is the Barracks.\n" +
            "To the south is the Engine Room. Hallway",
            new Item[]{
                new Item("Batteries"),
                new Item("Food")
            });

        Room barracks = createRoom("Barracks",
            "The Barracks serve as living quarters where the crew sleeps and rests.",
            null);

        Room barracksStorage = createRoom("Barracks Storage",
            "Barracks Storage holds personal belongings and supplies for the crew's accommodations.",
            new Item[]{
                new Item("Batteries"),
                });

        Room hallwayBarracks = createRoom("Barracks Hallway",
            "This hallway connects the Barracks to the main areas of the station.",
            null);

        Room weaponsBay = createRoom("Weapons Bay",
            "The Weapons Bay stores and maintains the station's defensive armaments.",null);

        Room hallwayWeapons = createRoom("Weapons Bay Hallway",
            "A secure hallway leading to the Weapons Bay.",
            null);

        Room bridge = createRoom("Bridge",
            "The Bridge is the command center where the station's operations are monitored and controlled.",
            null);

        Room botanyBay = createRoom("Botany Bay",
            "The Botany Bay is dedicated to growing plants for food, oxygen production, and research.",
            new Item[]{
                new Item("Flowers"),
                new Item("Seeds"),
                new Item("Oxygen Tanks")
            });

        Room botanyStorage = createRoom("Botany Storage",
            "Botany Storage houses equipment and supplies for agricultural activities.",
            new Item[]{
                new Item("Flowers"),
                new Item("Seeds")
            });

        Room hallwayBotany = createRoom("Botany Hallway",
            "A humid hallway connecting to the Botany areas.",
            new Item[]{
                new Item("Mound of Dirt"),
            });

        Room engineRoom = createRoom("Engine Room",
            "The Engine Room contains the main power generators for the station.",
            null);

        Room engineStorage = createRoom("Engine Room Storage",
            "Engine Room Storage holds spare parts and tools for engine maintenance.",
            null);

        Room hallwayEngine = createRoom("Engine Room Hallway",
            "A hallway leading to the Engine Room. Warning signs line the walls.",
            null);

        // Central Storage Bay Area
        Room storageBay = createRoom("Storage Bay",
            "The Storage Bay is a large area for storing various supplies, equipment, and cargo.",
            new Item[]{
                new Item("Batteries"), 
                new Item("Food"), 
                new Item("Screws"),
                new Item("Red Keycard")
            });

        // Communication Hub Area
        Room communicationHub = createRoom("Communication Hub",
            "The Communication Hub manages the station's internal and external communications.",
            null);

        Room hallwayCommunication = createRoom("Communication Hub Hallway",
            "A hallway leading to the Communication Hub. Network cables line the ceiling.",
            null);

		Room hallwayDocks = createRoom("Dock Hallway",
						"A hallway leading to the Docking bay.",
						null);

		Room dockingBay = createRoom("Dock Bay",
						"The Docking Bay is where spacecraft dock to load and unload passengers and cargo.",
                         new Item[]{new Item("BLA")});

        // Data Center Area
        Room dataCenter = createRoom("Data Center",
            "The Data Center processes and stores all digital information and computing tasks.",
            new Item[]{
                 new Item("Blue Keycard")
            });

        Room hallwayData = createRoom("Data Center Hallway",
            "A cold hallway leading to the Data Center. The hum of servers can be heard.",
            null);

        // Medical Area
        Room medicalBay = createRoom("Medical Bay",
            "The Medical Bay provides medical treatment and healthcare services for the crew.",
            new Item[]{
                new Item("Bandages")
            });

        Room medicalCloset = createRoom("Medical Closet",
            "The Medical Closet stores medical supplies and equipment needed for healthcare.",
            new Item[]{
                new Item("Bandages")
            });

        Room hallwayMedical = createRoom("Medical Bay Hallway",
            "A sterile hallway leading to the Medical Bay. Emergency equipment lines the walls.",
            null);

        // Thruster Control Area
        Room thrusterBay = createRoom("Thruster Bay",
            "The Thruster Bay houses maneuvering thrusters to adjust the station's position.",
            new Item[]{
                new Item("Fuel"),
                new Item("Oxygen"),
                new Item("Batteries")
            });

        Room thruster1 = createRoom("Thruster 1",
            "Thruster 1 is a maneuvering thruster located on one side of the station.",
            new Item[]{
                new Item("Fuel")
            });

        Room thruster2 = createRoom("Thruster 2",
            "Thruster 2 is a maneuvering thruster located on one side of the station.",
            new Item[]{
                new Item("Fuel")
            });

        Room hallwayStorage = createRoom("Southern Storage Hallway",
            "A maintenance hallway leading to The Storage Bay. Warning signs about fuel leaks are posted.",
            null);

        // Waste Management Area
        Room wasteControl = createRoom("Waste Control",
            "Waste Control handles the station's processing and recycling of waste materials.",
            null);

        Room wasteControlRoom = createRoom("Waste Control Room",
            "Controls the Waste management systems.",
            null);

        Room hallwayWaste = createRoom("Waste Control Hallway",
            "A hallway leading to the Waste Control area. The air smells recycled.",
            null);

        // Quarantine Area
        Room quarantineRoom = createRoom("Quarantine Room",
            "The Quarantine Room isolates crew or materials that may be contaminated or hazardous.",
            null);

        Room hallwayQuarantine = createRoom("Quarantine Room Hallway",
            "A sealed hallway leading to the Quarantine Room. Decontamination equipment lines the walls.",
            null);

                // Waste Ejection Area
        Room wasteEjection = createRoom("Waste Ejection",
            "Waste Ejection is the system that disposes of non-recyclable waste into space.",
            
            new Item[]{
                new Item("Batteries")
            });

        Room ejectionControl = createRoom("Ejection Control",
            "Ejection Control monitors and operates the waste ejection systems.",
            null);

        Room hallwayEjection = createRoom("Waste Ejection Hallway",
            "A hallway leading to the Waste Ejection system. Warning signs about decompression line the walls.",
            null);

        // Fuel Management Area
        Room fuelStorage = createRoom("Fuel Storage",
            "Fuel Storage is where the station's fuel reserves are kept.",
            new Item[]{
                new Item("Fuel"), 
                new Item("Batteries"),
            });

        Room fuelControl = createRoom("Fuel Control",
            "Fuel Control manages fuel distribution throughout the station's systems.",
            null);

        Room hallwayFuel = createRoom("Fuel Storage Hallway",
            "A secure hallway leading to the Fuel Storage area. Heavy blast doors can seal this section.",
            null);

        // Connection corridor between areas
        Room mainCorridor = createRoom("Main Corridor",
            "The main corridor of Floor 3 connecting the Fuel and Waste Ejection areas.",
            null);

        // Turbo lifts 

        Room turboLiftDeck1hallwayBotany = createRoom("TurboLift Deck 1 Botany","TurboLift that goes down to the storage hallway on Deck 2", null);
        Room turboLiftDeck2hallwayWaste = createRoom("TurboLift Deck 2 Storage", "Turbo lift that goes up to the botany hallway on Deck 1", null);
        Room turboLiftDeck2ThrusterBay = createRoom("TurboLift Deck 2 ThrusterBay", "Turbo lift that goes down to Fuel Storage on Deck 3", null);
        Room turboLiftDeck2WasteControl = createRoom("TurboLift Deck 2 Waste Control", "Turbo lift that goes down to Waste Ejection on Deck 3", null);
        Room turboLiftDeck3FuelStorage = createRoom("TurboLift Deck 3 Fuel Storage", "Turbo lift that goes up to the Thruster bay on Deck 2", null);
        Room turboLiftDeck3WasteEjection = createRoom("TurboLift Deck 3 Waste Ejection", "Turbo lift that goes up to Waste Control on Deck 2", null);


        // Floor 1 connections (N, S, E, W)
        messHall.setExits(bridge, hallwayEngine, hallwayBotany, hallwayBarracks);
        barracks.setExits(barracksStorage, null, hallwayBarracks, null);
        barracksStorage.setExits(null, barracks, null, null);
        hallwayBarracks.setExits(null, null, barracks, messHall);
        weaponsBay.setExits(null, null, hallwayWeapons, null);
        hallwayWeapons.setExits(null, null, weaponsBay, hallwayEngine);
        bridge.setExits(null, messHall, null, null);
        botanyBay.setExits(null, null, hallwayBotany, botanyStorage);
        botanyStorage.setExits(null, null, botanyBay, null);
        hallwayBotany.setExits(turboLiftDeck1hallwayBotany, null, messHall, botanyBay);
        engineRoom.setExits(hallwayEngine, null, engineStorage, null);
        engineStorage.setExits(null, null, null, engineRoom);
        hallwayEngine.setExits(messHall, engineRoom, null, null);


        // Floor 2 connections (N, S, E, W)
        storageBay.setExits(hallwayCommunication, hallwayStorage, hallwayWaste, hallwayDocks);
		hallwayStorage.setExits(storageBay, thrusterBay, hallwayMedical, hallwayQuarantine);

        communicationHub.setExits(null, null , hallwayCommunication, null);
        hallwayCommunication.setExits(null, storageBay, hallwayData, communicationHub);

        dataCenter.setExits(null, null, null , hallwayData);
        hallwayData.setExits(null, null, dataCenter, hallwayCommunication);
        medicalBay.setExits(null, null, medicalCloset, hallwayMedical);
        medicalCloset.setExits(null, null, null, medicalBay);
        hallwayMedical.setExits(null, null, medicalBay , hallwayStorage);

        thrusterBay.setExits(hallwayStorage, turboLiftDeck2ThrusterBay, thruster2, thruster1);
        thruster1.setExits(null, null, thrusterBay, null);
        thruster2.setExits(null, null, null, thrusterBay);

        wasteControl.setExits(wasteControlRoom, turboLiftDeck2WasteControl, null, hallwayWaste);
        wasteControlRoom.setExits(null, wasteControl, null, null);
        hallwayWaste.setExits(turboLiftDeck2hallwayWaste, null, wasteControl , storageBay);

        quarantineRoom.setExits(null, null, hallwayQuarantine, null);
        hallwayQuarantine.setExits(null, null, hallwayStorage, quarantineRoom);

        hallwayDocks.setExits(storageBay, null, dockingBay, null);
        dockingBay.setExits(null, null,null,hallwayDocks);
        


        // Floor 3 connections
        wasteEjection.setExits(null, hallwayEjection, ejectionControl, null);
        ejectionControl.setExits(null, null, null, wasteEjection);
        hallwayEjection.setExits(wasteEjection, null, turboLiftDeck3WasteEjection, null);
        fuelStorage.setExits(fuelControl, hallwayFuel, null, null);
        fuelControl.setExits(null, fuelStorage, null, null);
        hallwayFuel.setExits(fuelStorage, null, turboLiftDeck3FuelStorage, null);


        // Turbo Lift Connections
        turboLiftDeck1hallwayBotany.setExits(null,hallwayBotany,null,null,null,turboLiftDeck2hallwayWaste);
        turboLiftDeck2hallwayWaste.setExits(null,hallwayWaste,null,null,turboLiftDeck1hallwayBotany,null);
        
        turboLiftDeck2ThrusterBay.setExits(thrusterBay,null,null,null,null,turboLiftDeck3FuelStorage);
        turboLiftDeck3FuelStorage.setExits(null,null,null,hallwayFuel,turboLiftDeck2ThrusterBay,null);
        
        turboLiftDeck2WasteControl.setExits(wasteControl,null,null,null,null,turboLiftDeck3WasteEjection);
        turboLiftDeck3WasteEjection.setExits(null,null,null,hallwayEjection, turboLiftDeck2WasteControl,null);

        
    }

        
    

    private Room createRoom(String name, String description, Item[] items) {
        Inventory inventory = new Inventory();
          if (items != null) {
              for (Item item : items) {
                  inventory.addItem(item);
              }
          }
        Room room = new Room(name, description, inventory);
        rooms.put(name, room);
        return room;
    }

    public void visualizeWorld() {
        try{
            RoomVisualizer viz = new RoomVisualizer();
            // Add all rooms in your game
            rooms.keySet().forEach(x -> viz.addRoom(rooms.get(x)));
            viz.saveToFile("Map.dot");
          
            } catch (IOException e){
                System.out.println(e);
            }
    }

    private void addInitialNPCs() {
        // Add NPCs to their starting locations
        rooms.get("Mess Hall").addNpc(new NPC("Alice", this.rooms.get("Mess Hall")));
        rooms.get("Botany Storage").addNpc(new NPC("Calhoun", this.rooms.get("Storage Bay")));
        rooms.get("Quarantine Room").addNpc(new NPC("Theodore", this.rooms.get("Quarantine Room")));
        rooms.get("Waste Control").addNpc(new NPC("Bony", this.rooms.get("Waste Control Room")));
    }
}
