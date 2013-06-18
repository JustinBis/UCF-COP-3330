/* ****************************************
 * Name: Justin Bisignano
 * COP 3330 – Spring 2013 
 * Program 5: Comets
 * Class: SmallComet
 * Date: April 19, 2013 
 * **************************************** */

package comets;

import java.util.Vector;

public class SmallComet extends Comet {

	/**
	 * The radius of a small comet
	 */
	private static final double SMALL_COMET_RADIUS = 20;
	
	/**
	 * Creates a new small comet. Small comets should have radius 20.
	 * @param xPos The location of the comet in the x direction
	 * @param yPos The location of the comet in the y direction
	 * @param xVel The velocity of the comet in the x direction
	 * @param yVel The velocity of the comet in the y direction
	 */
	public SmallComet(double xPos, double yPos, double xVel, double yVel){
		super(xPos, yPos, xVel, yVel, SMALL_COMET_RADIUS);
	}
	
	/**
	 * Since this is the smallest category of comet, it should be completely destroyed if hit. As such, it does not spawn more comets.
	 * @return An empty vector
	 */
	public Vector<Comet> explode() {
		// Because we aren't making any new comets, just return an empty vector;
		Vector<Comet> comets = new Vector<Comet>();
		return comets;
	}
}
