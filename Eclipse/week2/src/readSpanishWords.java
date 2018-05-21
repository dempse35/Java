import java.io.*;
import java.util.*;
public class readSpanishWords {

	public static void main(String[] args) {
		ArrayList<spanishWord> words = new ArrayList<spanishWord>();
		words = readTextFile();
		writeTextFile(words);
		writeBinaryFile(words);
		writeObjectFile(words);
		readObject();
	}
	public static ArrayList<spanishWord> readTextFile() {
		ArrayList<spanishWord> words = new ArrayList<spanishWord>();
		try {
			File file = new File("Spanish Translation.txt");		// attach to file
			Scanner scanner = new Scanner(file);					// scanner has nextline method
			for (int x = 0; x < 10; x++) { scanner.nextLine();}     //reading junk at top
			while(scanner.hasNextLine()){							// read until eof
				String oneLine = scanner.nextLine();  				// read a line
				spanishWord temp = new spanishWord(oneLine);		// create new spanish object
				words.add(temp);									// add to my array list
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return words;

	}
	public static void writeTextFile(ArrayList<spanishWord> words){
		try { 
			File file = new File("words.txt");
			PrintWriter pw = new PrintWriter(file);
			for (spanishWord e : words) {
				pw.println(e.getEnglishWord() + "\t" + e.getSpanishWord());
			}
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	public static void writeBinaryFile(ArrayList<spanishWord> words){
		try {
			FileOutputStream fos = new FileOutputStream("words.bin");
			BufferedOutputStream bos = new BufferedOutputStream(fos);
			DataOutputStream dos = new DataOutputStream(bos);
			for (spanishWord e : words){
				dos.writeUTF(e.getEnglishWord() + "\t" + e.getSpanishWord());
			}
			dos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static void writeObjectFile(ArrayList<spanishWord> words) {
		try {
			FileOutputStream fos = new FileOutputStream("wordss.txt");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(words);
			fos.close();
			oos.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void readObject() {
		ArrayList<spanishWord> words = new ArrayList<spanishWord>();
		try {
			FileInputStream fis = new FileInputStream("wordss.txt");
			ObjectInputStream ois = new ObjectInputStream(fis);
			words = (ArrayList<spanishWord>) ois.readObject();
			System.out.println("added " + words.size() + " new words");
			fis.close();
			ois.close();
			for(spanishWord e : words){
				System.out.println(e.getEnglishWord() + e.getSpanishWord());
			}
			
		
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		
		}
	}

}



 class spanishWord implements Serializable{
	private String spanishWord;
	private String englishWord;
	
	public spanishWord(){}
	public spanishWord(String wholeline){   //this constructor will read a line with both english and spanish words
		String[] tokens = wholeline.split("\t");
		this.englishWord=tokens[0];
		this.spanishWord=tokens[1];
	}
	public spanishWord (String spanishWord, String englishWord){
		this.spanishWord=spanishWord;
		this.englishWord=englishWord;
	}
	public String getSpanishWord(){
		return this.spanishWord;
	}
	public String getEnglishWord(){
		return this.englishWord;
	}
}
