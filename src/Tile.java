/* Max Rink
 * ICS4U
 * June 1 2016
 * Tile.java
 * 
 */
import java.awt.Image;

public abstract class Tile {
	
	//private Asset asset;
	protected boolean isSolid;
//	private String path;
	private Image asset;
	
	//private static Asset[] assets;
	//	private static Tile grassTile = new GrassTile();
	
	Tile(){
		//assets = new Asset[3];
		//asset = ImageLoader.loadImage(path);
	}
	
//	public String getPath() {
//		return path;
//	}

	public Image getAsset(){
		return asset;
	}
	
	public boolean isSolid(){
		return isSolid;
	}
	
	
	
}
