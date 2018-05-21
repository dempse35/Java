import java.io.IOException;
import java.util.Scanner;

public class SQLitePasswordTest {
	static Scanner in = new Scanner(System.in);
	public static void main(String[] args) throws IOException, InterruptedException {
		SQLitePassword passwords = new SQLitePassword();
		Thread.sleep(1000);
		String selection = "";
		while(!selection.equals("X")){
			System.out.println("\n\n\n");
			if(passwords.isLoggedIn) System.out.print("Welcome member " + passwords.user + "\nYou can  (O)Logout\n\t (C)Change password\n\t (D)Delete Account: ");
			else System.out.print("STATE BANK KIOSK\nYou can (L)Login\n\t(N)New account\n\t(X)Exit: ");
			selection = in.next().toUpperCase();
			switch (selection){
			case "L": 	System.out.print("Type login:    ");
						String login = in.next();
						System.out.print("Type password: ");
						String password = in.next();
						passwords.login(login, password);
						Thread.sleep(1000);
						break;
			case "O":	passwords.isLoggedIn = false;
						System.out.println("Successfully logged out.");
						Thread.sleep(1000);
						break;
			case "D":	System.out.print("Are you sure you want to delete account? (y/n) ");
						if(in.next().toUpperCase().equals("Y")) passwords.deleteAccount();
						Thread.sleep(1000);
						break;
			case "N":	System.out.print("Type login:    ");
						login = in.next();	
						System.out.print("Type password: ");
						password = in.next(); 
						passwords.createNewLogin(login, password);
						Thread.sleep(1000);
						break;
			case "C":	System.out.print("Type new password: ");
						passwords.changePassword(in.next());
						Thread.sleep(1000);
						break;
			case "X": 	System.out.println("You are exiting the system. Goodbye.");
						break;
			default: 	System.out.println("You have made incorrect selection.");
						Thread.sleep(1000);
			}
		}
	}
}
