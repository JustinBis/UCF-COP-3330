/* **********************
 * Name: Justin Bisignano
 * COP 3330 – Spring 2013 
 * Program 2: Computing Tax 
 * Date: February 6, 2013 
 * ********************** */

import java.util.Scanner;

public class TaxCalculator {
	
	// If the length of this rates array is changed, then the bracket arrays in findBracket() must also be changed
	private final static int[] rates = {10, 15, 25, 28, 33, 35};
	
	// Instance Variables
	private int filingStatus, income;
	private double tax;
	private int[] taxBracket;

	public static void main(String[] args) {

		// Create a new TaxCalculator object and a Scanner object
		TaxCalculator t = new TaxCalculator();
		Scanner input = new Scanner(System.in);
		
		// Ask for inputs and store results
		System.out.printf("Enter the filing status: ");
		t.filingStatus = input.nextInt();
		System.out.printf("Enter the taxable income: ");
		t.income = input.nextInt();
		
		// We're done asking for inputs, so close the scanner
		input.close();
		
		// Find the tax brackets based on filingStatus and fill the taxBrackets array with the correct dollar values 
		t.taxBracket = findBracket(t.filingStatus);

		//Calculate the tax
		t.tax = calculateTax(t.income, t.taxBracket, rates);
		
		// Finally, print the results
		System.out.printf("Tax is %.2f\n", t.tax);
	}
	
	// Returns a reference to the proper tax bracket array
	private static int[] findBracket(int filingStatus){
		// Setup the tax bracket values for each filing status based on the maximum dollar amount to fit in a bracket
		// Set each as final, though they could be changed to be set as instance variables if this class was needed in a larger tax program with varying brackets.
		final int[] single = {8350, 33950, 82250, 171550, 372950};
		final int[] joint = {16700, 67900, 137050, 208850, 372950};
		final int[] separately = {8350, 33950, 68525, 104425, 186475};
		final int[] headOfHousehold = {11950, 45500, 117450, 190200, 372950};
		
		// Based on the passed variable filingStatus, return the correct array
		if(filingStatus == 0)
			return single;
		if(filingStatus == 1)
			return joint;
		if(filingStatus == 2)
			return separately;
		if(filingStatus == 3)
			return headOfHousehold;
		
		// If we have a filingStatus we don't know how to handle, warn the user and just resort to returning the single tax brackets
		System.out.println("Warning: Unable to match filing status to a tax bracket. Please enter 0, 1, 2, or 3 next time. Assuming 0 (Single) for now.");
		return single;
	}
	
	// Calculates the amount of tax owed based on the given income, tax bracket maximums, and tax rates for those brackets. 
	private static double calculateTax(int income, int[] taxBrackets, int[] rates){
		double tax = 0;
		int index = taxBrackets.length-1; // By using the array length, this function will scale to any size tax brackets, not just the 6 brackets we currently use
				
		// Loop through the calculation, taxing each part of the income by its respective bracket and then setting income to the next lowest bracket
		while(index>=0){
			// This will move down a bracket each loop until the given income fits inside of a bracket.
			if(income>taxBrackets[index]){
				// Calculate income tax for this bracket only
				tax += (income-taxBrackets[index])*(rates[index+1]/100.00);
				// Set income to the next lowest bracket.
				income = taxBrackets[index];
			}
			index--;
		}
		
		// Include the final base tax bracket not calculated in the loop that also would handle an instance of having a flat tax.
		tax += income*(rates[0]/100.00);
		
		// Return the amount of tax owed
		return tax;
	}

}