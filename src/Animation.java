/* Max Rink
 * ICS4U
 * June 1 2016
 * Animation.java
 * 
 */
import java.awt.image.BufferedImage;

public class Animation {
	
	private int speed, index;
	private long lastTime, timer;
	private BufferedImage[] frames;
	
	public Animation(int speed, BufferedImage[] frames){
		this.speed = 250/speed;
		this.frames = frames;
		if(frames == null){
			System.out.print("null frames!");
		}
		index = 0;
		timer = 0;
		lastTime = System.currentTimeMillis();
	}
	
	public void tick(){
		timer += System.currentTimeMillis() - lastTime;
		lastTime = System.currentTimeMillis();
		
		if(timer > speed){
			index++;
			timer = 0;
			if(index >= frames.length)
				index = 0;
		}
	}
	
	public BufferedImage getCurrentFrame(){
		//System.out.println(index);
		return this.frames[index];
	}
	
	public BufferedImage getStill(int i){
		return this.frames[i];
	}

}
