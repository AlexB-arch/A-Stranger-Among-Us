package text_adventure.interfaces;

public class Item {
    // Static variable to keep track of the number of items created
    private static int itemCount = 0;

    private String name;
    private String description;
    private boolean takable;
    private boolean movable;

    private ItemHolder container;

    public Item(String name, String description, ItemHolder container) {
        // Increment the item count each time an item is created
        itemCount++;

        this.name = name;
        this.description = description;
        this.container = container;
        this.takable = true;
        this.movable = true;
    }

    public Item(String name, String description, boolean takable, boolean movable, ItemHolder container) {
        // Increment the item count each time an item is created
        itemCount++;

        this.name = name;
        this.description = description;
        this.takable = takable;
        this.movable = movable;
        this.container = container;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String aDescription) {
        this.description = aDescription;
    }

    public boolean isTakable() {
        return takable;
    }

    public void setTakable(boolean takable) {
        this.takable = takable;
    }

    public boolean isMovable() {
        return movable;
    }

    public void setMovable(boolean movable) {
        this.movable = movable;
    }

    public ItemHolder getContainer() {
        return container;
    }

    public void setContainer(ItemHolder container) {
        this.container = container;
    }

    public String open() {
        return "Cannot open " + name + " because it isn't a container.";
    }

    public String close() {
        return "Cannot close " + name + " because it isn't a container.";
    }

    public String describe() {
        return name + ": " + description;
    }

    // For when there are items inside a container
    private Boolean isInside(Container container) {
        ItemHolder holder;
        Boolean inContainer = false;

        holder = this.getContainer();
        
        while (holder != null) {
            if (holder == container) {
                inContainer = true;
            }
            holder = holder.getContainer();
        }
        return inContainer;
    }

    public Boolean is(Item item) {
        return (item instanceof Container) && (this.isInside((Container) item));
    }

    // Static method to get the number of items created
    public static int getInstancedItemCount() {
        return Item.itemCount;
    }
}
