import javax.swing.JOptionPane;

public class Circle extends Shape {
	double radius;

	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}
	
	public Circle() {
		super("circle");
	}
	
	@Override
	public void requestInput() {
		String answer = JOptionPane.showInputDialog("Please enter a radius in meters: ");
		setRadius(Double.parseDouble(answer));
	}

	@Override
	public double getArea() {
		return (radius * radius * Math.PI);
	}

	@Override
	public double getPerimeter() {
		return (2 * radius * Math.PI);
	}

	@Override
	public String getParameterString() {
		return "radius " + radius;
	}
	
	public static void main(String[] args) {
		boolean repeat = true;
		while (repeat) {
			Circle example = new Circle();
			example.requestInput();
			example.output();
			String finish = JOptionPane.showInputDialog("Would you like to go again? (Y/N)");
			if (!finish.equalsIgnoreCase("y")) {
				repeat = false;
			}
		}
	}
}
