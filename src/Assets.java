/* Max Rink
 * ICS4U
 * June 1 2016
 * Assets.java
 * 
 */

import java.awt.image.BufferedImage;

public class Assets {

	private static int width = 32, height = 32; // image size

	private static int playerWidth = 31, playerHeight = 32;
	private static int npcWidth = 96, npcHeight = 96, bujakWidth, bujakHeight;

	// private static BufferedImage stoneTileIMG, grassTileIMG, waterTileIMG;
	private static BufferedImage[] playerUp, playerDown, playerRight, playerLeft;
	private static BufferedImage[] npc1Up, npc1Down, npc1Right, npc1Left;
	private static BufferedImage[] npc2Up, npc2Down, npc2Right, npc2Left;
	private static BufferedImage[] npc3Up, npc3Down, npc3Right, npc3Left;
	private static BufferedImage[] npc4Up, npc4Down, npc4Right, npc4Left;
	private static BufferedImage[] npc5Up, npc5Down, npc5Right, npc5Left;
	private static BufferedImage[] npc6Up, npc6Down, npc6Right, npc6Left;
	private static BufferedImage[] hearts;
	private static BufferedImage[] enemies;
	private static BufferedImage[] BujakIdle, BujakWalk, BujakSpin, Bujakattack, BujakHit;
	private static BufferedImage rock, textBox, playerProfile, bujakProfile;
	private static SpriteSheet playerSheet, bujakSheet;

	private static BufferedImage playerUi;
	private static BufferedImage weaponUi;

	private static BufferedImage villager;

	Assets() {
		this.height = this.width = Handler.getTilewidth();
		//this.height = Handler.getTilewidth();

		init();
	}

	void init() {
		// SpriteSheet tilesSheet = new
		// SpriteSheet(ImageLoader.loadImage("/Tiles/SpriteSheet.png"));

		// stoneTileIMG = tilesSheet.crop(width, 0, width, height);

		playerInit();
		npcInit();
		BujakInit();
		rock = ImageLoader.loadImage("/rock.png");
		textBox = ImageLoader.loadImage("/textBox.png");

		playerProfile = ImageLoader.loadImage("/CharacterSprites/Player/profile.png");
		bujakProfile = ImageLoader.loadImage("/CharacterSprites/Bujak/BujakProfile.png");

		hearts = new BufferedImage[5];
		hearts[0] = ImageLoader.loadImage("/Hearts/emptyHeart.png");
		hearts[1] = ImageLoader.loadImage("/Hearts/oneHeart.png");
		hearts[2] = ImageLoader.loadImage("/Hearts/halfHeart.png");
		hearts[3] = ImageLoader.loadImage("/Hearts/threeHeart.png");
		hearts[4] = ImageLoader.loadImage("/Hearts/fullHeart.png");
		
		playerUi = ImageLoader.loadImage("/Ui.png");
		villager = ImageLoader.loadImage("/CharacterSprites/villager.png");

		enemies = new BufferedImage[20];
		enemies[0] = ImageLoader.loadImage("/CharacterSprites/Noblin.png");
		SpriteSheet enemySheet = new SpriteSheet(ImageLoader.loadImage("/CharacterSprites/enemies.png"));
		for(int i=1; i<8; i++) {
			enemies[i] = enemySheet.crop(i*64, 0, 64, 64);
		}
		for(int i=8; i<11; i++) {
			enemies[i] = enemySheet.crop((i-8)*128, 64, 128, 128);
		}
		for(int i=11; i<14; i++) {
			enemies[i] = enemySheet.crop((i-5)*64, 64, 64, 128);
		}
		for(int i=14; i<20; i++) {
			enemies[i] = enemySheet.crop((i-14)*64, 192, 64, 64);

		}

	}
	
