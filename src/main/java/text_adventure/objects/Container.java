package text_adventure.objects;

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

        if (isOpen) {
            string = "The " + getName() + " is already open.";
        } else if (!openable) {
            string = "Can't open " + getName();
        } else {
            isOpen = true;
            string = "You open the " + getName();
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
        
        string = getName();
        if (!openable) {
            return string + " can't be opened or closed.";
        } else if (openable && !isOpen) {
            string += " is closed.";
        } else if (openable && isOpen && getItemCount() == 0) {
             string += " is open. There is nothing in it.";
        }
        if (isOpen) {
            if (getItemCount() > 0) {
                string += " is open. It contains: " + getItems().toString();
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
