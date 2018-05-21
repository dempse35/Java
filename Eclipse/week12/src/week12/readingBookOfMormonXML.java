package week12;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class readingBookOfMormonXML {
	static Connection dbConnection = null;
	static String dbName = "BookOfMormon.db";
	// we have to download file from https://www.ibiblio.org/xml/examples/religion/bom/bom.xml



	public static void main(String[] args) {
		//readXML();
		//connectDB();
		searchDatabase();

	}
	public static void searchDatabase(){
		  System.out.print("Type word to search for: ");
		  Scanner in = new Scanner(System.in);
		  String searchWord = in.nextLine();
		  if(dbConnection == null) connectDB();
		  try{
		    Statement statement = dbConnection.createStatement();
		    String sql = "SELECT * FROM verses WHERE verse LIKE '%" + searchWord + "%';";
		    ResultSet rs = statement.executeQuery(sql);
		    while (rs.next()){
		      System.out.println(rs.getString("book") + "  " + rs.getString("chapter") + "  " + rs.getString("verse"));
		    }
		  }catch (Exception e){e.printStackTrace();}
		}
	public static void readXML(){
		  Document doc;
		  try{
		    doc = DocumentBuilderFactory.newInstance()
		        .newDocumentBuilder().parse("Book of Mormon.xml");
		    //or if the .xml file is available online:
		    //doc = DocumentBuilderFactory.newInstance()
		    // .newDocumentBuilder().parse(new URL(urlString).openStream());
		    System.out.println("Root element :" + doc.getDocumentElement().getAttribute("biblename"));
		    System.out.println("----------------------------");
		    NodeList bookList = doc.getElementsByTagName("BIBLEBOOK");
		    for (int x = 0; x < bookList.getLength(); x++) {
		      Node bookNode = bookList.item(x);
		      Element bookElement = (Element) bookNode;
		      System.out.println("Book : " + bookElement.getAttribute("bname"));
		      String book = bookElement.getAttribute("bname");
		      NodeList chapterList = bookElement.getElementsByTagName("CHAPTER");
		      for (int y = 0; y < chapterList.getLength(); y++) {
		        Node chapterNode = chapterList.item(y);
		        Element chapterElement = (Element) chapterNode;
		        System.out.println("  Chapter " + chapterElement.getAttribute("cnumber"));
		        String chapter = chapterElement.getAttribute("cnumber");
		        NodeList verseList = chapterElement.getElementsByTagName("VERS");
		        for (int z = 0; z < verseList.getLength(); z++) {
		          Node verseNode = verseList.item(z);
		          Element verseElement = (Element) verseNode;
		          System.out.println("    Verse " + verseElement.getAttribute("vnumber") + ": " + verseElement.getTextContent());
		          String verse = verseElement.getAttribute("vnumber") + ": " + verseElement.getTextContent();
		  		writeToDb(book,chapter,verse);

		        }
		      }
		    }
		  }catch(Exception e) {e.printStackTrace();}
		}
	private static void writeToDb(String book, String chapter, String verse){
		  try{
		    if(dbConnection == null) connectDB();
		    String sql = "INSERT INTO verses (book, chapter, verse) VALUES (?,?,?);";
		    PreparedStatement preparedStatement = dbConnection.prepareStatement(sql);
		    preparedStatement.setString(1,book);
		    preparedStatement.setString(2,chapter);
		    preparedStatement.setString(3,verse);
		    preparedStatement .executeUpdate();
		  }catch (Exception e){System.out.println(e.getMessage());}
		}
	
	private static void connectDB() {
		  try {
		    Class.forName("org.sqlite.JDBC");
		    String url = "jdbc:sqlite:"+ dbName;
		    dbConnection = DriverManager.getConnection(url);
		    Statement statement = dbConnection.createStatement();
		    String sql = "CREATE TABLE IF NOT EXISTS verses " +
		        "('id' INTEGER PRIMARY KEY, 'book' TEXT, 'chapter' TEXT, 'verse' TEXT);";
		    statement.executeUpdate(sql);
		    //only execute next 2 lines when you want to delete all
		    //sql = "DELETE FROM verses;";
		    //statement.executeUpdate(sql);
		    statement.close();
		  } catch (Exception e) {System.out.println(e.getMessage());}
		}



}
