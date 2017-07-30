/* Max Rink
 * ICS4U
 * June 1 2016
 * StateManager.java
 * 
 */


public class StateManager {
	
	private static State current = null;

	public static State getCurrent() {
		return current;
	}

	public static void setCurrent(State c, int i) {
//		System.out.println("set by " + i);
		current = c;
	}
	
	
	
}
