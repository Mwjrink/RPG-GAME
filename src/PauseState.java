/* Max Rink
 * ICS4U
 * June 1 2016
 * PauseState.java
 * 
 */
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JButton;

public class PauseState extends State{


	private Image pauseBKG;
	
	PauseState(){
		pauseBKG = ImageLoader.loadImage("/Menu.png");
	}
	
	@Override
	public void tick() {
		Handler.getKeyManager().tick();
		
		if (Handler.getKeyManager().f1)
			StateManager.setCurrent(Handler.getGameState(), 7);
		
		if(Handler.getMouseManager().isLeftPressed()){
			
		}
	}

	@Override
	public void render(Graphics g) {
		
		Handler.getGameState().render(g);
		
		g.drawImage(pauseBKG, (Handler.getW()-(int)(Handler.getW()*0.75))/2, (Handler.getH()-(int)(Handler.getH()*0.75))/2, (int)(Handler.getW()*0.75), (int)(Handler.getH()*0.75), null);
		g.setColor(Color.white);
		g.setFont(new Font("Arial", Font.PLAIN, 20));
		g.drawString("RESUME", Handler.getW() / 2 - 63, Handler.getH() / 3);
		
	}

}