	void npcInit() {
		SpriteSheet npcSheet = new SpriteSheet(ImageLoader.loadImage("/CharacterSprites/NPC.png"));

		npc1Down = new BufferedImage[3];
		npc1Left = new BufferedImage[3];
		npc1Right = new BufferedImage[3];
		npc1Up = new BufferedImage[3];

		npc1Down[0] = npcSheet.crop(npcWidth, 0, npcWidth, npcHeight);
		npc1Down[1] = npcSheet.crop(0, 0, npcWidth, npcHeight);
		npc1Down[2] = npcSheet.crop(npcWidth, 0, npcWidth, npcHeight);
		npc1Down[2] = npcSheet.crop(npcWidth*2, 0, npcWidth, npcHeight);

		npc1Left[0] = npcSheet.crop(npcWidth, npcHeight, npcWidth, npcHeight);
		npc1Left[1] = npcSheet.crop(0, npcHeight, npcWidth, npcHeight);
		npc1Left[2] = npcSheet.crop(npcWidth, npcHeight, npcWidth, npcHeight);
		npc1Left[2] = npcSheet.crop(npcWidth*2, 0, npcWidth, npcHeight);
		
		npc1Right[0] = npcSheet.crop(npcWidth, npcHeight * 2, npcWidth, npcHeight);
		npc1Right[1] = npcSheet.crop(0, npcHeight * 2, npcWidth, npcHeight);
		npc1Right[2] = npcSheet.crop(npcWidth, npcHeight * 2, npcWidth, npcHeight);
		npc1Right[2] = npcSheet.crop(npcWidth*2, npcHeight * 2, npcWidth, npcHeight);

		npc1Up[0] = npcSheet.crop(npcWidth, npcHeight * 3, npcWidth, npcHeight);
		npc1Up[1] = npcSheet.crop(0, npcHeight * 3, npcWidth, npcHeight);
		npc1Up[2] = npcSheet.crop(npcWidth, npcHeight * 3, npcWidth, npcHeight);
		npc1Up[2] = npcSheet.crop(npcWidth*2, npcHeight * 3, npcWidth, npcHeight);
		
		npc2Down = new BufferedImage[3];
		npc2Left = new BufferedImage[3];
		npc2Right = new BufferedImage[3];
		npc2Up = new BufferedImage[3];

		npc2Down[0] = npcSheet.crop(npcWidth+288, 0, npcWidth, npcHeight);
		npc2Down[1] = npcSheet.crop(0+288, 0, npcWidth, npcHeight);
		npc2Down[2] = npcSheet.crop(npcWidth+288, 0, npcWidth, npcHeight);
		npc2Down[2] = npcSheet.crop(npcWidth*2+288, 0, npcWidth, npcHeight);

		npc2Left[0] = npcSheet.crop(npcWidth+288, npcHeight, npcWidth, npcHeight);
		npc2Left[1] = npcSheet.crop(0+288, npcHeight, npcWidth, npcHeight);
		npc2Left[2] = npcSheet.crop(npcWidth+288, npcHeight, npcWidth, npcHeight);
		npc2Left[2] = npcSheet.crop(npcWidth*2+288, 0, npcWidth, npcHeight);
		
		npc2Right[0] = npcSheet.crop(npcWidth+288, npcHeight * 2, npcWidth, npcHeight);
		npc2Right[1] = npcSheet.crop(0+288, npcHeight * 2, npcWidth, npcHeight);
		npc2Right[2] = npcSheet.crop(npcWidth+288, npcHeight * 2, npcWidth, npcHeight);
		npc2Right[2] = npcSheet.crop(npcWidth*2+288, npcHeight * 2, npcWidth, npcHeight);

		npc2Up[0] = npcSheet.crop(npcWidth+288, npcHeight * 3, npcWidth, npcHeight);
		npc2Up[1] = npcSheet.crop(0+288, npcHeight * 3, npcWidth, npcHeight);
		npc2Up[2] = npcSheet.crop(npcWidth+288, npcHeight * 3, npcWidth, npcHeight);
		npc2Up[2] = npcSheet.crop(npcWidth*2+288, npcHeight * 3, npcWidth, npcHeight);
		
		npc3Down = new BufferedImage[3];
		npc3Left = new BufferedImage[3];
		npc3Right = new BufferedImage[3];
		npc3Up = new BufferedImage[3];

		npc3Down[0] = npcSheet.crop(npcWidth+576, 0, npcWidth, npcHeight);
		npc3Down[1] = npcSheet.crop(0+576, 0, npcWidth, npcHeight);
		npc3Down[2] = npcSheet.crop(npcWidth+576, 0, npcWidth, npcHeight);
		npc3Down[2] = npcSheet.crop(npcWidth*2+576, 0, npcWidth, npcHeight);

		npc3Left[0] = npcSheet.crop(npcWidth+576, npcHeight, npcWidth, npcHeight);
		npc3Left[1] = npcSheet.crop(0+576, npcHeight, npcWidth, npcHeight);
		npc3Left[2] = npcSheet.crop(npcWidth+576, npcHeight, npcWidth, npcHeight);
		npc3Left[2] = npcSheet.crop(npcWidth*2+576, 0, npcWidth, npcHeight);
		
		npc3Right[0] = npcSheet.crop(npcWidth+576, npcHeight * 2, npcWidth, npcHeight);
		npc3Right[1] = npcSheet.crop(0+576, npcHeight * 2, npcWidth, npcHeight);
		npc3Right[2] = npcSheet.crop(npcWidth+576, npcHeight * 2, npcWidth, npcHeight);
		npc3Right[2] = npcSheet.crop(npcWidth*2+576, npcHeight * 2, npcWidth, npcHeight);

		npc3Up[0] = npcSheet.crop(npcWidth+576, npcHeight * 3, npcWidth, npcHeight);
		npc3Up[1] = npcSheet.crop(0+576, npcHeight * 3, npcWidth, npcHeight);
		npc3Up[2] = npcSheet.crop(npcWidth+576, npcHeight * 3, npcWidth, npcHeight);
		npc3Up[2] = npcSheet.crop(npcWidth*2+576, npcHeight * 3, npcWidth, npcHeight);
		
		npc4Down = new BufferedImage[3];
		npc4Left = new BufferedImage[3];
		npc4Right = new BufferedImage[3];
		npc4Up = new BufferedImage[3];

		npc4Down[0] = npcSheet.crop(npcWidth, 0+384, npcWidth, npcHeight);
		npc4Down[1] = npcSheet.crop(0, 0+384, npcWidth, npcHeight);
		npc4Down[2] = npcSheet.crop(npcWidth, 0+384, npcWidth, npcHeight);
		npc4Down[2] = npcSheet.crop(npcWidth*2, 0+384, npcWidth, npcHeight);

		npc4Left[0] = npcSheet.crop(npcWidth, npcHeight+384, npcWidth, npcHeight);
		npc4Left[1] = npcSheet.crop(0, npcHeight+384, npcWidth, npcHeight);
		npc4Left[2] = npcSheet.crop(npcWidth, npcHeight+384, npcWidth, npcHeight);
		npc4Left[2] = npcSheet.crop(npcWidth*2, 0, npcWidth+384, npcHeight);
		
		npc4Right[0] = npcSheet.crop(npcWidth, npcHeight * 2+384, npcWidth, npcHeight);
		npc4Right[1] = npcSheet.crop(0, npcHeight * 2+384, npcWidth, npcHeight);
		npc4Right[2] = npcSheet.crop(npcWidth, npcHeight * 2+384, npcWidth, npcHeight);
		npc4Right[2] = npcSheet.crop(npcWidth*2, npcHeight * 2+384, npcWidth, npcHeight);

		npc4Up[0] = npcSheet.crop(npcWidth, npcHeight * 3+384, npcWidth, npcHeight);
		npc4Up[1] = npcSheet.crop(0, npcHeight * 3+384, npcWidth, npcHeight);
		npc4Up[2] = npcSheet.crop(npcWidth, npcHeight * 3+384, npcWidth, npcHeight);
		npc4Up[2] = npcSheet.crop(npcWidth*2, npcHeight * 3+384, npcWidth, npcHeight);
		
		npc5Down = new BufferedImage[3];
		npc5Left = new BufferedImage[3];
		npc5Right = new BufferedImage[3];
		npc5Up = new BufferedImage[3];

		npc5Down[0] = npcSheet.crop(npcWidth+288, 0+384, npcWidth, npcHeight);
		npc5Down[1] = npcSheet.crop(0+288, 0+384, npcWidth, npcHeight);
		npc5Down[2] = npcSheet.crop(npcWidth+288, 0+384, npcWidth, npcHeight);
		npc5Down[2] = npcSheet.crop(npcWidth*2+288, 0+384, npcWidth, npcHeight);

		npc5Left[0] = npcSheet.crop(npcWidth+288, npcHeight+384, npcWidth, npcHeight);
		npc5Left[1] = npcSheet.crop(0+288, npcHeight+384, npcWidth, npcHeight);
		npc5Left[2] = npcSheet.crop(npcWidth+288, npcHeight+384, npcWidth, npcHeight);
		npc5Left[2] = npcSheet.crop(npcWidth*2+288, 0+384, npcWidth, npcHeight);
		
		npc5Right[0] = npcSheet.crop(npcWidth+288, npcHeight * 2+384, npcWidth, npcHeight);
		npc5Right[1] = npcSheet.crop(0+288, npcHeight * 2+384, npcWidth, npcHeight);
		npc5Right[2] = npcSheet.crop(npcWidth+288, npcHeight * 2+384, npcWidth, npcHeight);
		npc5Right[2] = npcSheet.crop(npcWidth*2+288, npcHeight * 2+384, npcWidth, npcHeight);

		npc5Up[0] = npcSheet.crop(npcWidth+288, npcHeight * 3+384, npcWidth, npcHeight);
		npc5Up[1] = npcSheet.crop(0+288, npcHeight * 3+384, npcWidth, npcHeight);
		npc5Up[2] = npcSheet.crop(npcWidth+288, npcHeight * 3+384, npcWidth, npcHeight);
		npc5Up[2] = npcSheet.crop(npcWidth*2+288, npcHeight * 3+384, npcWidth, npcHeight);
		
		npc6Down = new BufferedImage[3];
		npc6Left = new BufferedImage[3];
		npc6Right = new BufferedImage[3];
		npc6Up = new BufferedImage[3];

		npc6Down[0] = npcSheet.crop(npcWidth+576, 0+384, npcWidth, npcHeight);
		npc6Down[1] = npcSheet.crop(0+576, 0+384, npcWidth, npcHeight);
		npc6Down[2] = npcSheet.crop(npcWidth+576, 0+384, npcWidth, npcHeight);
		npc6Down[2] = npcSheet.crop(npcWidth*2+576, 0+384, npcWidth, npcHeight);

		npc6Left[0] = npcSheet.crop(npcWidth+576, npcHeight+384, npcWidth, npcHeight);
		npc6Left[1] = npcSheet.crop(0+576, npcHeight+384, npcWidth, npcHeight);
		npc6Left[2] = npcSheet.crop(npcWidth+576, npcHeight+384, npcWidth, npcHeight);
		npc6Left[2] = npcSheet.crop(npcWidth*2+576, 0+384, npcWidth, npcHeight);
		
		npc6Right[0] = npcSheet.crop(npcWidth+576, npcHeight * 2+384, npcWidth, npcHeight);
		npc6Right[1] = npcSheet.crop(0+576, npcHeight * 2+384, npcWidth, npcHeight);
		npc6Right[2] = npcSheet.crop(npcWidth+576, npcHeight * 2+384, npcWidth, npcHeight);
		npc6Right[2] = npcSheet.crop(npcWidth*2+576, npcHeight * 2+384, npcWidth, npcHeight);

		npc6Up[0] = npcSheet.crop(npcWidth+576, npcHeight * 3+384, npcWidth, npcHeight);
		npc6Up[1] = npcSheet.crop(0+576, npcHeight * 3+384, npcWidth, npcHeight);
		npc6Up[2] = npcSheet.crop(npcWidth+576, npcHeight * 3+384, npcWidth, npcHeight);
		npc6Up[2] = npcSheet.crop(npcWidth*2+576, npcHeight * 3+384, npcWidth, npcHeight);
		
		
	}

