package text_adventure.objects;

public class ItemHolder extends Item {
    protected ItemList items;
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

        for (Item item : itemHolder.getItems()) {
            if (item.getName().equals(objectName)) {
                
                found = true;
            }
            if (!found) {
                container = toContainer(item);
                if (container != null && (container.isOpen())) {
                    findItemInAnyList(container, objectName);
                }
            }
        }
    }

    public void doDescribeItems(ItemHolder itemHolder) {
        ItemList itemList = itemHolder.getItems();
        Container container;

        for (Item item : itemList) {
            String containerName = "";
            if (item.getContainer() instanceof Container) {
                containerName = " in " + item.getContainer().getName();
            }

            itemName += item.getName() + containerName + "\n";
            container = toContainer(item);

            if (container != null && (container.isOpen())) {
                if (container.getItemCount() > 0) {
                    doDescribeItems(container);
                }
            }
        }
    }

    public String describeItems() {
        itemName = "";
        doDescribeItems(this);
        return itemName;
    }

    public boolean containsItem(Item item) {
        return getItems().contains(item);
    }

    public LootManager findItem(String objectName) {
        lootManager = null;

        findItemInAnyList(this, objectName);

        return lootManager;
    }

    public ItemList getItems() {
        return items;
    }

    public void setItems(ItemList items) {
        this.items = items;
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void removeItem(Item item) {
        items.remove(item);
    }

    protected void transferObject(Item item, ItemHolder from, ItemHolder to) {
        from.removeItem(item);
        to.addItem(item);
        item.setContainer(to);
    }
}
