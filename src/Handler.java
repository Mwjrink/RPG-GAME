/* Max Rink
 * ICS4U
 * June 1 2016
 * Handler.java
 * 
 */
import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

import javax.swing.JFrame;

public class Handler {

    private static Game game;
    private static KeyManager KeyManager;
    private static MouseManager mouseManager;
    private static Display display;
    private static BufferStrategy bs;
    private static Graphics g;
    private static MenuState menu;
    private static GameState gameState;
    private static SettingsState settingsState;
    private static int w = 1280, h = w / 16 * 9;
    private static final int FPS = 60;
    private static String Title;
    private static boolean isMusic, sound;
    private static Camera camera;
    private static Player player;
    private static World world;
    private static int TILEWIDTH = 32;
    private static Assets assets;
    private static InventoryState inventory;
    private static PauseState pause;
    private static GameOverState gameoverState;
    private static Musical musicClass;
    private static int musicVolume = 75;
    private static int soundVolume = 75;
    private static JFrame name;
    private static SlimyDalton slimyDalton;
    private static SnitchyDalton snitchyDalton;
    private static ShiftyDalton shiftyDalton;
    private static SneakyDalton sneakyDalton;
    private static boolean snitchyAnim;
    private static boolean sneakyAnim;
    private static boolean shiftyAnim;
    private static boolean slimyAnim;
    private static Earpe1 earpe1;
    private static Earpe2 earpe2;
    private static Earpe3 earpe3;
    private static Earpe4 earpe4;
    private static EntityManager entityManager;
    private static boolean cheat=false;
    private static Bujak bujak;
    private static int Char;
//    private static ArrayList<Entity> Entities;


