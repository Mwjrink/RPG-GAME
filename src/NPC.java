/* Max Rink
 * ICS4U
 * June 1 2016
 * NPC.java
 * 
 */
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class NPC extends Creature{

	private int health = 15, totalHealth = 15;
	private double speed = 2;
	private int cdx, cdy, dirx, diry;
	private boolean aggro = false;
	private int damage = 5;
	private int cooldown;
	public NPC(float x, float y, int width, int height) {
		super(x, y, width, height);
		
		bounds.x = (int)(width*0.2);
		bounds.y = (int)(height*0.1);
		bounds.width = (int)(width*0.6);
		bounds.height = (int)(height*0.8);
		collides = true;
	}

	@Override
	public void damage(int damage) {
		health -= damage;
		
	}
	@Override
	BufferedImage getProfile() {
		return Assets.getNpc1Down()[0];
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
		if(health < totalHealth){
			aggro=true;
		}
		if(health <= 0){
			Handler.getWorld().getEntityManager().destroyEntity(this);
			Handler.getWorld().getEntityManager().getEntities().add(new RockItem(x, y, width/2, height/2));
		}
//		if(cd > 0){
//			cd-=1;
//		}
//		if(cdx <= 0 && cdy <= 0 && cd <= 0 && !aggro){
//			rand = (int)(Math.random()*4+1);
//			//System.out.println(rand);
//			switch(rand){
//			case 1:
//				dirx = -1;
//				cdx = (int)(Handler.getTilewidth()/speed);
//				break;
//			case 2:
//				dirx = 1;
//				cdx = (int)(Handler.getTilewidth()/speed);
//				break;
//			case 3:
//				diry = -1;
//				cdy = (int)(Handler.getTilewidth()/speed);
//				break;
//			case 4:
//				diry = 1;
//				cdy = (int)(Handler.getTilewidth()/speed);
//				break;
//			}
//		}
		if(aggro){
			xMotion += (Math.abs(x-Handler.getPlayer().getX())/(x-Handler.getPlayer().getX()))*speed;
			//System.out.println(xMotion);
			//System.out.println((Math.abs(x-Handler.getPlayer().getX())/(x-Handler.getPlayer().getX())));
			yMotion += (Math.abs(y-Handler.getPlayer().getY())/(y-Handler.getPlayer().getY()))*speed;
			//System.out.println(xMotion);
		}
		//System.out.println(x + " " + y);
		moveOneX();
		moveOneY();
		move();
	}
	
	public void moveOneX(){
		if(cdx >= 0){
			xMotion += speed*dirx;
			//cdx-=1;
			//if(cdx <= 0){
				//cd = 90;
			//}
				
		}
	}
	
	public void moveOneY(){
		if(cdy >= 0){
			yMotion += speed*diry;
			//cdy-=1;
			//if(cdy <= 0){
				//cd = 90;
			//}
		}
	}
	
	public void render(Graphics g){
		//g.drawImage(Assets.getNPC(), (int) (x - Handler.getCamera().getxOffset()), (int) (y - Handler.getCamera().getyOffset()), width, height, null);
		//g.drawOval((int)(x - Handler.getCamera().getxOffset()-range+width/2), (int)(y - Handler.getCamera().getyOffset()-range+height/2), range*2, range*2);
		//g.(arg0, arg1, arg2, arg3);
	}

}
