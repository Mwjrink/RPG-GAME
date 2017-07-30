/* Max Rink
 * ICS4U
 * June 1 2016
 * Utils.java
 * 
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Utils {
	
	public static String loadFileAsString(String path){
		StringBuilder bob = new StringBuilder();
		
		try{
			BufferedReader br = new BufferedReader(new FileReader(path));
			String line;
			
			while((line = br.readLine()) != null){
				bob.append(line + "\n");
				
			}
			
			br.close();
			
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		return bob.toString();
	}
	
	
	public static int parseInt(String num){
		try{
			return Integer.parseInt(num);
		} catch(NumberFormatException e){
			e.printStackTrace();
			return 0;
		}
	}
	
}
