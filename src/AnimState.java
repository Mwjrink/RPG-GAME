/* Max Rink
 * ICS4U
 * June 1 2016
 * AnimState.java
 * 
 */
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.WindowEvent;

import javax.swing.JButton;

public class AnimState extends State {

	private Entity en;
	private int anim;
	
	
	AnimState(Entity en, int anim){
		this.en = en;
		this.anim = anim;
	}
	
	@Override
	public void tick() {
		
		if(!en.animation(anim)){
			StateManager.setCurrent(Handler.getGameState(), 3);
		}
		
	}

	@Override
	public void render(Graphics g) {
		Handler.getGameState().render(g);
	}

}
