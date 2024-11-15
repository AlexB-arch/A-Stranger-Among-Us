package text_adventure.objects;

import java.util.ArrayList;
import java.util.List;

import text_adventure.interfaces.Item;

public abstract class AbstractItem implements Item {

    protected String name;
    protected String description;
    protected List<String> actions = new ArrayList<>();

    // Constructor
    public AbstractItem(String name, String description) {
        this.name = name;
        this.description = description;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public List<String> getActions() {
        return actions;
    }
}
