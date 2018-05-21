import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class SQLitePassword {
	private String dbName = "passwords.db";
	public boolean isLoggedIn = false;
	public String user;
	public String password;
	Statement statement;

	SQLitePassword(){
		try {
			Connection con = this.connect(); 
			statement = con.createStatement();
			String sql = "CREATE TABLE IF NOT EXISTS passwords " +
					"('id' integer PRIMARY KEY, 'user' text, 'password' text);";
			System.out.println("Created new database.");
			statement.executeUpdate(sql);
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	}}
	
	public void createNewLogin(String user, String password){
		try {
			Connection con = this.connect();
			statement = con.createStatement();
			String sqlCheck = "SELECT COUNT(*) as count FROM passwords " +
					"WHERE user = '" + user + "';";	
			ResultSet rs = statement.executeQuery(sqlCheck);
			if(rs.next() && rs.getInt(1)==0){
				String insertSQL = "INSERT INTO passwords " +
						"(user, password) VALUES(?,?)";

				PreparedStatement preparedStatement = con.prepareStatement(insertSQL);
				preparedStatement.setString(1, user);
				preparedStatement.setString(2, password);
				preparedStatement .executeUpdate();
				System.out.println("Added new user.");
				statement.close();
			}
			else{
				System.out.println(user + " already exists!");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	}}
	
	public void login(String user, String password){
		String sqlCheck = "SELECT COUNT(*) as count FROM passwords WHERE user = '" + user + "' AND password = '" + password + "';";
		try {
			Connection con = this.connect();
			statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sqlCheck);
			if(rs.next() && rs.getInt(1)==1){
				this.user = user;
				this.password = password;
				isLoggedIn = true;System.out.println("\n" + "Welcome back " + user);
			}
			else{
				System.out.println("\nLogin or password do not match!");
				Thread.sleep(1000);
			}
			statement.close();
		} catch (SQLException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	public void changePassword(String newPassword){
		this.password = newPassword;
		try {	
			Connection con = this.connect();
			String updateSQL = "UPDATE passwords SET password = ? WHERE user = ?";

			PreparedStatement preparedStatement = con.prepareStatement(updateSQL);
			preparedStatement.setString(1, this.password);
			preparedStatement.setString(2, this.user);
			preparedStatement .executeUpdate();

			System.out.println("Changed password.");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	}}
	
	public void deleteAccount(){
		Connection con = this.connect();
		String deleteSQL = "DELETE FROM passwords WHERE user = ?";
		PreparedStatement preparedStatement;
		try {
			preparedStatement = con.prepareStatement(deleteSQL);
			preparedStatement.setString(1, this.user);
			preparedStatement.executeUpdate();
			System.out.println("You just deleted your account!");
			this.isLoggedIn = false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	}}

	private Connection connect() {
		Connection conn = null;
		try {
			Class.forName("org.sqlite.JDBC");
			String url = "jdbc:sqlite:"+ dbName;
			conn = DriverManager.getConnection(url);
		} catch (SQLException |ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
		return conn;
	}}
