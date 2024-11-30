package text_adventure.objects;

public class Container extends ItemHolder implements java.io.Serializable {
    private boolean openable;
    private boolean isOpen;
    
    public Container(String name, String description, ItemList itemList, ItemHolder container, boolean openable) {
        super(name, description, itemList, container);
        this.items = new ItemList();
        this.openable = false;
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

        if (!openable) {
            string = "Can't open " + getName();
        } else {
            if (isOpen) {
                string = "The " + getName() + " is already open.";
            } else {
                isOpen = true;
                string = "You open the " + getName();
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
        String string;
        
        string = super.describe();
        if (openable) {
            if (isOpen) {
                string += " (open)";
            } else {
                string += " (closed)";
            }
        }
        if (isOpen) {
            if (getItemCount() > 0) {
                string += "\n There is something in it.";
            }
        }
        return string;
    }

    public ItemList getInventory() {
        return getItems();
    }

    public Item getItemByName(String itemName) {
        return getItems().getItemByName(itemName);
    }
}
