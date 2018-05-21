//Import these items:
import java.io.File;
import java.io.FileFilter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.regex.Pattern;

import javax.swing.filechooser.FileSystemView;

//MAIN METHOD
public class CMD{
public static void main(String[] args) {
	Scanner input = new Scanner(System.in);
	String currentPath = System.getProperty("user.dir");
	String response = "";
	System.out.println("Jimbo's Operating System [ver1.1]\n(c) My Corporation. All rights reserved.\n\n");
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
			System.out.println("cd- change directory, used to see curent or switch to other directory on curent filepath");
			break;
		case "copy":    //help copy
			System.out.println("used to copy a file, often followed by a destination ");
			break;
		case "del":     //help del
			System.out.println("to delete selected files");
			break;
		case "dir":    //help dir
			System.out.println("dir- directory, displays current directory, and sub directories");
			break;
		case "exit":    //help exit
			System.out.println("end current command prompt session");
			break;
		case "md":     //help md
			System.out.println("md- make directory, followed by name of new directory");
			break;
		case "rd":     //help rd
			System.out.println("rd- remove directory, used to remove directory named after this command?");
			break;
		default:    //no help
			System.out.println("Looking for help in the wrong places???");
		}
	}
	
	//ADDITIONAL METHODS TO BE ADDED
	public static void dir(String currentPath){
		int totalByteSize = 0;
		int fileCount = 0;
		int dirCount = 0;
		String fileType = "";
		File dir = new File(currentPath);
        String driveLetter = currentPath.substring(0,1);
        File rootDirectory = new File(Paths.get(dir.getPath()).getRoot().toString());
        FileSystemView fsv = FileSystemView.getFileSystemView();
        String driveName = FileSystemView.getFileSystemView().getSystemDisplayName (rootDirectory);
        System.out.printf("Volume in drive %s:\\ is %s\n", driveLetter, driveName);
        File[] filesList = dir.listFiles();
        for(File file : filesList){
           totalByteSize += file.length();
           if (file.isDirectory()){
        	  fileType = "<DIR>"; 
        	  dirCount +=1;
           }
           else{
        	   fileType = "<File>";
        	   fileCount +=1;
           }
           
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy  hh:mm a");
        System.out.printf("     %s      %6s       %s\n",
        		sdf.format(file.lastModified()), fileType, file.getName());
        }
   //sample of output:   4 File(s)          1,351 bytes              bytes comes from accumulated totalByteSize
      //                    36 Dir(s)  1,838,806,347,776 bytes free     bytes free comes from dir.getFreeSpace()	
        System.out.printf("     %3d File(s)        %10d bytes\n",
        		fileCount, totalByteSize);
        System.out.printf("     %3d Dir(s)         %,20d bytes free\n",
        		dirCount, dir.getFreeSpace());
	}

	public static String cd(String response, String currentPath){
		//cd\ means go to root  (get first first letter of currentDirectory
		//cd\directory means to back to root and then the directory
		//cd.. means go up one directory (take currrentDirectory, parse up one directory
		//cd directory means goto this directory in current path  (add directory name to current path + / is necessary)
		//- note: you have to use \\ to signify a single \ because it is the escape char.
		//first, strip away cd  and any extra spaces  
		response = response.substring(2).trim();
	    if(response.substring(0,1).equals("\\")){
	    	if (response.length() == 1) {  //go to root
	    		currentPath = currentPath.substring(0,1);
	    	}
	    	
	    	else{
	    		if(Files.exists(Paths.get(currentPath.substring(0,2) + response))){
	    			currentPath = currentPath.substring(0,2) + response;
	    		}
	    		else {
	    			System.out.println(currentPath.substring(0,2) + response + " doesn't exist!");
	    		}
	    	}
	    }
	    else if(response.contains("..")){ // move up one directory
	    	if(currentPath.length()==2){
	    		System.out.println("Sorry, you are at the root already!");
	    	}
	    	else{
	    		int locOfLastBackSlash = currentPath.lastIndexOf("\\");
	    		currentPath = currentPath.substring(0,locOfLastBackSlash);
	    	}
	    }
	    else{
	    	if(Files.exists(Paths.get(currentPath +"\\" + response))){
	    		currentPath = currentPath + "\\" + response;
	    	}
	    	else System.out.println(currentPath + "\\" + response + " doesn't exist!");
	    }

		// if(response.contains(“..”))  you have to search for previous \ and parse out  \ to beginning of currentString
//		         return currentPath.substring(0,locOfLastBackSlash)
		// else      return  currentPath = “ \”    +  response

		return currentPath;
	}
	public static void md(String response, String currentPath){
			File.mkdir(response);
			currentPath = currentPath;
		
	}

	public static void rd(String response, String currentPath){}

	public static void rename(String response, String currentPath){}
	
	public static void del(String response, String currentPath){}

	public static void copy(String response, String currentPath){}
}