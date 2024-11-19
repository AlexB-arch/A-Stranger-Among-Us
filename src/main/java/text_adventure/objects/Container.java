package text_adventure.objects;

public class Container extends ItemHolder implements java.io.Serializable {
    private boolean openable;
    private boolean isOpen;
    
    public Container(String name, String description, ItemList itemList, ItemHolder container, boolean openable) {
        super(name, description, itemList, container);
        this.openable = openable;
        this.isOpen = false;
    }
}
