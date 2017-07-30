/* Max Rink
 * ICS4U
 * June 1 2016
 * SpriteSheet.java
 * 
 */



import java.awt.image.BufferedImage;

public class SpriteSheet {

	private BufferedImage sheet;
	
	public SpriteSheet(BufferedImage sheet){
		this.sheet = sheet;
	}
	
	public BufferedImage crop(int x, int y, int width, int height){
		return sheet.getSubimage(x, y, width, height);
	}
	
	public int getW(){
		return sheet.getWidth();
	}
	
	public int getH(){
		return sheet.getHeight();
	}
}
