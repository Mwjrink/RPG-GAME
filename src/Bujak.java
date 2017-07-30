/* Max Rink
 * ICS4U
 * June 1 2016
 * Bujak.java
 * 
 */

import java.awt.*;
import java.awt.image.BufferedImage;

public class Bujak extends Creature{
    private int maxHealth = 100, health = 100;
    private int damage = 5;
    private int defaultSpeed = 1;
    private Animation BujakIdle, BujakWalk, BujakSpin, Bujakattack, BujakHit;
    private int activity;
    private int aoeTimer;
    private AOE aoe;
    private boolean aoeAbility, spinning;
    private int aoeCC=120;
    private BufferedImage currentImage;
    private int sizeMult, activityCooldown;
    private int movementCC, cooldown;
    private int spinCC, spinActive, Xmove, Ymove;
    private int aoeCounter, spinCounter;
    private int sequence=0;
    private boolean walking;
    private int walkingCounter;
    private boolean disabled=false;


    public Bujak(float x, float y, int width, int height, int sizeMult) {
        super(x, y, width, height);
        bounds.x = (int)(width*0.1);
        bounds.y = (int)(height*0.1);
        bounds.width = (int)(width*0.8);
        bounds.height = (int)(height*0.8);
        collides = true;
        collectible = false;
        this.sizeMult = sizeMult;

//        animDown = new Animation((playerStats.getSpeed() * defaultSpeed), Assets.getPlayerDown());
        BujakIdle = new Animation((2), Assets.getBujakIdle());
        BujakWalk = new Animation((2), Assets.getBujakWalk());
        BujakSpin = new Animation((2), Assets.getBujakSpin());
        Bujakattack = new Animation((2), Assets.getBujakattack());
        BujakHit = new Animation((2), Assets.getBujakHit());

        aoeAbility = false;

    }

    @Override
    public void damage(int damage) {
        if(activity == 2 || activity == 3){
            return;
        }
        activity=4;
        activityCooldown=(124/4);
        health -= damage;
    }

    @Override
    public String getText(int i) {
        return "0 My name is \"The Great Bujak\"! You have killed all of my Noblins, I refuse to give you 100 in the course. Come and argue if you dare...";
    }

    public void tick() {
        if(disabled){
            if(Handler.getPlayer().getyMotion() >= 23){
                disabled = false;
            }
            return;
        }
        if (health <= 0) {
            GameOverState gostate = new GameOverState(true);
            Handler.setGameoverState(gostate);
            StateManager.setCurrent(gostate, 6);
            System.out.println("You can never beat the Bujak...");
            if(Handler.isCheat()){
                System.out.println("Unless your name is Max Rink, then its actually quite easy\n !!!!YOU HAVE WON!!!!");
            }
        }

        if(cooldown > 0){cooldown--;}

        if(activityCooldown == 0){
            if(sequence >=5){sequence=0;}
            switch(sequence){
                case 0:
                    activityCooldown=60;
                    sequence++;
                    walk();
                    break;
                case 1:
                    activityCooldown=75;
                    sequence++;
                    aoe();
                    break;
                case 2:
                    activityCooldown=60;
                    sequence++;
                    walk();
                    break;
                case 3:
                    activityCooldown=90;
                    sequence++;
                    spin();
                    break;
                case 4:
                    activityCooldown=60;
                    sequence++;
                    activity=0;
                    break;
            }
        } else {
            activityCooldown--;
        }

        if(aoeAbility){
            aoe.tick();
            aoeCounter++;
            activity=3;
            if(aoeCounter >= 90) {
                aoeCounter = 0;
                aoeAbility = false;
                activityCooldown=60;
            }
        }

        if(spinning){
            activity=2;
            if(spinCounter%5 == 0) {
                xMotion += Xmove;
                yMotion += Ymove;
            }
            spinCounter++;
            if(spinCounter>=30){
                Xmove=0;
                Ymove=0;
                activityCooldown=90;
                spinCounter=0;
                spinning=false;
            }
        }

        if(walking){
            if(walkingCounter%10==0) {
                xMotion -= (Math.abs(x + width / 2 - Handler.getPlayer().getX()) / (x + width / 2 - Handler.getPlayer().getX()))*defaultSpeed;
                yMotion -= (Math.abs(y + height / 2 - Handler.getPlayer().getY()) / (y + height / 2 - Handler.getPlayer().getY()))*defaultSpeed;
            }
            walkingCounter++;
            if(walkingCounter >= 60){
                walkingCounter=0;
                walking=false;
                activityCooldown=30;
            }
        }


//        BujakIdle
//        BujakWalk
//        BujakSpin
//        Bujakattack
//        BujakHit

        BujakIdle.tick();
        BujakWalk.tick();
        BujakSpin.tick();
        Bujakattack.tick();
        BujakHit.tick();

        move();
    }
    
