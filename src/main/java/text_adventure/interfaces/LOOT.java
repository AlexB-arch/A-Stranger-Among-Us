package text_adventure.interfaces;

import java.util.List;

public interface LOOT {
    List<Item> getAvailableItems();
    void collectItem(Item item);
}
