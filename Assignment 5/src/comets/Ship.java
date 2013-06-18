/* ****************************************
 * Name: Justin Bisignano
 * COP 3330 – Spring 2013 
 * Program 5: Comets
 * Class: Ship
 * Date: April 19, 2013 
 * **************************************** */

package comets;

public class Ship extends SpaceObject {
	
	///////////////
	// CONSTANTS //
	///////////////
	/**
	 * The radius of the ship
	 */
	private static final double shipRadius = 10;
	
	/**
	 * The turning rate of the ship in radians/frame
	 */
	private static final double SHIP_TURNING_RATE = 0.1;
	
	/**
	 * The ship's acceleration in pixels/frame^2
	 */
	private static final double SHIP_ACCELERATION = 0.1;
	
	/**
	 * The maximum speed of the ship in pixels/frame
	 */
	private static final double MAX_SHIP_SPEED = 10;
	
	////////////////////////
	// INSTANCE VARIABLES //
	////////////////////////
	/**
	 * The angle the ship is facing in radians
	 */
	private double angle = 0;
	
	/**
	 * Creates a new ship. A ship has radius 10. A new ship is initially pointing at angle 0.
	 * @param xPos The location of the ship in the x direction
	 * @param yPos The location of the ship in the y direction
	 * @param xVel The velocity of the ship in the x direction
	 * @param yVel The velocity of the ship in the y direction
	 */
	public Ship(double xPos, double yPos, double xVel, double yVel){
		super(xPos, yPos, xVel, yVel, shipRadius);
	}
	
	/**
	 * Increases the speed of the ship by a slight amount in the direction that it is facing.
	 * If this causes the ship to be moving faster than 10 pixels per frame, then the speed is scaled down to 10 pixels per frame.
	 */
	public void accelerate(){
		xVelocity += SHIP_ACCELERATION * Math.sin(angle);
		yVelocity += SHIP_ACCELERATION * Math.cos(angle);
		
		double speed = Math.sqrt(xVelocity*xVelocity + yVelocity*yVelocity);
		
		// If the ship's speed is over the maximum, scale the speed down by multiplying each velocity by the max speed devided by the current speed
		if(speed > MAX_SHIP_SPEED){
			xVelocity *= MAX_SHIP_SPEED / speed;
			yVelocity *= MAX_SHIP_SPEED / speed;
		}
	}
	
	/**
	 * Produces a new shot originating from the center of the ship.
	 * The shot's velocity is equal to three pixels per frame in the direction that the ship is pointing added to the ship's current velocity.
	 * @return the new shot
	 */
	public Shot fire(){
		double shotXVel = xVelocity + 3*Math.sin(angle);
		double shotYVel = yVelocity + 3*Math.cos(angle);
		Shot shot = new Shot(this.getXPosition(), this.getYPosition(), shotXVel, shotYVel);
		return shot;
	}
	
	/**
	 * Returns the direction that the ship is facing.
	 * @return Angle the ship is facing in radians.
	 */
	public double getAngle(){
		return angle;
	}
	
	/**
	 * Slightly increases this ship's angle.
	 */
	public void rotateLeft(){
		angle += SHIP_TURNING_RATE;
	}
	
	/**
	 * Slightly decreases this ship's angle.
	 */
	public void rotateRight(){
		angle -= SHIP_TURNING_RATE;
	}
}
