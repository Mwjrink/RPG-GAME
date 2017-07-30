/* Max Rink
 * ICS4U
 * June 1 2016
 * World.java
 * 
 */
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class World {

    private int[][] map;
    private Tile[][] tilesMap;
    private ArrayList<Integer[][]> layers;
    private int width, height, tileWidth, tileHeight;
    private int startX, startY;
    private ArrayList<String> doors;
    private ArrayList<Integer[]> doorCoords;
    private int[][] startPos;
    private Tile tiles[];
    private Game game;
    private Tile voidTile;
    private Camera camera;
    //private int wh = 32;
    private String[] paths;
    private String path;
    private Document document;
    private DocumentBuilderFactory dbf;
    private File file;
    private TileSetArray tileSetArray;
    private int animNumber = -1;
    private ArrayList<map> maps;
    private map current;
    private int count = 0;
    private boolean anim;
    private int animCounter;
    private WorldAnim worldAnim;
    private boolean talked = false;

    private EntityManager entityManager;

    World(Game game, map maper) {
        //loadWorld();
        layers = new ArrayList<Integer[][]>();
        this.game = game;
        this.path = maper.getPath();
        this.anim = false;

        this.current = maper;
        startX = current.getStartX();
        startY = current.getStartY();
        tileSetArray = new TileSetArray();
        loadWorld(path);

        camera = new Camera(0, 0);
        Handler.setCamera(camera);

        if (Handler.isCheat()) {
            entityManager = new EntityManager(new Player(this.game, camera, startX, startY, 38, 47));
        } else {
            entityManager = new EntityManager(new Player(this.game, camera, startX, startY, 32, 32));
        }
//        entityManager = new EntityManager(new Player(this.game, camera, startX, startY, 32, 32));

//        entityManager = new EntityManager(new Player(this.game, camera, startX, startY, 26, 32));
        entityManager.getPlayer().setX(startX * tileWidth);
        entityManager.getPlayer().setY(startY * tileHeight);
        Handler.setPlayer(entityManager.getPlayer());

//        if(Handler.getPlayer() == null) {
//            entityManager.getPlayer().setX(startX * tileWidth);
//            entityManager.getPlayer().setY(startY * tileHeight);
//            Handler.setPlayer(entityManager.getPlayer());
//        } else {
//            entityManager = new EntityManager(Handler.getPlayer());
//            entityManager.getPlayer().setX(startX * tileWidth);
//            entityManager.getPlayer().setY(startY * tileHeight);
//            Handler.setPlayer(entityManager.getPlayer());
//        }

        maps = new ArrayList<map>();
        doorCoords = new ArrayList<Integer[]>();
        doors = new ArrayList<String>();
        if (path.endsWith("TESTSMALLER.tmx")) {
            maps.add(current);
        } else {
            maps.add(Handler.getGameState().getWorlds().get(0).getCurrent());
        }
        maps.add(new map(24, 32, "res/Worlds/bar.tmx"));
        maps.add(new map(32, 61, "res/Worlds/station.tmx"));
        maps.add(new map(31, 62, "res/Worlds/bank.tmx"));
        maps.add(new map(31, 1, "res/Worlds/BossFight.tmx"));
        maps.add(new map(46, 58, "res/Worlds/hotel.tmx"));

        loadEntities();

        voidTile = new VoidTile("/Tiles/VoidTile.png");

        if (path.endsWith("BossFight.tmx")) {
            anim = true;
            worldAnim = new WorldAnim(new map(31, 1, "res/Worlds/2BossFight.tmx"), camera);
        }

    }

    World(Game game, map maper, Stats playerStats) {
        //loadWorld();
        layers = new ArrayList<Integer[][]>();
        this.game = game;
        this.path = maper.getPath();
        this.anim = false;

        this.current = maper;
        startX = current.getStartX();
        startY = current.getStartY();
        tileSetArray = new TileSetArray();
        loadWorld(path);

        camera = new Camera(0, 0);
        Handler.setCamera(camera);

        if (Handler.isCheat()) {
            entityManager = new EntityManager(new Player(this.game, camera, startX, startY, 38, 47, playerStats));
        } else {
            entityManager = new EntityManager(new Player(this.game, camera, startX, startY, 32, 32, playerStats));
        }
            entityManager.getPlayer().setX(startX * tileWidth);
            entityManager.getPlayer().setY(startY * tileHeight);
            Handler.setPlayer(entityManager.getPlayer());

        entityManager.getPlayer().setX(startX * tileWidth);
        entityManager.getPlayer().setY(startY * tileHeight);
        Handler.setPlayer(entityManager.getPlayer());

        maps = new ArrayList<map>();
        doorCoords = new ArrayList<Integer[]>();
        doors = new ArrayList<String>();
        if (path.endsWith("TESTSMALLER.tmx")) {
            maps.add(current);
        } else {
            maps.add(Handler.getGameState().getWorlds().get(0).getCurrent());
        }
        maps.add(new map(24, 32, "res/Worlds/bar.tmx"));
        maps.add(new map(32, 61, "res/Worlds/station.tmx"));
        maps.add(new map(31, 62, "res/Worlds/bank.tmx"));
        maps.add(new map(31, 1, "res/Worlds/BossFight.tmx"));
        maps.add(new map(46, 58, "res/Worlds/hotel.tmx"));

        loadEntities();

        voidTile = new VoidTile("/Tiles/VoidTile.png");

        if (path.endsWith("BossFight.tmx")) {
            anim = true;
            worldAnim = new WorldAnim(new map(31, 1, "res/Worlds/2BossFight.tmx"), camera);
        }

    }

    public Stats getPlayerStats(){
        return entityManager.getPlayer().getPlayerStats();
    }

    public void loadEntities() {
        for (int y = 0; y < layers.get(layers.size() - 1)[0].length; y++) {
            for (int x = 0; x < layers.get(layers.size() - 1).length; x++) {
                Integer[] coords = {x, y};
                switch (layers.get(layers.size() - 1)[x][y]) {
                    case 1:
//					System.out.println(1);
                        entityManager.addEntity(new SlimyDalton(x * tileWidth, y * tileHeight, 32, 32));
//                        Handler.getEntityManager().addEntity(new SlimyDalton(x * tileWidth, y * tileHeight, 32, 32));
//                        Handler.setEntityManager(entityManager);
                        Handler.setSlimyDalton((SlimyDalton) entityManager.getEntities().get(entityManager.getEntities().size() - 1));
                        break;
                    case 2:
//					System.out.println(2);
                        entityManager.addEntity(new ShiftyDalton(x * tileWidth, y * tileHeight, 32, 32));
//                        Handler.getEntityManager().addEntity(new ShiftyDalton(x * tileWidth, y * tileHeight, 32, 32));
//                        Handler.setEntityManager(entityManager);
                        Handler.setShiftyDalton((ShiftyDalton) entityManager.getEntities().get(entityManager.getEntities().size() - 1));
                        break;
                    case 3:
//					System.out.println(3);
                        entityManager.addEntity(new SneakyDalton(x * tileWidth, y * tileHeight, 32, 32));
//                        Handler.getEntityManager().addEntity(new SneakyDalton(x * tileWidth, y * tileHeight, 32, 32));
//                        Handler.setEntityManager(entityManager);
                        Handler.setSneakyDalton((SneakyDalton) entityManager.getEntities().get(entityManager.getEntities().size() - 1));
                        break;
                    case 4:
//					System.out.println(4);
                        entityManager.addEntity(new SnitchyDalton(x * tileWidth, y * tileHeight, 32, 32));
//                        Handler.getEntityManager().addEntity(new SnitchyDalton(x * tileWidth, y * tileHeight, 32, 32));
//                        Handler.setEntityManager(entityManager);
                        Handler.setSnitchyDalton((SnitchyDalton) entityManager.getEntities().get(entityManager.getEntities().size() - 1));
                        break;
                    case 5:
//					System.out.println(1);
                        entityManager.addEntity(new Earpe1(x * tileWidth, y * tileHeight, 32, 32));
//                        Handler.getEntityManager().addEntity(new Earpe1(x * tileWidth, y * tileHeight, 32, 32));
//                        Handler.setEntityManager(entityManager);
                        Handler.setEarpe1((Earpe1) entityManager.getEntities().get(entityManager.getEntities().size() - 1));
                        break;
                    case 6:
//					System.out.println(2);
                        entityManager.addEntity(new Earpe2(x * tileWidth, y * tileHeight, 32, 32));
//                        Handler.getEntityManager().addEntity(new Earpe2(x * tileWidth, y * tileHeight, 32, 32));
//                        Handler.setEntityManager(entityManager);
                        Handler.setEarpe2((Earpe2) entityManager.getEntities().get(entityManager.getEntities().size() - 1));
                        break;
                    case 7:
//					System.out.println(3);
                        entityManager.addEntity(new Earpe3(x * tileWidth, y * tileHeight, 32, 32));
//                        Handler.getEntityManager().addEntity(new Earpe3(x * tileWidth, y * tileHeight, 32, 32));
//                        Handler.setEntityManager(entityManager);
                        Handler.setEarpe3((Earpe3) entityManager.getEntities().get(entityManager.getEntities().size() - 1));
                        break;
                    case 8:
//					System.out.println(4);
                        entityManager.addEntity(new Earpe4(x * tileWidth, y * tileHeight, 32, 32));
//                        Handler.getEntityManager().addEntity(new Earpe4(x * tileWidth, y * tileHeight, 32, 32));
//                        Handler.setEntityManager(entityManager);
                        Handler.setEarpe4((Earpe4) entityManager.getEntities().get(entityManager.getEntities().size() - 1));
                        break;
                    case 829:

//                        doorCoords.add(coords);
//                        maps.add(new map());
                        break;
                    case 830:

//                        doorCoords.add(coords);

                        if (path.endsWith("TESTSMALLER.tmx")) {
                            doors.add("station");
                            current.getDoorsTo().add(maps.get(2));
                            current.getDoors().add(coords);
                        }
                        break;
                    case 831:

//                        doorCoords.add(coords);
//                        maps.add(new map());
                        break;
                    case 832:

//                        doorCoords.add(coords);
//                        maps.add(new map());
                        break;
                    case 833:

//                        doorCoords.add(coords);
//                        maps.add(new map());
                        break;
                    case 834:

//                        doorCoords.add(coords);
//                        maps.add(new map());
                        break;
                    case 835:
//                        System.out.println("X: " + x);
//                        System.out.println("Y: " + y);
                        if (path.endsWith("TESTSMALLER.tmx")) {
                            doors.add("bar");
                            current.getDoorsTo().add(maps.get(1));
                            current.getDoors().add(coords);
                        }
//                        maps.add(new map());
                        break;
                    case 836:
                        if (path.endsWith("TESTSMALLER.tmx")) {
                            doors.add("bank");
                            current.getDoorsTo().add(maps.get(3));
                            current.getDoors().add(coords);
                        }
//                        doorCoords.add(coords);
//                        maps.add(new map());
                        break;
                    case 837:

//                        doorCoords.add(coords);
//                        maps.add(new map());
                        break;
                    case 838:
                        if (path.endsWith("TESTSMALLER.tmx")) {
                            doors.add("BossFight");
                            current.getDoorsTo().add(maps.get(4));
                            current.getDoors().add(coords);
                        }
                        break;
                    case 593:
                        if (path.endsWith("bar.tmx")) {
                            doors.add("overworld");
                            current.getDoorsTo().add(maps.get(0));
                            current.getDoors().add(coords);
                        }
                        break;
                    case 271:
                        if (path.endsWith("station.tmx")) {
                            doors.add("overworld1");
                            current.getDoorsTo().add(maps.get(0));
                            current.getDoors().add(coords);
                        }
                        break;
                    case 553:
                        if (path.endsWith("bank.tmx")) {
                            doors.add("overworld2");
                            current.getDoorsTo().add(maps.get(0));
                            current.getDoors().add(coords);
                        }
                        break;
                    case 668:
                        if (path.endsWith("BossFight.tmx")) {
                            doors.add("overworld3");
                            current.getDoorsTo().add(maps.get(0));
                            current.getDoors().add(coords);
                        }
                        break;
                    case 669:
                        if (path.endsWith("BossFight.tmx")) {
                            int mult = 4;
                            entityManager.addEntity(new Bujak(x * tileWidth, y * tileHeight, 70 * mult, 80 * mult, mult));
                            Handler.setBujak((Bujak) entityManager.getEntities().get(entityManager.getEntities().size() - 1));
                            //SPAWN THE BUJAKI

                        }
                        break;
                    case 594:
                        if (path.endsWith("bar.tmx")) {
                            doors.add("Hotel");
                            current.getDoorsTo().add(maps.get(5));
                            current.getDoors().add(coords);
                        }
                        break;
                    case 1217:
                        if (path.endsWith("hotel.tmx")) {
                            doors.add("bar1");
                            current.getDoorsTo().add(maps.get(1));
                            current.getDoors().add(coords);
                        }
                        break;
                    default:
                        if (layers.get(layers.size() - 1)[x][y] != 0) {
                            System.out.println("x: " + x + ", Y: " + y + ", GID: " + layers.get(layers.size() - 1)[x][y]);
                        }
//					    System.out.println(layers.get(layers.size() - 1)[x][y]);
                        break;

                }
//                Handler.setEntityManager(entityManager);
            }
        }
    }

    public String getPath() {
        return path;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

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

    public void tick() {

        Handler.getCamera().tick();

        entityManager.tick();
        if (Handler.isCheat()) {
            if (Handler.getKeyManager().one) {
                if (animNumber < 5) {
                    animNumber = 5;
                }
            }
            if (Handler.getKeyManager().two) {
                count--;
            }
        }

    }

    public void render(Graphics g) {
        if (animCounter >= 30) {
            animCounter = 0;
        }
        int xBegin = (int) Math.max(0, camera.getxOffset() / tileWidth);
        int xEnd = (int) Math.min(Handler.getWorld().getWidth(), (camera.getxOffset() + Handler.getW()) / tileWidth + 1);
        int yBegin = (int) Math.max(0, camera.getyOffset() / tileHeight);
        int yEnd = (int) Math.min(Handler.getWorld().getHeight(), (camera.getyOffset() + Handler.getH()) / tileHeight + 1);
        g.setColor(Color.red);

        if (anim && animCounter < 15) {

            worldAnim.render(g);
            entityManager.render(g);
            worldAnim.renderOver(g);
            entityManager.getPlayer().render(g, true);
            animCounter++;
            return;
        }

        for (int l = 0; l < layers.size() - 3; l++) {
            for (int y = yBegin; y < yEnd; y++) {
                for (int x = xBegin; x < xEnd; x++) {
                    g.drawImage(getTileImage(x, y, l), (int) (x * tileHeight - camera.getxOffset()), (int) (y * tileHeight - camera.getyOffset()), tileWidth, tileHeight, null);
                }
            }
        }

        entityManager.render(g);
//        if(Handler.getBujak() != null) {
//            Handler.getBujak().render(g);
//        }

        int l = layers.size() - 3;
        for (int y = yBegin; y < yEnd; y++) {
            for (int x = xBegin; x < xEnd; x++) {
                g.drawImage(getTileImage(x, y, l), (int) (x * tileHeight - camera.getxOffset()), (int) (y * tileHeight - camera.getyOffset()), tileWidth, tileHeight, null);
            }
        }
        entityManager.getPlayer().render(g, true);
        animCounter++;

    }

    public BufferedImage getTileImage(int x, int y, int l) {
        try {
            if (layers.get(l)[x][y] == 0) {
                return null;
            }
        } catch (NullPointerException e) {
            System.out.println("x: " + x + ", y: " + y);
            // return (BufferedImage)voidTile.getAsset();
        } catch (ArrayIndexOutOfBoundsException a) {
            return (BufferedImage) voidTile.getAsset();
        }
        return tileSetArray.getTileImage(layers.get(l)[x][y]);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getStartX() {
        return startX;
    }

    public int getStartY() {
        return startY;
    }

    public void setStartXY(int x, int y) {
        Handler.getPlayer().setX(startX * tileWidth);
        Handler.getPlayer().setY(startY * tileHeight);
    }

    public map isDoor() {
        int x = (int) Handler.getPlayer().getX() / Handler.getTilewidth();
        int y = (int) Handler.getPlayer().getY() / Handler.getTilewidth();
//        System.out.println("player X: " + x);
//        System.out.println("player Y: " + y);

        for (int i = 0; i < current.getDoors().size(); i++) {
//            System.out.println("door X: " + doorCoords.get(i)[0]);
//            System.out.println("door Y: " + doorCoords.get(i)[1]);
            if (y == current.getDoors().get(i)[1]) {
//                System.out.println("Y");
                if (x == current.getDoors().get(i)[0]) {
//                    Handler.setEntities(entityManager.getEntities());
//                System.out.println(doors.get(i));
                    switch (doors.get(i)) {
                        case "bar":
                            return maps.get(1);
                        case "overworld":
                            maps.get(0).setStartX(24);
                            maps.get(0).setStartY(78);
//                        maps.get(0).setStartXY(24, 78);
                            return maps.get(0);
//                        return Handler.getGameState().getWorlds().get(0).getCurrent();
                        case "overworld1":
                            maps.get(0).setStartX(67);
                            maps.get(0).setStartY(11);
//                        maps.get(0).setStartXY(24, 78);
                            return maps.get(0);
                        case "overworld2":
                            maps.get(0).setStartX(72);
                            maps.get(0).setStartY(72);
//                        maps.get(0).setStartXY(24, 78);
                            return maps.get(0);
                        case "overworld3":
                            maps.get(0).setStartX(50);
                            maps.get(0).setStartY(198);
//                        maps.get(0).setStartXY(24, 78);
                            return maps.get(0);
                        case "station":
                            return maps.get(2);
                        case "bank":
                            return maps.get(3);
                        case "BossFight":
                            return maps.get(4);
                        case "Hotel":
                            return maps.get(5);
                        case "bar1":
                            maps.get(1).setStartX(22);
                            maps.get(1).setStartY(1);
                            return maps.get(1);
                        default:
                            return null;
                    }
                }
            }
        }
        isAnim();
        return null;
//        return "null";
    }

    public void isAnim() {
        float x = Handler.getPlayer().getX() / Handler.getTilewidth();
        float y = Handler.getPlayer().getY() / Handler.getTilewidth();
        if (path.endsWith("TESTSMALLER.tmx")) {
            if (animNumber == -1) {
                StateManager.setCurrent(new TalkingState(Handler.getPlayer(), 0), 4);
                animNumber = 0;
            } else if (x >= 41 && x <= 51 && y <= 25 && y >= 20 && /*!Handler.getAnims() && */animNumber == 0 && path.endsWith("TESTSMALLER.tmx")) {
                if (!Handler.isSnitchyAnim()) {
                    StateManager.setCurrent(new AnimState(Handler.getSnitchyDalton(), 1), 4);
                    return;
                }
                if (!Handler.isSlimyAnim()) {
                    StateManager.setCurrent(new AnimState(Handler.getSlimyDalton(), 1), 4);
                    return;
                }
                if (!Handler.isShiftyAnim()) {
                    StateManager.setCurrent(new AnimState(Handler.getShiftyDalton(), 1), 4);
                    return;
                }
                if (!Handler.isSneakyAnim()) {
                    StateManager.setCurrent(new AnimState(Handler.getSneakyDalton(), 1), 4);
                    return;
                }
                Handler.setShiftyAnim(false);
                Handler.setSneakyAnim(false);
                Handler.setSlimyAnim(false);
                Handler.setSnitchyAnim(false);
                animNumber = 1;
                return;
            } else if (x >= 42 && x <= 52 && y <= 62 && y >= 52 /*&& !Handler.getAnims() */ && animNumber == 1 && path.endsWith("TESTSMALLER.tmx")) {
//            System.out.println("in le box");
                if (!Handler.isSnitchyAnim()) {
                    StateManager.setCurrent(new AnimState(Handler.getSnitchyDalton(), 2), 4);
//                    Handler.setSnitchyAnim(true);
                    return;
                }
                if (!Handler.isSlimyAnim()) {
                    StateManager.setCurrent(new AnimState(Handler.getSlimyDalton(), 2), 4);
//                    Handler.setSlimyAnim(true);
                    return;
                }
                if (!Handler.isShiftyAnim()) {
                    StateManager.setCurrent(new AnimState(Handler.getShiftyDalton(), 2), 4);
//                    Handler.setShiftyAnim(true);
                    return;
                }
                if (!Handler.isSneakyAnim()) {
                    StateManager.setCurrent(new AnimState(Handler.getSneakyDalton(), 2), 4);
//                    Handler.setSneakyAnim(true);
                    return;
                }
                Handler.setShiftyAnim(false);
                Handler.setSneakyAnim(false);
                Handler.setSlimyAnim(false);
                Handler.setSnitchyAnim(false);
                animNumber = 2;
                return;
            } else if (animNumber == 2 && path.endsWith("TESTSMALLER.tmx")) {
                StateManager.setCurrent(new TalkingState(Handler.getEarpe1(), 0), 4);
                animNumber = 3;
                Handler.getGame().takeABreak(500);
            } else if (animNumber == 3 && path.endsWith("TESTSMALLER.tmx")) {
                StateManager.setCurrent(new TalkingState(Handler.getSlimyDalton(), 0), 4);
                animNumber = 4;
                Handler.getGame().takeABreak(500);
            } else if (animNumber == 4 && path.endsWith("TESTSMALLER.tmx")) {
                StateManager.setCurrent(new TalkingState(Handler.getEarpe1(), 1), 4);
                animNumber = 5;
                Handler.getGame().takeABreak(500);
            } else if (animNumber == 5 && path.endsWith("TESTSMALLER.tmx")) {
                Handler.getEarpe1().damage(15, true);
                entityManager.destroyEntity(Handler.getEarpe1());
                Handler.getGame().render();
//            Handler.getGame().takeABreak(50);
                Handler.getEarpe2().damage(15, true);
                entityManager.destroyEntity(Handler.getEarpe2());
                Handler.getGame().render();
//            Handler.getGame().takeABreak(50);
                Handler.getEarpe3().damage(15, true);
                entityManager.destroyEntity(Handler.getEarpe3());
                Handler.getGame().render();
//            Handler.getGame().takeABreak(50);
                Handler.getEarpe4().damage(15, true);
                entityManager.destroyEntity(Handler.getEarpe4());
                Handler.getGame().render();
//            Handler.getGame().takeABreak(50);
                Handler.getSlimyDalton().damage(15, true);
                entityManager.destroyEntity(Handler.getSlimyDalton());
                Handler.getGame().render();
//            Handler.getGame().takeABreak(50);
                Handler.getSnitchyDalton().damage(15, true);
                entityManager.destroyEntity(Handler.getSnitchyDalton());
                Handler.getGame().render();
//            Handler.getGame().takeABreak(50);
                Handler.getSneakyDalton().damage(15, true);
                entityManager.destroyEntity(Handler.getSneakyDalton());
                Handler.getGame().render();
//            Handler.getGame().takeABreak(50);
                Handler.getShiftyDalton().damage(15, true);
                entityManager.destroyEntity(Handler.getShiftyDalton());
                Handler.getGame().render();
                Handler.getGame().takeABreak(200);
                animNumber = 6;
            } else if (animNumber == 6 && path.endsWith("TESTSMALLER.tmx")) {
                if (!talked) {
                    StateManager.setCurrent(new TalkingState(new NPC1(0, 0, 0, 0)), 4);
                    talked = true;
                    return;
                } else {
                    StateManager.setCurrent(new TalkingState(Handler.getPlayer(), 1), 4);
                }

                Handler.getGame().takeABreak(500);
                for (int i = 0; i < Math.random() * 50 + 20; i++) {
                    int size = ((int) (Math.random() * 128)) + 64;
                    if (i < 20) {
                        entityManager.addEntity(new Noblin((float) (Math.random() * 95 + 3), (float) (Math.random() * 100 + 95), size, size, i));
                    } else {
                        entityManager.addEntity(new Noblin((float) (Math.random() * 95 + 3), (float) (Math.random() * 100 + 95), size, size, (int) (Math.random() * 20)));
                    }
                    count++;
                }
//            entityManager.addEntity(new Noblin(40, 116, 512, 512));
//            System.out.println("add enemies to world");
                animNumber = 7;
            } else if (animNumber == 7) {
                if (count <= 0) {
                    animNumber = 8;
                }
            } else if (animNumber == 8) {
            	StateManager.setCurrent(new TalkingState(new NPC1(0, 0, 0, 0), 0), 4);
            	animNumber=9;
            }
        } else if (path.endsWith("BossFight.tmx")) {
            if (animNumber == -1) {
                StateManager.setCurrent(new TalkingState(Handler.getBujak(), 0), 4);
//                StateManager.setCurrent(new TalkingState(entityManager.getEntities().get(entityManager.getEntities().size()-1), 0), 4);
                Handler.getGame().takeABreak(500);
                animNumber = 0;
//            System.out.println("Now for the great Bujak");
            } else if (animNumber == 0 && y >= 23) {
//                Handler.getBujak().setDisabled(false);
                animNumber=2;
            }
        }
        return;
    }

    public boolean isSolid(int xx, int yy) {
//        System.out.println("X: " + xx);
//        System.out.println("Y: " + yy);
        if (animNumber < 8 && yy == 198 && path.endsWith("TESTSMALLER.tmx")) {
            return true;
        }
        try {
            if (layers.get(layers.size() - 2)[xx][yy] == 0) {
                return false;
            }
            return true;
        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
    }

    public map getCurrent() {
        return current;
    }

    public void setCurrent(map current) {
        this.current = current;
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }

    public void reset() {
//        entityManager = new EntityManager(Handler.getEntities());
//        entityManager.setEntities(Handler.getEntityManager().getEntities());
        Handler.setPlayer(entityManager.getPlayer());
//        Handler.setWorld(this);
//        camera = new Camera(0, 0);
//        Handler.setCamera(camera);
        camera = Handler.getCamera();
        camera.centerCameraOnCreature(getEntityManager().getPlayer());
        entityManager.getPlayer().setX(startX * tileWidth);
        entityManager.getPlayer().setY(startY * tileHeight);
        entityManager.getPlayer().setCamera(camera);

//        setStartXY(startX, startY);
    }

    public void setStartX(int startX) {
        this.startX = startX;
    }

    public void setStartY(int startY) {
        this.startY = startY;
    }

    public void killNoblin() {
        count--;
    }

    public int getCount() {
        return count;
    }
}