	public void BujakInit() {
		bujakSheet = new SpriteSheet(ImageLoader.loadImage("/CharacterSprites/Bujak/Bujak.png"));
//		bujakWidth = 31;
//		bujakHeight = 32;


		BujakIdle  = new BufferedImage[8];
		BujakWalk = new BufferedImage[8];
		BujakSpin = new BufferedImage[8];
		Bujakattack = new BufferedImage[6];
		BujakHit = new BufferedImage[4];

		BujakIdle[0] = bujakSheet.crop(2, 4, 72, 80);
		BujakIdle[1] = bujakSheet.crop(79, 4, 66, 80);
		BujakIdle[2] = bujakSheet.crop(152, 4, 59, 80);
		BujakIdle[3] = bujakSheet.crop(216, 4, 61, 80);
		BujakIdle[4] = bujakSheet.crop(282, 4, 67, 80);
		BujakIdle[5] = bujakSheet.crop(354, 4, 67, 80);
		BujakIdle[6] = bujakSheet.crop(426, 4, 79, 80);
		BujakIdle[7] = bujakSheet.crop(510, 4, 80, 80);

		BujakWalk[0] = bujakSheet.crop(2, 88, 97, 85);
		BujakWalk[1] = bujakSheet.crop(107, 88, 112, 85);
		BujakWalk[2] = bujakSheet.crop(231, 88, 101, 85);
		BujakWalk[3] = bujakSheet.crop(340, 88, 68, 85);
		BujakWalk[4] = bujakSheet.crop(418, 88, 57, 85);
		BujakWalk[5] = bujakSheet.crop(481, 88, 59, 85);
		BujakWalk[6] = bujakSheet.crop(546, 88, 72, 85);
		BujakWalk[7] = bujakSheet.crop(627, 88, 80, 85);

		BujakSpin[0] = bujakSheet.crop(2, 177, 57, 92);
		BujakSpin[1] = bujakSheet.crop(69, 177, 86, 92);
		BujakSpin[2] = bujakSheet.crop(162, 177, 70, 92);
		BujakSpin[3] = bujakSheet.crop(239, 177, 71, 92);
		BujakSpin[4] = bujakSheet.crop(320, 177, 124, 92);
		BujakSpin[5] = bujakSheet.crop(450, 177, 82, 92);
		BujakSpin[6] = bujakSheet.crop(542, 177, 112, 92);
		BujakSpin[7] = bujakSheet.crop(662, 177, 57, 92);

		Bujakattack[0] = bujakSheet.crop(3, 274, 68, 108);
		Bujakattack[1] = bujakSheet.crop(79, 274, 62, 108);
		Bujakattack[2] = bujakSheet.crop(151, 274, 66, 108);
		Bujakattack[3] = bujakSheet.crop(222, 274, 95, 108);
		Bujakattack[4] = bujakSheet.crop(325, 274, 68, 108);
		Bujakattack[5] = bujakSheet.crop(411, 274, 61, 108);

		BujakHit[0] = bujakSheet.crop(0, 385, 80, 84);
		BujakHit[1] = bujakSheet.crop(88, 385, 88, 84);
		BujakHit[2] = bujakSheet.crop(194, 385, 93, 84);
		BujakHit[3] = bujakSheet.crop(297, 385, 87, 84);

	}

