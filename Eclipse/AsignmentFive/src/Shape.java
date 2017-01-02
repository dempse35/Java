import javax.swing.JOptionPane;
// @author james dempsey
public abstract class Shape {
	String type;

	public String getType() {
		return type;
	}
	
	public abstract double getArea();
	
	public abstract double getPerimeter();
	
	public abstract String getParameterString();
	
	public Shape() {
	}
	
	public Shape(String type) {
		this.type = type;
	}
	
	public abstract void requestInput(); 
	
	public String toString() {
		return "Details for " + getType() + "\n"
				+ "with " + getParameterString() + ":\n"
				+ "The area is " + getArea() + " meters. \n"
				+ "The perimeter is " + getPerimeter() + " meters.";
	}
	
	public void output() {
		JOptionPane.showMessageDialog(null, this.toString());
	}
}
