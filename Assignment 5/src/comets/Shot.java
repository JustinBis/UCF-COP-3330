/* ****************************************
 * Name: Justin Bisignano
 * COP 3330 – Spring 2013 
 * Program 5: Comets
 * Class: Shot
 * Date: April 19, 2013 
 * **************************************** */

package comets;

public class Shot extends SpaceObject {
	
	/**
	 * The radius of a shot
	 */
	private static final double radius = 3;
	
	/**
	 * The age of the shot, i.e. the number of times move() has been called for this shot
	 */
	private int age = 0;
	
	/**
	 * Creates a new shot. Shots have radius 3. New shots have age 0.
	 * @param xPos The location of the shot in the x direction
	 * @param yPos The location of the shot in the y direction
	 * @param xVel The velocity of the shot in the x direction
	 * @param yVel The velocity of the shot in the y direction
	 */
	public Shot(double xPos, double yPos, double xVel, double yVel){
		super(xPos, yPos, xVel, yVel, radius);
	}
	
	/**
	 * Returns the age of the shot, i.e. the number of times that it has had move() called on it.
	 * @return the age of the shot
	 */
	public int getAge(){
		return age;
	}
	
	/**
	 * In addition to moving the shot, the age of the shot should be incremented by one.
	 */
	public void move(){
		super.move();
		age++;
	}
}
