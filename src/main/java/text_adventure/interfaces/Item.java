package text_adventure.interfaces;

import java.util.ArrayList;


// We want to implement a standard interface for items
// Sounds super cool these interfaces
// I want to do classes of Itmes.
public interface Item {
  String getName();
  String getDescription();
  double getValue();
  double getWeight();
  ArrayList<String> getActions();
  // there is so much better way of doing this but I'm tired so this works for now
  boolean performAction(String action);
  String toString();
}

