import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;


public class ReadCSVPopulation {
	public static ArrayList<state> states = new ArrayList();
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		//URL Base = http://www.census.gov/popclock/
		//URL Parent = http://www.census.gov/popest/data/national/totals/2015/index.html
		//URL actual table = http://factfinder.census.gov/faces/tableservices/jsf/pages/productview.xhtml?src=bkmk
		// Excel File is available on this page 
		String url = "http://www.census.gov/popest/data/state/totals/2015/tables/NST-EST2015-01.csv";
		String databaseName = "StatePopulations.db";
		Class.forName("org.sqlite.JDBC");
		Connection connection = DriverManager.getConnection("jdbc:sqlite:" + databaseName);
		readURL(url);
		storeData(connection);
		printSomeStats(connection); 
	}
	public static void readURL(String urlString){
				//CSV (comma separated values) State/Area, 2010 census, 2010 base census, (population estimates)2010, 2011, 2012, 2013, 2014, 2015 
				try{
					URL url  = new URL(urlString);
		            URLConnection URLconnection = url.openConnection();
		            //InputStreamReader inStream = new InputStreamReader(URLconnection.getInputStream());
		            BufferedReader bufferedReader= new BufferedReader(new InputStreamReader(URLconnection.getInputStream()));
		            for(int x = 0; x < 9; x++){
		            	// read these lines but they are junk
		            	
		            }
		            for(int x = 0; x < 51; x++){	// these lines have data, read them, parse them, make new class objects, and add them to a list
		            	String content = bufferedReader.readLine();
		            	System.out.println(content);
		            	//you can delete above println line
		            	//parse out content see my example.
		            	//create a new object of the class that you created below and add to the list added above
		            	// columns (0)State  (1)disregard  (2)disregard   (3)2010     (4)2011     (5)2012      (6)2013     (7)2014   (8)2015
		            	// note:   .Alabama,  "4,779,736",  "4,780,127","4,785,161","4,801,108","4,816,089","4,830,533","4,846,411","4,858,979"
		            }
		            bufferedReader.close();
		            
		            System.out.println("read data into list");
				}
				 catch (Exception e){
			            System.out.print("Exception");
			        }
	}
	public static void storeData(Connection connnection){}
	//create a method that will store the 
	
	public static void printSomeStats(Connection connection){}
	//create a method that will print out some data  (see assignment)
}
//add a class below to contain
class state{
	//add some public vars
	//create a constructor to receive all data and set vars
}