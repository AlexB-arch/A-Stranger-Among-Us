package text_adventure.interfaces;

public interface INVENTORY {
    public void addItem(Item item);
    public void removeItem(Item item);
    public void useItem(Item item);
    public int size();
    public boolean contains(Item item);
    public void printItems();
}
