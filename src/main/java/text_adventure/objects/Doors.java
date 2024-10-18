package text_adventure.objects;

import text_adventure.objects.Room;

public class Door{

	public static string color;
	public static int id;
	public static boolean locked;
	public static Room destination;

	//Constructor
	public Door(string color, int id,  boolean locked, Room destination)
	{
		setColor(color);
		setID(id);
		setLock(locked);
		setdest(destination);
	}

	public void setColor(String color){
			this.color = color;
	}

	public setLock(boolean locked){
			this.locked = locked;
	}
	public setID(int id){
			this.id = id;
	}
	public setdest(Room destination){
			this.destination = destination;
	}

	public unlock(string color)
	{
		if this.color == color
		{
			
		}
	}

}
