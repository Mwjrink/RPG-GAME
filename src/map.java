/* Max Rink
 * ICS4U
 * June 1 2016
 * map.java
 * 
 */
import java.util.ArrayList;

public class map {
    private int startX, startY;
    private String path;
    private ArrayList<Integer[]> doors;
    private ArrayList<map> doorsTo;

    public map(int startX, int startY, String path) {
        this.startX = startX;
        this.startY = startY;
        this.path = path;
        this.doors = new ArrayList<Integer[]>();
        this.doorsTo = new ArrayList<map>();
    }

    public int getStartX() {
        return startX;
    }

    public void setStartX(int startX) {
        this.startX = startX;
    }

    public int getStartY() {
        return startY;
    }

    public void setStartY(int startY) {
        this.startY = startY;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public ArrayList<Integer[]> getDoors() {
        return doors;
    }

    public void setDoors(ArrayList<Integer[]> doors) {
        this.doors = doors;
    }

    public ArrayList<map> getDoorsTo() {
        return doorsTo;
    }

    public void setDoorsTo(ArrayList<map> doorsTo) {
        this.doorsTo = doorsTo;
    }
}
