package text_adventure.interfaces;

import java.util.List;

import text_adventure.objects.Item;

public interface LootManagement {
    List<Item> getAvailableItems();
    void collectItem(Item item);
}