    public static int getChar() {
        return Char;
    }
    public static void setChar(int aChar) {
        Char = aChar;
    }
    public static Bujak getBujak() {
        return bujak;
    }
    public static void setBujak(Bujak bujak) {
        Handler.bujak = bujak;
    }
    public static Earpe1 getEarpe1() {
        return earpe1;
    }
    public static void setEarpe1(Earpe1 earpe1) {
        Handler.earpe1 = earpe1;
    }
    public static Earpe2 getEarpe2() {
        return earpe2;
    }
    public static void setEarpe2(Earpe2 earpe2) {
        Handler.earpe2 = earpe2;
    }
    public static Earpe3 getEarpe3() {
        return earpe3;
    }
    public static void setEarpe3(Earpe3 earpe3) {
        Handler.earpe3 = earpe3;
    }
    public static Earpe4 getEarpe4() {
        return earpe4;
    }
    public static void setEarpe4(Earpe4 earpe4) {
        Handler.earpe4 = earpe4;
    }
    public static Musical getMusicClass() {
        return musicClass;
    }
    public static boolean isSnitchyAnim() {
        return snitchyAnim;
    }
    public static void setSnitchyAnim(boolean snitchyAnim) {
        Handler.snitchyAnim = snitchyAnim;
    }
    public static boolean isSneakyAnim() {
        return sneakyAnim;
    }
    public static void setSneakyAnim(boolean sneakyAnim) {
        Handler.sneakyAnim = sneakyAnim;
    }
    public static boolean isShiftyAnim() {
        return shiftyAnim;
    }
    public static void setShiftyAnim(boolean shiftyAnim) {
        Handler.shiftyAnim = shiftyAnim;
    }
    public static boolean isSlimyAnim() {
        return slimyAnim;
    }
    public static void setSlimyAnim(boolean slimyAnim) {
        Handler.slimyAnim = slimyAnim;
    }
    public static SlimyDalton getSlimyDalton() {
        return slimyDalton;
    }
    public static void setSlimyDalton(SlimyDalton slimyDalton) {
        Handler.slimyDalton = slimyDalton;
    }
    public static SnitchyDalton getSnitchyDalton() {
        return snitchyDalton;
    }
    public static void setSnitchyDalton(SnitchyDalton snitchyDalton) {
        Handler.snitchyDalton = snitchyDalton;
    }
    public static ShiftyDalton getShiftyDalton() {
        return shiftyDalton;
    }
    public static void setShiftyDalton(ShiftyDalton shiftyDalton) {
        Handler.shiftyDalton = shiftyDalton;
    }
    public static SneakyDalton getSneakyDalton() {
        return sneakyDalton;
    }
    public static void setSneakyDalton(SneakyDalton sneakyDalton) {
        Handler.sneakyDalton = sneakyDalton;
    }
    public static int getTILEWIDTH() {
        return TILEWIDTH;
    }
    public static void setTILEWIDTH(int tILEWIDTH) {
        TILEWIDTH = tILEWIDTH;
    }
    public static int getMusicVolume() {
        return musicVolume;
    }
    public static void setMusicVolume(int musicVolume) {
        //System.out.println("now");
        name.dispose();
        Handler.musicVolume = musicVolume;
    }
    public static int getSoundVolume() {
        return soundVolume;
    }
    public static void setSoundVolume(int soundVolume) {
        Handler.soundVolume = soundVolume;
    }
    public static void setMusicClass(Musical musicClass) {
        Handler.musicClass = musicClass;
    }
    public static InventoryState getInventory() {
        return inventory;
    }
    public static void setInventory(InventoryState inventory) {
        Handler.inventory = inventory;
    }
    public static PauseState getPause() {
        return pause;
    }
    public static void setPause(PauseState pause) {
        Handler.pause = pause;
    }
    public static GameOverState getGameoverState() {
        return gameoverState;
    }
    public static void setGameoverState(GameOverState gameoverState) {
        Handler.gameoverState = gameoverState;
    }
    public static Assets getAssets() {
        return assets;
    }
    public static void setAssets(Assets assets) {
        Handler.assets = assets;
    }
    public static int getTilewidth() {
        return TILEWIDTH;
    }
    public static World getWorld() {
        return world;
    }
    public static void setWorld(World world) {
        Handler.world = world;
    }
    public static Player getPlayer() {
        return player;
    }
    public static void setPlayer(Player player) {
        Handler.player = player;
    }
    public static Camera getCamera() {
        return camera;
    }
    public static void setCamera(Camera camera) {
        Handler.camera = camera;
    }
    public static boolean isMusic() {
        return isMusic;
    }
    public static void setMusic(boolean music) {
        Handler.isMusic = music;
    }
    public static boolean isSound() {
        return sound;
    }
    public static void setSound(boolean sound) {
        Handler.sound = sound;
    }
    public static JFrame getFrame() {
        return display.getFrame();
    }
    public static Canvas getCanvas() {
        return display.getCanvas();
    }
    public static String getTitle() {
        return Title;
    }
    public static void setTitle(String title) {
        Title = title;
    }
    public static Game getGame() {
        return game;
    }
    public static void setGame(Game game) {
        Handler.game = game;
    }
    public static KeyManager getKeyManager() {
        return KeyManager;
    }
    public static void setKeyManager(KeyManager keyManager) {
        KeyManager = keyManager;
    }
    public static MouseManager getMouseManager() {
        return mouseManager;
    }
    public static void setMouseManager(MouseManager mouseManager) {
        Handler.mouseManager = mouseManager;
    }
    public static Display getDisplay() {
        return display;
    }
    public static void setDisplay(Display display) {
        Handler.display = display;
        //game.setDisplay(display);
    }
    public static BufferStrategy getBs() {
        return bs;
    }
    public static void setBs(BufferStrategy bs) {
        Handler.bs = bs;
    }
    public static Graphics getG() {
        return g;
    }
    public static void setG(Graphics g) {
        Handler.g = g;
    }
    public static MenuState getMenu() {
        return menu;
    }
    public static void setMenu(MenuState menu) {
        Handler.menu = menu;
    }
    public static GameState getGameState() {
        return gameState;
    }
    public static void setGameState(GameState gameState) {
        Handler.gameState = gameState;
    }
    public static SettingsState getSettingsState() {
        return settingsState;
    }
    public static void setSettingsState(SettingsState settingsState) {
        Handler.settingsState = settingsState;
    }
    public static int getW() {
        return w;
    }
    public static void setW(int w) {
        Handler.w = w;
    }
    public static int getH() {
        return h;
    }
    public static void setH(int h) {
        Handler.h = h;
    }
    public static int getFps() {
        return FPS;
    }
    public static InventoryState getInventoryState() {
        return inventory;
    }
    public static void setInventoryState(InventoryState inventory) {
        Handler.inventory = inventory;
    }
    public static PauseState getPauseState() {
        return pause;
    }
    public static void setPauseState(PauseState pause) {
        Handler.pause = pause;
    }
    public static boolean getAnims() {
        if (snitchyAnim && sneakyAnim && shiftyAnim && slimyAnim) {
            return true;
        }
        return false;
    }
    public static void setEntityManager(EntityManager entityManager) {
        Handler.entityManager = entityManager;
    }
    public static boolean isIsMusic() {
        return isMusic;
    }
    public static void setIsMusic(boolean isMusic) {
        Handler.isMusic = isMusic;
    }
    public static JFrame getName() {
        return name;
    }
    public static void setName(JFrame name) {
        Handler.name = name;
    }
    public static EntityManager getEntityManager() {
        return entityManager;
    }
    public static boolean isCheat() {
        return cheat;
    }
    public static void setCheat(boolean ch) {
        cheat = ch;
    }
}
