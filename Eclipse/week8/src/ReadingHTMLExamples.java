import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
//don't forget to download and build-path to jsoup!!!

public class ReadingHTMLExamples {
	public static void main(String[] args) {
		//getGrandHavenWeather();
		//getGrandRapidsWeather();
		//getWorldTimes();
		getCityInfo();
	}
	public static void getGrandHavenWeather(){
		try {
			//create a Document and connect to:  "http://www.grandhavenbeach.org/"
			Document doc = Jsoup.connect("http://www.grandhavenbeach.org/").get();
			Elements elements = doc.select("p");
			
			//get array of Elements  where   doc.select("p")
			System.out.println("Weather forcast for Grand Haven State Park:");
			System.out.println(elements.get(3).text());
			System.out.println("Forecast " + elements.get(13).text());
			System.out.println(elements.get(7).text());
			System.out.println(elements.get(8).text());
			System.out.println(elements.get(9).text());

			//println  the text of paragraph 3 .text
			//println "Forcast " + paragraph 13
			//println paragraph 7
			//println paragraph 8
			//println paragraph 9

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void getGrandRapidsWeather(){
		try {
			//create a Document and connect to:  "https://www.wunderground.com/us/mi/grand-rapids"
			Document doc = Jsoup.connect("https://www.wunderground.com/us/mi/grand-rapids").get();
			//Document document = Jsoup.connect("https://www.wunderground.com/us/mi/grand-rapids").get();
			//note: you can change grand-rapids to most larger cities in Michigan e.g. coopersville, lansing, etc.
			Elements elements = doc.getElementsByAttribute("property");
			//create an array of Elements   document.getElementsByAttribute("property");
			//loop through elements
			for(Element element:elements){
				if (element.attr("property").equals("og:title")){
					String stuff = element.attr("content");
					String[] items = stuff.split("\\|");
					String city = items[0].trim();
					String temp = items[1].trim();
					String weather = items[2].trim();
					System.out.printf("The temperature in %s is %s and it is %s.", city, temp, weather);
				
				}
			}
			//    if you don't filter elements, you will get lines of extra stuff
			// if currentElement.attr("property").equals("og:title")
			//       String = currentElement.attr("content"
			//       String[] = split string on "\\|"
			//        city = first index
			//        temp = secont index
			//        currWeather = third index
			//         use printf to print something like The temperature in GR is 10degrees and it is sunny.

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void getWorldTimes(){
		try {

			//create a Document and connect to: "https://www.timeanddate.com/worldclock/"
			Document doc = Jsoup.connect("https://www.timeanddate.com/worldclock/").get();
			//get Elements array and fill with elements that are wrapped with <td>    doc.select("td") 
			Elements elements = doc.select("td");
			//loop through elements using for x loop with step +2 because you need 2 elements to get city and time
			for(int x = 0; x < elements.size(); x+=2){
				if(elements.get(x).getElementsByTag("a").size() > 0);
				String country = elements.get(x).childNode(0).attributes().get("href");
				country = country.split("/")[2];
				country = Character.toUpperCase(country.charAt(0)) + country.substring(1);
				String city = elements.get(x).getElementsByTag("a").text();
				String time = elements.get(x+1).text();
				String day = time.substring(0,3).trim();
				time = time.substring(3); // tue 12:`12pm
				System.out.printf("In %s %s it is %s and the time is %s\n", city, country, day, time);
				
				
			}
			//    if single element .getElementsByTag("a").size > 0    because there are some junk ones
			//		  country = paragraphs.get(x).childNode(0).attributes().get("href");
			//  	  country = country.split("/")[2];
			//		  country = Character.toUpperCase(country.charAt(0)) + country.substring(1);
			//		  city = paragraphs.get(x).getElementsByTag("a").text();
			//		String time = paragraphs.get(x+1).text();
			//		String day = time.substring(0,3).trim();
			//		time = time.substring(3);
			//		use printf to print out something like: In Accra Ghana it is Mon and time is 10:30 pm


		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void getCityInfo(){
		try {
			Scanner in = new Scanner(System.in);
			String country = "usa";
			String city = "traversecity";
			System.out.print("Would you like to see a city's current time and info? (y/n)");
			if(in.next().toLowerCase().trim().equals("y")){
				System.out.println("Examples: USA    GrandRapids  (note: no spaces)");
				System.out.print("Type country: ");
				country = in.next().trim();
				System.out.print("Type city: ");
				city = in.next().trim();
				//next 3 lines required to open a webbrowser with a url
				Desktop desktop = java.awt.Desktop.getDesktop();
				URI uri = new URI("https://www.timeanddate.com/worldclock/" + country + "/" + city);
				desktop.browse(uri);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}