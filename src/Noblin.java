/* Max Rink
 * ICS4U
 * June 1 2016
 * Noblin.java
 * 
 */
//import jdk.internal.org.objectweb.asm.Handle;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Noblin extends Enemy{

	private int health = 15;
	private double speed = 3;
	private int cdx, cdy, dirx, diry, cd;
	private int rand, range=200;
	private boolean aggro = false;
	private int damage = 1;
	private int cooldown;
	private int width, height;
	private int index;
	public Noblin(float x, float y, int width, int height, int index) {
		super(x*Handler.getTILEWIDTH(), y*Handler.getTILEWIDTH(), width, height = (index == 11 || index == 12 || index == 13) ? width*2 : width);

//		health = (int)(Math.log10(width)/Math.log10(2));
//		speed = 25/health;
		health=(int)((double)((Math.random()*24)+5));
		this.width = width;
		this.height = height;
		this.index = index;

//		System.out.println("in the noblin constructor, health is: " + health);
		
		bounds.x = (int)(width*0.2);
		bounds.y = (int)(height*0.1);
		bounds.width = (int)(width*0.6);
		bounds.height = (int)(height*0.8);
		range = (int)Math.sqrt(width*width + height*height)*3;
		collides = true;
	}

	@Override
	public void damage(int damage) {
		health -= damage;
		
	}

	@Override
	public String getText(int i) {
		return null;
	}

	//public void move(){
		
	//}
	
	public void tick(){
		xMotion = 0;
		yMotion = 0;
		if(cooldown > 0)
			cooldown--;
		if(Math.sqrt(Math.pow(((int)(Handler.getPlayer().getX()-x-width/2)), 2) + Math.pow(((int)(Handler.getPlayer().getY()-y-height/2)), 2)) <= range){
			aggro=true;
		}
		if(health <= 0){
			Handler.getWorld().killNoblin();
			Handler.getWorld().getEntityManager().addEntity(new RockItem(x+width/2, y+height/2, 16, 16));
//			if(width >= 16){
////				System.out.println(width);
////				Handler.getWorld().getEntityManager().addEntity(new Noblin(x, y, width/2, height/2));
////				Handler.getWorld().getEntityManager().addEntity(new Noblin(x+width, y+height, width/2, height/2));
////				Handler.getWorld().getEntityManager().getEntities().add(new Noblin(x, y, width/2, height/2));
////				Handler.getWorld().getEntityManager().getEntities().add(new Noblin(x+width, y+height, width/2, height/2));
//			}
			Handler.getWorld().getEntityManager().destroyEntity(this);
		}
		if(cd > 0){
			cd-=1;
		}
		if(cdx <= 0 && cdy <= 0 && cd <= 0 && !aggro){
			rand = (int)(Math.random()*4+1);
			//System.out.println(rand);
			switch(rand){
			case 1:
				dirx = -1;
				cdx = (int)(Handler.getTilewidth()/speed);
				break;
			case 2:
				dirx = 1;
				cdx = (int)(Handler.getTilewidth()/speed);
				break;
			case 3:
				diry = -1;
				cdy = (int)(Handler.getTilewidth()/speed);
				break;
			case 4:
				diry = 1;
				cdy = (int)(Handler.getTilewidth()/speed);
				break;
			}
		}
		if(aggro){
			xMotion -= (Math.abs(x+width/2-Handler.getPlayer().getX())/(x+width/2-Handler.getPlayer().getX()))*speed;
			//System.out.println(xMotion);
			//System.out.println((Math.abs(x-Handler.getPlayer().getX())/(x-Handler.getPlayer().getX())));
			yMotion -= (Math.abs(y+height/2-Handler.getPlayer().getY())/(y+height/2-Handler.getPlayer().getY()))*speed;
			//System.out.println(xMotion);
		}
		//System.out.println(x + " " + y);
		moveOneX();
		moveOneY();
		move();
	}

	@Override
	BufferedImage getProfile() {
		return Assets.getEnemies((int)(Math.random()*20));
	}

	@Override
	public boolean checkEntityCollisions(float xOffset, float yOffset){
		//System.out.println("YES");
		for(Entity e : Handler.getWorld().getEntityManager().getEntities()){
			if(e.equals(this))
				continue;
			if(e.getCollisionBounds(0f, 0f).intersects(getCollisionBounds(xOffset, yOffset))){
				if(e.isCollectible()){
					return false;
				}
				if(e.equals(Handler.getPlayer()) && cooldown<=0){
					Handler.getPlayer().damage(damage);
					cooldown = 60;
				} 
				return true;
			}
		}
		return false;
	}
	
	
	public void moveOneX(){
		if(cdx >= 0){
			xMotion += speed*dirx;
			cdx-=1;
			if(cdx <= 0){
				cd = 90;
			}
				
		}
	}
	
	public void moveOneY(){
		if(cdy >= 0){
			yMotion += speed*diry;
			cdy-=1;
			if(cdy <= 0){
				cd = 90;
			}
		}
	}
	
	public void render(Graphics g){
		g.drawImage(Assets.getEnemies(index), (int) (x - Handler.getCamera().getxOffset()), (int) (y - Handler.getCamera().getyOffset()), width, height, null);
		//g.drawOval((int)(x - Handler.getCamera().getxOffset()-range+width/2), (int)(y - Handler.getCamera().getyOffset()-range+height/2), range*2, range*2);
		//g.(arg0, arg1, arg2, arg3);
	}

}
