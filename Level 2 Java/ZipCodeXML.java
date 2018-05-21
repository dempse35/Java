import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class ZipCodeXML {
	public static void main(String[] args) {
		String textFileName = "zipcodesSmall.txt";
		String xmlFileName = "zipcodes.xml";
		//    fill ArrayList
		ArrayList<ZipCode> zips = readTextFile();
		//    put ArrayList into xml document
		//Document document = createXMLdocument(zips);
		//    write xml document to file
		//writeXMLdocument(xmlFileName, document);
		//    re-fill ArrayList from xml file
		zips = null;
		//zips = readXMLfile(xmlFileName);
		System.out.println("Finished");
	}
	//public static Document createXMLdocument(ArrayList<ZipCode> zips){ 
	
	//public static void writeXMLdocument(String fileName, Document document){
	
	//public static ArrayList<ZipCode> readXMLfile(String fileName){
	
	public static ArrayList<ZipCode> readTextFile(){
		ArrayList<ZipCode> zips = new ArrayList<ZipCode>();
		try {
			File file = new File("zipcodesSmall.txt");    //attach to file
			Scanner scanner = new Scanner(file);                //scanners has nextLine method
			for(int x = 0; x < 0; x++){ scanner.nextLine();}   //reading junk
			while(scanner.hasNextLine()){                       //read until eof
				String oneLine = scanner.nextLine();            //read a line
				ZipCode temp = new ZipCode(oneLine);    //create a new spanishWord obj
				if(!temp.getState().equals(""))	zips.add(temp);                                //add to my arraylist
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return zips;
	}
}

class ZipCode {
	private String Zipcode;
	private String City;   
	private String State;  


	public ZipCode(){}
	public ZipCode(String wholeline){
		String[] tokens = wholeline.split("\t");
			this.Zipcode = tokens[0];
			this.City=tokens[1];
			this.State=tokens[2];
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
