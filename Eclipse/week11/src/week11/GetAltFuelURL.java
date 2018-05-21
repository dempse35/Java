package week11;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class GetAltFuelURL {
	static ArrayList<Station> stations = new ArrayList(); 
	public static void main(String[] args) {
		String urlName = "http://en.openei.org/doe-opendata/dataset/cbb80cd9-1dcc-48a1-ad88-f653a621a1e6/resource/44bdb515-eb8e-43d4-8994-a92f584ae5ac/download/04232015altfuelstations.csv"; 
		String dbName = "altfuel.db";
		//getURLStuff(urlName);
		//System.out.println(stations.size() + " records added");
		//putStuffInDataBase(dbName);
		getStations(dbName);
	}
	public static void getStations(String dbName) {
		try {
			Class.forName("org.sqlite.JDBC");
			Connection connection = DriverManager.getConnection("jdbc:sqlite:" + dbName);
			// this will create a table and delete data so we don't get duplicates each time we run the program
			Scanner in = new Scanner(System.in);
			System.out.println("type name of city");
			String city = in.nextLine();
			Statement statement = connection.createStatement();
			String sql = "SELECT * FROM stations WHJERE city =" + city;
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()){
				String name = rs.getString(3);
				String address = rs.getString(4);
				System.out.println(name + " located at " + address);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void putStuffInDataBase(String dbName) {
		try {
			Class.forName("org.sqlite.JDBC");
			Connection connection = DriverManager.getConnection("jdbc:sqlite:" + dbName);
			// this will create a table and delete data so we don't get duplicates each time we run the program
			Statement statement = connection.createStatement();
			String sql = "CREATE TABLE IF NOT EXISTS stations(id  INTEGER PRIMARY KEY, type TEXT, "
					+ "name TEXT, address TEXT, city TEXT, state TEXT, zip TEXT);";
			statement.execute(sql);
			sql = "DELETE FROM stations;";
			statement.execute(sql);
			// this will loop through ArrayList and add indexes to database
			for(Station station: stations){
				if(station.type.equals("ELEC")){
					sql = "INSERT INTO stations(type, name, address, city, state, zip) VALUES (?,?,?,?,?,?);";
					PreparedStatement preparedStatement = connection.prepareStatement(sql);
					preparedStatement.setString(1, station.type);
					preparedStatement.setString(2, station.name);
					preparedStatement.setString(3, station.address);
					preparedStatement.setString(4, station.city);
					preparedStatement.setString(5, station.state);
					preparedStatement.setString(6, station.zip);
					//			      (additional setStrings for 5 more fields) (2, name, 3, address, 4, city, 5, state, 6, zip)
					preparedStatement.executeUpdate();
				}
			}
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void getURLStuff(String urlName) {
		URL url;
		try {
			url = new URL(urlName);
			URLConnection URLconnection = url.openConnection();
			BufferedReader bufferedReader=
					new BufferedReader(new InputStreamReader(URLconnection.getInputStream()));
			// this will loop through the InputStream line after line
			bufferedReader.readLine();
			String line;
			while ((line = bufferedReader.readLine()) != null){
				// this will break apart a line into elements that are seperated by commas
				String[] elements = line.split(",");
				//we have to add a catcher to make sure that the line has > 7 elements
				if(elements.length>7){
					//Get strings from  elements
					String type = elements[0];
					//(name = 1, address = 2, city = 4, state = 5, zip = 6)
					String name = elements[1];
					String address = elements[2];
					String city = elements[4];
					String state = elements[5];
					String zip = elements[6];
					//create a new Station and add to stations ArrayList
					stations.add(new Station(type, name, address, city, state, zip));
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		} 



	}
}
//create this class to contain fields that I want to collect from online .csv file
class Station{
	public String type, name, address, city, state, zip;
	Station(String type, String name, String address, String city, String state, String zip){
		this.type = type;
		this.name = name;
		this.address = address;
		this.city = city;
		this.state = state;
		this.zip = zip;
	}
}