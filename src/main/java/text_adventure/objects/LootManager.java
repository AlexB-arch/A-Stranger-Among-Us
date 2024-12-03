package text_adventure.objects;

/**
 * LootManager - Alex
 * Class for managing loot.
 * 
 * Used to manage loot in a room or inventory.
 */

public class LootManager {
    private Item item;
    private ItemHolder itemHolder;

    public LootManager(Item item, ItemHolder itemHolder) {
        this.item = item;
        this.itemHolder = itemHolder;
    }

    public Item geItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public ItemHolder getItemHolder() {
        return itemHolder;
    }

    public void setItemHolder(ItemHolder itemHolder) {
        this.itemHolder = itemHolder;
    }

    public ItemList getItems() {
        return itemHolder.getItems();
    }
}
