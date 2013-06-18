/* ****************************************
 * Name: Justin Bisignano
 * COP 3330 – Spring 2013 
 * Program 5: Comets
 * Class: Comet
 * Date: April 19, 2013 
 * **************************************** */

package comets;

public abstract class Comet extends SpaceObject {
	
	/**
	 * Creates a new comet in the specified place, with the specified velocity and size
	 * @param xPos The location of the comet in the x direction
	 * @param yPos The location of the comet in the y direction
	 * @param xVel The velocity of the comet in the x direction
	 * @param yVel The velocity of the comet in the y direction
	 * @param radius The distance from the center of the comet to its outer edge
	 */
	public Comet(double xPos, double yPos, double xVel, double yVel, double radius){
		super(xPos, yPos, xVel, yVel, radius);
	}
	
	/**
	 * This indicates to the comet that it has been shot and should break into pieces.
	 * @return A (possibly empty) vector of the comets spawned by the destruction of this comet, or null if the comet refuses to die yet
	 */
	public abstract java.util.Vector<Comet> explode();
}
