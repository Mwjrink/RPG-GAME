/* Max Rink
 * ICS4U
 * June 1 2016
 * RockItem.java
 * 
 */
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class RockItem extends StaticEntity{
		
	private int health = 4;
	public RockItem(float x, float y, int width, int height) {
		super(x, y, width, height);
		super.literate = false;
		bounds.x = (int)(width*0.203125+0.5);
		bounds.y = (int)(height*0.234375+0.5);
		bounds.width = (int)(width*0.59375+0.5);
		bounds.height = (int)(height*0.53125+0.5);
		collides = false;
		collectible = true;
	}
	
	public void tick(){
		//System.out.println(index);
		
	}

	@Override
	public void damage(int damage){
		return;
	}
	
	@Override
	void render(Graphics g) {
		g.drawImage(
				Assets.getHearts(4),
				(int) (x - Handler.getCamera().getxOffset()), 
				(int) (y - Handler.getCamera().getyOffset()), 
				width, height, null);
		
	}

	@Override
	public int compareTo(Object arg0) {
		return 0;
	}

	@Override
	String getText() {
		return "Rock Item";
	}

	@Override
	public boolean animation(int anim) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getText(int i) {
		return null;
	}

	public int getHealth() {
		return health;
	}

	@Override
	BufferedImage getProfile() {
		return Assets.getHearts(4);
	}
}
