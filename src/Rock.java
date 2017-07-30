/* Max Rink
 * ICS4U
 * June 1 2016
 * Rock.java
 * 
 */
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Rock extends StaticEntity{
		
	private int health = 10;
	public Rock(float x, float y, int width, int height) {
		super(x, y, width, height);
		super.literate = false;
		bounds.x = (int)(width*0.203125+0.5);
		bounds.y = (int)(height*0.234375+0.5);
		bounds.width = (int)(width*0.59375+0.5);
		bounds.height = (int)(height*0.53125+0.5);
		collides = true;
	}
	
	public void tick(){
		//System.out.println(index);
		if(health <= 0){
			Handler.getWorld().getEntityManager().destroyEntity(this);
			Handler.getWorld().getEntityManager().getEntities().add(new RockItem(x, y, width/2, height/2));
		
		}
	}
	
	public void damage(int damage){
		health -= damage;
		//System.out.println(health);
	}
	
	@Override
	void render(Graphics g) {
		g.drawImage(
				Assets.getRock(), 
				(int) (x - Handler.getCamera().getxOffset()), 
				(int) (y - Handler.getCamera().getyOffset()), 
				width, height, null);
		
	}
	@Override
	BufferedImage getProfile() {
		return Assets.getRock();
	}
	@Override
	public int compareTo(Object arg0) {
		return 0;
	}

	@Override
	String getText() {
		return "rock";
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

}
