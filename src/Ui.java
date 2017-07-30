/* Max Rink
 * ICS4U
 * June 1 2016
 * Ui.java
 * 
 */

import java.awt.Color;
import java.awt.Graphics;

public class Ui {

	private Player focus;
	private int health;
//	private int width, height;
//	private int playerImgWidth, playerImgHeight;
//	private int[] xPoints, yPoints;
//	private int healthPercent;

	Ui(Player focus) {
		this.focus = focus;
//
//		width = (int) (Handler.getW() * 0.15625 + 0.5);
//		height = (int) (Handler.getH() * 0.1388888888889 + 0.5);

//		playerImgWidth = (int) (height * 0.8);
//		playerImgHeight = (int) (height * 0.8);
//		xPoints = new int[4];
//		yPoints = new int[4];
		//init();

	}

	void init(){
		

	}

	void tick() {
		this.health = focus.getPlayerStats().getHealth();
		
		//healthPercent = (int)(((double)focus.getPlayerStats().getHealth()/(double)focus.getPlayerStats().getTotalHealth())*100);
		//System.out.println(healthPercent);
	}

	void render(Graphics g) {
		//System.out.println(healthPercent);
		


	}

}
