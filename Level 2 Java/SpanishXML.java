import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class SpanishXML {

	public static void main(String[] args) {
		String textFileName = "Spanish Translation.txt";
		String xmlFileName = "Spanish Translation.xml";
		//fill ArrayList
		ArrayList<spanishWord> words = readTextFile(textFileName);
		//put ArrayList into xml document
		Document document = createXMLdocument(words);
		//write xml document to file
		writeXMLdocument(xmlFileName, document);
		//re-fill ArrayList from xml file
		words = null;
		words = readXMLfile(xmlFileName);
	}
	
	public static Document createXMLdocument(ArrayList<spanishWord> words){
		Document document = null;
		try {
			//New object of Document using DocumentBuilderFactory class and DocumentBuilder class
			
			// create rootElement with the name Words

			//appendChild document with rootElement
			
			//loop through all the words in ArrayList
			for (spanishWord word: words){
				//create child element with the name Word
				
				//appendChild rootElement with childElement
				
				//next three lines create English part of word
				//  create a childChildElement with the name English
				
				//  append childChildElement with a TextNode and the English part of word
				
				//  append childElement with childChildElement
				
				//next three lines create Spanish part of word
				//  create a childChildElement with the name Spanish
				
				//  append childChildElement with a TextNode and the Spanish part of word
				
				//  append childElement with childChildElement
				
			}
		} catch (Exception e) {    //cheated using global Exception!!
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Created document.");
		return document;
	}
	public static void writeXMLdocument(String fileName, Document document){
		Transformer transformer;
		try {
			//create a new transformer
			//		transformer = TransformerFactory.newInstance().newTransformer();
			//format transformer outputProperty with indent
			//		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			//format transformer outputProperty with indent amount
			//		transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
			//next 3 lines transforms document into xml file
			//		DOMSource source = new DOMSource(document);
			//		StreamResult result = new StreamResult(new File(fileName));
			//		transformer.transform(source, result);
		} catch (Exception e) {    //cheated using global Exception!!
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Wrote xml file.");
	}
	public static ArrayList<spanishWord> readXMLfile(String fileName){
		ArrayList<spanishWord> words = new ArrayList<spanishWord>();
		try {
			//create a new document using DocumentBuilderFactory
			//		Document document =	DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(fileName);
			//create a nodeList from document using getElementsByTagName("Word")
			//loop through nodeList
			for(int k=0;k<list.getLength();k++){
				//create a string english by using ((Element) list.item(k)).getElementsByTagName("English").item(0).getTextContent();
				//create a string spanish the same way
				//add a new index to words using the spanish constructor (english, spanish)
			}
		}
	 catch (Exception e){    //cheated using global Exception!!
			e.printStackTrace(System.out);
	}
		System.out.println("Re-created arrayList with " + words.size() + " words.");
		return words;
	}



	public static ArrayList<spanishWord> readTextFile(String fileName){
		ArrayList<spanishWord> words = new ArrayList<spanishWord>();
		try {
			File file = new File(fileName);
			Scanner scanner = new Scanner(file);            
			for(int x = 0; x < 10; x++){ scanner.nextLine();}
			while(scanner.hasNextLine()){                    
				String oneLine = scanner.nextLine();         
				spanishWord temp = new spanishWord(oneLine); 
				words.add(temp);                             
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Filled Array.");
		return words;
	}
}
class spanishWord {
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
