/* Max Rink
 * ICS4U
 * June 1 2016
 * VoidTile.java
 * 
 */
import java.awt.Image;

public class VoidTile extends Tile {
	
	private Image asset;
//	private String path;
	
	VoidTile(String path){
		isSolid = true;
//		this.path = path;
		asset = ImageLoader.loadImage(path);
	}
	
	public Image getAsset(){
		return asset;
	}
}
