// @author james dempsey
import javax.swing.JOptionPane;

public class ConvertDogYears {
	double humanYears;
	private static final double DOG_AGE_FIRST_TWO_YEARS = 10.5;
	private static final double DOG_AGE_REMAINING_YEARS = 4;
	
	public double getHumanYears() {
		return humanYears;
	}
	public double getDogYears() {
		double dogYears;
		if (humanYears > (2 * DOG_AGE_FIRST_TWO_YEARS)) {
			dogYears = ((humanYears - (DOG_AGE_FIRST_TWO_YEARS * 2)) / DOG_AGE_REMAINING_YEARS) + 2;
		} else {
			dogYears = humanYears / DOG_AGE_FIRST_TWO_YEARS;
		}
		return dogYears;
	}

	public void setHumanYears(double years) {
		if (years < 0) {
			years = 0;
		}
		this.humanYears = years;
	}
	
	public void setDogYears(double years) {
		double temp;
		if (years <= 2) {
			 temp = years * DOG_AGE_FIRST_TWO_YEARS;
		} else {
		     temp = (2 * DOG_AGE_FIRST_TWO_YEARS) + (years - 2) * DOG_AGE_REMAINING_YEARS;
		}
		setHumanYears(temp);
	}
	
	public String toString() {
		return "the value in human years is " + getHumanYears() + "\n" +
				"the value in dog years is " + getDogYears();
	}
	
	public static void main(String[] args) {
		boolean repeat = true;
		
		while (repeat) {
			String answer = JOptionPane.showInputDialog("Would you like to convert Human Years or Dog Years? (H/D)");
			ConvertDogYears converter = new ConvertDogYears();
			if (answer.equalsIgnoreCase("h")) {
				String humanYears = JOptionPane.showInputDialog("Enter a value for Human Years");
				converter.setHumanYears(Double.parseDouble(humanYears));
			}
			if (answer.equalsIgnoreCase("d")) {
				String dogYears = JOptionPane.showInputDialog("Enter a value for Dog Years");
				converter.setDogYears(Double.parseDouble(dogYears));
			} 
			JOptionPane.showMessageDialog(null, converter.toString());

			String finish = JOptionPane.showInputDialog("Would you like to go again? (Y/N)");
			if (!finish.equalsIgnoreCase("y")) {
				 repeat = false;
			}
		}
	}
}

