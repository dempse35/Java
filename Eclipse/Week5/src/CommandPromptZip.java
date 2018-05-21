import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Enumeration;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import javax.swing.filechooser.FileSystemView;

public class CommandPromptZip {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		String currentPath = System.getProperty("user.dir");
		System.out.println("Tom's Operating System [ver1.1]\n(c) My Corporation. All rights reserved.\n\n");
		String response = "";
		while (! response.equals("exit")){
			System.out.print(currentPath + ">");  //prints prompt    e.g. E:\junk>
			response = input.nextLine();
			if (response.startsWith("zipList"))
				zipList(response.substring(7).trim(), currentPath);
			else if (response.startsWith("zip"))
				zip(response.substring(3).trim(), currentPath);
			else if (response.startsWith("unzipOneFile"))
				unzipOneFile(response.substring(12).trim(), currentPath);
			else if (response.startsWith("unzip"))
				unZip(response.substring(5).trim(), currentPath);
			else if (response.startsWith("help")) 
				System.out.println("Help if not available.");
			else if (response.toLowerCase().startsWith("dir")) 
				System.out.println("dir if not available.");
			else if(response.toLowerCase().startsWith("cd")) 
				System.out.println("cd if not available.");
			else if (response.toLowerCase().startsWith("md")) 
				System.out.println("md if not available.");
			else if (response.toLowerCase().startsWith("rd"))
				System.out.println("rd if not available.");
			else if (response.toLowerCase().startsWith("del"))
				System.out.println("del if not available.");
			else if (response.toLowerCase().startsWith("copy"))
				System.out.println("copy if not available.");
			else if (response.toLowerCase().startsWith("rename"))
				System.out.println("rename if not available.");			
			else System.out.println("Error: What kind of a command is " + response + "???");
			}
		System.out.println("Exiting Tom's Operating System [ver1.1]");
		input.close();
	}
	//Edited from: https://www.mkyong.com/java/how-to-compress-files-in-zip-format/
		//this unzip will not do folders.. see above url
		public static void zip(String response, String currentPath){
			//the idea here is you will call method: zip fileName zipFileName
			//parse out file names
			String fileName = response.substring(0,response.indexOf("."));
			String fileExtension = response.substring(response.indexOf(".")+1, response.indexOf(" "));
			String zipName = response.substring(response.indexOf(" ")+1);
			File[] files = filesThatMatchThis(fileName + "." + fileExtension, currentPath);  //get a list of matching files
			try{
				ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(currentPath + File.separator + zipName));
				for(File file: files){   //loop through files
					if(file.isFile()){    //we are not doing folders/directories here this time
						ZipEntry ze = new ZipEntry(file.toString().substring(file.toString().lastIndexOf(File.separator)+1));
						byte[] buffer = new byte[1024];  //required for zos to write
						zos.putNextEntry(ze);
						FileInputStream in = new FileInputStream(file.toString());
						int len;
						while ((len = in.read(buffer)) > 0) {
							zos.write(buffer, 0, len);
						}
						in.close();
						zos.closeEntry();
					}
				}
				zos.close();
				System.out.println("Done");
			}
			catch(IOException ex){
				ex.printStackTrace();
			}
		}
		//Edited from://http://www.mkyong.com/java/how-to-decompress-files-from-a-zip-file/
		public static void unZip(String response,String currentPath){
			try{
				//the idea here is that you will call method: unZip zipFileName
				String zipFileName = response;
				String filePath = currentPath + File.separator + zipFileName;
				if (!new File(filePath).exists()){
					System.out.println(zipFileName + " does not exist.");
				}
				else{
					byte[] buffer = new byte[1024];  //required for zis
					ZipInputStream zis = new ZipInputStream(new FileInputStream(zipFileName));
					ZipEntry ze = zis.getNextEntry();  //get first entry
					while(ze!=null){
						String fileName = ze.getName();
						File file = new File(currentPath + File.separator + fileName);
						System.out.println("file unzip : "+ file.getAbsoluteFile());
						//next line required else you will hit FileNotFoundException
						new File(file.getParent()).mkdirs();
						FileOutputStream fos = new FileOutputStream(file); //required to write file
						int len;
						while ((len = zis.read(buffer)) > 0) {
							fos.write(buffer, 0, len);
						}
						fos.close();
						ze = zis.getNextEntry();
					}
					zis.closeEntry();
					zis.close();
				}
				System.out.println("Done");
			}
			catch(IOException ex){
				ex.printStackTrace();
			}
		}
	public static void unzipOneFile(String response, String currentPath){
		//syntax:  unzipOneFile fileName.ext zipFileName.zip
		String fileName = response.substring(0,response.indexOf(" "));
		String zipFileName = response.substring(response.indexOf(" ")+ 1);
		try{
			FileOutputStream fos = new FileOutputStream(fileName);
			ZipInputStream zin = new ZipInputStream(new BufferedInputStream(new FileInputStream(currentPath + File.separator + zipFileName)));
		//    create new FileOutputStream fos using fileName
		//    create a new ZipInputStream zin using a new BufferedInputStream using a new FileInputStream using currentPath, separator, and zipFileName
		//    create a mew 
			ZipEntry ze = null;
		    while ((ze = zin.getNextEntry()) != null){
		       if (ze.getName().equals(fileName)){
		//               create a new buffer 
		    	   byte[] buffer = new byte[1024];
		//               create 
		    	   int len = 0;
		//               
		    	   while ((len = zin.read(buffer))!=-1){
		//                   
		    		   fos.write(buffer,0,len);
		    		   break;
		    	   }
	    		   fos.close();

		       }

		       zin.close();
		       System.out.println("Done");
		    }
		       
		//               close out
		//               break   //I normally don't use breaks!!!
		//    close zin
		//    print done
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	public static void zipList(String response, String currentPath){
		//syntax:  zipList zipFileName.zip
		String zipFileName = response;
		try{
			//create new ZipFile zipFile using currentPath, separator, and zipFileName
			ZipFile zipFile = new ZipFile(currentPath + File.separator + zipFileName);
			//create an enum of entries in zip file        
			Enumeration<? extends ZipEntry> zipEntries = zipFile.entries();
			//loop 
			while (zipEntries.hasMoreElements()){
			//      print out    ((ZipEntry) zipEntries.nextElement()).getName()
				System.out.println(((ZipEntry) zipEntries.nextElement()).getName());
			}
			System.out.println("Done");
			//print done
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	public static File[] filesThatMatchThis(String strFileName, String strPath){   //note: strFileName can be either file name or path + file name
	    File copyPathFile = new File(strPath);
	    // http://stackoverflow.com/questions/10520566/regular-expression-wildcard-matching
	    StringBuilder sb = new StringBuilder(strFileName.length() + 25);
	    //convert fileName to regex
	    sb.append('^');
	    for (int i = 0; i < strFileName.length(); ++i) {
	      char c = strFileName.charAt(i);
	      if (c == '*')   sb.append(".*"); 
	      else if (c == '?')  sb.append('.');
	      else if ("\\.[]{}()+-^$|".indexOf(c) >= 0) {
	        sb.append('\\');
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
}