	public void playerInit() {

		if(Handler.isCheat()) {
			playerSheet = new SpriteSheet(ImageLoader.loadImage("/CharacterSprites/Player/Maximus.png"));
			playerHeight = 47;
			playerWidth = 38;
		}else if(Handler.getChar() == 1){
			playerSheet = new SpriteSheet(ImageLoader.loadImage("/CharacterSprites/Player/cowboy1.png"));
			playerWidth = 32;
			playerHeight = 32;
		} else if(Handler.getChar() == 2){
			playerSheet = new SpriteSheet(ImageLoader.loadImage("/CharacterSprites/Player/cowboy2.png"));
			playerWidth = 32;
			playerHeight = 32;
		} else if(Handler.getChar() == 3){
			playerSheet = new SpriteSheet(ImageLoader.loadImage("/CharacterSprites/Player/cowboy3.png"));
			playerWidth = 32;
			playerHeight = 32;
		} else {
			playerSheet = new SpriteSheet(ImageLoader.loadImage("/CharacterSprites/Player/Hoodie.png"));
			playerWidth = 31;
			playerHeight = 32;
		}

		playerDown = new BufferedImage[4];
		playerLeft = new BufferedImage[4];
		playerRight = new BufferedImage[4];
		playerUp = new BufferedImage[4];

		playerDown[0] = playerSheet.crop(playerWidth, 0, playerWidth, playerHeight);
		playerDown[1] = playerSheet.crop(0, 0, playerWidth, playerHeight);
		playerDown[2] = playerSheet.crop(playerWidth, 0, playerWidth, playerHeight);
		playerDown[3] = playerSheet.crop(playerWidth * 2, 0, playerWidth, playerHeight);

		playerLeft[0] = playerSheet.crop(playerWidth, playerHeight, playerWidth, playerHeight);
		playerLeft[1] = playerSheet.crop(0, playerHeight, playerWidth, playerHeight);
		playerLeft[2] = playerSheet.crop(playerWidth, playerHeight, playerWidth, playerHeight);
		playerLeft[3] = playerSheet.crop(playerWidth * 2, playerHeight, playerWidth, playerHeight);

		playerRight[0] = playerSheet.crop(playerWidth, playerHeight * 2, playerWidth, playerHeight);
		playerRight[1] = playerSheet.crop(0, playerHeight * 2, playerWidth, playerHeight);
		playerRight[2] = playerSheet.crop(playerWidth, playerHeight * 2, playerWidth, playerHeight);
		playerRight[3] = playerSheet.crop(playerWidth * 2, playerHeight * 2, playerWidth, playerHeight);

		playerUp[0] = playerSheet.crop(playerWidth, playerHeight * 3, playerWidth, playerHeight);
		playerUp[1] = playerSheet.crop(0, playerHeight * 3, playerWidth, playerHeight);
		playerUp[2] = playerSheet.crop(playerWidth, playerHeight * 3, playerWidth, playerHeight);
		playerUp[3] = playerSheet.crop(playerWidth * 2, playerHeight * 3, playerWidth, playerHeight);


	}

