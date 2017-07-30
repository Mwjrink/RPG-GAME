/* Max Rink
 * ICS4U
 * June 1 2016
 * State.java
 * 
 */

import java.awt.Graphics;

public abstract class State {
	
	public abstract void tick();
	
	public abstract void render(Graphics g);
	
}
