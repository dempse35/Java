import javax.swing.JOptionPane;

/**
 * 
 */

/**
 * @author james dempsey
 *
 */
public class Temperature {

	public static void main(String[] args) {
		float Celcius;
		float Fahrenheit;

		String answer = JOptionPane.showInputDialog("Enter degrees in Celcius:");

		Celcius = (float) Double.parseDouble(answer);

		Fahrenheit = (float) ((Celcius * (1.8)) + 32);
		JOptionPane.showMessageDialog(null,
				Celcius + " degrees celcius is equal to " + Fahrenheit + " degrees fahrenheit");

		JOptionPane.showConfirmDialog(null, "Would you like to convert from fahrenheit to celcius?");

		String answer1 = JOptionPane.showInputDialog("Enter degrees in Fahrenheit:");

		Fahrenheit = (float) Double.parseDouble(answer1);

		Celcius = (float) ((Fahrenheit - 32) / (1.8));

		JOptionPane.showMessageDialog(null,
				Fahrenheit + " degrees fahrenheit is equal to " + Celcius + " degrees celcius");

	}

}
