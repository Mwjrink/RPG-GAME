/* Max Rink
 * ICS4U
 * June 1 2016
 * Display.java
 * 
 */

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JComponent;
import javax.swing.JFrame;

public class Display {

	private JFrame frame;
	private Canvas canvas;

	Display(int w, int h, String title, boolean unDecorated, int close) {
		frame = new JFrame("<= The Crusades =>");
		frame.setSize(w, h);
		frame.setLocationRelativeTo(null);
		frame.setUndecorated(unDecorated);
		frame.setDefaultCloseOperation(close);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.addKeyListener(Handler.getKeyManager());
		frame.setIconImage(ImageLoader.loadImage("/icon.png"));

		Dimension d = new Dimension(w, h);

		if (unDecorated) {
			canvas = new Canvas();
			canvas.setMaximumSize(d);
			canvas.setMinimumSize(d);
			canvas.setPreferredSize(d);
			canvas.setFocusable(false);

			frame.add(canvas);
		}
		frame.pack();
		frame.setVisible(true);
	}

	JFrame getFrame() {
		return frame;
	}

	Canvas getCanvas() {
		return canvas;
	}

	void center() {
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dim.width / 2 - frame.getSize().width / 2, dim.height / 2 - frame.getSize().height / 2);
	}

	public void setMaxSize(JComponent jc) {
		Dimension max = jc.getMaximumSize();
		Dimension pref = jc.getPreferredSize();
		max.height = pref.height;
		jc.setMaximumSize(max);
	}
	
//	public void setSize(){
//		frame.setSize(Handler.getW(), Handler.getH());
//		canvas.setSize(Handler.getW(), Handler.getH());
//		frame.pack();
//	}

}
