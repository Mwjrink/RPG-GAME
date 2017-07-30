/* Max Rink
 * ICS4U
 * June 1 2016
 * Camera.java
 * 
 */
public class Camera {

	private float xOffset, yOffset;

	public Camera(float xOffset, float yOffset) {
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}

	public void move(float xAmt, float yAmt) {
		xOffset += xAmt;
		yOffset += yAmt;
		checkBounds();
	}

	public float getxOffset() {
		return xOffset;
	}

	public void setxOffset(float xOffset) {
		this.xOffset = xOffset;
	}

	public float getyOffset() {
		return yOffset;
	}

	public void setyOffset(float yOffset) {
		this.yOffset = yOffset;
	}

	void tick() {
		
	}
	
	void getInput(int speed){
		
	}
	
	void render(int speed){
		
	}
	
	public void checkBounds(){
		if(xOffset < 0){
			xOffset = 0;
		}else if(xOffset > Handler.getWorld().getWidth() * Handler.getTilewidth() - Handler.getW()){
			xOffset = Handler.getWorld().getWidth() * Handler.getTilewidth() - Handler.getW();
		}

		if(yOffset < 0){
			yOffset = 0;
		}else if(yOffset > Handler.getWorld().getHeight() * Handler.getTilewidth() - Handler.getH()){
			yOffset = Handler.getWorld().getHeight() * Handler.getTilewidth() - Handler.getH();
		}
	}
	
	void centerCameraOnCreature(Entity e) {
//		System.out.println("X: " + e.getX()/32);
//		System.out.println("Y: " + e.getY()/32);
//		System.out.println(Handler.getWorld().getHeight());
//		xOffset = (entity.getX() - Handler.getW() + (1*Handler.getTilewidth()));
//		yOffset = (entity.getY() - Handler.getH() + (1*Handler.getTilewidth()));

		xOffset = e.getX() - Handler.getW() / 2 + e.getWidth() / 2;
		yOffset = e.getY() - Handler.getH() / 2 + e.getHeight() / 2;
//		checkBounds();
//		if(Handler.getWorld().getWidth()*Handler.getTILEWIDTH() < Handler.getW()) {
//
//			xOffset = (Handler.getW() - Handler.getWorld().getWidth()*Handler.getTILEWIDTH())/2;
////			System.out.println(xOffset);
//		}
//		if(Handler.getWorld().getHeight()*Handler.getTILEWIDTH() < Handler.getH()) {
//
//			yOffset = (Handler.getH() - Handler.getWorld().getHeight()*Handler.getTILEWIDTH())/2;
////			System.out.println(yOffset);
//		}
		checkBounds();
	}

}
