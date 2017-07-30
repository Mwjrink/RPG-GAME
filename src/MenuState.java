/* Max Rink
 * ICS4U
 * June 1 2016
 * MenuState.java
 * 
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.WindowEvent;

import javax.swing.JButton;

public class MenuState extends State {

	private Color settings = new Color(0, 255, 250, 50);
	private Color exit = new Color(255, 0, 0, 50);
	private Color play = new Color(0, 195, 255, 50);
	private Color buttonBackground = new Color(200, 0, 0, 255);

	private int w, h;

	private Image mbkg;
	// private Display display;

	private JButton set, ex, pla;

	// private KeyManager keyManager;
	private SettingsState settingsState;
	private GameState gameState;

	MenuState() {
		this.w = Handler.getW();
		this.h = Handler.getH();
		// this.display = Handler.getDisplay();
		mbkg = ImageLoader.loadImage("/GameStart.png");
		Handler.getMusicClass().play(0);
		// keyManager = new KeyManager();
		// settingsState = new SettingsState();
	}

	public void tick() {
		Handler.getKeyManager().tick();
		this.w = Handler.getW();
		this.h = Handler.getH();
		Handler.getMusicClass().tick();

//		if (Handler.getKeyManager().s) {
//			if (settingsState == null) {
//				settingsState = new SettingsState();
//				Handler.setSettingsState(settingsState);
//			} else {
//				settingsState.show();
//			}
//
//		}

		if (settingsState != null) {
			settingsState.tick();
		}
		
		//System.out.println("menu tick");
		

		if (Handler.getMouseManager().isLeftPressed()) {
			if (Handler.getMouseManager().getMouseX() < w/3) {
				if (settingsState == null) {
					settingsState = new SettingsState();
					Handler.setSettingsState(settingsState);
				} else {
					settingsState.show();
				}
			} else if (Handler.getMouseManager().getMouseX() < w/3*2 && Handler.getMouseManager().getMouseX() > w/3) {
				Handler.getMusicClass().stop();
				Handler.getMusicClass().play(1);
				if (Handler.getGameState() == null) {
					gameState = new GameState();
					Handler.setGameState(gameState);
					StateManager.setCurrent(gameState, 2);
//					Handler.getWorld().printWorld();
					
				} else {
					gameState = Handler.getGameState();
					StateManager.setCurrent(gameState, 2);
				}
			} else if (Handler.getMouseManager().getMouseX() > w/3*2) {
				Handler.getDisplay().getFrame()
				.dispatchEvent(new WindowEvent(
						Handler.getDisplay().getFrame(), 
						WindowEvent.WINDOW_CLOSING));
			}
		}

	}

	// public void setSettingsWindowOpen(boolean settingsWindowOpen) {
	// this.settingsWindowOpen = settingsWindowOpen;
	// }

	public void render(Graphics g) {

		g.drawImage(mbkg, 0, 0, Handler.getW(), Handler.getH(), null);
//		g.drawImage(ImageLoader.loadImage("/TileSheets/Bones_A.png"), 0, 0, Handler.getW()/2, Handler.getH(), null);
//		g.drawImage(ImageLoader.loadImage("/TileSheets/Bones_A.png"), Handler.getW()/2, 0, Handler.getW()/2, Handler.getH(), null);

		g.setColor(settings);
		g.setFont(new Font("Arial", Font.PLAIN, 20));
		g.drawString("SETTINGS", Handler.getW() / 6 - 40, Handler.getH() / 3);
		//g.drawString("[S]", Handler.getW() / 6 - 5, Handler.getH() / 3 + 24);

		g.setColor(play);
		g.setFont(new Font("Arial", Font.PLAIN, 50));
		g.drawString("PLAY", Handler.getW() / 2 - 63, Handler.getH() / 4);
		//g.drawString("[SPACE]", Handler.getW() / 2 - 95, Handler.getH() / 4 + 50);

		g.setColor(exit);
		g.setFont(new Font("Arial", Font.PLAIN, 20));
		g.drawString("EXIT", Handler.getW() / 6 * 5 - 20, Handler.getH() / 3);
		//g.drawString("[ESC]", Handler.getW() / 6 * 5 - 25, Handler.getH() / 3 + 24);

	}
}
