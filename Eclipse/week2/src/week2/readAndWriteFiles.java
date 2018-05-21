package week2;

import java.io.*;
import java.util.Scanner;

public class readAndWriteFiles {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		writeTextFile();
		readTextFile();
		writeBinaryFile();
		readBinaryFile();

	}
	/**
	 * 
	 */
	public static void writeTextFile(){
		String data = "this is sample text data";   
		try {
			File file = new File("data.txt");           // create file to attach to file
			PrintWriter pw = new PrintWriter(file);     // create PrintWriter to write to file
			pw.println(data);                           // use println to write data
			pw.close();                                 // always close writer
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	public static void readTextFile(){
		try {
			File file = new File("data.txt");         // create file to attach to file
			Scanner scanner = new Scanner(file);      // create scanner to read file
			String data = scanner.nextLine();         // reading one line at a time
			System.out.println(data);
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	public static void writeBinaryFile(){
		String data = "this is some same binary data";
		try {
			FileOutputStream fos = new FileOutputStream("data.bin");    // create fos to attach to file
			BufferedOutputStream bos = new BufferedOutputStream(fos);   // create bos for buffer
			DataOutputStream dos = new DataOutputStream(bos);           // create dos for writeUTF
			dos.writeUTF(data);											// writing a line of string
			dos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static void readBinaryFile(){
		try {
			FileInputStream fis = new FileInputStream("data.bin");     // create fis to attach to file
			BufferedInputStream bis = new BufferedInputStream(fis);	   // create bis for buffer
			DataInputStream dis = new DataInputStream(bis);			   // create dis to read a line
			String data = dis.readUTF();							   // read a string of binary
			System.out.println(data);
			dis.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
