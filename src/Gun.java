/* Max Rink
 * ICS4U
 * June 1 2016
 * Gun.java
 * 
 */
import java.util.ArrayList;

public class Gun {
	
	private ArrayList<Projectile> bullets;
	private int barrelSize; //number of bullets
	private int reloadSpeed; //1.2 would be 120%
	private int minDamage, maxDamage;
	
	
	Gun(int barrelSize, int reloadSpeed){
		this.barrelSize = barrelSize;
		this.reloadSpeed = reloadSpeed;
		this.bullets = new ArrayList<Projectile>();
		
	}
	
	void shoot(){
		
	}
	
}
