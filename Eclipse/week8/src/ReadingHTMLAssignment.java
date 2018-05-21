import java.io.IOException;
import java.net.URI;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
//don't forget to download and build-path to jsoup!!!

public class ReadingHTMLAssignment {
	public static void main(String[] args) {
		getLkMIBuoyData();
		//getStockPrice();
	}
	public static void getLkMIBuoyData(){
		//"http://greatlakesbuoys.org/station_page.php?station=45168"
		//create a doc
		try {
			Document doc = Jsoup.connect("http://greatlakesbuoys.org/station_page.php?station=45168").get();
			Elements elements = doc.getElementsByClass("parameter");
			Elements elements1 = doc.getElementsByClass("value ");
			for(int x = 0; x < elements.size(); x++){
				String function = elements.get(x).getElementsByClass("parameter").text();
				String data = elements1.get(x).getElementsByClass("value ").text();
				System.out.printf("%-50s = %s\n", function, data);
				
			}
		//create 2 Element arrays
		//1st one will come from .getElementsByClass("parameter");
		//2nd one will come from .getElementsByClass("value ");
		//loop through them both together to get parameter and value on the same line
		
		//I would like to see this:
		//Weather data from South Haven buoy (station 45168)
		//-----------------------------------------------------
		//Wind Direction                           = SSE (157.4�)
		//Wind Speed                               = 12.5 knots
		//Wind Gust Speed                          = 16 knots
		//Air Temperature                          = 45.8 �F
		//Relative Humidity                        = 78.66 %
		//Dew Point                                = 39.5 �F
		//Significant Wave Height                  = 1.3 ft.
		//Dominant Period of Waves                 = 2.1 Sec.
		//Wave Direction                           = 118 �
		//Rainfall Rate                            = 0 inches/hr
		//Rain Accumulation                        = 0 inches
		//Station Air Pressure                     = 1005.27 mBar
		//Solar Radiation                          = 0 W m-2
		//Water Temperature at Surface             = 61.2 �F
		//Thermistor Temperature, depth: 3.3 ft.   = 61.0 �F
		//Thermistor Temperature, depth: 9.8 ft.   = 61.3 �F
		//Thermistor Temperature, depth: 16.4 ft.  = 61.0 �F
		//Thermistor Temperature, depth: 23.0 ft.  = 61.3 �F
		//Thermistor Temperature, depth: 29.5 ft.  = 55.1 �F
		//Thermistor Temperature, depth: 36.1 ft.  = 61.0 �F
		//Thermistor Temperature, depth: 42.7 ft.  = 61.0 �F
		//Thermistor Temperature, depth: 49.2 ft.  = 61.0 �F
		//Thermistor Temperature, depth: 55.8 ft.  = 61.0 �F
		//Battery Voltage                          = 12.64 V
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void getStockPrice(){
		//"http://www.barrons.com/search?keyword=gm"
		//note: you can use input to replace gm with another company
		//create a doc
		try {
			Scanner in = new Scanner(System.in);
			String company = "gm";
			System.out.print("Would youu like to see the stock of a certain company? (y/n)");
			if(in.next().toLowerCase().trim().equals("y")){
				System.out.println("Examples: GM    Ford  (note: no spaces)");
				System.out.print("Type company: ");
				company = in.next().trim();
			}
			
			Document doc = Jsoup.connect("http://www.barrons.com/search?keyword=" + "company").get();
			Elements elements = doc.getElementsByTag("div");
			for(int x = 0; x<elements.size(); x++){
			String comp = doc.getElementsByClass("quoteHeader__commonName").text();
			String stock = doc.getElementsByClass("market__price bgLast").text();
			
			System.out.printf("Company: %s     Current Stock Value: %s", comp, stock);
			}
			//Elements elements = doc.getElementsByClass("market__info");
			//for(int x=0; x< elements.size(); x++){
				//String data = doc.getElementsByClass("market__info").text();
				//String stuff = elements.get(x).getElementsByClass("market__info").text();
				//System.out.printf("should be stuff here: %s /n and %s", data, stuff);
			//}
			System.out.println("example");
			
			
		//you can get a list of element is you get elements by class = "market__info"
		//hint        list = doc.getElementsByClass("market__info");
		//the first element will have price and a bunch of other stuff so you will have to .substring
		
		//I would like to see results like this:
		//Type name of company :gm
		//General Motors Co.  $37.22
		} catch (IOException e) {
		e.printStackTrace();
	}
}
}