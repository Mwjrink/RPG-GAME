/* Max Rink
 * ICS4U
 * June 1 2016
 * Game.java
 * 
 */
import java.awt.Graphics;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;

public class Game implements Runnable {
	
	private int w = 1280, h = w/16*9;
	private final int FPS = 60;
	
	private Thread thread;
	private boolean running;
	
	private BufferStrategy bs;
	private Graphics g;
	
	private KeyManager keyManager;
	private Display display;

	private MouseManager mouseManager;
	
	private Assets assets;
	
	private PauseState pause;
	private InventoryState inventory;
	private TalkingState talking;
	
//	public void setDisplay(Display display) {
//		this.display = display;
//	}

	Game(int w, int h, Display display){
		this.w = w;
		this.h = h;
		
		this.display = display;
		
		keyManager = new KeyManager();
		Handler.setKeyManager(keyManager);
		mouseManager = new MouseManager();
		Handler.setMouseManager(mouseManager);
		assets = new Assets();
		Handler.setAssets(assets);
		pause = new PauseState();
		Handler.setPauseState(pause);
		inventory = new InventoryState();
		Handler.setInventoryState(inventory);
		
	}
	
	void init(){
		display.getFrame();
		display.getFrame().addKeyListener(keyManager);
		display.getFrame().addMouseListener(mouseManager);
		display.getCanvas().addMouseListener(mouseManager);
		
		display.getFrame().addMouseMotionListener(mouseManager);
		display.getCanvas().addMouseMotionListener(mouseManager);
	}

	void render(){
		
		bs = display.getCanvas().getBufferStrategy();
		Handler.setBs(bs);
		if(bs == null){
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		Handler.setG(g);
		//clear screen
		g.clearRect(0, 0, w, h);
		//draw
		
		StateManager.getCurrent().render(g);
		
		//end draw
		bs.show();
		g.dispose();
		
	}
	
	void tick(){
		Handler.getMusicClass().tick();
		if (Handler.getKeyManager().f2) {
			Handler.getDisplay().getFrame()
					.dispatchEvent(new WindowEvent(Handler.getDisplay().getFrame(), WindowEvent.WINDOW_CLOSING));
		}
		keyManager.tick();
		Handler.getKeyManager().tick();
		StateManager.getCurrent().tick();
	}
	
	public KeyManager getKeyManager() {
		return keyManager;
	}
	
	public void pause(){
		try {
			
			super.wait();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setDisplaySize(int wid, int hid){
		Handler.getFrame().setSize(wid, hid);
		Handler.setW(wid);
		Handler.setH(hid);
		display.center();
	}
	
	public void run(){
		
		init();
		
		double timePerTick = 1000000000 / FPS;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		int ticks = 0;
		
		while(running){
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			timer += now - lastTime;
			lastTime = now;
			
			if(delta >= 1){
				tick();
				render();
				ticks++;
				delta--;
			}
			
			if(timer >= 1000000000){
//				System.out.println("FPS: " + ticks);
				ticks = 0;
				timer = 0;
			}
		}
		
		stop();
		
	}
	
	public synchronized void start(){
		if(running)
			return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public boolean isRunning() {
		return running;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}

	public Thread getThread() {
		return thread;
	}

	public synchronized void stop(){
		if(!running)
			return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}


	public void takeABreak(int i) {
        try
        {
            synchronized(thread) {
                thread.wait(i);
            }
        } catch (InterruptedException e) {}
//        System.out.println("Runner away!");
    }

}
