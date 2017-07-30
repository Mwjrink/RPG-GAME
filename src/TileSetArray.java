/* Max Rink
 * ICS4U
 * June 1 2016
 * TileSetArray.java
 * 
 */
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class TileSetArray {
	
	private ArrayList<TileSet> tileArray;
	private ArrayList<BufferedImage[][]> TileSets;
	private SpriteSheet tileSheet;
	
	TileSetArray(){
		tileArray = new ArrayList<TileSet>();
		TileSets = new ArrayList<BufferedImage[][]>();
	}
	
	public void add(int firstGID, String name, int width, int height, int tileWidth, int tileHeight, int tilecount, String path){
//		System.out.println(path);
		
		tileArray.add(new TileSet(firstGID, name, width, height, tileWidth, tileHeight, tilecount, path));
		TileSets.add(new BufferedImage[width/tileWidth][height/tileHeight]);
		
		tileSheet = new SpriteSheet(ImageLoader.loadImage("/TileSheets/" + path));
		
		//TileSets.get(TileSets.size()-1) = new BufferedImage[width/tileWidth][height/tileHeight];
		
		for(int x=0; x<width/tileWidth; x++){
			for(int y=0; y<height/tileHeight; y++){
				TileSets.get(TileSets.size()-1)[x][y] = tileSheet.crop(x*tileWidth, y*tileHeight, tileWidth, tileHeight);
			}
		}
	}
	
	public BufferedImage getTileImage(int GID){
		int count=0;
		do{
			if(GID > tileArray.get(count).getFirstGID()+tileArray.get(count).getTileCount()-1){
				count++;
				continue;
			} else {
//				System.out.println(gid);
//				if (tileArray.get(count).getName().equals("Bone Planet A")) {
//					System.out.println("booyah");
//				}
				// System.out.println(tileArray.get(count).getName());
//				if(gid == 34){
//					System.out.println(tileArray.get(count).getPath());
//				}
//				return tileArray.get(count).getTileImage(gid);
				int gid = GID-tileArray.get(count).getFirstGID();
				return TileSets.get(count)[gid%(tileArray.get(count).getWidth()/tileArray.get(count).getTileWidth())][gid/(tileArray.get(count).getWidth()/tileArray.get(count).getTileWidth())];
				
			}
		}while(true);
	}
	
}
