//Import these items:
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.regex.Pattern;

import javax.swing.filechooser.FileSystemView;

//MAIN METHOD
public class CMD2{
	public static void main(String[] args) { //completed
		Scanner input = new Scanner(System.in);
		String currentPath = System.getProperty("user.dir");
		String response = "";
		System.out.println("Tom's Operating System [ver1.1]\n(c) My Corporation. All rights reserved.\n\n");
		while (! response.equals("exit")){
			System.out.print(currentPath + ">");
			response = input.nextLine();
			if (response.startsWith("help")){  
				help(response.substring(4).trim());
			}
			else if (response.toLowerCase().startsWith("exit")){
				System.out.println("See ya!\nProgram terminated.");
				System.exit(0);
			}
			else if (response.toLowerCase().startsWith("dir")){
				dir(currentPath);
			}
			else if(response.toLowerCase().startsWith("cd")){
				currentPath = cd(response.substring(2).trim(), currentPath);
			}
			else if (response.toLowerCase().startsWith("copy")){
				copy(response.substring(4).trim(), currentPath);
			}
			else if (response.toLowerCase().startsWith("del")){
				del(response.substring(3).trim(), currentPath);
			}else if (response.toLowerCase().startsWith("md")){
				md(response.substring(2).trim(), currentPath);
			}
			else if (response.toLowerCase().startsWith("rename")){
				rename(response.substring(6).trim(), currentPath);
			}else if (response.toLowerCase().startsWith("rd")){
				rd(response.substring(2).trim(), currentPath);
			}
			else{
				System.out.println("Error: What kind of a command is " + response + "???");
			}
    	}
	System.out.println("Exiting Tom's Operating System [ver1.1]");
	}
	public static void help(String response){ //completed
		//receives what follows help in input statement
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
			System.out.println("Displays the name of or changes the current directory.");
			
			System.out.println("\n  ..   Specifies that you want to change to the parent directory.");
			System.out.println("  " + File.separator + "    Specifies that you want to change to the root directory.");
			break;
		case "copy":    //help copy
			System.out.println("Copies one or more files to another location.");
			break;
		case "del":     //help del
			System.out.println("Deletes one or more files.");
			break;
		case "dir":    //help dir
			System.out.println("Displays a list of files and subdirectories in a directory.");
			break;
		case "exit":    //help exit
			System.out.println("Quits the CMD.EXE program (command interpreter).");
			break;
		case "md":     //help md
			System.out.println("Creates a directory.");
			break;
		case "rd":     //help rd
			System.out.println("Removes a directory.");
			break;
		default:    //no help
			System.out.println("This command is not supported by the help utility.");
		}
	}
	public static void dir(String currentPath){ //completed
    //create vars to store totalByteSize, fileCount, dirCount which need to be displayed
		int totalByteSize = 0;
		int fileCount = 0;
		int dirCount = 0;
		String fileType = "";
    //You need to get info of current directory so create   
		File dir = new File(currentPath);
    
		//You need to get root name and drive name to print out
		String driveLetter = currentPath.substring(0,1);
		File rootDirectory = new File(Paths.get(dir.getPath()).getRoot().toString());
       FileSystemView fsv = FileSystemView.getFileSystemView();
       String driveName = FileSystemView.getFileSystemView().getSystemDisplayName (rootDirectory);
   //use printf to print out equivalent to:      Volume in drive E:\ is GRCC FLASH (E:)
       System.out.printf("Volume in drive %s:" + File.separator + " is %s\n", driveLetter, driveName);
       File[] filesList = dir.listFiles();
       for(File file : filesList){
    	   totalByteSize += file.length();
    	   if (file.isDirectory()){
    		   fileType = "<DIR>";
    		   dirCount +=1;
    	   }
    	   else{
    		   fileType = "<File>";
    		   fileCount += 1;
    	   }
    	   SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy  hh:mm a");
    	   System.out.printf("      %s    %6s          %s\n", 
    			   sdf.format(file.lastModified()), fileType, file.getName());
       }
       System.out.printf("   %3d File(s)    %10d bytes\n", fileCount, totalByteSize);
       System.out.printf("   %3d Dir(s)  %,13d bytes free\n", dirCount, dir.getFreeSpace());
	}
	public static String cd(String response, String currentPath){ //completed
		//cd\ means go to root  (get first first letter of currentDirectory
		//cd\directory means to back to root and then the directory
		//cd.. means go up one directory (take currrentDirectory, parse up one directory
		//cd directory means goto this directory in current path  (add directory name to current path + / is necessary)
		//- note: you have to use \\ to signify a single \ because it is the escape char.
		//                  you can also use File.separator
		//first, strip away cd  and any extra spaces  response = response.sub(2).trim();
		if(response.substring(0,1).equals(File.separator)){
			if(response.length()==1){    //got to root
				currentPath = currentPath.substring(0,2);
			}
			else{
				if(Files.exists(Paths.get(currentPath.substring(0,2) + response))){
					currentPath = currentPath.substring(0,2) + response;
				}
				else{
					System.out.println(currentPath.substring(0,2) + response + " doesn't exist!");
				}
			}
		}
		else if(response.contains("..")){
			if(currentPath.length()==2){
				System.out.println("Sorry, you are at the root already!");
			}
			else{  //move up one directory
			int locOfLastBackSlash = currentPath.lastIndexOf(File.separator);
				currentPath= currentPath.substring(0,locOfLastBackSlash);
			}
		}
		else{
			if(Files.exists(Paths.get(currentPath + File.separator + response))){
				currentPath = currentPath + File.separator + response;
			}
			else{
				System.out.println("    " + currentPath + File.separator + response + " doesn't exist.");
			}
		}
		return currentPath;
	}
	public static void md(String response, String currentPath){ //completed
		File file = new File(currentPath + File.separator + response);
		file.mkdir();  
	}
	public static void copy(String response, String currentPath){ //completed
		//copy rules:
		    //    allow only files from current directory
		    //    allow wild cards on both search file and destination file
		    //    allow to copy file only within currentPath
		    //syntax
		    //copy text.txt ^ text.ttt    note:  ^ = <space>
		    //copy *.txt ^ *.ttt                   
		//parse response into fileNameToBeCopied, fileExtensionToBeCopied, fileNameDestination, fileExtensionDestination
		String sourceFileName = response.substring(0,response.indexOf("."));
		String sourceExtension = response.substring(response.indexOf(".") +1, response.indexOf(" "));
		String destFileName = response.substring(response.indexOf(" ")+1, response.lastIndexOf("."));
		String destExtension = response.substring(response.lastIndexOf(".")+1);
		//get a list of Files that match fileName using provided filesThatMatch() method
		String sourceName = sourceFileName + "." + sourceExtension;
		File[]	files = filesThatMatch(sourceName, currentPath);
		//loop through the list of files using for (File file: fileList)
		for (File file: files){
		//     get current file name and extension
			String currentFile = file.toString();
			currentFile = currentFile.substring(currentFile.lastIndexOf(File.separator)+1);
			String currentFileName = currentFile.substring(0, currentFile.indexOf("."));
			String currentExtension	= currentFile.substring(currentFile.indexOf(".")+1);
		//     something like this:  file.toString().substring(thisFileString.lastIndexOf("\\")+1,thisFileString.lastIndexOf("."));
		//if destination file name has * then make it name of source file name
			if(destFileName.equals("*")) destFileName = currentFileName;
			if(destExtension.equals("*")) destExtension = currentExtension;
		//if destination file extension is * then make it the source file extension
		// make new file out of source file and currentFile
			String dest = currentPath + File.separator + destFileName +"."+ destExtension;
			File destinationFile = new File(dest);
			if (!destinationFile.exists()){
				try {
					Files.copy(file.toPath(), destinationFile.toPath());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			else {System.out.println("file already exists");}
		}
		//else ask user if they want to overwrite
		//    if yes, Files.copy(file.toPath(), destinationFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
	}  
	public static void del(String response, String currentPath){ //not completed
		String fileName = response.substring(0,response.indexOf("."));
		String fileExtension = response.substring(response.indexOf(".") +1, response.indexOf(" "));
		String destFileName = response.substring(response.indexOf(" ")+1, response.lastIndexOf("."));
		String destExtension = response.substring(response.lastIndexOf(".")+1);
		String sourceName = fileName + "." + fileExtension;
		File[]	bunchOfFiles = filesThatMatch(sourceName, currentPath);
		for (File file: bunchOfFiles){
				String currentFile = file.toString();
				currentFile = currentFile.substring(currentFile.lastIndexOf(File.separator)+1);
				String currentFileName = currentFile.substring(0, currentFile.indexOf("."));
				String currentExtension	= currentFile.substring(currentFile.indexOf(".")+1);
				if (destExtension.equals("*")) destExtension = currentExtension;
				if (destFileName.equals("*")) destFileName = currentFileName;
				file.delete();
		}
		
		//del rules:
		//	    allow only files from current directory
		//	    allow wild cards 
			//syntax	del text.txt
		//	            del   *.txt
		//	            del   text.*

		//parse response into fileName, fileExtension
		//get a list of Files that match fileName using provided filesThatMatch() method
		//loop through the list of files using for (File file: bunchOfFiles)
		//file.delete();
		
		
}
	public static void rename(String response, String currentPath){ //completed
		//syntax rename test.txt test2.txt
		//       rename test.txt test2.*
		//need to parse fileName, fileExtension, fileDestinationName, fileDestinationExtension
		String fileName = response.substring(0,response.indexOf("."));
		String fileExtension = response.substring(response.indexOf(".") +1, response.indexOf(" "));
		String destFileName = response.substring(response.indexOf(" ")+1, response.lastIndexOf("."));
		String destExtension = response.substring(response.lastIndexOf(".")+1);
		String sourceName = fileName + "." + fileExtension;
		
		//    both extensionNames should have the period included   e.g. .txt
		//if fileDestionationName = * then assign it the value of fileName
		//if fileDestinationExtension = .* then assign it the value of fileExtension
		//create two new File types,
				File source = new File(currentPath + File.separator + sourceName);
				File destination = new File(currentPath + File.separator + destFileName + "." + destExtension);
				if (!source.exists())System.out.println("does not exist");
		//if destination.exists() then get input for "File exists, do you want to overwrite? y/n"
		//   if yes, source.renameTo(destination)
				else  source.renameTo(destination);
				if (!destination.exists())System.out.println("do you want to overwrite? y/n");
				Scanner in = new Scanner(System.in);
				if(in.next().equalsIgnoreCase("y")) {
					System.out.println("Overwriting!!");
					source.renameTo(destination);
					in.close();
				}
				
		}
	
	public static void rd(String response, String currentPath){ //completed
		//parse dir to be deleted from response
		String dir = response;
		//create a new File    currentPath + File.separator + dir
		File delDir = new File(currentPath + File.separator + dir);
		//if !File.isDirectory then say directory doesn't exist
		if (!delDir.isDirectory()) System.out.println("This folder doesnt exist dummy");
		else{
		//get number of dirs using countDirectories
			int numOfDirs = countDirectories(delDir);
		//get number of files using countFiles
			int numOfFiles = countFiles(delDir);
		//display numOfDirs and numOfFiles and display to user
			System.out.println(numOfDirs + " directories and " + numOfFiles + " files, are you sure you want to delete all of this?");
			Scanner in = new Scanner(System.in);
		//scanner in if user wants to continue
			if(in.next().equalsIgnoreCase("yes")) {
				System.out.println("Deleting all of your files!!!");
				rdActual(response, currentPath);
				
			}
			in.close();
		}
		//if answer is yes, call rdActual(response, currentPath)
	}
	public static void rdActual(String response, String currentPath){ //completed
		String dir = response;
		File delDir = new File(currentPath + File.separator + dir);
		int numOfDirs = countDirectories(delDir);
		if (numOfDirs ==0) delDir.delete();
		else{
			String files[] = delDir.list();
		//repeat 2 lines from previous method to get dir to remove
		//if deleteFile.list().length==0
		//       deleteDir.delete();
		//else, we got issues because dir has dirs in it!
		//String files[] = removeDir.list();
 	   		for (String temp : files) {
 	      //    create new parameter strings for the recursive deletes
 	   			File fileDelete = new File(delDir, temp);
 	   			String tempFullPath = fileDelete.toString();
 	   			String tempFileName = tempFullPath.substring(tempFullPath.lastIndexOf("\\")+1, tempFullPath.length());
 	   			tempFullPath = tempFullPath.substring(0,tempFullPath.indexOf(tempFileName)-1);
 	      //recursive delete
 	   			rdActual("rd " + tempFileName, tempFullPath);
 	   		}
 	   	//  check the directory again, if empty then delete it
 	   		if(delDir.list().length==0){
 	   			delDir.delete();
 	   			System.out.println("Directory is deleted : " + delDir.getAbsolutePath());
 		  
 	   		}
		}
	}
	public static File[] filesThatMatch(String strFileName, String strPath){ //completed
		//note: strFileName can be either file name or path + file name
		   File copyPathFile = new File(strPath);
		   // http://stackoverflow.com/questions/10520566/regular-expression-wildcard-matching
		   StringBuilder sb = new StringBuilder(strFileName.length() + 25);
		   //convert fileName to regex
		   sb.append('^');
		   for (int i = 0; i < strFileName.length(); ++i) {
		     char c = strFileName.charAt(i);
		     String regexString = File.separator + ".[]{}()+-^$|";
		     if (c == '*')   sb.append(".*"); 
		     else if (c == '?')  sb.append('.');
		     
		     else if (regexString.indexOf(c) >= 0) {
		       sb.append(File.separator);
		       sb.append(c);
		     }
		     else    sb.append(c);
		   }
		   sb.append('$');
		   final Pattern pattern = Pattern.compile(sb.toString()); // Caution: could also throw an exception!
			//get array of files that meet regex criteria
		   File[] FileList = (copyPathFile).listFiles(new FileFilter(){
		     public boolean accept(File file) {
		       return pattern.matcher(file.getName()).matches();
		     }
		   });
		 return FileList;
		}
	public static int countDirectories(File directory) { //completed
		  int count = 0;
		  for (File file : directory.listFiles()) {
		    if (file.isDirectory()) {
		      count++;
		    }
		    if (file.isDirectory()) {
		      count += countDirectories(file);
		    }
		  }
		  return count;
		}
	public static int countFiles(File directory) { //completed
	  int count = 0;
	  for (File file : directory.listFiles()) 
	  {
	    if (file.isFile()) {
	      count++;
	    }
	    if (file.isDirectory()) {
	      count += countFiles(file);
	    }
	  }
	  return count;
	}
}