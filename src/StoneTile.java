/* Max Rink
 * ICS4U
 * June 1 2016
 * StoneTile.java
 * 
 */
import java.awt.Image;

public class StoneTile extends Tile {
	
	private Image asset;
//	private String path;
	
	StoneTile(String path){
		isSolid = true;
//		this.path = path;
		asset = ImageLoader.loadImage(path);
	}
	
	public Image getAsset(){
		return asset;
	}
}
