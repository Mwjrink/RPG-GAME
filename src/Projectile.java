/* Max Rink
 * ICS4U
 * June 1 2016
 * Projectile.java
 * 
 */
//import jdk.internal.org.objectweb.asm.Handle;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Projectile extends Entity {

	protected float xMotion, yMotion;
	private int speed=2, direction, index;
	private boolean visible, delete;
	private float startX, startY, endX, endY, xinc, yinc;
	private Entity source;
	private float o, a, h, inc;


	Projectile(float startX, float startY, float endX, float endY, int width, int height, int index, Entity source) {
		super(startX, startY, width, height);
		super.literate = false;
		this.endX = endX;
		this.startX = startX;
		this.endY = endY;
		this.startY = startY;
		this.index=index;
		this.source = source;
		
		
		
		o = endX - startX;
		a = endY - startY;
		h = (float)(Math.sqrt(Math.pow(o, 2) + Math.pow(a, 2)));
		inc = h/speed;
		xinc = o/inc;
		yinc = a/inc;
//		if(Math.abs(endX-startX)>Math.abs(endY-startY)){
//			if(endX-startX > 0) {
//				direction=1;
//			} else {
//				direction=3;
//			}
//		} else {
//			if(endY-startY > 0) {
//				direction=2;
//			} else {
//				direction=4;
//			}
//		}
		visible = true;
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect((int)(getX() - Handler.getCamera().getxOffset()), (int)(getY() - Handler.getCamera().getyOffset()), width, height);
	}

	@Override
	public void tick() {
		
//		switch(direction){
//		case 1:
//			//System.out.println("up");
//			xMotion += speed;
//			//x += speed;
//			break;
//		case 2:
//			//System.out.println("right");
//			yMotion += speed;
//			//y += speed;
//			break;
//		case 3:
//			//System.out.println("down");
//			xMotion -= speed;
//			//x -= speed;
//			break;
//		case 4:
//			//System.out.println("left");
//			yMotion -= speed;
//			//y -= speed;
//			break;
//		}
		
		xMotion += xinc;
		yMotion += yinc;
		
		if(delete){
			destroy();
		}
		move();
	}

	@Override
	BufferedImage getProfile() {
		return Assets.getRock();
	}

	@Override
	public boolean checkEntityCollisions(float xOffset, float yOffset){
		for(Entity e : Handler.getWorld().getEntityManager().getEntities()){
			if(e.equals(this) || e.equals(source))
				continue;
			if(e.isCollides() && e.getCollisionBounds(0f, 0f).intersects(getCollisionBounds(xOffset, yOffset))){
				if(Handler.isCheat()){
					e.damage(5);
				} else {
					e.damage(3);
				}
				//destroy();
				return true;
			}
		}
		return false;
	}
	
	protected void move() {
		if (!checkEntityCollisions(xMotion, 0f))
			moveX();
		else
			destroy();
		
		if (!checkEntityCollisions(0f, yMotion))
			moveY();
		else
			destroy();
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	@Override
	void damage(int damage) {
		// TODO Auto-generated method stub
		
	}

	public void moveX() {
		if (xMotion > 0) {
			// System.out.println("right");
			int tempX = (int) (x + xMotion + bounds.width + bounds.x) / Handler.getTilewidth();

			if (!willCollide(tempX, ((int) (y + bounds.y) / Handler.getTilewidth()))
					&& !willCollide(tempX, ((int) (y + bounds.y + bounds.height) / Handler.getTilewidth()))
					//&& (x+xMotion < Handler.getCamera().getxOffset()+Handler.getW())
					//&& (x-xMotion > Handler.getCamera().getxOffset())
					) {
				if(x > Handler.getCamera().getxOffset()+Handler.getW()){
						Handler.getPlayer().remove(index);
					} else {
						x += xMotion;
					}
			} else { // System.out.println("will collide");
				x = tempX * Handler.getTilewidth() - bounds.x - bounds.width - 1;
				delete = true;
				//Handler.getPlayer().remove(index);
			}
		} else if (xMotion < 0) {
			// System.out.println("left");
			int tempX = (int) (x + xMotion + bounds.x) / Handler.getTilewidth();

			if (!willCollide(tempX, ((int) (y + bounds.y) / Handler.getTilewidth()))
					&& !willCollide(tempX, ((int) (y + bounds.y + bounds.height) / Handler.getTilewidth()))) {
				if((x > Handler.getCamera().getxOffset()+Handler.getW())
						|| (x < Handler.getCamera().getxOffset())){
						Handler.getPlayer().remove(index);
					} else {
						x += xMotion;
					}
			} else { // System.out.println("will collide");
				//Handler.getPlayer().remove(index);
				x = (tempX + 1) * Handler.getTilewidth() - bounds.x;
				delete = true;
				//Handler.getPlayer();//.remove(index);
			}
		}
		
		

	}

	public void moveY() {
		if (yMotion > 0) {
			// System.out.println("down");
			int tempY = (int) (y + yMotion + bounds.y + bounds.height) / Handler.getTilewidth();

			if (!willCollide(((int) (x + bounds.x) / Handler.getTilewidth()), tempY)
					&& !willCollide(((int) (x + bounds.x + bounds.width) / Handler.getTilewidth()), tempY)) {
				if(y > Handler.getCamera().getyOffset()+Handler.getH()){
						Handler.getPlayer().remove(index);
					} else {
						y += yMotion;
					}
				
			} else { // System.out.println("will collide");
				//Handler.getPlayer().remove(index);
				y = tempY * Handler.getTilewidth() - height - 1;
				delete = true;
				//Handler.getPlayer().remove(index);
			}
		} else if (yMotion < 0) {
			// System.out.println("up");
			int tempY = (int) (y + yMotion + bounds.y) / Handler.getTilewidth();
			// System.out.print(tempY);
			if (!willCollide(((int) (x + bounds.x) / Handler.getTilewidth()), tempY)
					&& !willCollide(((int) (x + bounds.x + bounds.width) / Handler.getTilewidth()), tempY)) {
				if((y < Handler.getCamera().getyOffset())){
						Handler.getPlayer().remove(index);
					} else {
						y += yMotion;
					}
			} else { // System.out.println("will collide");
				//Handler.getPlayer().remove(index);
				y = (tempY + 1) * Handler.getTilewidth() - bounds.y;// + 1;
				delete = true;
				//Handler.getPlayer().remove(index);
			}
		}

	}
	
	protected boolean willCollide(int xx, int yy) {
		// System.out.println(xx + " " + yy);
		return Handler.getWorld().isSolid(xx, yy);
	}

	public void setIndex(int index) {
		this.index = index;
	}
	
	public void destroy(){
		Handler.getPlayer().remove(index);
	}

	@Override
	public int compareTo(Object arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	String getText() {
		return ("projectile");
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
