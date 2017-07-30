/* Max Rink
 * ICS4U
 * June 1 2016
 * Musical.java
 * 
 */
import java.io.*;
import javax.sound.sampled.*;

public class Musical {
	
	private File[] music;
	private AudioInputStream stream;
	private AudioFormat format;
	private DataLine.Info info;
	private Clip clip;
	private int song = 2;
	private int index;
	private boolean mute;

	private FloatControl gainControl;

	private boolean isMusic = true;

	public Musical(){
		init();
	}
	
	public boolean isMusic() {
		return isMusic;
	}

	public void setMusic(boolean isMusic) {
		this.isMusic = isMusic;
	}
	
	public void init(){
		music = new File[4];
		music[0] = new File("res/Music/Intro.wav");
		music[1] = new File("res/Music/Blue_Moon.wav");
		music[2] = new File("res/Music/Wakeful_Thunder.wav");
		music[3] = new File("res/Music/Combat.wav");
	}

	public void tick() {
//		System.out.println(Handler.getMusicVolume());
		if(!clip.isActive() && !mute){
			switch(index){
			case 0:
				play(0);
				break;
			case 1:
				play(2);
				break;
			case 2:
				play(1);
				break;
			case 3:
				play(3);
				break;
			default:
				play(1);
				break;
			}
		}
	}

	public int getCurrent() {
		return 0;
	}

	public void play(int index){
		if(mute){
			return;
		}
		this.index = index;
		if (clip == null || !clip.isActive()) {
			try {
				stream = AudioSystem.getAudioInputStream(music[index]);
				clip = (Clip) AudioSystem.getLine(new DataLine.Info(Clip.class, stream.getFormat()));
				clip.open(stream);
				gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
				clip.start();
				volume();
			} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void stop(){
		clip.stop();
	}
	
	public void setMute(boolean mute){
		this.mute = mute;
		if(mute && clip.isActive()){
			clip.stop();
		} else if (!clip.isActive()){
			clip.start();
		}
	}
	
	public void volume(){
//		System.out.println(Handler.getMusicVolume());
		float range = gainControl.getMaximum() - gainControl.getMinimum();
		float gain = (range * ((float)Handler.getMusicVolume()/100)) + gainControl.getMinimum();
		gainControl.setValue(gain);

	}
	
}