    public void aoe(){
            xMotion=0;
            yMotion=0;
            activity=3;
            aoeCounter=0;
			aoe = new AOE(x + width/2, y + height/2, 0, 0, true, this);
			aoeAbility = true;
	}
	public void spin(){
            Xmove -= (Math.abs(x+width/2-Handler.getPlayer().getX())/(x+width/2-Handler.getPlayer().getX()))*defaultSpeed;
            Ymove -= (Math.abs(y+height/2-Handler.getPlayer().getY())/(y+height/2-Handler.getPlayer().getY()))*defaultSpeed;
            spinning=true;
    }
    public void walk(){
            activity = 1;
            walking=true;
            walkingCounter=0;
    }

    @Override
    BufferedImage getProfile() {
        return Assets.getBujakProfile();
    }

    @Override
    public boolean checkEntityCollisions(float xOffset, float yOffset){
        //System.out.println("YES");
        for(Entity e : Handler.getWorld().getEntityManager().getEntities()){
            if(e.equals(this))
                continue;
            if(e.getCollisionBounds(0f, 0f).intersects(getCollisionBounds(xOffset, yOffset))){
                if(e.equals(Handler.getPlayer())){
                    switch (activity){
                    
                        case 2:
                        	if(cooldown<=0){
                        		Handler.getPlayer().damage(3);
                                cooldown = 30;
                            }
                            break;
                        case 3:
                        	if(cooldown<=0){
                        		Handler.getPlayer().damage(5);
                                cooldown = 30;
                            }
                            break;
                        default:
                            if(cooldown<=0){
                                Handler.getPlayer().damage(damage);
                                cooldown = 90;
                            }
                            break;
                    }
                }
                return true;
            }
        }
        return false;
    }

    public void render(Graphics g) {
        if(aoeAbility){
            aoe.render(g);
        }

        currentImage = getCurrentAnimationFrame();
        g.drawImage(currentImage, (int) (x - Handler.getCamera().getxOffset()), (int) (y - Handler.getCamera().getyOffset()), currentImage.getWidth()*sizeMult, currentImage.getHeight()*sizeMult, null);
        renderHealth(g);
    }

    public void renderHealth(Graphics g) {
        g.setColor(Color.green);
        g.drawRect(Handler.getW()/3, Handler.getH()-20, Handler.getW()/3, 16);
        g.setColor(Color.red);
        g.fillRect(Handler.getW()/3+2, Handler.getH()-18, (int)((Handler.getW()/3-2)*((double)health/(double)maxHealth)), 14);
//        System.out.println(health);
    }


    private BufferedImage getCurrentAnimationFrame() {
            //0=idle
            //1=walking
            //2=spin
            //3=attack
            //4=hit
            switch (activity) {
                case 0:
                    return BujakIdle.getCurrentFrame();
                case 1:
                    return BujakWalk.getCurrentFrame();
                case 2:
                    return BujakSpin.getCurrentFrame();
                case 3:
                    return Bujakattack.getCurrentFrame();
                case 4:
                    return BujakHit.getCurrentFrame();
                default:
                    return BujakIdle.getCurrentFrame();

            }
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }
}
