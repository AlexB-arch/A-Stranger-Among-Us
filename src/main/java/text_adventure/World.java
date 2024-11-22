package text_adventure;

import text_adventure.objects.*;
import java.util.HashMap;
import java.util.Map;

public class World {
    private Map<String, Room> rooms;
    
    public World() {
        this.rooms = new HashMap<>();
    }
    
    public Map<String, Room> initializeRooms() {
        initializeDeckOne();
        initializeDeckTwo();
        initializeDeckThree();
        return rooms;
    }

    private void initializeDeckOne() {
        // Main Rooms
        Room messHall = createRoom("Mess Hall", 
            "This is the main hall of the space station.\n" +
            "To the north is the Bridge. The door appears to have emergency locked.\n" +
            "To the east is the Botany Bay. The door appears to have emergency locked.\n" +
            "To the west is the Weapons Bay.\n" +
            "To the south is the Engine Room.",
            new String[]{"Batteries", "Food"});

        Room barracks = createRoom("Barracks", 
            "The Barracks serve as living quarters where the crew sleeps and rests.", 
            null);

        Room barracksStorage = createRoom("Barracks Storage", 
            "Barracks Storage holds personal belongings and supplies for the crew's accommodations.", 
            new String[]{"Batteries"});

        Room hallwayBarracks = createRoom("Barracks Hallway", 
            "This hallway connects the Barracks to the main areas of the station.", 
            null);

        Room hallwayBarracksStorage = createRoom("Barracks Storage Hallway", 
            "A short hallway leading to the Barracks Storage room.", 
            null);

        Room weaponsBay = createRoom("Weapons Bay", 
            "The Weapons Bay stores and maintains the station's defensive armaments.", 
            new String[]{"Plasma Gun", "Plasma Gun ammunition"});

        Room hallwayWeapons = createRoom("Weapons Bay Hallway", 
            "A secure hallway leading to the Weapons Bay.", 
            null);

        Room bridge = createRoom("Bridge", 
            "The Bridge is the command center where the station's operations are monitored and controlled.", 
            null);

        Room hallwayBridge = createRoom("Bridge Hallway", 
            "A hallway leading to the Bridge. Security doors can seal this area off.", 
            null);

        Room botanyBay = createRoom("Botany Bay", 
            "The Botany Bay is dedicated to growing plants for food, oxygen production, and research.", 
            new String[]{"Flowers", "Seeds", "Oxygen Tanks"});

        Room botanyStorage = createRoom("Botany Storage", 
            "Botany Storage houses equipment and supplies for agricultural activities.", 
            new String[]{"Flowers", "Seeds"});

        Room hallwayBotany = createRoom("Botany Hallway", 
            "A humid hallway connecting to the Botany areas.", 
            null);

        Room engineRoom = createRoom("Engine Room", 
            "The Engine Room contains the main power generators for the station.", 
            null);

        Room engineStorage = createRoom("Engine Room Storage", 
            "Engine Room Storage holds spare parts and tools for engine maintenance.", 
            null);

        Room hallwayEngine = createRoom("Engine Room Hallway", 
            "A hallway leading to the Engine Room. Warning signs line the walls.", 
            null);

        // Floor 1 connections
        messHall.setExits(hallwayBridge, hallwayEngine, hallwayBotany, hallwayWeapons);
        barracks.setExits(hallwayBarracksStorage, null, hallwayBarracks, null);
        barracksStorage.setExits(null, null, null, hallwayBarracksStorage);
        hallwayBarracks.setExits(null, null, barracks, hallwayWeapons);
        hallwayBarracksStorage.setExits(barracksStorage, barracks, null, null);
        weaponsBay.setExits(null, null, hallwayWeapons, null);
        hallwayWeapons.setExits(null, null, weaponsBay, messHall);
        bridge.setExits(null, hallwayBridge, null, null);
        hallwayBridge.setExits(bridge, messHall, null, null);
        botanyBay.setExits(null, null, hallwayBotany, botanyStorage);
        botanyStorage.setExits(null, null, botanyBay, null);
        hallwayBotany.setExits(null, null, messHall, botanyBay);
        engineRoom.setExits(hallwayEngine, null, engineStorage, null);
        engineStorage.setExits(null, null, null, engineRoom);
        hallwayEngine.setExits(messHall, engineRoom, null, null);
    }

