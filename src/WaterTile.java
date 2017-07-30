/* Max Rink
 * ICS4U
 * June 1 2016
 * WaterTile.java
 * 
 */
import java.awt.Image;

public class WaterTile extends Tile {
	
	private Image asset;
//	private String path;
	
	
	WaterTile(String path){
		isSolid = true;
//		this.path = path;
		asset = ImageLoader.loadImage(path);
	}
	
	public Image getAsset(){
		return asset;
	}
	
}
