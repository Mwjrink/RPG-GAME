/* Max Rink
 * ICS4U
 * June 1 2016
 * Inventory.java
 * 
 */
import java.util.ArrayList;
import java.util.Collections;

public class Inventory {
	private ArrayList<Entity> inventory = new ArrayList<Entity>();
	private ArrayList<Integer> quantity = new ArrayList<Integer>();
	public void Inventory(){
		
	}
	
	public void add(Entity o){
		inventory.add(o);
		Collections.sort(inventory);
		//System.out.println("added");
	}

	public void remove(Entity o){
		inventory.remove(o);
	}
	
	public void getQuantity(Entity o){
		
	}
	
	public ArrayList<Entity> getAll(){
		return inventory;
	}
}
