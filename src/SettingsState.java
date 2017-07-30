/* Max Rink
 * ICS4U
 * June 1 2016
 * SettingsState.java
 * 
 */

import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

//@SuppressWarnings("all")
public class SettingsState extends State implements ActionListener, ChangeListener{
	
	private Display display;
	private final int WIDTH = 640, HEIGHT = 480;
	private JComboBox dropDown;
	private String[] labelStrings = {"     Resolution", "     Mute Sound", "     Mute Music", "     Music Volume", "     Sound Volume"};
	private JLabel resLabel, soundLabel, musicLabel, soundVolumeLabel, musicVolumeLabel;
	private JSlider soundSlider, musicSlider;
	private String[] resolutions = {"1024x576", "1152x648", "1280x720", "1366x768", "1600x900", "1920x1080", "2560x1440", "3840x2160"};
	private int[][] resolutionsReal = {{1024, 1152, 1280, 1366, 1600, 1920, 2560, 3840}, {576, 648, 720, 768, 900, 1080, 1440, 2160}};
	private int selected;
	private JCheckBox checkMusic, checkSound;
	private JButton apply;
//	private Graphics g;
	//private KeyManager keyManager;
	
	
	public Display getDisplay() {
		return display;
	}


	SettingsState(){
		
		//keyManager = new KeyManager();
		display = new Display(
				WIDTH, HEIGHT, "SETTINGS", false, JFrame.HIDE_ON_CLOSE);
		//Handler.getDisplay().getFrame().setVisible(false);
		//display.getFrame().setLayout(new FlowLayout());
		display.getFrame().setLayout(new GridLayout(0, 2, 300, 100));
		display.getFrame().setSize(WIDTH, HEIGHT);
		
		resLabel = new JLabel(labelStrings[0]);
		resLabel.setVerticalAlignment(JLabel.CENTER);
		resLabel.setHorizontalAlignment(JLabel.LEFT);
		
		soundLabel = new JLabel(labelStrings[1]);
		soundLabel.setVerticalAlignment(JLabel.CENTER);
		soundLabel.setHorizontalAlignment(JLabel.LEFT);
		
		musicLabel = new JLabel(labelStrings[2]);
		musicLabel.setVerticalAlignment(JLabel.CENTER);
		musicLabel.setHorizontalAlignment(JLabel.LEFT);
		
		musicVolumeLabel = new JLabel(labelStrings[3]);
		musicVolumeLabel.setVerticalAlignment(JLabel.CENTER);
		musicVolumeLabel.setHorizontalAlignment(JLabel.LEFT);
		
		soundVolumeLabel = new JLabel(labelStrings[4]);
		soundVolumeLabel.setVerticalAlignment(JLabel.CENTER);
		soundVolumeLabel.setHorizontalAlignment(JLabel.LEFT);
		
		checkSound = new JCheckBox();
		checkSound.setVerticalAlignment(JLabel.CENTER);
		checkSound.setHorizontalAlignment(JLabel.CENTER);
		checkSound.setActionCommand("muteSound");
		checkSound.addActionListener(this);
		
		checkMusic = new JCheckBox();
		checkMusic.setVerticalAlignment(JLabel.CENTER);
		checkMusic.setHorizontalAlignment(JLabel.CENTER);
		checkMusic.setActionCommand("muteMusic");
		checkMusic.addActionListener(this);
		
		musicSlider = new JSlider();
		musicSlider.setMaximum(100);
		musicSlider.setMinimum(0);
		musicSlider.setValue(Handler.getMusicVolume());
		musicSlider.addChangeListener(this);
		
		soundSlider = new JSlider();
		soundSlider.setMaximum(100);
		soundSlider.setMinimum(0);
		soundSlider.setValue(Handler.getSoundVolume());
		soundSlider.addChangeListener(this);
		
		apply = new JButton("Apply");
		apply.setVerticalAlignment(JLabel.CENTER);
		apply.setHorizontalAlignment(JLabel.CENTER);
		
		dropDown = new JComboBox(resolutions);
		dropDown.setSelectedIndex(2);
		
		dropDown.addItemListener(
				new ItemListener(){
					public void itemStateChanged(ItemEvent e){
						if(e.getStateChange() == ItemEvent.SELECTED)
							selected = dropDown.getSelectedIndex();
					}
				});
		
		
		display.getFrame().add(new JLabel(" "));
		display.getFrame().add(new JLabel(" "));
		display.getFrame().add(resLabel);
		display.getFrame().add(dropDown);
		display.getFrame().add(soundLabel);
		display.getFrame().add(checkSound);
		display.getFrame().add(musicLabel);
		display.getFrame().add(checkMusic);
		display.getFrame().add(musicVolumeLabel);
		display.getFrame().add(musicSlider);
		display.getFrame().add(soundVolumeLabel);
		display.getFrame().add(soundSlider);
		display.getFrame().add(new JLabel(" "));
		display.getFrame().add(apply);
		//display.getFrame().addKeyListener(keyManager);
		//display.getCanvas().setEnabled(false);
		//System.out.println(musicSlider.getValue());

		display.getFrame().setVisible(true);
		display.getFrame().pack();
		display.center();

		
	}
	
