/* Max Rink
 * ICS4U
 * June 1 2016
 * SneakyDalton.java
 * 
 */
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class SneakyDalton extends Creature {

	private int health = 15, totalHealth = 15;
	private double speed = 2;
	private int cdx, cdy, dirx, diry;
	private boolean aggro = false;
	private int damage = 5;
	private int cooldown;
	private Animation animDown, animUp, animRight, animLeft;
	private int direction;
	

	public SneakyDalton(float x, float y, int width, int height) {
		super(x, y, width, height);
		super.literate = true;
		collides = true;

		bounds.x = 8;
		bounds.y = 10;
		bounds.width = 16;
		bounds.height = 22;

		unkillable = true;

		animDown = new Animation((4), Assets.getNpc3Down());
		animUp = new Animation((4), Assets.getNpc3Up());
		animRight = new Animation((4), Assets.getNpc3Right());
		animLeft = new Animation((4), Assets.getNpc3Left());
	}

	@Override
	public void damage(int damage) {
		if(unkillable && health-damage <= 0){
			return;
		}
		health -= damage;

	}

	@Override
	String getText(){
		return "1 Hello, My name is Sneaky";
		
	}
	
	// public void move(){

	// }

	public void tick() {
		animDown.tick();
		animUp.tick();
		animRight.tick();
		animLeft.tick();
		
		xMotion = 0;
		yMotion = 0;
		if (health < totalHealth) {
			aggro = true;
		}
		if (health <= 0) {
			Handler.getWorld().getEntityManager().destroyEntity(this);
		}
		/*if (cooldown > 0)
			cooldown--;
		if (health < totalHealth) {
			aggro = true;
		}
		if (health <= 0) {
			Handler.getWorld().getEntityManager().destroyEntity(this);
			Handler.getWorld().getEntityManager().getEntities().add(new RockItem(x, y, width / 2, height / 2));
		}
		// if(cd > 0){
		// cd-=1;
		// }
		// if(cdx <= 0 && cdy <= 0 && cd <= 0 && !aggro){
		// rand = (int)(Math.random()*4+1);
		// //System.out.println(rand);
		// switch(rand){
		// case 1:
		// dirx = -1;
		// cdx = (int)(Handler.getTilewidth()/speed);
		// break;
		// case 2:
		// dirx = 1;
		// cdx = (int)(Handler.getTilewidth()/speed);
		// break;
		// case 3:
		// diry = -1;
		// cdy = (int)(Handler.getTilewidth()/speed);
		// break;
		// case 4:
		// diry = 1;
		// cdy = (int)(Handler.getTilewidth()/speed);
		// break;
		// }
		// }
		if (aggro) {
			xMotion += (Math.abs(x - Handler.getPlayer().getX()) / (x - Handler.getPlayer().getX())) * speed;
			// System.out.println(xMotion);
			// System.out.println((Math.abs(x-Handler.getPlayer().getX())/(x-Handler.getPlayer().getX())));
			yMotion += (Math.abs(y - Handler.getPlayer().getY()) / (y - Handler.getPlayer().getY())) * speed;
			// System.out.println(xMotion);
			if (xMotion > 0) {
				direction = 3;
			} else {
				direction = 1;
			}
			if (yMotion > 0) {
				direction = 0;
			} else {
				direction = 2;
			}
		}*/
		
		if (aggro) {
			goToSweatyPost();
		}
		// System.out.println(x + " " + y);
		moveOneX();
		moveOneY();
		move();
	}

	@Override
	BufferedImage getProfile() {
		return animDown.getStill(0);
	}

	@Override
	public boolean animation(int anim){
		aggro = true;
		animDown.tick();
		animUp.tick();
		animRight.tick();
		animLeft.tick();
		xMotion = 0;
		yMotion = 0;

		switch(anim){
		case 1:
			Handler.setSneakyAnim(true);
			return goToSweatyPost();
		case 2:
			Handler.setSneakyAnim(true);
			return enterSweatyPost();
		}
		return false;
	}

	@Override
	public String getText(int i) {
		return null;
	}

	public boolean enterSweatyPost(){
		if(y < 54*Handler.getTilewidth()){
			yMotion += 5;
			moveY();
			return true;
		}
		return false;
	}
	public boolean goToSweatyPost(){
		if(x > Handler.getCamera().getxOffset()+Handler.getW()){
			return false;
		}
		if((x > Handler.getCamera().getxOffset()+Handler.getW())
				|| (x < Handler.getCamera().getxOffset())){
			return false;
		}
		if(y > Handler.getCamera().getyOffset()+Handler.getH()){
			return false;
		}
		if((y < Handler.getCamera().getyOffset())){
			return false;
		}
		if(y < 24*Handler.getTilewidth()){
			yMotion += 5;
			moveY();
			return true;
		} else if (x < 46*Handler.getTilewidth()) {
			xMotion += 5;
			moveX();
			return true;
		}
		return false;
	}
	
	@Override
	public boolean checkEntityCollisions(float xOffset, float yOffset) {
		// System.out.println("YES");
		for (Entity e : Handler.getWorld().getEntityManager().getEntities()) {
			if (e.equals(this))
				continue;
			if (e.getCollisionBounds(0f, 0f).intersects(getCollisionBounds(xOffset, yOffset))) {
				if (e.equals(Handler.getPlayer()) && cooldown <= 0) {
					Handler.getPlayer().damage(damage);
					cooldown = 20;
				}
				return true;
			}
		}
		return false;
	}

	public void moveOneX() {
		if (cdx >= 0) {
			xMotion += speed * dirx;
			// cdx-=1;
			// if(cdx <= 0){
			// cd = 90;
			// }

		}
	}

	public void moveOneY() {
		if (cdy >= 0) {
			yMotion += speed * diry;
			// cdy-=1;
			// if(cdy <= 0){
			// cd = 90;
			// }
		}
	}

	public void render(Graphics g) {
		g.drawImage(getCurrentAnimationFrame(), (int) (x - Handler.getCamera().getxOffset()), (int) (y - Handler.getCamera().getyOffset()), width, height, null);
		// g.drawImage(Assets.getNPC(), (int) (x -
		// Handler.getCamera().getxOffset()), (int) (y -
		// Handler.getCamera().getyOffset()), width, height, null);
		// g.drawOval((int)(x - Handler.getCamera().getxOffset()-range+width/2),
		// (int)(y - Handler.getCamera().getyOffset()-range+height/2), range*2,
		// range*2);
		// g.(arg0, arg1, arg2, arg3);
	}

	private BufferedImage getCurrentAnimationFrame() {
		if (xMotion == 0 && yMotion == 0) {
			switch (direction) {
			case 1:
				return animLeft.getStill(0);
			case 2:
				return animUp.getStill(0);
			case 3:
				return animRight.getStill(0);
			default:
				return animDown.getStill(0);

			}
		}

		if (yMotion < 0) {
			return animUp.getCurrentFrame();
		} else if (xMotion > 0) {
			return animRight.getCurrentFrame();
		} else if (xMotion < 0) {
			return animLeft.getCurrentFrame();
		} else {
			return animDown.getCurrentFrame();
		}
	}

}
