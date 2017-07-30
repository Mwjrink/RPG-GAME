/* Max Rink
 * ICS4U
 * June 1 2016
 * Creature.java
 * 
 */
import java.awt.Graphics;

public abstract class Creature extends Entity {

	protected float xMotion, yMotion;
	private int speed;
	protected boolean unkillable;

	public Creature(float x, float y, int width, int height) {
		super(x, y, width, height);

		xMotion = 0;
		yMotion = 0;
	}

	protected void move() {
		if (!checkEntityCollisions(xMotion, 0f))
			moveX();
		if (!checkEntityCollisions(0f, yMotion))
			moveY();
	}
	
	protected void move(boolean source) {
		if (!checkEntityCollisions(xMotion, 0f, source))
			moveX();
		if (!checkEntityCollisions(0f, yMotion, source))
			moveY();
	}

	public void tick() {

	}

	public void damage(int damage){

	}

	public void moveX() {
		if (xMotion > 0) {
			// System.out.println("right");
			int tempX = (int) (x + xMotion + bounds.width + bounds.x) / Handler.getTilewidth();

			if (!willCollide(tempX, ((int) (y + bounds.y) / Handler.getTilewidth()))
					&& !willCollide(tempX, ((int) (y + bounds.y + bounds.height) / Handler.getTilewidth()))) {
				x += xMotion;
			} else { // System.out.println("will collide");
				x = tempX * Handler.getTilewidth() - bounds.x - bounds.width - 1;
			}
		} else if (xMotion < 0) {
			// System.out.println("left");
			int tempX = (int) (x + xMotion + bounds.x) / Handler.getTilewidth();

			if (!willCollide(tempX, ((int) (y + bounds.y) / Handler.getTilewidth()))
					&& !willCollide(tempX, ((int) (y + bounds.y + bounds.height) / Handler.getTilewidth()))) {
				x += xMotion;
			} else { // System.out.println("will collide");
				x = (tempX + 1) * Handler.getTilewidth() - bounds.x;
			}
		}

	}

	public void moveY() {
		if (yMotion > 0) {
			// System.out.println("down");
			int tempY = (int) (y + yMotion + bounds.y + bounds.height) / Handler.getTilewidth();

			if (!willCollide(((int) (x + bounds.x) / Handler.getTilewidth()), tempY)
					&& !willCollide(((int) (x + bounds.x + bounds.width) / Handler.getTilewidth()), tempY)) {
				y += yMotion;
			} else { // System.out.println("will collide");
				y = tempY * Handler.getTilewidth() - height - 1;
			}
		} else if (yMotion < 0) {
			// System.out.println("up");
			int tempY = (int) (y + yMotion + bounds.y) / Handler.getTilewidth();
			// System.out.print(tempY);
			if (!willCollide(((int) (x + bounds.x) / Handler.getTilewidth()), tempY)
					&& !willCollide(((int) (x + bounds.x + bounds.width) / Handler.getTilewidth()), tempY)) {
				y += yMotion;
			} else { // System.out.println("will collide");
				y = (tempY + 1) * Handler.getTilewidth() - bounds.y;// + 1;
			}
		}

	}

	protected boolean willCollide(int xx, int yy) {
		// System.out.println(xx + " " + yy);
		return Handler.getWorld().isSolid(xx, yy);
	}

	public void render(Graphics g) {

	}

	// GETTERS AND SETTERS

	public float getxMotion() {
		return xMotion;
	}

	public void setxMotion(int xMotion) {
		this.xMotion = xMotion;
	}

	public float getyMotion() {
		return yMotion;
	}

	public void setyMotion(int yMotion) {
		this.yMotion = yMotion;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	@Override
	public int compareTo(Object arg0) {
		return 0;
	}

	@Override
	String getText() {
		return "creature";
	}

	@Override
	public boolean animation(int anim) {
		// TODO Auto-generated method stub
		return false;
	}

}
