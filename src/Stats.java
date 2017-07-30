/* Max Rink
 * ICS4U
 * June 1 2016
 * Stats.java
 * 
 */
import java.awt.*;

public class Stats {

	private int health;
	private int armor; // subtract from damage
	private int speed; // 1.2 would be 120% speed
	private int pDamage; // physical
	private int eArmor; // energy armor
	private int eDamage; // energy damage
	private int totalHealth;
	private int barrel = 6;
	private int reloading;
	private float energy, maxEnergy;
	private float energyRegen;

	Stats(int health, int totalHealth, int armor, int speed, int pDamage, int eArmor, int eDamage, int energy, int maxEnergy) {
		this.health = health;
		this.totalHealth = totalHealth;
		this.armor = armor;
		this.speed = speed;
		this.pDamage = pDamage;
		this.eArmor = eArmor;
		this.eDamage = eDamage;
		this.energy = energy;
		this.maxEnergy = maxEnergy;
		energyRegen = (1F/20F);
	}

	public void render(Graphics g) {
		//System.out.println(((double) health / totalHealth));

//		g.setColor(Color.BLACK);
//		g.drawRect(0, 0, Handler.getW(), 9);
//
//		if ((double)((double)health/(double)totalHealth) > 0.5) {
//			g.setColor(Color.GREEN);
//		} else if ((double)((double)health / (double)totalHealth) > 0.25) {
//			g.setColor(Color.yellow);
//		} else {
//			g.setColor(Color.RED);
//		}
//
//		int w = (int) (((double) health / (double) totalHealth) * Handler.getW() - 4);
//		// g.setColor(Color.RED);
//		g.fillRect(2, 2, w, 5);

		int spacing = 32;
		int size = 30;
		int offset = 2;
		
		int full = health/4;
		for(int i=0; i<full; i++){
			g.drawImage(Assets.getHearts(4), i*spacing+offset, 5, size, size, null);
		}
		int fract = health%4;
		switch(fract){
		case 0:
//			System.out.println("0");
			for(int i=0; i<(totalHealth-health)/4; i++){
				g.drawImage(Assets.getHearts(0), full*spacing+i*spacing+offset, 5, size, size, null);
			}
			break;
		case 1:
//			System.out.println("no");
			g.drawImage(Assets.getHearts(1), full*spacing+offset, 5, size, size, null);
			for(int i=0; i<(totalHealth-health)/4; i++){
				g.drawImage(Assets.getHearts(0), full*spacing+spacing+i*spacing+offset, 5, size, size, null);
			}
			break;
		case 2:
//			System.out.println("no");
			g.drawImage(Assets.getHearts(2), full*spacing+offset, 5, size, size, null);
			for(int i=0; i<(totalHealth-health)/4; i++){
				g.drawImage(Assets.getHearts(0), full*spacing+spacing+i*spacing+offset, 5, size, size, null);
			}
			break;
		case 3:
//			System.out.println("yes");
			g.drawImage(Assets.getHearts(3), full*spacing+offset, 5, size, size, null);
			for(int i=0; i<(totalHealth-health)/4; i++){
				g.drawImage(Assets.getHearts(0), full*spacing+spacing+i*spacing+offset, 5, size, size, null);
			}
			break;
		}
		
		
		g.setColor(Color.magenta);
		switch (barrel) {
		case 6:
			g.fillRect(57, 40, 9, 9);
		case 5:
			g.fillRect(46, 40, 9, 9);
		case 4:
			g.fillRect(35, 40, 9, 9);
		case 3:
			g.fillRect(24, 40, 9, 9);
		case 2:
			g.fillRect(13, 40, 9, 9);
		case 1:
			g.fillRect(2, 40, 9, 9);
		}
		
		if (reloading > 0) {
			g.fillRect(2, 38, 72, 12);
			g.setColor(Color.black);
			g.setFont(new Font("Arial", Font.PLAIN, 12));
			g.drawString("RELOADING", 2, 48);
		}
		
        g.setColor(Color.cyan);
        g.drawRect(2, 52, 101, 10);
        g.fillRect(3, 53, (int)(100*(energy/maxEnergy)), 9);

        int c = Handler.getWorld().getCount();
		if(c > 0){
			g.setColor(Color.RED);
			g.setFont(new Font("Arial", Font.PLAIN, 20));
			g.drawString("Noblins Left:  "+c, Handler.getW()-10-g.getFontMetrics().stringWidth("Noblins Left: " + c), 24);
		}

	}

	public void tick() {
		if (getBarrel() == 0) {
			setBarrel(6);
			setReloading(180);
		}
		if (reloading > 0) {
			reloading--;
		}
		if (energy < (float)maxEnergy) {
//			System.out.println(energy);
//			System.out.println(energyRegen);
			energy += energyRegen;
		}
	}

	public int getReloading() {
		return reloading;
	}

	public void setReloading(int reloading) {
		this.reloading = reloading;
	}

	public void shoot() {
		barrel -= 1;
	}

	public int getBarrel() {
		return barrel;
	}

	public void setBarrel(int barrel) {
		this.barrel = barrel;
	}

	public int getTotalHealth() {
		return totalHealth;
	}

	public void setTotalHealth(int totalHealth) {
		this.totalHealth = totalHealth;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getArmor() {
		return armor;
	}

	public void setArmor(int armor) {
		this.armor = armor;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getpDamage() {
		return pDamage;
	}

	public void setpDamage(int pDamage) {
		this.pDamage = pDamage;
	}

	public int geteArmor() {
		return eArmor;
	}

	public void seteArmor(int eArmor) {
		this.eArmor = eArmor;
	}

	public int geteDamage() {
		return eDamage;
	}

	public void seteDamage(int eDamage) {
		this.eDamage = eDamage;
	}

	public void damage(int damage) {
		health -= damage;
	}

	public boolean useEnergy(int i){
//		System.out.println(i);
		if(i<=energy){
			energy -= i;
			return true;
		}
		return false;
	}

	public float getEnergy(){
		return energy;
	}

	public void reload(){
		if(reloading <= 0) {
			setReloading(30 * (6 - barrel));
			setBarrel(6);

		}
//		setReloading(180);
	}
}
