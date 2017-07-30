/* Max Rink
 * ICS4U
 * June 1 2016
 * Launch.java
 * 
 */
import java.util.Scanner;

import javax.swing.JFrame;

public class Launch {
	
	private static int w = 1280, h = w/16*9;
	private static Display display;
	private static final String TITLE = "<= The Crusades =>";
	private static Musical music;
	private static GameState gameState;

	public static void main(String[] args){
		
		Scanner input = new Scanner(System.in);
		
		//System.out.println("Welcome to The Crusades!");
//		System.out.println("    _          _________          _______    _______  _______           _______  _______  ______   _______  _______           _       \n"
//				+ "   / )         \\__   __/|\\     /|(  ____ \\  (  ____ \\(  ____ )|\\     /|(  ____ \\(  ___  )(  __  \\ (  ____ \\(  ____ \\         ( \\      \n"
//				+ "  / /    ___      ) (   | )   ( || (    \\/  | (    \\/| (    )|| )   ( || (    \\/| (   ) || (  \\  )| (    \\/| (    \\/   ___    \\ \\     \n"
//				+ " / /    (___)     | |   | (___) || (__      | |      | (____)|| |   | || (_____ | (___) || |   ) || (__    | (_____   (___)    \\ \\    \n"
//				+ "( (      ___      | |   |  ___  ||  __)     | |      |     __)| |   | |(_____  )|  ___  || |   | ||  __)   (_____  )   ___      ) )   \n"
//				+ " \\ \\    (___)     | |   | (   ) || (        | |      | (\\ (   | |   | |      ) || (   ) || |   ) || (            ) |  (___)    / /    \n"
//				+ "  \\ \\             | |   | )   ( || (____/\\  | (____/\\| ) \\ \\__| (___) |/\\____) || )   ( || (__/  )| (____/\\/\\____) |          / /     \n"
//				+ "   \\_)            )_(   |/     \\|(_______/  (_______/|/   \\__/(_______)\\_______)|/     \\|(______/ (_______/\\_______)         (_/      \n"
//				+ "                                                                                                                                      \n"
//				+ " _       _       _      _________          _______    _______  _______ _________   _______  _______  _______       _       _       _  \n"
//				+ "( \\     ( \\     ( \\     \\__   __/|\\     /|(  ____ \\  (  ____ )(  ____ \\\\__   __/  (  ____ )(  ____ )(  ____ \\     / )     / )     / ) \n"
//				+ " \\ \\     \\ \\     \\ \\       ) (   | )   ( || (    \\/  | (    )|| (    \\/   ) (     | (    )|| (    )|| (    \\/    / /     / /     / /  \n"
//				+ "  \\ \\     \\ \\     \\ \\      | |   | (___) || (__      | (____)|| (_____    | |     | (____)|| (____)|| |         / /     / /     / /   \n"
//				+ "   ) )     ) )     ) )     | |   |  ___  ||  __)     |     __)(_____  )   | |     |     __)|  _____)| | ____   ( (     ( (     ( (    \n"
//				+ "  / /     / /     / /      | |   | (   ) || (        | (\\ (         ) |   | |     | (\\ (   | (      | | \\_  )   \\ \\     \\ \\     \\ \\   \n"
//				+ " / /     / /     / /       | |   | )   ( || (____/\\  | ) \\ \\__/\\____) |   | |     | ) \\ \\__| )      | (___) |    \\ \\     \\ \\     \\ \\  \n"
//				+ "(_/     (_/     (_/        )_(   |/     \\|(_______/  |/   \\__/\\_______)   )_(     |/   \\__/|/       (_______)     \\_)     \\_)     \\_) \n"
//				+ "                                                                                                                                      \n"
//				+ "                        ______                 _______  _______             _______ _________ _        _                              \n"
//				+ "                       (  ___ \\ |\\     /|     (       )(  ___  )|\\     /|  (  ____ )\\__   __/( (    /|| \\    /\\                       \n"
//				+ "                       | (   ) )( \\   / ) _   | () () || (   ) |( \\   / )  | (    )|   ) (   |  \\  ( ||  \\  / /                       \n"
//				+ "                       | (__/ /  \\ (_) / (_)  | || || || (___) | \\ (_) /   | (____)|   | |   |   \\ | ||  (_/ /                        \n"
//				+ "                       |  __ (    \\   /       | |(_)| ||  ___  |  ) _ (    |     __)   | |   | (\\ \\) ||   _ (                         \n"
//				+ "                       | (  \\ \\    ) (    _   | |   | || (   ) | / ( ) \\   | (\\ (      | |   | | \\   ||  ( \\ \\                        \n"
//				+ "                       | )___) )   | |   (_)  | )   ( || )   ( |( /   \\ )  | ) \\ \\_____) (___| )  \\  ||  /  \\ \\                       \n"
//				+ " _____  _____  _____   |/ \\___/    \\_/        |/     \\||/     \\||/     \\|  |/   \\__/\\_______/|/    )_)|_/    \\/   _____  _____  _____ \n"
//				+ "(_____)(_____)(_____)                                                                                            (_____)(_____)(_____)");
		
		
		System.out.println("You, Maximus Prime, have crash landed on a primitive world named earth whilst in pursuit of the greatest criminal in the Universe: \"The Great Bujak\"\n" +
				"He has stolen your 100 in Computer Science and refuses to give it to you, no matter how much you deserve it\n" +
				"You are far more advanced than the primary inhabitants of the planet, humans\n" +
				"You seem to have landed in some remote county in a horrid desert called \"Sweaty Post\"\n" +
				"You have already disguised yourself to appear like the humans\n" +
				"You have also managed to find a revolver, oddly inaccurate and only holds 6 bullets for no reason at all apparently\n" +
				"You can move with W A S D\n" +
				"You have the ability to release energy from your body by hitting 3\n" +
				"You have the ability to teleport short distances with the SPACEBAR\n" +
				"Unfortunately this results in your getting stuck in walls sometimes\n" +
				"Luckily if you can blink into it you can blink out of it\n" +
				"It is now time to defeat The Great Bujak once and for all...\n");
		//WRITE SOME BS STORY
		
		System.out.println("Are you ready to begin? enter 1, 2 or 3 for different characters \n[ENTER]");
		String in = input.nextLine();
		if(in.toLowerCase().equals("maximusprime")){
			Handler.setCheat(true);
		} else if(in.startsWith("1")){
				Handler.setChar(1);
		} else if(in.startsWith("2")){
			Handler.setChar(2);
		} else if(in.startsWith("3")){
			Handler.setChar(3);
		}
		music = new Musical();
		Handler.setMusicClass(music);
		MenuState menu = new MenuState();
		StateManager.setCurrent(menu, 0);
		display = new Display(w, h, TITLE, true, JFrame.EXIT_ON_CLOSE);
		Game game = new Game(w, h, display);
		gameState = new GameState();
//		StateManager.setCurrent(gameState, 0);
		
		//music = new Musical();
		Handler.setGameState(gameState);
		Handler.setMenu(menu);
		Handler.setW(w);
		Handler.setH(h);
		Handler.setTitle(TITLE);
		Handler.setDisplay(display);
		Handler.setGame(game);
		
		game.start();
		
	}
	
}
