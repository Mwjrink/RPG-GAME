/* Max Rink
 * ICS4U
 * June 1 2016
 * Quadtree.java
 * 
 */
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

public class Quadtree {
/*
	private int MAX_OBJECTS = 10;
	private int MAX_LEVELS = 5;

	private int level;
	private List objects;
	private Rectangle bounds;
	private Quadtree[] nodes;

	
	public Quadtree(int pLevel, Rectangle pBounds) {
		level = pLevel;
		objects = new ArrayList();
		bounds = pBounds;
		nodes = new Quadtree[4];
	}

	public void clear() {
		objects.clear();

		for (int i = 0; i < nodes.length; i++) {
			if (nodes[i] != null) {
				nodes[i].clear();
				nodes[i] = null;
			}
		}
	}

	private void split() {
		int subWidth = (int) (bounds.getWidth() / 2);
		int subHeight = (int) (bounds.getHeight() / 2);
		int x = (int) bounds.getX();
		int y = (int) bounds.getY();

		nodes[0] = new Quadtree(level + 1, new Rectangle(x + subWidth, y, subWidth, subHeight));
		nodes[1] = new Quadtree(level + 1, new Rectangle(x, y, subWidth, subHeight));
		nodes[2] = new Quadtree(level + 1, new Rectangle(x, y + subHeight, subWidth, subHeight));
		nodes[3] = new Quadtree(level + 1, new Rectangle(x + subWidth, y + subHeight, subWidth, subHeight));
	}

	private int getIndex(Rectangle pRect) {
		int index = -1;
		double verticalMidpoint = bounds.getX() + (bounds.getWidth() / 2);
		double horizontalMidpoint = bounds.getY() + (bounds.getHeight() / 2);

		// Object can completely fit within the top quadrants
		boolean topQuadrant = (pRect.getY() < horizontalMidpoint
				&& pRect.getY() + pRect.getHeight() < horizontalMidpoint);
		// Object can completely fit within the bottom quadrants
		boolean bottomQuadrant = (pRect.getY() > horizontalMidpoint);

		// Object can completely fit within the left quadrants
		if (pRect.getX() < verticalMidpoint && pRect.getX() + pRect.getWidth() < verticalMidpoint) {
			if (topQuadrant) {
				index = 1;
			} else if (bottomQuadrant) {
				index = 2;
			}
		}
		// Object can completely fit within the right quadrants
		else if (pRect.getX() > verticalMidpoint) {
			if (topQuadrant) {
				index = 0;
			} else if (bottomQuadrant) {
				index = 3;
			}
		}

		return index;
	}

	public void insert(Rectangle pRect) {
		if (nodes[0] != null) {
			int index = getIndex(pRect);

			if (index != -1) {
				nodes[index].insert(pRect);

				return;
			}
		}

		objects.add(pRect);

		if (objects.size() > MAX_OBJECTS && level < MAX_LEVELS) {
			if (nodes[0] == null) {
				split();
			}

			int i = 0;
			while (i < objects.size()) {
				int index = getIndex(objects.get(i));
				if (index != -1) {
					nodes[index].insert(objects.remove(i));
				} else {
					i++;
				}
			}
		}
	}

	public List retrieve(List returnObjects, Rectangle pRect) {
		int index = getIndex(pRect);
		if (index != -1 && nodes[0] != null) {
			nodes[index].retrieve(returnObjects, pRect);
		}

		returnObjects.addAll(objects);

		return returnObjects;
	}*/
}


/*
public class Quadtree<Key extends Comparable<Key>, Value>  {
    private Node root;

    // helper node data type
    private class Node {
        Key x, y;              // x- and y- coordinates
        Node NW, NE, SE, SW;   // four subtrees
        Value value;           // associated data

        Node(Key x, Key y, Value value) {
            this.x = x;
            this.y = y;
            this.value = value;
        }
    }



    public void insert(Key x, Key y, Value value) {
        root = insert(root, x, y, value);
    }

    private Node insert(Node h, Key x, Key y, Value value) {
        if (h == null) return new Node(x, y, value);
        //// if (eq(x, h.x) && eq(y, h.y)) h.value = value;  // duplicate
        else if ( less(x, h.x) &&  less(y, h.y)) h.SW = insert(h.SW, x, y, value);
        else if ( less(x, h.x) && !less(y, h.y)) h.NW = insert(h.NW, x, y, value);
        else if (!less(x, h.x) &&  less(y, h.y)) h.SE = insert(h.SE, x, y, value);
        else if (!less(x, h.x) && !less(y, h.y)) h.NE = insert(h.NE, x, y, value);
        return h;
    }




    public void query2D(Interval2D<Key> rect) {
        query2D(root, rect);
    }

    private void query2D(Node h, Interval2D<Key> rect) {
        if (h == null) return;
        Key xmin = rect.intervalX.min();
        Key ymin = rect.intervalY.min();
        Key xmax = rect.intervalX.max();
        Key ymax = rect.intervalY.max();
        if (rect.contains(h.x, h.y))
            StdOut.println("    (" + h.x + ", " + h.y + ") " + h.value);
        if ( less(xmin, h.x) &&  less(ymin, h.y)) query2D(h.SW, rect);
        if ( less(xmin, h.x) && !less(ymax, h.y)) query2D(h.NW, rect);
        if (!less(xmax, h.x) &&  less(ymin, h.y)) query2D(h.SE, rect);
        if (!less(xmax, h.x) && !less(ymax, h.y)) query2D(h.NE, rect);
    }




    private boolean less(Key k1, Key k2) { return k1.compareTo(k2) <  0; }
    private boolean eq  (Key k1, Key k2) { return k1.compareTo(k2) == 0; }



    public static void main(String[] args) {
        int M = Integer.parseInt(args[0]);   // queries
        int N = Integer.parseInt(args[1]);   // points

        Quadtree<Integer, String> st = new Quadtree<Integer, String>();

        // insert N random points in the unit square
        for (int i = 0; i < N; i++) {
            Integer x = (int) (100 * Math.random());
            Integer y = (int) (100 * Math.random());
            // StdOut.println("(" + x + ", " + y + ")");
            st.insert(x, y, "P" + i);
        }
        StdOut.println("Done preprocessing " + N + " points");

        // do some range searches
        for (int i = 0; i < M; i++) {
            Integer xmin = (int) (100 * Math.random());
            Integer ymin = (int) (100 * Math.random());
            Integer xmax = xmin + (int) (10 * Math.random());
            Integer ymax = ymin + (int) (20 * Math.random());
            Interval<Integer> intX = new Interval<Integer>(xmin, xmax);
            Interval<Integer> intY = new Interval<Integer>(ymin, ymax);
            Interval2D<Integer> rect = new Interval2D<Integer>(intX, intY);
            StdOut.println(rect + " : ");
            st.query2D(rect);
        }
    }

}*/