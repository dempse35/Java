import java.nio.file.*;
import java.io.*;
public class DataReader {
	public InputStream input;
	
	public DataReader() {
		input = getClass().getResourceAsStream("data.txt");
	}
	
	public static void main(String[] args) {
		DataReader dataReader = new DataReader();
		System.out.println(dataReader.input);
		Path filePath = Paths.get("data.txt");
		int count = filePath.getNameCount();
		System.out.println("Path is " + filePath.toString());
		System.out.println("there are " + count + " elements in the file path");
		String fileName = "data.txt";
		String line = null;
		try {
			FileReader fileReader = new FileReader(fileName);
			BufferedReader bufferedReader= new BufferedReader(fileReader);
			while((line = bufferedReader.readLine()) != null) {
				System.out.println(line);
			}
			bufferedReader.close();
		}
	
		catch(FileNotFoundException ex) {
			System.out.println("Unable to open file '" + fileName + "'");                
		}
		catch(IOException ex) {
			System.out.println("Error reading file '" + fileName + "'");  
		}
	}
}



