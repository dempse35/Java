import java.util.Scanner;

import javax.swing.JOptionPane;

/**
 *  Copyright 2010 by Daniel Mish and the Grand Rapids Community College.
 *
 *  Creative Commons Attribution-ShareAlike 2.5
 *  You are free:
 *  * to Share -- to copy, distribute, display, and perform the work
 *  * to Remix -- to make derivative works
 *  
 *  Under the following conditions:
 *  * Attribution. You must attribute the work in the manner specified by the 
 *    author or licensor.
 *  * Share Alike. If you alter, transform, or build upon this work, you may 
 *    distribute the resulting work only under a license identical to this one.
 *  * For any reuse or distribution, you must make clear to others the license 
 *    terms of this work.
 *  * Any of these conditions can be waived if you get permission from the 
 *    copyright holder.
 *
 *  Disclaimer disclaimer
 *  Your fair use and other rights are in no way affected by the above.
 * 
 *  This is a human-readable summary of the Legal Code (the full license).
 *  <!--Creative Commons License--><a rel="license" href="http://creativecommons.org/licenses/by-sa/2.5/"><img alt="Creative Commons License" style="border-width: 0" src="http://i.creativecommons.org/l/by-sa/2.5/88x31.png"/></a><br/>This work is licensed under a <a rel="license" href="http://creativecommons.org/licenses/by-sa/2.5/">Creative Commons Attribution-Share Alike 2.5  License</a>.<!--/Creative Commons License--><!-- <rdf:RDF xmlns="http://web.resource.org/cc/" xmlns:dc="http://purl.org/dc/elements/1.1/" xmlns:rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns#" xmlns:rdfs="http://www.w3.org/2000/01/rdf-schema#">
 *   <Work rdf:about="">
 *       <license rdf:resource="http://creativecommons.org/licenses/by-sa/2.5/" />
 *   </Work>
 *  <License rdf:about="http://creativecommons.org/licenses/by-sa/2.5/"><permits rdf:resource="http://web.resource.org/cc/Reproduction"/><permits rdf:resource="http://web.resource.org/cc/Distribution"/><requires rdf:resource="http://web.resource.org/cc/Notice"/><requires rdf:resource="http://web.resource.org/cc/Attribution"/><permits rdf:resource="http://web.resource.org/cc/DerivativeWorks"/><requires rdf:resource="http://web.resource.org/cc/ShareAlike"/></License></rdf:RDF> -->
 *
 *  Author: Daniel Mish
 *  Assignment One
 *
 * <p>$Source$</p>
 **/

public class LengthConverter {

	/**
	 * @param args not used
	 */
	public static void main(String[] args) {
		
		// convert from meters to feet
				
		// declare feet variable, we will calculate it later
		double feet;
		// declare meters and initialize with a value
		double meters;
		
		// print the results
//		System.out.println("The scale is Meters");
//		Scanner input = new Scanner(System.in);
		String answer = JOptionPane.showInputDialog("Please Enter a Value for METERS");
//		meters = input.nextDouble();
		
		meters = Double.parseDouble(answer);
		System.out.println("The measurement in Meters is " + meters);
		
		// calculate the feet value
		feet = meters * 3.2808;
		
		// print the calculated feet value
		System.out.println("The measurement in Feet is "+feet);
		System.out.println();

		// The next section is similar to the first, we are just using new 
		// values to convert from feet to meters
		
		System.out.println("The scale is Feet");
		System.out.println("Please enter a Value for Feet:");
		
		// initialize feet
		feet = input.nextDouble();
		// calculate meters
		meters = feet / 3.2808;
		
		//print the results
		System.out.println("The measurement in Meters is " + meters);
		System.out.println("The measurement in Feet is "+feet);
		System.out.println("Thank you for using the Length Converter.");
	}
}