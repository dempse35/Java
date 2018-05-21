package week10;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class GetCurrency {
	public static void main(String[] args) {
		String dbName = "Currency.db";
		String url = "http://www.ecb.europa.eu/stats/eurofxref/eurofxref-daily.xml";
		getData(dbName, url);


	}
	public static void getData(String dbName, String url){
		try {
			Document doc = DocumentBuilderFactory.newInstance().
					newDocumentBuilder().parse(new URL(url).openStream());

			Double usd = 0.d;
			Double jpy = 0.d;
			Double bgn = 0.d;
			Double czk = 0.d;
			Double dkk = 0.d;
			Double gbp = 0.d;
			
			NodeList nodes = doc.getElementsByTagName("Cube");
			for (int x = 0; x < nodes.getLength(); x ++){
				Element element = (Element) nodes.item(x);
				if(element.hasAttribute("currency") && element.getAttribute("currency").equals("USD"))  
					usd = Double.parseDouble(element.getAttribute("rate"));
				if(element.hasAttribute("currency") && element.getAttribute("currency").equals("JPY"))  
					jpy = Double.parseDouble(element.getAttribute("rate"));
				if(element.hasAttribute("currency") && element.getAttribute("currency").equals("BGN"))  
					bgn = Double.parseDouble(element.getAttribute("rate"));
				if(element.hasAttribute("currency") && element.getAttribute("currency").equals("CZK"))  
					czk = Double.parseDouble(element.getAttribute("rate"));
				if(element.hasAttribute("currency") && element.getAttribute("currency").equals("DKK"))  
					dkk = Double.parseDouble(element.getAttribute("rate"));
				if(element.hasAttribute("currency") && element.getAttribute("currency").equals("GBP"))  
					gbp = Double.parseDouble(element.getAttribute("rate"));
				
			}
			Class.forName("org.sqlite.JDBC");
            Connection connection = DriverManager.getConnection("jdbc:sqlite:" + dbName);
            Statement statement = connection.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS currency(id INTEGER PRIMARY KEY, usd TEXT, jpy TEXT, bgn TEXT, "
            		+ "czk TEXT, dkk TEXT, gbp TEXT);";
            statement.execute(sql);
            sql = "INSERT INTO currency(usd, jpy, bgn, czk, dkk, gbp)" +	
            		" VALUES (?,?,?,?,?,?);";
            PreparedStatement preparedStatement;
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "usd");
            preparedStatement.setString(1, "jpy");
            preparedStatement.setString(1, "bgn");
            preparedStatement.setString(1, "czk");
            preparedStatement.setString(1, "dkk");
            preparedStatement.setString(1, "gbp");
            preparedStatement.executeUpdate();




		} catch (SAXException | IOException | ParserConfigurationException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		
		}

	}

}
