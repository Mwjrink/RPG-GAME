/* Max Rink
 * ICS4U
 * June 1 2016
 * InventoryState.java
 * 
 */
import java.awt.Graphics;

import javax.swing.JButton;

public class InventoryState extends State {

	@Override
	public void tick() {
		Handler.getKeyManager().tick();
		
		if (Handler.getKeyManager().f1)
			StateManager.setCurrent(Handler.getGameState(), 10);
		
	}

	@Override
	public void render(Graphics g) {
		
		
	}

}
