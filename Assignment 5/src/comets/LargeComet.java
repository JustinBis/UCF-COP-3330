/* ****************************************
 * Name: Justin Bisignano
 * COP 3330 – Spring 2013 
 * Program 5: Comets
 * Class: LargeComet
 * Date: April 19, 2013 
 * **************************************** */

package comets;

import java.util.Vector;
import java.util.Random;

public class LargeComet extends Comet {

	/**
	 * The radius of a large comet
	 */
	private static final double LARGE_COMET_RADIUS = 40;
	
	/**
	 * The number of medium comets a large comet explodes into when explode() is called.
	 */
	private static final int NUM_BREAKAWAY_COMETS = 2;
	
	/**
	 * A random number object to be used in the explode() method
	 */
	private Random random = new Random();
	
	/**
	 * Creates a new large comet. Large comets should have radius 40.
	 * @param xPos The location of the comet in the x direction
	 * @param yPos The location of the comet in the y direction
	 * @param xVel The velocity of the comet in the x direction
	 * @param yVel The velocity of the comet in the y direction
	 */
	public LargeComet(double xPos, double yPos, double xVel, double yVel){
		super(xPos, yPos, xVel, yVel, LARGE_COMET_RADIUS);
	}
	
	/**
	 * Splits this comet into pieces.
	 * @return A vector containing two medium comets with the same location as this comet, but randomly chosen velocities.
	 */
	public Vector<Comet> explode() {
		// Create a vector to hold the new comets
		Vector<Comet> comets = new Vector<Comet>();
		
		for(int i = 0; i<NUM_BREAKAWAY_COMETS; i++){
			// A random angle that could point in any direction
			double cometAngle = random.nextDouble()*2*Math.PI;
			// A random speed that could be anywhere from 0 to 10, inclusive;
			double cometSpeed = random.nextDouble()*10;
			
			double cometXVel = cometSpeed*Math.cos(cometAngle); // X velocity is the cosine of the angle times the speed. (Remember: SOHCAHTOA)
			double cometYVel = cometSpeed*Math.sin(cometAngle); // Y velocity is the sine of the angle times the speed.
			
			// Add a new comet to the comets vector
			comets.add(new MediumComet(this.getXPosition(), this.getYPosition(), cometXVel, cometYVel));
		}
		
		return comets;
	}

}