	public static BufferedImage getEnemies(int i) {
		return enemies[i];
	}

	public static BufferedImage getPlayerUi() {
		return playerUi;
	}

	public static void setPlayerUi(BufferedImage playerUi) {
		Assets.playerUi = playerUi;
	}

	public static BufferedImage getWeaponUi() {
		return weaponUi;
	}

	public static void setWeaponUi(BufferedImage weaponUi) {
		Assets.weaponUi = weaponUi;
	}

	public static void setRock(BufferedImage rock) {
		Assets.rock = rock;
	}

	public static BufferedImage[] getPlayerUp() {
		return playerUp;
	}

	public static BufferedImage[] getPlayerDown() {
		return playerDown;
	}

	public static BufferedImage getRock() {
		return rock;
	}

	public static BufferedImage[] getPlayerRight() {
		return playerRight;
	}

	public static BufferedImage[] getPlayerLeft() {
		return playerLeft;
	}

	public static int getNpcWidth() {
		return npcWidth;
	}

	public static int getNpcHeight() {
		return npcHeight;
	}

	public static BufferedImage[] getNpc1Up() {
		return npc1Up;
	}

	public static BufferedImage[] getNpc1Down() {
		return npc1Down;
	}

	public static BufferedImage[] getNpc1Right() {
		return npc1Right;
	}

