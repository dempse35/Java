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

public class ElecComp {
	static ArrayList<Company> companies = new ArrayList(); 

	public static void main(String[] args) {
		String urlName = "http://en.openei.org/doe-opendata/dataset/b09df2ba-b818-48fe-83c1-46bb2f2c20dc/resource/9ffdea94-fe03-4e51-bd09-4d8c12dd92ed/download/iouzipcodes2013.csv";  
		String dbName = "elecCompanies.db";
				getURLStuff(urlName);
				System.out.println(companies.size() + " records added");
				putStuffInDataBase(dbName);
				getStations(dbName);

	}
	public static void getStations(String dbName) {
		try {
			Class.forName("org.sqlite.JDBC");
			Connection connection = DriverManager.getConnection("jdbc:sqlite:" + dbName);
			// this will create a table and delete data so we don't get duplicates each time we run the program
			Scanner in = new Scanner(System.in);
			System.out.println("type zipcode:");
			String zipcode = in.nextLine();
			Statement statement = connection.createStatement();
			String sql = "SELECT * FROM companies WHERE zipcode =" + zipcode;
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()){
				String name = rs.getString("name");
				String state = rs.getString("state");
				Double rate = rs.getDouble("rate");
				System.out.println(name + " in " + state + " with a rate of " + rate);
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
			String sql = "CREATE TABLE IF NOT EXISTS companies(id  INTEGER PRIMARY KEY, zip TEXT, name TEXT, state TEXT, rate REAL);";
			statement.execute(sql);
			sql = "DELETE FROM companies;";
			statement.execute(sql);
			// this will loop through ArrayList and add indexes to database
			for(Company company: companies){
				if(company.zip.equals("")){
					sql =  "INSERT INTO companies(zip, name, state, rate) VALUES (?,?,?,?);";
					PreparedStatement preparedStatement = connection.prepareStatement(sql);
					preparedStatement.setString(1, company.zip);
					preparedStatement.setString(2, company.name);
					preparedStatement.setString(3, company.state);
					preparedStatement.setDouble(4, company.rate);
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
				if(elements.length>9){
					//Get strings from  elements
					String zip = elements[0];
					//(name = 1, address = 2, city = 4, state = 5, zip = 6)
					String name = elements[2];
					String state = elements[3];
					double rate = Double.parseDouble(elements[8]);
					//create a new Station and add to stations ArrayList
					companies.add(new Company(zip, name, state, rate));
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		} 



	}
}
//create this class to contain fields that I want to collect from online .csv file
class Company{
	public String zip, name, state;
	double rate;
	Company(String zip, String name, String state, double rate){
		this.zip = zip;
		this.name = name;
		this.state = state;
		this.rate = rate;
		
		
	}

}
