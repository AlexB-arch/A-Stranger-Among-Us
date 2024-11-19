package text_adventure.objects;

public class ItemHolder extends Item {
    private ItemList items;
    private String itemName;
    ItemList itemList = new ItemList();
    private LootManager lootManager = null;

    public ItemHolder(String name, String description, ItemList items, ItemHolder container) {
        super(name, description, container);
        this.items = items;
    }

    public ItemHolder(String name, String description, boolean takable, boolean movable, ItemList items, ItemHolder container) {
        super(name, description, takable, movable, container);
        this.items = items;
    }

    public static Container toContainer(Item item){
        Container container = null;

        if (item instanceof Container) {
            container = (Container) item;
        }

        return container;
    }

    public int getItemCount() {
        return items.size();
    }

    public ItemList getItems() {
        return items;
    }

    private ItemList itemsToFlatList(ItemHolder itemHolder) {

        for (Item item : itemHolder.getItems()) {
            itemList.add(item);
            if (item instanceof Container) {
                itemsToFlatList((Container) item);
            }
        }

        return itemList;
    }

    public ItemList flatten() {
        itemList.clear();
        return itemsToFlatList(this);
    }

    private void findItemInAnyList(ItemHolder itemHolder, String objectName) {
        boolean found = false;
        Container container;

        
    }
}
