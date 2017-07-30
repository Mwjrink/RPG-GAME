/* Max Rink
 * ICS4U
 * June 1 2016
 * TalkingState.java
 * 
 */
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JButton;

public class TalkingState extends State {

	private Image textBox;
	private Entity en;
	private int fontSize = 20, yCounter, xCounter;
	private boolean post = false;
	private String[] current;
	private int anim;
	// private int spaceCounter;

	TalkingState(Entity en) {
		this.en = en;
		textBox = Assets.getTextBox();
		current = getCurrent();
	}

	TalkingState(Entity en, int i) {
		this.en = en;
		textBox = Assets.getTextBox();
		current = getCurrent(i);
	}

	@Override
	public void tick() {
		Handler.getKeyManager().tick();

		if (Handler.getKeyManager().f1 || Handler.getKeyManager().enter)
			if (post) {
				StateManager.setCurrent(new AnimState(en, anim), 4);
			} else {
				StateManager.setCurrent(Handler.getGameState(), 4);
			}

	}

	@Override
	public void render(Graphics g) {

		Handler.getGameState().render(g);
		// g.drawImage(en.getPortrait(), 0,
		// (int)(Handler.getH()-Handler.getH()/2.88)+19, Handler.getW(),
		// (int)(Handler.getH()/2.88)+19, null);
		g.drawImage(en.getProfile(), 19, (int) (Handler.getH() - Handler.getH() / 2.88) +19, 206, 206, null);
		g.drawImage(textBox, 0, (int) (Handler.getH() - Handler.getH() / 2.88), Handler.getW(),
				(int) (Handler.getH() / 2.88), null);
		g.setColor(Color.white);
		g.setFont(new Font("Arial", Font.PLAIN, fontSize));

		yCounter = 0;
		xCounter = 0;
		// spaceCounter = 0;
		for (int i = 1; i < current.length; i++) {
			if (xCounter + g.getFontMetrics().stringWidth(current[i]) > Handler.getW() / 1.28) {
				yCounter++;
				xCounter = 0;
				// spaceCounter = 0;
			}

			// System.out.println(current.length);
			g.drawString(current[i], (int) (Handler.getW() / 5.12) + xCounter,
					(int) ((Handler.getH() - Handler.getH() / 2.88 + 45) + yCounter * fontSize * 1.5));
			xCounter += g.getFontMetrics().stringWidth(current[i]);
			xCounter += 5;
			// spaceCounter+=4;
		}

	}

	public String[] getCurrent() {
		String[] s = en.getText().split("\\s+");
		anim = Utils.parseInt(s[0]);
		if (anim > 0) {
			post = true;
		}
		return s;
		// return "BLANK. This is Blank. There is no text here. No text is
		// available to display to the screen. At this moment, there is no
		// disposable text assets to borrow for your viewing
		// pleasure.".split("\\s+");
	}

	public String[] getCurrent(int i) {
		String[] s = en.getText(i).split("\\s+");
		anim = Utils.parseInt(s[0]);
		if (anim > 0) {
			post = true;
		}
		return s;
		// return "BLANK. This is Blank. There is no text here. No text is
		// available to display to the screen. At this moment, there is no
		// disposable text assets to borrow for your viewing
		// pleasure.".split("\\s+");
	}

}
