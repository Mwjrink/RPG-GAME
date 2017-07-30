/* Max Rink
 * ICS4U
 * June 1 2016
 * WorldAnim.java
 * 
 */
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class WorldAnim {

    private ArrayList<Integer[][]> layers;
    private int width, height, tileWidth, tileHeight;
    private Tile voidTile;
    private Camera camera;
    private String path;
    private TileSetArray tileSetArray;

    WorldAnim(map maper, Camera camera) {

        this.camera = camera;
        this.path = maper.getPath();

        layers = new ArrayList<Integer[][]>();
        tileSetArray = new TileSetArray();
        voidTile = new VoidTile("/Tiles/VoidTile.png");
        loadWorld(path);
    }

//    public EntityManager getEntityManager() {
//        return entityManager;
//    }

    public void loadWorld(String path) {
        File file = new File(path);

        DocumentBuilder dBuilder;
        try {
            dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = dBuilder.parse(file);

            // doc.getDocumentElement().normalize();

//			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

            if (doc.hasChildNodes()) {

                NodeList nodeList = doc.getChildNodes();

                for (int count = 0; count < nodeList.getLength(); count++) {

                    Node tempNode = nodeList.item(count);

                    // make sure it's element node.
                    if (tempNode.getNodeType() == Node.ELEMENT_NODE) {

                        if (tempNode.hasAttributes()) {

                            // get attributes names and values
                            NamedNodeMap nodeMap = tempNode.getAttributes();
                            for (int i = 0; i < nodeMap.getLength(); i++) {

                                Node node = nodeMap.item(i);

                                // int tempheight, tempwidth, temptileheight,
                                // temptilewidth;

                                if (node.getNodeName().equals("height")) {
                                    this.height = Utils.parseInt(node.getNodeValue());
//									System.out.println(this.height);
                                } else if (node.getNodeName().equals("width")) {
                                    this.width = Utils.parseInt(node.getNodeValue());
//									System.out.println(this.width);
                                } else if (node.getNodeName().equals("tileheight")) {
                                    this.tileHeight = Utils.parseInt(node.getNodeValue());
                                } else if (node.getNodeName().equals("tilewidth")) {
                                    this.tileWidth = Utils.parseInt(node.getNodeValue());
                                    Handler.setTILEWIDTH(this.tileWidth);
                                }
                            }
                            NodeList list = tempNode.getChildNodes();
//								System.out.println(list.getLength());
                            for (int s = 0; s < list.getLength(); s++) {
                                Node temp = list.item(s);
//									System.out.println(temp.getNodeName());
//								if(temp.getNodeName().equals("properties")){
//									NodeList prop = temp.getChildNodes();
//									for(int d=0; d<prop.getLength(); d++){
//										if(prop.item(d).equals("property")){
//											NamedNodeMap nnm = prop.item(d).getAttributes();
//											for(int f=0; f<prop.getLength(); f++){
//												if(nnm.item(f).getNodeName().equals("name")){
//													nnm.item(f).getNodeValue() = 
//												}
//											}
//										}
//									}
                                /*} else */
                                if (temp.getNodeName().equals("tileset")) {
                                    //System.out.println("tileset");
                                    loadTileSet(temp);
                                } else if (temp.getNodeName().equals("layer")) {

//										for(int g=0;g<3;g++){
//											if(temp.getAttributes().item(g).getNodeName().equals("name")){
//												System.out.println(temp.getAttributes().item(g).getNodeValue());
//											}
//										}
//										System.out.println(temp.getAttributes().item(1).getNodeValue());
//										System.out.println(height);
                                    layers.add(new Integer[width][height]);
//										System.out.println(layers.get(0).length);
                                    loadData(temp, layers.size() - 1);
                                }
                            }

                        }
                    }
                }
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }

    public void loadData(Node node, int index) {
        NodeList gg = node.getChildNodes();
        NodeList temp = gg.item(1).getChildNodes();
        NamedNodeMap nnm;
        int counter = 0, x = 0, y = 0;
        //last = temp.getLastChild();
//		System.out.println("index: " + index + "\nlayers size: " + layers.size());\


        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            try {
                layers.get(index)[x][y] = Utils.parseInt(temp.item(i).getAttributes().item(0).getNodeValue());
//					System.out.println("GID: " + Utils.parseInt(temp.item(i).getAttributes().item(0).getNodeValue()));
//					System.out.println(layers.get(index)[x][y]);
                x++;
                if (x == (width)) {
                    if (y == height - 1)
                        break;
                    y++;
                    x = 0;
                }
            } catch (NullPointerException e) {
                continue;
            }
        }

//		for(int i=0; i<temp.getLength(); i++){
////		for(int y=0; y<height; y++){
////			for(int x=0; x<width; x++){
//			if(x == (width)){
//				if(y == 35)
//					break;
//				y++;
//				x=0;
//				
//			}
//			int z = x+y+counter;
//			
//			if (temp.item(x + y + counter).getNodeName().equals("tile")) {
//				nnm = temp.item(x + y + counter).getAttributes();
//
//				for (int s = 0; s < nnm.getLength(); s++) {
//					 try {
//					if (nnm.item(i).getNodeName().equals("gid")) {
//						// System.out.println(x+y+counter+i);
//						// System.out.print(nnm.item(0).getNodeValue() + ",
//						// ");
//
////						if (index == 2) {
////							 System.out.println("x: " + x);
////							 System.out.println("y: " + y);
////							System.out.println("GID: " + Utils.parseInt(nnm.item(s).getNodeValue()));
////						}
//						layers.get(index)[x][y] = Utils.parseInt(nnm.item(s).getNodeValue());
//						//x++;
//					}
//
//					 } catch (NullPointerException e) {
//					// continue;
//					 }
//					 x++;
//				}
//			} else {
//				counter++;
//				//System.out.println(counter);
//			}
//		}
        //temp = temp.item(x+y).getNextSibling();
//			}
//			//System.out.println("");
//		}
    }

    public void loadTileSet(Node node) {
        int firstGID = 0, tileHeight = 0, tileWidth = 0, width = 0, height = 0, tileCount = 0;
        String name = "", path = "";
        NamedNodeMap nodeMap = node.getAttributes();
        for (int i = 0; i < nodeMap.getLength(); i++) {
            Node temp = nodeMap.item(i);
            if (temp.getNodeName().equals("firstgid")) {
                firstGID = Utils.parseInt(temp.getNodeValue());
            } else if (temp.getNodeName().equals("name")) {
                name = temp.getNodeValue();
            } else if (temp.getNodeName().equals("tileheight")) {
                tileHeight = Utils.parseInt(temp.getNodeValue());
            } else if (temp.getNodeName().equals("tilewidth")) {
                tileWidth = Utils.parseInt(temp.getNodeValue());
            } else if (temp.getNodeName().equals("tilecount")) {
                tileCount = Utils.parseInt(temp.getNodeValue());
            }
        }
        NodeList secondNodeMap = node.getChildNodes();
//		System.out.println(secondNodeMap);
        for (int i = 0; i < secondNodeMap.getLength(); i++) {
            //System.out.println(secondNodeMap.item(i).getNodeName());
            if (secondNodeMap.item(i).getNodeName().equals("image")) {
                NamedNodeMap gg = secondNodeMap.item(i).getAttributes();
                for (int h = 0; h < gg.getLength(); h++) {
                    Node temp = gg.item(h);
                    //System.out.println("Node Name: " + temp.getNodeName());
                    if (temp.getNodeName().equals("width")) {
                        width = Utils.parseInt(temp.getNodeValue());
                        //System.out.println(width);
                    } else if (temp.getNodeName().equals("height")) {
                        height = Utils.parseInt(temp.getNodeValue());
                        //System.out.println(height);
                    } else if (temp.getNodeName().equals("source")) {
                        path = temp.getNodeValue().replaceFirst("../TileSheets/", "");
                    }
                }
            }
        }
        //System.out.println("path: " + path);
        tileSetArray.add(firstGID, name, width, height, tileWidth, tileHeight, tileCount, path);
    }

    public void render(Graphics g) {
        int xBegin = (int) Math.max(0, camera.getxOffset() / tileWidth);
        int xEnd = (int) Math.min(Handler.getWorld().getWidth(), (camera.getxOffset() + Handler.getW()) / tileWidth + 1);
        int yBegin = (int) Math.max(0, camera.getyOffset() / tileHeight);
        int yEnd = (int) Math.min(Handler.getWorld().getHeight(), (camera.getyOffset() + Handler.getH()) / tileHeight + 1);

        for (int l = 0; l < layers.size() - 3; l++) {
            for (int y = yBegin; y < yEnd; y++) {
                for (int x = xBegin; x < xEnd; x++) {
                    g.drawImage(getTileImage(x, y, l), (int) (x * tileHeight - camera.getxOffset()), (int) (y * tileHeight - camera.getyOffset()), tileWidth, tileHeight, null);
                }
            }
        }
    }

    public void renderOver(Graphics g) {
        int xBegin = (int) Math.max(0, camera.getxOffset() / tileWidth);
        int xEnd = (int) Math.min(Handler.getWorld().getWidth(), (camera.getxOffset() + Handler.getW()) / tileWidth + 1);
        int yBegin = (int) Math.max(0, camera.getyOffset() / tileHeight);
        int yEnd = (int) Math.min(Handler.getWorld().getHeight(), (camera.getyOffset() + Handler.getH()) / tileHeight + 1);
        int l = layers.size() - 3;
        for (int y = yBegin; y < yEnd; y++) {
            for (int x = xBegin; x < xEnd; x++) {
                g.drawImage(getTileImage(x, y, l), (int) (x * tileHeight - camera.getxOffset()), (int) (y * tileHeight - camera.getyOffset()), tileWidth, tileHeight, null);
            }
        }
    }


    public BufferedImage getTileImage(int x, int y, int l) {
        try {
            if (layers.get(l)[x][y] == 0) {
                return null;
            }
        } catch (NullPointerException e) {
            System.out.println("x: " + x + ", y: " + y);
            // return (BufferedImage)voidTile.getAsset();
        } catch (ArrayIndexOutOfBoundsException a){
            return (BufferedImage) voidTile.getAsset();
        }
        return tileSetArray.getTileImage(layers.get(l)[x][y]);
    }

}