    private void initializeDeckTwo() {
        // Central Storage Bay Area
        Room storageBay = createRoom("Storage Bay", 
            "The Storage Bay is a large area for storing various supplies, equipment, and cargo.", 
            new String[]{"Batteries", "Food", "Screws", "Red Keycard"});

        // Communication Hub Area
        Room communicationHub = createRoom("Communication Hub", 
            "The Communication Hub manages the station's internal and external communications.", 
            null);

        Room hallwayCommunication = createRoom("Communication Hub Hallway", 
            "A hallway leading to the Communication Hub. Network cables line the ceiling.", 
            null);

        // Data Center Area
        Room dataCenter = createRoom("Data Center", 
            "The Data Center processes and stores all digital information and computing tasks.", 
            new String[]{"Blue Keycard"});

        Room hallwayData = createRoom("Data Center Hallway", 
            "A cold hallway leading to the Data Center. The hum of servers can be heard.", 
            null);

        // Medical Area
        Room medicalBay = createRoom("Medical Bay", 
            "The Medical Bay provides medical treatment and healthcare services for the crew.", 
            new String[]{"Bandages"});

        Room medicalCloset = createRoom("Medical Closet", 
            "The Medical Closet stores medical supplies and equipment needed for healthcare.", 
            new String[]{"Bandages"});

        Room hallwayMedical = createRoom("Medical Bay Hallway", 
            "A sterile hallway leading to the Medical Bay. Emergency equipment lines the walls.", 
            null);

        // Thruster Control Area
        Room thrusterBay = createRoom("Thruster Bay", 
            "The Thruster Bay houses maneuvering thrusters to adjust the station's position.", 
            new String[]{"Fuel", "Oxygen", "Batteries"});

        Room thruster1 = createRoom("Thruster 1", 
            "Thruster 1 is a maneuvering thruster located on one side of the station.", 
            new String[]{"Fuel"});

        Room thruster2 = createRoom("Thruster 2", 
            "Thruster 2 is a maneuvering thruster located on one side of the station.", 
            new String[]{"Fuel"});

        Room hallwayThruster1 = createRoom("Thruster 1 Hallway", 
            "A maintenance hallway leading to Thruster 1. Warning signs about fuel leaks are posted.", 
            null);

        Room hallwayThruster2 = createRoom("Thruster 2 Hallway", 
            "A maintenance hallway leading to Thruster 2. Warning signs about fuel leaks are posted.", 
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

        // Floor 2 connections
        storageBay.setExits(hallwayCommunication, thrusterBay, hallwayQuarantine, hallwayMedical);
        
        communicationHub.setExits(null, hallwayCommunication, null, hallwayData);
        hallwayCommunication.setExits(communicationHub, storageBay, null, null);
        
        dataCenter.setExits(null, null, hallwayData, null);
        hallwayData.setExits(null, null, communicationHub, dataCenter);
        
        medicalBay.setExits(null, null, storageBay, medicalCloset);
        medicalCloset.setExits(null, null, medicalBay, null);
        hallwayMedical.setExits(null, null, storageBay, medicalBay);
        
        thrusterBay.setExits(storageBay, null, hallwayThruster2, hallwayThruster1);
        thruster1.setExits(null, null, thrusterBay, null);
        thruster2.setExits(null, null, null, thrusterBay);
        hallwayThruster1.setExits(null, null, thrusterBay, thruster1);
        hallwayThruster2.setExits(null, null, thruster2, thrusterBay);
        
        wasteControl.setExits(null, null, storageBay, wasteControlRoom);
        wasteControlRoom.setExits(null, null, wasteControl, null);
        hallwayWaste.setExits(null, null, storageBay, wasteControl);
        
        quarantineRoom.setExits(null, null, hallwayQuarantine, null);
        hallwayQuarantine.setExits(null, null, null, storageBay);
    }

    private void initializeDeckThree() {
        // Waste Ejection Area
        Room wasteEjection = createRoom("Waste Ejection", 
            "Waste Ejection is the system that disposes of non-recyclable waste into space.", 
            new String[]{"Batteries"});

        Room ejectionControl = createRoom("Ejection Control", 
            "Ejection Control monitors and operates the waste ejection systems.", 
            null);

        Room hallwayEjection = createRoom("Waste Ejection Hallway", 
            "A hallway leading to the Waste Ejection system. Warning signs about decompression line the walls.", 
            null);

        // Fuel Management Area
        Room fuelStorage = createRoom("Fuel Storage", 
            "Fuel Storage is where the station's fuel reserves are kept.", 
            new String[]{"Fuel", "Batteries"});

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

        // Floor 3 connections
        wasteEjection.setExits(null, hallwayEjection, ejectionControl, null);
        ejectionControl.setExits(null, null, null, wasteEjection);
        hallwayEjection.setExits(wasteEjection, mainCorridor, null, null);
        
        fuelStorage.setExits(fuelControl, hallwayFuel, null, null);
        fuelControl.setExits(null, fuelStorage, null, null);
        hallwayFuel.setExits(fuelStorage, mainCorridor, null, null);
        
        mainCorridor.setExits(hallwayEjection, null, hallwayFuel, null);
    }

    private Room createRoom(String name, String description, String[] items) {
        Inventory inventory = new Inventory();
        // if (items != null) {
        //     for (String itemName : items) {
        //         inventory.addItem(new Item(itemName));
        //     }
        // }
        Room room = new Room(name, description, inventory);
        rooms.put(name, room);
        return room;
    }

    private void addInitialNPCs() {
        // Add NPCs to their starting locations
        rooms.get("Mess Hall").addNpc(new NPC("Alice", this.rooms.get("Mess Hall")));
        rooms.get("Botany Storage").addNpc(new NPC("Calhoun", this.rooms.get("Storage Bay")));
        rooms.get("Quarantine Room").addNpc(new NPC("Theodore", this.rooms.get("Quarantine Room")));
        rooms.get("Waste Control").addNpc(new NPC("Bony", this.rooms.get("Waste Control Room")));
    }
}
