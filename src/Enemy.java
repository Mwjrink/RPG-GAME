/* Max Rink
 * ICS4U
 * June 1 2016
 * Enemy.java
 * 
 */
import java.awt.Graphics;

public abstract class Enemy extends Creature{

	protected int health;
	protected int armor;
	protected int damage;
	
	public Enemy(float x, float y, int width, int height) {
		super(x, y, width, height);
		
	}
	
	@Override
	abstract public void damage(int damage);
	
	abstract public void tick();
	
	abstract public void render(Graphics g);
}
