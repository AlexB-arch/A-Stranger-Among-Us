package text_adventure.objects;

import java.util.ArrayList;


// We want to implement a standard interface for items
// Sounds super cool these interfaces

public interface Item {
  String getName();
  String getDescription();
  double getValue();
  double getWeight();
  ArrayList<String> actions();
  
}
