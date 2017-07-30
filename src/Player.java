/* Max Rink
 * ICS4U
 * June 1 2016
 * Player.java
 * 
 */
//import jdk.internal.org.objectweb.asm.Handle;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Player extends Creature {

//	private BufferedImage sprite;
	private int defaultSpeed = 1;
	private Animation animDown, animUp, animRight, animLeft;
	private Stats playerStats;
	private Camera camera;
	private int direction;
	private int AAcooldown = 0;
	private ArrayList<Projectile> project = new ArrayList<Projectile>();
	private Inventory inv;
	private int barrel = 6;
	private float o, a, h, inc, startX, startY, endX, endY;
	private int blinkCC, blinkCost=15, blinker;
	private boolean blinking;
	private int aoeTimer;
	private AOE aoe;
	private boolean aoeAbility;
	private int aoeCC, aoeCost=20;
	private boolean oneUp=true;

	public Player(Game game, Camera camera, float x, float y, int width, int height) {
		// super(game, x, y, width, height);
		super(Handler.getW() / 2 - width / 2, Handler.getH() / 2 - 36, width, height);

		direction = 0;
		// int health, int totalHealth, int armor, int speed, int pDamage, int
		// eArmor, int eDamage)
		if(Handler.isCheat()){
			defaultSpeed = 3;
			aoeCost = 1;
			blinkCost = 1;
			playerStats = new Stats(64, 64, 500, 3, 500, 500, 500, 500, 500);
		} else {
			playerStats = new Stats(12, 12, 1, 3, 0, 0, 0, 100, 100);
		}
		// gun = new Gun(5, 1);

		// playerUi = new Ui(this);

		// FOR ANIMATIONS
		// this.init();
		
		
//		draw armor and sword

		this.camera = camera;

		bounds.x = 8;
		bounds.y = 10;
		bounds.width = 16;
		bounds.height = 22;

		// sprite =
		// ImageLoader.loadImage("/CharacterSprites/Player/Astronaut.png");
		animDown = new Animation((playerStats.getSpeed() * defaultSpeed), Assets.getPlayerDown());
		animUp = new Animation((playerStats.getSpeed() * defaultSpeed), Assets.getPlayerUp());
		animRight = new Animation((playerStats.getSpeed() * defaultSpeed), Assets.getPlayerRight());
		animLeft = new Animation((playerStats.getSpeed() * defaultSpeed), Assets.getPlayerLeft());

		inv = new Inventory();
		aoeAbility = false;

	}

	public Player(Game game, Camera camera, float x, float y, int width, int height, Stats playerStats) {
		// super(game, x, y, width, height)
		super(Handler.getW() / 2 - width / 2, Handler.getH() / 2 - 36, width, height);


		if(Handler.isCheat()){
			defaultSpeed = 3;
			aoeCost = 1;
			blinkCost = 1;
		}
		direction = 0;
		this.playerStats = playerStats;
		this.camera = camera;

		bounds.x = 8;
		bounds.y = 10;
		bounds.width = 16;
		bounds.height = 22;

		animDown = new Animation((playerStats.getSpeed() * defaultSpeed), Assets.getPlayerDown());
		animUp = new Animation((playerStats.getSpeed() * defaultSpeed), Assets.getPlayerUp());
		animRight = new Animation((playerStats.getSpeed() * defaultSpeed), Assets.getPlayerRight());
		animLeft = new Animation((playerStats.getSpeed() * defaultSpeed), Assets.getPlayerLeft());

		inv = new Inventory();
		aoeAbility = false;

	}

	public Stats getPlayerStats() {
		return playerStats;
	}

	public void setPlayerStats(Stats playerStats) {
		this.playerStats = playerStats;
	}

	/*
	 * public Gun getGun() { return gun; }
	 * 
	 * public void setGun(Gun gun) { this.gun = gun; }
	 */
	@Override
	public void damage(int damage) {
		if(damage < 0 && playerStats.getHealth() == playerStats.getTotalHealth()){
			if(Handler.isCheat()){
				playerStats.setTotalHealth(playerStats.getTotalHealth()+4);
			} else {
				playerStats.setTotalHealth(playerStats.getTotalHealth() + 1);
			}
		}
		if(Handler.isCheat()){
			playerStats.damage(-1);
		} else {
			playerStats.damage(damage);
		}

	}

	@Override
	public String getText(int i) {
		if(i==0) {
			return "0 I need to find \"The Great Bujak\" and argue with him until he gives me my 100 or dies...";
		} else {
			return "0 Man humans really are stupid, Oh well won't keep me up at night, atleast now I know where \"The Great Bujak\' is. Better get going";
		}
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

//	public BufferedImage getSprite() {
//		return sprite;
//	}

	public void tick() {
		playerStats.tick();
		if (playerStats.getHealth() <= 0) {
			if(Handler.isCheat() && oneUp){
				playerStats.setHealth(playerStats.getTotalHealth());
				System.out.println("You have been resurrected by the Gods");
				oneUp = false;
			} else {
				// System.out.println("deado");
				GameOverState gostate = new GameOverState(false);
				Handler.setGameoverState(gostate);
				StateManager.setCurrent(gostate, 6);
			}
		}
		if(blinkCC >= 0){
			blinkCC--;
//			System.out.println(blinkCC);
		}
		if(blinking){
			blink();
			blinker++;
		}
		if(blinker >= 10){
//			System.out.println(blinker);
			blinker=0;
			blinkCC += 120;
			blinking = false;
		}
		if (project != null) {
			for (int i = 0; i < project.size(); i++) {
				project.get(i).tick();
			}
		}
		if(aoeAbility){
			return;
		} else if(aoeCC > 0){
			aoeCC--;
		}
		if(aoeAbility){
			aoe.tick();
		}
		input();
		move(true);

		animDown.tick();
		animUp.tick();
		animRight.tick();
		animLeft.tick();

		Handler.getCamera().centerCameraOnCreature(this);
	}

	@Override
	BufferedImage getProfile() {
		return Assets.getPlayerProfile();
	}

	private void input() {
		xMotion = 0;
		yMotion = 0;

		if (Handler.getKeyManager().a) {
			direction = 1;
			xMotion -= (playerStats.getSpeed() * defaultSpeed);
		}
		if (Handler.getKeyManager().s) {
			direction = 0;
			yMotion += (playerStats.getSpeed() * defaultSpeed);
		}
		if (Handler.getKeyManager().d) {
			direction = 3;
			xMotion += (playerStats.getSpeed() * defaultSpeed);
		}
		if (Handler.getKeyManager().w) {
			direction = 2;
			yMotion -= (playerStats.getSpeed() * defaultSpeed);
		}

		if (Handler.getKeyManager().space) {
			blink();
			// special move (teleport? blink?)
		}
		if (Handler.getKeyManager().one) {
			// special move (teleport? blink?)
		}
		if (Handler.getKeyManager().two) {
			// special move (teleport? blink?)
		}
		if (Handler.getKeyManager().three) {
			aoe();
			// special move (teleport? blink?)
		}
		if (Handler.getKeyManager().r) {
			playerStats.reload();
		}
		if (Handler.getMouseManager().isLeftPressed() && AAcooldown == 0) {
			// float startX, float startY, float endX, float endY, int width,
			// int height
			// System.out.println(project.size());
			if (playerStats.getBarrel() > 0 && playerStats.getReloading() <= 0) {
				AAcooldown = 12;
				playerStats.shoot();
				project.add(new Projectile(x + (width / 2) + xMotion, y + (height / 2) + yMotion,
						Handler.getMouseManager().getMouseX() + Handler.getCamera().getxOffset(),
						Handler.getMouseManager().getMouseY() + Handler.getCamera().getyOffset(), 5, 5, project.size(),
						this));
			} else {
				AAcooldown = 180;
				//playerStats.setBarrel(6);
			}

			// System.out.println(getX());
			// System.out.println(getY());
			// System.out.println(Handler.getMouseManager().getMouseX()+Handler.getCamera().getxOffset());
			// System.out.println(Handler.getMouseManager().getMouseY()+Handler.getCamera().getyOffset());
			// System.out.println(Math.sqrt(Math.pow((Handler.getMouseManager().getMouseX()+Handler.getCamera().getxOffset()-getX()),
			// 2.0)+(Math.pow((Handler.getMouseManager().getMouseY()+Handler.getCamera().getyOffset()-getY()),
			// 2.0))));

		}
		if (AAcooldown != 0) {
			AAcooldown -= 1;
		}

	}

	public void aoe(){
		if(aoeCC <=0 && playerStats.getEnergy()>=aoeCost) {
			aoe = new AOE(x - camera.getxOffset(), y - camera.getyOffset(), 0, 0, false, this);
			aoeTimer = 0;
			aoeAbility = true;
			aoeCC = 240;
			if(Handler.isCheat()){
				aoeCC = 0;
			}
			playerStats.useEnergy(aoeCost);
		}
	}

	public void blink(){
//		System.out.println(playerStats.getEnergy());
//		project.add(new Projectile(x + (width / 2) + xMotion, y + (height / 2) + yMotion,
//				Handler.getMouseManager().getMouseX() + Handler.getCamera().getxOffset(),
//				Handler.getMouseManager().getMouseY() + Handler.getCamera().getyOffset(), 5, 5, project.size(),
//				this));
		if(blinkCC > 0 || playerStats.getEnergy() < blinkCost){
			return;
		}
		endX = Handler.getMouseManager().getMouseX() + Handler.getCamera().getxOffset();
		endY = Handler.getMouseManager().getMouseY() + Handler.getCamera().getyOffset();
		startX = x;
		startY = y;
		
		o = endX - startX;
		a = endY - startY;
		h = (float)(Math.sqrt(Math.pow(o, 2) + Math.pow(a, 2)));
		inc = h/32;
		xMotion += o/inc;
		yMotion += a/inc;
		if(blinking == false){
			playerStats.useEnergy(blinkCost);
			blinking = true;
		}
	}

	public int getSpeed() {
		return (playerStats.getSpeed() * defaultSpeed);
	}

	public void render(Graphics g) {
		playerStats.render(g);
		if (project != null) {
			for (int i = 0; i < project.size(); i++) {
				project.get(i).render(g);
			}
		}
		if(aoeAbility){
			if(aoeTimer==32){
					aoe = null;
					aoeAbility=false;
					aoeTimer=0;
					return;
			}
			aoe.render(g);
			aoeTimer++;
		}
		if(blinking){
			g.setColor(Color.cyan);
			g.fillOval((int) (x - camera.getxOffset())+width/4, (int) (y - camera.getyOffset())+height/4, width/2, height/2);
			return;
		}
		// temporary
		g.drawImage(getCurrentAnimationFrame(), (int) (x - camera.getxOffset()), (int) (y - camera.getyOffset()), width,
				height, null);

		// RENDER BOOTS, LEGS, ARMOR, HEAD IN THAT ORDER.
		// playerUi.render(g);

	}

	public void render(Graphics g, boolean b) {
		playerStats.render(g);
	}

	public void remove(int index) {
		// System.out.println("removed bullet index: " + index);
		// System.out.println("bullets on screen: " + project.size());
		try {
			project.remove(index);
		} catch (IndexOutOfBoundsException e) {

		}
		for (int i = 0; i < project.size(); i++) {
			project.get(i).setIndex(i);
		}
	}

	private BufferedImage getCurrentAnimationFrame() {
		if (xMotion == 0 && yMotion == 0) {
			switch (direction) {
			case 0:
				return animDown.getStill(0);
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
		} else if(yMotion > 0) {
			return animDown.getCurrentFrame();
		} else if (xMotion > 0) {
			return animRight.getCurrentFrame();
		} else if (xMotion < 0) {
			return animLeft.getCurrentFrame();
		} else {
			return animDown.getCurrentFrame();
		}
	}

	public Inventory getInv() {
		return inv;
	}

	public void setCamera(Camera camera) {
		this.camera = camera;
	}

//	@Override
//	public void setX(float x){
//		System.out.println("x set " + x);
//		super.setX(x);
//	}
//	@Override
//	public void setY(float y){
//		System.out.println("y set" + y);
//		super.setY(y);
//	}
}
