package text_adventure.interfaces;

import java.util.List;

public interface LootManagement {
    List<Item> getAvailableItems();
    void collectItem(Item item);
}
