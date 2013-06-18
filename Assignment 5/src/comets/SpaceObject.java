/* ****************************************
 * Name: Justin Bisignano
 * COP 3330 – Spring 2013 
 * Program 5: Comets
 * Class: SpaceObject
 * Date: April 19, 2013 
 * **************************************** */

package comets;

public abstract class SpaceObject {

	/**
	 * The size of the play area in the y direction
	 */
	public static double playfieldHeight;
	
	/**
	 * The size of the play area in the x direction
	 */
	public static double playfieldWidth;
	
	/**
	 * The rate at which the object is moving in the x direction
	 */
	protected double xVelocity;
	
	/**
	 * The rate at which the object is moving in the y direction
	 */
	protected double yVelocity;
	
	private double xPosition;
	private double yPosition;
	private double radius;
	
	/**
	 * Creates a space object in the specified place, with the specified speed, and the specified size.
	 * @param xPos The location of the object in the x direction
	 * @param yPos The location of the object in the y direction
	 * @param xVel The velocity of the object in the x direction
	 * @param yVel The velocity of the object in the y direction
	 * @param radius The distance from the center of the object to its outer edge
	 */
	public SpaceObject(double xPos, double yPos, double xVel, double yVel, double objectRadius){
		xPosition = xPos;
		yPosition = yPos;
		xVelocity = xVel;
		yVelocity = yVel;
		radius = objectRadius;
	}
	
	/**
	 * Returns the size of the object.
	 * @return the object's radius
	 */
	public double getRadius(){
		return radius;
	}
	
	/**
	 * Returns the position of the object in the x direction.
	 * @return the object's x position
	 */
	public double getXPosition(){
		return xPosition;
	}
	
	/**
	 * Returns the position of the object in the y direction.
	 * @return the object's y position
	 */
	public double getYPosition(){
		return yPosition;
	}
	
	/**
	 * Updates the position of the object based on its velocity, and wraps it around to the other side of the play field if it goes off the edge.
	 */
	public void move(){
		xPosition += xVelocity;
		yPosition += yVelocity;
		
		// Check to see if the positions are off screen, and if they are, wrap them around
		// If it's too far left, wrap it to the right
		if(xPosition < 0)
			xPosition = xPosition + playfieldWidth;
		// If it's too far right, wrap it to the left
		if(xPosition > playfieldWidth)
			xPosition = xPosition - playfieldWidth;
		// If it's too far up, wrap it around the bottom
		if(yPosition > playfieldHeight)
			yPosition = yPosition - playfieldHeight;
		// If it's too far below, wrap it around the top
		if(yPosition < 0)
			yPosition = yPosition + playfieldWidth;
	}
	
	/**
	 * Determines whether this object is overlapping with another object.
	 * @param otherObject The other object that we're comparing this one against
	 * @return true if the objects overlap and false otherwise
	 */
	public boolean overlapping(SpaceObject otherObject){
		double otherX = otherObject.getXPosition();
		double otherY = otherObject.getYPosition();
		double otherRadius = otherObject.getRadius();
		
		if(Math.sqrt(Math.pow((xPosition-otherX), 2) + Math.pow((yPosition-otherY), 2)) < radius+otherRadius)
			return true;
		else
			return false;
	}
}
