import java.io.*;
import java.util.ArrayList;
public class ReadZipCodes {

	public static void main(String[] args) {
		ArrayList<ZipCode> zips = new ArrayList<ZipCode>();
		
	}
	public static ArrayList<ZipCode> readTextFile(){
		ArrayList<ZipCode> zips = new ArrayList<ZipCode>();
		
		
		//after creating required objects to read the text file, 
		//    don't forget to loop through 5 lines of junk before reading data!!!
		
		//note, as you read each line send the line as a constructor to a new zipcode.
		//     there is a constructor that will take that line and parse out the zipcode, city, and state
		//     and create a new zipcode with those fields filled.
		
		return zips;
	}

}

class ZipCode implements Serializable{
	private String Zipcode;    //[0]
	private String City;       //[2]
	private String State;      //[3]
	
	
	public ZipCode(){}
	public ZipCode(String wholeline){
		String[] tokens = wholeline.split("\t");
		if (tokens.length==3){
			this.Zipcode = tokens[0];
			this.City=tokens[1];
			this.State=tokens[2];
		}
		else{
			this.Zipcode = tokens[0];
			this.City=tokens[2];
			this.State=tokens[3];
		}
	}
	public ZipCode(String zipcode, String city, String state) {
		Zipcode = zipcode;
		City = city;
		State = state;
	}
	
	public String getZipcode() {
		return Zipcode;
	}
	public void setZipcode(String zipcode) {
		Zipcode = zipcode;
	}
	public String getCity() {
		return City;
	}
	public void setCity(String city) {
		City = city;
	}
	public String getState() {
		return State;
	}
	public void setState(String state) {
		State = state;
	}
	
	@Override
	public String toString() {
		return  Zipcode + "\t" + City + "\t" + State;
	}	
}
