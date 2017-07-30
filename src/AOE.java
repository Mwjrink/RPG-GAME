/* Max Rink
 * ICS4U
 * June 1 2016
 * AOE.java
 * 
 */
import java.awt.*;
import java.awt.image.BufferedImage;

public class AOE extends Entity{
	
	private BufferedImage abilit;
	private int size;
	private int x, y;
	private int count;
	private int i;
	private int offset;
	private int aoeDamage = 9;
	private Entity source;
	private boolean bujak;
	
	public AOE(float x, float y, int width, int height, boolean bujak, Entity source) {
		super(x, y, width, height);
		this.x = (int)x;
		this.y = (int)y;
		this.count = 0;
		this.source = source;
		this.bujak = bujak;
		size=0;
		abilit = ImageLoader.loadImage("/CharacterSprites/Player/AOE.png");
		// TODO Auto-generated constructor stub
	}

	@Override
	public int compareTo(Object arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	void render(Graphics g){
		if(bujak){
			offset = (size/5)*2;
			g.setColor(Color.red);
			g.fillOval((int)(x-offset-Handler.getCamera().getxOffset()), (int)(y-offset-Handler.getCamera().getyOffset()), size, size);
			g.drawImage(abilit, (int)(x-offset-Handler.getCamera().getxOffset()), (int)(y-offset-Handler.getCamera().getyOffset()), size, size, null);
			tick();
			size += 10;
		} else {
			offset = (size/5)*2;
			g.drawImage(abilit, x-offset, y-offset, size, size, null);
			size += 10;
			tick();
		}
	}

	@Override
	void tick() {
		if(bujak){
			if(size >= 120){
				for(Entity e : Handler.getWorld().getEntityManager().getEntities()) {
					if(e.equals(source)){
						continue;
					}
					float dist = (float)Math.sqrt(Math.pow((e.getX()+e.getWidth()/2-x-Handler.getCamera().getxOffset()), 2) + Math.pow((e.getY()+e.getHeight()/2-y-Handler.getCamera().getyOffset()), 2));
//					System.out.println(dist);
					if(dist <= 240){
						e.damage(aoeDamage);
					}
				}

			}
			return;
		}
		if(size >= 120){
			for(Entity e : Handler.getWorld().getEntityManager().getEntities()) {
				if(e.equals(source)){
					continue;
				}
				float dist = (float)Math.sqrt(Math.pow((e.getX()+e.getWidth()/2-x-Handler.getCamera().getxOffset()), 2) + Math.pow((e.getY()+e.getHeight()/2-y-Handler.getCamera().getyOffset()), 2));
//				System.out.println(dist);
				if(dist <= 160){
					e.damage(aoeDamage);
				}
			}
		}
	}

	@Override
	BufferedImage getProfile() {
		return abilit;
	}

	@Override
	void damage(int damage) {
		// TODO Auto-generated method stub
		
	}

	@Override
	String getText() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean animation(int anim) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getText(int i) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