	public static BufferedImage[] getNpc1Left() {
		return npc1Left;
	}

	public static BufferedImage[] getNpc2Up() {
		return npc2Up;
	}

	public static BufferedImage[] getNpc2Down() {
		return npc2Down;
	}

	public static BufferedImage[] getNpc2Right() {
		return npc2Right;
	}

	public static BufferedImage[] getNpc2Left() {
		return npc2Left;
	}

	public static BufferedImage[] getNpc3Up() {
		return npc3Up;
	}

	public static BufferedImage[] getNpc3Down() {
		return npc3Down;
	}

	public static BufferedImage[] getNpc3Right() {
		return npc3Right;
	}

	public static BufferedImage[] getNpc3Left() {
		return npc3Left;
	}

	public static BufferedImage[] getNpc4Up() {
		return npc4Up;
	}

	public static BufferedImage[] getNpc4Down() {
		return npc4Down;
	}

	public static BufferedImage[] getNpc4Right() {
		return npc4Right;
	}

	public static BufferedImage[] getNpc4Left() {
		return npc4Left;
	}

	public static BufferedImage[] getNpc5Up() {
		return npc5Up;
	}

	public static BufferedImage[] getNpc5Down() {
		return npc5Down;
	}

	public static BufferedImage[] getNpc5Right() {
		return npc5Right;
	}

	public static BufferedImage[] getNpc5Left() {
		return npc5Left;
	}

	public static BufferedImage[] getNpc6Up() {
		return npc6Up;
	}

	public static BufferedImage[] getNpc6Down() {
		return npc6Down;
	}

	public static BufferedImage[] getNpc6Right() {
		return npc6Right;
	}

	public static BufferedImage[] getNpc6Left() {
		return npc6Left;
	}

	public static BufferedImage getTextBox() {
		return textBox;
	}

	public static BufferedImage getHearts(int i) {
		return hearts[i];
	}

	public static BufferedImage getPlayerProfile() {
		return playerProfile;
	}

	public static BufferedImage getBujakProfile() {
		return bujakProfile;
	}

	public static BufferedImage[] getBujakIdle() {
		return BujakIdle;
	}

	public static BufferedImage[] getBujakWalk() {
		return BujakWalk;
	}

	public static BufferedImage[] getBujakSpin() {
		return BujakSpin;
	}

	public static BufferedImage[] getBujakattack() {
		return Bujakattack;
	}

	public static BufferedImage[] getBujakHit() {
		return BujakHit;
	}

	public static BufferedImage getVillager() {
		return villager;
	}
}
