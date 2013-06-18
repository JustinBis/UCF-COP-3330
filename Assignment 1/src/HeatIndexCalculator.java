/* **********************
 * Name: Justin Bisignano
 * COP 3330 – Spring 2013 
 * Assignment title: Program 1: Creating A Java Application From A UML Diagram 
 * Date: January 30, 2013 
 * ********************** */

import java.util.Scanner;

public class HeatIndexCalculator {
	
	private int temperature;
	private double humidity, heatIndex;
	
	public static void main(String[] args) {
		// Create a new HeatIndexCalculator object and a Scanner object
		HeatIndexCalculator h = new HeatIndexCalculator();
		Scanner input = new Scanner(System.in);
		
		// Ask for inputs and store results
		System.out.println("Please enter the current temperature in degrees Fahrenheit:");
		h.temperature = input.nextInt();
		System.out.println("Please enter the current humidity as a percentage:");
		h.humidity = input.nextDouble();
		
		// We're done asking for inputs, so close the scanner
		input.close();
		
		// Calculate the heat index and store it in heatIndex
		h.heatIndex = calculateHeatIndex(h.temperature, h.humidity);
		
		// Finally, print the results
		printHeatIndex(h.temperature, h.humidity, h.heatIndex);
	}
	
	private static double calculateHeatIndex(int currentTemp, double currentHumidity) {
		// Initialize an array of constants for ease of calculation
		final double[] c = {-42.379, 2.04901523, 10.14333127, -0.22475541, -.00683783, -.05481717, .00122874, .00085282, -.00000199};

		// Perform the calculation
		double calculation = c[0]+c[1]*currentTemp + c[2]*currentHumidity+c[3]*currentTemp*currentHumidity + c[4]*currentTemp*currentTemp +
				c[5]*currentHumidity*currentHumidity  +c[6]*currentTemp*currentTemp*currentHumidity +
				c[7]*currentTemp*currentHumidity*currentHumidity + c[8]*currentTemp*currentTemp*currentHumidity*currentHumidity;
		
		// Return the resulting number
		return calculation;
		
		
	}
	
	private static void printHeatIndex(int currentTemp, double currentHumidity, double calculatedHeatIndex) {
		// Print the final output
		System.out.println("At a temperature of "+currentTemp+"F and a humidity of "+currentHumidity+" percent . . .");
		// Use printf instead of println to make formatting the number to two decimal places simpler.
		System.out.printf("It actually feels like: %.2fF\n", calculatedHeatIndex);
	}

}
