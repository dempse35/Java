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
import org.xml.sax.SAXException;

public class GetWeather {
	
	public static void main(String[] args) {
		String dbName = "Weather.db";
		String url = "http://w1.weather.gov/xml/current_obs/KGRR.xml";
		getData(dbName,url);


	}
	public static void getData(String dbName, String url){
		try {
			// create document and connect to url
			Document doc = DocumentBuilderFactory.newInstance().
					newDocumentBuilder().parse(new URL(url).openStream());
			// vars to hold data 
			String location = doc.getElementsByTagName("location").item(0).getTextContent();
			String time = doc.getElementsByTagName("observation_time_rfc822").item(0).getTextContent();
			String weather = doc.getElementsByTagName("weather").item(0).getTextContent();
			String temperature = doc.getElementsByTagName("temp_f").item(0).getTextContent();
			String humidity = doc.getElementsByTagName("relative_humidity").item(0).getTextContent();
			String wind = doc.getElementsByTagName("wind_string").item(0).getTextContent();
			//create connection to database
			Class.forName("org.sqlite.JDBC");
            Connection connection = DriverManager.getConnection("jdbc:sqlite:" + dbName);
            //create table in necessary
            Statement statement = connection.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS weather(id INTEGER PRIMARY KEY, location TEXT, time TEXT, weather TEXT, "
            		+ "temperature TEXT, humidity TEXT, wind TEXT);";
            statement.execute(sql);
            
            sql = "INSERT INTO weather(location, time, weather, temperature, humidity, wind)" +	
            		" VALUES (?,?,?,?,?,?);";
            PreparedStatement preparedStatement;
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "location");
            preparedStatement.setString(1, "time");
            preparedStatement.setString(1, "weather");
            preparedStatement.setString(1, "temperature");
            preparedStatement.setString(1, "humidity");
            preparedStatement.setString(1, "wind");
            preparedStatement.executeUpdate();




            
            

		} catch (SAXException | IOException | ParserConfigurationException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

}
