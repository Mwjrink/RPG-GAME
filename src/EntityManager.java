/* Max Rink
 * ICS4U
 * June 1 2016
 * EntityManager.java
 * 
 */
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class EntityManager {
	
	private ArrayList<Entity> entities;
//	private Entity[] ent;
	private Player player;
	
	
	EntityManager(Player player){
//		entities = new ArrayList<Entity>();
		this.player = player;
		
		entities = new ArrayList<Entity>();
		entities.add(player);
	}

	EntityManager(ArrayList<Entity> entities){
		this.entities = entities;
		this.player = (Player)entities.get(0);
	}

	void addEntity(Entity e){
		entities.add(e);
	}

	public void render(Graphics g){
		//java 8
		//entities.sort(Comparator.comparing(Entity::getY));
		
		sortEntities();
		
		for(Entity e : entities){
			
			e.render(g);
		}
		//player.render(g);
	}
	
	public void tick(){
		for(int i=0;i < entities.size();i++){
			Entity e = entities.get(i);
			e.tick();
		}
		//player.tick();
	}
	
	void sortEntities(){
		Collections.sort(entities, new Comparator<Entity>() {
		    @Override
		    public int compare(Entity o1, Entity o2) {
		        //return ((int)o1.getY()).compareTo((int)o2.getY());
		    	return Integer.compare((int)o1.getY(), (int)o2.getY());
		    }
		});
	}

	public Player getPlayer() {
		return player;
	}

	public ArrayList<Entity> getEntities() {
		return entities;
	}

	public void destroyEntity(Entity e){
		entities.remove(e);
	}
	
	public void setEntities(ArrayList<Entity> entities) {
		this.entities = entities;
	}
	
}
