package softwarepulse.app;

import java.sql.ResultSet;
import java.sql.SQLException;

public class App {

	public static void main(String[] args) {
		SQLiteTest test = new SQLiteTest();
		ResultSet rs;

		try {
			rs = test.displayUsers();
			while(rs.next()) {
				System.out.println(rs.getString("fname") + " " + rs.getString("lname"));
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
