/* Max Rink
 * ICS4U
 * June 1 2016
 * TileSet.java
 * 
 */
import java.awt.image.BufferedImage;

public class TileSet {

	private int firstGID;
	private String name, path;
	private int width, height;
	private int tileWidth, tileHeight;
	private int tileCount;
	private static BufferedImage[][] tileImages;
	private SpriteSheet tileSheet;
	
	
	public TileSet(int firstGID, String name, int width, int height, int tileWidth, int tileHeight, int tilecount, String path) {
		this.firstGID = firstGID;
		this.name = name;
		this.width = width;
		this.height = height;
		this.tileWidth = tileWidth;
		this.tileHeight = tileHeight;
		this.tileCount = tilecount;
		this.path = path;
//		System.out.println("path: " + path + ", startGID: " + name);
//		System.out.println("tileCount: " + tileCount);
		tileInit();
	}
	
	void tileInit(){
		tileSheet = new SpriteSheet(ImageLoader.loadImage("/TileSheets/" + path));
		
		tileImages = new BufferedImage[width/tileWidth][height/tileHeight];
		
		for(int x=0; x<width/tileWidth; x++){
			for(int y=0; y<height/tileHeight; y++){
				tileImages[x][y] = tileSheet.crop(x*tileWidth, y*tileHeight, tileWidth, tileHeight);
			}
		}
	}
	
	public BufferedImage getTileImage(int GID){
		int gid = GID-firstGID;
//		System.out.println("GID: " + GID);
//		System.out.println("first: " + gid%(width/tileWidth));
//		System.out.println("second: " + gid/(width/tileWidth));
//		System.out.println("Name: " + name + ", x: " + gid%(width/tileWidth) + ", y: " + gid/(width/tileWidth));
//		if(path.equals("Bones_A.png")){
//			System.out.println("loading");
//		}
		
//		if(name.equals("Bone Planet A") ){
//			Handler.getG().drawImage(ImageLoader.loadImage("/TileSheets/" + path), 0, 0, 128, 128, null);
			//Handler.getG().drawImage(tileImages[1][4], 0, 0, 128, 128, null);
//		}
		
		return tileImages[gid%(width/tileWidth)][gid/(width/tileWidth)];
	}


	public int getTileCount() {
//		System.out.println(tileCount);
		return tileCount;
	}
	
	public String getName(){
		return name;
	}

	public int getFirstGID() {
		return firstGID;
	}

	public String getPath() {
		return path;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getTileWidth() {
		return tileWidth;
	}

	public int getTileHeight() {
		return tileHeight;
	}

	public static BufferedImage[][] getTileImages() {
		return tileImages;
	}
	
}
