import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class myTimerProject {

	public static void main(String[] args) {
		int seconds = 10; // how often do i want to run this?
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new myTimerTask(), 0, seconds * 1000);
		Scanner in = new Scanner(System.in);
		while(!in.hasNextLine()){
			
		}
		timer.cancel();
		System.out.println("Done.");
	}
	public static void getMyData(){
		String URL = "https://www.youtube.com/watch?v=tvTRZJ-4EyI";
		String dbName = "VideoViews.db";
		try {
			Document doc = Jsoup.connect(URL).get();
			Class.forName("org.sqlite.JDBC");
			Connection con = DriverManager.getConnection("jdbc:sqlite:" + dbName);
			Elements elements = doc.getElementsByClass("watch-view-count");
			for (int x = 0; x < elements.size(); x++){
				String views = elements.get(x).getElementsByClass("watch-view-count").text();
				System.out.printf("there are %s views", views);
				
			}
			
			
			
			
		} catch (IOException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		System.out.println("Second");
		
		
	}
	private static void saveToDb(Connection con, String views){
		try {
			checkDB(con);
			Statement state = con.createStatement();
			String sql = "INSERT INTO view_data(views)" + "VALUES('" + views + "');";
			state.executeUpdate(sql);
			
			
		} catch (Exception e) {
			System.out.println("Couldnt add to DB!");
			e.printStackTrace();
		}
		System.out.println(views);
	}
	private static void checkDB(Connection con){
		Statement state;
		try {
			state = con.createStatement();
			String sql = "CREATE TABLE IF NOT EXISTS view_data(id INTEGER, views TEXT, date_time DATETIME DEFAULT CURRENT_TIMESTAMP, PRIMARY KEY(id));";
			state.execute(sql);
		} catch (Exception e) {
			System.out.println("Couldnt create DB!");
			e.printStackTrace();
		}
	}

}
class myTimerTask extends TimerTask {
	public void run(){
		
		myTimerProject.getMyData();
		System.out.println("Press <enter> to stop");
	}
}