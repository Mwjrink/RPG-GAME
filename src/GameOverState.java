/* Max Rink
 * ICS4U
 * June 1 2016
 * GameOverState.java
 * 
 */
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.WindowEvent;

import javax.swing.JButton;

public class GameOverState extends State {
	private boolean isWin;

	GameOverState(boolean isWin){
		this.isWin = isWin;
	}

	@Override
	public void tick() {
		Handler.getKeyManager().tick();

		if (Handler.getKeyManager().f2) {
			Handler.getDisplay().getFrame()
					.dispatchEvent(new WindowEvent(Handler.getDisplay().getFrame(), WindowEvent.WINDOW_CLOSING));
		}
		if (Handler.getKeyManager().esc) {
			Handler.getDisplay().getFrame().dispatchEvent(new WindowEvent(Handler.getDisplay().getFrame(), WindowEvent.WINDOW_CLOSING));
		}
		
	}

	@Override
	public void render(Graphics g) {
		if(isWin){
			Handler.getGameState().render(g);
			g.setColor(new Color(64, 64, 64, 216));
			g.fillRect(0, 0, Handler.getW(), Handler.getH());
			g.setColor(Color.red);
			g.setFont(new Font("Arial", Font.PLAIN, Handler.getW()/25));
			g.drawString("GAME OVER", Handler.getW()/2-g.getFontMetrics().stringWidth("GAME OVER")/2, Handler.getH()/3);
			g.drawString("YOU HAVE WON", Handler.getW()/2-g.getFontMetrics().stringWidth("YOU HAVE WON")/2, Handler.getH()/3+50);
			return;
		}
		Handler.getGameState().render(g);
		g.setColor(new Color(64, 64, 64, 216));
		g.fillRect(0, 0, Handler.getW(), Handler.getH());
		g.setColor(Color.red);
		g.setFont(new Font("Arial", Font.PLAIN, Handler.getW()/25));
		g.drawString("GAME OVER", Handler.getW()/2-g.getFontMetrics().stringWidth("GAME OVER")/2, Handler.getH()/3);
		
	}
}
