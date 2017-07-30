/* Max Rink
 * ICS4U
 * June 1 2016
 * MouseManager.java
 * 
 */


import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;



public class MouseManager implements MouseListener, MouseMotionListener {

	public static boolean leftPressed, rightPressed;
	public static int mouseX, mouseY;
	
	public MouseManager(){
		
	}
	
	
	// Getters
	
	public boolean isLeftPressed(){
		return leftPressed;
	}
	
	public boolean isRightPressed(){
		return rightPressed;
	}
	
	public int getMouseX(){
		return mouseX;
	}
	
	public int getMouseY(){
		return mouseY;
	}
	
	// Implemented methods
	
	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1)
			leftPressed = true;
		else if(e.getButton() == MouseEvent.BUTTON3)
			rightPressed = true;
		mouseX = e.getX();
//		System.out.println(mouseX);
		mouseY = e.getY();
//		System.out.println(mouseY);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1)
			leftPressed = false;
		else if(e.getButton() == MouseEvent.BUTTON3)
			rightPressed = false;
	}

	@Override
	public void mouseMoved(MouseEvent e) {
//		System.out.println("mouse moved");
		mouseX = e.getX();
//		System.out.println(mouseX);
		mouseY = e.getY();
//		System.out.println(mouseY);
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
