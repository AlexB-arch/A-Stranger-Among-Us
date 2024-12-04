package text_adventure.objects;

import java.util.stream.Collectors;

/**
 * Container - Alex
 * Class for items that can hold other items.
 * 
 */

public class Container extends ItemHolder implements java.io.Serializable {
    private boolean openable;
    private boolean isOpen;
    
    public Container(String name, String description, ItemList itemList, ItemHolder container, boolean openable) {
        super(name, description, itemList, container);
        this.items = new ItemList();
        this.openable = openable;
        this.isOpen = false;
    }

    public Container(String name, String description, ItemList itemList, ItemHolder container, boolean openable, boolean isOpen, boolean canTake, boolean canMove) {
        super(name, description, canTake, canMove, itemList, container);
        this.openable = openable;
        this.isOpen = isOpen;
    }

    public boolean isOpenable() {
        return openable;
    }

    public void setOpenable(boolean openable) {
        this.openable = openable;
    }

    public boolean isOpen() {
        return isOpen;
    }

    @Override
    public String open() {
        String string;
        // If the container is not openable, return a message saying so
        if (!openable) {
            string = "Can't open " + getName();
        } else {
            // If the container is already open, return a message saying so
            if (isOpen) {
                string = "The " + getName() + " is already open.";
            } else {
                // If the container is closed, open it and if its empty, return a message saying so
                isOpen = true;
                if (getItems().isEmpty()) {
                    string = "You open the " + getName() + " and find nothing inside.";
                } else {
                    // If the container is not empty, return a message with the items inside
                    string = "You open the " + getName() + " and find: " + getItems().stream().map(Item::getName).collect(Collectors.joining(", "));
                }
            }
        }
        return string;
    }

    @Override
    public String close() {
        String string;

        if (!openable) {
            string = "Can't close " + getName();
        } else {
            if (isOpen) {
                isOpen = false;
                string = "You close the " + getName();
            } else {
                string = "The " + getName() + " is already closed.";
            }
        }
        return string;
    }

    @Override
    public String describe() {
        return getDescription();
    }

    public ItemList getInventory() {
        return getItems();
    }

    public Item getItemByName(String itemName) {
        return getItems().getItemByName(itemName);
    }
}
