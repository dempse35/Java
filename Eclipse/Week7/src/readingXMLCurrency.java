import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class readingXMLCurrency {

	public static void main(String[] args) {
		ArrayList<Currency> currencies = getCurrencyData();
		display(currencies);
		System.out.println("Program complete.");
	}
	public static void display(ArrayList<Currency> currencies){
		Scanner in = new Scanner(System.in);
		System.out.println("Example of input:    EUR  MXN GPB  (euro, mexican peso, british sterling)");
		System.out.print("Input type of currency: ");
		String resp = in.next().toUpperCase().trim();
		Double rate = 0d;
		
		
		//create var name and assign to inputed value
		//create a holder var for value = 0d
		//loop through currencies
		//   if the current currency name = inputed name
		//      assign current currency val to var value
		//if value ==0, print out that there is no currency = inputed name
		//else use System.out.printf("$1.00 in USD is worth %.2f %s/n" name, value) to print out something like   $1.00 in USD is worth 20.47 MXN
	}
	public static ArrayList<Currency> getCurrencyData(){
		ArrayList<Currency> currencies = new ArrayList<Currency>();
		//create a document
		Document doc;
		try{
			String url = "http://www.ecb.europa.eu/stats/eurofxref/eurofxref-daily.xml";
			doc = DocumentBuilderFactory.newInstance().
					newDocumentBuilder().parse(new URL(url).openStream());
			NodeList nodes = doc.getElementsByTagName("Cube");  //from document where elements tag name = "Cube" and getAttributes().getLength() == 2
			//                                                  which means that this is a node you want.
			for (int x = 0; x < nodes.getLength(); x++){
				if(nodes.item(x).getAttributes().getLength() == 2){
					String name = nodes.item(x).getAttributes().getNamedItem("currency").getTextContent();
					Double rate = Double.parseDouble(nodes.item(x).
							getAttributes().getNamedItem("rate").getTextContent());
					currencies.add(new Currency(name, rate));

				}
			}
			//loop through nodelist
			//create a String var to hold name  nodes.item(x).getAttributes().item(0).getTextContent();
			//create a Double var to hold rate  Double.parseDouble(nodes.item(x).getAttributes().item(1).getTextContent());
			//create a temp instance of Currency class
			//add temp to currencies array of Currency
		}catch(Exception e) {
			e.printStackTrace();
		}
		currencies.add(new Currency("EUR", 1d));
		
		return currencies;
	}

}
class Currency {
	//this class is intended to be used with url: http://www.ecb.europa.eu/stats/eurofxref/eurofxref-daily.xml
	//the url is based on EUR = 1 (note: EUR is not included in rates so you have to add 
	//constructor sets ratio var at USD so rate is based on rate/ratio which will make USD = 1 and all other rates based on USD=1
	private String name;
	private String fullName;
	private Double rate;
	private static Double ratio;
	//I added this array of string for your info
	String[] _allCurrencyNames = { "USD (United States Dollars)",
			"JPY (Japanese Yen)", "BGN (Bulgarian Lev)", "CZK (Czech Koruna)",
			"DKK (Danish Krone)", "GBP (Sterling)", "HUF (Hungarian Forint)",
			"PLN (Polish Zloty)", "RON (Romanian New Leu)",
			"SEK (Swedish Krona)", "CHF (Swiss Franc)",
			"NOK (Norwegian Krone)", "HRK (Croatian Kuna)",
			"RUB (Russian Rouble)", "TRY (Turkish Lira)",
			"AUD (Australian Dollars)", "BRL (Brazilian Real)",
			"CAD (Canadian Dollar)", "CNY (Chinese Yuan)",
			"HKD (Hong Kong Dollar)", "IDR (Indonesian Rupiah)",
			"ILS (Israeli New Sheqel)", "INR (Indian Rupee)",
			"KWD (Kuwaiti Dinar)", "MXN (Mexican Peso)",
			"MYR - Malaysian Ringgit", "NZD - New Zealand Dollar",
			"PHP (Philippine Peso)", "SGD (Singapore Dollar)",
			"THB (Thai Bhat)", "ZAR (South African Rand)", "EUR (Euro)" };
	//constructor
	public Currency(String name, Double rate){
		this.name = name;
		this.rate = rate;
		for (String fullName: _allCurrencyNames){
			if(name == fullName.substring(0,3)) this.fullName= fullName.substring(5,fullName.length()-1-5);
		}
		if(name.equals("USD")){
			this.ratio = rate;
		}
	}
	public String getName(){
		return this.name;
	}
	public Double getValue(){
		return rate / ratio;
	}
	@Override
	//in case you want to print out currency
	public String toString() {
		return name + " " + rate;
	}
}