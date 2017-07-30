/* Max Rink
 * ICS4U
 * June 1 2016
 * Entity.java
 * 
 */
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public abstract class Entity implements Comparable<Object> {
	
	protected float x, y;
	protected int width, height;
	protected Rectangle bounds;
	protected Polygon boundsPoly;
	protected boolean collides;
	protected boolean collectible, literate;
	protected int health;
	//protected Quadtree quadtree;
	
	public Entity(float x, float y, int width, int height){
		literate = false;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		bounds = new Rectangle(0, 0, width, height);
		
		//quadtree = new Quadtree(0, new Rectangle(0, 0, Handler.getW(), Handler.getH()));
	}
	
	abstract void render(Graphics g);
	
	abstract void tick();

	abstract BufferedImage getProfile();
	
	public Rectangle getCollisionBounds(float xOffset, float yOffset){
		return new Rectangle(
				(int) (x + bounds.x + xOffset), 
				(int) (y + bounds.y + yOffset), 
				bounds.width, bounds.height);
	}
	
	public boolean checkEntityCollisions(float xOffset, float yOffset){
		for(Entity e : Handler.getWorld().getEntityManager().getEntities()){
			if(e.equals(this))
				continue;
			if(e.getCollisionBounds(0f, 0f).intersects(getCollisionBounds(xOffset, yOffset))){
				if(e.collectible){
					return false;
				}
				return true;
			}
		}
		return false;
	}
	
	public boolean checkEntityCollisions(float xOffset, float yOffset, boolean source){
		for(Entity e : Handler.getWorld().getEntityManager().getEntities()){
			if(e.equals(this))
				continue;
			if(e.getCollisionBounds(0f, 0f).intersects(getCollisionBounds(xOffset, yOffset))){
				//System.out.println("entity collision");
				if(e.isCollectible() && source){
					//System.out.println("pick the damn rock up bitch");
//					Handler.getPlayer().getInv().add(e);
					Handler.getPlayer().damage(-1);
					Handler.getWorld().getEntityManager().destroyEntity(e);
					return false;
				} else if (e.isLiterate() && source) {
					StateManager.setCurrent(new TalkingState(e), 8);
					return true;
				}
				if(!e.isCollides())
					continue;
				return true;
			}
		}
		return false;
	}
	
	abstract void damage(int damage);

	public void damage(int damage, boolean trueDamage){
		health -= damage;
	}
	
	abstract String getText();

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}	
	
	public boolean isCollides(){
		return this.collides;
	}
	
	public boolean isCollectible(){
		return this.collectible;
	}

	public final boolean isLiterate() {
		return literate;
	}

	public abstract boolean animation(int anim);

    public abstract String getText(int i);
}