	void show(){
		display.getFrame().setVisible(true);
	}
	
	void hide(){
		display.getFrame().dispatchEvent(
				new WindowEvent(
						display.getFrame(), WindowEvent.WINDOW_CLOSING));
	}

	@Override
	public void tick() {
//		if(keyManager.esc){
//			Handler.getDisplay().getFrame().setVisible(true);
//			display.getFrame().dispatchEvent(
		//new WindowEvent(display.getFrame(), 
				//WindowEvent.WINDOW_CLOSING));
//		}
		
		display.getFrame().addWindowListener(new WindowAdapter() {
	        public void windowClosing(WindowEvent e) {
	        	StateManager.setCurrent(Handler.getMenu(), 5);
	        	//Handler.getDisplay().getFrame().setVisible(true);
	        	//Handler.getDisplay().getCanvas().setVisible(true);
	        }

	    });
		//System.out.println("Setting state tick");
		
		if(apply.getModel().isPressed()){
			Handler.setMusic(checkMusic.isSelected());
			Handler.setSound(checkSound.isSelected());
//			Handler.getDisplay().getFrame().dispose();
//			Display dis = new Display(
//					resolutionsReal[0][dropDown.getSelectedIndex()], 
//					resolutionsReal[1][dropDown.getSelectedIndex()], 
//					Handler.getTitle(), 
//					true, JFrame.EXIT_ON_CLOSE);
//			Handler.setDisplay(dis);
			Handler.setW(resolutionsReal[0][dropDown.getSelectedIndex()]);
			Handler.setH(resolutionsReal[1][dropDown.getSelectedIndex()]);
//			Handler.getDisplay().setSize();
//			Handler.getDisplay().center();
			
			Handler.getGame().setDisplaySize(
					resolutionsReal[0][dropDown.getSelectedIndex()], 
					resolutionsReal[1][dropDown.getSelectedIndex()]);
			
			System.out.println("applied");
			
			StateManager.setCurrent(Handler.getMenu(), 5);
			display.getFrame().dispatchEvent(
					new WindowEvent(
							Handler.getDisplay().getFrame(), 
							WindowEvent.WINDOW_CLOSING));
		}
		
	}


	@Override
	public void render(Graphics g) {
//		if(g == null)
//			this.g = g;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("muteMusic")){
			if(checkMusic.isSelected()){
				Handler.getMusicClass().setMute(true);
			} else {
				Handler.getMusicClass().setMute(false);
			}
		}
		
	}


	@Override
	public void stateChanged(ChangeEvent e) {
		Handler.setMusicVolume(musicSlider.getValue());
		Handler.setSoundVolume(soundSlider.getValue());
		Handler.getMusicClass().volume();
	}
	
}
