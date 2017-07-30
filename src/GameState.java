/* Max Rink
 * ICS4U
 * June 1 2016
 * GameState.java
 * 
 */

import java.awt.Graphics;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class GameState extends State {

	private World current;
	private map maps;
	//private World house, world;
	private ArrayList<World> worlds;

	// private Player player;

	// private WaterTile waterTile;

	GameState() {
		//worlds.add(new World("res/worlds/worldTest3.txt", Handler.getGame()));
		worlds = new ArrayList<World>();
		maps = new map(7, 11, "res/worlds/TESTSMALLER.tmx");
//		maps = new map(50, 195, "res/worlds/TESTSMALLER.tmx");
		worlds.add(new World(Handler.getGame(), maps));
		current = worlds.get(worlds.size()-1);
		current.setStartXY(7, 11);
		Handler.setWorld(current);
		
		// player = new Player(Handler.getGame(), 0, 0, 32, 32);
		// Handler.setPlayer(player);

	}

	public void tick() {
		// player.tick();
//		System.out.println("GameState tick");
		current.tick();
		
		if (Handler.getKeyManager().esc){
			System.out.println("pause");
			Handler.getMusicClass().stop();
			Handler.getMusicClass().play(0);
			StateManager.setCurrent(Handler.getMenu(), 1);
		}
		if (Handler.getKeyManager().i)
			StateManager.setCurrent(Handler.getInventoryState(), 1);
//		if (Handler.getKeyManager().t)
//			StateManager.setCurrent(Handler.getTalkingState());
		
		maps = current.isDoor();
		if (maps != null) {
			for(World w : worlds){
				if(maps.getPath().equals(w.getPath())){
					current = w;
					current.setCurrent(maps);
//					current = overworld;
					Handler.setWorld(current);
//					System.out.println("Start X: " + maps.getStartX());
//					System.out.println("Start Y: " + maps.getStartY());
//					current.setStartXY(maps.getStartX(), maps.getStartY());
//					Handler.setCamera(new Camera(maps.getStartX(), maps.getStartY()));
//					current.setCamera(Handler.getCamera());
//					Handler.getCamera().centerCameraOnCreature(Handler.getPlayer());
					Handler.getCamera().centerCameraOnCreature(current.getEntityManager().getPlayer());
					current.setStartX(maps.getStartX());
					current.setStartY(maps.getStartY());
					current.reset();
//					System.out.println(w.getPath());
					return;
				}
			}
			current = new World(Handler.getGame(), maps, worlds.get(0).getPlayerStats());
			Handler.setWorld(current);
			current.setStartXY(maps.getStartX(), maps.getStartY());
		}
	}

	public void render(Graphics g) {
		// put this in world

		// g.drawImage(Handler.getWorld().getTile(18, 18).getAsset(), 0, 0, 32,
		// 32, null);

		current.render(g);
	}

	public World getCurrent() {
		return current;
	}

	public void setCurrent(World current) {
		this.current = current;
	}

	public map getMaps() {
		return maps;
	}

	public void setMaps(map maps) {
		this.maps = maps;
	}

	public ArrayList<World> getWorlds() {
		return worlds;
	}

	public void setWorlds(ArrayList<World> worlds) {
		this.worlds = worlds;
	}
}
