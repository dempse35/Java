//Import these items:
import java.io.File;
import java.io.FileFilter;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.regex.Pattern;

//MAIN METHOD
public class CMD{
public static void main(String[] args) {
	Scanner input = new Scanner(System.in);
	String currentPath = System.getProperty("user.dir");
	String response = "";
	System.out.println("Tom's Operating System [ver1.1]\n(c) My Corporation. All rights reserved.\n\n");
	while (! response.equals("exit")){
		System.out.print(currentPath + ">");
		response = input.nextLine();
		if (response.startsWith("help")){  
			help(response);
		}
		else if (response.toLowerCase().startsWith("exit")){
			System.out.println("See ya!\nProgram terminated.");
			System.exit(0);
		}
		else if (response.toLowerCase().startsWith("dir")){
			dir(currentPath);
		}
		else if(response.toLowerCase().startsWith("cd")){
			currentPath = cd(response, currentPath);
		}
		else if (response.toLowerCase().startsWith("md")){
			md(response, currentPath);
		}
		else if (response.toLowerCase().startsWith("rd")){
			rd(response, currentPath);
		}
		else if (response.toLowerCase().startsWith("del")){
			del(response, currentPath);
		}
		else if (response.toLowerCase().startsWith("copy")){
			copy(response, currentPath);
		}
		else if (response.toLowerCase().startsWith("rename")){
			rename(response, currentPath);
		}
		else{
			System.out.println("Error: What kind of a command is " + response + "???");
		}
    	}
	System.out.println("Exiting Tom's Operating System [ver1.1]");
	}


	//HELP method
	public static void help(String response){  //receives what follows help in input statement
		response = response.replace("help", "").trim();   //get what follows help
		switch (response){
		case "":    //just help
			System.out.println("For more information on a specific command, type HELP command-name\n"+
					"CD             Displays the name of or changes the current directory.\n" +
					"COPY           Copies one or more files to another location.\n" +
					"DEL            Deletes one or more files.\n" +
					"DIR            Displays a list of files and subdirectories in a directory.\n" +
					"EXIT           Quits the CMD.EXE program (command interpreter)\n." +
					"HELP           Provides Help information for Windows commands.\n" +
					"MD             Creates a directory.\n" +
					"RD             Removes a directory.\n" +
					"For more information on tools see the command-line reference in the online help.\n\n\n");
			break;
		case "cd":   //help cd
			System.out.println("Something about cd???");
			break;
		case "copy":    //help copy
			System.out.println("Something about copy???");
			break;
		case "del":     //help del
			System.out.println("Something about del???");
			break;
		case "dir":    //help dir
			System.out.println("Something about dir???");
			break;
		case "exit":    //help exit
			System.out.println("Something about exit???");
			break;
		case "md":     //help md
			System.out.println("Something about md???");
			break;
		case "rd":     //help rd
			System.out.println("Something about rd???");
			break;
		default:    //no help
			System.out.println("Looking for help in the wrong places???");
		}
	}
	
	//ADDITIONAL METHODS TO BE ADDED
	public static void dir(String currentPath){}

	public static String cd(String response, String currentPath){
		return currentPath;
	}
	public static void md(String response, String currentPath){}

	public static void rd(String response, String currentPath){}

	public static void rename(String response, String currentPath){}
	
	public static void del(String response, String currentPath){}

	public static void copy(String response, String currentPath){}
}