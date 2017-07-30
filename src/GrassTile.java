/* Max Rink
 * ICS4U
 * June 1 2016
 * GrassTile.java
 * 
 */
import java.awt.Image;

public class GrassTile extends Tile {
	private Image asset;
//	private String path;
	
	
	GrassTile(String path){
		isSolid = false;
//		this.path = path;
		asset = ImageLoader.loadImage(path);
	}
	
	public Image getAsset(){
		return asset;
	}
	
}
