/* Max Rink
 * ICS4U
 * June 1 2016
 * KeyManager.java
 * 
 */
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener{
	
	private boolean[] keys;
	public boolean w, s, a, d, esc, space, atk, f2, f1, i, enter, one, two, three, r;

	public void setS(boolean s) {
		this.s = s;
	}

	public void setD(boolean d) {
		this.d = d;
	}

	public KeyManager(){
		keys = new boolean[256];
	}
	
	public void tick(){
		w = keys[KeyEvent.VK_W];
		s = keys[KeyEvent.VK_S];
		a = keys[KeyEvent.VK_A];
		d = keys[KeyEvent.VK_D];
		esc = keys[KeyEvent.VK_ESCAPE];
		space = keys[KeyEvent.VK_SPACE];
		atk = keys[KeyEvent.VK_Q];
		f2 = keys[KeyEvent.VK_F2];
		f1 = keys[KeyEvent.VK_F1];
		i = keys[KeyEvent.VK_I];
		enter = keys[KeyEvent.VK_ENTER];
		one = keys[KeyEvent.VK_1];
		two = keys[KeyEvent.VK_2];
		three = keys[KeyEvent.VK_3];
		r = keys[KeyEvent.VK_R];
	}

	@Override
	public void keyPressed(KeyEvent e) {
		try {
			keys[e.getKeyCode()] = true;
		} catch (ArrayIndexOutOfBoundsException a) {
			return;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		try {
			keys[e.getKeyCode()] = false;
		} catch (ArrayIndexOutOfBoundsException a) {
			return;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

}
