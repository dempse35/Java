import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;


public class readPowerBall {

	public static void main(String[] args) {
		ArrayList<powerBall> numbers = getPowerBallData();
		for(powerBall number: numbers){
			number.printNumbers();
		}
	}
	public static ArrayList<powerBall> getPowerBallData(){
		ArrayList<powerBall> numbers = new ArrayList<powerBall>();
		//create a new document
		try{
			String url = "https://data.ny.gov/api/views/d6yy-54nr/rows.xml?accessType=DOWNLOAD";
			//fill document using = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new URL(url).openStream());
			//create a nodelist by using doc.getElementsByTagName("row");
			//loop through nodelist
			//create string var and get date string = ((Element) nodes.item(x)).getElementsByTagName("draw_date").item(0).getTextContent();
			//convert stringdate to new Date var = (Date)new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss").parse(strdate);
			//create string winningNumber = ((Element) nodes.item(x)).getElementsByTagName("winning_numbers").item(0).getTextContent();
			//fortunately, I have a constructor that receives this string of numbers and breaks it down!!
			//add a new instance of powerBall to numbers
		}catch (Exception e) {
			e.printStackTrace();
		}
		return numbers;
	}

}
class powerBall {
	//private vars
	private int[] numbers = new int[6];
	private Date date;
	//constructors
	public powerBall(){}
	public powerBall(String wholeline, Date date){   //this constructor will read a line with both english and spanish words
		String[] tokens = wholeline.split(" ");
		for (int x = 0; x < 6; x++) this.numbers[x] = Integer.parseInt(tokens[x]);
		this.date=date;
	}
	//gets & sets
	public int[] getNumbers(){
		int[] temp = {numbers[0], numbers[1], numbers[2], numbers [3], numbers[4]};
		return temp;
	}
	public int getPowerball(){
		return this.numbers[5];
	}
	public Date getDate(){
		return this.date;
	}
	//public methods
	public void printNumbers(){		//mmm dd, yyyy   num1 num2 num3 num4 num5 *pb
		System.out.printf(" %tb %<td, %<tY    %2d  %2d  %2d  %2d  %2d  *%2d\n",
				date, this.numbers[0], this.numbers[1], this.numbers[2], 
				this.numbers[3], this.numbers[4], this.numbers[5]);
	}

}
